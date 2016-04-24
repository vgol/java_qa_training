package vgol.java.qa.addressbook.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import vgol.java.qa.addressbook.model.ContactData;
import vgol.java.qa.addressbook.model.GroupData;
import vgol.java.qa.addressbook.model.Groups;

import java.util.OptionalInt;
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
    app.goTo().homePage();
    app.contact().showGroup("[all]");
    ContactData contact = app.db().contacts().iterator().next();
    Groups groups = app.db().groups();
    groups.removeAll(contact.getGroups());
    // If chosen contact already in all groups, create new contact.
    if (groups.size() == 0) {
      contact = new ContactData()
          .withFirstname("New")
          .withLastname("Character")
          .withAddress("somewhere")
          .withEmail("new.character@somewhere.org")
          .withHomePhone("+7 (495) 222 33 44")
          .withMobilePhone("89263332221")
          .withWorkPhone("333-66-88");
      app.contact().create(contact);
    }
    GroupData group = app.db().groups().iterator().next();
    Groups before = contact.getGroups();
    final int id = getMaxId();
    app.contact().addToGroup(id, group);
    Groups after = app.db().contacts().stream().filter((c) -> c.getId() == id)
        .collect(Collectors.toSet()).iterator().next().getGroups();

    assertThat(after.stream().map(ContactGroupRelationTests::compareJustNames).collect(Collectors.toSet()),
        equalTo(before.withAdded(group).stream()
            .map(ContactGroupRelationTests::compareJustNames).collect(Collectors.toSet())));
  }

  @Test
  public void testRemoveContactFromGroup() {
    app.goTo().homePage();
    app.contact().showGroup("[all]");
    ContactData contact = app.db().contacts().iterator().next();
    if (contact.getGroups().size() == 0) {
      app.contact().addToGroup(contact.getId(), app.db().groups().iterator().next());
      app.goTo().homePage();
    }
    // Renew contact data.
    final int id = contact.getId();
    Groups before = app.db().contacts().stream().filter((c) -> c.getId() == id)
        .collect(Collectors.toSet()).iterator().next().getGroups();
    GroupData leavedGroup = before.iterator().next();
    app.contact().removeFromGroup(contact.getId(), leavedGroup);
    Groups after = app.db().contacts().stream().filter((c) -> c.getId() == id)
        .collect(Collectors.toSet()).iterator().next().getGroups();

    assertThat(after.stream().map(ContactGroupRelationTests::compareJustNames).collect(Collectors.toSet()),
        equalTo(before.without(leavedGroup).stream()
            .map(ContactGroupRelationTests::compareJustNames).collect(Collectors.toSet())));
  }

  private static GroupData compareJustNames(GroupData group) {
    return new GroupData().withName(group.getName());
  }

  private int getMaxId() {
    OptionalInt maxId = app.db().contacts().stream().mapToInt(ContactData::getId).max();
    if (maxId.isPresent()) {
      return maxId.getAsInt();
    }
    return Integer.MAX_VALUE;
  }
}
