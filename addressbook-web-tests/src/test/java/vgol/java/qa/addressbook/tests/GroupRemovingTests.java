package vgol.java.qa.addressbook.tests;

import org.testng.annotations.Test;

public class GroupRemovingTests extends TestBase {

  @Test
  public void testGroupRemoving() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }
}
