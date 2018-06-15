package tests;

import core.*;
import core.GroupPages.Group;
import core.GroupPages.GroupCreator;
import core.GroupPages.GroupMainPage;
import core.GroupPages.GroupNavigationPage;
import model.GroupInfo;
import model.GroupType;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import wrappers.LookupGroupWrapper;

import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;

public class DeletionOfPublicPage extends TestBase{
    private Group publicPage;

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
    public void testDeletionOfPublicPage() throws Exception {
        String groupId = publicPage.getGroupId();
        publicPage.delete();

        GroupNavigationPage groupNavigationPage = new GroupMainPage(driver)
                .clickOnGroupNavigation();

        List<LookupGroupWrapper> groupWrappers = groupNavigationPage.getLookupGroups();

        LookupGroupWrapper lookupGroup = null;
        for(LookupGroupWrapper wrapper : groupWrappers){
            if(wrapper.getGroupId().equals(groupId)) {
                lookupGroup = wrapper;
                break;
            }
        }

        Assert.assertNull("Группа с " + groupId + " найдена , группа не была удалена", lookupGroup);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
