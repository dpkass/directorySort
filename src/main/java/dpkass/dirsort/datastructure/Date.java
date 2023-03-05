package dpkass.dirsort.datastructure;

import java.time.LocalDate;

public class Date {

  private FromToContainer any = new FromToContainer();
  private FromToContainer last_modified = new FromToContainer();
  private FromToContainer created = new FromToContainer();

  public void setAny(FromToContainer any) {
    this.any = any;
  }

  public void setLast_modified(FromToContainer last_modified) {
    this.last_modified = last_modified;
  }

  public void setCreated(FromToContainer created) {
    this.created = created;
  }

  public LocalDate createdFrom() {
    return select(any.from(), created.from(), LocalDate.EPOCH);
  }

  public LocalDate createdTo() {
    return select(any.to(), created.to(), LocalDate.now());
  }

  public LocalDate lastModifiedFrom() {
    return select(any.from(), last_modified.from(), LocalDate.of(2000, 1, 1));
  }

  public LocalDate lastModifiedTo() {
    return select(any.to(), last_modified.to(), LocalDate.now());
  }

  private LocalDate select(LocalDate override, LocalDate main, LocalDate alt) {
    return override != null ? override : main != null ? main : alt;
  }
}
