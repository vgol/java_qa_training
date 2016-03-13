package vgol.java.qa.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    ContactData contact = new ContactData("John", "Smith", "somewhere", "john.smith@somewhere.org");
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(contact);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact();
    app.getContactHelper().clickEditOnElement();
    app.getContactHelper().fillContactFrom(contact);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
