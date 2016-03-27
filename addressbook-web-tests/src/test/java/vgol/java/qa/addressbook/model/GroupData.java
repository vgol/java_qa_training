package vgol.java.qa.addressbook.model;

public class GroupData {
  private int id = Integer.MAX_VALUE;
  private String name;
  private String header;
  private String footer;

  public int getId() {
    return id;
  }

  public String getGroupname() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withName(String groupname) {
    this.name = groupname;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }


  @Override
  public String toString() {
    return "GroupData{" +
        "id=" + id +
        ", groupname='" + name + '\'' +
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
