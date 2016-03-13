package vgol.java.qa.addressbook.model;

public class GroupData {
  private final String id;
  private final String groupname;
  private final String header;
  private final String footer;

  public GroupData(String groupname, String header, String footer) {
    this.id = null;
    this.groupname = groupname;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(String id, String groupname, String header, String footer) {
    this.id = id;
    this.groupname = groupname;
    this.header = header;
    this.footer = footer;
  }

  public String getId() {
    return id;
  }

  public String getGroupname() {
    return groupname;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  @Override
  public String toString() {
    return "GroupData{" +
        "groupname='" + groupname + '\'' +
        ", id='" + id + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (getId() != null ? !getId().equals(groupData.getId()) : groupData.getId() != null)
      return false;
    return getGroupname() != null ? getGroupname().equals(groupData.getGroupname()) : groupData.getGroupname() == null;

  }

  @Override
  public int hashCode() {
    int result = getId() != null ? getId().hashCode() : 0;
    result = 31 * result + (getGroupname() != null ? getGroupname().hashCode() : 0);
    return result;
  }
}
