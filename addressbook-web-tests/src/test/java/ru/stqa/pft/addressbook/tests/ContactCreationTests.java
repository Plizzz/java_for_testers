package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreationTests() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(createContact, true);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().gotoHomePage();
  }

}
