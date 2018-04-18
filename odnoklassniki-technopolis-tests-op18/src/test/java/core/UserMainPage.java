package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPage extends HelperBase{
    private static final By PHOTO_ADD_BUTTON = By.xpath(".//input[@type = 'file' and @name = 'photo']");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public void clickGroupsOnToolbar() {
        click(By.xpath(".//*[contains(@data-l,'userAltGroup')]"));
    }

    /**
     * Загружает фото
     */
    public void addPhoto(String pathname) {
        driver.findElement(PHOTO_ADD_BUTTON).sendKeys(pathname);
    }
}