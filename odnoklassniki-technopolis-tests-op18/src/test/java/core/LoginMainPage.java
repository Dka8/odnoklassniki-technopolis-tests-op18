package core;

import model.TestBot;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginMainPage extends HelperBase{

    private static final By EMAIL_FIELD = By.id("field_email");
    private static final By PASSWORD_FIELD = By.id("field_password");
    private static final By LOGIN_BUTTON = By.xpath(".//*[contains(@value,'Log in')]");

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не дождались поля ввода логина",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD),
                        5,
                        500));

        Assert.assertTrue("Не дождались поля ввода пароля",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD),
                        5,
                        500));
    }

    public UserMainPage doLogin(TestBot testBot) {
        type(testBot.getLogin(), By.id("field_email"));
        type(testBot.getPassword(), By.id("field_password"));

        Assert.assertTrue("Не найден кнопка для входа", isElementPresent(LOGIN_BUTTON));

        click(LOGIN_BUTTON);

        return new UserMainPage(driver);
    }
}