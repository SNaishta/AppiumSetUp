package pages.objects.Login;

import browser.AppiumDriverClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

    public AppiumDriver driver;

    @iOSXCUITFindBy(id = "title")
    @AndroidFindBy(id = "gdpr_accept_button_text")
    public MobileElement gdprAcceptButton;

    public LoginPageObject(){
        this.driver = AppiumDriverClass.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}
