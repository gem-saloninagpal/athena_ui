package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendCustomMail {

    @When("^user selects \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userSelectsDetails(String templateName, String recipient) {
        try {
            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.templateName, 120);
            DriverAction.click(SendCustomMail_Locators.templateName);
            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.nameInput, 120);
            DriverAction.typeText(SendCustomMail_Locators.nameInput, templateName);
            DriverAction.waitSec(2);
            DriverAction.click(SendCustomMail_Locators.nameItem);
            DriverAction.waitSec(2);
            DriverAction.click(SendCustomMail_Locators.recipientDD);
            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.recipientInput, 120);
            DriverAction.typeText(SendCustomMail_Locators.recipientInput, recipient);
            DriverAction.waitSec(2);
            DriverAction.click(SendCustomMail_Locators.recipientItem);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Details not entered", "Error occurred: " + e, Status.ERR);
        }
    }

    @And("user clicks on send button")
    public void userClicksOnSendButton() {
        try {
            DriverAction.scrollToBottom();
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(SendCustomMail_Locators.sendBtn);
            DriverAction.waitUntilElementClickable(SendCustomMail_Locators.sendBtn, 120);
            DriverAction.click(SendCustomMail_Locators.sendBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("verify mail is sent")
    public void verifyMailIsSent() {
        try {
//            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.toastMsg, 200);
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if (DriverAction.isDisplayed(SendCustomMail_Locators.toastMsg)) {
                GemTestReporter.addTestStep("Mail sent successfully", "Mail was not sent successfully", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Mail not sent", "Mail was not sent", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Mail not sent", "Error occurred: " + e, Status.ERR);
        }
    }

    @When("^user does not select \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userDoesNotSelectAnd(String templateName, String recipient) {
        try {
            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.templateName, 120);
            DriverAction.waitSec(3);
            DriverAction.click(SendCustomMail_Locators.templateName);
            DriverAction.waitSec(2);
            DriverAction.click(SendCustomMail_Locators.recipientDD);
            DriverAction.click(SendCustomMail_Locators.subject);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Fields not verified", "Error occurred: " + e, Status.ERR);
        }
    }

    @And("user clicks on reset button")
    public void userClicksOnResetButton() {
        try {
            DriverAction.scrollToBottom();
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(SendCustomMail_Locators.resetBtn);
            DriverAction.click(SendCustomMail_Locators.resetBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("verify details are reset")
    public void verifyDetailsAreReset() {
        try {
            DriverAction.waitSec(3);
            if(DriverAction.isDisplayed(SendCustomMail_Locators.blankEditor)) {
                GemTestReporter.addTestStep("Fields successfully reset", "Fields were successfully reset", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Fields not reset", "Fields were not reset", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Fields not reset", "Error occurred: " + e, Status.ERR);
        }
    }

    @When("user clicks on view past mails button")
    public void userClicksOnViewPastMailsButton() {
        try {
            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.viewPastMails, 200);
            DriverAction.click(SendCustomMail_Locators.viewPastMails);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("verify mailing history page is displayed")
    public void verifyMailingHistoryPageIsDisplayed() {
        try {
            if(DriverAction.isDisplayed(SendCustomMail_Locators.mailingHistory)) {
                GemTestReporter.addTestStep("Mailing history page verified", "Mailing history page successfully verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Mailing history page not verified", "Mailing history page not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Mailing history page not verified", "Error occurred: " + e, Status.ERR);
        }
    }

    @And("user clicks on preview button")
    public void userClicksOnPreviewButton() {
        try {
            DriverAction.scrollToBottom();
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(SendCustomMail_Locators.previewBtn);
            DriverAction.click(SendCustomMail_Locators.previewBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "Error occurred: " + e, Status.ERR);
        }
    }

    @When("^user selects \"([^\"]*)\" and \"([^\"]*)\", \"([^\"]*)\"$")
    public void userSelectsAnd(String templateName, String recipient, String recipient2) {
        try {
            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.templateName, 120);
            DriverAction.click(SendCustomMail_Locators.templateName);
            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.nameInput, 120);
            DriverAction.typeText(SendCustomMail_Locators.nameInput, templateName);
            DriverAction.waitSec(2);
            DriverAction.click(SendCustomMail_Locators.nameItem);
            DriverAction.waitSec(2);
            DriverAction.click(SendCustomMail_Locators.recipientDD);
            DriverAction.waitUntilElementAppear(SendCustomMail_Locators.recipientInput, 120);
            DriverAction.typeText(SendCustomMail_Locators.recipientInput, recipient);
            DriverAction.waitSec(2);
            DriverAction.click(SendCustomMail_Locators.recipientItem);
            DriverAction.waitSec(2);
            DriverAction.typeText(SendCustomMail_Locators.recipientInput, recipient2);
            DriverAction.waitSec(2);
            DriverAction.click(SendCustomMail_Locators.recipientItem);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Details not entered", "Error occurred: " + e, Status.ERR);
        }
    }
}
