package browser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utilities.Constants.*;


public class Setup {

    public static synchronized String getTestDataFileName(List<Long> deviceThreadsList) {

        System.out.println("THE ID OF THE THREAD IS: " + Thread.currentThread().getId());
        ThreadVariables.id.set(Thread.currentThread().getId());

        if (Globals.PLATFORM.IS_ANDROID_DEVICE) {
            ThreadVariables.currentAndroidThreadsList.add(ThreadVariables.id.get());
        } else if (Globals.PLATFORM.IS_IOS_DEVICE){
            ThreadVariables.currentIOSThreadsList.add(ThreadVariables.id.get());
        }

        System.out.println("THE PLATFORM VALUE IN THE getTestDataFileName METHOD IS: " + ThreadVariables.platformValue.get());
        for (int i = 0; i < deviceThreadsList.size(); i++) {
            if (deviceThreadsList.get(i) == Thread.currentThread().getId()) {
                return ThreadVariables.platformValue.get() + "TestData" + (i + 1);
            }
        }
        return "";
    }

    public synchronized void setupAndroid() {

        ThreadVariables.platformValue.set("ANDROID");

        ThreadVariables.testDataFileName.set(getTestDataFileName(ThreadVariables.currentAndroidThreadsList));
        System.out.println("The test data fileName is: " + ThreadVariables.testDataFileName.get());

        long id = Thread.currentThread().getId();
        System.out.println("Android Before class-method. Thread id is: " + id);
    }


    public static synchronized void setupIOS() {
        ThreadVariables.platformValue.set("IOS");
        ThreadVariables.platformValue.get();
        ThreadVariables.testDataFileName.set(getTestDataFileName(ThreadVariables.currentIOSThreadsList));

        System.out.println("The test data fileName is: " + ThreadVariables.testDataFileName.get());

        long id = Thread.currentThread().getId();
        System.out.println("IOS Before class-method. Thread id is: " + id);

    }

    public synchronized void afterSuiteTearDown() {
        if(Globals.PLATFORM.IS_ANDROID_DEVICE){
            System.out.println("The test has run on Platform :  " + ThreadVariables.platformValue.get().toUpperCase() + " Number of devices connected :  "
                     +  ThreadVariables.currentAndroidThreadsList.size());
        }
        else if(Globals.PLATFORM.IS_IOS_DEVICE){
            System.out.println("The test has run on Platform : " + ThreadVariables.platformValue.get().toUpperCase() + " Number of devices connected :  "
                    + ThreadVariables.currentIOSThreadsList.size());
        }
    }
}
