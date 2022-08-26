package ru.stqa.mantis.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.mantis.appmanager.ApplicationManager;

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

}
