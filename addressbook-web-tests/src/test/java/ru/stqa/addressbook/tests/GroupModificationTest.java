package ru.stqa.addressbook.tests;

import net.bytebuddy.build.ToStringPlugin;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isGroupPresented()){
            app.getGroupHelper().createGroup(new GroupData().withName("novikova1").withHeader("novikovaHeader").withFooter("novikovaFooter"));
            app.getNavigationHelper().gotoGroups();
        }
    }

    @Test
    public void testGroupModification(){
        Groups groupsBefore = app.getGroupHelper().getGroupSet();
        GroupData modifyingGroup = groupsBefore.iterator().next();
        GroupData newGroup = new GroupData().withId(modifyingGroup.getId()).withName("novikovaM").withHeader("novikovaHeaderN").withFooter("novikovaFooterD");
        app.getGroupHelper().modifyGroup(newGroup);
        Groups groupsAfter = app.getGroupHelper().getGroupSet();
        assertThat(groupsAfter, equalTo(groupsBefore.without(modifyingGroup).withAdded(newGroup)));
    }
}
