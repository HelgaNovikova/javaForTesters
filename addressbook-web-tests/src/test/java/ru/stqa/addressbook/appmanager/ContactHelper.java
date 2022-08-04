package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactData(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("home"), contactData.getHomePhoneNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void submitContactData() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void chooseContact() {
        click(By.xpath("//tr[@class=\"\"]/td[@class = \"center\"][1]/input"));
    }

    public void chooseContactById(int index) {
        click(By.xpath("//input[@value='" + index + "']"));
    }

    public void submitUpdateContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void chooseEditOption() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public boolean isContactPresented() {
        if (isElementPresent(By.xpath("//tr[@class=\"\"]/td[@class = \"center\"][1]/input"))) {
            return true;
        } else return false;
    }

    public void createContact(ContactData contact) {
        fillContactData(contact);
        submitContactData();
    }

    public Contacts getContactsSet() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = \"entry\"]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.findElement(By.xpath("td[3]")).getText();
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String allEmails = element.findElement(By.xpath("td[5]")).getText();
            String allPhones = element.findElement(By.xpath("td[6]")).getText();
            String postAddress = element.findElement(By.xpath("td[4]")).getText();
            ContactData contact = new ContactData().withId(id).withFirstName(name).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withPostAddress(postAddress);
            contacts.add(contact);
        }
        return contacts;
    }

    public void modifyContact(ContactData newContact) {
        chooseContact();
        chooseEditOption();
        fillContactData(newContact);
        submitUpdateContact();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String name = wd.findElement(By.xpath("//form[1]/input[3]")).getAttribute("value");
        String lastName = wd.findElement(By.xpath("//form[1]/input[5]")).getAttribute("value");
        String homePhoneNumber = wd.findElement(By.xpath("//form[1]/input[11]")).getAttribute("value");
        String mobilePhoneNumber = wd.findElement(By.xpath("//form[1]/input[12]")).getAttribute("value");
        String workPhoneNumber = wd.findElement(By.xpath("//form[1]/input[13]")).getAttribute("value");
        String email = wd.findElement(By.xpath("//form[1]/input[15]")).getAttribute("value");
        String email2 = wd.findElement(By.xpath("//form[1]/input[16]")).getAttribute("value");
        String email3 = wd.findElement(By.xpath("//form[1]/input[17]")).getAttribute("value");
        String postAddress = wd.findElement(By.xpath("//textarea[@name='address']")).getText();
        return new ContactData().withWorkPhoneNumber(workPhoneNumber).withMobilePhoneNumber(mobilePhoneNumber)
                .withHomePhoneNumber(homePhoneNumber).withFirstName(name).withLastName(lastName)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withPostAddress(postAddress);
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }
}
