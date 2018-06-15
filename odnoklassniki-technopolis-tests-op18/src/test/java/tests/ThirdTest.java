package tests;

import core.LoginMainPage;
import core.UserMainPage;
import model.TestBot;
import org.junit.Test;

public class ThirdTest extends TestBase {

    @Test
    public void testAddPhoto() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("technopolisbot", "technopolis16"));
        //тут ваш путь до файла
        String pathname = "/Users/emiliya.kutsareva/Downloads/2018-04-04 19.23.47.jpg";
        new UserMainPage(driver).addPhoto(pathname);
        //todo ...
    }
}