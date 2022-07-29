package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactUpdateTest extends TestBase{

    @Test
    public void testUpdateContact(){
        app.getNavigationHelper().goHome();
        if (!app.getContactHelper().isContactPresented()){
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData("Olga", "Novikova", "Novikova", "tester", "nickname", "company", "address", "23445435345", "6785634", "3475687", "567789789", "dfgdffg@sf.ru", "mail2@mail.ru", "mail3@mail.ru", "homepage.com"));
            app.getNavigationHelper().goHome();
        };
        List <ContactData> contactsBefore = app.getContactHelper().getContacts();
        app.getContactHelper().chooseContact();
        app.getContactHelper().chooseEditOption();
        ContactData newContact = new ContactData(contactsBefore.get(0).getId(),"Olga1", "Novikova1", "Novikova1", "tester", "nickname", "company", "address", "23445435345", "6785634", "3475687", "567789789", "dfgdffg@sf.ru", "mail2@mail.ru", "mail3@mail.ru", "homepage.com");
        app.getContactHelper().fillContactData(newContact);
        app.getContactHelper().submitUpdateContact();
        app.getNavigationHelper().goHome();
        contactsBefore.remove(0);
        contactsBefore.add(newContact);
        List <ContactData> contactsAfter = app.getContactHelper().getContacts();
        Comparator<? super ContactData> byId = (Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());;
        contactsBefore.sort(byId);
        contactsAfter.sort(byId);
        Assert.assertEquals(contactsBefore, contactsAfter);
    }
}
