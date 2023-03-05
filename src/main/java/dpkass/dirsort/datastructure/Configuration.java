package dpkass.dirsort.datastructure;

import java.io.File;
import java.time.LocalDate;

public class Configuration {

  private Directory directory = new Directory();
  private Options options = new Options();

  public void setDirectory(Directory directory) {
    this.directory = directory;
  }

  public void setOptions(Options options) {
    this.options = options;
  }

  public String source() {
    return directory.source();
  }

  public String target() {
    return directory.target();
  }

  public LocalDate createdFrom() {
    return options.createdFrom();
  }

  public LocalDate createdTo() {
    return options.createdTo();
  }

  public LocalDate lastModifiedFrom() {
    return options.lastModifiedFrom();
  }

  public LocalDate lastModifiedTo() {
    return options.lastModifiedTo();
  }

  public boolean copyByCreated() {
    return options.copyByCreated();
  }

  public boolean copyByLastModified() {
    return options.copyByLastModified();
  }

  public boolean cleanup(){
    return options.cleanup();
  }
}
