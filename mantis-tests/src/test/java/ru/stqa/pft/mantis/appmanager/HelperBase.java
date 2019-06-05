package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
    ApplicationManager app;
    WebDriver wd;

    HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    void click(By locator) {
        wd.findElement(locator).click();
    }

    void type(By locator, String text) {
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login.php");
        type(By.id("username"), username);
        click(By.cssSelector("[value='Login']"));
        type(By.id("password"), password);
        click(By.cssSelector("[value='Login']"));
    }
}
