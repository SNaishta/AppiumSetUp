package utilities.Constants;

import utilities.DeviceConnectionAutoConfig.MobileDevice;

import java.util.ArrayList;
import java.util.List;

public interface DeviceConnectionAutomation {

    List<String> androidDeviceIdentifierList = new ArrayList<String>();
    List<String> iosDeviceIdentifierList = new ArrayList<String>();
    List<MobileDevice> deviceVersionAndUDIDIosList = new ArrayList<MobileDevice>();
    List<MobileDevice> deviceVersionAndUDIDAndroidList = new ArrayList<MobileDevice>();
    String POM_PLATFORM_VALUE = System.getProperty("pomPlatformValue");

}
