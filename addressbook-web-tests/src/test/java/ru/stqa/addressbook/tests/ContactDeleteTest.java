package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase{

    @BeforeMethod
    public void preconditions() {
        app.getNavigationHelper().goHome();
        if (!app.getContactHelper().isContactPresented()){
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData().withFirstName("Olga").withLastName("Novikova").withEmail("mail2@mail.ru").withHomePhoneNumber("567789789"));
            app.getNavigationHelper().goHome();
        };
    }

    @Test
    public void testContactDelete(){
        List <ContactData> contactsBefore = app.getContactHelper().getContacts();
        contactsBefore.remove(0);
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goHome();
        List <ContactData> contactsAfter = app.getContactHelper().getContacts();
        Assert.assertEquals(contactsBefore, contactsAfter);
    }


}
