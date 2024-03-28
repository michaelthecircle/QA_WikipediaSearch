package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Objects;

public class Driver {
    private final WebDriver webDriver;

    public Driver(String browserName) {
        if (Objects.equals(browserName, "chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            webDriver = new ChromeDriver(option);
            /*ChromeOptions option = new ChromeOptions();
            String path = System.getProperty("user.dir");
            System.out.println(path);
            System.setProperty("webdriver.chrome.driver",path+"\\drivers\\chromedriver.exe");
            webDriver = new ChromeDriver(option);*/
        } else { //добавить логику для сафари
            this.webDriver = new SafariDriver();
        }
    }
    public WebDriver getDriver() {
        return this.webDriver;
    }


}
