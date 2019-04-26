package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAnElement()) {
            app.getContactHelper().createContact(createContact);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData modifyContact = new ContactData(before.get(before.size() - 1).getId(), "Robert", "Towards", "89862551445", "webberM@google.com");

        app.getContactHelper().initContactModification(before.size() - 1);
        app.getContactHelper().fillContactForm(modifyContact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(modifyContact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }
}
