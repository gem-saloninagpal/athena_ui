package com.gemini.athenaUi.stepdefinitions;

import java.com.gemini.athenaUi.locators.AssignLearnerInBatchLocators;
import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AssignLearnerToBatch {
    int _learnersCount=0;
    int _learnersCountOnAssign=0;
    int _learnersCountOnUnassign=0;
    int _learnersCountOnMultipleAssign=0;
    int _learnersCountOnMultipleUnassign=0;

    @And("^Assign a learner$")
    public void assignALearner() {
        try{
            DriverAction.click(AssignLearnerInBatchLocators.assignLearner,"Assign a learner","Successfully assigned learner to a batch.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Assign a learner","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Get assigned learners count$")
    public void assignedLearnerCount(){
        try{
            String learnersAssigned=DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCount=Integer.parseInt(learnersAssigned);
            GemTestReporter.addTestStep("Get assigned learners count","Successfully fetched the count of assigned learners.",STATUS.PASS,DriverAction.takeSnapShot());
        }catch(Exception e){
            GemTestReporter.addTestStep("Get assigned learners count","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Validate count after assigning$")
    public void validateCountAfterAssigning() {
        try{
            String learnersAssigned=DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnAssign=Integer.parseInt(learnersAssigned);
            if(_learnersCountOnAssign==_learnersCount+1){
                GemTestReporter.addTestStep("Validate count after assigning","Successfully validated the count after assigning.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate count after assigning","Could not validate the count after assigning.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate count after assigning","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Validate count after unassigning$")
    public void validateCountAfterUnassigning() {
        try{
            String learnersAssigned=DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnUnassign=Integer.parseInt(learnersAssigned);
            if(_learnersCountOnUnassign==_learnersCount-1){
                GemTestReporter.addTestStep("Validate count after unassigning","Successfully validated the count after unassigning.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate count after unassigning","Could not validate the count after unassigning.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate count after unassigning","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Search a learner \"([^\"]*)\"$")
    public void searchALearner(String learner) {
        try{
            DriverAction.typeText(AssignLearnerInBatchLocators.searchbox,learner,"Search a learner");
        }catch(Exception e){
            GemTestReporter.addTestStep("Search a learner","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Unassign a learner$")
    public void unassignALearner() {
        try{
            DriverAction.click(AssignLearnerInBatchLocators.unassignLearner,"Unassign a learner","Successfully unassigned learner to a batch.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Unassign a learner","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Select an unassigned learner$")
    public void selectAnUnassignedLearner() {
        try{
            DriverAction.click(AssignLearnerInBatchLocators.selectUnassignedCheckbox,"Select an unassigned learner","Successfully selected an unassigned learner.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Select an unassigned learner","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Go to next page$")
    public void goToNextPage() {
        try{
            DriverAction.click(AssignLearnerInBatchLocators.nextPage,"Go to next page","Successfully clicked the paginator.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Go to next page","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Validate the count after assigning learners from different pages$")
        public void countOnAssigningFromDifferentPages() {
        try{
            String learnersAssigned=DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnMultipleAssign=Integer.parseInt(learnersAssigned);
            if(_learnersCountOnMultipleAssign==_learnersCount+2){
                GemTestReporter.addTestStep("Validate the count after assigning learners from different pages","Successfully validated the count after multiple assign.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate the count after assigning learners from different pages","Could not validate the count after multiple assign.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate the count after assigning learners from different pages","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Select an assigned learner$")
    public void selectAnAssignedLearner() {
        try{
            DriverAction.click(AssignLearnerInBatchLocators.selectAssignedCheckbox,"Select an assigned learner","Successfully selected an assigned learner.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Select an assigned learner","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Validate the count after unassigning learners from different pages$")
    public void countOnUnassigningFromDifferentPages() {
        try{
            String learnersAssigned=DriverAction.getElementText(AssignLearnerInBatchLocators.assignedLearnerCount);
            _learnersCountOnMultipleUnassign=Integer.parseInt(learnersAssigned);
            if(_learnersCountOnMultipleUnassign==_learnersCount-2){
                GemTestReporter.addTestStep("Validate the count after unassigning learners from different pages","Successfully validated the count after multiple unassign.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate the count after unassigning learners from different pages","Could not validate the count after multiple unassign.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate the count after unassigning learners from different pages","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }
}
