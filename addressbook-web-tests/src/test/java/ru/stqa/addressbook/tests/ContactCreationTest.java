package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goHome();
    List <ContactData> contactsBefore = app.getContactHelper().getContacts();
    app.getNavigationHelper().gotoAddNewContact();
    ContactData newContact = new ContactData("Olga", "Novikova", "Novikova", "tester", "nickname", "company", "address", "23445435345", "6785634", "3475687", "567789789", "dfgdffg@sf.ru", "mail2@mail.ru", "mail3@mail.ru", "homepage.com");
    contactsBefore.add(newContact);
    app.getContactHelper().createContact(newContact);
    app.getNavigationHelper().goHome();
    List <ContactData> contactsAfter = app.getContactHelper().getContacts();
    Comparator<? super ContactData> byId = (Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());;
    contactsBefore.sort(byId);
    contactsAfter.sort(byId);
    Assert.assertEquals(contactsBefore, contactsAfter);
   // app.getSessionHelper().logout();
  }

}
