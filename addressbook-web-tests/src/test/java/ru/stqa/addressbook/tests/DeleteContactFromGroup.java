package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {

    @BeforeMethod
    public void preconditionsPresenceOfContactsAndGroups() {
        if (app.db().groups().size() == 0) {
            app.getNavigationHelper().gotoGroups();
            app.getGroupHelper().createGroup(new GroupData().withName("novikova1").withHeader("novikovaHeader").withFooter("novikovaFooter"));
        }
        app.getNavigationHelper().goHome();
        if (app.db().contacts().size() == 0) {
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData().withFirstName("Olga").withLastName("Novikova").withEmail("mail2@mail.ru").withHomePhoneNumber("567789789"));
            app.getNavigationHelper().goHome();
        }
        ;
    }

    public void checkIfContactHasAssignedGroup(ContactData contact) {
        if (contact.getGroups().size() == 0) {
            Groups groups = app.db().groups();
            GroupData group = groups.iterator().next();
            app.getContactHelper().addContactInGroup(contact, group);
        }
    }

    @Test
    public void testDeleteGroupFromContact() {
        ContactData contact = app.db().contacts().iterator().next();
        checkIfContactHasAssignedGroup(contact);
        GroupData group = contact.getGroups().iterator().next();
        Set<GroupData> groupsContactBefore = contact.getGroups();
        removeGroup(contact, group);
        groupsContactBefore.remove(group);
        app.db().refresh(contact);
        Set<GroupData> groupsContactAfter = contact.getGroups();
        assertThat(groupsContactAfter, equalTo(groupsContactBefore));
    }

    private void removeGroup(ContactData contact, GroupData group) {
        app.getNavigationHelper().goHome();
        app.getGroupHelper().chooseGroupForDeletionFromContact(group);
        app.getContactHelper().chooseContactById(contact.getId());
        app.getGroupHelper().submitDeletionGroupFromContact();
    }

}
