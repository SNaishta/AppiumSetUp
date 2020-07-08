package utilities.DeviceConnectionAutoConfig;

import browser.DriverHelper;
import utilities.Constants.*;


import java.util.List;

public class ModifyDeviceConnectionFilesIos{

    public List<MobileDevice> obtainUdidAndVersionIosDevices(){

        List<String> iosDeviceConnectedOutput = new DriverHelper().runTerminalMultiLineReturnCommand("instruments -s devices");

        for(int i = 2; i < iosDeviceConnectedOutput.size(); i++){
            if(!iosDeviceConnectedOutput.get(i).contains("Simulator")){
                try {
                    String replacementLine = iosDeviceConnectedOutput.get(i);
                    int firstIndex = replacementLine.indexOf("(", 0)+1;
                    int secondIndex = replacementLine.indexOf(")", 0);
                    String versionNumber = replacementLine.substring(firstIndex, secondIndex);

                    firstIndex = replacementLine.indexOf("[", 0)+1;
                    secondIndex = replacementLine.indexOf("]", 0);
                    String udid = replacementLine.substring(firstIndex, secondIndex);

                    DeviceConnectionAutomation.deviceVersionAndUDIDIosList.add(new MobileDevice(versionNumber, udid));
                } catch(Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
        }

        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDIosList.size(); i++){
            System.out.println("Index " + i + " for version: " + DeviceConnectionAutomation.deviceVersionAndUDIDIosList.get(i).getVersionNumber());
            System.out.println("Index " + i + " for udid: " + DeviceConnectionAutomation.deviceVersionAndUDIDIosList.get(i).getUdid());
        }

        return DeviceConnectionAutomation.deviceVersionAndUDIDIosList;
    }

    public void assignPlatformIdentifier() {

        for (int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDIosList.size(); i++) {
            DeviceConnectionAutomation.iosDeviceIdentifierList.add("IOSTestData" + (i+1));
        }
    }

    public void processDevices(){
        obtainUdidAndVersionIosDevices();
        assignPlatformIdentifier();
        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDIosList.size(); i++){
            new CSVWriterDeviceConnectionAutomation().writeValueToFile(CSVs.UDID_KEY, DeviceConnectionAutomation.deviceVersionAndUDIDIosList.get(i).getUdid(), DeviceConnectionAutomation.iosDeviceIdentifierList.get(i));
        }
        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDIosList.size(); i++){
            new CSVWriterDeviceConnectionAutomation().writeValueToFile(CSVs.PLATFORM_VERSION_KEY, DeviceConnectionAutomation.deviceVersionAndUDIDIosList.get(i).getVersionNumber(), DeviceConnectionAutomation.iosDeviceIdentifierList.get(i));
        }
        for(int i = 0; i < DeviceConnectionAutomation.deviceVersionAndUDIDIosList.size(); i++){
            new CSVWriterDeviceConnectionAutomation().writeValueToFile(CSVs.DEVICE_NAME_KEY, DeviceConnectionAutomation.deviceVersionAndUDIDIosList.get(i).getUdid(), DeviceConnectionAutomation.iosDeviceIdentifierList.get(i));
        }
    }
}
