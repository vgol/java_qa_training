package vgol.java.qa.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;

  public ContactData(String firstname, String lastname, String address, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }
}
