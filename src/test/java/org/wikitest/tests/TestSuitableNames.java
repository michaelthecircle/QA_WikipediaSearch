package org.wikitest.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.wikitest.pages.HomePage;
import org.wikitest.pages.ResultPage;
import org.wikitest.utils.BaseTest.BaseTest;

import static org.testng.Assert.assertTrue;

@Slf4j
public class TestSuitableNames extends BaseTest {
    @Test
    @Parameters({ "textToSearch" })
    public void searchTest(String textToSearch){
        log.info("launched searchTest");
        HomePage home = loadFirstPage();
        boolean result = home.searchText(textToSearch);
        log.info("searchTest gave " + result + " result");
        assertTrue(result, "В сайджесте есть подсказка, начало которой не совпадает заданным в поиске словом");
    }
}
