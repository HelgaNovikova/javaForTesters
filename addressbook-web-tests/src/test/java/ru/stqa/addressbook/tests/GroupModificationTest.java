package ru.stqa.addressbook.tests;

import net.bytebuddy.build.ToStringPlugin;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.*;

public class GroupModificationTest extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isGroupPresented()){
            app.getGroupHelper().createGroup(new GroupData("novikova1", "novikovaHeader", "novikovaFooter"));
            app.getNavigationHelper().gotoGroups();
        }
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().chooseGroup(groupsBefore.size() - 1);
        app.getGroupHelper().initModification();
        GroupData newGroup = new GroupData(groupsBefore.get(groupsBefore.size() - 1).getId(),"novikovaM", "novikovaHeaderN", "novikovaFooterD");
        app.getGroupHelper().fillGroupData(newGroup);
        app.getGroupHelper().submitModification();
        groupsBefore.remove(groupsBefore.size() - 1);
        groupsBefore.add(newGroup);
        app.getNavigationHelper().gotoGroups();
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());;
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsBefore, groupsAfter);
    }
}
