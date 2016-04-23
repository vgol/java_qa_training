package vgol.java.qa.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import vgol.java.qa.addressbook.appmanager.ApplicationManager;
import vgol.java.qa.addressbook.model.ContactData;
import vgol.java.qa.addressbook.model.Contacts;
import vgol.java.qa.addressbook.model.GroupData;
import vgol.java.qa.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  static final ApplicationManager app =
      new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  void verifyGroupListInUi() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
          .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
          .collect(Collectors.toSet())));
    }
  }

  void verifyContactListInUi() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts.stream().map(TestBase::simplifyContacts).collect(Collectors.toSet()),
          equalTo(dbContacts.stream().map(TestBase::simplifyContacts).collect(Collectors.toSet())));
    }
  }

  private static ContactData simplifyContacts(ContactData contact) {
    return new ContactData().withId(contact.getId()).withFirstname(contact.getFirstname())
        .withLastname(contact.getLastname()).withAddress(contact.getAddress());
  }
}
