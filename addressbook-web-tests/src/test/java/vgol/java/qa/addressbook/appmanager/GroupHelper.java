package vgol.java.qa.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vgol.java.qa.addressbook.model.GroupData;
import vgol.java.qa.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  GroupHelper(WebDriver wd) {
    super(wd);
  }

  private Groups groupCache = null;

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

  private void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initModificationGroup();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteGroup();
    groupCache = null;
    returnToGroupPage();
  }

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }

    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(name);
      groupCache.add(group);
    }
    return new Groups(groupCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
