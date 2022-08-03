package ru.stqa.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroups();
    Groups groupsBefore = app.getGroupHelper().getGroupSet();
    GroupData newGroup = new GroupData().withName("novikova1").withHeader("novikovaHeader").withFooter("novikovaFooter");
    app.getGroupHelper().createGroup(newGroup);
    app.getNavigationHelper().gotoGroups();
    Groups groupsAfter = app.getGroupHelper().getGroupSet();
    newGroup.withId(groupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(newGroup)));
  }
}
