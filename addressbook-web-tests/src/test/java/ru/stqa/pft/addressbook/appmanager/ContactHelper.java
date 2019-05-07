package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

    private void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void deleteSelectedContact() {
        click(By.cssSelector("[value='Delete']"));
        wd.switchTo().alert().accept();
    }

    private void initContactModification(int index) {
        wd.findElements(By.cssSelector("[alt='Edit']")).get(index).click();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactForm();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));

        for (WebElement element :
                elements) {
            String name = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String lastname = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            ContactData contact = new ContactData(id, name, lastname);
            contacts.add(contact);
        }

        return contacts;
    }

    public void delete(List<ContactData> before) {
        selectContact(before.size() - 1);
        deleteSelectedContact();
    }

    public void modify(List<ContactData> contacts, ContactData modifyContact) {
        initContactModification(contacts.size() - 1);
        fillContactForm(modifyContact, false);
        submitContactModification();
    }
}
