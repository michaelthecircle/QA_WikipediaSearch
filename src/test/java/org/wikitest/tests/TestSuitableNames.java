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
public class TestSuitableNames extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(TestSuitableNames.class);
    @Test
    @Parameters({ "textToSearch" })
    public void searchTest(String textToSearch){
        log.info("launched searchTest");
        HomePage home = loadFirstPage();
        log.info("first page loaded successfully");
        boolean result = home.searchText(textToSearch);
        log.info("searchTest gave " + result + " result");
        assertTrue(result, "There's a suggestion which title doesn't match the given word");
    }
}
