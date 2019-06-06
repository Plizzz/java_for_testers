package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws MessagingException, IOException {
        String password = "password";
        String newPassword = "changedPassword";

        app.registration().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        Users users = app.db().users();
        UserData user = users.iterator().next();

        app.apacheJames().drainEmail(user.getUsername(), password);

        app.registration().resetPassword(user);

//        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        List<MailMessage> mailMessages = app.apacheJames().waitForMail(user.getUsername(), password, 60000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, user.getEmail());
        app.registration().changePassword(confirmationLink, newPassword);

        assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
