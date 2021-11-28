package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

            static {
                        SEARCH_ELEMENT_INIT = "xpath://*[@text='Search Wikipedia']";
                        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
                        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
                        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                        EMPTY_RESULTS_LABEL = "xpath://*[@text='No results found']";
                        // FOR DZ
                        LIST_OF_ARTICLE_TITLES = "xpath:(//*[@resource-id='org.wikipedia:id/page_list_item_title'])";
                        ARTICLE_TITLE_AND_DESCRIPTION_TPL =
                                "xpath://android.widget.LinearLayout/android.widget.TextView[@text='{TITLE}']/../android.widget.TextView[@text='{DESCRIPTION}']";

            }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
