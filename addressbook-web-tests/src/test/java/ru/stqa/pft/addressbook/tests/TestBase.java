package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;

public class TestBase {

    ContactData createContact = new ContactData("Michael", "Webber", "89862551445", "webberM@google.com", "test1");
    ContactData modifyContact = new ContactData("Michael", "Webber", "89862551445", "webberM@google.com", null);

    final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
}
