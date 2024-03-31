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
public class TestSuitablePage extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(TestSuitablePage.class);
    @Test
    @Parameters({ "textToSearch" })
    public void listPagesTest(String textToSearch) {
        log.info("launched firstPageTest");
        HomePage home = loadFirstPage();
        log.info("first page loaded successfully");
        /*boolean result = home.enterPagesList(textToSearch).isTitleCorrect(textToSearch);
        log.info("firstPageTest gave " + result + " result");
        assertTrue(result, "First available page has a title that differs from the given one");*/
        assertTrue(home.enterPagesList(textToSearch));
    }
}
