package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
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
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.goTo().homePage();
        app.contact().delete(deletedContact);
        app.goTo().homePage();

        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
