package vgol.java.qa.addressbook;

import org.testng.annotations.Test;

public class GroupRemovingTests extends TestBase{

  @Test
  public void testGroupRemoving() {
    gotoGroupPage();
    selectElement();
    deleteGroup();
    returnToGroupPage();
  }
}
