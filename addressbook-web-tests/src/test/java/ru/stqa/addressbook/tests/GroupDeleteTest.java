package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTest extends TestBase {

    @Test
    public void testGroupDelete(){
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isGroupPresented()){
            app.getGroupHelper().createGroup(new GroupData("novikova1", "novikovaHeader", "novikovaFooter"));
            app.getNavigationHelper().gotoGroups();
        }
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().chooseGroup(groupsBefore.size() - 1);
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().gotoGroups();
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        groupsBefore.remove(groupsBefore.size() - 1);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
