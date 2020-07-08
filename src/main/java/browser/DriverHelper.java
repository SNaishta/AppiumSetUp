package browser;

import io.appium.java_client.AppiumDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DriverHelper {

    public static AppiumDriver driver;

    public DriverHelper() {
        this.driver = AppiumDriverClass.getAppiumDriver();
    }

    public String runTerminalCommand(String command) {
        Process proc = null;
        String terminalResponse = "";
        try {
            proc = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream())); // Read the output
            String line = "";
            while ((line = reader.readLine()) != null) {
                //System.out.print(line + "\n");
                terminalResponse = line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return terminalResponse;
    }

    public List<String> runTerminalMultiLineReturnCommand(String command) {
        Process proc = null;
        List<String> terminalResponse = new ArrayList<String>();
        try {
            proc = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream())); // Read the output
            String line = "";
            while ((line = reader.readLine()) != null) {
                terminalResponse.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return terminalResponse;
    }

    public void test() {
        System.out.println(" ************* 123 *************");
    }
}
