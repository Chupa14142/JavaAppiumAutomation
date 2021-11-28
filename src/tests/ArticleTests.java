package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.Duration;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        String search_line = "Java";
        String article_substring = "Object-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
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

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.clickByArticleWithSubstring(article_substring);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

    }

    @Test
    public void testAssertArticleTitlePresent() {
        String search_line = "Appium";
        String article_substring = "Appium";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(search_line);
        searchPageObject.clickByArticleWithSubstring(article_substring);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

//         Для проверки валидного кейса с title
//        articlePageObject.waitForElementPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
//                "Can't find an article title");

        assertTrue("Title isn't appear", articlePageObject.assertArticleTitlePresent());

    }


}
