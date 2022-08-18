package ru.stqa.addressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.addressbook.appmanager.ApplicationManager;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", Browser.FIREFOX.browserName()));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] p){
        logger.info("Start test " + method.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod
    public void logTestStop(Method method, Object[] p){
        logger.info("Stop test "  + method.getName() + " with parameters " + Arrays.asList(p));
    }

    public void verifygroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups groupsFromDB = app.db().groups();
            Groups groupsFromUI = app.getGroupHelper().getGroupSet();
            assertThat(groupsFromDB.stream().map((g) ->
                            new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet()),
                    equalTo(groupsFromUI));
        }
    }

}
