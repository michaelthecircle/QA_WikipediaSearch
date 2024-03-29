package org.wikitest.utils.BaseTest;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.wikitest.pages.HomePage;
import org.wikitest.utils.Driver;

@Slf4j
public class BaseTest {
    Driver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({ "browser", "url" })
    public void beforeMethod(String browser, String url) {
        driver = new Driver(browser);
        driver.getDriver().manage().window().maximize();
        navigateTo(url);
    }
    public void navigateTo(String url) {
        driver.getDriver().get(url);
    }
    public HomePage loadFirstPage() {
        log.info("loading first page");
        return new HomePage(driver.getDriver());
    }
    @AfterMethod()
    public void afterMethod() {
        driver.getDriver().close();
    }
}
