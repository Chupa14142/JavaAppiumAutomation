package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {

    protected static String
    MY_LISTS_BUTTON;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }


    public void clickMyLists() {
        this.waitForElementPresentAndClick(MY_LISTS_BUTTON, "Cannot find navigation to My list");
    }




}
