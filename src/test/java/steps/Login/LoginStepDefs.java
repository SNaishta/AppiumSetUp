package steps.Login;

import cucumber.api.java.en.Given;
import pages.actions.Login.LoginPage;

public class LoginStepDefs {

    LoginPage loginPage;

    public LoginStepDefs(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Given("^application is launched$")
    public void applicationIsLaunched() {
        loginPage.checkApplicationIsLaunched();
    }
}
