package vgol.java.qa.addressbook.tests;

import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().createContact(new ContactData("John", "Smith", "somewhere", "john.smith@somewhere.org"));
    }
}
