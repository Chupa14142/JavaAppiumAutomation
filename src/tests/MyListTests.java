package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    @Test
    public void testSaveSelectedArticle() {

        String search_line = "Appium";
        String article_substring = "Appium";
        String name_of_folder = "Java programming language";


        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.clickByArticleWithSubstring(article_substring);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);

    }


    //    DZ - refactoring
    @Test
    public void testSaveTwoArticlesToTheReadingList() {
        String search_line = "Java";
        String name_of_reading_list_folder = this.getName();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);

        String first_article_title = searchPageObject.getArticleTitleByNumber(1);
        searchPageObject.clickByArticleWithSubstring(first_article_title);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.addArticleToMyList(name_of_reading_list_folder);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        String second_article_title = searchPageObject.getArticleTitleByNumber(2);
        searchPageObject.clickByArticleWithSubstring(second_article_title);

        articlePageObject.addArticleToTheExistingReadingListByName(name_of_reading_list_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_reading_list_folder);
        myListsPageObject.thereIsArticlesInTheReadingList();
        assertEquals("Count of article in the readin list != 2",
                2, myListsPageObject.getAmountOfArticlesInTheReadingList());
        myListsPageObject.swipeByArticleToDelete(first_article_title);

        myListsPageObject.waitForArticleToAppearByTitle(second_article_title);
        myListsPageObject.clickOnArticleByTitle(second_article_title);

        articlePageObject.waitForTitleElement();
        assertEquals("Titles is not equals",
                second_article_title, articlePageObject.getArticleTitle());
    }
}
