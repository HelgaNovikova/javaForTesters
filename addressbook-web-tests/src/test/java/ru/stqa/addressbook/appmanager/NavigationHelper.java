package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroups() {
      click(By.linkText("groups"));
    }

    public void gotoAddNewContact() {
     // wd.findElement(By.id("content")).click();
        click(By.linkText("add new"));
    }
}
