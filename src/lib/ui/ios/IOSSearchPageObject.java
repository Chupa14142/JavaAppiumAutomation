package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_ELEMENT_INIT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
//      SEARCH_CANCEL_MINI_BUTTON = "id:clear mini";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "xpath:(//XCUIElementTypeCollectionView)[2]/XCUIElementTypeCell";
        EMPTY_RESULTS_LABEL = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        // FOR DZ
        LIST_OF_ARTICLE_TITLES = "xpath:(//XCUIElementTypeCollectionView)[2]//XCUIElementTypeStaticText[1]";
        ARTICLE_TITLE_AND_DESCRIPTION_TPL =
                "xpath://XCUIElementTypeStaticText[@name='{TITLE}']/../XCUIElementTypeStaticText[@name={DESCRIPTION}']";
    }



    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
