package dpkass.dirsort.datastructure;

public class Copy {
  private boolean created = true;
  private boolean last_modified = true;

  public boolean created() {
    return created;
  }

  public void setCreated(boolean created) {
    this.created = created;
  }

  public boolean last_modified() {
    return last_modified;
  }

  public void setLast_modified(boolean last_modified) {
    this.last_modified = last_modified;
  }
}
