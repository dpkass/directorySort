package dpkass.dirsort;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class DirectoryCreator {

  Directories directories;

  private final List<String> months =
      List.of("01_Jan", "02_Feb", "03_Mar", "04_Apr", "05_May", "06_Jun", "07_Jul", "08_Aug",
          "09_Sep", "10_Oct", "11_Nov", "12_Dec");


  public DirectoryCreator(Directories directories) {
    this.directories = directories;
  }

  boolean make(File target, LocalDate from, LocalDate to) {
    if (!target.exists()) {
      System.err.println("Couldn't create target Directories.");
      return false;
    }
    return createYears(target, from, to);
  }

  boolean createYears(File target, LocalDate from, LocalDate to) {
    for (int currentYear = from.getYear(); currentYear <= to.getYear(); currentYear++) {
      File currentDir = new File(target, String.valueOf(currentYear));
      if (!currentDir.exists() || !createMonths(currentDir, currentYear)) {
        System.err.println("Couldn't create subdirectories.");
        return false;
      }
    }
    return true;
  }

  boolean createMonths(File currentDir, int currentYear) {
    for (int i = 0; i < 12; i++) {
      String month = months.get(i);
      File f = new File(currentDir, month);
      if (!f.exists())
        return false;
      directories.add(currentYear, i + 1, f);
    }
    return true;
  }

}
