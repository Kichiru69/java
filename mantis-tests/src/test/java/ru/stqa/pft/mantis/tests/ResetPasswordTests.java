package ru.stqa.pft.mantis.tests;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{


    @Test
  public void resetPasswordTest() throws InterruptedException, MessagingException, IOException {
      //app.db().contacts();

      app.resetPassword().loginAsAdmin();
      app.resetPassword().initChooseMenu(6);
      app.resetPassword().initChooseMenuAbove();
      app.resetPassword().initChooseUserById(2);
      app.resetPassword().initResetPassword();

      String email = "ealfutova@localhost.localdomein";
      String user = "Elena";
      String password = "password";
      //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      assertTrue(app.newSession().login(user, password));
    }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
