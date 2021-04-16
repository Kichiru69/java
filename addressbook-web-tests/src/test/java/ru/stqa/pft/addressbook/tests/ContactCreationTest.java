package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Elena", "Alfutova", "Moscow, street Testovaya 77, 88", "849566655588", "test@test.ru"));
    app.getContactHelper().submit();
    app.getNavigationHelper().returnToHomePage();
  }



}
