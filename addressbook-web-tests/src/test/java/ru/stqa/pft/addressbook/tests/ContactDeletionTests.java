package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            ContactData newContact = new ContactData("Michael", "Webber", "89862551445", "webberM@google.com", "test1");
            app.contact().createContact(newContact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();

        app.contact().delete(before);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
