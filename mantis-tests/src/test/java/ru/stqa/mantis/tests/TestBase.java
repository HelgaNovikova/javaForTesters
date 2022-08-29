package ru.stqa.mantis.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import ru.stqa.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(Browser.FIREFOX.browserName());

    @BeforeSuite
    public void setUp() throws Exception {
        app.initWithUI();
        //  app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws Exception {
        //     app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }


    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (app.soap().getIssueStatus(issueId).equals("closed")) {
            return false;
        } else return true;
    }
}
