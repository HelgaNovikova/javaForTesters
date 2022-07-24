package ru.stqa.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroups();
  /*  app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupData(new GroupData("novikova1", "novikovaHeader", "novikovaFooter"));
    app.getGroupHelper().submitGroupCreation();

   */
    app.getGroupHelper().createGroup(new GroupData("novikova1", "novikovaHeader", "novikovaFooter"));
    app.getNavigationHelper().gotoGroups();
    app.getSessionHelper().logout();
  }
}
