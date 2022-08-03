package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class GroupDeleteTest extends TestBase {

    @BeforeMethod
    public void preconditions(){
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isGroupPresented()){
            app.getGroupHelper().createGroup(new GroupData().withName("novikova1").withHeader("novikovaHeader").withFooter("novikovaFooter"));
            app.getNavigationHelper().gotoGroups();
        }
    }

    @Test
    public void testGroupDelete(){
        Groups groupsBefore = app.getGroupHelper().getGroupSet();
        GroupData deletingGroup = groupsBefore.iterator().next();
        app.getGroupHelper().chooseGroupById(deletingGroup.getId());
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().gotoGroups();
        Groups groupsAfter = app.getGroupHelper().getGroupSet();
        assertThat(groupsAfter,equalTo(groupsBefore.without(deletingGroup)));
    }

}
