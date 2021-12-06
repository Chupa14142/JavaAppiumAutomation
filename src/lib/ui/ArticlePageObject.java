package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {


    protected static String
            ARTICLE_TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_MODAL_WINDOW,
    //    DZ
    OPTIONS_BY_TEXT_TPL,
    SAVE_TO_READING_LIST_MENU_TITLE,
    NAME_OF_EXISTING_READING_LIST_TPL,
    MORE_OPTIONS_POPUP,
    ARTICLE_TITLE_TPL,
    ARTICLE_DESCRIPTION_TPL;

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
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
        this.swipeToFindElement(FOOTER_ELEMENT, "Cannot swipe to footer", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,"Cannot swipe to footer",40);
        }

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

    public void addArticleToMySavedList() {
        this.waitForElementPresentAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find Options to Add article to the reading list");
    }

    public void closeModalWindow() {
        this.waitForElementPresentAndClick(
                CLOSE_MODAL_WINDOW,"Cannot find Modal window locator");
        this.waitForElementNotPresent(CLOSE_MODAL_WINDOW,"Modal window is still present");
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
