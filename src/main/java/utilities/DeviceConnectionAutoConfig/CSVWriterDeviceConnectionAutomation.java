package utilities.DeviceConnectionAutoConfig;

import utilities.Writers.PropertiesReaderWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utilities.Constants.*;


public class CSVWriterDeviceConnectionAutomation {

    public void writeDataToFile(String testDataRowKey, String testDataWriteValue, String deviceName, String csvFilePath) {

        if(testDataRowKey.equalsIgnoreCase("DEVICENAME")){
            testDataWriteValue = PropertiesReaderWriter.getProperties(testDataWriteValue, CSVs.DEVICE_NAME_PROPERTIES_PATH);
        }

        List<String> lines = new ArrayList<>();

        Path path = Paths.get(csvFilePath);
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
        }

        int columnNumber = -1;

        String[] deviceRowArray = lines.get(0).split(",");

        for (int j = 1; j < deviceRowArray.length; j++) {
            if (deviceRowArray[j].equalsIgnoreCase(deviceName)) {
                columnNumber = j;
            }
        }

        System.out.println("THE COLUMN OF THE DEVICE IS: --------------> " + columnNumber);

        lines = linesFormatting(lines, testDataRowKey, columnNumber, testDataWriteValue);

        writeChangesToFile(lines, path);
    }

    public void ammendTestNGFile(int deviceCount, String csvFilePath) {
        List<String> lines = new ArrayList<>();

        Path path = Paths.get(csvFilePath);
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 0; i < lines.size(); i++) {

            String suiteLine = lines.get(i);
            if (suiteLine.contains("<suit")) {
                int cutOffIndex = suiteLine.indexOf("thread", 0);
                String replacementSuitLine = suiteLine.substring(0, cutOffIndex);
                replacementSuitLine += "thread-count=\"" + deviceCount + "\">";
                System.out.println(replacementSuitLine);
                lines.add(i, replacementSuitLine);
                lines.remove(i + 1);
            }
        }

        boolean classTagDeleted = false;
        while (!classTagDeleted) {
            for (int i = 0; i < lines.size(); i++) {
                String classLineTag = lines.get(i);
                if (classLineTag.contains("<class name")) {
                    lines.remove(i);
                }
                classTagDeleted = true;
            }
            for (int i = 0; i < lines.size(); i++) {
                String classLineTag = lines.get(i);
                if (classLineTag.contains("<class name")) {
                    classTagDeleted = false;
                }
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            String classesName = lines.get(i);
            if (classesName.contains("<classes")) {
                for (int j = 0; j < deviceCount; j++) {
                    if (DeviceConnectionAutomation.POM_PLATFORM_VALUE.equalsIgnoreCase(Android.ANDROID_PLATFORM_NAME)) {
                        lines.add((i + 1 + j), "      <class name=\"runner.android.PositiveTestsAndroid" + (j + 1) + "\"/>");
                    } else if (DeviceConnectionAutomation.POM_PLATFORM_VALUE.equalsIgnoreCase(Ios.IOS_PLATFORM_NAME)) {
                        lines.add((i + 1 + j), "      <class name=\"runner.iOS.PositiveTestsiOS" + (j + 1) + "\"/>");
                    }
                }
            }
        }

        writeChangesToFile(lines, path);
    }

    public synchronized void writeValueToFile(String key, String value, String deviceName) {
        writeDataToFile(key, value, deviceName, CSVs.PLATFORM_DATA_CSV_PATH);
    }

    public void modifyTestNGFile(int deviceCount) {
        if (DeviceConnectionAutomation.POM_PLATFORM_VALUE.equalsIgnoreCase(Android.ANDROID_PLATFORM_NAME)) {
            ammendTestNGFile(deviceCount, CSVs.ANDROID_TESTNG_XML_FILE_PATH);
        } else if (DeviceConnectionAutomation.POM_PLATFORM_VALUE.equalsIgnoreCase(Ios.IOS_PLATFORM_NAME)) {
            ammendTestNGFile(deviceCount, CSVs.IOS_TESTNG_XML_FILE_PATH);
        }
    }

    public List<String> linesFormatting(List<String> lines, String testDataRowKey,
                                        int columnNumber, String testDataWriteValue) {
        for (int j = 0; j < lines.size(); j++) {

            String[] testDataRowArray = lines.get(j).split(",");
            if (testDataRowArray[0].equalsIgnoreCase(testDataRowKey)) {
                testDataRowArray[columnNumber] = testDataWriteValue;
                String replacementLine = Arrays.toString(testDataRowArray);
                replacementLine = replacementLine.replace("[", "");
                replacementLine = replacementLine.replace("]", "");
                replacementLine = replacementLine.replace(" ", "");
                System.out.println("THE REPLACMENT LINE OF THE ARRAY IS: -------------> " + replacementLine);
                lines.add(j, replacementLine);
                lines.remove(j + 1);
            }
        }
        return lines;
    }

    public void writeChangesToFile(List<String> lines, Path path) {

        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
