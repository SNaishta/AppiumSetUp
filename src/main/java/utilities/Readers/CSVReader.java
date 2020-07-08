package utilities.Readers;

import utilities.Constants.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    private static String getValueMaster(String csvKey, String csvFile) {

        //String csvFile = System.getProperty("user.dir")+"/src/main/resources/testData/TestData.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int columnNumber = 0;

        try {
            br = new BufferedReader(new FileReader(csvFile));
            String deviceName = ThreadVariables.testDataFileName.get();

            // Find the column Name of the deviceThread
            while ((line = br.readLine()) != null) {

                String[] csvValue = line.split(cvsSplitBy);
                if (csvValue[0].equalsIgnoreCase("DEVICE")) {
                    for (int i = 1; i < csvValue.length; i++) {
                        if (csvValue[i].equalsIgnoreCase(deviceName)) {
                            columnNumber = i;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] csvValue = line.split(cvsSplitBy);
                if (csvValue[0].equalsIgnoreCase(csvKey)) {
                    System.out.println("THE VALUE FOR " + csvKey + " is: " + csvValue[columnNumber]);
                    return csvValue[columnNumber];
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getPlatformValue(String csvKey){

        return getValueMaster(csvKey, CSVs.PLATFORM_DATA_CSV_PATH);
    }


    public static String getValue(String csvKey){

        return getValueMaster(csvKey, CSVs.TEST_DATA_CSV_PATH);
    }

}

