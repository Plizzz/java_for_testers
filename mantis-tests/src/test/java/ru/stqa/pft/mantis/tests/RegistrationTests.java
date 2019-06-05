package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {
    //    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", now);
        String user = String.format("user%s", now);
        String password = "password";

        app.apacheJames().createUser(user, password);

        app.registration().start(user, email);
//        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        List<MailMessage> mailMessages = app.apacheJames().waitForMail(user, password, 6000000);

        String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, user, password);

        assertTrue(app.newSession().login(user, password));
    }

    //    @AfterMethod (alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
