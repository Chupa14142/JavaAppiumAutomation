package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class SearchPageObject extends MainPageObject {


    private static final String
            SEARCH_ELEMENT_INIT = "//*[@text='Search Wikipedia']",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            EMPTY_RESULTS_LABEL = "//*[@text='No results found']",
    // FOR DZ
            LIST_OF_ARTICLE_TITLES = "(//*[@resource-id='org.wikipedia:id/page_list_item_title'])",
//android.widget.LinearLayout[android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java']]
// [android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Island of Indonesia']]

            ARTICLE_TITLE_AND_DESCRIPTION_TPL =
                    "//android.widget.LinearLayout/android.widget.TextView[@text='{TITLE}']/../android.widget.TextView[@text='{DESCRIPTION}']";



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
        this.waitForElementPresentAndClick(By.xpath(SEARCH_ELEMENT_INIT), "Can't find and click search element input");
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Can't find search input");
    }

    public void typeSearchInput(String search_line) {
        waitForElementPresentAndSendKeys(By.xpath(SEARCH_INPUT), "Can't find search input", search_line);
    }

    public void waitForCancelSearchButtonToAppear() {
        waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find Cancel Button");
    }

    public void waitForCancelSearchButtonToDisappear() {
        waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Cancel button is still present");
    }

    public void clickCancelSearchButton() {
        waitForElementPresentAndClick(By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click on Cancel Search Button");
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find search result with substring:" + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        waitForElementPresentAndClick(By.xpath(search_result_xpath),
                "Cannot find and CLick search result with substring:" + substring);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request");
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        waitForElementPresent(By.xpath(EMPTY_RESULTS_LABEL), "Cannot find empty result label");
    }

    public void assertThereIsNoResultsFound() {
        this.assertElementNoPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void waitForListOfArticleTitlesToAppear() {
        this.waitForElementsPresent(By.xpath(LIST_OF_ARTICLE_TITLES), "Cannot find list of article titles");
    }

    public void waitForListOfArticleTitlesToDisappear() {
        this.waitForElementNotPresent(By.xpath(LIST_OF_ARTICLE_TITLES), "List of Article is still present");
    }

    public int getNumberOfArticleTitlesFoundAfterSearching() {
        return this.getAmountOfElements(By.xpath(LIST_OF_ARTICLE_TITLES));
    }

    public void assertThereIsNoTitleFound() {
        this.assertElementNoPresent(By.xpath(LIST_OF_ARTICLE_TITLES), "We supposed not to find any titles");
    }

    public String getArticleTitleByNumber(int number) {
        String article_title = waitForElementAndGetAttribute(
                By.xpath(LIST_OF_ARTICLE_TITLES + "[" + number + "]"),
                "Can't find article by number " + number, "text", 15);
        return article_title;
    }

    public void waitForElementByTitleOrDescription(String articleTitle, String articleDescription) {
        String articleXpathByTitleAndDescription = getTitleXpathByTitleAndDescription(articleTitle, articleDescription);
          this.waitForElementsPresent(
                  By.xpath(articleXpathByTitleAndDescription),
                  " \n Cannot find an Article with Title: " + articleTitle + " Or Description " + articleDescription
          );
    }
}
