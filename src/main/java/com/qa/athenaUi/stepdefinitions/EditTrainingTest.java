package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.Course_Locators;
import com.qa.athenaUi.locators.EditTrainingTest_Locators;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import com.qa.athenaUi.locators.Tests_TestControl_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import io.restassured.response.ResponseBodyData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.Driver;
import java.util.List;

import static com.qa.athenaUi.stepdefinitions.Course.generateRandomCourseName;

public class EditTrainingTest {
    String testName = "";
    String courseName = "";
    @And("Select Has Test checkbox")
    public void selectHasTestCheckbox() {
        try {
            DriverAction.scrollToTop();
            courseName = DriverAction.getElementText(Course_Locators.courseNameInput);
            DriverAction.scrollToBottom();
            DriverAction.scrollIntoView(EditTrainingTest_Locators.testCheckbox);
            if(DriverAction.isDisplayed(EditTrainingTest_Locators.testCheckbox)) {
                DriverAction.click(EditTrainingTest_Locators.testCheckbox);
                GemTestReporter.addTestStep("Checkbox clicked", "Checkbox was successfully clicked", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Checkbox not clicked", "Checkbox was not clicked", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Checkbox not clicked", "Checkbox was not clicked. Exception : " + e, Status.ERR);
        }
    }

    @And("Click on Add new test")
    public void clickOnAddNewTest() {
        try {
            DriverAction.waitUntilElementIsClickable(EditTrainingTest_Locators.addNewTestBtn);
            DriverAction.click(EditTrainingTest_Locators.addNewTestBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("button not clicked", "Button was not clicked. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("Add test details")
    public void addTestDetails() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            testName = generateRandomCourseName(10);
            DriverAction.typeText(EditTrainingTest_Locators.testName, testName);
            DriverAction.typeText(EditTrainingTest_Locators.testTag, testName+ "_tag");
            DriverAction.typeText(EditTrainingTest_Locators.duration, "00:30");
            DriverAction.click(EditTrainingTest_Locators.levelDd);
            DriverAction.click(EditTrainingTest_Locators.levelBeginner);
            DriverAction.typeText(EditTrainingTest_Locators.description, "automation test");
            DriverAction.waitSec(2);
            DriverAction.click(EditTrainingTest_Locators.nextBtn);
            DriverAction.waitSec(2);
            DriverAction.waitUntilElementAppear(EditTrainingTest_Locators.shuffleDd, 120);
            DriverAction.click(EditTrainingTest_Locators.shuffleDd);
            DriverAction.waitUntilElementAppear(EditTrainingTest_Locators.noOption, 120);
            DriverAction.click(EditTrainingTest_Locators.noOption);
            DriverAction.waitSec(2);
            DriverAction.click(EditTrainingTest_Locators.serverSide);
            DriverAction.waitUntilElementAppear(EditTrainingTest_Locators.noOption, 120);
            DriverAction.click(EditTrainingTest_Locators.noOption);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Details not entered", "Details were not entered", Status.ERR);
        }
    }

    @And("Click on Next button")
    public void clickOnNextButton() {
        try {
            DriverAction.waitUntilElementIsClickable(EditTrainingTest_Locators.nextBtn1);
            DriverAction.click(EditTrainingTest_Locators.nextBtn1);
        } catch (Exception e) {
            GemTestReporter.addTestStep("button not clicked", "Button was not clicked. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("Click on Add To Course button")
    public void clickOnAddToCourseButton() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            DriverAction.scrollToTop();
            DriverAction.waitUntilElementIsClickable(EditTrainingTest_Locators.addToCourseBtn);
            DriverAction.click(EditTrainingTest_Locators.addToCourseBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("button not clicked", "Button was not clicked. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("Publish the course")
    public void publishTheCourse() {
        try {
            DriverAction.waitSec(2);
            if(DriverAction.isDisplayed(EditTrainingTest_Locators.defaultOrder)) {
                DriverAction.click(EditTrainingTest_Locators.defaultOrder);
                DriverAction.click(EditTrainingTest_Locators.saveCourseAndPublish);
            } else {
                DriverAction.click(EditTrainingTest_Locators.saveCourseAndPublish);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("button not clicked", "Button was not clicked. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("Search course")
    public void searchCourse() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader,200);
            DriverAction.waitUntilElementAppear(EditTrainingTest_Locators.searchCourse, 200);
            DriverAction.typeText(EditTrainingTest_Locators.searchCourse, courseName);
        } catch (Exception e) {
            GemTestReporter.addTestStep("course not searched", "Course was not searched. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("Click on edit course")
    public void clickOnEditCourse() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            DriverAction.click(EditTrainingTest_Locators.courseActionsIcon);
            DriverAction.waitSec(2);
            DriverAction.click(EditTrainingTest_Locators.editCourse);
            DriverAction.waitSec(1);
            DriverAction.click(EditTrainingTest_Locators.archiveNo);
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
        } catch (Exception e) {
            GemTestReporter.addTestStep("edit unsuccessful", "Course was not edited. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("Edit added test")
    public void editAddedTest() {
        try {
            DriverAction.waitSec(5);
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            DriverAction.click(EditTrainingTest_Locators.courseActionsIcon);
            DriverAction.waitUntilElementAppear(EditTrainingTest_Locators.editTest, 120);
            DriverAction.click(EditTrainingTest_Locators.editTest);
            DriverAction.waitSec(3);
            DriverAction.click(EditTrainingTest_Locators.nextBtn);
            DriverAction.waitSec(2);
            DriverAction.click(EditTrainingTest_Locators.shuffleDd);
            DriverAction.click(EditTrainingTest_Locators.noOption);
            DriverAction.click(EditTrainingTest_Locators.nextBtn1);
        } catch (Exception e) {
            GemTestReporter.addTestStep("button not clicked", "Button was not clicked. Exception occurred : " + e, Status.ERR);
        }
    }

    @Then("verify test is edited")
    public void verifyTestIsEdited() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            if(DriverAction.isDisplayed(EditTrainingTest_Locators.successfulMsg)) {
                GemTestReporter.addTestStep("Test was edited", "Test was successfully edited.", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Test not edited", "Test was not edited.", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Test not edited", "Test was not edited. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("Update added questions")
    public void updateAddedQuestions() {
        try {
//            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            DriverAction.waitSec(2);
            DriverAction.scrollToBottom();
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(Tests_TestControl_Locators.addQuestion);
            DriverAction.scrollToBottom();
            //click on Add Question for the following test
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.addQuestion)) {
                DriverAction.click(Tests_TestControl_Locators.addQuestion,"Validate user able to click on add question button","User successfully able to click on question button");
            }
            DriverAction.scrollToBottom();
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.importRandomBtn)) {
                DriverAction.click(Tests_TestControl_Locators.importRandomBtn,"Validate user able to click on import random button","user successfully able to click on import random button");
            }
            DriverAction.waitSec(5);
            //filling the Question Details
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.updateQuestionTypeDD)) {
                DriverAction.click(Tests_TestControl_Locators.updateQuestionTypeDD);
                DriverAction.waitSec(5);
                if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input", "Multiple choice question")))) {
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input", "Multiple choice question")),"Validate user able to select the required option","User successfully select Multiple choice option");
                }
            }
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.noOfQuestionInput)) {
                DriverAction.typeText(Tests_TestControl_Locators.noOfQuestionInput, "2","Validate user able to type required text","User able to type no of question successfully");
            }
            Thread.sleep(2000);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.updateDifficultyDD)) {
                DriverAction.click(Tests_TestControl_Locators.updateDifficultyDD);
                if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input", "Hard")))) {
                    DriverAction.waitSec(5);
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input", "Hard")),"Validate user able to select the required option","User successfully select Hard option");
                }
            }
            Thread.sleep(2000);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.updateLevelDD)) {
                DriverAction.click(Tests_TestControl_Locators.updateLevelDD);
                if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input", "Basic")))) {
                    DriverAction.waitSec(5);
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input", "Basic")),"Validate user able to select the required option","User successfully select Basic option");
                }
            }
            DriverAction.waitSec(2);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.submitButton)) {
                DriverAction.click(Tests_TestControl_Locators.submitButton,"Validate user able to click on submit button","User successfully submit the import random question form");
            }
            DriverAction.scrollToBottom();
            DriverAction.waitSec(2);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.saveButton)) {
                DriverAction.click(Tests_TestControl_Locators.saveButton,"Validate user able to click on save button","Test was saved successfully");
            }
            DriverAction.scrollToBottom();
//            DriverAction.waitSec(2);
//            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.continueButton)) {
                DriverAction.click(Tests_TestControl_Locators.continueButton);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter course description", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("Select Training Tests")
    public void selectTrainingTests() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            DriverAction.waitUntilElementIsClickable(EditTrainingTest_Locators.trainingTests);
            DriverAction.click(EditTrainingTest_Locators.trainingTests);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Training tests not opened", "Could not open training tests section. Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Search training test \"([^\"]*)\"$")
    public void searchTrainingTest(String testName) {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            DriverAction.typeText(EditTrainingTest_Locators.searchTrainingTest, testName);
            DriverAction.waitSec(5);
//            DriverAction.typeText(EditTrainingTest_Locators.searchTrainingTest, " ");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Training tests not searched", "Could not search training tests. Exception encountered- " + e, Status.ERR);
        }
    }

    @And("Edit test")
    public void editTest() {
        try {
            DriverAction.waitSec(5);
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            DriverAction.click(EditTrainingTest_Locators.testActions);
            DriverAction.waitUntilElementAppear(EditTrainingTest_Locators.editTest, 120);
            DriverAction.click(EditTrainingTest_Locators.editTest);
            DriverAction.waitSec(5);
            DriverAction.click(EditTrainingTest_Locators.nextBtn);
            DriverAction.waitSec(2);
            DriverAction.click(EditTrainingTest_Locators.shuffleDd);
            DriverAction.click(EditTrainingTest_Locators.noOption);
            DriverAction.click(EditTrainingTest_Locators.nextBtn1);
        } catch (Exception e) {
            GemTestReporter.addTestStep("button not clicked", "Button was not clicked. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("Update added questions when editing from training test section")
    public void updateAddedQuestionsWhenEditingFromTrainingTestSection() {
        try {
//            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            DriverAction.waitSec(2);
            DriverAction.scrollToBottom();
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(Tests_TestControl_Locators.addQuestion);
            DriverAction.scrollToBottom();
            //click on Add Question for the following test
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.addQuestion)) {
                DriverAction.click(Tests_TestControl_Locators.addQuestion,"Validate user able to click on add question button","User successfully able to click on question button");
            }
            DriverAction.scrollToBottom();
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.importRandomBtn)) {
                DriverAction.click(Tests_TestControl_Locators.importRandomBtn,"Validate user able to click on import random button","user successfully able to click on import random button");
            }
            DriverAction.waitSec(3);
            //filling the Question Details
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.updateQuestionTypeTraining)) {
                DriverAction.click(Tests_TestControl_Locators.updateQuestionTypeTraining);
                DriverAction.waitSec(3);
                if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input", "Multiple choice question")))) {
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input", "Multiple choice question")),"Validate user able to select the required option","User successfully select Multiple choice option");
                }
            }
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.noOfQuestionInput)) {
                DriverAction.typeText(Tests_TestControl_Locators.noOfQuestionInput, "2","Validate user able to type required text","User able to type no of question successfully");
            }
            Thread.sleep(2000);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.difficultyTraining)) {
                DriverAction.click(Tests_TestControl_Locators.difficultyTraining);
                if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input", "Hard")))) {
                    DriverAction.waitSec(3);
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input", "Hard")),"Validate user able to select the required option","User successfully select Hard option");
                }
            }
            Thread.sleep(2000);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.updateLevelTraining)) {
                DriverAction.click(Tests_TestControl_Locators.updateLevelTraining);
                if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input", "Basic")))) {
                    DriverAction.waitSec(3);
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input", "Basic")),"Validate user able to select the required option","User successfully select Basic option");
                }
            }
            DriverAction.waitSec(2);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.submitButton)) {
                DriverAction.click(Tests_TestControl_Locators.submitButton,"Validate user able to click on submit button","User successfully submit the import random question form");
            }
            DriverAction.scrollToBottom();
            DriverAction.waitSec(2);
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.saveButton)) {
                DriverAction.click(Tests_TestControl_Locators.saveButton,"Validate user able to click on save button","Test was saved successfully");
            }
            DriverAction.scrollToBottom();
            if (DriverAction.isDisplayed(Tests_TestControl_Locators.continueButton)) {
                DriverAction.click(Tests_TestControl_Locators.continueButton);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter course description", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("Click on edit test")
    public void clickOnEditTest() {
        try {
            DriverAction.waitSec(5);
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            DriverAction.click(EditTrainingTest_Locators.testActions);
            DriverAction.waitUntilElementAppear(EditTrainingTest_Locators.editTest, 120);
            DriverAction.click(EditTrainingTest_Locators.editTest);
        } catch (Exception e) {
            GemTestReporter.addTestStep("button not clicked", "Button was not clicked. Exception occurred : " + e, Status.ERR);
        }
    }

    @Then("Verify test does not get edited")
    public void verifyTestDoesNotGetEdited() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 120);
            if(DriverAction.isDisplayed(EditTrainingTest_Locators.alreadyAttemptedMsg)) {
                GemTestReporter.addTestStep("Validation passed", "Validation passed.", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Validation failed", "Validation failed. Exception occurred", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validation failed", "Validation failed. Exception occurred : " + e, Status.ERR);
        }
    }

    @When("^User searches for course \"([^\"]*)\"$")
    public void userSearchesForCourse(String courseNameNew) {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader,200);
            DriverAction.waitUntilElementAppear(EditTrainingTest_Locators.searchCourse, 200);
            DriverAction.typeText(EditTrainingTest_Locators.searchCourse, courseNameNew);
        } catch (Exception e) {
            GemTestReporter.addTestStep("course not searched", "Course was not searched. Exception occurred : " + e, Status.ERR);
        }
    }

    @And("click on test actions icon")
    public void clickOnTestActionsIcon() {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(EditTrainingTest_Locators.courseActionsIcon);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Not searched", "Actions was not clicked. Exception occurred : " + e, Status.ERR);
        }
    }

    @Then("verify edit test option is not available")
    public void verifyEditTestOptionIsNotAvailable() {
        try {
            List<WebElement> actionItems = DriverAction.getElements(EditTrainingTest_Locators.trainingTestActionItems);
            if(actionItems.size() == 1) {
                GemTestReporter.addTestStep("Validation successful", "Validation was successful.", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Validation failed", "Validation failed. Number of items : " + actionItems.size(), Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validation failed", "Validation failed. Exception occurred : " + e, Status.ERR);
        }
    }
}
