package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Batches {
    String _courseName ="";
    String _email ="";
    String _batchName ="";
    String _courseState ="";

    @And("^Click actions icon of a batch$")
    public void batchActionsIcon(){
        try {
            //expand the action icon of batch
            Thread.sleep(4000);
            DriverAction.click(MyLocators.batchActionsIcon);
            GemTestReporter.addTestStep("Click actions icon of batch","Successfully clicked the actions icon", STATUS.PASS);
        }catch (Exception e){
            GemTestReporter.addTestStep("Click actions icon of batch","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Select \"([^\"]*)\" from actions dropdown$")
    public void selectFromActionsDropdown(String option) throws InterruptedException {
        try {
            Thread.sleep(5000);
            _courseState = option;
            //select option from dropdown
            DriverAction.click(By.xpath(MyLocators.editOptions.replace("input", option)));
        }catch(Exception e){
            GemTestReporter.addTestStep("Select "+option+" from dropdown","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Add a course in batch$")
    public void addCourseInBatch() {
        try{
            //add a course in batch
            Thread.sleep(3000);
            DriverAction.click(MyLocators.addCourse);
            List<WebElement>addedCourses=DriverAction.getElements(MyLocators.addedCourseName);
            int total= addedCourses.size();

            //store added course in a string
            _courseName =DriverAction.getElementText(addedCourses.get(total-1));
            System.out.print(_courseName);
            GemTestReporter.addTestStep("Add course in a batch","Successfully added the course- "+ _courseName +" in batch.",STATUS.PASS);
        }catch(Exception e){
            GemTestReporter.addTestStep("Add course in a batch","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify added course displays in batch summary$")
    public void verifyCourseInBatchSummary() {
        try {
            Thread.sleep(5000);
            DriverAction.scrollToBottom();
            //get the list of all added courses and verify the last course is recently added
            List<WebElement>courses=DriverAction.getElements(MyLocators.recentlyAddedCourse);
            int total= courses.size();
            int c=0;
            for(int i=0;i<total;i++) {
                String course = DriverAction.getElementText(courses.get(i));
                if (course.contains(_courseName)) {
                   c++;
                   break;
                }
            }
            if(c==0){
                GemTestReporter.addTestStep("Verify added course displays in batch summary","Could not verify the added course.",STATUS.FAIL,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify added course displays in batch summary","Successfully verified the added course.",STATUS.PASS,DriverAction.takeSnapShot());
            }
        }catch(Exception e){

            GemTestReporter.addTestStep("Verify added course displays in batch summary","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Get email of user$")
    public void getEmail() {
        try {
            _email = DriverAction.getElementText(MyLocators.userEmail);
            GemTestReporter.addTestStep("Get email of user", "Successfully fetched the email- " + _email, STATUS.PASS);
        }catch(Exception e){
            GemTestReporter.addTestStep("Get email of user","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify owner of a batch is selected by default$")
    public void verifyOwnerOfBatchSelected() {
        try {
            String owner = DriverAction.getElementText(MyLocators.owner);
            if (_email.contains(owner)) {
                GemTestReporter.addTestStep("Verify owner of a batch is selected by default", "Successfully verified the owner of batch is selected by default.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify owner of a batch is selected by default", "Could not verify owner of batch is selected by default.", STATUS.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify owner of a batch is selected by default","Exception encountered- "+e,STATUS.ERR);
        }

    }

    @And("^Enter respective values in batch fields \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterInBatchFields(String fileLocation, String owner) {

        try {
            //entering batch name, owner, description and uploading file
            List<WebElement> inputFields= DriverAction.getElements(MyLocators.batchInputFields);
            System.out.print(inputFields.size());
            _batchName = RandomStringUtils.randomAlphanumeric(10);
            _batchName ="a"+ _batchName;
            String[] inputValues ={_batchName,fileLocation,owner};
            int k=0;
            for(int i=0;i<inputFields.size();i++){
                if(i==1&& _courseState.equals("Edit")){
                    continue;
                }
                Thread.sleep(2000);
                String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
                String upload=inputFields.get(i).getAttribute("id");
                //dropdown
                if(dropdown!=null&&dropdown.equals("listbox")){
                    List<WebElement>dropdownFields=DriverAction.getElements(MyLocators.fieldsDropdown);

                    DriverAction.click(dropdownFields.get(k));
                    k++;
                    DriverAction.click(By.xpath(MyLocators.option1.replace("input",inputValues[i])));
                    if(DriverAction.isDisplayed(MyLocators.crossIcon)){
                        DriverAction.click(MyLocators.crossIcon);
                    }else{

                    }
                }
                //file-upload
                else if(upload!=null&&(upload.equals("getFile"))){
                    DriverAction.fileUpload(inputFields.get(i),fileLocation);
                }
                //textbox
                else{
                    inputFields.get(i).clear();
                    DriverAction.typeText(inputFields.get(i),inputValues[i]);
                }

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in batch input fields", "Exception encountered- " + e, STATUS.ERR);
        }

    }

    @Then("^Verify batch is created/updated$")
    public void verifyBatchCreatedUpdated() {

        try {
            Thread.sleep(15000);
            String batch = DriverAction.getElementText(MyLocators.batchCreated);
            if (batch.contains(_batchName)) {
                GemTestReporter.addTestStep("Verify batch is created/updated", "Successfully verified the batch is created/updated.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify batch is created/updated", "Could not verify the created/updated batch", STATUS.FAIL);
            }
        }catch(Exception e){
                GemTestReporter.addTestStep("Verify batch is created/updated","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Click the button in batch \"([^\"]*)\"$")
    public void clickCreateBatch(String button) throws InterruptedException {
        Thread.sleep(3000);
        DriverAction.click(By.xpath(MyLocators.createBatch.replace("input",button)));
    }

    @And("^Enter respective values in batch fields while editing \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterInBatchFieldsOnEdit(String fileLocation, String owner) {

        try {

            //editing batch name, owner, uploaded file etc.
            List<WebElement> inputFields= DriverAction.getElements(MyLocators.batchInputFields);
            System.out.print(inputFields.size());
            _batchName = RandomStringUtils.randomAlphanumeric(10);
            _batchName ="a"+ _batchName;
            String[] inputValues ={_batchName,"",fileLocation,owner};
            int k=0;
            for(int i=0;i<inputFields.size();i++){
                if(i==1&& _courseState.equals("Edit")){
                    continue;
                }
                Thread.sleep(2000);
                String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
                String upload=inputFields.get(i).getAttribute("id");
                //dropdown
                if(dropdown!=null&&dropdown.equals("listbox")){
                    List<WebElement>dropdownFields=DriverAction.getElements(MyLocators.fieldsDropdown);

                    DriverAction.click(dropdownFields.get(k));
                    k++;
                    DriverAction.click(By.xpath(MyLocators.option1.replace("input",inputValues[i])));
                    if(DriverAction.isDisplayed(MyLocators.crossIcon)){
                        DriverAction.click(MyLocators.crossIcon);
                    }else{

                    }
                }
                //file-upload
                else if(upload!=null&&(upload.equals("getFile"))){
                    DriverAction.fileUpload(inputFields.get(i),fileLocation);
                }
                //textbox
                else{
                    inputFields.get(i).clear();
                    DriverAction.typeText(inputFields.get(i),inputValues[i]);
                }

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in batch input fields", "Exception encountered- " + e, STATUS.ERR);
        }

    }

    @Then("^Verify added course after editing displays in batch summary$")
    public void verifyCourseAfterEditingInBatchSummary() {
        try {
            Thread.sleep(5000);
            DriverAction.scrollToBottom();
            String course=DriverAction.getElementText(MyLocators.recentlyAddedCourseAfterEdit);
            if (course.contains(_courseName)) {
                GemTestReporter.addTestStep("Verify added course displays in batch summary", "Successfully verified added course displays in batch summary.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify added course displays in batch summary", "Could not verify added course displays in batch summary.", STATUS.FAIL);
            }
        }catch(Exception e){

            GemTestReporter.addTestStep("Verify added course displays in batch summary","Exception encountered- "+e,STATUS.ERR);
        }
    }
}
