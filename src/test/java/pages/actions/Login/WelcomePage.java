package pages.actions.Login;

import browser.DriverHelper;
import pages.interfaceHandler.InterfaceFactory;
import pages.interfaceHandler.PlatformInterface;

public class WelcomePage {

    public InterfaceFactory interfaceFactory = new InterfaceFactory();
    public PlatformInterface runnerInfo;
    DriverHelper driverHelper;

    public WelcomePage() {
        driverHelper = new DriverHelper();
        runnerInfo = interfaceFactory.getMobilePlatform();
    }

    public void validateWelcome () {
        System.out.println("Welcome Page loaded");
    }
}
