package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchResult() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
//          searchPageObject.typeSearchInput("Java");
        searchPageObject.waitForCancelSearchButtonToAppear();
        searchPageObject.clickCancelSearchButton();
        searchPageObject.waitForCancelSearchButtonToDisappear();

    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String search_line = "Linkin Park Discography";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        assertTrue("We found too few results", amount_of_search_results > 0);

    }

    @Test
    public void testAmountOfEmptySearch() {
        String search_line = "fffgfgfg";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultsFound();
    }

//   DZ - refactoring
    @Test
    public void testCancelSearchWithFewFoundElements() {
        String search_line = "manual";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.waitForListOfArticleTitlesToAppear();

        int countOfElementWasFound = searchPageObject.getNumberOfArticleTitlesFoundAfterSearching();
        assertTrue("countOfElementWasFound < 2", countOfElementWasFound > 2);

        searchPageObject.clickCancelSearchButton();
        searchPageObject.assertThereIsNoTitleFound();
    }

    @Test
    public void testExample() {
        WebElement element = driver.findElement(By.xpath("//*[@text='Search Wikipedia']"));
        element.click();
    }

}
