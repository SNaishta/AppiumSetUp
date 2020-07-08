package pages.flows;

import pages.actions.Login.LoginPage;
import pages.interfaceHandler.PlatformInterface;
import browser.DriverHelper;

public class AndroidFlow extends DriverHelper implements PlatformInterface {

    public AndroidFlow() {
        super();
    }

    public void login() { // flow which is different from iOS and can use dependency injection
        System.out.println("This is Android");
    }

    public void selectDate(LoginPage loginPage) {
        System.out.println("ANDROID DATE SELECTED");
    }
}
