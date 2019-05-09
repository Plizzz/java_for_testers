package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreationTests() {
        long leftLimit = 10000000001L;
        long rightLimit = 99999999999L;
        long randomNumber = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        Contacts before = app.contact().all();

        ContactData newContact = new ContactData()
                .withFirstname("Michael")
                .withLastname("Webber")
                .withHomenumber(Long.toString(randomNumber))
                .withEmail("webberM@google.com")
                .withGroup("test1");

        app.contact().createContact(newContact);
        app.goTo().homePage();

        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }

}
