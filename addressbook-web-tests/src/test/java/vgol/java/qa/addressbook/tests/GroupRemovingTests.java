package vgol.java.qa.addressbook.tests;

import org.testng.annotations.Test;

public class GroupRemovingTests extends TestBase {

  @Test
  public void testGroupRemoving() {
    app.gotoGroupPage();
    app.selectElement();
    app.deleteGroup();
    app.returnToGroupPage();
  }
}
