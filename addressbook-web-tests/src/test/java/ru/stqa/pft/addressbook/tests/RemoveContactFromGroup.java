package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstname("Elena").withLastname("Alfutova")
                      .withAddress("Moscow, street Testovaya 77, 88").withHomePhone("849566655588")
                      .withEmail("test@test.ru")
              , true);

    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData contact = contacts.iterator().next();
    GroupData group = groups.iterator().next();

    int contactId = contact.getId();
    Groups groupInContact = contact.getGroups();
    int groupId = group.getId();
    if (!groupInContact.contains(group)) {
      app.contact().goToHomePage();
      app.contact().selectContactById(contactId);
      app.contact().addContactToGroupById(groupId);
    }
  }


  @Test
  public void testContactDeletionFromGroup() {
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData contact = before.iterator().next();
    GroupData group = groups.iterator().next();
    app.contact().goToHomePage();
    app.contact().selectContactById(contact.getId());
    app.contact().selectGroupFromDropDownByIdToRemove(group.getId());
    app.contact().selectContactById(contact.getId());
    app.contact().initRemoveContactFromGroup();
    assertThat(group.getId(), equalTo(contact.getGroups().stream().mapToInt((g) -> g.getId()).max().getAsInt()));
  }

}

