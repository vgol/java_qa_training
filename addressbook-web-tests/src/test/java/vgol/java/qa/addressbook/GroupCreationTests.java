package vgol.java.qa.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initCreateGroup();
    fillGroupForm(new GroupData("group0", "text", "text"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
