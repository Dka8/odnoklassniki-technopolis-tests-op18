package wrappers;

import core.HelperBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserDropDownActions extends HelperBase {
    private static final By ACTIONS_DROP_DOWN_LIST = By.xpath(".//div[@class = 'ucard-mini toolbar_ucard']//img[@class='ucard-mini_img']");
    private static final By EXIT_BUTTON = By.xpath(".//a[@data-l = 't,logoutCurrentUser']");
    private static final By CONFIRM_EXIT =
            By.xpath(".//div[@class='modal-new_center']//input[@name='logoff.confirm_not_decorate']");

    public UserDropDownActions(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не дождались выпадающего меню пользователя",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(ACTIONS_DROP_DOWN_LIST),
                        5,
                        500));

        Assert.assertTrue("По меню пользователя нельзя кликнуть",
                explicitWait(
                        ExpectedConditions.elementToBeClickable(ACTIONS_DROP_DOWN_LIST),
                        5,
                        500));
    }

    public void logOut(){
        Assert.assertTrue("Не найдено выпадающее меню пользователся ", isElementPresent(ACTIONS_DROP_DOWN_LIST));
        click(ACTIONS_DROP_DOWN_LIST);

        Assert.assertTrue("Не дождались кнопки выход",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(EXIT_BUTTON),
                        5,
                        500));

        click(EXIT_BUTTON);

        Assert.assertTrue("Не дождались кнопки подтверждения выхода",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(CONFIRM_EXIT),
                        5,
                        500));

        click(CONFIRM_EXIT);
    }
}
