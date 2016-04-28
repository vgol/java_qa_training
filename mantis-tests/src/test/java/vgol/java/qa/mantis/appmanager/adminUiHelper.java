package vgol.java.qa.mantis.appmanager;


import org.openqa.selenium.By;
import vgol.java.qa.mantis.model.MantisUser;

public class adminUiHelper extends HelperBase {

  adminUiHelper(ApplicationManager app) {
    super(app);
  }

  public void adminLogin() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.cssSelector("input[value='Login']"));
  }

  public void resetUserPassword(MantisUser user) {
    click(By.linkText("Manage Users"));
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", user.getId())));
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
