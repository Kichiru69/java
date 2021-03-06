package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    //attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups()
                .iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }

  private void modifyContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void initAddToGroup() {
    wd.findElement(By.name("add")).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//a[contains(@href, 'edit.php?id=" + id + "')]")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void alertAccept() {
    wd.switchTo().alert().accept();
  }

  public void contactDeletion() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectGroupFromDropDownByIdToRemove(int id) {
      wd.findElement(By.cssSelector(" form#right select option[value='" + id +"']")).click();
    }
  public void selectGroupFromDropDownByIdToAdd(int id) {
    wd.findElement(By.cssSelector("select[name='to_group'] > option[value='" + id +"']")).click();
  }


  public void initRemoveContactFromGroup() {
    wd.findElement(By.name("remove")).click();
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    goToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    modifyContactForm(contact);
    submitContactModification();
    goToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    contactDeletion();
    alertAccept();
    goToHomePage();
  }

  public void goToHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return contacts;
  }

  public void addContactToGroupById(int groupId) {
    Select groupsDropDown =
            new Select(wd.findElement(By.name("to_group")));
    groupsDropDown.selectByValue(String.valueOf(groupId));
    initAddToGroup();
  }

  public void addToGroup(ContactData contact, GroupData groupToAdd) {
    goToHomePage();
    selectContactById(contact.getId());
    selectGroupFromDropDownByIdToAdd(groupToAdd.getId());
    initAddToGroup();
  }
  public void removeContact(int contactId, int groupId) {
    goToHomePage();
    selectContactById(contactId);
    selectGroupFromDropDownByIdToRemove(groupId);
    selectContactById(contactId);
    initRemoveContactFromGroup();
  }
}
