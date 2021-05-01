package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().returnToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Elena", "Alfutova",
              "Moscow, street Testovaya 77, 88", "849566655588", "test@test.ru", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactsList();
    app.getContactHelper().selectContact();
    app.getContactHelper().contactDeletion();
    app.getContactHelper().alertAccept();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size()- 1);

  }
}
