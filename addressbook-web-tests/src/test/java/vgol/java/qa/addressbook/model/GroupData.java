package vgol.java.qa.addressbook.model;

public class GroupData {
  private int id;
  private final String groupname;
  private final String header;
  private final String footer;

  public GroupData(String groupname, String header, String footer) {
    this.id = Integer.MAX_VALUE;
    this.groupname = groupname;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(int id, String groupname, String header, String footer) {
    this.id = id;
    this.groupname = groupname;
    this.header = header;
    this.footer = footer;
  }

  public int getId() {
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

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "GroupData{" +
        "id=" + id +
        ", groupname='" + groupname + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    return getGroupname() != null ? getGroupname().equals(groupData.getGroupname()) : groupData.getGroupname() == null;

  }

  @Override
  public int hashCode() {
    return getGroupname() != null ? getGroupname().hashCode() : 0;
  }
}
