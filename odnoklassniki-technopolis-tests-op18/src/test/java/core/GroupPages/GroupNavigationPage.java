package core.GroupPages;

import core.HelperBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.LookupGroupWrapper;

import java.util.*;

public class GroupNavigationPage extends HelperBase {
    private static final By GROUP_WRAPPER = By.className("ugrid_i");


    public GroupNavigationPage(WebDriver driver) {
        super(driver);
    }

    protected void check(){
        Assert.assertTrue("Не дождались поля ввода логина",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(GROUP_WRAPPER),
                        5,
                        500));
    }

    public List<LookupGroupWrapper> getLookupGroups() {
        if(isElementPresent(GROUP_WRAPPER)){
            List<WebElement> webElements = driver.findElements(GROUP_WRAPPER);

            return LookupGroupWrapper.transform(webElements, driver);
        }

        return Collections.emptyList();
    }
}
