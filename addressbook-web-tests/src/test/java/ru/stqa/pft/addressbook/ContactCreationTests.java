package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
  private WebDriver wb;
  private ContactData firstcase = new ContactData("Michael", "Webber", "89862551445", "webberM@google.com");

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    wb = new FirefoxDriver();
    wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wb.get("http://localhost:99/addressbook/index.php");
    login("admin", "secret");
  }

  @Test
  public void testContactCreationTests() {
    initContactCreation();
    fillContactForm(firstcase);
    submitContactForm();
    returnToHomePage();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    wb.findElement(By.linkText("Logout")).click();
    wb.quit();
  }

  private void returnToHomePage() {
    wb.findElement(By.linkText("home page")).click();
  }

  private void submitContactForm() {
    wb.findElement(By.name("submit")).click();
  }

  private void fillContactForm(ContactData contactData) {
    wb.findElement(By.name("firstname")).clear();
    wb.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    wb.findElement(By.name("lastname")).clear();
    wb.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    wb.findElement(By.name("home")).clear();
    wb.findElement(By.name("home")).sendKeys(contactData.getHomenumber());
    wb.findElement(By.name("email")).clear();
    wb.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void initContactCreation() {
    wb.findElement(By.linkText("add new")).click();
  }

  private void login(String username, String password) {
    wb.findElement(By.name("user")).click();
    wb.findElement(By.name("user")).clear();
    wb.findElement(By.name("user")).sendKeys(username);
    wb.findElement(By.name("pass")).clear();
    wb.findElement(By.name("pass")).sendKeys(password);
    wb.findElement(By.cssSelector("input[type=\"submit\"]")).click();
  }
}
