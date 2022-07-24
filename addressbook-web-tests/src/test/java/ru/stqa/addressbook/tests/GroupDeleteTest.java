package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

public class GroupDeleteTest extends TestBase {

    @Test
    public void testGroupDelete(){
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isGroupPresented()){
            app.getGroupHelper().createGroup(new GroupData("novikova1", "novikovaHeader", "novikovaFooter"));
            app.getNavigationHelper().gotoGroups();
        }
        app.getGroupHelper().chooseGroup();
        app.getGroupHelper().deleteGroup();
    }

}
