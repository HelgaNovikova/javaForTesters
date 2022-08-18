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
        if (app.db().groups().size() == 0){
            app.getNavigationHelper().gotoGroups();
            app.getGroupHelper().createGroup(new GroupData().withName("novikova1").withHeader("novikovaHeader").withFooter("novikovaFooter"));
        }
    }

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroups();
        Groups groupsBefore = app.db().groups();
        GroupData modifyingGroup = groupsBefore.iterator().next();
        GroupData newGroup = new GroupData().withId(modifyingGroup.getId()).withName("novikovaM").withHeader("novikovaHeaderN").withFooter("novikovaFooterD");
        app.getGroupHelper().modifyGroup(newGroup);
        Groups groupsAfter = app.db().groups();
        assertThat(groupsAfter, equalTo(groupsBefore.without(modifyingGroup).withAdded(newGroup)));
        verifygroupListInUI();
    }
}
