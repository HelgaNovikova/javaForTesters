package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactUpdateTest extends TestBase {

    @BeforeMethod
    public void preconditions() {
        app.getNavigationHelper().goHome();
        if (app.db().groups().size() == 0) {
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData().withFirstName("Olga").withLastName("Novikova").withEmail("mail2@mail.ru").withHomePhoneNumber("567789789"));
            app.getNavigationHelper().goHome();
        };
    }

    @Test
    public void testUpdateContact() {
        Contacts contactsBefore = app.db().contacts();
        ContactData modifyingContact = contactsBefore.iterator().next();
        ContactData newContact = new ContactData().withId(modifyingContact.getId()).withFirstName("Olga1").withLastName("Novikova1")
                .withHomePhoneNumber("23445435345").withEmail("dfgdffg@sf.ru").withEmail2("sdfs").withEmail3("dfgdfg")
                .withMobilePhoneNumber("567567").withWorkPhoneNumber("456456").withSecondPhoneNumber("45645567");
        app.getContactHelper().modifyContact(newContact);
        app.getNavigationHelper().goHome();
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter, equalTo(contactsBefore.without(modifyingContact).withAdded(newContact)));
    }
}
