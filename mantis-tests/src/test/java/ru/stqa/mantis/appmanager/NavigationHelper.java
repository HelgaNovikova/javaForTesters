package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{
    private WebDriver wd = app.getDriver();

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public NavigationHelper gotoManage(){
        //click(By.xpath("//a[@href='/mantisbt-2.25.4/mantisbt-2.25.4/manage_overview_page.php\']"));
        click(By.xpath("//a[@href='" + app.getProperty("web.baseUrl").substring(16) + "manage_overview_page.php\']"));
        return this;
    }

    public NavigationHelper gotoManageUsers(){
        click(By.linkText("Manage Users"));
        return this;
    }
}

