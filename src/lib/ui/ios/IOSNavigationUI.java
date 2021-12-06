package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static{
        MY_LISTS_BUTTON = "id:Saved";
    }

    public IOSNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
