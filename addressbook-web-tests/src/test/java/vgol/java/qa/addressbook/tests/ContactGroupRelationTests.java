package vgol.java.qa.addressbook.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.ContactData;
import vgol.java.qa.addressbook.model.Contacts;
import vgol.java.qa.addressbook.model.GroupData;
import vgol.java.qa.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupRelationTests extends TestBase {

  @BeforeClass
  private void ensureContactExists() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
          .withFirstname("John")
          .withLastname("Smith")
          .withAddress("somewhere")
          .withEmail("john.smith@somewhere.org")
          .withHomePhone("+7 (495) 222 33 44")
          .withMobilePhone("89263332221")
          .withWorkPhone("333-66-88"));
    }
  }

  @BeforeClass
  private void ensureGroupExists() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("mygroup").withHeader("text").withFooter("text"));
    }
  }

  @Test
  public void testAddContactToGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    Groups before = contact.getGroups();
    app.goTo().homePage();
    app.contact().addToGroup(contact, group);
    Groups after = app.db().contacts().stream().filter((c) -> c.getId() == contact.getId())
        .collect(Collectors.toSet()).iterator().next().getGroups();

    assertThat(after.stream().map(ContactGroupRelationTests::compareJustNames).collect(Collectors.toSet()),
        equalTo(before.withAdded(group).stream()
            .map(ContactGroupRelationTests::compareJustNames).collect(Collectors.toSet())));
  }

  private static GroupData compareJustNames(GroupData group) {
    return new GroupData().withName(group.getName());
  }

  @Test(enabled = false)
  public void testRemoveContactFromGroup() {

  }
}
