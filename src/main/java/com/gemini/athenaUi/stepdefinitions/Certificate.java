package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaAPI.utils.CommonUtils;
import com.gemini.athenaUi.locators.Course_Locators;
import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import static com.gemini.athenaUi.stepdefinitions.UserDashboard._CourseName;
import static com.gemini.athenaUi.stepdefinitions.UserDashboard.generateRandomCourseName;

public class Certificate {
    String additionalComment;
    @And("^Enter additional comment for certificate$")
    public void addAdditionalComment(){
        try{
            DriverAction.clearText(Course_Locators.additionalCommentField);
            additionalComment=generateRandomCourseName(5);
            DriverAction.typeText(Course_Locators.additionalCommentField,additionalComment);
        }catch(Exception e){
            GemTestReporter.addTestStep("Enter additional comment for certificate","Exception encountered- "+e, Status.ERR);
        }

    }

    @And("^Click actions icon of a completed course$")
    public void completedCourseActionIcon() throws InterruptedException {
        Thread.sleep(2000);
        DriverAction.waitUntilElementIsClickable(Course_Locators.completedCourseActionIcon);
        DriverAction.click(Course_Locators.completedCourseActionIcon);
    }

    @Then("^Verify the additional comment$")
    public void verifyTheAdditionalComment() {
        try{
            Thread.sleep(7000);
            String comment=DriverAction.getElementText(Course_Locators.certificateComment);
            if(comment.contains(additionalComment)){
                GemTestReporter.addTestStep("Verify the additional comment","Successfully verified the additional comment",Status.PASS);
            }else{
                GemTestReporter.addTestStep("Verify the additional comment","Could not verify the additional comment",Status.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the additional comment","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("Select the option {string}")
    public void selectTheOption(String option) {
        try{
            Thread.sleep(2000);
            DriverAction.click(By.xpath(Course_Locators.viewDownloadCertificate.replace("input",option)),"Select the option- "+option,"Successfully selected- "+option);
        }catch(Exception e){
            GemTestReporter.addTestStep("Select the option- "+option,"Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify comment is not present$")
    public void verifyCommentIsNotPresent() {
        try{
            String comment=DriverAction.getElementText(Course_Locators.certificateComment);
            if(comment.equals("")){
                GemTestReporter.addTestStep("Verify comment is not present","Successfully verified comment is not present.",Status.PASS);
            }else{
                GemTestReporter.addTestStep("Verify comment is not present","Could not verify comment is not present.",Status.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify comment is not present","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());

        }
    }

    @And("^Enter additional comment with exceeded limit$")
    public void enterAdditionalCommentWithExceededLimit() {
        try{
            DriverAction.clearText(Course_Locators.additionalCommentField);
            additionalComment=generateRandomCourseName(80);
            DriverAction.typeText(Course_Locators.additionalCommentField,additionalComment);
        }catch(Exception e){
            GemTestReporter.addTestStep("Enter additional comment with exceeded limit for certificate","Exception encountered- "+e, Status.ERR);
        }
    }

    @Then("Verify error message {string}")
    public void verifyErrorMessage(String error) {
        try{
            String message=DriverAction.getElementText(Course_Locators.certificateMessage);
            if(message.contains(error)){
                GemTestReporter.addTestStep("Verify error message","Successfully verified the error message",Status.PASS);
            }else{
                GemTestReporter.addTestStep("Verify error message","Could not verify the error message",Status.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify error message","Exception encountered- "+e,Status.ERR);
        }
    }
}
