package steps;

import browser.AppiumDriverClass;
import browser.AppiumServer;
import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class BeforeActions {

	AppiumServer appiumServer = new AppiumServer();

	@Before
	public void setUp(Scenario scenario) {
		System.out.println("The scenario name of the current test is: " + scenario.getName());

        appiumServer.startAppiumServer();
        AppiumDriverClass.setUpAppiumDriver();

	}
	
}
