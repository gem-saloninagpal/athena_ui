package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.TestAnalyticsLocators;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.gemini.athenaUi.stepdefinitions.CandidateModule_UserManagement.*;
public class TestAnalytics {

    int _activeStatistics;
    int _ongoingEvents;
    int _upcomingEvents;
    String _campusName;
    String _count;
    int _passCount;
    int _passedCandidates;

    @And("^Get active test statistics$")
    public void getActiveStatistics(){
        try{
            DriverAction.waitUntilElementAppear(TestAnalyticsLocators.activeStats,5);
            _activeStatistics = DriverAction.getElements(TestAnalyticsLocators.activeStats).size();
            GemTestReporter.addTestStep("Get active test statistics","The active statistics are- "+ _activeStatistics, Status.PASS,DriverAction.takeSnapShot());
        }catch(Exception e){
            GemTestReporter.addTestStep("Get active test statistics","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Compare ongoing and upcoming events with active statistics$")
    public void compareOngoingAndUpcomingEventsWithActiveStatistics() {
         try{
             Thread.sleep(3000);
             _ongoingEvents =DriverAction.getElements(TestAnalyticsLocators.events).size();
             DriverAction.click(TestAnalyticsLocators.ongoingEventsDropdown);
             DriverAction.click(TestAnalyticsLocators.selectUpcomingEvents);
             Thread.sleep(2000);
             _upcomingEvents =DriverAction.getElements(TestAnalyticsLocators.events).size();
             //sum of ongoing and upcoming tests should be equal to active tests
             if(_ongoingEvents + _upcomingEvents == _activeStatistics){
                 GemTestReporter.addTestStep("Compare ongoing and upcoming events with active statistics","Successfully validated the ongoing and upcoming events.",Status.PASS,DriverAction.takeSnapShot());
             }else{
                 GemTestReporter.addTestStep("Compare ongoing and upcoming events with active statistics","Could not validate the ongoing and upcoming events.",Status.FAIL,DriverAction.takeSnapShot());
             }
         }catch(Exception e){
             GemTestReporter.addTestStep("Compare ongoing and upcoming events with active statistics","Exception encountered- "+e,Status.ERR);
         }
    }

    @When("^Get the pass count of first campus displayed$")
    public void getThePassCountOfFirstCampusDisplayed() {
        try{
            //fetching campus name
            _campusName =DriverAction.getElementText(TestAnalyticsLocators.campus);
            _name = _campusName;
            //count of candidates passed from a particular campus
            _count =DriverAction.getElementText(TestAnalyticsLocators.passCount);
            _passCount =Integer.parseInt(_count);
            GemTestReporter.addTestStep("Get the pass count of first campus displayed","Successfully fetched the pass count as- "+ _passCount +" of campus- "+ _campusName,Status.PASS,DriverAction.takeSnapShot());
        }catch(Exception e){
            GemTestReporter.addTestStep("Get the pass count of first campus displayed","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify the passed candidates$")
    public void verifyThePassedCandidates() {
        try{
            List<WebElement> candidates=DriverAction.getElements(TestAnalyticsLocators.passedCandidates);
            for(int i=0;i<candidates.size();i++) {
                //splitting the text present on test ticket and taking passed candidate count
                String[] num = candidates.get(i).getText().split(" ");
                String passed=num[0];
                //adding the passed count of all the tests from a particular campus
                    _passedCandidates += Integer.parseInt(passed);
            }
            if(_passedCandidates == _passCount){
                GemTestReporter.addTestStep("Verify the passed candidates","Successfully verified the passed candidates",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify the passed candidates","Could not verify the passed candidates",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the passed candidates","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }
}
