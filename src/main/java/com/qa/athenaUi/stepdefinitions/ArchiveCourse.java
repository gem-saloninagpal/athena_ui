package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.ArchiveCourseLocators;
import com.qa.athenaUi.locators.Course_Locators;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import com.qa.athenaUi.locators.Tests_TestControl_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qa.athenaUi.stepdefinitions.Course.generateRandomCourseName;

public class ArchiveCourse {
    @When("User selects Archive course option")
    public void userSelectsArchiveCourseOption() {
        try {
            DriverAction.waitUntilElementIsClickable(ArchiveCourseLocators.actionsIcon);
            DriverAction.click(ArchiveCourseLocators.actionsIcon);
            DriverAction.waitSec(2);
            DriverAction.click(ArchiveCourseLocators.archiveCourseOption);
        } catch (Exception e) {
            GemTestReporter.addTestStep("archive course not clicked", "archive course option was not clicked", Status.ERR);
        }
    }

    @Then("verify course is archived")
    public void verifyCourseIsArchived() {
        try {
             DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
             if(DriverAction.isDisplayed(ArchiveCourseLocators.archivedMsg)) {
                 GemTestReporter.addTestStep("course archived", "course was successfully archived", Status.PASS);
             } else {
                 GemTestReporter.addTestStep("course not archived", "course was not archived", Status.FAIL);
             }
        } catch (Exception e) {
            GemTestReporter.addTestStep("course not archived", "course was not archived", Status.ERR);
        }
    }

    @And("^user enters archived course name$")
    public void userEntersArchivedCourseName() {
        try {
            DriverAction.waitUntilElementAppear(ArchiveCourseLocators.archivedName, 150);
            String archiveName = generateRandomCourseName(10);
            DriverAction.typeText(ArchiveCourseLocators.archivedName, archiveName + "_archive");
        } catch (Exception e) {
            GemTestReporter.addTestStep("course name not entered", "course name was not entered", Status.ERR);
        }
    }

    @When("User clicks on archived courses tab")
    public void userClicksOnArchivedCoursesTab() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            DriverAction.waitUntilElementIsClickable(ArchiveCourseLocators.archivedCoursesTab);
            DriverAction.click(ArchiveCourseLocators.archivedCoursesTab);
        } catch (Exception e) {
            GemTestReporter.addTestStep("tab not clicked", "archived courses tab not clicked", Status.ERR);
        }
    }

    @And("user selects course summary option")
    public void userSelectsCourseSummaryOption() {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(ArchiveCourseLocators.courseSummaryOption);
        } catch (Exception e) {
            GemTestReporter.addTestStep("course summary not clicked", "course summary option not clicked. Exception : " + e, Status.ERR);
        }
    }

    @Then("verify course summary is displayed")
    public void verifyCourseSummaryIsDisplayed() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            if(DriverAction.isDisplayed(ArchiveCourseLocators.courseSummary)) {
                GemTestReporter.addTestStep("course summary verified", "course summary verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("course summary not verified", "course summary not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("course summary not verified", "course summary not verified. Exception : " + e, Status.ERR);
        }
    }

    @And("user clicks on actions icon")
    public void userClicksOnActionsIcon() {
        try {
//            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            DriverAction.waitSec(8);
            if(DriverAction.isDisplayed(ArchiveCourseLocators.actionsIconArchived)) {
                GemTestReporter.addTestStep("actions icon clicked", "actions icon was clicked", Status.PASS);
                DriverAction.click(ArchiveCourseLocators.actionsIconArchived);
            } else {
                GemTestReporter.addTestStep("actions icon not clicked", "actions icon was not clicked", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("actions icon not clicked", "actions icon was not clicked. Exception : " + e, Status.ERR);
        }
    }

    @And("user selects learner reports option")
    public void userSelectsLearnerReportsOption() {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(ArchiveCourseLocators.learnerReportsOption);
        } catch (Exception e) {
            GemTestReporter.addTestStep("course summary not clicked", "course summary option not clicked. Exception : " + e, Status.ERR);
        }
    }

    @Then("verify learner reports are displayed")
    public void verifyLearnerReportsAreDisplayed() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader,200);
            if(DriverAction.isDisplayed(ArchiveCourseLocators.learnerReports)) {
                GemTestReporter.addTestStep("learner reports verified", "learner reports verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("learner reports not verified", "learner reports not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("learner reports not verified", "learner reports not verified. Exception : " + e, Status.ERR);
        }
    }

    @When("user clicks on actions icon of course")
    public void userClicksOnActionsIconOfCourse() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.actions)) {
                GemTestReporter.addTestStep("actions icon clicked", "actions icon was clicked", Status.PASS);
                DriverAction.click(Tests_TestControl_Locators.actions);
            } else {
                GemTestReporter.addTestStep("actions icon not clicked", "actions icon was not clicked", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("actions icon not clicked", "actions icon was not clicked. Exception : " + e, Status.ERR);
        }
    }

    @And("^user clicks on archive course and enters name \"([^\"]*)\"$")
    public void userClicksOnArchiveCourseAndEntersName(String archiveName) {
        try {
            DriverAction.click(ArchiveCourseLocators.archiveCourseOption);
            DriverAction.waitSec(2);
            DriverAction.typeText(ArchiveCourseLocators.archivedNameInput, archiveName);
        } catch (Exception e) {
            GemTestReporter.addTestStep("tab not visible", "archived courses tab not visible. Exception : " + e, Status.ERR);
        }
    }

    @Then("verify error appears")
    public void verifyErrorAppears() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if(DriverAction.isDisplayed(ArchiveCourseLocators.archiveError)) {
                GemTestReporter.addTestStep("Error verified", "Error message verified", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Error not verified", "Error message not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Error not verified", "Error message not verified. Exception : " + e, Status.ERR);
        }
    }

    @And("user clicks on edit")
    public void userClicksOnEdit() {
        try {
            DriverAction.click(ArchiveCourseLocators.editCourse);
        } catch (Exception e) {
            GemTestReporter.addTestStep("actions icon not clicked", "actions icon was not clicked. Exception : " + e, Status.ERR);
        }
    }

    @Then("verify archive course confirmation appears")
    public void verifyArchiveCourseConfirmationAppears() {
        try {
            if(DriverAction.isDisplayed(ArchiveCourseLocators.archiveConfirmation)) {
                GemTestReporter.addTestStep("confirmation verified", "confirmation verified.", Status.PASS);
            } else {
                GemTestReporter.addTestStep("confirmation not verified", "confirmation not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("confirmation not verified", "confirmation not verified. Exception : " + e, Status.ERR);
        }
    }

    @Then("verify edit screen appears")
    public void verifyEditScreenAppears() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            DriverAction.waitSec(5);
            if(DriverAction.isDisplayed(ArchiveCourseLocators.editScreen)) {
                GemTestReporter.addTestStep("screen verified", "edit screen verified." , Status.PASS);
            } else {
                GemTestReporter.addTestStep("screen not verified", "edit screen not verified", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("screen not verified", "Edit screen not verified. Exception : " + e, Status.ERR);
        }
    }
}
