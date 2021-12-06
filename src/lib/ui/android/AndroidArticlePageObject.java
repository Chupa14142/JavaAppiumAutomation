package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']";
                FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
                OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
                OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
                ADD_TO_MY_LIST_OVERLAY = "xpath://*[@text='GOT IT']";
                MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']";
                MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
                CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                //    DZ
                OPTIONS_BY_TEXT_TPL = "xpath://android.widget.LinearLayout//.[@text='{selected_options}']";
                SAVE_TO_READING_LIST_MENU_TITLE = "xpath://*[@text='Save to reading list']";
                NAME_OF_EXISTING_READING_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{reading_list_name}']";
                MORE_OPTIONS_POPUP = "xpath://*[@class='android.widget.ListView']";
                ARTICLE_TITLE_TPL = "xpath://*[(@resource-id='org.wikipedia:id/page_list_item_title' and @text='{articleTitle}')]";
                ARTICLE_DESCRIPTION_TPL = "xpath://*[(@resource-id='org.wikipedia:id/page_list_item_description' and @text='{articleDescription}')]";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
