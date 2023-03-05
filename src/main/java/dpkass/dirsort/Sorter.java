package dpkass.dirsort;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;

public class Sorter {

  private final File sourceDirectory;
  private final File targetDirectory;

  private final LocalDate fromCreated;
  private final LocalDate toCreated;

  private final LocalDate fromModified;
  private final LocalDate toModified;

  private final boolean copyByCreated;
  private final boolean copyByModified;

  private final boolean cleanup;

  private final Set<String> unmigratedFiles = new HashSet<>();

  private final Directories directories = new Directories();

  public Sorter(File sourceDirectory, File targetDirectory, LocalDate fromCreated,
      LocalDate toCreated, LocalDate fromModified, LocalDate toModified, boolean copyByCreated,
      boolean copyByModified, boolean cleanup) {
    this.sourceDirectory = sourceDirectory;
    this.targetDirectory = targetDirectory;
    this.fromCreated = fromCreated.minusDays(1);
    this.toCreated = toCreated.plusDays(1);
    this.fromModified = fromModified.minusDays(1);
    this.toModified = toModified.plusDays(1);
    this.copyByCreated = copyByCreated;
    this.copyByModified = copyByModified;
    this.cleanup = cleanup;
  }

  void run() {
    DirectoryCreator dc = new DirectoryCreator(directories);
    if (!dc.make(targetDirectory, fromMin(), toMax())) {
      return;
    }
    migrateFiles(sourceDirectory);
    if (!unmigratedFiles.isEmpty()) {
      System.err.println("Couldn't migrate.\n" + String.join("\n", unmigratedFiles));
    }
    if (cleanup && !cleanUpEmpty()) {
      System.err.println("Couldn't clean up.");
    }
  }

  private LocalDate fromMin() {
    return Stream.of(fromCreated, fromModified).min(LocalDate::compareTo).orElse(LocalDate.MIN);
  }

  private LocalDate toMax() {
    return Stream.of(toCreated, toModified).max(LocalDate::compareTo).orElse(LocalDate.MAX);
  }

  private void migrateFiles(File dir) {
    for (File file : dir.listFiles()) {
      if (file.getName().startsWith(".")) return;
      if (file.isDirectory()) {
        migrateFiles(file);
      } else {
        migrateFile(file);
      }
    }
  }


  private void migrateFile(File file) {
    if (copyByCreated) {
      migrateFileBy(file, fromCreated, toCreated, "creationTime");
    }
    if (copyByModified) {
      migrateFileBy(file, fromModified, toModified, "lastModifiedTime");
    }
  }

  private void migrateFileBy(File file, LocalDate from, LocalDate to, String attribute) {
    try {
      LocalDate time = LocalDate.ofEpochDay(
          ((FileTime) Files.getAttribute(file.toPath(), attribute)).to(TimeUnit.DAYS));
      if (time.isAfter(from) && time.isBefore(to)) {
        FileUtils.copyFileToDirectory(file, directories.get(time.getYear(), time.getMonthValue()));
      }
    } catch (Exception e) {
      unmigratedFiles.add(file.getAbsolutePath());
    }
  }

  private boolean cleanUpEmpty() {
    try {
      for (File yearDir : targetDirectory.listFiles()) {
        for (File monthDir : yearDir.listFiles()) {
          if (FileUtils.isEmptyDirectory(monthDir)) {
            monthDir.delete();
          }
        }
        if (FileUtils.isEmptyDirectory(yearDir)) {
          yearDir.delete();
        }
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
