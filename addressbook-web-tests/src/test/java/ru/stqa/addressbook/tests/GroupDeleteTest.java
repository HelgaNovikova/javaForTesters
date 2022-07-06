package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {

    @Test
    public void testGroupDelete(){
        app.getNavigationHelper().gotoGroups();
        app.getGroupHelper().chooseGroup();
        app.getGroupHelper().deleteGroup();
    }

}
