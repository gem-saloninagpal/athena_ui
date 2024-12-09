package com.qa.athenaUi.stepdefinitions;

import com.qa.athenaUi.locators.CampusPerformanceLocators;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CampusPerformance {

    String _performanceOnHover;

    @When("^Hover over a campus and get performance$")
    public void hoverAndGetCampusPerformance(){
        try{
            DriverAction.hoverOver(CampusPerformanceLocators.graph,"Hover over a campus");
            _performanceOnHover =DriverAction.getElementText(CampusPerformanceLocators.performanceOnHover);
            GemTestReporter.addTestStep("Hover over a campus and get performance","Successfully fetched the performance on hover- "+ _performanceOnHover, Status.PASS,DriverAction.takeSnapShot());
        }catch(Exception e){
            GemTestReporter.addTestStep("Hover over a campus and get performance","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Validate with the performance displayed in table$")
    public void validatePerformanceWithTable() {
        try{
            String performanceOnTable=DriverAction.getElementText(CampusPerformanceLocators.performanceOnTable);
            if(_performanceOnHover.contains(performanceOnTable)){
                GemTestReporter.addTestStep("Validate the performance with table data","Successfully validated the performance with table data.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate the performance with table data","Could not validate the performance with table data.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate with the performance displayed in table","Exception encountered- "+e,Status.ERR);
        }
    }
}
