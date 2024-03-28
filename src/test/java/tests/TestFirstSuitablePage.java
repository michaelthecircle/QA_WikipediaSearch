package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultPage;
import utils.BaseTest.BaseTest;

import static org.testng.Assert.assertTrue;

public class TestFirstSuitablePage extends BaseTest {
    //тест - проверка того, что мы переходим по первой ссылке при заданном слове
    //при вводе russia мы перейдем на страницу Russia, заголовок будет такой же, тк игнорируем регистр
    @Test
    @Parameters({ "textToSearch" })
    public void searchTest(String textToSearch){
        HomePage home = loadFirstPage();
        ResultPage results = home.searchText(textToSearch);
        assertTrue(results.isTitleCorrect(textToSearch), "Заголовок не совпадает");
    }
}
