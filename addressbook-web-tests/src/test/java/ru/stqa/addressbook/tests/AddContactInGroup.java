package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroup extends TestBase{

    @BeforeMethod
    public void preconditionsPresenceOfContactsAndGroups(){
        if (app.db().groups().size() == 0){
            app.getNavigationHelper().gotoGroups();
            app.getGroupHelper().createGroup(new GroupData().withName("novikova1").withHeader("novikovaHeader").withFooter("novikovaFooter"));
        }
        app.getNavigationHelper().goHome();
        if (app.db().contacts().size() == 0) {
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData().withFirstName("Olga").withLastName("Novikova").withEmail("mail2@mail.ru").withHomePhoneNumber("567789789"));
            app.getNavigationHelper().goHome();
        };
    }

    public void checkTheAmountOfContactGroups(ContactData contact, Groups groups){
        if (contact.getGroups().size() == groups.size()){
            app.getNavigationHelper().gotoGroups();
            app.getGroupHelper().createGroup(new GroupData().withName("novikova1").withHeader("novikovaHeader").withFooter("novikovaFooter"));
            app.getNavigationHelper().goHome();
        }
    }

    @Test
    public void testAddContactInGroup(){
        Groups groups = app.db().groups();
        ContactData contact = app.db().contacts().iterator().next();
        checkTheAmountOfContactGroups(contact, groups);
        groups = app.db().groups();
        Set<GroupData> groupsContactBefore = contact.getGroups();
        Iterator<GroupData> iterator = groups.iterator();
        GroupData group = iterator.next();
        while (checkIfGroupAlreadyAssigned(contact, group)){
            group = iterator.next();
        };
        app.getContactHelper().addContactInGroup(contact, group);
        app.db().refresh(contact);
        Set<GroupData> groupsContactAfter = contact.getGroups();
        groupsContactBefore.add(group);
        assertThat(groupsContactAfter, equalTo(groupsContactBefore));
    }

    private boolean checkIfGroupAlreadyAssigned(ContactData contact, GroupData group) {
        List<GroupData> listOfContactGroups = new ArrayList<GroupData>(contact.getGroups());
        for (int i = 0; i < listOfContactGroups.size(); i++){
            if (listOfContactGroups.get(i).getId() == group.getId()){
                return true;
            }
        }
        return false;
    }
}

