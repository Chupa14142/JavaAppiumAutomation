package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;


public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchResult() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;

        searchPageObject.initSearchInput();
//          searchPageObject.typeSearchInput("Java");
        searchPageObject.waitForCancelSearchButtonToAppear();
        searchPageObject.clickCancelSearchButton();
        searchPageObject.waitForCancelSearchButtonToDisappear();

    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String search_line = "Linkin Park Discography";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        assertTrue("We found too few results", amount_of_search_results > 0);

    }

    @Test
    public void testAmountOfEmptySearch() {
        String search_line = "fffgfgfg";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultsFound();
    }

    //   DZ - refactoring
    @Test
    public void testCancelSearchWithFewFoundElements() {
        String search_line = "manual";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.waitForListOfArticleTitlesToAppear();

        int countOfElementWasFound = searchPageObject.getNumberOfArticleTitlesFoundAfterSearching();
        assertTrue("countOfElementWasFound < 2", countOfElementWasFound > 2);

        searchPageObject.clickCancelSearchButton();
        searchPageObject.assertThereIsNoTitleFound();
    }

    @Test
    public void testWaitForArticleByTitleAndDescription() {
        String title = "Java";
        String description = "Island of Indonesia";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(title);

//      wait for article with selected title and description
        searchPageObject.waitForElementByTitleOrDescription(title, description);

    }

    @Test
    public void testWaitForThreeArticlesByTitleAndDescription() {
        String searching_text = "Java";
        String[] titles = {"Java", "JavaScript", "Java (programming language)"};
        String[] descriptions = {"Island of Indonesia", "Programming language", "Object-oriented programming language"};

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(searching_text);
        for (int i = 0; i < titles.length; i++) {
            System.out.println("Article Title and Description are: " + titles[i] + " | " + descriptions[i]);
            searchPageObject.waitForElementByTitleOrDescription(titles[i], descriptions[i]);
        }
    }


    @Test
    public void testWaitForArticleByTitleAndDescriptionEx9() {
        String title = "Java";
        String description = "Island of Indonesia";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(title);

//      wait for article with selected title and description
        searchPageObject.waitForElementByTitleOrDescription(title, description);

    }


}
