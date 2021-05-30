package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResetPasswordHelper extends BaseHelper {


  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }
  public void initChooseMenu(int number) {
    click(By.cssSelector("#nav nav-list li::nth-child(" + number + ")"));
  }
  public void initChooseMenuAbove(int number) {
    click(By.cssSelector("#nav nav-tabs padding-18 li::nth-child(" + number + ")"));
  }
  public void initChooseUserById(int id) {
    click(By.cssSelector("href=manage_user_edit_page.php?user_id=" + id));
  }

  public void initResetPassword() {
    click(By.cssSelector("input[value='Сбросить пароль'"));
  }
  public void loginAsAdmin() {
    wd.get(app.getProperty("web.baseURL") + "signup_page.php");
    type(By.name("username"), "administrator");
    type(By.name("password"), "root");
    click(By.cssSelector("input[value='Вход']"));
  }
}
