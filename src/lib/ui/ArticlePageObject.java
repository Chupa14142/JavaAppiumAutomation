package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            ARTICLE_TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "//*[@text='GOT IT']",
            MY_LIST_NAME_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    //    DZ
    OPTIONS_BY_TEXT_TPL = "//android.widget.LinearLayout//.[@text='{selected_options}']",
    SAVE_TO_READING_LIST_MENU_TITLE = "//*[@text='Save to reading list']",
    NAME_OF_EXISTING_READING_LIST_TPL="//*[@resource-id='org.wikipedia:id/item_title'][@text='{reading_list_name}']",
    MORE_OPTIONS_POPUP = "//*[@class='android.widget.ListView']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    //Template method for DZ
    private String getExistingReadingListXpath(String reading_list_name) {
        return NAME_OF_EXISTING_READING_LIST_TPL.replace("{reading_list_name}",reading_list_name);
    }

    private String getOptionXpath(String selected_option) {
        return OPTIONS_BY_TEXT_TPL.replace("{selected_options}", selected_option);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(ARTICLE_TITLE), "Cannot find article on page!");
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot swipe to footer", 30);

    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementPresentAndClick(
                By.xpath(OPTIONS_BUTTON), "Can't find More options Button"
        );

        this.waitForElementPresent(By.xpath(MORE_OPTIONS_POPUP),"Cannot see MORE_OPTIONS_POPUP");

        this.waitForElementPresentAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON), "Can't find Add to reading list Button"
        );

        this.waitForElementPresentAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY), "Can't find GOT IT Button"
        );


        this.waitForElementPresentAndSendKeys(
                By.xpath(MY_LIST_NAME_INPUT), "Can't find Name of this list input", name_of_folder
        );

        this.waitForElementPresentAndClick(By.xpath(MY_LIST_OK_BUTTON), "Can't find OK Button");

    }

    public void closeArticle() {
        this.waitForElementPresentAndClick(By.xpath(CLOSE_ARTICLE_BUTTON), "Can't find Close(X) Button");
    }

    public void clickMoreOptionsAndSelectOptionByText(String option) {
        this.waitForElementPresentAndClick(By.xpath(OPTIONS_BUTTON), "Cannot find options button");
        String selectedOptions = getOptionXpath(option);
        waitForElementPresentAndClick(By.xpath(selectedOptions), "Cannot find and click " + option);
    }

    public void addArticleToTheExistingReadingListByName (String readingListName) {
        this.waitForElementPresentAndClick(By.xpath(OPTIONS_BUTTON),"Cannot find More Options Button");
        this.waitForElementPresentAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find and click Add to the reading list button");
        this.waitForElementPresent(By.xpath(SAVE_TO_READING_LIST_MENU_TITLE),"Cannot see SAVE_TO_READING_LIST_MENU_TITLE");

        String existing_reading_list_xpath = getExistingReadingListXpath(readingListName);
        this.waitForElementPresentAndClick(By.xpath(existing_reading_list_xpath),
                "Cannot see and click existing reading list with name " + readingListName);
        this.waitForElementNotPresent(By.xpath(SAVE_TO_READING_LIST_MENU_TITLE),
                "SAVE_TO_THE_READING_LIST_MENU is still present");

    }

    public boolean assertArticleTitlePresent(){
        return this.assertElementPresent(By.xpath(ARTICLE_TITLE));
    }


}
