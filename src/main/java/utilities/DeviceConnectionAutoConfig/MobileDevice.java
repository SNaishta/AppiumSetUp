package utilities.DeviceConnectionAutoConfig;

public class MobileDevice {

    String versionNumber;
    String udid;

    public MobileDevice(String versionNumber, String udid){
        this.versionNumber = versionNumber;
        this.udid = udid;
    }

    public String getUdid() {
        return udid;
    }

    public String getVersionNumber() {
        return versionNumber;
    }
}
