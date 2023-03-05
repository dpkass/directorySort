package dpkass.dirsort.datastructure;

import java.time.LocalDate;

public class Options {

  private Date date = new Date();
  private Copy copy = new Copy();
  private boolean cleanup = true;

  public Date date() {
    return date;
  }

  public void setCopy(Copy copy) {
    this.copy = copy;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setCleanup(boolean cleanup) {
    this.cleanup = cleanup;
  }

  public LocalDate createdFrom() {
    return date.createdFrom();
  }

  public LocalDate createdTo() {
    return date.createdTo();
  }

  public LocalDate lastModifiedFrom() {
    return date.lastModifiedFrom();
  }

  public LocalDate lastModifiedTo() {
    return date.lastModifiedTo();
  }

  public boolean copyByCreated() {
    return copy.created();
  }

  public boolean copyByLastModified() {
    return copy.last_modified();
  }

  public boolean cleanup() {
    return cleanup;
  }
}
