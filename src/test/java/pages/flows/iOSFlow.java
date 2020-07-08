package pages.flows;

import pages.actions.Login.LoginPage;
import pages.interfaceHandler.PlatformInterface;
import browser.DriverHelper;

public class iOSFlow extends DriverHelper implements PlatformInterface {

    public iOSFlow() {
        super();
    }

    public void login() {
        System.out.println("I am iOS");
    }

    public void selectDate(LoginPage loginPage) {
        System.out.println("Ios date select");
    }
}
