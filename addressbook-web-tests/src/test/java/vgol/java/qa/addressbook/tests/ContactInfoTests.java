package vgol.java.qa.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
          .withFirstname("Johm")
          .withLastname("Smith")
          .withAddress("somewhere")
          .withEmail("john.smith@somewhere.org")
          .withHomePhone("+7 (495) 222 33 44")
          .withMobilePhone("89263332221")
          .withWorkPhone("333-66-88"));
    }
  }

  @Test
  public void testContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoFromInfoPage = app.contact().infoFromInfoPage(contact);

    assertThat(cleaned(contactInfoFromInfoPage), equalTo(merged(contactInfoFromEditForm)));
  }

  private String merged(ContactData contact) {
    return Arrays.asList(fullName(contact), contact.getAddress().replaceAll("\n", ""),
        contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
        contact.getEmail(), contact.getEmail2(), contact.getEmail3())
        .stream().filter((s -> ! s.equals(""))).collect(Collectors.joining(""));
    }

  private String fullName(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastname())
        .stream().filter((s -> ! s.equals(""))).collect(Collectors.joining(" "));
  }

  private String cleaned(String contactInfo) {
    /*
    * Drop all newlines with spaces.
    * Drop letters with colons before phone numbers.
    * Drop this strange domain names in brackets after email addresses.
    */
    return contactInfo.replaceAll("\n", "").replaceAll("(H: |M: |W: )", "")
        .replaceAll("( |\\t)\\(www\\.([a-z0-9]*\\.)*[a-z0-9]+\\)","");
    }
}
