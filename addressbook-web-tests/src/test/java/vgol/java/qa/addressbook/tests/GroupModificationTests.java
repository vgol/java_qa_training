package vgol.java.qa.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("group0", "text", "text"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initModificationGroup();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "group1", "alt text", "alt text");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(group);
    Assert.assertEquals(new HashSet<GroupData>(before), new HashSet<GroupData>(after));
  }
}
