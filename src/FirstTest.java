import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class FirstTest {

    private AppiumDriver driver;


    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "andr80");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "/Users/vadimzakharkin/Desktop/learnQA/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void ternDown() {
        driver.quit();
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementPresentAndClick(
                By.xpath(
                        "(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can't find the Search field", 15
        );

        waitForElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button"
        );

        String searchingText = "java";

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find the Search field",
                searchingText);

        String articleTitle = "Java (programming language)";

        waitForElementPresent(
                By.xpath("//*[@text='" +  articleTitle + "']"),
                "Can't find Java (programming language) article" + searchingText);

        driver.runAppInBackground(Duration.ofSeconds(2));

        waitForElementPresent(
                By.xpath("//*[@text='" +  articleTitle + "']"),
                "Can't find Java (programming language) article" + searchingText);



    }


    @Test
    public void testChangeScreenOrientationOnSearchResultPage() {

        waitForElementPresentAndClick(
                By.xpath(
                        "(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can't find the Search field", 15
        );

        waitForElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button"
        );

        String searchingText = "java";

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find the Search field",
                searchingText);

        String articleTitle = "Java (programming language)";

        waitForElementPresentAndClick(
                By.xpath("//*[@text='" +  articleTitle + "']"),
                "Can't find Java (programming language) article" + searchingText);

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find text of article",
                "text", 15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find text of article",
                "text", 15
        );

        Assert.assertEquals(
                "Article title have benn change after rotation",
                title_before_rotation, title_after_rotation);



        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find text of article",
                "text", 15
        );

        Assert.assertEquals(
                "Article title have benn change after rotation",
                title_before_rotation, title_after_second_rotation);

    }


    @Test
    public void testAmountOfEmptySearch() {
        waitForElementPresentAndClick(
                By.xpath(
                        "(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can't find the Search field", 15
        );

        waitForElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button"
        );

        String searchingText = "dfdfdfdfdf";
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']";
        String no_results_xpath = "//*[@text='No results found']";

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find the Search field",
                searchingText);

        waitForElementPresent(
                By.xpath(no_results_xpath),
                "Can't find No result text");

        assertElementNoPresent(
                By.xpath(search_result_locator),
                search_result_locator + " is Present on the Screen"
        );




    }

    @Test
    public void testTest() {
        WebElement element = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        element.click();
        waitForElementNotPresent(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "sdsd",14);
    }



    @Test
    public void testSaveSelectedArticle() {

        waitForElementPresentAndClick(
                By.xpath(
                        "(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can't find the Search field", 15
        );

        waitForElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button"
        );

        String searchingText = "java";

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find the Search field",
                searchingText);

        String articleTitle = "Java (programming language)";
        waitForElementPresentAndClick(
                By.xpath("//*[@text='" +  articleTitle + "']"),
                "Can't find Java (programming language) article" + searchingText);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_header_image_placeholder"),
                "Can't find page header"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find More options Button"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find Add to reading list text"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@text='GOT IT']"),
                "Can't find GOT IT Button"
        );

        String name_of_folder = "Java programming language";

        waitForElementPresentAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                "Can't find Name of this list input",
                name_of_folder
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't find OK Button"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find Close(X) Button");

        waitForElementPresentAndClick(By.xpath("//*[@content-desc='My lists']"),
                "Can not find My list Button");

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_folder + "']"),
                "Can not find List with text = ");

        waitForElementPresent(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "sdsdsd");


        leftSwipeOnElement(
                By.xpath("//*[@text='" + articleTitle + "']"),
                "Can not find Created List"
        );
        //*[@resource-id='org.wikipedia:id/page_list_item_container']

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "Created List is still Present",
                10
        );


    }


    @Test
    public void testToSwipeArticle() {
        waitForElementPresentAndClick(
                By.xpath(
                        "(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can't find the Search field", 15
        );

        waitForElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button"
        );

        String searchingText = "java";

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find the Search field",
                searchingText);

        waitForElementPresentAndClick(
                By.xpath("(//*[@text='Java'])[1]"),
                "Can't find first article with text = " + searchingText);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_header_image_placeholder"),
                "Can't find page header");

        swipeToFindElement(
                By.xpath("//*[@content-desc='Yogyakarta Special Region of Yogyakarta']"),
                "",
                10
        );


    }

    @Test
    public void checkForSearchWikipediaTextTest() {

        waitForElementPresent(By.xpath("(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can't find an element Search Box",
                15);

        assertElementHasText(
                By.xpath("(//*[@class='android.widget.TextView'])[1]"),
                "Search Wikipedia",
                "Element has incorrect text");


    }

    @Test
    public void cancelSearchTest() {

        waitForElementPresentAndClick(
                By.xpath(
                        "(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can't find the Search field", 15
        );

        waitForElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button"
        );

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find the Search field",
                "manual");

        int countOfElementWasFound = countOfElementsLocated(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Can't find page_list_item_title"
        );

        Assert.assertTrue("countOfElementWasFound < 2", countOfElementWasFound > 2);

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button"
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "List item is still present",
                15
        );

    }


    @Test
    public void checkForTextPresentInTitleTest() {
        waitForElementPresentAndClick(
                By.xpath(
                        "(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can't find the Search field", 15
        );

        waitForElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button"
        );

        String searchingText = "java";

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find the Search field",
                searchingText);

        boolean isSelectedTextPresent = isSelectedTextPresentInResult(
                "SelectedText is not Present ",
                searchingText,
                15
        );

        Assert.assertTrue("Text = " + searchingText + " is not present in every title or description",
                isSelectedTextPresent == true);

    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.withMessage(error_message + "\n").until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 10);
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message) {
        return waitForElementPresentAndClick(by, error_message, 15);
    }

    private WebElement waitForElementPresentAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement waitForElementPresentAndClear(By by, String error_message) {
        return waitForElementPresentAndClear(by, error_message, 15);
    }

    private WebElement waitForElementPresentAndSendKeys(By by, String error_message, String value) {
        WebElement element = waitForElementPresentAndClear(by, error_message);
        element.sendKeys(value);
        return element;
    }

    private String getElementTextByAttribute(By by, String error_message) {
        String elementTextByAttribute = waitForElementPresent(by, error_message).getAttribute("text");
        return elementTextByAttribute;
    }

    private void assertElementHasText(By by, String expectedElementText, String error_message) {
        String actualElementText = getElementTextByAttribute(by, error_message);
        Assert.assertEquals(error_message, actualElementText, expectedElementText);
    }

    private List<WebElement> waitForElementsPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.withMessage(error_message).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private List<WebElement> waitForElementsPresent(By by, String error_message) {
        return waitForElementsPresent(by, error_message, 15);
    }

    private int countOfElementsLocated(By by, String error_message) {
        int count = waitForElementsPresent(by, error_message).size();
        System.out.println("countOfElementsLocated = " + count);
        return count;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait
                .withMessage(error_message + "\n")
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

//    private boolean isSelectedTextPresent(By by, String error_message, String expectedText, long timeoutInSeconds) {
//        List<WebElement> list = waitForElementsPresent(by, error_message, timeoutInSeconds);
//        boolean result = false;
//        for (WebElement element : list) {
//            String getElementTextWithAttribute = element.getAttribute("text").toLowerCase();
//            if (getElementTextWithAttribute.contains(expectedText.toLowerCase())) {
//                result = true;
//            } else {
//                result = false;
//            }
//        }
//            return result;
//    }

    private boolean isSelectedTextPresent(By by, String error_message, String expectedText, long timeoutInSeconds) {
        List<WebElement> list = waitForElementsPresent(by, error_message, timeoutInSeconds);
        Pattern pattern = Pattern.compile(expectedText.toLowerCase());
        boolean result = false;
        for (WebElement element : list) {
            String getElementTextWithAttribute = element.getAttribute("text").toLowerCase();
            if (pattern.matcher(getElementTextWithAttribute.toLowerCase()).find()) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    private boolean isSelectedTextPresentInResult(String error_message, String expectedText, long timeoutInSeconds) {
        List<WebElement> titleList = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));
        List<WebElement> descriptionList = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']"));

        Pattern pattern = Pattern.compile(expectedText.toLowerCase());

        if (titleList.size() == descriptionList.size()) {
            for (int i = 0; i < titleList.size(); i++) {
                if (
                        pattern.matcher(titleList.get(i).getAttribute("text").toLowerCase()).find() ||
                                pattern.matcher(descriptionList.get(i).getAttribute("text").toLowerCase()).find()
                ) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < titleList.size(); i++) {
                if (pattern.matcher(titleList.get(i).getAttribute("text").toLowerCase()).find()) {
                    return true;
                }
            }
            for (int i = 0; i < descriptionList.size(); i++) {
                if (pattern.matcher(descriptionList.get(i).getAttribute("text").toLowerCase()).find()) {
                    return true;
                }
            }
        }
        return false;
    }


    public void swipeUp(long timeOfSwipe) {
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.getHeight() * 0.8);
        int end_y = (int) (size.getHeight() * 0.2);

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        this.swipeUp(200);
    }

    public void swipeToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Can't find element by swiping up. \n" + error_message);
                return;
            }

            this.swipeUpQuick();
            already_swiped++;
        }
    }

    public void leftSwipeOnElement(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        System.out.println(middle_y);

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();

    }

    protected int getAmountOfElements(By by) {
        List<WebElement> list = driver.findElements(by);
        return list.size();
    }

    protected void assertElementNoPresent (By by, String error_message) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed not be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }


    private String waitForElementAndGetAttribute(By by, String error_message,String attribute, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
        return element.getAttribute(attribute);
    }


}



