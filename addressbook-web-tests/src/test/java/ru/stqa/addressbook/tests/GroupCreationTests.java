package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroups();
    List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
    GroupData newGroup = new GroupData().withName("novikova1").withHeader("novikovaHeader").withFooter("novikovaFooter");
    app.getGroupHelper().createGroup(newGroup);
    app.getNavigationHelper().gotoGroups();
    List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();

    groupsBefore.add(newGroup);
    Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());;
    groupsBefore.sort(byId);
    groupsAfter.sort(byId);
    Assert.assertEquals(groupsBefore, groupsAfter);
   // app.getSessionHelper().logout();
  }
}
