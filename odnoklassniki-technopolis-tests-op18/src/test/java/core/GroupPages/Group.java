package core.GroupPages;

import core.HelperBase;
import model.GroupType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Group extends HelperBase {
    private static final By NAME = By.xpath(".//div[@class='mctc-top']//h1[@class='mctc_name_tx']");
    private static final By DESCRIPTION = By.xpath(".//div[@class='mctc-top']//div[@class='group-info_desc']");
    private static final By TYPE = By.xpath(".//div[@class='mctc-top']//div[@class='group-info_category']");
    private static final By COVER = By.xpath(".//div[@class='card_wrp']//img[@class='add-happening_poster_img']");
    private static final By OTHER_ACTION = By.xpath(".//ul[@class='u-menu']//span[@data-active-class='u-menu_a__sub-open']");
    private static final By DELETE_GROUP = By.xpath(".//ul[@class='u-menu']//a[contains(@hrefattrs,'ButtonsRemoveAltGroup')]");
    private static final By DELETE_CONFIRM_BUTTON = By.xpath(".//div[@class='modal-new_hld']//input[@name='button_delete']");
    private static final By OTHER_OPTIONS = By.xpath(".//div[@class='mctc_navMenu __groups']//span[@class='mctc_navMenuDropdownSec']");
    private static final By GROUP_SETTINGS = By.xpath(".//ul[@class='u-menu']//a[contains(@hrefattrs, 'AltGroupTopCardButtonsEdit')]");

    private static final String COVER_ATTRIBUTE_SRC = "src";

    public Group(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не дождались поля с именем группы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(NAME),
                        5,
                        500));

        Assert.assertTrue("Не дождались поля с описанием группы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(DESCRIPTION),
                        5,
                        500));

        Assert.assertTrue("Не дождались поля с типом группы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(TYPE),
                        5,
                        500));

        Assert.assertTrue("Не дождались кнопки \"Другие действия\"",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(OTHER_ACTION),
                        5,
                        500));

        Assert.assertTrue("Не дождались поля с обложкой группы",
                explicitWait(
                        ExpectedConditions.presenceOfElementLocated(COVER),
                        5,
                        500));

        Assert.assertTrue("Не дождались пункта меню \"Настройки\"",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(GROUP_SETTINGS),
                        5,
                        500));

        Assert.assertTrue("Не дождались выпадающего меню \"Еще\"",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(OTHER_OPTIONS),
                        5,
                        500));

    }

    public String getGroupId(){
        String pageUrl = getCurrentUrl();
        Pattern p = Pattern.compile("([0-9]*$)");
        Matcher m = p.matcher(pageUrl);
        Assert.assertTrue("Id группы не найден в url", m.find());
        return m.group(0);
    }

    public String getGroupName(){
        WebElement name = getElement(NAME);
        Assert.assertNotNull("Не нашли поле с именем группы", name);
        return name.getText();
    }

    public String getGroupDescription(){
        WebElement description = getElement(DESCRIPTION);
        Assert.assertNotNull("Не нашли поле с описанием группы", description);
        return description.getText();
    }

    public String getGroupType(){
        WebElement type = getElement(TYPE);
        Assert.assertNotNull("Не нашли поле с типом группы", type);
        return type.getText();
    }

    public String getCoverUrl(){
        return getAttribute(COVER, COVER_ATTRIBUTE_SRC);
    }

    public void delete(){
        Assert.assertTrue("Не найдено кнопка \"Другие действия\"", isElementPresent(DESCRIPTION));
        click(OTHER_ACTION);

        Assert.assertTrue("Не дождались кнопки \"Другие действия\"",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(DELETE_GROUP),
                        5,
                        500));
        click(DELETE_GROUP);

        Assert.assertTrue("Не дождались кнопки подтверждения удаления группы",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(DELETE_CONFIRM_BUTTON),
                        5,
                        500));
        click(DELETE_CONFIRM_BUTTON);
    }

    public GroupSettings clickOnGroupSettings(){
        Assert.assertTrue("Не дождались пункта меню \"Настройки\"", isElementPresent(GROUP_SETTINGS));
        click(GROUP_SETTINGS);

        return new GroupSettings(driver);
    }
}
