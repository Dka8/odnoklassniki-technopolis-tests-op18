package tests;

import core.*;
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

public class AbortCreationOfPublicPage extends TestBase{
    private GroupCreator creator;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GroupMainPage groupMainPage = new LoginMainPage(driver)
                .doLogin(TestBot.create())
                .clickGroupsOnToolbar();

        creator = groupMainPage
                .clickCreateGroup()
                .getNewGroupCreator(GroupType.PublicPage);
    }

    @Test
    public void testAbortCreationOfPublicPage() throws Exception {
        GroupInfo groupInfo = GroupInfo.create();
        creator.abortCreatingGroup(groupInfo);

        GroupNavigationPage groupNavigationPage = new GroupMainPage(driver)
                .clickOnGroupNavigation();

        List<LookupGroupWrapper> groupWrappers = groupNavigationPage.getLookupGroups();

        LookupGroupWrapper lookupGroup = null;
        for(LookupGroupWrapper wrapper : groupWrappers){
            if(wrapper.getGroupName().equals(groupInfo.getName())) {
                lookupGroup = wrapper;
                break;
            }
        }

        Assert.assertNull("Группа с именем " + groupInfo.getName() + " была создана, не смотря на отмену", lookupGroup);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
