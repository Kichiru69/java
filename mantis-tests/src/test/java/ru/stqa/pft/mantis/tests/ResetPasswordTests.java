package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void resetPasswordTest() throws InterruptedException, MessagingException, IOException {

    Users allUsers = app.db().users();
    allUsers.removeIf(g -> g.getUsername().equals("administrator"));
    UserData userForChange = allUsers.iterator().next();
    app.resetPassword().resetPasswordAsAdmin(userForChange);
    String email = userForChange.getEmail();
    String user = userForChange.getUsername();
    long now = System.currentTimeMillis();
    String password = String.format("%s", now);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.resetPassword().resetPasswordForUser(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
