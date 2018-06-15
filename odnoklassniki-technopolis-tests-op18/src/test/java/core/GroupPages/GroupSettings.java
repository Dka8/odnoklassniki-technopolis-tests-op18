package core.GroupPages;

import core.HelperBase;
import model.GroupInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupSettings extends HelperBase{
    private static final By BACK_TO_GROUP_MAIN_PAGE = By.xpath(".//div[@class='compact-profile']//a[contains(@hrefattrs, 'altGroupMain')]");
    private static final By GROUP_NAME_EDIT = By.xpath(".//div[@class='groups_interest group-settings']//input[@name='st.name']");
    private static final By GROUP_DESCRIPTION_EDIT = By.xpath(".//div[@class='groups_interest group-settings']//textarea[@name='st.description']");
    private static final By SAVE_CHANGES = By.xpath(".//div[@id='group_settings_sticky_save']//input[@name='button_save_settings']");
    private static final By DONT_SAVE_CHANGES = By.xpath(".//[@id='group_settings_sticky_save']//a[contains(@hrefattrs,'AltGroup_Settings_Cancel')]");

    public GroupSettings(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не дождались поля для возвращения на страницу группы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(BACK_TO_GROUP_MAIN_PAGE),
                        5,
                        500));

        Assert.assertTrue("Не дождались поля для редактирования названия группы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(GROUP_NAME_EDIT),
                        5,
                        500));

        Assert.assertTrue("Не дождались поля для редактирования описания группы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(GROUP_DESCRIPTION_EDIT),
                        5,
                        500));

        Assert.assertTrue("Не дождались кнопки для сохранения изменений",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(SAVE_CHANGES),
                        5,
                        500));

        Assert.assertTrue("Не дождались кнопки отмены изменений",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(SAVE_CHANGES),
                        5,
                        500));
    }

    public Group changeSetting(GroupInfo groupInfo){
        changeName(groupInfo);
        changeDescription(groupInfo);
        saveChanges();
        return backToGroupPage();
    }

    private void changeName(GroupInfo groupInfo){
        Assert.assertTrue("Не найдено поле для редактирования названия", isElementPresent(GROUP_NAME_EDIT));
        type(groupInfo.getName(), GROUP_NAME_EDIT);
    }

    private void changeDescription(GroupInfo groupInfo){
        Assert.assertTrue("Не найдено поле для редактирования описания", isElementPresent(GROUP_DESCRIPTION_EDIT));
        type(groupInfo.getDescription(), GROUP_DESCRIPTION_EDIT);
    }

    private void saveChanges(){
        Assert.assertTrue("Не найдена кнопка для сохранения названия", isElementPresent(SAVE_CHANGES));
        click(SAVE_CHANGES);
    }

    private Group backToGroupPage(){
        Assert.assertTrue("Не найдено поля для возврата на страницу группы", isElementPresent(BACK_TO_GROUP_MAIN_PAGE));
        click(BACK_TO_GROUP_MAIN_PAGE);

        return new Group(driver);
    }
}
