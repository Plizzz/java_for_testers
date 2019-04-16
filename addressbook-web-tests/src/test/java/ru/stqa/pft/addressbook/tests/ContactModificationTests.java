package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(firstcase);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
