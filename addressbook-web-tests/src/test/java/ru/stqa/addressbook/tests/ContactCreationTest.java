package ru.stqa.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator <Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json = json + line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List <ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData newContact) throws Exception {
        app.getNavigationHelper().goHome();
        Contacts contactsBefore = app.getContactHelper().getContactsSet();
        app.getNavigationHelper().gotoAddNewContact();
        app.getContactHelper().createContact(newContact);
        app.getNavigationHelper().goHome();
        Contacts contactsAfter = app.getContactHelper().getContactsSet();
        newContact.withId(contactsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertThat(contactsAfter, equalTo(contactsBefore.withAdded(newContact)));
    }

}
