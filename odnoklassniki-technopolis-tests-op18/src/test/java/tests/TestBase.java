package tests;

import core.UserMainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import wrappers.UserDropDownActions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TestBase {
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    private static final String BASE_URL = "https://ok.ru/";
    private static final By USER_DROP_DOWN_MENU = By.xpath(".//div[contains(@class, 'toolbar_dropdown_w')]");
    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        init();
    }

    @After
    public void tearDown() throws Exception {
        logOut();
        stop();
    }

    public void init() {
        ChromeDriverService driverService = ChromeDriverService.createDefaultService();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("allow-file-access-from-files");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        driver = new ChromeDriver(driverService, options);
        driver.manage().window().setSize(new Dimension(1600, 1200));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    public UserMainPage getUserMainPage(){
        driver.get(BASE_URL);
        return new UserMainPage(driver);
    }

    public void logOut(){
        getUserMainPage();
        UserDropDownActions menu = new UserDropDownActions(driver);
        menu.logOut();
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}