package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase{

    @Test
    public void testContactDelete(){
        app.getNavigationHelper().goHome();
        if (!app.getContactHelper().isContactPresented()){
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData("Olga", "Novikova", "Novikova", "tester", "nickname", "company", "address", "23445435345", "6785634", "3475687", "567789789", "dfgdffg@sf.ru", "mail2@mail.ru", "mail3@mail.ru", "homepage.com"));
            app.getNavigationHelper().goHome();
        };
        List <ContactData> contactsBefore = app.getContactHelper().getContacts();
        contactsBefore.remove(0);
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goHome();
        List <ContactData> contactsAfter = app.getContactHelper().getContacts();
        Assert.assertEquals(contactsBefore, contactsAfter);
    }
}
