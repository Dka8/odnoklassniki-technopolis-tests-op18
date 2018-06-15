package core.GroupPages;

import core.GroupPages.Group;
import core.HelperBase;
import model.GroupInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class GroupCreator extends HelperBase {
    private static final By CREATE_BUTTON = By.id("hook_FormButton_button_create");
    private static final By CANCEL_BUTTON = By.id("button_cancel");

    public GroupCreator(WebDriver driver) {
        super(driver);
    }

    protected void check(){
        Assert.assertTrue("Не дождались кнопки \"Создать\"",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(CREATE_BUTTON),
                        5,
                        500));

        Assert.assertTrue("Не дождались кнопки \"Отмена\"",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(CANCEL_BUTTON),
                        5,
                        500));
    }

    protected abstract void fillData(GroupInfo groupInfo);

    public Group createGroup(GroupInfo groupInfo){
        fillData(groupInfo);
        return confirm();
    }

    public void abortCreatingGroup(GroupInfo groupInfo){
        fillData(groupInfo);
        cancel();
    }

    private Group confirm(){
        Assert.assertTrue("Не найдена кнопка \"Создать\"", isElementPresent(CREATE_BUTTON));
        click(CREATE_BUTTON);

        return new Group(driver);
    }

    private void cancel(){
        Assert.assertTrue("Не найдена кнопка отменить \"Отменить\"", isElementPresent(CANCEL_BUTTON));
        click(CANCEL_BUTTON);
    }
}
