package core;

import core.GroupPages.GroupMainPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserMainPage extends HelperBase{
    private static final By PHOTO_ADD_BUTTON = By.xpath(".//input[@type = 'file' and @name = 'photo']");
    private static final By GROUPS_ON_TOOLBAR = By.xpath(".//*[contains(@data-l,'userAltGroup')]");
    //private static final By TOOLBAR = By.className("navigation");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не дождались раздела группы в тулбаре",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(GROUPS_ON_TOOLBAR),
                        5,
                        500));
    }

    public GroupMainPage clickGroupsOnToolbar() {
        Assert.assertTrue("Не найден раздел группы в тулбаре", isElementPresent(GROUPS_ON_TOOLBAR));
        click(GROUPS_ON_TOOLBAR);

        return new GroupMainPage(driver);
    }

    /**
     * Загружает фото
     */
    public void addPhoto(String pathname) {
        driver.findElement(PHOTO_ADD_BUTTON).sendKeys(pathname);
    }
}