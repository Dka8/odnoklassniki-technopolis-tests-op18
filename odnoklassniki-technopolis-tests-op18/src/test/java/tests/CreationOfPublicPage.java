package tests;

import core.*;
import core.GroupPages.Group;
import core.GroupPages.GroupCreator;
import core.GroupPages.GroupMainPage;
import model.GroupInfo;
import model.GroupType;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;

public class CreationOfPublicPage extends TestBase{
    private GroupCreator creator;
    private Group publicPage;

    @Before
    public void setUpLocal() throws Exception {
        //super.setUp();
        GroupMainPage groupMainPage = new LoginMainPage(driver)
                .doLogin(TestBot.create())
                .clickGroupsOnToolbar();

        creator = groupMainPage
                .clickCreateGroup()
                .getNewGroupCreator(GroupType.PublicPage);
    }

    @Test
    public void testCreationOfPublicPage() throws Exception {
        GroupInfo groupInfo = GroupInfo.create();
        publicPage = creator.createGroup(groupInfo);

        Assert.assertEquals(
                "Имя группы не соответствует " + groupInfo.getName(),
                groupInfo.getName(),
                publicPage.getGroupName());

        Assert.assertEquals(
                "Описание группы не соответствует " + groupInfo.getDescription(),
                groupInfo.getDescription(),
                publicPage.getGroupDescription());

        Assert.assertThat(
                "Тип группы не публичная страница",
                publicPage.getGroupType(),
                anyOf(containsString("Страница"), containsString("Публичная страница")));

        Assert.assertThat("Подкатегория группы не " + publicPage.getGroupType(),
                publicPage.getGroupType(),
                containsString(publicPage.getGroupType()));

        Assert.assertNotNull("Обложка группы не была загружена", publicPage.getCoverUrl());
    }

    @After
    public void tearDownLocal() throws Exception {
        if(publicPage != null){
            publicPage.delete();
        }
        //super.tearDown();
    }
}
