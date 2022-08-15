package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTest extends TestBase{

    @BeforeMethod
    public void preconditions() {
        app.getNavigationHelper().goHome();
        if (app.db().groups().size() == 0){
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData().withFirstName("Olga").withLastName("Novikova").withEmail("mail2@mail.ru").withHomePhoneNumber("567789789"));
            app.getNavigationHelper().goHome();
        };
    }

    @Test
    public void testContactDelete(){
        Contacts contactsBefore = app.db().contacts();
        ContactData deletingContact = contactsBefore.iterator().next();
        app.getContactHelper().chooseContactById(deletingContact.getId());
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goHome();
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter, equalTo(contactsBefore.without(deletingContact)));
    }


}
