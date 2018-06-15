package tests;

import core.examples.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Пример вызова раличных конструкций
 */
public class ExampleTest extends TestBase {

    @Test
    public void test() throws Exception {

        //фабрика
        ExampleInterface exampleInterface = new ExampleFactory().get(driver);
        Assert.assertTrue("Комментарии при невыполнении условия", exampleInterface.isSmthgPresent());

        //промис
        ExamplePageNew examplePageNew = new ExampleCommonPage(driver).addComment().andExamplePageNewOpen();

        //хэлпер
        Assert.assertFalse("Комментарии при невыполнении условия", ExampleHelper.isSmthgPresent());

        //враппер
        List<ExampleWrapper> comments = new ExampleCommonPage(driver).getComments();
        Assert.assertTrue("Комментарии при невыполнении условия", comments.get(0).isSmthgDisplayed());
    }
}