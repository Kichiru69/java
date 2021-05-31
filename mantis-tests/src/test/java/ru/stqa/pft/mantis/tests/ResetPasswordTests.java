package ru.stqa.pft.mantis.tests;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{


    @Test
  public void resetPasswordTest() throws InterruptedException {
      app.db().contacts();

      app.resetPassword().loginAsAdmin();
      app.resetPassword().initChooseMenu(6);
      app.resetPassword().initChooseMenuAbove();
      app.resetPassword().initChooseUserById(2);
      app.resetPassword().initResetPassword();


    }
}
