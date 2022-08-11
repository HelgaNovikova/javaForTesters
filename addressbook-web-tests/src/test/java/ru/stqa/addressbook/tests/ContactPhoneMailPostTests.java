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
                    .withEmail("mail@mail.ru").withHomePhoneNumber("1")
                    .withEmail2("mail2@mail.ru")
                    .withEmail3("mail3@mail.com").withMobilePhoneNumber("2").withWorkPhoneNumber("3")
                    .withSecondPhoneNumber("4"));
            app.getNavigationHelper().goHome();
        };
        contactFromMainPage = app.getContactHelper().getContactsSet().iterator().next();
        contactFromEditForm = app.getContactHelper().infoFromEditForm(contactFromMainPage);
    }

    @Test
    public void testPhonesEmailsPOstAddress() {
        String test = contactFromMainPage.getAllPhones();
        String test2 = mergePhones(contactFromEditForm);
        assertThat(contactFromMainPage.getAllPhones(),equalTo(mergePhones(contactFromEditForm)));
        assertThat(contactFromMainPage.getAllEmails(),equalTo(mergeMails(contactFromEditForm)));
        assertThat(contactFromMainPage.getPostAddress(),equalTo(contactFromEditForm.getPostAddress()));
    }

    private String mergePhones(ContactData contact){
        return Arrays.asList(contact.getHomePhoneNumber(), contact.getMobilePhoneNumber(),
                        contact.getWorkPhoneNumber(), contact.getSecondPhoneNumber())
                .stream().filter((s) -> !(s.equals("")))
                .map(ContactPhoneMailPostTests::cleanPhone)
                .collect(Collectors.joining("\n"));
    }

    private String mergeMails(ContactData contact){
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !(s.equals("")))
                .collect(Collectors.joining("\n"));
    }


    private static String cleanPhone(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }


}
