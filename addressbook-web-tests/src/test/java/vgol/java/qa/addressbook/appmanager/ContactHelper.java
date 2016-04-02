package vgol.java.qa.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vgol.java.qa.addressbook.model.ContactData;
import vgol.java.qa.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  ContactHelper(WebDriver wd) {
    super(wd);
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  private void returnToHomePage() {
    click(By.linkText("home page"));
  }

  private void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  private void fillContactFrom(ContactData contactData) {
    type((By.name("firstname")), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
  }

  private void initContactCreation() {
    click(By.linkText("add new"));
  }

  private void deleteContactFromEdit() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  private void clickEditOnElement() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  private void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  private void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  private void alertAccept() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactFrom(contact);
    submitContactCreation();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    alertAccept();
  }

  public void deleteFromEdit(ContactData deletedContact) {
    selectContactById(deletedContact.getId());
    clickEditOnElement();
    deleteContactFromEdit();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    clickEditOnElement();
    fillContactFrom(contact);
    submitContactModification();
    returnToHomePage();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.name("selected[]")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }
}
