package vgol.java.qa.addressbook.tests;

import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("group0", "text", "text"));
    }

}
