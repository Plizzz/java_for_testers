package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

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
}
