package vgol.java.qa.addressbook;

import org.testng.annotations.Test;

public class ContactRemovingTests extends TestBase{

  @Test
  public void testContactRemoving() {
    gotoHomePage();
    selectElement();
    clickEditOnElement();
    deleteContactFromEdit();
  }
}
