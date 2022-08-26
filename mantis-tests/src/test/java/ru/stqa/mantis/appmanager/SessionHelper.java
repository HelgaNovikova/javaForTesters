package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
    public SessionHelper(ApplicationManager app) {
        super(app);
    }

//    public void logout() {
//        click(By.linkText("Logout"));
//    }

    public void login(String login, String password) {
        type(By.name("username"), login);
        click(By.xpath("//input[@value='Login']"));
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}
