package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_MODAL_WINDOW = "id:places auth close";
        //    DZ
        OPTIONS_BY_TEXT_TPL = "xpath://android.widget.LinearLayout//.[@text='{selected_options}']";
        SAVE_TO_READING_LIST_MENU_TITLE = "xpath://*[@text='Save to reading list']";
        NAME_OF_EXISTING_READING_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{reading_list_name}']";
        MORE_OPTIONS_POPUP = "xpath://*[@class='android.widget.ListView']";
        ARTICLE_TITLE_TPL = "xpath://*[(@resource-id='org.wikipedia:id/page_list_item_title' and @text='{articleTitle}')]";
        ARTICLE_DESCRIPTION_TPL = "xpath://*[(@resource-id='org.wikipedia:id/page_list_item_description' and @text='{articleDescription}')]";
    }




    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
