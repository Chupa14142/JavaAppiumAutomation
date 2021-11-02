package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class DZ {

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
    public void saveTwoArticles() {
        clickInSearchInputAndSearchText("java");

        String first_article_title = getArticleTitleByNumber(1);
        waitForElementPresentAndClick(
                By.xpath("//*[@text='" +  first_article_title + "']"),
                "Can't find " + first_article_title + " title"
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_header_image_placeholder"),
                "Can't find page header"
        );

        String name_of_new_list_name = "Java Reading List";

        addArticleToTheNewReadingList(name_of_new_list_name,"Can't find Reading list button");

        driver.navigate().back();

        clickInSearchInputAndSearchText("java");


        String second_article_title = getArticleTitleByNumber(2);
        waitForElementPresentAndClick(
                By.xpath("//*[@text='" +  second_article_title + "']"),
                "Can't find " + second_article_title + " title"
        );

        addArticleToExistReadingList(name_of_new_list_name,"Can't find: " + name_of_new_list_name);

        driver.navigate().back();

        waitForElementPresentAndClick(
                By.xpath("//*[@content-desc='My lists']"),"Can't find My list button");

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_new_list_name + "']"),
                "Can't find Reading list"
        );

        int countArticlesInReadingListBeforeDelete = countOfElementsLocated(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_container'])"),
                "Can't find Articles container"
        );

        Assert.assertTrue("Count of Articles != 2",countArticlesInReadingListBeforeDelete == 2);

        leftSwipeOnElement(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='"+ first_article_title + "']"),
                "Can't find first article container"
        );


        int countArticlesInReadingListAfterDelete = countOfElementsLocated(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_container'])"),
                "Can't find Articles container"
        );
        Assert.assertTrue("Count of Articles != 2",countArticlesInReadingListAfterDelete == 1);

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
        return waitForElementPresentAndClear(by, error_message, 20);
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


// DZ

    public void clickInSearchInputAndSearchText(String searchingText) {
        waitForElementPresentAndClick(
                By.xpath("(//*[@class='android.widget.TextView'])[1]"), "Can't find the Search field");

        waitForElementsPresent(By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Can't find Clear button");

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"), "Can't find the Search field", searchingText
        );

    }

    public String getArticleTitleByNumber(int number) {
      String article_title = waitForElementAndGetAttribute(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'])[" + number + "]"),
                "Can't find " + number + " article title","text",15);
      return article_title;
    }

    public void clickMoreOptionsAndSelectOptionByText(String option, String error_message) {
        waitForElementPresentAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find More Options Button");
        String optionsXpath = "//android.widget.LinearLayout//.[@text='%s']";
        String selectedOption = String.format(optionsXpath,option);
        By selectedOptionLocator = By.xpath(selectedOption);
        waitForElementPresentAndClick(selectedOptionLocator,
                error_message + "by locator: " + selectedOptionLocator);
    }

    public void createNewReadingListWithName(String readingListName, String error_message) {
        By readingListInputLocator = By.xpath("//*[@text='My reading list' and @resource-id='org.wikipedia:id/text_input']");
        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button' and @text='GOT IT']"), error_message);

        waitForElementPresentAndSendKeys(readingListInputLocator, error_message, readingListName);
        driver.findElement(By.xpath("//*[@text='OK']")).click();
        waitForElementNotPresent(readingListInputLocator,"Reading List Input is still here",15);
    }


    public void addArticleToTheNewReadingList(String readingListName, String error_message) {
        clickMoreOptionsAndSelectOptionByText("Add to reading list",error_message);
        createNewReadingListWithName(readingListName,error_message);
    }

    public void addArticleToExistReadingList(String readingListName, String error_message) {
        clickMoreOptionsAndSelectOptionByText("Add to reading list",error_message);
        By listNameXpath = By.xpath("//*[@text='" + readingListName + "'][@resource-id='org.wikipedia:id/item_title']");
        int countOfExistList = countOfElementsLocated(listNameXpath, error_message);
        if(countOfExistList == 1) {
            waitForElementPresentAndClick(listNameXpath,error_message);
            waitForElementNotPresent(listNameXpath,"Element is already here " + listNameXpath,10);
        } else throw new AssertionError("Listname with locator " + listNameXpath + "doesn't exist");
    }





}
