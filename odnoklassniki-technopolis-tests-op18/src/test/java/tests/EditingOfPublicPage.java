package tests;

import core.GroupPages.*;
import core.LoginMainPage;
import model.GroupInfo;
import model.GroupType;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import wrappers.LookupGroupWrapper;

import java.util.List;

public class EditingOfPublicPage extends TestBase{
    private Group publicPage;
    private Group editedPublicPage;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        GroupCreator creator = new LoginMainPage(driver)
                .doLogin(TestBot.create())
                .clickGroupsOnToolbar()
                .clickCreateGroup()
                .getNewGroupCreator(GroupType.PublicPage);

        GroupInfo groupInfo = GroupInfo.create();
        publicPage = creator.createGroup(groupInfo);
    }

    @Test
    public void testChangeGroupSettings() throws Exception {
        GroupInfo newGroupInfo = GroupInfo.create();
        editedPublicPage = publicPage.clickOnGroupSettings().changeSetting(newGroupInfo);

        Assert.assertEquals(
                "Имя группы не соответствует " + newGroupInfo.getName(),
                newGroupInfo.getName(),
                editedPublicPage.getGroupName());

        Assert.assertEquals(
                "Описание группы не соответствует " + newGroupInfo.getDescription(),
                newGroupInfo.getDescription(),
                editedPublicPage.getGroupDescription());
    }

    @After
    public void tearDown() throws Exception {
        if(editedPublicPage != null){
            editedPublicPage.delete();
        }
        else if(publicPage != null){
            publicPage.delete();
        }

        super.tearDown();
    }
}
