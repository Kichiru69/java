package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = false)

  public void testContactModification() {
    app.goTo().returnToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Elena", "Alfutova",
              "Moscow, street Testovaya 77, 88", "849566655588", "test@test.ru", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactsList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Elena", "Alfutova",
            "Moscow, street Testovaya 66, 66", "849566655588", "test@test.ru", null);
    app.getContactHelper().fillContactForm(new ContactData("Elena", "Alfutova",
            "Moscow, street Testovaya 66, 66", "849566655588", "test@test.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.goTo().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
