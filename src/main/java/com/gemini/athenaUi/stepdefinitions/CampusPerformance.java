package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.CampusPerformanceLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CampusPerformance {

    String performanceOnHover;

    @When("^Hover over a campus and get performance$")
    public void hoverAndGetCampusPerformance(){
        try{
            DriverAction.hoverOver(CampusPerformanceLocators.graph,"Hover over a campus");
            performanceOnHover=DriverAction.getElementText(CampusPerformanceLocators.performanceOnHover);
            GemTestReporter.addTestStep("Hover over a campus and get performance","Successfully fetched the performance on hover- "+performanceOnHover, STATUS.PASS,DriverAction.takeSnapShot());
        }catch(Exception e){
            GemTestReporter.addTestStep("Hover over a campus and get performance","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Validate with the performance displayed in table$")
    public void validatePerformanceWithTable() {
        try{
            String performanceOnTable=DriverAction.getElementText(CampusPerformanceLocators.performanceOnTable);
            if(performanceOnHover.contains(performanceOnTable)){
                GemTestReporter.addTestStep("Validate the performance with table data","Successfully validated the performance with table data.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate the performance with table data","Could not validate the performance with table data.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate with the performance displayed in table","Exception encountered- "+e,STATUS.ERR);
        }
    }
}
