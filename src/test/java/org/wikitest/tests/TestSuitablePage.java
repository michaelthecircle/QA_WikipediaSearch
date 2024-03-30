package org.wikitest.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.wikitest.pages.HomePage;
import org.wikitest.utils.BaseTest.BaseTest;

import static org.testng.Assert.assertTrue;

@Slf4j
public class TestSuitablePage extends BaseTest {
    @Test
    @Parameters({ "textToSearch" })
    public void firstPageTest(String textToSearch) {
        log.info("launched firstPageTest");
        HomePage home = loadFirstPage();
        log.info("first page loaded successfully");
        boolean result = home.enterFirstPage(textToSearch).isTitleCorrect(textToSearch);
        log.info("firstPageTest gave " + result + " result");
        assertTrue(result, "First available page has a title that differs from the given one");
    }
}
