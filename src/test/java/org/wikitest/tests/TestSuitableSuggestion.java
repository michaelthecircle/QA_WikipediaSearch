package org.wikitest.tests;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.wikitest.pages.HomePage;
import org.wikitest.utils.BaseTest.BaseTest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Slf4j
public class TestSuitableSuggestion extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(TestSuitableNames.class);
    @Test
    @Parameters({ "positiveSearchText" })
    public void positiveSearchText(String positiveSearchText) {
        log.info("launched positiveSearchText");
        HomePage home = loadFirstPage();
        log.info("first page loaded successfully");
        boolean result = home.checkFirstSuggestion(positiveSearchText);
        log.error("first suggestion doesnt send to search button result");
        assertTrue(result, "first suggestion doesnt send to search button result");
    }
    @Test
    @Parameters({ "negativeSearchText" })
    public void negativeSearchText(String negativeSearchText) {
        log.info("launched negativeSearchText");
        HomePage home = loadFirstPage();
        log.info("first page loaded successfully");
        boolean result = home.checkFirstSuggestion(negativeSearchText);
        log.error("first suggestion doesnt send to search button result");
        assertFalse(result, "first suggestion doesnt send to search button result");
    }
}