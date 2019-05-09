package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
    ContactHelper(WebDriver wd) {
        super(wd);
    }

    private void submitContactForm() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomenumber());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void selectContact(int id) {
        wd.findElement(By.id(Integer.toString(id))).click();
    }

    private void deleteSelectedContact() {
        click(By.cssSelector("[value='Delete']"));
        wd.switchTo().alert().accept();
    }

    private void initContactModification(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactForm();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));

        for (WebElement element :
                elements) {
            String name = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String lastname = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String phoneNumber = element.findElement(By.cssSelector("td:nth-of-type(6)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstname(name)
                    .withLastname(lastname)
                    .withHomenumber(phoneNumber));
        }

        return contacts;
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteSelectedContact();
    }

    public void modify(ContactData modifyContact) {
        initContactModification(modifyContact.getId());
        fillContactForm(modifyContact, false);
        submitContactModification();
    }
}
