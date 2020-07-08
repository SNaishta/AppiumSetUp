package utilities.DeviceConnectionAutoConfig;

import browser.DriverHelper;
import utilities.Constants.*;


import java.util.List;

public class ModifyDeviceConnectionFilesAndroid{

    public List<MobileDevice> obtainUdidAndVersionAndroidDevices(){

        List<String> adbDevicesdOutput = new DriverHelper().runTerminalMultiLineReturnCommand("adb devices");

        for(int i = 1; i < adbDevicesdOutput.size()-1; i++){
            String unformattedUdid =  adbDevicesdOutput.get(i);
            int deviceIndex = adbDevicesdOutput.get(i).indexOf("dev", 0);
            String formattedUdid = unformattedUdid.substring(0, deviceIndex-1);
            String versionNumber = new DriverHelper().runTerminalCommand("adb -s " + formattedUdid + " shell getprop ro.build.version.release");
            DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.add(new MobileDevice(versionNumber, formattedUdid));
        }

        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.size(); i++){
            System.out.println("Index " + i + " for version: " + DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.get(i).getVersionNumber());
            System.out.println("Index " + i + " for udid: " + DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.get(i).getUdid());
        }

        return DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList;
    }

    public void assignPlatformIdentifier() {

        for (int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.size(); i++) {
            DeviceConnectionAutomation.androidDeviceIdentifierList.add("ANDROIDTestData" + (i+1));
        }

        for(int i = 0; i < DeviceConnectionAutomation.androidDeviceIdentifierList.size(); i++){
            System.out.println(DeviceConnectionAutomation.androidDeviceIdentifierList.get(i));
        }
    }

    public void processDevices(){
        obtainUdidAndVersionAndroidDevices();
        assignPlatformIdentifier();
        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.size(); i++){
            new CSVWriterDeviceConnectionAutomation().writeValueToFile("UDID", DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.get(i).getUdid(), DeviceConnectionAutomation.androidDeviceIdentifierList.get(i));
        }
        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.size(); i++){
            new CSVWriterDeviceConnectionAutomation().writeValueToFile("PLATFORMVERSION", DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.get(i).getVersionNumber(), DeviceConnectionAutomation.androidDeviceIdentifierList.get(i));
        }
        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.size(); i++){
            new CSVWriterDeviceConnectionAutomation().writeValueToFile("DEVICENAME", DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.get(i).getUdid(), DeviceConnectionAutomation.androidDeviceIdentifierList.get(i));
        }
        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.size(); i++){
            new CSVWriterDeviceConnectionAutomation().writeValueToFile("ANDROIDFINGERPRINT", DeviceConnectionAutomation.deviceVersionAndUDIDAndroidList.get(i).getUdid(), DeviceConnectionAutomation.androidDeviceIdentifierList.get(i));
        }
    }

}
