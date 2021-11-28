package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

abstract public class SearchPageObject extends MainPageObject {


     protected static String
            SEARCH_ELEMENT_INIT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            EMPTY_RESULTS_LABEL,
    // FOR DZ
            LIST_OF_ARTICLE_TITLES,

            ARTICLE_TITLE_AND_DESCRIPTION_TPL;



    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    // TEMPLATES METHOD
    public String getTitleXpathByTitleAndDescription(String articleTitle, String articleDescription) {
        return ARTICLE_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}",articleTitle).replace("{DESCRIPTION}",articleDescription);
    }

    public String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    // TEMPLATES METHOD

    public void initSearchInput() {
        this.waitForElementPresentAndClick(SEARCH_ELEMENT_INIT, "Can't find and click search element input");
        this.waitForElementPresent(SEARCH_INPUT, "Can't find search input");
    }

    public void typeSearchInput(String search_line) {
        waitForElementPresentAndSendKeys(SEARCH_INPUT, "Can't find search input", search_line);
    }

    public void waitForCancelSearchButtonToAppear() {
        waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Cancel Button");
    }

    public void waitForCancelSearchButtonToDisappear() {
        waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Cancel button is still present");
    }

    public void clickCancelSearchButton() {
        waitForElementPresentAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click on Cancel Search Button");
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        waitForElementPresent(search_result_xpath,
                "Cannot find search result with substring:" + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        waitForElementPresentAndClick(search_result_xpath,
                "Cannot find and CLick search result with substring:" + substring);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request");
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        waitForElementPresent(EMPTY_RESULTS_LABEL, "Cannot find empty result label");
    }

    public void assertThereIsNoResultsFound() {
        this.assertElementNoPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void waitForListOfArticleTitlesToAppear() {
        this.waitForElementsPresent(LIST_OF_ARTICLE_TITLES, "Cannot find list of article titles");
    }

    public void waitForListOfArticleTitlesToDisappear() {
        this.waitForElementNotPresent(LIST_OF_ARTICLE_TITLES, "List of Article is still present");
    }

    public int getNumberOfArticleTitlesFoundAfterSearching() {
        return this.getAmountOfElements(LIST_OF_ARTICLE_TITLES);
    }

    public void assertThereIsNoTitleFound() {
        this.assertElementNoPresent(LIST_OF_ARTICLE_TITLES, "We supposed not to find any titles");
    }

//    public String getArticleTitleByNumber(int number) {
//        String article_title = waitForElementAndGetAttribute(
//                By.xpath(LIST_OF_ARTICLE_TITLES + "[" + number + "]"),
//                "Can't find article by number " + number, "text", 15);
//        return article_title;
//    }

    public void waitForElementByTitleOrDescription(String articleTitle, String articleDescription) {
        String articleXpathByTitleAndDescription = getTitleXpathByTitleAndDescription(articleTitle, articleDescription);
          this.waitForElementsPresent(articleXpathByTitleAndDescription,
                  " \n Cannot find an Article with Title: " + articleTitle + " Or Description " + articleDescription
          );
    }
}
