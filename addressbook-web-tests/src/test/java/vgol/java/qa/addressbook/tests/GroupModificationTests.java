package vgol.java.qa.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("group0").withHeader("text").withFooter("text"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
        .withId(modifiedGroup.getId())
        .withName("group1")
        .withHeader("alt text")
        .withFooter("alt text");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(before.size(), after.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before,after);
  }
}
