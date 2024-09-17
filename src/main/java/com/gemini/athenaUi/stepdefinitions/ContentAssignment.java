package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContentAssignment {

    String _contentName ="";
    String _assignmentName ="";

    @Then("^Enter respective values in content fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterContent(String contentTags, String filetype, String duration, String file) {
        try {
            List<WebElement> inputFields= DriverAction.getElements(MyLocators.contentInputFields);
            _contentName = RandomStringUtils.randomAlphanumeric(10);
            _contentName ="s"+ _contentName;

            System.out.println(_contentName);
            String inputValues[]={_contentName,contentTags,filetype,duration};
            int k=0;
            for(int i=0;i<inputFields.size();i++){
                String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
                String upload=inputFields.get(i).getAttribute("type");
                //if dropdown is present
                if(dropdown!=null&&dropdown.equals("listbox")){

                    List<WebElement>dropdownFields=DriverAction.getElements(MyLocators.fieldsDropdown);

                    DriverAction.click(dropdownFields.get(k));
                    k++;
                    DriverAction.click(By.xpath(MyLocators.option.replace("input",inputValues[i])));
                    //close the dropdown
                    if(DriverAction.isDisplayed(MyLocators.crossIcon)){
                        DriverAction.click(MyLocators.crossIcon);
                    }
                }
                //if file-upload
                else if(upload!=null&&upload.equals("file")){
                     DriverAction.fileUpload(inputFields.get(i),file);
                }
                //if textbox is present
                else{

                    Thread.sleep(2000);
                    DriverAction.typeText(inputFields.get(i),inputValues[i]);
                }

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in content input fields", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Enter description \"([^\"]*)\"$")
    public void addDescription(String description){
        try{

            DriverAction.typeText(MyLocators.textarea,description);
        }catch(Exception e){

            GemTestReporter.addTestStep("Enter description","Exception encountered- "+e,Status.ERR);
        }
    }

    @And("^Verify content is created successfully \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyContent(String contentTag, String duration, String fileType) {

        try {
            Thread.sleep(8000);

            //checking if the created fields match the field values passed by us
            String[] contentData = {_contentName, contentTag, duration, fileType};
            List<WebElement> verifyContent = DriverAction.getElements(MyLocators.contentData);
            int c = 0;
            for (int i = 0; i < verifyContent.size(); i++) {
                String data = DriverAction.getElementText(verifyContent.get(i));
                if (data.contains(contentData[i])) {
                    c++;
                    if(c==contentData.length){
                        break;
                    }
                }
                DriverAction.waitSec(1);
            }
            if (c == contentData.length) {
                GemTestReporter.addTestStep("Verify content is created successfully", "Verified the successful creation of content.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify content is created successfully", "Could not verify the successful creation of content.", Status.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify content is created successfully","Exception encountered- "+e,Status.ERR);
        }


    }


    @And("^Enter assignment description \"([^\"]*)\"$")
    public void assignmentDescription(String description) {
        try{

            DriverAction.typeText(MyLocators.assignmentDescription,description);
        }catch(Exception e){

            GemTestReporter.addTestStep("Enter assignment description","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Enter respective values in assignment fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterAssignment(String assignmentTag, String marks, String duration, String category, String fileLocation) {

        try {
            List<WebElement> inputFields= DriverAction.getElements(MyLocators.assignmentInputFields);
            _assignmentName = RandomStringUtils.randomAlphanumeric(10);
            //adding 'a' at start because assignment name does not start with a digit
            _assignmentName ="a"+ _assignmentName;
            String inputValues[]={_assignmentName,assignmentTag,marks,duration,category,fileLocation};
            int k=0;
            for(int i=0;i<inputFields.size()-1;i++){
                String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
                String upload=inputFields.get(i).getAttribute("type");
                //if dropdown is present
                if(dropdown!=null&&dropdown.equals("listbox")){
                    List<WebElement>dropdownFields=DriverAction.getElements(MyLocators.fieldsDropdown);

                        DriverAction.click(dropdownFields.get(k));
                        k++;

                    //select an option from dropdown
                    if(DriverAction.isDisplayed(By.xpath(MyLocators.option.replace("input",inputValues[i])))) {
                        DriverAction.click(By.xpath(MyLocators.option.replace("input", inputValues[i])));
                    }
                    if(DriverAction.isDisplayed(MyLocators.crossIcon)){
                        DriverAction.click(MyLocators.crossIcon);
                    }
                }
                //if file-upload field is present
                else if(upload!=null&&upload.equals("file")){
                    DriverAction.fileUpload(inputFields.get(i),fileLocation);
                }
                //if textbox is present
                else{
                    DriverAction.typeText(inputFields.get(i),inputValues[i]);
                }

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in assignment input fields", "Exception encountered- " + e, Status.ERR,DriverAction.takeSnapShot());
        }


    }

    @And("^Verify assignment is created \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyAssignment(String category, String tags, String marks) {
        try {
            Thread.sleep(7000);
            String[] contentData = {_assignmentName, category, tags, marks};
            List<WebElement> verifyContent = DriverAction.getElements(MyLocators.contentData);
            int c = 0;
            for (int i = 0; i < verifyContent.size(); i++) {
                String data = DriverAction.getElementText(verifyContent.get(i));
                if (data.equals(contentData[i])) {
                    c++;
                    if(c==contentData.length){
                        break;
                    }
                }
                DriverAction.waitSec(1);
            }
            if (c == contentData.length) {
                GemTestReporter.addTestStep("Verify assignment is created successfully", "Verified the successful creation of assignment.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify assignment is created successfully", "Could not verify the successful creation of assignment.", Status.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify assignment is created successfully","Exception encountered- "+e,Status.ERR);
        }
    }

    @When("^Click Actions icon of recently created content/assignment$")
    public void contentActionIcon() throws InterruptedException {
        try {
            Thread.sleep(5000);
            DriverAction.click(MyLocators.contentActionsIcon,"Click Actions icon of recently created content/assignment","Successfully clicked actions icon of recently created content/assignment.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Actions icon of recently created content/assignment","Exception encountered- "+e,Status.ERR);
        }
    }

    @When("^Update values in assignment fields \"([^\"]*)\", \"([^\"]*)\"$")
    public void updateValuesInAssignment(String tag, String marks) {

        try {
            //expand dropdown
        //    List<WebElement> dropdownFields = DriverAction.getElements(MyLocators.dropdownIcon);

            DriverAction.click(MyLocators.dropdownIcon,"Expand dropdown","Successfully expanded the dropdown.");
            DriverAction.waitSec(2);
            //select an option from dropdown
            DriverAction.click(By.xpath(MyLocators.option.replace("input", tag)),"Select "+tag+" from dropdown","Successfully selected "+tag+" from dropdown");
            if (DriverAction.isExist(MyLocators.crossIcon)) {
                DriverAction.click(MyLocators.crossIcon);
            }
            //enter marks
            DriverAction.typeText(MyLocators.assignmentMarks, marks,"Update marks in assignment","Successfully updated assignment marks.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Update values in assignment fields","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify assignment is updated \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyAssignmentUpdated(String tag, String marks) {

        try {
            Thread.sleep(5000);
            //verifying the updated tag and marks
            String tagDisplayed = DriverAction.getElementText(MyLocators.assignmentTagDisplayed);
            String marksDisplayed = DriverAction.getElementText(MyLocators.assignmentMarksDisplayed);
            if (tagDisplayed.contains(tag) && marksDisplayed.equals(marks)) {
                GemTestReporter.addTestStep("Verify assignment is updated", "Successfully verified the assignment is updated", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify assignment is updated", "Could not verify the updated assignment", Status.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify assignment is updated","Exception encountered- "+e,Status.ERR);
        }
    }

    @When("^Update values in content fields \"([^\"]*)\", \"([^\"]*)\"$")
    public void updateContentFields(String tags, String duration) {
        try {
            //Update content tag and duration
            DriverAction.click(MyLocators.contentTags);
            DriverAction.waitSec(2);
            DriverAction.click(By.xpath(MyLocators.option.replace("input", tags)));
            DriverAction.typeText(MyLocators.contentDuration, duration);
        }catch(Exception e){
            GemTestReporter.addTestStep("Update values in content fields","Exception encountered- "+e,Status.ERR);
        }

    }

    @Then("^Verify content is updated successfully \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyContentUpdated(String tag, String duration) {
        try {
            Thread.sleep(8000);
            String tagDisplayed = DriverAction.getElementText(MyLocators.contentTagDisplayed);
            String durationDisplayed = DriverAction.getElementText(MyLocators.contentDurationDisplayed);
            if (tagDisplayed.contains(tag) && durationDisplayed.equals(duration)) {
                GemTestReporter.addTestStep("Verify content is updated", "Successfully verified the content is updated", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify content is updated", "Could not verify the updated content", Status.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify content is updated","Exception encountered- "+e,Status.ERR);
        }
    }

    @When("^Deactivate content/assignment Status$")
    public void deactivateStatus() {
        try{
            DriverAction.click(MyLocators.switchStatus);
        }catch(Exception e){
            GemTestReporter.addTestStep("Deactivate content Status","Exception encountered- "+e,Status.ERR);
        }
    }

    @And("^Expand the dropdown containing Status$")
    public void expandStatus() {
        try{
            DriverAction.click(MyLocators.StatusDropdown);
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand dropdown containing sections","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify deactivated content/assignment$")
    public void isDeactivated() {
        try{
            Thread.sleep(4000);
            String value=DriverAction.getAttributeName(MyLocators.inactiveStatusBar,"ng-reflect-model");
            //if Status bar's attribute is false that means content/assignment is deactivated
            if(value.equals("false")){
                GemTestReporter.addTestStep("Verify deactivated content","Successfully verified deactivated content.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify deactivated content","Could not verify deactivated content.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify deactivated content","Exception encountered- "+e,Status.ERR);
        }
    }

    @When("Click Add New  button")
    public void clickAddNewButton() throws InterruptedException {
        Thread.sleep(5000);
        try {
            DriverAction.click(MyLocators.addNewAssignment);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not found", "Exception encountered : " + e,Status.ERR);
        }
    }
}
