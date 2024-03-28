package utils.BaseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.HomePage;
import utils.Driver;

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
        return new HomePage(driver.getDriver());
    }
    @AfterMethod()
    public void afterMethod() {
        driver.getDriver().close();
    }
}
