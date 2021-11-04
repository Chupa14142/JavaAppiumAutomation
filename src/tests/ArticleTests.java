package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        String search_line = "Java";
        String article_substring = "Object-oriented programming language";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.clickByArticleWithSubstring(article_substring);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle();

        assertEquals("We see unexpected title!",
                "Java (programming language)", article_title);

    }

    @Test
    public void testSwipeArticle() {
        String search_line = "Appium";
        String article_substring = "Appium";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.clickByArticleWithSubstring(article_substring);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

    }



}
