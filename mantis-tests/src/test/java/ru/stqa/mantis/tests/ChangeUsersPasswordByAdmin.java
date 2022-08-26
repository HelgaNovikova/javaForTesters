package ru.stqa.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mantis.model.Account;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUsersPasswordByAdmin extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }
    @Test
    public void changeUsersPasswordByAdmin() throws IOException {
        app.getNavigationHelper().gotoManage().gotoManageUsers();
        Account account= new Account()
                .setUsername("test").setRealName("realtest").setEmail("test@test.tst");
        if (!app.getAccountHelper().checkAccountPresence(account)){
            app.getAccountHelper().createNewAccount(account);
        }
        app.getAccountHelper().chooseAccount(account);
        app.getAccountHelper().clickResetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, account.getEmail());
        String password = "password";
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(account.getUsername(), password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regexp = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regexp.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
