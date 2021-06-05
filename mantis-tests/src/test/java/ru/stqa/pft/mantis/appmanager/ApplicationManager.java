package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;

  private String browser;
  private RegistrationHelper registrationHelper;
  private FTPHelper ftp;
  private MailHelper MailHelper;
  private JamesHelper JamesHelper;
  private ResetPasswordHelper ResetPasswordHelper;
  private DbHelper dbHelper;
  private SoapHelper soapHelper;

  public ApplicationManager(String browser)  {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
 }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public FTPHelper ftp() {
    if (ftp == null) {
      ftp = new FTPHelper(this);
    }
    return ftp;
  }

  public WebDriver getDriver() {
    if(wd == null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseURL"));
    }
    return wd;
  }

  public MailHelper mail() {
    if (MailHelper == null) {
      MailHelper = new MailHelper(this);
    }
    return MailHelper;
  }

  public JamesHelper james() {
    if (JamesHelper == null) {
      JamesHelper = new JamesHelper(this);
    }
    return JamesHelper;
  }
  public ResetPasswordHelper resetPassword() {
    if (ResetPasswordHelper == null) {
      ResetPasswordHelper = new ResetPasswordHelper(this);
    }
    return ResetPasswordHelper;
  }
  public DbHelper db() {
    if (dbHelper == null) {
      dbHelper = new DbHelper();
    }
    return dbHelper;
  }
  public SoapHelper soap() {
    if (soapHelper == null) {
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }
}
