package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.MyListsPageObjectFactory;
import lib.ui.factory.NavigationUIFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveSelectedArticle() {

        String search_line = "Java";
        String article_substring = "Object-oriented programming language";


        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.clickByArticleWithSubstring(article_substring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
             articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticleToMySavedList();
            articlePageObject.closeModalWindow();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        }

        myListsPageObject.swipeByArticleToDelete(article_title);

    }


    //    DZ - refactoring
//    @Test
//    public void testSaveTwoArticlesToTheReadingList() {
//        String search_line = "Java";
//        String name_of_reading_list_folder = this.getName();
//
//        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
//        searchPageObject.initSearchInput();
//        searchPageObject.typeSearchInput(search_line);
//
//        String first_article_title = searchPageObject.getArticleTitleByNumber(1);
//        searchPageObject.clickByArticleWithSubstring(first_article_title);
//
//        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
//        articlePageObject.addArticleToMyList(name_of_reading_list_folder);
//        articlePageObject.closeArticle();
//
//        searchPageObject.initSearchInput();
//        searchPageObject.typeSearchInput(search_line);
//        String second_article_title = searchPageObject.getArticleTitleByNumber(2);
//        searchPageObject.clickByArticleWithSubstring(second_article_title);
//
//        articlePageObject.addArticleToTheExistingReadingListByName(name_of_reading_list_folder);
//        articlePageObject.closeArticle();
//
//        NavigationUI navigationUI = new NavigationUI(driver);
//        navigationUI.clickMyLists();
//
//        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
//        myListsPageObject.openFolderByName(name_of_reading_list_folder);
//        myListsPageObject.thereIsArticlesInTheReadingList();
//        assertEquals("Count of article in the readin list != 2",
//                2, myListsPageObject.getAmountOfArticlesInTheReadingList());
//        myListsPageObject.swipeByArticleToDelete(first_article_title);
//
//        myListsPageObject.waitForArticleToAppearByTitle(second_article_title);
//        myListsPageObject.clickOnArticleByTitle(second_article_title);
//
//        articlePageObject.waitForTitleElement();
//        assertEquals("Titles is not equals",
//                second_article_title, articlePageObject.getArticleTitle());
//    }





}
