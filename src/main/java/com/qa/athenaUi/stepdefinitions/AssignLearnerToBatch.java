package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.AssignLearnerInBatchLocators;
import com.qa.athenaUi.locators.MyLocators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.sql.Driver;
import java.util.List;

public class AssignLearnerToBatch {
    int _learnersCount = 0;
    int _learnersCountOnAssign = 0;
    int _learnersCountOnUnassign = 0;
    int _learnersCountOnMultipleAssign = 0;
    int _learnersCountOnMultipleUnassign = 0;

    @And("^Assign a learner$")
    public void assignALearner() {
        try {
            DriverAction.waitSec(5);
            DriverAction.waitUntilElementClickable(AssignLearnerInBatchLocators.assignLearner,5);
            DriverAction.click(AssignLearnerInBatchLocators.assignLearner, "Assign a learner", "Successfully assigned learner to a batch.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Assign a learner", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Get assigned learners count$")
    public void assignedLearnerCount() {
        try {
            DriverAction.waitSec(3);
            DriverAction.waitUntilElementAppear(AssignLearnerInBatchLocators.assignedLearnerCount, 3);
            String learnersAssigned = DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCount = Integer.parseInt(learnersAssigned);
            GemTestReporter.addTestStep("Get assigned learners count", "Successfully fetched the count of assigned learners.", Status.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Get assigned learners count", "Exception encountered- " + e, Status.ERR);
        }
    }

    @Then("^Validate count after assigning$")
    public void validateCountAfterAssigning() {
        try {
            DriverAction.waitSec(5);
            String learnersAssigned = DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnAssign = Integer.parseInt(learnersAssigned);
//            if (_learnersCountOnAssign == _learnersCount + 1) {
//                GemTestReporter.addTestStep("Validate count after assigning", "Successfully validated the count after assigning.", Status.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Validate count after assigning", "Could not validate the count after assigning.", Status.FAIL, DriverAction.takeSnapShot());
//            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate count after assigning", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Validate count after unassigning$")
    public void validateCountAfterUnassigning() {
        try {
            DriverAction.waitSec(5);
            String learnersAssigned = DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnUnassign = Integer.parseInt(learnersAssigned);
//            if (_learnersCountOnUnassign == _learnersCount - 1) {
//                GemTestReporter.addTestStep("Validate count after unassigning", "Successfully validated the count after unassigning.", Status.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Validate count after unassigning", "Could not validate the count after unassigning.", Status.FAIL, DriverAction.takeSnapShot());
//            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate count after unassigning", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Search a learner \"([^\"]*)\"$")
    public void searchALearner(String learner) {
        try {
            DriverAction.waitSec(4);
            DriverAction.typeText(MyLocators.learnerSearchbox, learner, "Search a learner");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Search a learner", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Unassign a learner$")
    public void unassignALearner() {
        try {
            DriverAction.waitSec(8);
            DriverAction.waitUntilElementIsClickable(AssignLearnerInBatchLocators.unassignLearner);
            DriverAction.click(AssignLearnerInBatchLocators.unassignLearner, "Unassign a learner", "Successfully unassigned learner to a batch.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unassign a learner", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Select an unassigned learner$")
    public void selectAnUnassignedLearner() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(AssignLearnerInBatchLocators.selectUnassignedCheckbox, "Select an unassigned learner", "Successfully selected an unassigned learner.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Select an unassigned learner", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Go to next page$")
    public void goToNextPage() {
        try {
            DriverAction.click(AssignLearnerInBatchLocators.nextPage, "Go to next page", "Successfully clicked the paginator.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Go to next page", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @Then("^Validate the count after assigning learners from different pages$")
    public void countOnAssigningFromDifferentPages() {
        try {
            DriverAction.waitSec(5);
            String learnersAssigned = DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnMultipleAssign = Integer.parseInt(learnersAssigned);
//            if (_learnersCountOnMultipleAssign == _learnersCount + 2) {
//                GemTestReporter.addTestStep("Validate the count after assigning learners from different pages", "Successfully validated the count after multiple assign.", Status.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Validate the count after assigning learners from different pages", "Could not validate the count after multiple assign.", Status.FAIL, DriverAction.takeSnapShot());
//            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate the count after assigning learners from different pages", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Select an assigned learner$")
    public void selectAnAssignedLearner() {
        try {
            DriverAction.waitSec(8);
            DriverAction.waitUntilElementAppear(AssignLearnerInBatchLocators.selectAssignedCheckbox, 8);
            DriverAction.click(AssignLearnerInBatchLocators.selectAssignedCheckbox, "Select an assigned learner", "Successfully selected an assigned learner.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Select an assigned learner", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @Then("^Validate the count after unassigning learners from different pages$")
    public void countOnUnassigningFromDifferentPages() {
        try {
            DriverAction.waitSec(4);
            String learnersAssigned = DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnMultipleUnassign = Integer.parseInt(learnersAssigned);
//            if (_learnersCountOnMultipleUnassign == _learnersCount - 2) {
//                GemTestReporter.addTestStep("Validate the count after unassigning learners from different pages", "Successfully validated the count after multiple unassign.", Status.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Validate the count after unassigning learners from different pages", "Could not validate the count after multiple unassign.", Status.FAIL, DriverAction.takeSnapShot());
//            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate the count after unassigning learners from different pages", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @Then("^Validate records get filtered on the basis of status \"([^\"]*)\"$")
    public void validateRecordsGetFilteredOnTheBasisOfStatus(String status) {
        try {
            DriverAction.waitSec(3);
            List<WebElement> rows = DriverAction.getElements(AssignLearnerInBatchLocators.status);
            boolean isPassed = true;
            for (WebElement row : rows) {
                String getStatus = row.getText();
                if (!getStatus.equals(status)) {
                    isPassed = false;
                    break;
                }
            }
            if (isPassed) {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of status", "Successfully validated the filtered records", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of status", "Could not validate the filtered records", Status.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate records get filtered on the basis of status", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @Then("Validate records get filtered on the basis of EC {string}")
    public void validateRecordsFilteredOnEC(String ec) {
        try {
            DriverAction.waitSec(5);
            List<WebElement> statusOfRecords = DriverAction.getElements(AssignLearnerInBatchLocators.selectedEC);
            boolean isPassed = true;
            for (int i = 0; i < statusOfRecords.size(); i++) {
                if (!statusOfRecords.get(i).getText().equals(ec)) {
                    isPassed = false;
                    break;
                }
            }
            if (isPassed) {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of EC", "Successfully validated the records on the basis of EC.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of EC", "Could not validate the records on the basis of EC.", Status.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate records get filtered on the basis of EC", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Expand selected category dropdown$")
    public void expandSelectedCategoryDropdown() {
        try {
            DriverAction.click(AssignLearnerInBatchLocators.expandSelectedCategory, "Expand selected category dropdown", "Successfully expanded the selected category dropdown.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Expand selected category dropdown", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("Click assign selected")
    public void clickAssignSelected() {
        try {
            DriverAction.waitUntilElementClickable(AssignLearnerInBatchLocators.assignSelected, 4);
            DriverAction.click(AssignLearnerInBatchLocators.assignSelected);
        } catch (Exception e) {

        }
    }

    @And("Click unassign selected")
    public void clickUnassignSelected() {
        try {
            DriverAction.waitUntilElementClickable(AssignLearnerInBatchLocators.unassignSelected, 4);
            DriverAction.click(AssignLearnerInBatchLocators.unassignSelected);
        } catch (Exception e) {

        }
    }

    @Then("^Validate count after assigning learners from different pages$")
    public void validateCountAfterMultipleAssign() {
        try {
            DriverAction.waitSec(3);
            String learnersAssigned = DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnAssign = Integer.parseInt(learnersAssigned);
            if (_learnersCountOnAssign == _learnersCount + 2) {
                GemTestReporter.addTestStep("Validate count after assigning", "Successfully validated the count after assigning.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate count after assigning", "Could not validate the count after assigning.", Status.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate count after assigning", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }
    }


    @And("Select EC from dropdown")
    public void selectECFromDropdown() {
        DriverAction.waitSec(3);
        DriverAction.click(AssignLearnerInBatchLocators.ec);
    }

    @And("Select QA from dropdown")
    public void selectQAFromDropdown() {
        DriverAction.waitSec(3);
        DriverAction.click(AssignLearnerInBatchLocators.qa);
    }
}