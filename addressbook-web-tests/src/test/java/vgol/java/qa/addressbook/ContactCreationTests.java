package vgol.java.qa.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    gotoHomePage();
    initContactCreation();
    fillContactFrom(new ContactData("John", "Smith", "somewhere", "john.smith@somewhere.org"));
    submitContactCreation();
    returnToHomePage();
  }
}
