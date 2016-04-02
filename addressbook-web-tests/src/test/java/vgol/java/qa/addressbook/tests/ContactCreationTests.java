package vgol.java.qa.addressbook.tests;

import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.ContactData;
import vgol.java.qa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
        .withFirstname("John")
        .withLastname("Smith")
        .withAddress("somewhere")
        .withEmail("john.smith@somewhere.org");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
        contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }
}
