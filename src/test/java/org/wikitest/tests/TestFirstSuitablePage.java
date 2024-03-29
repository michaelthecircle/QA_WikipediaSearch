package org.wikitest.tests;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.wikitest.pages.HomePage;
import org.wikitest.pages.ResultPage;
import org.wikitest.utils.BaseTest.BaseTest;

import static org.testng.Assert.assertTrue;

@Slf4j
public class TestFirstSuitablePage extends BaseTest {

    //тест - проверка того, что мы переходим по первой ссылке при заданном слове
    //при вводе russia мы перейдем на страницу Russia, заголовок будет такой же, тк игнорируем регистр
    @Test
    @Parameters({ "textToSearch" })
    public void searchTest(String textToSearch){
        log.info("launched searchTest");
        HomePage home = loadFirstPage();
        ResultPage results = home.searchText(textToSearch);
        assertTrue(results.isTitleCorrect(textToSearch), "Заголовок не совпадает");
    }
}
