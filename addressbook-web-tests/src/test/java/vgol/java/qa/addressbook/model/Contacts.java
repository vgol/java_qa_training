package vgol.java.qa.addressbook.model;


import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData>{

  private Set<ContactData> delegete;

  private Contacts(Contacts contacts) {
    this.delegete = new HashSet<>(contacts);
  }

  public Contacts() {
    this.delegete = new HashSet<>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegete;
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

}
