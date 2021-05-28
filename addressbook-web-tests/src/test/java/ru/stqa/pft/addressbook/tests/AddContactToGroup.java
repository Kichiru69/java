package ru.stqa.pft.addressbook.tests;

import com.sun.org.apache.xpath.internal.operations.Equals;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

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
  }


  @Test
  public void testContactAddToGroup() {
    Groups allGroups = app.db().groups();
    Contacts contactsBefore = app.db().contacts();
    boolean createGroup = false;
    for (ContactData contact : contactsBefore) {
      Groups contactInGroups = contact.getGroups();
      allGroups.removeAll(contactInGroups);
      if (allGroups.size() > 0) {
        GroupData groupToAdd = allGroups.iterator().next();
        int contactId = contact.getId();
        app.goTo().HomePage();
        app.contact().addToGroup(contact, groupToAdd);
        ContactData addedContactAfter = app.db().contactWithId(contactId);
        assertThat(addedContactAfter.getGroups().size(), equalTo(contact.getGroups().size() + 1));
        assertThat(addedContactAfter.getGroups(), equalTo(contactInGroups.withAdded(groupToAdd)));
        break;
      } else {
        createGroup = true;
      }
    }

    if (createGroup) {
      GroupData newTestGroup = new GroupData().withName("test 10");
      app.goTo().groupPage();
      app.group().create(newTestGroup);
      Groups newAllGroups = app.db().groups();
      int id = newAllGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt();
      GroupData groupToAdd = app.db().groupWithId(id);
      ContactData addedContactBefore = contactsBefore.iterator().next();
      int contactId = addedContactBefore.getId();
      Groups contactInGroups = addedContactBefore.getGroups();
      app.goTo().HomePage();
      app.contact().addToGroup(addedContactBefore, groupToAdd);
      ContactData addedContactAfter = app.db().contactWithId(contactId);
      assertThat(addedContactAfter.getGroups().size(), equalTo(addedContactBefore.getGroups().size() + 1));
      assertThat(addedContactAfter.getGroups(), equalTo(contactInGroups.withAdded(groupToAdd)));
    }
  }


}
