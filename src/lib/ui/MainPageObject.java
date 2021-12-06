package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.withMessage(error_message + "\n").until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 10);
    }

    public WebElement waitForElementPresentAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementPresentAndClick(String locator, String error_message) {
        return waitForElementPresentAndClick(locator, error_message, 15);
    }

    public WebElement waitForElementPresentAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement waitForElementPresentAndClear(String locator, String error_message) {
        return waitForElementPresentAndClear(locator, error_message, 20);
    }

    public WebElement waitForElementPresentAndSendKeys(String locator, String error_message, String value) {
        WebElement element = waitForElementPresentAndClear(locator, error_message);
        element.sendKeys(value);
        return element;
    }

    public String getElementTextByAttribute(String locator, String error_message) {
        String elementTextByAttribute = waitForElementPresent(locator, error_message).getAttribute("text");
        return elementTextByAttribute;
    }

    public void assertElementHasText(String locator, String expectedElementText, String error_message) {
        String actualElementText = getElementTextByAttribute(locator, error_message);
        Assert.assertEquals(error_message, actualElementText, expectedElementText);
    }

    public List<WebElement> waitForElementsPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.withMessage(error_message).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> waitForElementsPresent(String locator, String error_message) {
        return waitForElementsPresent(locator, error_message, 15);
    }

    public int countOfElementsLocated(String locator, String error_message) {
        int count = waitForElementsPresent(locator, error_message).size();
        System.out.println("countOfElementsLocated = " + count);
        return count;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait
                .withMessage(error_message + "\n")
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean waitForElementNotPresent(String locator, String error_message) {
        return this.waitForElementNotPresent(locator, error_message, 15);
    }

//    public boolean isSelectedTextPresent(By by, String error_message, String expectedText, long timeoutInSeconds) {
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

    public boolean isSelectedTextPresent(String locator, String error_message, String expectedText, long timeoutInSeconds) {
        List<WebElement> list = waitForElementsPresent(locator, error_message, timeoutInSeconds);
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

    public boolean isSelectedTextPresentInResult(String error_message, String expectedText, long timeoutInSeconds) {
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

    public void swipeToFindElement(String locator, String error_message, int max_swipes) {
         By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Can't find element by swiping up. \n" + error_message);
                return;
            }

            this.swipeUpQuick();
            already_swiped++;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message,int max_swipes) {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if(already_swiped > max_swipes) {
                Assert.assertTrue(error_message,this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y =
                this.waitForElementPresent(locator,"Cannot find element by Locator " + locator).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;

    }

    public void clickElementToTheRightUpperCorner(String locator,String error_message) {
        WebElement element = this.waitForElementPresent(locator + "/.." ,error_message);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(point_to_click_x,point_to_click_y)).perform();


    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
        if(Platform.getInstance().isAndroid()) {
            action.moveTo(PointOption.point(left_x, middle_y));
        } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(PointOption.point(offset_x,0));
        }
        action.release();
        action.perform();

    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List<WebElement> list = driver.findElements(by);
        return list.size();
    }

    public int waitForElementsPresentAndGetAmount(String locator, String error_message) {
        List<WebElement> list = waitForElementsPresent(locator, error_message);
        return list.size();
    }

    public void assertElementNoPresent(String locator, String error_message) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String default_message = "An element '" + locator + "' supposed not be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String error_message, String attribute, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


// DZ

    public boolean assertElementPresent(By by) {
        boolean isElementPresent = driver.findElement(by).isDisplayed();
        if (isElementPresent) {
            return true;
        } else throw new AssertionError("Element with locator " + by + " is not present");
    }


    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else throw new IllegalArgumentException("Cannot get type of locator: " + locator_with_type);
    }


}
