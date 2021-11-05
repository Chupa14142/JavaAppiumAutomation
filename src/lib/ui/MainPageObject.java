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

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.withMessage(error_message + "\n").until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 10);
    }

    public WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementPresentAndClick(By by, String error_message) {
        return waitForElementPresentAndClick(by, error_message, 15);
    }

    public WebElement waitForElementPresentAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement waitForElementPresentAndClear(By by, String error_message) {
        return waitForElementPresentAndClear(by, error_message, 20);
    }

    public WebElement waitForElementPresentAndSendKeys(By by, String error_message, String value) {
        WebElement element = waitForElementPresentAndClear(by, error_message);
        element.sendKeys(value);
        return element;
    }

    public String getElementTextByAttribute(By by, String error_message) {
        String elementTextByAttribute = waitForElementPresent(by, error_message).getAttribute("text");
        return elementTextByAttribute;
    }

    public void assertElementHasText(By by, String expectedElementText, String error_message) {
        String actualElementText = getElementTextByAttribute(by, error_message);
        Assert.assertEquals(error_message, actualElementText, expectedElementText);
    }

    public List<WebElement> waitForElementsPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.withMessage(error_message).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> waitForElementsPresent(By by, String error_message) {
        return waitForElementsPresent(by, error_message, 15);
    }

    public int countOfElementsLocated(By by, String error_message) {
        int count = waitForElementsPresent(by, error_message).size();
        System.out.println("countOfElementsLocated = " + count);
        return count;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait
                .withMessage(error_message + "\n")
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean waitForElementNotPresent(By by, String error_message) {
        return this.waitForElementNotPresent(by, error_message, 15);
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

    public boolean isSelectedTextPresent(By by, String error_message, String expectedText, long timeoutInSeconds) {
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

    public void swipeElementToLeft(By by, String error_message) {
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

    public int getAmountOfElements(By by) {
        List<WebElement> list = driver.findElements(by);
        return list.size();
    }

    public void assertElementNoPresent(By by, String error_message) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed not be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(By by, String error_message, String attribute, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


// DZ

    public boolean assertElementPresent(By by) {
        boolean isElementPresent = driver.findElement(by).isDisplayed();
        if (isElementPresent) {
            return true;
        } else throw new AssertionError("Element with locator " + by + " is not present");
    }


}
