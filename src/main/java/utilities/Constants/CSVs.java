package utilities.Constants;

public interface CSVs {

    static String currentUsersHomeDir = System.getProperty("user.dir");

    // PLATFORM DATA KEYS
    String WDALOCALPORT_KEY = "WDALOCALPORT";
    String SYSTEM_PORT_KEY = "SYSTEMPORT";
    String PLATFORM_VERSION_KEY = "PLATFORMVERSION";
    String DEVICE_NAME_KEY = "DEVICENAME";
    String UDID_KEY = "UDID";

    //CSV FILE PATHS
    String testDataCSVFolderPath = currentUsersHomeDir + "/src/main/resources/testData/csvFiles/";
    String testDataPropertiesFolderPath = currentUsersHomeDir+ "/src/main/resources/testData/propertiesFiles/";

    String DEVICE_LOGIN_PROPERTIES_PATH = testDataPropertiesFolderPath + "DeviceLogin.properties";
    String DEVICE_NAME_PROPERTIES_PATH = testDataPropertiesFolderPath + "DeviceName.properties";

    String PLATFORM_DATA_CSV_PATH = testDataCSVFolderPath + "PlatformData.csv";
    String TEST_DATA_CSV_PATH = testDataCSVFolderPath + "TestData.csv";

    String ANDROID_APK_NAME = currentUsersHomeDir + "/Build/pi-app-3.8.0(380438)-[HEAD-9860d5e7d]-stage-release.apk";
    String IOS_IPA_NAME = currentUsersHomeDir + "/Build/Test-DEV.ipa";

    String ANDROID_TESTNG_XML_FILE_PATH = currentUsersHomeDir + "/src/test/resources/runner/testNgAndroid.xml";
    String IOS_TESTNG_XML_FILE_PATH = currentUsersHomeDir + "/src/test/resources/runner/testNgIos.xml";

    String IOS_CONFIG_FILE_PATH = currentUsersHomeDir + "/Build/Config.xcconfig";
    
}
