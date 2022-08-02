package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

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
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        int index = groupsBefore.size() - 1;
        app.getGroupHelper().chooseGroup(index);
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().gotoGroups();
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        groupsBefore.remove(index);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
