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
        searchInput.sendKeys(text);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-menu"))); // Ожидаем видимости контейнера с подсказками

        List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='cdx-typeahead-search-menu-0']/li"));
        log.info("got list of elements in suggest");
        short i = 0;
        boolean startsCorrectly = true;
        boolean containsBold;
        for (WebElement element : searchResults.subList(0, 9)) { // 10 элемент сайджеста - стандартная строка "search for pages containing"
            String fontWeight = element.getCssValue("font-weight");
            String elementText = element.getText().replaceAll("\\s+", " ");
            // Проверяем, что значение font-weight "bold" или "700"
            containsBold = fontWeight.equals("bold") || fontWeight.equals("700");
            log.info(i++ + " element of list = " + elementText + " is " + (containsBold  ? "bold" : " isn't bold"));
            if (!elementText.trim().toLowerCase().startsWith(text.toLowerCase())) {
                startsCorrectly = false;
            }
        }
        return startsCorrectly;
    }

    public boolean enterPagesList(String text) {
        /*searchInput.sendKeys(text);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchButton));*/
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-menu")));
        //List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='cdx-typeahead-search-menu-0']/li"));
        String homeURL = driver.getCurrentUrl();
        for (short i = 0; i < 10; i ++) {
            var searchResults = getListElements(text);
            String elementText = searchResults.get(i).getText().replaceAll("\\s+", " ");
            wait.until(ExpectedConditions.visibilityOf(searchResults.get(i)));
            searchResults.get(i).click();
            ResultPage resultPage = new ResultPage(super.getDriver());
            System.out.println(resultPage.isTitleCorrect(elementText.split(" ")[0]));
            //waitSomeSeconds(1);
            driver.navigate().back();
            //driver.navigate().to(homeURL);
        }
        return true;
    }

    private List<WebElement> getListElements(String text) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(text);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-menu")));
        return driver.findElements(By.xpath("//ul[@id='cdx-typeahead-search-menu-0']/li"));
    }
}
