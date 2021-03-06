package runner.android;

import browser.Setup;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps")

public class PositiveTestsAndroid1 extends AbstractTestNGCucumberTests {

    @BeforeClass
    public static void setup() {
        new Setup().setupAndroid();
    }

    @AfterClass
    public void afterSuiteTearDown(){ // After testSuite has finished running
        new Setup().afterSuiteTearDown();
    }

}


