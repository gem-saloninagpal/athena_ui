package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.qa.athenaUi.locators.ContactUsLocators;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUsForm_StepDef {
    @Given("^user clicks on Contact Us tab$")
    public void userClicksOnContactUsTab() throws InterruptedException {
        Thread.sleep(2000);
        DriverAction.click(ContactUsLocators.contactUsTab);
    }

    @When("^user enters required details$")
    public void userEntersRequiredDetails() throws InterruptedException {
        Thread.sleep(2000);
        DriverAction.typeText(ContactUsLocators.name, "abc");
        DriverAction.typeText(ContactUsLocators.email, "abc123@gmail.com");
        DriverAction.typeText(ContactUsLocators.contact, "9998887776");
        DriverAction.typeText(ContactUsLocators.desc, "ejbejsbe");
    }

    @When("^user clicks on submit button$")
    public void userClicksOnSubmitButton() throws InterruptedException {
        Thread.sleep(2000);
        DriverAction.click(ContactUsLocators.submitBtn);
    }

    @Then("^validate form is submitted$")
    public void validateFormIsSubmitted() throws InterruptedException {
        Thread.sleep(2000);
        DriverAction.isExist(ContactUsLocators.logo);
    }

    @Given("^Navigate to website$")
    public void navigateToWebsite() {
        DriverAction.navigateToUrl("https://dev-athena.geminisolutions.com/");
    }

    @Then("^verify Submit button is disabled$")
    public void verifySubmitButtonIsDisabled() throws InterruptedException {
        try {
            DriverAction.waitSec(2);
            if(DriverAction.isExist(ContactUsLocators.submit_disabled)) {
                GemTestReporter.addTestStep("submit button disabled", "submit button is disabled due to incomplete/incorrect details", Status.PASS);
            } else {
                GemTestReporter.addTestStep("submit button not disabled", "submit button is not disabled", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("submit button not disabled", "submit button is not disabled", Status.ERR);
        }
    }

    @When("user enters invalid name")
    public void userEntersInvalidName() throws InterruptedException {
        Thread.sleep(2000);
        DriverAction.typeText(ContactUsLocators.name, "3490348");
        DriverAction.typeText(ContactUsLocators.email, "abc123@gmail.com");
        DriverAction.typeText(ContactUsLocators.contact, "9998887776");
        DriverAction.typeText(ContactUsLocators.desc, "ejbejsbe");
    }

    @When("user enters invalid email")
    public void userEntersInvalidEmail() throws InterruptedException {
        Thread.sleep(2000);
        DriverAction.typeText(ContactUsLocators.name, "abc");
        DriverAction.typeText(ContactUsLocators.email, "abc1");
        DriverAction.typeText(ContactUsLocators.contact, "9998887776");
        DriverAction.typeText(ContactUsLocators.desc, "ejbejsbe");
    }

    @When("user enters invalid contact number")
    public void userEntersInvalidContactNumber() throws InterruptedException {
        Thread.sleep(2000);
        DriverAction.typeText(ContactUsLocators.name, "abc");
        DriverAction.typeText(ContactUsLocators.email, "abc@gmail.com");
        DriverAction.typeText(ContactUsLocators.contact, "abc");
        DriverAction.typeText(ContactUsLocators.desc, "ejbejsbe");
    }

    @Then("verify form is submitted")
    public void verifyFormIsSubmitted() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader,150);
            if(DriverAction.isDisplayed(ContactUsLocators.toast_msg)) {
                GemTestReporter.addTestStep("Form submitted", "Form submitted successfully", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Form not submitted", "Form not submitted", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Form not submitted", "Form not submitted", Status.ERR);
        }
    }
}
