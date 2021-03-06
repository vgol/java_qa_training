package vgol.java.qa.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;
import vgol.java.qa.mantis.appmanager.ApplicationManager;
import vgol.java.qa.mantis.model.MailMessage;
import vgol.java.qa.mantis.model.MantisUser;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

public class TestBase {

  static final ApplicationManager app =
      new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bp");
  }

  @AfterSuite
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bp", "config_inc.php");
    app.stop();
  }

  MantisUser getRandomUser() {
    Iterator<MantisUser> iterator = app.db().users().iterator();
    MantisUser user = iterator.next();
    while (user.getId() == 1 || user.getName().equals(app.getProperty("web.adminLogin"))) {
      user = iterator.next();
    }
    return user;
  }

  String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    String status = app.soap().getIssueStatus(issueId);
    return !(status.equals("resolved") || status.equals("closed"));
  }
}
