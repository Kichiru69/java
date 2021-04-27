package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().returnToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Elena", "Alfutova",
              "Moscow, street Testovaya 77, 88", "849566655588", "test@test.ru", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().contactDeletion();
    app.getContactHelper().alertAccept();
    app.getNavigationHelper().returnToHomePage();

  }
}
