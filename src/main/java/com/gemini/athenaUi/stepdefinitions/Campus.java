package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.CampusLocators;
import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.gemini.athenaUi.stepdefinitions.CandidateModule_UserManagement.*;

public class Campus {

    String _tpoEmail = "";

    @Then("^Enter respective values in campus fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void campusValues(String location, String university, String tpoName, String tpoEmail, String tpoContact, String description) {
        try {
            //enter the details in fields while registering a campus
            generateUniqueEmail();
            tpoEmail = "a" + _randomString;
            List<WebElement> campusFields = DriverAction.getElements(MyLocators.inputFields);
            //here randomString is campus mail
            String[] campusValues = {_name, _randomString, location, university, tpoName, tpoEmail, tpoContact, description};

            for (int i = 0; i < campusFields.size(); i++) {
                campusFields.get(i).clear();
                campusFields.get(i).sendKeys(campusValues[i]);
                GemTestReporter.addTestStep("Enter " + campusValues[i] + "in " + campusFields.get(i), "Successfully entered the value.", STATUS.PASS);
            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in input fields", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Search a campus$")
    public void searchCampus() {
        try {
            Thread.sleep(3000);
            DriverAction.typeText(MyLocators.searchbox, _name);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Search a campus", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Select actions icon of campus$")
    public void selectActionsOfCampus() {
        try {
            DriverAction.click(CampusLocators.actionsIcon);
            GemTestReporter.addTestStep("Select action icon of campus", "Successfully selected the actions icon of campus", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Select actions icon of campus", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Verify campus is registered$")
    public void verifyCampusRegistered() {
        try {
            String campus = DriverAction.getElementText(CampusLocators.registeredCampus);
            if (campus.contains(_name)) {
                GemTestReporter.addTestStep("Verify campus is registered", "Successfully registered the campus", STATUS.PASS,DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify campus is registered", "Could not verify the registered campus", STATUS.FAIL,DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify campus is registered", "Exception encountered- " + e, STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify campus is updated \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyCampusUpdated(String tpoName, String tpoContact, String location, String university) {
        try {
            String[] campusValues = {_name, "", tpoName, tpoContact, _tpoEmail, location, university};
            List<WebElement> row = DriverAction.getElements(CampusLocators.rowLength);
            int c = 0;

            //comparing updated fields with the values passed
            for (int i = 0; i < row.size()-1; i++) {
                String value = row.get(i).getText();
                if (value.contains(campusValues[i])) {
                    c++;
                }
            }
            //verifies if the count matches
            if (c == row.size() - 1) {
                GemTestReporter.addTestStep("Verify campus is updated", "Successfully verified the campus is updated.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify campus is updated", "Could not verify the campus is updated.", STATUS.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify campus is updated","Exception encountered- "+e,STATUS.ERR);
        }
    }
}