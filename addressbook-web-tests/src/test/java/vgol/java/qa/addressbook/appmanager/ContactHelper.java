package vgol.java.qa.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import vgol.java.qa.addressbook.model.ContactData;
import vgol.java.qa.addressbook.model.Contacts;
import vgol.java.qa.addressbook.model.GroupData;

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
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
  }

  private void initContactCreation() {
    click(By.linkText("add new"));
  }

  private void deleteContactFromEdit() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
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

  public void deleteFromEdit(ContactData contact) {
    initContactModificationById(contact.getId());
    deleteContactFromEdit();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
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
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
          .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
        .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
        .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public String infoFromInfoPage(ContactData contact) {
    initContactInfoById(contact.getId());
    return wd.findElement(By.id("content")).getText();
  }

  private void initContactInfoById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }

  public void addToGroup(int id, GroupData group) {
    selectContactById(id);
    Select dropdown = new Select(wd.findElement(By.name("to_group")));
    dropdown.selectByVisibleText(group.getName());
    wd.findElement(By.name("add")).click();
  }

  public void showGroup(String name) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
  }

  public void removeFromGroup(int id, GroupData group) {
    showGroup(group.getName());
    selectContactById(id);
    wd.findElement(By.name("remove")).click();
  }
}
