package vgol.java.qa.addressbook.tests;


import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("John", "Smith", "somewhere", "john.smith@somewhere.org"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().clickEditOnElement();
    app.getContactHelper().fillContactFrom(new ContactData("John", "Smith", "somewhere", "john.smith@somewhere.org"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

  }
}
