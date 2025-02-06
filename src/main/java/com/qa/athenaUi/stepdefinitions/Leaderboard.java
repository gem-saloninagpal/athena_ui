package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.ArchiveCourseLocators;
import com.qa.athenaUi.locators.Leaderboard_Locators;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Leaderboard {
    @Given("User is on Courses page")
    public void userIsOnCoursesPage() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if(DriverAction.isDisplayed(Leaderboard_Locators.courses_screen)) {
                GemTestReporter.addTestStep("Screen verified", "Courses screen verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Screen not verified", "Failed to verify courses screen", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Screen not verified", "Failed to verify courses screen" + e, Status.ERR);
        }
    }

    @When("user clicks on arrow icon")
    public void userClicksOnArrowIcon() {
        try {
            DriverAction.click(Leaderboard_Locators.arrow_icon);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Icon not displayed", "Failed to click on arrow icon" + e, Status.ERR);
        }
    }

    @Then("verify leaderboard is being displayed")
    public void verifyLeaderboardIsBeingDisplayed() {
        try {
            if (DriverAction.isDisplayed(Leaderboard_Locators.leaderboard_heading)) {
                GemTestReporter.addTestStep("Leaderboard displayed", "Leaderboard verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Leaderboard not displayed", "Leaderboard was not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Leaderboard not displayed", "Leaderboard was not verified" + e, Status.ERR);
        }
    }

    @Then("verify leaderboard is collapsed")
    public void verifyLeaderboardIsCollapsed() {
        try {
            if(DriverAction.isDisplayed(Leaderboard_Locators.leaderboard_collapsed)) {
                GemTestReporter.addTestStep("Leaderboard collapsed", "Leaderboard collapse was verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Leaderboard not collapsed", "Leaderboard collapse was not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Leaderboard not collapsed", "Leaderboard collapse was not verified" + e, Status.ERR);
        }
    }

    @Then("verify ten entries are displayed")
    public void verifyTenEntriesAreDisplayed() {
        try {
            List<WebElement> leaderboard_items = DriverAction.getElements(Leaderboard_Locators.leaderboard_items);
            if(leaderboard_items.size() == 10) {
                GemTestReporter.addTestStep("Leaderboard entries verified", "Leaderboard entries were verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Leaderboard entries not verified", "Leaderboard entries were not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Leaderboard entries not verified", "Leaderboard entries were not verified" + e, Status.ERR);
        }
    }

    @Then("verify top user has highest score")
    public void verifyTopUserHasHighestScore() {
        try {
            DriverAction.waitSec(3);
            String score1 = DriverAction.getElementText(Leaderboard_Locators.score1);
            System.out.println(score1);
            String score2 = DriverAction.getElementText(Leaderboard_Locators.score2);
            int score1_int = Integer.parseInt(score1);
            System.out.println(score1);
            int score2_int = Integer.parseInt(score2);
            System.out.println(score2);
            if(score1_int>=score2_int) {
                GemTestReporter.addTestStep("Leaderboard entries verified", "Leaderboard entries were verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Leaderboard entries not verified", "Leaderboard entries were not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Leaderboard entries not verified", "Leaderboard entries were not verified" + e, Status.ERR);
        }
    }
}
