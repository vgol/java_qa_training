package vgol.java.qa.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vgol.java.qa.mantis.model.MailMessage;
import vgol.java.qa.mantis.model.MantisUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangeUserPassword() throws IOException, MessagingException {
    String newPassword = String.format("%s", System.currentTimeMillis());
    MantisUser user = getRandomUser();
    app.adminUi().adminLogin();
    app.adminUi().resetUserPassword(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    app.registration().finish(confirmationLink, newPassword);

    assertTrue(app.newSession().login(user.getName(), newPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
