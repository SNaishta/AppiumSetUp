package utilities.Writers;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesReaderWriter {

    public static synchronized void writeDataToFile(String testDataRowKey, String testDataWriteValue, String csvFilePath){
        List<String> lines = new ArrayList<>();

        Path path = Paths.get(csvFilePath);
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch(Exception e){
            System.out.println(e);
        }

        String[] testDataRowArray;
        String replacementLine = "";

        for(int i = 0; i < lines.size(); i++) {
            try {
                testDataRowArray = lines.get(i).split("=");
            }catch(Exception e) {
                continue;
            }
            if(testDataRowArray[0].equalsIgnoreCase(testDataRowKey)){
                testDataRowArray[1] = testDataWriteValue;
                replacementLine += testDataRowArray[0]+ "=";
                replacementLine += testDataRowArray[1];
                lines.add(i, replacementLine);
                lines.remove(i+1);
            }
        }

        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static synchronized String getProperties(String key, String propertiesFilePath){
        FileInputStream fis;
        Properties propertiesFile = new Properties();

        try {
            fis = new FileInputStream(propertiesFilePath);
            propertiesFile.load(fis);
        }catch(Exception e){
            System.out.println(e);
        }
        return propertiesFile.getProperty(key);
    }

    public static synchronized void writeValueToFile(String key, String value, String propertiesFilePath){
        writeDataToFile(key, value, propertiesFilePath);
    }

}
