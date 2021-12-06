package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static{
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{ARTICLE_TITLE}']";
//         DZ
        LIST_OF_ARTICLES_CONTAINER = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_image_container']";
        YOU_HAVE_NO_ARTICLES_TITLE = "xpath://*[@resource-id='org.wikipedia:id/reading_list_empty_text']";
    }

    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
