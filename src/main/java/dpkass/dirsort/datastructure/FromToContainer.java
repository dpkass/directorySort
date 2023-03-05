package dpkass.dirsort.datastructure;

import java.time.LocalDate;

public class FromToContainer {

  private LocalDate from;
  private LocalDate to;

  public LocalDate from() {
    return from;
  }

  public void setFrom(LocalDate from) {
    this.from = from;
  }

  public LocalDate to() {
    return to;
  }

  public void setTo(LocalDate to) {
    this.to = to;
  }
}
