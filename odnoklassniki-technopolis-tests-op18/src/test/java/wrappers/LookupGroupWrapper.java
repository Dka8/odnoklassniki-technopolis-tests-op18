package wrappers;

import core.GroupPages.Group;
import core.HelperBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LookupGroupWrapper extends HelperBase{

    private WebElement element;

    private static final By GROUP_TITLE = By.xpath(".//div[@class = 'caption']//a[contains(@hrefattrs, 'UserGroups_MiniList')]");

    public LookupGroupWrapper(WebElement element, WebDriver driver) {
        super(driver);
        this.element = element;
    }

    protected void check() {
        Assert.assertTrue("Не дождались поля с именем группы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(GROUP_TITLE),
                        5,
                        500));
    }

    /**
     * Возвращает true если отображается что-то
     */

    public String getGroupId() {
        String groupUrl = getAttribute(GROUP_TITLE, "href");
        Assert.assertNotNull("Таг href не найден в названии группы ");

        Pattern p = Pattern.compile("([0-9]*$)");
        Matcher m = p.matcher(groupUrl);
        Assert.assertTrue("Id группы не найден в url", m.find());
        return m.group(0);
    }

    public Group toGroup(){
        Assert.assertTrue("Не найден заголовок группы", isElementPresent(GROUP_TITLE));

        click(GROUP_TITLE);

        return new Group(driver);
    }

    public static List<LookupGroupWrapper> transform(List<WebElement> elements, WebDriver driver){
        List<LookupGroupWrapper> groups = new ArrayList<LookupGroupWrapper>();

        for (WebElement element : elements){
            groups.add(new LookupGroupWrapper(element, driver));
        }
        return groups;
    }

    public String getGroupName() {
        WebElement title = getElement(GROUP_TITLE);
        Assert.assertNotNull("Не нашли поле с именем группы", title);
        return title.getText();
    }
}
