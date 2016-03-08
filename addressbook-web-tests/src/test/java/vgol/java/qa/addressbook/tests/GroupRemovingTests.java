package vgol.java.qa.addressbook.tests;

import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.GroupData;

public class GroupRemovingTests extends TestBase {

  @Test
  public void testGroupRemoving() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("group0", "text", "text"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }
}
