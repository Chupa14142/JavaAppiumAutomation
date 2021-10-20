import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
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

public class FirstTest {

    private AppiumDriver driver;


    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","andr80");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app",
                "/Users/vadimzakharkin/Desktop/learnQA/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void ternDown(){
        driver.quit();
    }

    @Test
    public void firstTest(){

        waitForElementPresent(By.xpath("(//*[@class=\"android.widget.TextView\"])[1]"),
                "",
                15);

        getElementTextByAttribute(By.xpath("(//*[@class=\"android.widget.TextView\"])[1]"),
                "Can not find an element");
    }





    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        return wait.withMessage(error_message + "\n").until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by,error_message,10);
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutInSeconds) {
      WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
      element.click();
      return element;
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message) {
        return waitForElementPresentAndClick(by,error_message,15);
    }

    private WebElement waitForElementPresentAndClear(By by, String error_message,long timeoutInSeconds) {
      WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
      element.clear();
      return element;
    }

    private WebElement waitForElementPresentAndClear(By by, String error_message) {
      return  waitForElementPresentAndClear(by,error_message,15);
    }

    private WebElement waitForElementPresentAndSendKeys(By by, String error_message, String value) {
        WebElement element = waitForElementPresentAndClear(by,error_message);
        element.sendKeys(value);
        return element;
    }

    private String getElementTextByAttribute(By by, String error_message) {
        String elementTextByAttribute = waitForElementPresent(by,error_message).getAttribute("text");
        return elementTextByAttribute;
    }


    

}
