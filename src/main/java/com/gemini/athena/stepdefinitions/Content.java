package com.gemini.athena.stepdefinitions;

import com.gemini.athena.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Content {

    String contentName="";
    String assignmentName="";

    @Then("^Enter respective values in content fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterContent(String contentTags, String filetype, String duration, String file) {
        try {
            List<WebElement> inputFields= DriverAction.getElements(MyLocators.contentInputFields);
            contentName= RandomStringUtils.randomAlphanumeric(10);

            System.out.println(contentName);
            String inputValues[]={contentName,contentTags,filetype,duration};
            for(int i=0;i<inputFields.size();i++){
                String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
                String upload=inputFields.get(i).getAttribute("type");
                //dropdown
                if(dropdown!=null&&dropdown.equals("listbox")){

                    DriverAction.click(MyLocators.dropdownIcon);
                    DriverAction.click(By.xpath(MyLocators.option.replace("input",inputValues[i])));
                }
                //file-upload
                else if(upload!=null&&upload.equals("file")){
                     DriverAction.fileUpload(inputFields.get(i),file);
                }
                //textbox
                else{

                    DriverAction.typeText(inputFields.get(i),inputValues[i]);
                }

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in content input fields", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Enter description \"([^\"]*)\"$")
    public void addDescription(String description){
        try{

            DriverAction.typeText(MyLocators.textarea,description);
        }catch(Exception e){

            GemTestReporter.addTestStep("Enter description","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Verify content is created successfully \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyContent(String contentTag, String duration, String fileType) {

        try {
            String[] contentData = {contentName, contentTag, duration, fileType};
            List<WebElement> verifyContent = DriverAction.getElements(MyLocators.contentData);
            int c = 0;
            for (int i = 0; i < verifyContent.size(); i++) {
                String data = DriverAction.getElementText(verifyContent.get(i));
                if (data.equals(contentData[i])) {
                    c++;
                }
            }
            if (c == contentData.length) {
                GemTestReporter.addTestStep("Verify content is created successfully", "Verified the successful creation of content.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify content is created successfully", "Could not verify the successful creation of content.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify content is created successfully","Exception encountered- "+e,STATUS.ERR);
        }


    }


    @And("^Enter assignment description \"([^\"]*)\"$")
    public void assignmentDescription(String description) {
        try{

            DriverAction.typeText(MyLocators.assignmentDescription,description);
        }catch(Exception e){

            GemTestReporter.addTestStep("Enter assignment description","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Enter respective values in assignment fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterAssignment(String assignmentTag, String marks, String duration, String category, String fileLocation) {

        try {
            List<WebElement> inputFields= DriverAction.getElements(MyLocators.assignmentInputFields);
            assignmentName= RandomStringUtils.randomAlphanumeric(10);
            String inputValues[]={assignmentName,assignmentTag,marks,duration,category,fileLocation};
            for(int i=0;i<inputFields.size()-1;i++){
                String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
                String upload=inputFields.get(i).getAttribute("type");
                //dropdown
                if(dropdown!=null&&dropdown.equals("listbox")){
                    List<WebElement>dropdownFields=DriverAction.getElements(MyLocators.dropdownIcon);

                        DriverAction.click(dropdownFields.get(i));
                    DriverAction.click(By.xpath(MyLocators.option.replace("input",inputValues[i])));
                    if(DriverAction.isDisplayed(MyLocators.crossIcon)){
                        DriverAction.click(MyLocators.crossIcon);
                    }
                }
                //file-upload
                else if(upload!=null&&upload.equals("file")){
                    DriverAction.fileUpload(inputFields.get(i),fileLocation);
                }
                //textbox
                else{

                    DriverAction.typeText(inputFields.get(i),inputValues[i]);
                }

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in assignment input fields", "Exception encountered- " + e, STATUS.ERR);
        }


    }

    @And("^Verify assignment is created \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyAssignment(String category, String tags, String marks) {
        try {
            String[] contentData = {assignmentName, category, tags, marks};
            List<WebElement> verifyContent = DriverAction.getElements(MyLocators.contentData);
            int c = 0;
            for (int i = 0; i < verifyContent.size(); i++) {
                String data = DriverAction.getElementText(verifyContent.get(i));
                if (data.equals(contentData[i])) {
                    c++;
                }
            }
            if (c == contentData.length) {
                GemTestReporter.addTestStep("Verify content is created successfully", "Verified the successful creation of content.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify content is created successfully", "Could not verify the successful creation of content.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify content is created successfully","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @When("^Click Actions icon of recently created content$")
    public void contentActionIcon() {
        DriverAction.click(MyLocators.contentActionsIcon);
    }

    @Then("^Verify content is updated successfully \"([^\"]*)\"$")
    public void verifyContentUpdates(String contentTags) {
        try {
            String[] contentData = {contentName, contentTags};
            List<WebElement> verifyContent = DriverAction.getElements(MyLocators.contentData);
            int c = 0;
            for (int i = 0; i < verifyContent.size(); i++) {
                String data = DriverAction.getElementText(verifyContent.get(i));
                if (data.equals(contentData[i])) {
                    c++;
                }
            }
            if (c == contentData.length) {
                GemTestReporter.addTestStep("Verify content is updated successfully", "Verified the successful update of content.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify content is updated successfully", "Could not verify the successful update of content.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify content is updated successfully","Exception encountered- "+e,STATUS.ERR);
        }


    }
}
