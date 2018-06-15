package core.GroupPages;

import core.HelperBase;
import model.GroupType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GroupFactory extends HelperBase {
    public static final By PUBLIC_PAGE = By.xpath(".//div[@class='create-group-dialog']//a[@data-l='t,PAGE']");


    public GroupFactory(WebDriver driver) {
        super(driver);
    }

    public GroupCreator getNewGroupCreator(GroupType type){
        if(type == GroupType.PublicPage){
            clickPublicPage();
            return new PublicPageCreator(driver);
        }
        throw new NotImplementedException();
    }

    protected void check() {
        Assert.assertTrue("Не дождались поля для создания публичной страницы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(PUBLIC_PAGE),
                        5,
                        500));
    }

    public void clickPublicPage() {
        Assert.assertTrue("Не найдено поля для создания публичной страницы", isElementPresent(PUBLIC_PAGE));
        click(PUBLIC_PAGE);
    }
}
