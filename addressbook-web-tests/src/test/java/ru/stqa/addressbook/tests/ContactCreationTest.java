package ru.stqa.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().fillContactData(new ContactData("Olga", "Novikova", "Novikova", "tester", "nickname", "company", "address", "23445435345", "6785634", "3475687", "567789789", "dfgdffg@sf.ru", "mail2@mail.ru", "mail3@mail.ru", "homepage.com"));
    app.getContactHelper().submitContactData();
    app.getSessionHelper().logout();
  }

}
