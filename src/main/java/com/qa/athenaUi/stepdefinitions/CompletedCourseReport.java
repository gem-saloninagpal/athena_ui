package com.qa.athenaUi.stepdefinitions;

import com.qa.athenaUi.locators.CampusPerformanceLocators;
import com.qa.athenaUi.locators.CompletedCourseReportLocator;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
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
            GemTestReporter.addTestStep("Enter name or email in searchbox","Exception encountered- "+e, Status.ERR,DriverAction.takeSnapShot());
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
                GemTestReporter.addTestStep("Validate records get filtered on the basis of key searched.","Successfully validated the records on the basis of key searched.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate records get filtered on the basis of key searched.","Could not validate the records on the basis of key searched.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate records get filtered on the basis of name and email","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
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
                GemTestReporter.addTestStep("Validate records get filtered on the basis of location", "Successfully validated the filtered records.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of location", "Could not validate the filtered records.", Status.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate records get filtered on the basis of location", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
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
                GemTestReporter.addTestStep("Validate records get filtered on the basis of selected category", "Successfully validated the filtered records.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate records get filtered on the basis of selected category", "Could not validate the filtered records.", Status.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate records get filtered on the basis of selected category", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
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
            GemTestReporter.addTestStep("Select start date and end date","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
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
                GemTestReporter.addTestStep("Verify records get filtered on the basis of date","Successfully verified the filtered records on the basis of date.",Status.PASS, DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify records get filtered on the basis of date","Could not verify the filtered records on the basis of date.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify records get filtered on the basis of date","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
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
                GemTestReporter.addTestStep("Validate records get unfiltered.","Successfully validated the unfiltered records.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate records get unfiltered.","Could not validate the unfiltered records.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate records get unfiltered","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify the file gets downloaded \"([^\"]*)\"$")
    public void verifyTheFileGetsDownloaded(String file) {
        try{
       //     File downloadedFile = getLatestDownloadedFile(file);
            File downloadedFile=verifyTheDownloadedFile(file);

            if (downloadedFile != null && downloadedFile.exists()) {
                if(downloadedFile.getName().contains(file)){
                    GemTestReporter.addTestStep("Verify the file gets downloaded","Successfully verified the downloaded file.",Status.PASS,DriverAction.takeSnapShot());
                }
            }else {
                GemTestReporter.addTestStep("Verify the file gets downloaded","Could not verify the downloaded file.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the file gets downloaded","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }
    private static File getLatestDownloadedFile(String downloadDir) {
        File[] files = new File("C:\\Users\\saloni.nagpal\\Downloads").listFiles();
        if (files != null && files.length > 0) {
            File latestFile = files[0];
            for (File file : files) {
                if (file.lastModified() > latestFile.lastModified()) {
                    latestFile = file;
                }
            }
            return latestFile;
        }
        return null;
}

    @And("^Expand selected category dropdown \"([^\"]*)\"$")
    public void expandSelectedCategoryDropdown(String selectedCategory) {
        try{
            DriverAction.click(By.xpath(CompletedCourseReportLocator.selectedCategoryDropdown.replace("input",selectedCategory)),"Expand selected category dropdown- "+selectedCategory,"Successfully expanded the selected category dropdown.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand selected category dropdown- "+selectedCategory,"Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Remove category selected from dropdown$")
    public void removeCategorySelectedFromDropdown() {
        try{
            DriverAction.click(CompletedCourseReportLocator.selectedCategoryCheckbox,"Remove category selected from dropdown","Successfully removed selected category.");
        }catch(Exception e){

        }
    }

    @Then("Verify the downloaded file {string}")
    public File verifyTheDownloadedFile(String file1) {
        try{
            // Specify the directory to search for downloaded files
            String directoryPath = "C:\\Users\\saloni.nagpal\\Downloads";

            File latestFile = null;
            long latestModifiedTime = Long.MIN_VALUE;

            File directory = new File(directoryPath);
            if (directory.isDirectory()) {
                for (File file : directory.listFiles()) {
                    if (file.isFile()) {
                        long modifiedTime = file.lastModified();
                        if (modifiedTime > latestModifiedTime) {
                            latestModifiedTime = modifiedTime;
                            latestFile = file;
                        }
                    }
                }
            }

            if (latestFile != null) {
                System.out.println("Most recently downloaded file: " + latestFile.getName());
                return latestFile;
            } else {
                System.out.println("No files found in the directory.");
            }
        }catch(Exception e){

        }
        return null;
    }
}
