package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Test
    public void testCheckTestCaseClass() {
        System.out.println("Name of test before changing: " + this.getName());

        this.setName("For QA Automation");
        System.out.println("Name of test after changing: " + this.getName());
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
//      driver = new AndroidDriver(new URL(AppiumURL), capabilities);
//        driver = new IOSDriver(new URL(AppiumURL), capabilities);
        driver = this.getNewDriverByPlatformEnv();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    protected void goToPreviousPage() {
        driver.navigate().back();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 8");
            capabilities.setCapability("platformVersion", "14.4");
            capabilities.setCapability("app",
                    "/Users/vadimzakharkin/Desktop/learnQaCourse/JavaAppiumAutomation/apks/Wikipedia.app");
        } else if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "andr80");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app",
                    "/Users/vadimzakharkin/Desktop/learnQA/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else {
            throw new Exception("Cannot get run platform form env variable" + platform);
        }
        return capabilities;
    }


    private AndroidDriver getAndroidDriver() throws Exception{
        return new  AndroidDriver(new URL(AppiumURL), getCapabilitiesByPlatformEnv());
    }

    private IOSDriver getIosDriver() throws Exception {
        return new IOSDriver(new URL(AppiumURL), getCapabilitiesByPlatformEnv());
    }


    private AppiumDriver getNewDriverByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        AppiumDriver appiumDriver;

        if(platform.equals(PLATFORM_IOS)) {
            appiumDriver = getIosDriver();
        } else if(platform.equals(PLATFORM_ANDROID)) {
            appiumDriver = getAndroidDriver();
        } else {
            throw new Exception("Cannot get Driver by Platform Env" + platform);
        }
        return appiumDriver;
    }

}
