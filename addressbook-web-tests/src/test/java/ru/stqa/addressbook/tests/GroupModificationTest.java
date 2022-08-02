package ru.stqa.addressbook.tests;

import net.bytebuddy.build.ToStringPlugin;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.*;

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
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        int index = groupsBefore.size() - 1;
        GroupData newGroup = new GroupData().withId(groupsBefore.get(index).getId()).withName("novikovaM").withHeader("novikovaHeaderN").withFooter("novikovaFooterD");
        app.getGroupHelper().modifyGroup(index, newGroup);
        groupsBefore.remove(index);
        groupsBefore.add(newGroup);
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());;
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsBefore, groupsAfter);
    }
}
