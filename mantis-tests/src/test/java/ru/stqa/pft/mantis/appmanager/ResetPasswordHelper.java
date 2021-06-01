package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

import java.util.concurrent.TimeUnit;

public class ResetPasswordHelper extends BaseHelper {

  public void resetPasswordAsAdmin(UserData userForChange) throws InterruptedException {
    app.resetPassword().login("administrator", "root");
    app.resetPassword().initChooseMenu(6);
    app.resetPassword().initChooseMenuAbove();
    app.resetPassword().initChooseUserById(userForChange.getId());
    app.resetPassword().initResetPassword();
  }

  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void initChooseMenu(int number) throws InterruptedException {
    wd.findElement(By.id("menu-toggler")).click();
    TimeUnit.SECONDS.sleep(1);
    wd.findElement(By.cssSelector("#sidebar > ul > li:nth-child(" + number + ")")).click();
  }

  public void initChooseMenuAbove() {
    click(By.linkText("Управление пользователями"));
  }

  public void initChooseUserById(int id) {
    wd.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']")).click();
  }

  public void initResetPassword() {
    click(By.cssSelector("input[value='Сбросить пароль'"));
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseURL") + "login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Вход']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Вход']"));
  }

  public void resetPasswordForUser(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type = 'submit']"));
  }
}
