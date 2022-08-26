package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String userName, String userEmail) {
        wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
        type(By.id("username"), userName);
        type(By.name("email"), userEmail);
        click(By.xpath("//input[@value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//span[text()='Update User']"));
    }
}
