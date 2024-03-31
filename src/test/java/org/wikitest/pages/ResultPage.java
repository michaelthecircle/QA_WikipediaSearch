package org.wikitest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wikitest.utils.BasePage.BasePage;

public class ResultPage extends BasePage {
    public ResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"firstHeading\"]")
    private WebElement pageTitle;

    public boolean isTitleCorrect(String title){
        waitElementVisibility(pageTitle);
        System.out.println(pageTitle.getText());
        return pageTitle.isDisplayed() && pageTitle.getText().startsWith(title);
    }
}
