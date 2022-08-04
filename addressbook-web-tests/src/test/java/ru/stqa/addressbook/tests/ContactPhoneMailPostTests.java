package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneMailPostTests extends TestBase {
    private ContactData contactFromMainPage;
    private ContactData contactFromEditForm;

    @BeforeMethod
    public void preconditions() {
        app.getNavigationHelper().goHome();
        if (!app.getContactHelper().isContactPresented()) {
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData().withFirstName("Olga").withLastName("Novikova")
                    .withEmail("mail2@mail.ru").withHomePhoneNumber("567789789").withEmail2("mail2@mail.ru")
                    .withEmail3("mail3@mail.com").withMobilePhoneNumber("345345 45").withWorkPhoneNumber("45-44-555"));
            app.getNavigationHelper().goHome();
        };
        contactFromMainPage = app.getContactHelper().getContactsSet().iterator().next();
        contactFromEditForm = app.getContactHelper().infoFromEditForm(contactFromMainPage);
    }

    @Test
    public void testPhones() {
        assertThat(contactFromMainPage.getAllPhones(),equalTo(mergePhones(contactFromEditForm)));
    }

    private String mergePhones(ContactData contact){
        return Arrays.asList(contact.getMobilePhoneNumber(), contact.getWorkPhoneNumber(), contact.getHomePhoneNumber())
                .stream().filter((s) -> !(s.equals("")))
                .map(ContactPhoneMailPostTests::cleanPhone)
                .collect(Collectors.joining("\n"));
    }

    private String mergeMails(ContactData contact){
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !(s.equals("")))
                .map(ContactPhoneMailPostTests::cleanPhone)
                .collect(Collectors.joining("\n"));
    }

    private static String cleanPhone(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    @Test
    public void testEmails(){
        assertThat(contactFromMainPage.getAllEmails(),equalTo(mergeMails(contactFromEditForm)));
    }

    @Test
    public void testPostAddress(){
        assertThat(contactFromMainPage.getPostAddress(),equalTo(contactFromEditForm.getPostAddress()));
    }
}
