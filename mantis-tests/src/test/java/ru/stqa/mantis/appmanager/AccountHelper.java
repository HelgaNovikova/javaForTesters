package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.Account;
import java.util.NoSuchElementException;

public class AccountHelper extends HelperBase {
    public AccountHelper(ApplicationManager app) {
        super(app);
    }

    public boolean checkAccountPresence(Account account) {
        try{
            wd.findElement(By.xpath("//a[text()='" + account.getUsername() + "']"));
            return true;
        }
        catch (NoSuchElementException e){
                return false;
           }
    }

    public void createNewAccount(Account account){
        clickCreateNewAccount();
        fillAccountData(account);
        submitNewAccountCreation();
    }

    public void clickCreateNewAccount() {
        click(By.linkText("Create New Account"));
    }

    public void fillAccountData(Account account) {
        type(By.name("username"), account.getUsername());
        type(By.name("realname"), account.getRealName());
        type(By.name("email"), account.getEmail());
    }

    public void submitNewAccountCreation() {
        click(By.xpath("//input[@value='Create User']"));
    }

    public void clickResetPassword() {
        click(By.xpath("//input[@value='Reset Password']"));
    }

    public void chooseAccount(Account account){
        click(By.xpath("//a[text()='"+ account.getUsername() + "']"));
    }
}
