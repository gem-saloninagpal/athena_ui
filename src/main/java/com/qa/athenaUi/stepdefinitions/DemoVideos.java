package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.DemoVideos_Locators;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DemoVideos {
    @When("User clicks on Demo Video")
    public void userClicksOnDemoVideo() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            DriverAction.waitSec(2);
            DriverAction.click(DemoVideos_Locators.video);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Video not displayed", "Video not displayed. Exception : " + e, Status.ERR);
        }
    }

    @Then("verify video is played")
    public void verifyVideoIsPlayed() {
        try {
            if(DriverAction.isDisplayed(DemoVideos_Locators.videoContainer)) {
                GemTestReporter.addTestStep("Video displayed", "Video displayed", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Video not displayed", "Video not displayed.", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Video not displayed", "Video not displayed. Exception : " + e, Status.ERR);
        }
    }
}
