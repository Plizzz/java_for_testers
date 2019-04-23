package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreationTests() {
    app.getContactHelper().createContact(createContact);
  }

}
