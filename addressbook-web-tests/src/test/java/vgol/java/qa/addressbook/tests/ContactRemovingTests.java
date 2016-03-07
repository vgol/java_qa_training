package vgol.java.qa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactRemovingTests extends TestBase {

  @Test
  public void testContactRemoving() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().alertAccept();
  }

  @Test
  public void testContactRemovingFromEdit() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().clickEditOnElement();
    app.getContactHelper().deleteContactFromEdit();
  }
}
