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
import java.util.NoSuchElementException;

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
        waitSearchInputAndClick(text);
        log.debug("send keys to the search field");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-menu"))); // Ожидаем видимости контейнера с подсказками
        List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='cdx-typeahead-search-menu-0']/li"));
        log.debug("got list of elements in suggest");
        short i = 0;
        boolean startsCorrectly = true;
        boolean containsBold;
        for (WebElement element : searchResults.subList(0, 9)) { // 10 элемент сайджеста - стандартная строка "search for pages containing"
            String fontWeight = element.getCssValue("font-weight");
            String elementText = element.getText().replaceAll("\\s+", " ");
            containsBold = fontWeight.equals("bold") || fontWeight.equals("700"); //весь элемент не жирный => не получилось взять для сравнения часть с жирной подсказкой
            log.info(i++ + " element of list = " + elementText + " is " + (containsBold  ? "bold" : " isn't bold"));
            if (!elementText.trim().toLowerCase().startsWith(text.toLowerCase())) {
                startsCorrectly = false;
            }
        }
        return startsCorrectly;
    }

    public boolean enterPagesList(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        waitSearchInputAndClick(text);
        log.debug("send keys to the search field");
        for (short i = 0; i < 10; i ++) {
            var searchResults = getListElements();
            String elementText = searchResults.get(i).getText().replaceAll("\\s+", " ");
            wait.until(ExpectedConditions.visibilityOf(searchResults.get(i)));
            searchResults.get(i).click();
            ResultPage resultPage = new ResultPage(super.getDriver());
            log.info("suggestion " + i + " is: " + elementText);
            log.info("title in suggestion " + i + " is " + (resultPage.isTitleCorrect(elementText.split(" ")[0]) ? " correct" : " incorrect"));
            driver.navigate().back();
        }
        return true;
    }

    private List<WebElement> getListElements() {
        waitSomeSeconds(1);
        waitSearchInputAndClick(null);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-menu")));
        return driver.findElements(By.xpath("//ul[@id='cdx-typeahead-search-menu-0']/li"));
    }

    private void waitSearchInputAndClick(String text) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        waitSomeSeconds(1);
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        if (text != null) {
            searchInput.sendKeys(text);
        }
        searchInput.click();
    }
    public boolean checkFirstSuggestion(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        waitSearchInputAndClick(text);
        log.debug("send keys to the search field");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-menu")));
        var listOfSuggestions = driver.findElements(By.xpath("//ul[@id='cdx-typeahead-search-menu-0']/li"));
        if (listOfSuggestions.size() == 1) {
            //если существует только 1 подсказка => переход на страницу поиска
            return false;
        }
        WebElement firstSuggestion = listOfSuggestions.get(0);
        log.debug("first suggestion is " + firstSuggestion.getText().replaceAll("\\s+", " "));

        wait.until(ExpectedConditions.elementToBeClickable(firstSuggestion));
        firstSuggestion.click();
        String firstSugURL = driver.getCurrentUrl();
        driver.navigate().back();

        waitSearchInputAndClick(null);
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        String searchURL = driver.getCurrentUrl();
        return firstSugURL.equals(searchURL);
    }
}
