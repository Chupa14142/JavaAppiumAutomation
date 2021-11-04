package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[@text='View page in browser']",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "//*[@text='GOT IT']",
    MY_LIST_NAME_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article on page!");
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeToFindElement(By.xpath(FOOTER_ELEMENT),"Cannot swipe to footer",30);

    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementPresentAndClick(
                By.xpath(OPTIONS_BUTTON), "Can't find More options Button"
        );

        this.waitForElementPresentAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON), "Can't find Add to reading list Button"
        );

        this.waitForElementPresentAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY), "Can't find GOT IT Button"
        );


        this.waitForElementPresentAndSendKeys(
                By.xpath(MY_LIST_NAME_INPUT), "Can't find Name of this list input", name_of_folder
        );

        this.waitForElementPresentAndClick(
                By.xpath(MY_LIST_OK_BUTTON), "Can't find OK Button"
        );

    }

    public void closeArticle() {
        this.waitForElementPresentAndClick(By.xpath(CLOSE_ARTICLE_BUTTON), "Can't find Close(X) Button");
    }

}
