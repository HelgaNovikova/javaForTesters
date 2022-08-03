package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import java.util.List;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupData(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void chooseGroupById(int index) {
        wd.findElement(By.cssSelector("input[value='" + index + "']")).click();
    }

    public void deleteGroup() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void initModification() {
        click(By.xpath("//div[@id='content']/form/input[6]"));
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group){
        initGroupCreation();
        fillGroupData(group);
        submitGroupCreation();
        }

    public boolean isGroupPresented() {
        if (isElementPresent(By.name("selected[]")))
        {
            return true;
        }
        else return false;
    }

    public Groups getGroupSet() {
        Groups groups = new Groups();
        List <WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element: elements){
            groups.add(new GroupData().withId(Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"))).
                    withName(element.getText()).withFooter(null).withHeader(null));
        };
        return groups;
    }

    public void gotoGroups() {
        if (isElementPresent(By.tagName("h1")) && isElementPresent(By.name("new"))){
            return;
        }
        else click(By.linkText("groups"));
    }

    public void modifyGroup(GroupData newGroup){
        chooseGroupById(newGroup.getId());
        initModification();
        fillGroupData(newGroup);
        submitModification();
        gotoGroups();
    }
}
