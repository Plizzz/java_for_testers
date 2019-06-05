package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;

public class RegistrationHelper extends HelperBase {

    RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.id("username"), username);
        type(By.id("email-field"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void finish(String confirmationLink, String username, String password) {
        wd.get(confirmationLink);
        type(By.id("realname"), username);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    public void resetPassword(UserData user) {
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
        click(By.linkText(user.getUsername()));
        click(By.cssSelector("[value='Reset Password']"));
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    public void changePassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
