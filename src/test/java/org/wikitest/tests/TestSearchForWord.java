package org.wikitest.tests;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.wikitest.pages.HomePage;
import org.wikitest.utils.BaseTest.BaseTest;

import static org.testng.Assert.assertTrue;


@Slf4j
public class TestSearchForWord extends BaseTest {
    //Тест 4 - проверяет что при каждом запросе в поиске есть предложение перейти на страницу с поисковыми результатами
    private static final Logger logger = LoggerFactory.getLogger(TestSuitableNames.class);
    @Test
    @Parameters({ "positiveSearchText" })
    public void searchWithSuggestion(String positiveSearchText) { //при вводе слова будут подсказки => пройдемся по ним до перехода на желаемую тестом страницу
        log.info("launched searchWithSuggestion");
        HomePage home = loadFirstPage();
        log.info("first page loaded successfully");
        boolean result = home.checkLastSuggestion(positiveSearchText);
        log.info("checkLastSuggestion gave " + result + " with " + positiveSearchText);
        assertTrue(result, "suggestionSearch gave negative result");
    }
    @Test
    @Parameters({ "negativeSearchText" })
    public void searchWithoutSuggestion(String negativeSearchText) { //при вводе слова не будет подсказок кроме желаемой страницы
        log.info("launched searchWithoutSuggestion");
        HomePage home = loadFirstPage();
        boolean result = home.checkLastSuggestion(negativeSearchText);
        log.info("searchWithoutSuggestion gave " + result + " with " + negativeSearchText);
        assertTrue(result, "suggestionSearch gave negative result");
    }
}
