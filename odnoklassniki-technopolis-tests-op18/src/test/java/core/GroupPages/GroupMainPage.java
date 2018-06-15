package core.GroupPages;

import core.HelperBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupMainPage extends HelperBase {

    private static final By CREATE_NEW_GROUP = By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");
    private static final By GROUP_NAVIGATION = By.xpath(".//div[@id='hook_Block_MyGroupsNavBlock']//a[contains(@hrefattrs, User_MyGroupsNav_Header)]");

    public GroupMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //пример использования класса ExpectedConditions в сочетании с методом explicitWait из HelperBase
        Assert.assertTrue("Не дождались кнопки созданиия новой группы",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CREATE_NEW_GROUP), 5, 500));

        Assert.assertTrue("Не дождались панели навигации по группам",
                explicitWait(ExpectedConditions
                        .visibilityOfElementLocated(GROUP_NAVIGATION),
                        5,
                        500));
    }

    public void clickCreateButton() {
        click(By.id("hook_FormButton_button_create"));
    }

    public void typeGroupName(String groupName) {
        type(groupName, By.id("field_name"));
    }

    public void clickInterestGroup() {
        click(By.xpath(".//*[contains(@class,'create-group-dialog_img __interest')]"));
    }

    public GroupFactory clickCreateGroup() {
        Assert.assertTrue("Не найден элемент создания группы", isElementPresent(CREATE_NEW_GROUP));
        driver.findElement(CREATE_NEW_GROUP).click();

        return new GroupFactory(driver);
    }

    public GroupNavigationPage clickOnGroupNavigation(){
        Assert.assertTrue("Не найдена панель навигации по группам",
                isElementPresent(GROUP_NAVIGATION));

        click(GROUP_NAVIGATION);

        return new GroupNavigationPage(driver);
    }
}