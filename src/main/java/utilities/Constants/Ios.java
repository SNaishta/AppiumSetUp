package utilities.Constants;

import org.apache.log4j.Logger;

public interface Ios {

    String IOS_CONFIG_FILE_PATH = System.getProperty("user.dir") + "/build/iosConfig.xcconfig";
    String IOS_APP_PATH = System.getProperty("user.dir") + "/build/Payload/Enterprise.app";
    String IOS_PLATFORM_NAME = "iOS";
    String IOS_AUTOMATION_NAME = "XCUITest";
    String IOS_BUNDLE_ID = "com.whitbread.pi";


    Logger ioslog = Logger.getLogger("iosLogger");


}
