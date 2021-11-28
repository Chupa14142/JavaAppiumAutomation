package lib;

import io.appium.java_client.AppiumDriver;

import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestCase extends TestCase {


    protected AppiumDriver driver;

    @Test
    public void testCheckTestCaseClass() {
        System.out.println("Name of test before changing: " + this.getName());

        this.setName("For QA Automation");
        System.out.println("Name of test after changing: " + this.getName());
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
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

    private void skipWelcomePageForIOSApp() {
        if(Platform.getInstance().isIOS()) {
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }


}
