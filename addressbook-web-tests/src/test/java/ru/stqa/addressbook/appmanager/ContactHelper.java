package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactData(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("title"), contactData.getJobTitle());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhoneNumber());
        type(By.name("mobile"), contactData.getMobilePhoneNumber());
        type(By.name("work"), contactData.getWorkPhoneNumber());
        type(By.name("fax"), contactData.getFaxNumber());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
    }

    public void submitContactData() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void chooseContact() {
        click(By.xpath("//tr[@class=\"\"]/td[@class = \"center\"][1]/input"));
       // "//*[@title=\"Select (Olga Novikova)\"]"
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

    public boolean isContactPresented(){
        if (isElementPresent(By.xpath("//tr[@class=\"\"]/td[@class = \"center\"][1]/input"))){
            return true;
        }
        else return false;
    }

    public void createContact(ContactData contact){
        fillContactData(contact);
        submitContactData();
    }
}
