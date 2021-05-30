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
  }

  @Test
  public void testContactDeletionFromGroup() {
    Contacts allContactsBefore = app.db().contacts();
    Groups allGroupsBefore = app.db().groups();
    boolean addGroupToContact = false;
    for (GroupData group : allGroupsBefore) {
      Contacts contactInGroup = group.getContacts();
      if (contactInGroup.size() > 0) {
        int groupId = group.getId();
        ContactData contactDeleteFromGroup = contactInGroup.iterator().next();
        int contactId = contactDeleteFromGroup.getId();
        app.contact().removeContact(contactId, groupId);
        GroupData removedGroup = app.db().groupWithId(groupId);
        assertThat(removedGroup.getContacts().size(), equalTo(contactInGroup.size() - 1));
        assertThat(removedGroup.getContacts(), equalTo(contactInGroup.without(contactDeleteFromGroup)));
        break;
      } else {
        addGroupToContact = true;
      }
    }
    if (addGroupToContact) {
      ContactData contactBeforeInGroup = allContactsBefore.iterator().next();
      GroupData groupBeforeWithContact = allGroupsBefore.iterator().next();
      int contactId = contactBeforeInGroup.getId();
      int groupId = groupBeforeWithContact.getId();
      Groups groupsInContact = contactBeforeInGroup.getGroups();
      app.contact().addToGroup(contactBeforeInGroup, groupBeforeWithContact);
      ContactData contactAfterInGroup = app.db().contactWithId(contactId);
      GroupData groupAfterWithContact = app.db().groupWithId(groupId);
      assertThat(contactAfterInGroup.getGroups(), equalTo(groupsInContact.withAdded(groupBeforeWithContact)));
      Contacts allContactsInGroup = groupAfterWithContact.getContacts();
      app.contact().removeContact(contactId, groupId);
      GroupData groupAfterDeleteContact = app.db().groupWithId(groupId);
      assertThat(groupAfterDeleteContact.getContacts().size(), equalTo(allContactsInGroup.size() - 1));
      assertThat(groupAfterDeleteContact.getContacts(), equalTo(allContactsInGroup.without(contactAfterInGroup)));

    }
  }

}