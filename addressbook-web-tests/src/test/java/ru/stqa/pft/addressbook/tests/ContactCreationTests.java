package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreationTests() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(firstcase);
    app.getContactHelper().submitContactForm();
    app.getContactHelper().returnToHomePage();
  }

}
