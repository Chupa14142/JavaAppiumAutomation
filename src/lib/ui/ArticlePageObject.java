package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {


    private static final String
            ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "xpath://*[@text='GOT IT']",
            MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
    //    DZ
    OPTIONS_BY_TEXT_TPL = "xpath://android.widget.LinearLayout//.[@text='{selected_options}']",
    SAVE_TO_READING_LIST_MENU_TITLE = "xpath://*[@text='Save to reading list']",
    NAME_OF_EXISTING_READING_LIST_TPL="xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{reading_list_name}']",
    MORE_OPTIONS_POPUP = "xpath://*[@class='android.widget.ListView']",
    ARTICLE_TITLE_TPL = "xpath://*[(@resource-id='org.wikipedia:id/page_list_item_title' and @text='{articleTitle}')]",
    ARTICLE_DESCRIPTION_TPL = "xpath://*[(@resource-id='org.wikipedia:id/page_list_item_description' and @text='{articleDescription}')]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    //Template method for DZ
//    private String getExistingReadingListXpath(String reading_list_name) {
//        return NAME_OF_EXISTING_READING_LIST_TPL.replace("{reading_list_name}",reading_list_name);
//    }

    private String getOptionXpath(String selected_option) {
        return OPTIONS_BY_TEXT_TPL.replace("{selected_options}", selected_option);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(ARTICLE_TITLE, "Cannot find article on page!");

    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeToFindElement(FOOTER_ELEMENT, "Cannot swipe to footer", 30);

    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementPresentAndClick(OPTIONS_BUTTON, "Can't find More options Button");
        this.waitForElementPresent(MORE_OPTIONS_POPUP,"Cannot see MORE_OPTIONS_POPUP");
        this.waitForElementPresentAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Can't find Add to reading list Button");
        this.waitForElementPresentAndClick(ADD_TO_MY_LIST_OVERLAY, "Can't find GOT IT Button");
        this.waitForElementPresentAndSendKeys(MY_LIST_NAME_INPUT,
                "Can't find Name of this list input", name_of_folder);
        this.waitForElementPresentAndClick(MY_LIST_OK_BUTTON, "Can't find OK Button");
    }

    public void closeArticle() {
        this.waitForElementPresentAndClick(CLOSE_ARTICLE_BUTTON, "Can't find Close(X) Button");
    }

//    public void clickMoreOptionsAndSelectOptionByText(String option) {
//        this.waitForElementPresentAndClick(OPTIONS_BUTTON, "Cannot find options button");
//        String selectedOptions = getOptionXpath(option);
//        waitForElementPresentAndClick(By.xpath(selectedOptions), "Cannot find and click " + option);
//    }

//    public void addArticleToTheExistingReadingListByName (String readingListName) {
//        this.waitForElementPresentAndClick(OPTIONS_BUTTON,"Cannot find More Options Button");
//        this.waitForElementPresentAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
//                "Cannot find and click Add to the reading list button");
//        this.waitForElementPresent(SAVE_TO_READING_LIST_MENU_TITLE,"Cannot see SAVE_TO_READING_LIST_MENU_TITLE");
//
//        String existing_reading_list_xpath = getExistingReadingListXpath(readingListName);
//        this.waitForElementPresentAndClick(By.xpath(existing_reading_list_xpath),
//                "Cannot see and click existing reading list with name " + readingListName);
//        this.waitForElementNotPresent(SAVE_TO_READING_LIST_MENU_TITLE,
//                "SAVE_TO_THE_READING_LIST_MENU is still present");
//
//    }

    public boolean assertArticleTitlePresent(){
        return this.assertElementPresent(By.xpath(ARTICLE_TITLE));
    }

}
