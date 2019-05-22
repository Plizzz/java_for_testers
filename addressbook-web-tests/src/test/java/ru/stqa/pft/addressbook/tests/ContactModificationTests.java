package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();

            ContactData newContact = new ContactData()
                    .withFirstname("Michael")
                    .withLastname("Webber")
                    .withMobilePhone("89862551445")
                    .withEmail("webberM@google.com")
                    .inGroup(groups.iterator().next())
                    .withPhoto(new File("src/test/resources/ninja.png"));

            app.contact().createContact(newContact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Michael")
                .withLastname("James")
                .withMobilePhone("89862551445")
                .withEmail("webberM@google.com")
                .withPhoto(new File("src/test/resources/ninja.png"));

        app.goTo().homePage();
        app.contact().modify(contact);

        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.withModify(modifiedContact, contact)));
    }
}
