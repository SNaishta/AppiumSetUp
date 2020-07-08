package pages.interfaceHandler;

import browser.DriverHelper;
import pages.flows.AndroidFlow;
import pages.flows.iOSFlow;
import utilities.Constants.Globals;

public class InterfaceFactory extends DriverHelper {

    public AndroidFlow androidFlow;
    public iOSFlow iOSFlow;

    public InterfaceFactory() {
        super();
    }

    public PlatformInterface getMobilePlatform() {
        if (Globals.PLATFORM.IS_ANDROID_DEVICE) {
            if (androidFlow == null) {
                return androidFlow = new AndroidFlow();
            }
            return androidFlow;
        } else if (Globals.PLATFORM.IS_IOS_DEVICE) {
            if (iOSFlow == null) {
                return iOSFlow = new iOSFlow();
            }
            return iOSFlow;
        }
        return null;
    }
}
