package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  private ContactData firstcase = new ContactData("Michael", "Webber", "89862551445", "webberM@google.com");

  @Test
  public void testContactCreationTests() {
    app.initContactCreation();
    app.fillContactForm(firstcase);
    app.submitContactForm();
    app.returnToHomePage();
  }

}
