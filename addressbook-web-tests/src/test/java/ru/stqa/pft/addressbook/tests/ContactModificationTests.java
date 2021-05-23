package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstname("Elena").withLastname("Alfutova")
              .withAddress("Moscow, street Testovaya 77, 88").withHomePhone("849566655588")
              .withEmail("test@test.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Alena").withLastname("Alfutova")
            .withAddress("Moscow, street Testovaya 77, 88").withHomePhone("849566655588")
            .withEmail("test@test.ru").withMobilePhone("8916888666").withWorkPhone("8495654423")
            .withEmail2("email2@mail.com").withEmail3("email3@gmail.com");
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
