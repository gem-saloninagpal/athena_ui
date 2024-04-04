package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.CampusPerformanceLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CompletedCourseReport {
    String _startDate;
    String _endDate;
    String formattedStartDate;
    String formattedEndDate;
    @And("^Enter name or email in searchbox \"([^\"]*)\"$")
    public void searchByNameEmail(String text) {
        try{
     //       DriverAction.waitUntilElementAppear(MyLocators.searchbox,5);
            DriverAction.typeText(CampusPerformanceLocators.nameEmailSearchbox,text,"Enter name or email");
        }catch(Exception e){
            GemTestReporter.addTestStep("Enter name or email in searchbox","Exception encountered- "+e, STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("Validate records get filtered on the basis of name and email {string}")
    public void validateRecordsOnTheBasisOfNameAndEmail(String searchedKey) {
        try{
            List<WebElement> firstname=DriverAction.getElements(CampusPerformanceLocators.name);
            List<WebElement> lastname=DriverAction.getElements(CampusPerformanceLocators.lastname);
            List<WebElement> email=DriverAction.getElements(CampusPerformanceLocators.email);
            boolean isPassed=true;
            for(int i=0;i<firstname.size()&&i<email.size()&&i<lastname.size();i++){
             //   String abc=name.get(i).getAttribute("ng-reflect-text");
                if(!firstname.get(i).getAttribute("ng-reflect-text").contains(searchedKey) && !lastname.get(i).getAttribute("ng-reflect-text").contains(searchedKey) && !email.get(i).getAttribute("ng-reflect-text").contains(searchedKey)){
                    isPassed=false;
                    break;
                }
                firstname=DriverAction.getElements(CampusPerformanceLocators.name);
                lastname=DriverAction.getElements(CampusPerformanceLocators.lastname);
                email=DriverAction.getElements(CampusPerformanceLocators.email);
            }
            if(isPassed){
                GemTestReporter.addTestStep("Validate records get filtered on the basis of key searched.","Successfully validated the records on the basis of key searched.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate records get filtered on the basis of key searched.","Could not validate the records on the basis of key searched.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate records get filtered on the basis of name and email","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("Validate completed courses records get filtered on the basis of location {string}")
    public void validateCompletedCoursesOnLocation(String location) {
        try {
            DriverAction.waitSec(3);
            List<WebElement> rows = DriverAction.getElements(CampusPerformanceLocators.locationDisplayedOnTable);
            boolean isPassed = true;
            for (WebElement row : rows) {
                String getStatus = row.getText();
                if (!getStatus.contains(location)) {
                    isPassed = false;
                    break;
                }
            }
            if (isPassed) {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of location", "Successfully validated the filtered records.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of location", "Could not validate the filtered records.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate records get filtered on the basis of location", "Exception encountered- " + e, STATUS.ERR, DriverAction.takeSnapShot());
        }

    }

    @Then("Validate completed courses records get filtered on the basis of selected category {string}")
    public void validateCompletedCoursesRecordsGetFilteredOnTheBasisOfSelectedCategory(String selectedCategory) {
        try {
            DriverAction.waitSec(5);
            List<WebElement> rows = DriverAction.getElements(CampusPerformanceLocators.selectedCategoryDisplayedOnTable);
            boolean isPassed = true;
            for (WebElement row : rows) {
                String getStatus = row.getText();
                if (!getStatus.contains(selectedCategory)) {
                    isPassed = false;
                    break;
                }
            }
            if (isPassed) {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of selected category", "Successfully validated the filtered records.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of selected category", "Could not validate the filtered records.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate records get filtered on the basis of selected category", "Exception encountered- " + e, STATUS.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Select start date and end date$")
    public void selectStartDateAndEndDate() {
        try {
       //     String startDate;
            DriverAction.click(CampusPerformanceLocators.expandStartDateCalendar, "Expand start date calendar", "Successfully expanded the start date calendar.");
            DriverAction.click(CampusPerformanceLocators.selectDate, "Select start date", "Successfully selected the start date.");
            _startDate = DriverAction.getAttributeName(CampusPerformanceLocators.selectedStartDate, "ng-reflect-model");
//            startDate=_startDate;
//            LocalDate _startDate = LocalDate.parse(startDate, formatter);
            DriverAction.click(CampusPerformanceLocators.expandEndDateCalendar, "Expand end date calendar", "Successfully expanded the end date calendar.");
            DriverAction.click(CampusPerformanceLocators.selectDate, "Select end date", "Successfully selected the end date.");
            _endDate = DriverAction.getAttributeName(CampusPerformanceLocators.selectedEndDate, "ng-reflect-model");
            formattedStartDate = convertDate(_startDate);
            formattedEndDate = convertDate(_endDate);
            System.out.println(formattedStartDate);
            System.out.println(formattedEndDate);
        }catch(Exception e){
            GemTestReporter.addTestStep("Select start date and end date","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

        private static String convertDate(String dateString) {
            // Parse the date string using the specified format
//            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
//            LocalDate date = LocalDate.parse(dateString, inputFormatter);
//
//            // Format the date object to the desired output format
//            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            return date.format(outputFormatter);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);
        //    System.out.println(date);
            String _date= String.valueOf(date);
            String formattedDate = _date.substring(5, 7) + "/" + _date.substring(8) + "/" + _date.substring(0, 4);
             return formattedDate;
        }

    @Then("^Verify records get filtered on the basis of date$")
    public void verifyRecordsGetFilteredOnTheBasisOfDate() {
        try{
            List<WebElement>records=DriverAction.getElements(CampusPerformanceLocators.recordsDisplayed);
            List<WebElement>startDatesDisplayed=DriverAction.getElements(CampusPerformanceLocators.startDates);
            List<WebElement>endDatesDisplayed=DriverAction.getElements(CampusPerformanceLocators.endDates);
            int c=0;
            for(int i=0;i<records.size();i++){
                String startDate=DriverAction.getElementText(startDatesDisplayed.get(i));
                String endDate=DriverAction.getElementText(endDatesDisplayed.get(i));
                if (startDate.compareTo(formattedStartDate) >= 0 && endDate.compareTo(formattedEndDate) <= 0){
                    c++;
                }
            }
            if(c==10){
                GemTestReporter.addTestStep("Verify records get filtered on the basis of date","Successfully verified the filtered records on the basis of date.",STATUS.PASS, DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify records get filtered on the basis of date","Could not verify the filtered records on the basis of date.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify records get filtered on the basis of date","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Validate records get unfiltered \"([^\"]*)\"$")
    public void validateRecordsGetUnfiltered(String searchedKey) {
        try{
            List<WebElement> name=DriverAction.getElements(CampusPerformanceLocators.name);
            List<WebElement> email=DriverAction.getElements(CampusPerformanceLocators.email);
            boolean isPassed=true;
            for(int i=0;i<name.size()&&i<email.size();i++){
                if(!name.get(i).getText().contains(searchedKey) && !email.get(i).getText().contains(searchedKey)){
                    isPassed=false;
                    break;
                }
                name=DriverAction.getElements(CampusPerformanceLocators.name);
                email=DriverAction.getElements(CampusPerformanceLocators.email);
            }
            if(!isPassed){
                GemTestReporter.addTestStep("Validate records get unfiltered.","Successfully validated the unfiltered records.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate records get unfiltered.","Could not validate the unfiltered records.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate records get unfiltered","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }
}
