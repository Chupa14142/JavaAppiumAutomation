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
}
