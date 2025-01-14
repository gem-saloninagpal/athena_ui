package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import com.qa.athenaUi.locators.ShareFeedback_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.swing.*;

public class ShareFeedback {
    @Given("user clicks on feedback icon")
    public void userClicksOnFeedbackIcon() {
        try {
            DriverAction.click(ShareFeedback_Locators.feedbackIcon);
            if(DriverAction.isDisplayed(ShareFeedback_Locators.feedbackContainer)) {
                GemTestReporter.addTestStep("Feedback icon clicked", "Feedback icon was clicked.", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Feedback icon not clicked", "Feedback icon was not clicked", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Feedback icon not clicked", "Feedback icon was not clicked. Exception : " + e, Status.ERR);
        }
    }

    @When("^User enters feedback details \"([^\"]*)\", \"([^\"]*)\"$")
    public void userEntersFeedbackDetails(String subject, String description) {
        try {
            DriverAction.typeText(ShareFeedback_Locators.feedbackSubject, subject);
            DriverAction.click(ShareFeedback_Locators.categoryDD);
            DriverAction.waitSec(2);
            DriverAction.click(ShareFeedback_Locators.selectCategory);
            DriverAction.waitSec(1);
            DriverAction.click(ShareFeedback_Locators.featuresDD);
            DriverAction.waitSec(2);
            DriverAction.click(ShareFeedback_Locators.selectFeature);
            DriverAction.typeText(ShareFeedback_Locators.feedbackDescription, description);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Details not added", "Feedback details not added. Exception : " + e, Status.ERR);
        }
    }

    @And("Click on submit button")
    public void clickOnSubmitButton() {
        try {
            DriverAction.click(ShareFeedback_Locators.submitBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "Button was not clicked. Exception : " + e, Status.ERR);
        }
    }

    @Then("verify feedback is submitted")
    public void verifyFeedbackIsSubmitted() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if(DriverAction.isDisplayed(ShareFeedback_Locators.successMsg)) {
                GemTestReporter.addTestStep("Feedback submitted", "Feedback was submitted successfully", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Feedback not submitted", "Feedback was not submitted.", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Feedback not submitted", "Feedback was not submitted. Exception : " + e, Status.ERR);
        }
    }

    @Then("verify submit button remains disabled")
    public void verifySubmitButtonRemainsDisabled() {
        try {
            if(DriverAction.isDisplayed(ShareFeedback_Locators.disabledSubmitBtn)) {
                GemTestReporter.addTestStep("Fields verified", "Mandatory fields were verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Fields not verified", "Mandatory fields were not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button was not disabled", "Button was not disabled. Exception : " + e, Status.ERR);
        }
    }

    @And("^User uploads screenshot \"([^\"]*)\"$")
    public void userUploadsScreenshot(String filePath) {
        try {
            DriverAction.fileUpload(ShareFeedback_Locators.chooseSS, filePath);
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            DriverAction.waitSec(2);
            DriverAction.click(ShareFeedback_Locators.uploadSS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Screenshot not uploaded", "Screenshot was not uploaded. Exception : " + e, Status.ERR);
        }
    }
}
