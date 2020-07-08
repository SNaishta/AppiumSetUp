package steps;

import browser.AppiumDriverClass;
import browser.AppiumServer;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class AfterActions {

	@After
	public void tearDown(Scenario scenario) {
		System.out.println(scenario.getName() + " --- > Completed execution");

    	 AppiumDriverClass.tearDownAppium();
    	 new AppiumServer().stopCurrentAppiumNode();
    }

}
