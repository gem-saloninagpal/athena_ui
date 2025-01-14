package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.ResetPassword_Locators;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ResetPassword {
    @Given("user clicks on forgot password")
    public void userClicksOnForgotPassword() {
        try {
            DriverAction.click(ResetPassword_Locators.forgotPassword);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not clicked", "Button not clicked. Exception: " + e, Status.ERR);
        }
    }

    @When("user enters email id")
    public void userEntersEmailId() {
        try {
            DriverAction.typeText(ResetPassword_Locators.emailInput, "learner2@gmail.com");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not clicked", "Button not clicked. Exception: " + e, Status.ERR);
        }
    }

    @And("user clicks on RESET button")
    public void userClicksOnRESETButton() {
        try {
            DriverAction.click(ResetPassword_Locators.resetBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not clicked", "Button not clicked. Exception: " + e, Status.ERR);
        }
    }

    @Then("verify reset password mail is sent")
    public void verifyResetPasswordMailIsSent() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if(DriverAction.isDisplayed(ResetPassword_Locators.successMsg)) {
                GemTestReporter.addTestStep("Mail sent", "Reset password mail sent successfully", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Mail not sent", "Reset password mail was not sent", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not reset", "Reset password email not sent. Exception: " + e, Status.ERR);
        }
    }

    @When("^user enters invalid email id \"([^\"]*)\"$")
    public void userEntersInvalidEmailId(String email) {
        try {
            DriverAction.typeText(ResetPassword_Locators.emailInput, email);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not verified", "Error not verified. Exception: " + e, Status.ERR);
        }
    }

    @Then("verify enter valid email error appears")
    public void verifyEnterValidEmailErrorAppears() {
        try {
            if(DriverAction.isDisplayed(ResetPassword_Locators.errorMsg)) {
                GemTestReporter.addTestStep("Verified", "Error verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Not verified", "Error not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not verified", "Error not verified. Exception: " + e, Status.ERR);
        }
    }

    @Then("verify user not found error appears")
    public void verifyUserNotFoundErrorAppears() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if(DriverAction.isDisplayed(ResetPassword_Locators.userNotFoundMsg)) {
                GemTestReporter.addTestStep("Verified", "Error verified", Status.PASS);
            }else {
                GemTestReporter.addTestStep("Not verified", "Error not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not verified", "Error not verified. Exception: " + e, Status.ERR);
        }
    }

    @Then("verify required error appears")
    public void verifyRequiredErrorAppears() {
        try {
            if(DriverAction.isDisplayed(ResetPassword_Locators.error)) {
                GemTestReporter.addTestStep("Verified", "Error verified", Status.PASS);
            }else {
                GemTestReporter.addTestStep("Not verified", "Error not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not verified", "Error not verified. Exception: " + e, Status.ERR);
        }
    }
}
