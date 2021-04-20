package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().contactDeletion();
    app.getContactHelper().alertAccept();
    app.getNavigationHelper().returnToHomePage();

  }
}
