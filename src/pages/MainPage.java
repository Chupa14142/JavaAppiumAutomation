package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

//    Search Wikipedia
    By searchFieldLocator = By.xpath("//*[@resource-id='org.wikipedia:id/search_container']");
    By moreOptionsButtonLocator = By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]");
    By goToPreviousPageButtonLocator = By.xpath("//*[@class=\"android.widget.ImageButton\"]");
    By closeSearchButtonLocator = By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']");

//    In the news
    By newsBlockLocator = By
        .xpath("//*[@resource-id=\"org.wikipedia:id/view_list_card_list\"]/android.widget.FrameLayout");
    By moreOptionsButtonInTheNews = By.xpath("(//android.widget.ImageView[@content-desc=\"More options\"])[1]");

//    In Future article
    By  moreOptionsButtonInFutureArticleLocator = By
        .xpath("(//*[@resource-id=\"org.wikipedia:id/view_list_card_header_menu\"])[1]");
    By hideCardButtonInFutureArticleLocator = By
        .xpath("(//android.widget.ImageView[@content-desc=\"More options\"])[1]");
    By saveButtonInFutureArticleLocator = By
            .xpath("//*[@resource-id=\"org.wikipedia:id/view_card_action_footer_button_text\"]");
    By shareButtonInFutureArticleLocator = By
            .xpath("//*[@resource-id=\"org.wikipedia:id/view_card_action_footer_share_button\"]");

//    Trending
By  moreOptionsButtonInTrendingLocator = By
        .xpath("(//*[@resource-id=\"org.wikipedia:id/view_list_card_header_menu\"])[2]");
    By hideCardButtonInFTrendingLocator = By
            .xpath("(//android.widget.ImageView[@content-desc=\"More options\"])[2]");
    By countOfTrendingCards = By
            .xpath("(//*[@resource-id=\"org.wikipedia:id/view_list_card_list\"]/android.widget.FrameLayout)");
    By moreOptionsInTrendingCard = By
            .xpath("(//android.widget.ImageView[@content-desc=\"More options\"])");
    By addToReadingListInTrends = By.xpath("//*[@text=\"Add to reading list\"]");
    By shareLinkInTrends = By.xpath("//*[@text=\"Share link\"]");
}
