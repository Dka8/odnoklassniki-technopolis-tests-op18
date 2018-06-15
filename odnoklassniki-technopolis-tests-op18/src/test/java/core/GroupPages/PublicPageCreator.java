package core.GroupPages;

import model.GroupInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PublicPageCreator extends GroupCreator {

    private static final By NAME = By.id("field_name");
    private static final By DESCRIPTION = By.id("field_description");
    private static final By SUBCATEGORY = By.id("field_pageMixedCategory");
    private static final By ADD_COVER_BUTTON = By.xpath("//div[@class='groups_photo-upload']//input[@name='photo']");
    private static final String COVER_LOADING_STUB_SRC = "https://ok.ru/res/default/Images/share/Process_72x72_2.gif";
    private static final By COVER_LOADING_STUB_IMG =
            By.xpath("//div[@class='groups_photo-upload']//img[@class='add-happening_poster_img' and @style='display: inline;']");


    public PublicPageCreator(WebDriver driver) {
        super(driver);
    }

    protected void check(){
        super.check();

        Assert.assertTrue("Не дождались поля для ввода названия",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(NAME),
                        5,
                        500));

        Assert.assertTrue("Не дождались поля для ввода описания",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(DESCRIPTION),
                        5,
                        500));

        Assert.assertTrue("Не дождались поля для выбора подкатегории",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(SUBCATEGORY),
                        5,
                        500));

        Assert.assertTrue("Не дождались кнопки для добавления обложки",
                explicitWait(
                        ExpectedConditions.presenceOfElementLocated(ADD_COVER_BUTTON),
                        5,
                        500));
    }

    public void fillData(GroupInfo groupInfo){
        fillName(groupInfo);
        fillDescription(groupInfo);
        chooseSubcategory(groupInfo);
        uploadCover(groupInfo);
    }


    private void fillName(GroupInfo groupInfo){
        Assert.assertTrue("Не найдено поле для ввода названия", isElementPresent(NAME));
        type(groupInfo.getName(), NAME);
    }

    private void fillDescription(GroupInfo groupInfo){
        Assert.assertTrue("Не найдено поле для ввода описания", isElementPresent(DESCRIPTION));
        type(groupInfo.getDescription(), DESCRIPTION);
    }

    private void chooseSubcategory(GroupInfo groupInfo){
        Select subcategory = getSelectElement(SUBCATEGORY);
        Assert.assertNotNull("Не найдено поле для выбора подкатегории", subcategory);
        subcategory.selectByVisibleText(groupInfo.getSubcategory());
    }

    private void uploadCover(GroupInfo groupInfo) {
        if(groupInfo.pathToCover != null){
            addImage(ADD_COVER_BUTTON, groupInfo.pathToCover);

            String src = getAttribute(COVER_LOADING_STUB_IMG, "src");
            System.out.println(src);

            Assert.assertTrue("Не дождались загрузки обложки",
                    explicitWait(
                            ExpectedConditions.not(
                                    ExpectedConditions.attributeToBe(COVER_LOADING_STUB_IMG, "src", COVER_LOADING_STUB_SRC)),
                            5,
                            500));
        }
    }
}
