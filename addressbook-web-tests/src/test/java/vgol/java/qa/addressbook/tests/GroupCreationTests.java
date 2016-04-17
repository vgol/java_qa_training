package vgol.java.qa.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.GroupData;
import vgol.java.qa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      list.add(new Object[]{new GroupData().withName("group" + i)
          .withHeader("header" + i).withFooter("footer0" + i)});
    }
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(
        group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData()
        .withName("group33'a").withHeader("text").withFooter("text"); // Invalid group name.
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
