package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("UnnamedGroup"));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            ContactData newContact = new ContactData()
                    .withFirstname("Michael")
                    .withLastname("Webber")
                    .withMobilePhone("89862551445")
                    .withEmail("webberM@google.com")
                    .withPhoto(new File("src/test/resources/ninja.png"));

            app.contact().createContact(newContact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts().stream().filter(contact -> contact.getGroups().size() < groups.size()).collect(Collectors.toCollection(Contacts::new));

        if (contacts.size() == 0) {
            ContactData newContact = new ContactData()
                    .withFirstname("Michael")
                    .withLastname("Webber")
                    .withMobilePhone("89862551445")
                    .withEmail("webberM@google.com")
                    .withPhoto(new File("src/test/resources/ninja.png"));

            app.contact().createContact(newContact);
            app.goTo().homePage();
            contacts = new Contacts().withAdded(newContact);
        }
        app.goTo().homePage();

        ContactData modifyContact = contacts.iterator().next();
        groups.removeAll(modifyContact.getGroups());

        app.contact().addToGroup(modifyContact, groups.iterator().next());

        ContactData modified = app.db().contacts().stream().filter(c -> c.getId() == modifyContact.getId()).collect(Collectors.toList()).iterator().next();
        assertThat(modified.getGroups(), equalTo(modifyContact.getGroups().withAdded(groups.iterator().next())));
    }

    @Test
    public void testRemoveContactFromGroup() {

    }
}
