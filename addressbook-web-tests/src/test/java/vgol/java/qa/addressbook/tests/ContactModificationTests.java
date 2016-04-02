package vgol.java.qa.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.ContactData;
import vgol.java.qa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
          .withFirstname("Johm")
          .withLastname("Smith")
          .withAddress("somewhere")
          .withEmail("john.smith@somewhere.org"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
        .withId(modifiedContact.getId())
        .withFirstname("Drizzt")
        .withLastname("DoUrden")
        .withAddress("Menzoberanzan")
        .withEmail("drizzt@menzoberanzan");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
