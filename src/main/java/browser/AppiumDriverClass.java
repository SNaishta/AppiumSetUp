package browser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.Constants.*;


import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverClass {

    private static AppiumDriverClass appiumDriverClass;
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public AppiumDriverClass() {
        getDesiredCapabilities();
    }

    public DesiredCapabilities getDesiredCapabilities() {
        if (Globals.PLATFORM.IS_ANDROID_DEVICE) {
            capabilities = getAndroidCapabilities();
            ThreadVariables.appiumDriver.set(new AndroidDriver(getAppiumDriverUrl(), capabilities));
            Android.androidlog.debug(ThreadVariables.testDataFileName.get() + ": - Android Driver successfully launched!!");
        } else {
            capabilities = getIosCapabilities();
            ThreadVariables.appiumDriver.set(new IOSDriver(getAppiumDriverUrl(), capabilities));
            Ios.ioslog.debug(ThreadVariables.testDataFileName.get() + ": - iOS Driver successfully launched!!");
        }
        return capabilities;
    }


    private void setGlobalCapabilities() {
        capabilities.setCapability(MobileCapabilityType.UDID, ThreadVariables.csvValue.get().getPlatformValue(CSVs.UDID_KEY));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ThreadVariables.csvValue.get().getPlatformValue(CSVs.PLATFORM_VERSION_KEY));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ThreadVariables.csvValue.get().getPlatformValue(CSVs.DEVICE_NAME_KEY));
    }

    private DesiredCapabilities getAndroidCapabilities() {
        setGlobalCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, CSVs.ANDROID_APK_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Android.ANDROID_PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, Android.ANDROID_AUTOMATION_NAME);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Android.ANDROID_APP_ACTIVITY);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Android.ANDROID_APP_PACKAGE);
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);
        capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
        capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Integer.valueOf(ThreadVariables.csvValue.get().getPlatformValue(CSVs.SYSTEM_PORT_KEY)));
        return capabilities;
    }

    public DesiredCapabilities getIosCapabilities() {
        setGlobalCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, CSVs.IOS_IPA_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, Ios.IOS_AUTOMATION_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Ios.IOS_PLATFORM_NAME);
        capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, Integer.valueOf(ThreadVariables.csvValue.get().getPlatformValue(CSVs.WDALOCALPORT_KEY)));
        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_CONFIG_FILE, CSVs.IOS_CONFIG_FILE_PATH);

        return capabilities;
    }


    private URL getAppiumDriverUrl() {
        try {
            return new URL("http://127.0.0.1:" + String.valueOf(ThreadVariables.portNumber.get()) + "/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AppiumDriver getAppiumDriver() {
        return ThreadVariables.appiumDriver.get();
    }

    public static void setUpAppiumDriver() {
        if (appiumDriverClass != null) {
            appiumDriverClass = null;
        }

        if (appiumDriverClass == null)
            appiumDriverClass = new AppiumDriverClass();
    }

    public static void tearDownAppium() {
        if (ThreadVariables.appiumDriver != null) {
            if(Globals.PLATFORM.IS_IOS_DEVICE)
            ThreadVariables.appiumDriver.get().removeApp(Ios.IOS_BUNDLE_ID);
        } else ThreadVariables.appiumDriver.get().close();
        appiumDriverClass = null;
    }
}