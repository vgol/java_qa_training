package vgol.java.qa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactRemovingTests extends TestBase {

  @Test
  public void testContactRemoving() {
    app.gotoHomePage();
    app.selectElement();
    app.clickEditOnElement();
    app.deleteContactFromEdit();
  }
}
