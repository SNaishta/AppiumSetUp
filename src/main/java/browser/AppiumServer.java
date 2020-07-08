package browser;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import utilities.Constants.*;


public class AppiumServer {

    public AppiumDriverLocalService appiumDriverLocalService;

    public void startAppiumServer(){
        if (!isAppiumServerRunning())
            appiumDriverLocalService = getAppiumDriverLocalService();

        setAppiumPort();
        try {
            appiumDriverLocalService.start();
            appiumDriverLocalService.clearOutPutStreams(); //Removes AppiumServer logs from console
            System.out.println("APPIUM SERVER STARTED ON PORT: "+ ThreadVariables.portNumber.get().toString());
        } catch (AppiumServerHasNotBeenStartedLocallyException e){
            throw new AppiumServerHasNotBeenStartedLocallyException("Local Appium Server failed to start");
        }

    }

    public void setAppiumPort(){
        ThreadVariables.portNumber.set(appiumDriverLocalService.getUrl().getPort());
    }

    public void stopCurrentAppiumNode(){
        String stopAppiumNodeCommand[] = new String[]{"sh", "-c", String.format("lsof -P | grep ':%s' | awk '{print $2}' | xargs kill -9", ThreadVariables.portNumber.get().toString())};
        try {
            Runtime.getRuntime().exec(stopAppiumNodeCommand);
            System.out.println("Appium node server on port "+ ThreadVariables.portNumber.get().toString()+" stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isAppiumServerRunning(){
        return appiumDriverLocalService !=null && appiumDriverLocalService.isRunning();
    }

    public AppiumDriverLocalService getAppiumDriverLocalService() {
        try {
            return appiumDriverLocalService.buildService(
                    new AppiumServiceBuilder()
                            .usingAnyFreePort()
                            .usingDriverExecutable(getDriverExecutable())
                            .withAppiumJS(getAppiumJS())
                            .withIPAddress("127.0.0.1")
                            .withStartUpTimeOut(45, TimeUnit.SECONDS)
                            .withArgument(GeneralServerFlag.LOG_LEVEL, "error") //warn
            );

        }catch (AppiumServerHasNotBeenStartedLocallyException e){
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium Server failed to start " +e.getMessage());
        }
    }

    private File getAppiumJS (){
        return new File(String.valueOf("/usr/local/lib/node_modules/appium/build/lib/main.js"));
    }
    private File getDriverExecutable() {
        return new File(String.valueOf("/usr/local/bin/node"));
    }

}
