package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.TestUtil;
import webConnector.WebConnector;

public class StepDefinition {

    @Before
    public void before(Scenario scenario) {
        WebConnector.setUpDriver();
    }

    @After
    public void after(Scenario scenario) {
        WebConnector.closeDriver(scenario);
    }

    @Given("User is on landing page")
    public void landing_page() {
        TestUtil.initialization();
    }

    @When("User hover row to get and click on pencil icon")
    public void userHoversAndClick() {
        TestUtil.hoverClick();
    }

    @When("User enter new value as {string} and click on check icon")
    public void userUpdatesBasePrice(String value) {
        TestUtil.enterValue(value);
    }

    @When("User click on label and add new label and value and submit")
    public void userAddsNewData(io.cucumber.datatable.DataTable dataTable) {
        TestUtil.addingNewValues(dataTable);
    }

    @When("User deletes {string} and displayed sum shows correct sum")
    public void userDeletesEntry(String string) {
        TestUtil.deleteEntryAndVerify(string);
    }

    @When("User edit {string} to change to new label {string} and checks the warning message as {string} and label remains same")
    public void userEditLabelWithWarning(String label, String newLabel, String warning) {
        TestUtil.editEntryAndVerifyWarning(label, newLabel, warning);
    }

    @When("User edit {string} to change to new value {string} and checks the warning message as {string} and label remains same")
    public void userEditValueWithWarning(String label, String newValue, String warning) {
        TestUtil.editEntryValueAndVerifyWarning(label, newValue, warning);
    }

    @When("User edit {string} to change to new value {string} and displayed sum shows correct sum")
    public void userEditValue(String label, String newValue) {
        TestUtil.editEntryValueAndVerifySum(label,newValue);
    }

    @Then("Displayed values are shown as expected")
    public void verifyingValues() {
        TestUtil.verifyEnteredValues();
    }

    @Then("Verify display shows correct base price as {string}")
    public void verifySum(String value) {
        TestUtil.verifyBasePrice(value);
    }
}
