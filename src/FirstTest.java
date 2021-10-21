import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
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
}



