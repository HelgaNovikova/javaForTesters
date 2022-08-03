package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts contactsBefore = app.getContactHelper().getContactsSet();
        ContactData deletingContact = contactsBefore.iterator().next();
        app.getContactHelper().chooseContactById(deletingContact.getId());
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goHome();
        Contacts contactsAfter = app.getContactHelper().getContactsSet();
        assertThat(contactsAfter, equalTo(contactsBefore.without(deletingContact)));
    }


}
