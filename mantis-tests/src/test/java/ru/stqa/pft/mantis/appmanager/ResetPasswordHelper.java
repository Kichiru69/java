package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResetPasswordHelper extends BaseHelper {


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

  public void loginAsAdmin() {
    wd.get(app.getProperty("web.baseURL") + "login_page.php");
    type(By.name("username"), "administrator");
    click(By.cssSelector("input[value='Вход']"));
    type(By.name("password"), "root");
    click(By.cssSelector("input[value='Вход']"));
  }
  public String getEmail() {
  WebElement contactEmail = wd.findElement(By.cssSelector("tbody > tr:nth-child(2) > td:nth-child(3)"));
  String email = contactEmail.getText();
  return email;
  }

}
