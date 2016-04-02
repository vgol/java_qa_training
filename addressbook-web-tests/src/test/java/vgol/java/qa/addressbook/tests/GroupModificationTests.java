package vgol.java.qa.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.GroupData;
import vgol.java.qa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
        .withId(modifiedGroup.getId())
        .withName("group1")
        .withHeader("alt text")
        .withFooter("alt text");
    app.group().modify(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}
