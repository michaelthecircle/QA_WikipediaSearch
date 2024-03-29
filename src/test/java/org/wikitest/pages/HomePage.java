package org.wikitest.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wikitest.utils.BasePage.BasePage;

import java.util.List;

import static org.openqa.selenium.By.xpath;

@Slf4j
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='search']")
    private WebElement searchInput;

    @FindBy(css = "#searchform button")
    private WebElement searchButton;

    public ResultPage searchText(String text){
        searchInput.sendKeys(text);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-menu"))); // Ожидаем видимости контейнера с подсказками


        List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='cdx-typeahead-search-menu-0']/li"));
        short i = 0;
        for (WebElement element : searchResults) {
            log.info(i++ + " element of list = " + element.getText());
        }
        searchButton.click();
        return new ResultPage(super.getDriver());
    }
}
