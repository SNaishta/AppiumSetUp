package utilities.Constants;

import io.appium.java_client.AppiumDriver;
import utilities.Readers.CSVReader;

import java.util.ArrayList;
import java.util.List;

public interface ThreadVariables {

    ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>(); //Initialise Android/iOS driver
    ThreadLocal<Integer> portNumber = new ThreadLocal<>();
    ThreadLocal<String> platformValue = new ThreadLocal<>();
    ThreadLocal<String> testDataFileName = new ThreadLocal<>();
    ThreadLocal<CSVReader> csvValue = new ThreadLocal<>();
    ThreadLocal<Long> id = new ThreadLocal<>();
    List<Long> currentAndroidThreadsList = new ArrayList<>();
    List<Long> currentIOSThreadsList = new ArrayList<>();
}


