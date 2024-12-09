package com.qa.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.*;
//import com.gemini.athenaUi.locators.UserDashboard_Locator;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.gemini.gemjar.utils.ui.DriverManager;
import com.qa.athenaUi.locators.Course_Locators;
import com.qa.athenaUi.locators.MyLocators;
import com.qa.athenaUi.locators.UserDashboard_Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDashboard {
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);

static String _CourseName=" ";

@And("^Enter the \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" in Course fields$")
    public void enterCourseFields(String courseType,String duration,String fileLocation,String category)
{
    try
    {
        int c=2;
        List<WebElement> inputFields= DriverAction.getElements(Course_Locators.courseInputFields);
//        CourseName= RandomStringUtils.randomAlphanumeric(10);
        _CourseName = generateRandomCourseName(10);
        String courseTag= _CourseName+"12";
        String inputValues[]={_CourseName,courseType,duration,courseTag,fileLocation,category};
        for(int i=0;i<=5;i++){
            String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
            String upload=inputFields.get(i).getAttribute("type");
            //dropdown
            if(dropdown!=null&&dropdown.equals("listbox")){
                DriverAction.click(By.xpath(Course_Locators.dropdownIcon.replace("itr",String.valueOf(c))));
                c++;
                DriverAction.click(By.xpath(Course_Locators.option.replace("input",inputValues[i])));
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

    }
catch (Exception e) {
        GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, Status.FAIL);
    }

}

@And("^publish the course$")
    public void publishCourse()
{
try{
    //in this function we are publishing the course
    DriverAction.scrollToTop();
    if(DriverAction.isExist(Course_Locators.courseSummaryDiv))
    {
        if(DriverAction.isEnabled(By.xpath(Course_Locators.button.replace("input","Reset")))&&DriverAction.isEnabled(By.xpath(Course_Locators.button.replace("input","Save As Draft")))&&DriverAction.isEnabled(By.xpath(Course_Locators.button.replace("input","Save Course & Publish"))))
        {
            GemTestReporter.addTestStep("Initially Reset,Save As Draft,Save Course & Publish buttons should be disabled", "It is Enabled", Status.FAIL, DriverAction.takeSnapShot());
        }
        else
        {
            GemTestReporter.addTestStep("Initially Reset,Save As Draft,Save Course & Publish buttons should be disabled","It is Disabled", Status.PASS, DriverAction.takeSnapShot());

        }
        if(DriverAction.isEnabled(By.xpath(Course_Locators.button.replace("input","Default Order"))))
        {
            GemTestReporter.addTestStep("Initially Default Order button should be enabled", "It is Enabled", Status.PASS, DriverAction.takeSnapShot());
        }
        else
        {
            GemTestReporter.addTestStep("Initially Default Order button should be enabled","It is Disabled", Status.FAIL, DriverAction.takeSnapShot());
        }
    }
    //here we are checking the Default order button functionality

    List<WebElement> firstTable=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[1]//tr"));
    int firstTableSize=firstTable.size();
    if(DriverAction.isExist(By.xpath(Course_Locators.button.replace("input","Default Order")))) {
        DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Default Order")), "clicked on Default Order button", "Successfully clicked on Default Order button");
    }
    else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click on Default Order button", Status.FAIL,
                DriverAction.takeSnapShot());
    }
    List<WebElement> secondTable=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
    int secondTableSize=secondTable.size();
    if(firstTableSize==secondTableSize)
    {
        GemTestReporter.addTestStep("Data added after clicking the Default Order button", "Successfully added the data", Status.PASS, DriverAction.takeSnapShot());
    }
    else
    {
        GemTestReporter.addTestStep("Data added after clicking the Default Order button", "Data is not added", Status.FAIL, DriverAction.takeSnapShot());
    }
    //here we are checking the reset button functionality

    if(DriverAction.isExist(By.xpath(Course_Locators.button.replace("input","Reset")))) {
        DriverAction.click(By.xpath(Course_Locators.button.replace("input","Reset")),"Clicked on Reset Button","Successfully clicked on Reset button");
    }
    else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click on Reset button", Status.FAIL,
                DriverAction.takeSnapShot());
    }
    List<WebElement> tableAfterReset=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
    if(tableAfterReset.size()!=firstTableSize)
    {
        GemTestReporter.addTestStep("Data is Reset after clicking the Reset button", "Successfully get reset", Status.PASS, DriverAction.takeSnapShot());
    }
    else
    {
        GemTestReporter.addTestStep("Data is Reset after clicking the Reset button", "Not able to Reset the data", Status.FAIL, DriverAction.takeSnapShot());
    }
    if(DriverAction.isExist(By.xpath(Course_Locators.button.replace("input","Default Order")))) {
        DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Default Order")), "clicked on Default Order button", "Successfully clicked on Default Order button");
    }
    else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click on Default Order button", Status.FAIL,
                DriverAction.takeSnapShot());
    }

    if(DriverAction.isExist(By.xpath(Course_Locators.button.replace("input","Save Course & Publish"))))
    {
        DriverAction.scrollToBottom();
        DriverAction.click(UserDashboard_Locator.publishBtn,"clicked on Save Course and Publish button","Successfully clicked on Save Course and Publish button");
        DriverAction.waitSec(3);
        if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input","Yes"))))
        {
            DriverAction.click(By.xpath(Course_Locators.button.replace("input","Yes")));
//            if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input","Yes")))) {
//                DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Yes")));
//            }
            DriverAction.waitSec(5);
            DriverAction.click(Course_Locators.courseTypeDropdown);
            DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
            DriverAction.click(Course_Locators.draftOrPublishDropdown);
            DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Published")));
            DriverAction.waitSec(5);
            String fetchedPublishedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
            if(fetchedPublishedCourseName.equals(_CourseName))
            {
                GemTestReporter.addTestStep("Course is published finally","Successfully", Status.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Course is published finally","UnSuccessfully", Status.FAIL, DriverAction.takeSnapShot());
            }
        }

    }
}
catch (Exception e) {
    GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, Status.FAIL);
}
}

    @And("^Assign a Learner to the course$")
    public void assignLearnerToCourse(){

        try {
            //Assign a Learner to a course
            DriverAction.waitSec(5);
//            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);
            if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                String assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners"))));
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners")));

                    if(DriverAction.isDisplayed(Course_Locators.nameFilterInput))
                    {
                        DriverAction.waitSec(5);
                        DriverAction.typeText(Course_Locators.nameFilterInput,"saloni.nagpal@geminisolutions.com");
                        DriverAction.waitSec(5);
//                        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);
                        DriverAction.click(Course_Locators.addIcon);
                        System.out.print("check");

                    }
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }

    }

    @Then("^Validate Assigned on UserDash Board$")
    public void validateAssignUserDashBoard()
    {
        try{
//in this function we are weather assigned learner is visible on user dashboard
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdown))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Assigned"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Assigned")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Assigned option" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInput,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.learnerId))
            {
               String getLearnerId=DriverAction.getElementText(UserDashboard_Locator.learnerId);
               String actualId="saloni.nagpal@geminisolutions.com";
               if(actualId.equals(getLearnerId))
               {
                   GemTestReporter.addTestStep("Validate Learner is assigned properly", "Learner is assigned successfully" , Status.PASS,
                           DriverAction.takeSnapShot());
               }
               else
               {
                   GemTestReporter.addTestStep("Validate Learner is assigned properly", "Learner is not  assigned successfully" , Status.FAIL,
                           DriverAction.takeSnapShot());
               }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }




        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }
@And("^Unassigned a leaner to the course$")
    public void unassignedLearner()
{
    try{
//        DriverAction.waitSec(5);
//        if(DriverAction.isExist(UserDashboard_Locator.courseFilterInput))
//        {
//            DriverAction.typeText(UserDashboard_Locator.courseFilterInput,CourseName);
//        }
//        if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
//            String assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
//            DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
//            if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners"))));
//            {
//                DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners")));
                DriverAction.waitSec(5);
                if(DriverAction.isExist(UserDashboard_Locator.deleteIcon))
                {
                    DriverAction.click(UserDashboard_Locator.deleteIcon);
                }
//            }
//        }

    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
    }
}
    @Then("^Validate Unassigned on UserDash Board$")
    public void validateUnAssignUserDashBoard()
    {
        try{
// we are validating unassigned learner on user dashboard
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdown))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Unassigned"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Unassigned")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Unassigned option" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInput,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.learnerId))
            {
                String getLearnerId=DriverAction.getElementText(UserDashboard_Locator.learnerId);
                String actualId="rahul44@gmail.com";
                if(actualId.equals(getLearnerId))
                {
                    GemTestReporter.addTestStep("Validate Learner is Unassigned properly", "Learner is Unassigned successfully" , Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Learner is Unassigned properly", "Learner is not Unassigned successfully" , Status.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }




        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }

    @And("^Manually completed course$")
    public void manuallyCompleted(){
    try{
        DriverAction.click(By.xpath(MyLocators.backBtnIcon.replace("input", "Back")));
   
        //in this we are manually completing the course
        DriverAction.waitSec(3);
if(DriverAction.isExist(UserDashboard_Locator.courseFilterInput))
{
    DriverAction.typeText(UserDashboard_Locator.courseFilterInput,_CourseName);
}
else {
    GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
            DriverAction.takeSnapShot());
}
        DriverAction.waitSec(3);
        if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
            String assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
            DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
            if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Learner Reports"))));
            {
                DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Learner Reports")));
            }
        }
     //Manually complete the course

        if(DriverAction.isExist(Course_Locators.editIcon))
        {
            DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.label.replace("input","Complete Course"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.label.replace("input","Complete Course")));
                DriverAction.waitSec(3);
                String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                String reqPopUpMessage="Course Completed Manually";
                System.out.println("hello");
                System.out.println(popupMessage);
                System.out.println("hello");
                if(reqPopUpMessage.equals(popupMessage))
                {
                    GemTestReporter.addTestStep("Validate Course is completed Manually", "Course is successfully completed Manually" , Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Error Occur", "Fail to complete the course manually" , Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                    DriverAction.takeSnapShot());
        }
    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
    }
    }
    @Then("^Validate Manually Completed on User Dashboard$")
    public void validateManuallyCompleted()
    {
        try{
            //validating manually completed course
            DriverAction.waitSec(3);
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdown))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Manually Completed"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Manually Completed")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Assigned option" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInput,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.learnerId))
            {
                String getLearnerId=DriverAction.getElementText(UserDashboard_Locator.learnerId);
                String actualId="rahul44@gmail.com";
                if(actualId.equals(getLearnerId))
                {
                    GemTestReporter.addTestStep("Validate Learner is assigned properly", "Learner successfully Completed Course Manually" , Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Learner is assigned properly", "Learner not able to Complete Course Manually" , Status.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }

    @And("^Reattempt the course$")
    public void reattemptCourse()
    {
        try{
            // reattempt  the once completed course
            DriverAction.waitSec(5);
            if(DriverAction.isExist(Course_Locators.editIcon))
            {
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isExist(By.xpath(UserDashboard_Locator.label.replace("input","Reattempt Course"))))
                {
                    DriverAction.click(By.xpath(UserDashboard_Locator.label.replace("input","Reattempt Course")));
                    DriverAction.waitSec(3);
                    String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                    String reqPopUpMessage="Learner can reattempt course";
                    if(reqPopUpMessage.equals(popupMessage))
                    {
                        GemTestReporter.addTestStep("Validate Course is change to Reattempt", "Course is successfully changed to Reattempt" , Status.PASS,
                                DriverAction.takeSnapShot());
                    }
                    else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to change the Reattempt" , Status.FAIL,
                                DriverAction.takeSnapShot());
                    }
                }
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }
    @Then("^Validate Reattempt on UserDash Board$")
    public void validateReattempt()
    {
        try{
            // validate reattempt course
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdown))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Reattempt"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Reattempt")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Reattempt option" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInput,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.learnerId))
            {
                String getLearnerId=DriverAction.getElementText(UserDashboard_Locator.learnerId);
                String actualId="rahul44@gmail.com";
                if(actualId.equals(getLearnerId))
                {
                    GemTestReporter.addTestStep("Validate Learner is Reattempt properly", "Learner is Reattempt successfully" , Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Learner is Reattempt properly", "Learner is not  Reattempt successfully" , Status.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }
    @Then("^Validate Course Created on User Dashboard$")
    public void validateCourseDashboard()
    {
        try{
            //in this function we are validating once we create a new course it is visible on user dashboard or not.
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            DriverAction.scrollToBottom();
            js.executeScript("window.scrollTo(0, arguments[0]);",500);
            DriverAction.waitSec(5);
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdownCourse))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdownCourse);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Create"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Create")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Create option" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInputCourse,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.courseName))
            {
                String getCourse=DriverAction.getElementText(UserDashboard_Locator.courseName);
                if(_CourseName.equals(getCourse))
                {
                    GemTestReporter.addTestStep("Validate Course Created is present on User Dashboard", "Course is present on User Dashboard" , Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course Created is present on User Dashboard", "Course is present on User Dashboard" , Status.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }

    }
    @And("^Update the course$")
    public void updateCourse(){
    try{
        // in this function we are updating the course
        if(DriverAction.isExist(UserDashboard_Locator.courseFilterInput))
        {
            DriverAction.typeText(UserDashboard_Locator.courseFilterInput,_CourseName);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
            String assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
            DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
            if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Edit"))));
            {
                DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Edit")));
            }
        }
        DriverAction.waitSec(3);
        if (DriverAction.isExist(UserDashboard_Locator.courseNameInput)) {
            DriverAction.typeText(UserDashboard_Locator.courseNameInput,"Edit "+_CourseName);
        }
        DriverAction.scrollToBottom();
        if(DriverAction.isExist(UserDashboard_Locator.addContentBtn))
        {
            DriverAction.click(UserDashboard_Locator.addContentBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on Add Content Button" , Status.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.addToCourseBtn))
        {
            DriverAction.click(UserDashboard_Locator.addToCourseBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on Add To Course Button" , Status.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.addToCourseBtn))
        {
            DriverAction.click(UserDashboard_Locator.addToCourseBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on Add To Course Button" , Status.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.resetBtn))
        {
            DriverAction.click(UserDashboard_Locator.resetBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on reset Button" , Status.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.defaultOrderBtn))
        {
            DriverAction.click(UserDashboard_Locator.defaultOrderBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on default Order Button" , Status.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.updatePublishBtn))
        {
            DriverAction.click(UserDashboard_Locator.updatePublishBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on Update course and Publish button" , Status.FAIL,
                    DriverAction.takeSnapShot());
        }
        if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input","Yes")))) {
            DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Yes")));
            if (DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input", "Yes")))) {
                DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Yes")));
            }
        }
        DriverAction.waitSec(5);
    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
    }
    }
    @And("^Delete the course$")
    public void deleteCourse(){
        try{
            //in this function we are deleting the course
            if(DriverAction.isExist(UserDashboard_Locator.courseFilterInput))
            {
                DriverAction.typeText(UserDashboard_Locator.courseFilterInput,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(3);
            if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                String assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Delete"))));
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Delete")));
                }
            }

            if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input","Yes")))) {
                DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Yes")));
            }
            DriverAction.waitSec(5);
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }
    @Then("^Validate Course Updated on User Dashboard$")
    public void validateUpdateCourseDashboard()
    {
        try{
            // we are validating updated course is visible on user dashboard
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            DriverAction.scrollToBottom();
            js.executeScript("window.scrollTo(0, arguments[0]);",500);
            DriverAction.waitSec(5);
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdownCourse))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdownCourse);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Update"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Update")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Update option" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInputCourse,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.courseName))
            {
                String getCourse=DriverAction.getElementText(UserDashboard_Locator.courseName);
                String courseName="Edit "+_CourseName;
                if(courseName.equals(getCourse))
                {
                    GemTestReporter.addTestStep("Validate Course Updated is present on User Dashboard", "Course is present on User Dashboard" , Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course Updated is present on User Dashboard", "Course is present on User Dashboard" , Status.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }

    }
    @Then("^Validate Course Deleted on User Dashboard$")
    public void validateDeleteCourseDashboard()
    {
        try{
            // we are validating once the course is deleted it is visible on user dashboard
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            DriverAction.scrollToBottom();
            js.executeScript("window.scrollTo(0, arguments[0]);",500);
            DriverAction.waitSec(5);
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdownCourse))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdownCourse);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Delete"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Delete")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Delete option" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInputCourse,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.courseName))
            {
                String getCourse=DriverAction.getElementText(UserDashboard_Locator.courseName);

                if(_CourseName.equals(getCourse))
                {
                    GemTestReporter.addTestStep("Validate Course Delete is present on User Dashboard", "Course is present on User Dashboard" , Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course Delete is present on User Dashboard", "Course is present on User Dashboard" , Status.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }

    }
    @Then("^Validate \"([^\"]*)\" on User Dashboard$")
    public void validateCourseDetailsUserDashboard(String courseOption)
    {
        try{
            // we are validating course detail on user dashboard
            DriverAction.waitSec(5);
            DriverAction.scrollToBottom();
            if(DriverAction.isExist(UserDashboard_Locator.searchInputLibrary))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInputLibrary,_CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(3);
            if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                String assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input",courseOption))));
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input",courseOption)));
                }
            }
            DriverAction.waitSec(3);
            DriverAction.scrollToTop();
            if(DriverAction.isExist(UserDashboard_Locator.pageHeading)) {
                String fetchedHeading = DriverAction.getElementText(UserDashboard_Locator.pageHeading);
                System.out.println(fetchedHeading);
                switch (courseOption) {
                    case "Course Summary":
                        String heading="Course Summary: "+_CourseName;
                       if(heading.equals(fetchedHeading))
                       {
                           GemTestReporter.addTestStep("Validate Course Summary page opened", "Course Summary page opened successfully" , Status.PASS,
                                   DriverAction.takeSnapShot());
                       }
                       else {
                           GemTestReporter.addTestStep("Validate Course Summary page opened", "Failed to open Course Summary page " , Status.FAIL,
                                   DriverAction.takeSnapShot());
                       }
                        break;
                    case "Assign Learners":
                        String heading1="Assign Learners: "+_CourseName;
                        if(heading1.equals(fetchedHeading))
                        {
                            GemTestReporter.addTestStep("Validate Learner Report page opened", "Learner Report page opened successfully" , Status.PASS,
                                    DriverAction.takeSnapShot());
                        }
                        else {
                            GemTestReporter.addTestStep("Validate Learner Report page opened", "Failed to open Learner Report page " , Status.FAIL,
                                    DriverAction.takeSnapShot());
                        }
                        break;
                    case "Learner Reports":
                        String heading2="View Learner(s) Report: "+_CourseName;
                        if(heading2.contains(fetchedHeading))
                        {
                            GemTestReporter.addTestStep("Validate Learner Report page opened", "Learner Report page opened successfully" , Status.PASS,
                                    DriverAction.takeSnapShot());
                        }
                        else {
                            GemTestReporter.addTestStep("Validate Learner Report page opened", "Failed to open Learner Report page " , Status.FAIL,
                                    DriverAction.takeSnapShot());
                        }

                        break;
                    case "Edit":
                        String heading3="Create Course";
                        if(heading3.trim().equals(fetchedHeading.trim()))
                        {
                            GemTestReporter.addTestStep("Validate Create Course page opened", "Create Course page opened successfully" , Status.PASS,
                                    DriverAction.takeSnapShot());
                        }
                        else {
                            GemTestReporter.addTestStep("Validate Create Course page opened", "Failed to open Create Course page " , Status.FAIL,
                                    DriverAction.takeSnapShot());
                        }

                        break;

                }
            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to fetch the heading" , Status.FAIL,
                        DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }
    public static String generateRandomCourseName(int length) {
    //in this function we are generating a random name
        String courseName = RandomStringUtils.randomAlphabetic(1); // Ensure the first character is a letter
        courseName += RandomStringUtils.randomAlphanumeric(length - 1); // Generate the rest of the string
        return courseName;
    }
}
