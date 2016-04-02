package vgol.java.qa.addressbook.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private WebDriver wd;

  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    switch (browser) {
      case BrowserType.FIREFOX:
        wd = new FirefoxDriver();
        break;
      case BrowserType.CHROME:
        wd = new ChromeDriver();
        break;
      case BrowserType.EDGE:
        // Driver is not stable yet. There may be problems.
        File edgewd = new File("c:/tools/MicrosoftWebDriver.exe");
        System.setProperty("webdriver.edge.driver", edgewd.getAbsolutePath());
        wd = new EdgeDriver();
        break;
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost:8080/addressbook");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    SessionHelper sessionHelper = new SessionHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
