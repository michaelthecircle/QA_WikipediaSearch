package org.wikitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wikitest.utils.BasePage.BasePage;

import java.util.List;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("suggestions-container"))); // Ожидаем видимости контейнера с подсказками

        List<WebElement> searchResults = driver.findElements(By.cssSelector(".suggestions li"));

        for (WebElement element : searchResults) {
            element.click();
            String linkText = element.getText();
            System.out.println(linkText);
        }

        searchButton.click();
        return new ResultPage(super.getDriver());
    }
}
