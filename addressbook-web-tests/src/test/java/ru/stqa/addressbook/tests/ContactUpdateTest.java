package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactUpdateTest extends TestBase{

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
    public void testUpdateContact(){
        List <ContactData> contactsBefore = app.getContactHelper().getContacts();
        ContactData newContact = new ContactData().withId(contactsBefore.get(0).getId()).withFirstName("Olga1").withLastName("Novikova1")
                .withHomePhoneNumber("23445435345").withEmail("dfgdffg@sf.ru");
        app.getContactHelper().modifyContact(newContact);
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
