package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAnElement()) {
            app.getContactHelper().createContact(createContact);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(modifyContact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
