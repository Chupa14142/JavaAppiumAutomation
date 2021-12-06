package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{ARTICLE_TITLE}']";
//         DZ
        LIST_OF_ARTICLES_CONTAINER = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_image_container']";
        YOU_HAVE_NO_ARTICLES_TITLE = "xpath://*[@resource-id='org.wikipedia:id/reading_list_empty_text']";
    }

    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
