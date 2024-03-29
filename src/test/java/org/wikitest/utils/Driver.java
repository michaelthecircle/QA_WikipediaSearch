package org.wikitest.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Objects;


public class Driver {
    private final WebDriver webDriver;

    public Driver(String browserName) {
        if (Objects.equals(browserName, "chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            webDriver = new ChromeDriver(option);
        } else if (Objects.equals(browserName, "safari")) {
            WebDriverManager.safaridriver().setup();
            SafariOptions options = new SafariOptions();
            webDriver = new SafariDriver(options);
        } else {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }
    }
    public WebDriver getDriver() {
        return this.webDriver;
    }


}
