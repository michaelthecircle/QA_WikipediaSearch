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

@Slf4j
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='search']")
    private WebElement searchInput;

    @FindBy(css = "#searchform button")
    private WebElement searchButton;

    public boolean searchText(String text) {
        searchInput.sendKeys(text);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-menu"))); // Ожидаем видимости контейнера с подсказками

        List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='cdx-typeahead-search-menu-0']/li"));
        log.info("got list of elements in suggest");
        short i = 0;
        boolean startsCorrectly = true;
        for (WebElement element : searchResults.subList(0, 9)) { // 10 элемент сайджеста - стандартная строка "search for pages containing"
            String elementText = element.getText();
            log.info("№ " + i++ + " element of list = " + elementText.replaceAll("\\s+", " "));
            if (!elementText.trim().toLowerCase().startsWith(text.toLowerCase())) {
                startsCorrectly = false;
            }
        }
        return startsCorrectly;
    }

    public ResultPage enterFirstPage(String text) {
        searchInput.sendKeys(text);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
        return new ResultPage(super.getDriver());
    }
}
