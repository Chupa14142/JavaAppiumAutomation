package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
   public void testChangeScreenOrientationOnSearchResultPage() {
        String search_line = "Java";
        String article_substring = "Object-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.clickByArticleWithSubstring(article_substring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title have benn change after rotation",
                title_before_rotation, title_after_rotation);

        this.rotateScreenPortrait();

        String title_after_second_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title have benn change after rotation",
                title_before_rotation, title_after_second_rotation);

    }

    @Test
    public void testCheckSearchArticleInBackground() {
        String search_line = "Java";
        String article_substring = "Object-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.waitForSearchResult(article_substring);
        this.backgroundApp(3);
        searchPageObject.waitForSearchResult(article_substring);

    }
}
