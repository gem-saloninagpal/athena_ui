package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.FeedbackLocators;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Feedback {
    @When("Click on view icon")
    public void clickOnViewIcon() {
        try {
            DriverAction.waitUntilElementIsClickable(FeedbackLocators.viewIcon);
            DriverAction.click(FeedbackLocators.viewIcon);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "view button not clicked", Status.ERR);
        }
    }

    @Then("verify feedback is visible")
    public void verifyFeedbackIsVisible() {
        DriverAction.waitUntilElementAppear(FeedbackLocators.feedbackPage, 200);
        String pageHeading = DriverAction.getElementText(FeedbackLocators.feedbackPage);
        if(pageHeading.equals("View Bug / Feedback")) {
            GemTestReporter.addTestStep("feedback viewed", "view feedback functionality working", Status.PASS);
        } else {
            GemTestReporter.addTestStep("feedback not viewed", "view feedback functionality not working", Status.FAIL);
        }
    }

    @When("Click on delete icon")
    public void clickOnDeleteIcon() {
        try {
            DriverAction.waitUntilElementIsClickable(FeedbackLocators.deleteIcon);
            DriverAction.click(FeedbackLocators.deleteIcon);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "delete button not clicked", Status.ERR);
        }
    }

    @Then("verify feedback is deleted")
    public void verifyFeedbackIsDeleted() {
        try {
            DriverAction.waitSec(2);
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if(DriverAction.isDisplayed(FeedbackLocators.deletedMsg)) {
                GemTestReporter.addTestStep("feedback deleted", "feedback successfully deleted", Status.PASS);
            } else {
                GemTestReporter.addTestStep("feedback not deleted", "feedback not deleted", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("feedback not deleted", "feedback not deleted", Status.ERR);
        }
    }

    @And("Update the status")
    public void updateTheStatus() {
        try {
            DriverAction.click(FeedbackLocators.editIcon);
            DriverAction.waitUntilElementIsClickable(FeedbackLocators.statusDropdown);
            DriverAction.click(FeedbackLocators.statusDropdown);
            DriverAction.click(FeedbackLocators.closedStatus);
            DriverAction.waitSec(2);
            if(DriverAction.isDisplayed(FeedbackLocators.saveBtn)) {
                DriverAction.click(FeedbackLocators.saveBtn);
                GemTestReporter.addTestStep("Save button clicked", "Save button was clicked successfully", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Save button not clicked", "Save button was not clicked", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Save button not clicked", "Save button was not clicked", Status.ERR);
        }
    }

    @Then("verify status is updated")
    public void verifyStatusIsUpdated() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            if(DriverAction.isDisplayed(FeedbackLocators.successMsg)) {
                GemTestReporter.addTestStep("Status updated", "Status was updated successfully", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Status not updated", "Status was not updated", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Status not updated", "Status was not updated", Status.ERR);
        }
    }
}
