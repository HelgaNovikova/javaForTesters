package ru.stqa.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private RegistrationHelper registrationHelper;
    private MailHelper mailHelper;
    private FtpHelper ftp;
    private NavigationHelper navigationHelper;
    private String browser;
    private SessionHelper sessionHelper;
    private SoapHelper soapHelper;

    public AccountHelper getAccountHelper() {
        return accountHelper;
    }

    private AccountHelper accountHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    private WebDriver wd;

    public void initWithUI() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        navigationHelper = new NavigationHelper(this);

        sessionHelper = new SessionHelper(this);
        accountHelper = new AccountHelper(this);
        wd.get(properties.getProperty("web.baseUrl"));
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp(){
        if (ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public SoapHelper soap(){
        if (soapHelper == null){
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(Browser.FIREFOX.browserName())) {
                wd = new FirefoxDriver();
            } else if (browser.equals(Browser.CHROME.browserName())) {
                wd = new ChromeDriver();
            } else if (browser.equals(Browser.IE.browserName())) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail(){
        if (mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

}
