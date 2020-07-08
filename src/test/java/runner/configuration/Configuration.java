package runner.configuration;

import org.testng.annotations.BeforeSuite;
import utilities.Constants.Android;
import utilities.Constants.DeviceConnectionAutomation;
import utilities.DeviceConnectionAutoConfig.CSVWriterDeviceConnectionAutomation;
import utilities.DeviceConnectionAutoConfig.ModifyDeviceConnectionFilesAndroid;
import utilities.DeviceConnectionAutoConfig.ModifyDeviceConnectionFilesIos;

public class Configuration {

    @BeforeSuite
    public void configureTests(){
        if(DeviceConnectionAutomation.POM_PLATFORM_VALUE.equalsIgnoreCase(Android.ANDROID_PLATFORM_NAME)){
            new ModifyDeviceConnectionFilesAndroid().processDevices();
            new CSVWriterDeviceConnectionAutomation().modifyTestNGFile(DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.size());
        }else {
            new ModifyDeviceConnectionFilesIos().processDevices();
            new CSVWriterDeviceConnectionAutomation().modifyTestNGFile(DeviceConnectionAutomation.deviceVersionAndUDIDIosList.size());
        }
    }

}
