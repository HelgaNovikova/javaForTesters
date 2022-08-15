package ru.stqa.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator <Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator <Object[]> validGroupsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json = json + line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List <GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData newGroup){

    app.getNavigationHelper().gotoGroups();
    Groups groupsBefore = app.db().groups();
    app.getGroupHelper().createGroup(newGroup);
    app.getNavigationHelper().gotoGroups();
    Groups groupsAfter = app.db().groups();
    newGroup.withId(groupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(newGroup)));

  }
}
