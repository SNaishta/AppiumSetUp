package pages.actions.Login;

import browser.DriverHelper;
import pages.interfaceHandler.InterfaceFactory;
import pages.interfaceHandler.PlatformInterface;
import pages.objects.Login.LoginPageObject;

import java.util.Arrays;
import java.util.List;

public class LoginPage extends LoginPageObject {
    DriverHelper driverHelper;
    InterfaceFactory interfaceFactory;
    PlatformInterface platformInterface;

    public LoginPage(DriverHelper driverHelper, InterfaceFactory interfaceFactory) {
        this.driverHelper = driverHelper;
        this.interfaceFactory = interfaceFactory;
        platformInterface = interfaceFactory.getMobilePlatform();
    }

    public void checkApplicationIsLaunched() {
        System.out.println("Application Launched");
        driverHelper.test();
        System.out.println(" GDPR GET TEXT *****************" + gdprAcceptButton.getText());

        List<String> myList = Arrays.asList("kostas","james");
        System.out.println( "******************** " + myList.stream().filter(t->t.startsWith("k")));
    }
    public void login() {
        platformInterface.login();
    }

    public void selectDate() {
        platformInterface.selectDate(this);
    }
}