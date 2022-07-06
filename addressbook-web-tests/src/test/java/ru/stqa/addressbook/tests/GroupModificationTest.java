package ru.stqa.addressbook.tests;

import net.bytebuddy.build.ToStringPlugin;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroups();
        app.getGroupHelper().chooseGroup();
        app.getGroupHelper().initModification();
        app.getGroupHelper().fillGroupData(new GroupData("novikovaM", "novikovaHeaderN", "novikovaFooterD"));
        app.getGroupHelper().submitModification();
    }
}
