package vgol.java.qa.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vgol.java.qa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

  GroupHelper(WebDriver wd) {
    super(wd);
  }

  private void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  private void submitGroupCreation() {
    click(By.name("submit"));
  }

  private void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getGroupname());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  private void initCreateGroup() {
    click(By.name("new"));
  }

  private void deleteGroup() {
    click(By.xpath("//div[@id='content']/form/input[5]"));
  }

  private void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void initModificationGroup() {
    click(By.xpath("//div[@id='content']/form/input[6]"));
  }

  private void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initCreateGroup();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public void modify(int index, GroupData group) {
    selectGroup(index);
    initModificationGroup();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }

  public void delete(int index) {
    selectGroup(index);
    deleteGroup();
    returnToGroupPage();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(name);
      groups.add(group);
    }
    return groups;
  }
}
