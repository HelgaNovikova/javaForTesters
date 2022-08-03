package ru.stqa.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goHome();
    Contacts contactsBefore = app.getContactHelper().getContactsSet();
    app.getNavigationHelper().gotoAddNewContact();
    ContactData newContact = new ContactData().withFirstName("Olga").withLastName("Novikova").withEmail("dfgdffg@sf.ru").withHomePhoneNumber("567789789");
    app.getContactHelper().createContact(newContact);
    app.getNavigationHelper().goHome();
    Contacts contactsAfter = app.getContactHelper().getContactsSet();
    newContact.withId(contactsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(contactsAfter, equalTo(contactsBefore.withAdded(newContact)));
  }

}
