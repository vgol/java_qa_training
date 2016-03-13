package vgol.java.qa.addressbook.model;

public class GroupData {
  private final String groupname;
  private final String header;
  private final String footer;

  public GroupData(String groupname, String header, String footer) {
    this.groupname = groupname;
    this.header = header;
    this.footer = footer;
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
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    return groupname != null ? groupname.equals(groupData.groupname) : groupData.groupname == null;

  }

  @Override
  public int hashCode() {
    return groupname != null ? groupname.hashCode() : 0;
  }
}
