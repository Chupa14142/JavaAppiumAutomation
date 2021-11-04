package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    private static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{ARTICLE_TITLE}']";


    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderXpathBYName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathBYName(name_of_folder);
        this.waitForElementPresentAndClick(
                By.xpath(folder_name_xpath), "Can not find folder by name " + name_of_folder);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath),
                "Cannot find saved Article by title " + article_title,15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath),"Article is still Appear",15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath), "Cannot swiped article with title " + article_title
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
