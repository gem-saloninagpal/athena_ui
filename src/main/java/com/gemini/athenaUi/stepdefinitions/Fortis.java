package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.FortisLocators;
import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Fortis {
    String test="";
    @And("^Verify question is skipped$")
    public void verifyQuestionSkipped(){
        String text= DriverAction.getAttributeName(FortisLocators.firstQuestion,"class");
        if(text.contains("toAttempt")){
            GemTestReporter.addTestStep("Verify question is skipped","Successfully verified the skipped question.", STATUS.PASS);
        }else{
            GemTestReporter.addTestStep("Verify question is skipped","Could not verify the skipped question.",STATUS.FAIL);
        }

    }

    @Given("Sign in using {string} and {string}")
    public void signIn(String username, String password) {
        try {

            DriverAction.waitUntilElementClickable(MyLocators.usernameField,120);

            DriverAction.typeText(MyLocators.usernameField, username);

            DriverAction.typeText(MyLocators.passwordField, password);
            DriverAction.waitUntilElementClickable(FortisLocators.signInBtn,90);
            DriverAction.click(FortisLocators.signInBtn);

            Thread.sleep(25000);

            //verify dashboard is displayed on login
            if (DriverAction.isExist(MyLocators.dashboard)) {
                GemTestReporter.addTestStep("Verify dashboard is displayed", "Successfully displayed the dashboard.", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            System.out.print("Could not login to Athena.");
        }
    }

    @And("^Start test and validate video$")
    public void startTestValidateVideo() {
        try {
            //  DriverAction.click(By.xpath(MyLocators.testName.replace("name",test)));
            test=DriverAction.getElementText(MyLocators.getTestName);
            DriverAction.click(MyLocators.startTest);
            GemTestReporter.addTestStep("Click the start test button of test", "Start test button is clicked successfully.", STATUS.PASS);

            if (DriverAction.isExist(MyLocators.instructionsVideo)) {
                GemTestReporter.addTestStep("Verify fortis video is displayed", "Successfully displayed the fortis video on clicking start test button.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify instructions video is displayed", "Instructions video is not displayed on clicking start test button.", STATUS.FAIL);
            }
            DriverAction.waitUntilElementIsClickable(FortisLocators.videoNextBtn);
            DriverAction.click(FortisLocators.videoNextBtn);
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on verifying instructions video.");
        }
    }

    @And("^Validate instructions video and click next$")
    public void validateInstructionsVideo() throws InterruptedException {
        try {
            Thread.sleep(2000);
            if (DriverAction.isExist(MyLocators.instructionsVideo)) {
                GemTestReporter.addTestStep("Verify instructions video is displayed", "Successfully displayed the instructions video on clicking start test button.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify instructions video is displayed", "Instructions video is not displayed on clicking start test button.", STATUS.FAIL);
            }
           // DriverAction.waitUntilElementIsClickable(FortisLocators.videoNextBtn);
      //      Thread.sleep(80000);
            DriverAction.click(FortisLocators.videoNextBtn);
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate instructions video and click next","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify the answer got saved$")
    public void verifyAnswerSaved() {
        String text= DriverAction.getAttributeName(FortisLocators.firstQuestion,"class");
        if(text.contains("submitted")){
            GemTestReporter.addTestStep("Verify the answer got saved","Successfully verified the saved answer.", STATUS.PASS);
        }else{
            GemTestReporter.addTestStep("Verify the answer got saved","Could not verify the saved answer.",STATUS.FAIL);
        }
    }

    @And("^Attempt all the MCQ$")
    public void attemptAllMCQ() {
        try {
            int totalQues = DriverAction.getElements(MyLocators.paletteBtn).size();
            String quesNum = DriverAction.getElementText(FortisLocators.quesNumber);
            String[] arr = quesNum.split(" ");
            int num = Integer.parseInt(arr[1]);
            while (num <= totalQues) {
                DriverAction.click(MyLocators.selectOption, "Select an option");
                GemTestReporter.addTestStep("Select an answer", "Successfully selected an answer.", STATUS.PASS);
                DriverAction.click(FortisLocators.SaveNext);
                num++;
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Attempt all MCQs","Exception encountered- "+e,STATUS.ERR);
        }
    }
}
