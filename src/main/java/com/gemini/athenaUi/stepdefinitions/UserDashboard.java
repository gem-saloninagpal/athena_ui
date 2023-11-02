package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.*;
//import com.gemini.athenaUi.locators.UserDashboard_Locator;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
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

static String CourseName=" ";

@And("^Enter the \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" in Course fields$")
    public void enterCourseFields(String courseType,String duration,String fileLocation,String category)
{
    try
    {
        int c=2;
        List<WebElement> inputFields= DriverAction.getElements(Course_Locators.courseInputFields);
//        CourseName= RandomStringUtils.randomAlphanumeric(10);
        CourseName = generateRandomCourseName(10);
        String courseTag= CourseName+"12";
        String inputValues[]={CourseName,courseType,duration,courseTag,fileLocation,category};
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
        GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
    }

}

@And("^publish the course$")
    public void publishCourse()
{
try{
    DriverAction.scrollToTop();
    if(DriverAction.isExist(Course_Locators.courseSummaryDiv,120))
    {
        if(DriverAction.isEnabled(By.xpath(Course_Locators.button.replace("input","Reset")))&&DriverAction.isEnabled(By.xpath(Course_Locators.button.replace("input","Save As Draft")))&&DriverAction.isEnabled(By.xpath(Course_Locators.button.replace("input","Save Course & Publish"))))
        {
            GemTestReporter.addTestStep("Initially Reset,Save As Draft,Save Course & Publish buttons should be disabled", "It is Enabled", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        else
        {
            GemTestReporter.addTestStep("Initially Reset,Save As Draft,Save Course & Publish buttons should be disabled","It is Disabled", STATUS.PASS, DriverAction.takeSnapShot());

        }
        if(DriverAction.isEnabled(By.xpath(Course_Locators.button.replace("input","Default Order"))))
        {
            GemTestReporter.addTestStep("Initially Default Order button should be enabled", "It is Enabled", STATUS.PASS, DriverAction.takeSnapShot());
        }
        else
        {
            GemTestReporter.addTestStep("Initially Default Order button should be enabled","It is Disabled", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
    //here we are checking the Default order button functionality

    List<WebElement> firstTable=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[1]//tr"));
    int firstTableSize=firstTable.size();
    if(DriverAction.isExist(By.xpath(Course_Locators.button.replace("input","Default Order")))) {
        DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Default Order")), "clicked on Default Order button", "Successfully clicked on Default Order button");
    }
    else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click on Default Order button", STATUS.FAIL,
                DriverAction.takeSnapShot());
    }
    List<WebElement> secondTable=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
    int secondTableSize=secondTable.size();
    if(firstTableSize==secondTableSize)
    {
        GemTestReporter.addTestStep("Data added after clicking the Default Order button", "Successfully added the data", STATUS.PASS, DriverAction.takeSnapShot());
    }
    else
    {
        GemTestReporter.addTestStep("Data added after clicking the Default Order button", "Data is not added", STATUS.FAIL, DriverAction.takeSnapShot());
    }
    //here we are checking the reset button functionality

    if(DriverAction.isExist(By.xpath(Course_Locators.button.replace("input","Reset")))) {
        DriverAction.click(By.xpath(Course_Locators.button.replace("input","Reset")),"Clicked on Reset Button","Successfully clicked on Reset button");
    }
    else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click on Reset button", STATUS.FAIL,
                DriverAction.takeSnapShot());
    }
    List<WebElement> tableAfterReset=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
    if(tableAfterReset.size()!=firstTableSize)
    {
        GemTestReporter.addTestStep("Data is Reset after clicking the Reset button", "Successfully get reset", STATUS.PASS, DriverAction.takeSnapShot());
    }
    else
    {
        GemTestReporter.addTestStep("Data is Reset after clicking the Reset button", "Not able to Reset the data", STATUS.FAIL, DriverAction.takeSnapShot());
    }
    if(DriverAction.isExist(By.xpath(Course_Locators.button.replace("input","Default Order")))) {
        DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Default Order")), "clicked on Default Order button", "Successfully clicked on Default Order button");
    }
    else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click on Default Order button", STATUS.FAIL,
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
            if(fetchedPublishedCourseName.equals(CourseName))
            {
                GemTestReporter.addTestStep("Course is published finally","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Course is published finally","UnSuccessfully", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }

    }
}
catch (Exception e) {
    GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
}
}

    @And("^Assign a Learner to the course$")
    public void assignLearnerToCourse(){

        try {
            //Assign a Learner to a course
            DriverAction.waitSec(5);
//            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
            if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                String assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners"))));
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners")));

                    if(DriverAction.isDisplayed(Course_Locators.nameFilterInput))
                    {
                        DriverAction.waitSec(5);
                        DriverAction.typeText(Course_Locators.nameFilterInput,"rahul44@gmail.com");
                        DriverAction.waitSec(5);
//                        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                        DriverAction.click(Course_Locators.addIcon);

                    }
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    @Then("^Validate Assigned on UserDash Board$")
    public void validateAssignUserDashBoard()
    {
        try{

            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdown,120))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Assigned"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Assigned")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Assigned option" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput,120))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInput,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.learnerId,120))
            {
               String getLearnerId=DriverAction.getElementText(UserDashboard_Locator.learnerId);
               String actualId="rahul44@gmail.com";
               if(actualId.equals(getLearnerId))
               {
                   GemTestReporter.addTestStep("Validate Learner is assigned properly", "Learner is assigned successfully" , STATUS.PASS,
                           DriverAction.takeSnapShot());
               }
               else
               {
                   GemTestReporter.addTestStep("Validate Learner is assigned properly", "Learner is not  assigned successfully" , STATUS.FAIL,
                           DriverAction.takeSnapShot());
               }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }




        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }
@And("^Unassigned a leaner to the course$")
    public void unassignedLearner()
{
    try{
//        DriverAction.waitSec(5);
//        if(DriverAction.isExist(UserDashboard_Locator.courseFilterInput,120))
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
                if(DriverAction.isExist(UserDashboard_Locator.deleteIcon,120))
                {
                    DriverAction.click(UserDashboard_Locator.deleteIcon);
                }
//            }
//        }

    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
    }
}
    @Then("^Validate Unassigned on UserDash Board$")
    public void validateUnAssignUserDashBoard()
    {
        try{

            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdown,120))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Unassigned"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Unassigned")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Unassigned option" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput,120))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInput,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.learnerId,120))
            {
                String getLearnerId=DriverAction.getElementText(UserDashboard_Locator.learnerId);
                String actualId="rahul44@gmail.com";
                if(actualId.equals(getLearnerId))
                {
                    GemTestReporter.addTestStep("Validate Learner is Unassigned properly", "Learner is Unassigned successfully" , STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Learner is Unassigned properly", "Learner is not Unassigned successfully" , STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }




        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("^Manually completed course$")
    public void manuallyCompleted(){
    try{
        DriverAction.waitSec(3);
if(DriverAction.isExist(UserDashboard_Locator.courseFilterInput,120))
{
    DriverAction.typeText(UserDashboard_Locator.courseFilterInput,CourseName);
}
else {
    GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
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

        if(DriverAction.isExist(Course_Locators.editIcon,120))
        {
            DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.label.replace("input","Complete Course")),120))
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
                    GemTestReporter.addTestStep("Validate Course is completed Manually", "Course is successfully completed Manually" , STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Error Occur", "Fail to complete the course manually" , STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                    DriverAction.takeSnapShot());
        }
    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
    }
    }
    @Then("^Validate Manually Completed on User Dashboard$")
    public void validateManuallyCompleted()
    {
        try{
            DriverAction.waitSec(3);
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdown,120))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Manually Completed"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Manually Completed")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Assigned option" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput,120))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInput,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.learnerId,120))
            {
                String getLearnerId=DriverAction.getElementText(UserDashboard_Locator.learnerId);
                String actualId="rahul44@gmail.com";
                if(actualId.equals(getLearnerId))
                {
                    GemTestReporter.addTestStep("Validate Learner is assigned properly", "Learner successfully Completed Course Manually" , STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Learner is assigned properly", "Learner not able to Complete Course Manually" , STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("^Reattempt the course$")
    public void reattemptCourse()
    {
        try{
DriverAction.waitSec(5);
            if(DriverAction.isExist(Course_Locators.editIcon,120))
            {
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isExist(By.xpath(UserDashboard_Locator.label.replace("input","Reattempt Course")),120))
                {
                    DriverAction.click(By.xpath(UserDashboard_Locator.label.replace("input","Reattempt Course")));
                    DriverAction.waitSec(3);
                    String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                    String reqPopUpMessage="Learner can reattempt course";
                    if(reqPopUpMessage.equals(popupMessage))
                    {
                        GemTestReporter.addTestStep("Validate Course is change to Reattempt", "Course is successfully changed to Reattempt" , STATUS.PASS,
                                DriverAction.takeSnapShot());
                    }
                    else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to change the Reattempt" , STATUS.FAIL,
                                DriverAction.takeSnapShot());
                    }
                }
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }
    @Then("^Validate Reattempt on UserDash Board$")
    public void validateReattempt()
    {
        try{
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdown,120))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Reattempt"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Reattempt")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Reattempt option" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput,120))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInput,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.learnerId,120))
            {
                String getLearnerId=DriverAction.getElementText(UserDashboard_Locator.learnerId);
                String actualId="rahul44@gmail.com";
                if(actualId.equals(getLearnerId))
                {
                    GemTestReporter.addTestStep("Validate Learner is Reattempt properly", "Learner is Reattempt successfully" , STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Learner is Reattempt properly", "Learner is not  Reattempt successfully" , STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }
    @Then("^Validate Course Created on User Dashboard$")
    public void validateCourseDashboard()
    {
        try{
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            DriverAction.scrollToBottom();
            js.executeScript("window.scrollTo(0, arguments[0]);",500);
            DriverAction.waitSec(5);
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdownCourse,120))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdownCourse);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Create"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Create")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Create option" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput,120))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInputCourse,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.courseName,120))
            {
                String getCourse=DriverAction.getElementText(UserDashboard_Locator.courseName);
                if(CourseName.equals(getCourse))
                {
                    GemTestReporter.addTestStep("Validate Course Created is present on User Dashboard", "Course is present on User Dashboard" , STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course Created is present on User Dashboard", "Course is present on User Dashboard" , STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }
    @And("^Update the course$")
    public void updateCourse(){
    try{
        if(DriverAction.isExist(UserDashboard_Locator.courseFilterInput,120))
        {
            DriverAction.typeText(UserDashboard_Locator.courseFilterInput,CourseName);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
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
        if (DriverAction.isExist(UserDashboard_Locator.courseNameInput, 120)) {
            DriverAction.typeText(UserDashboard_Locator.courseNameInput,"Edit "+CourseName);
        }
        DriverAction.scrollToBottom();
        if(DriverAction.isExist(UserDashboard_Locator.addContentBtn,120))
        {
            DriverAction.click(UserDashboard_Locator.addContentBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on Add Content Button" , STATUS.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.addToCourseBtn,120))
        {
            DriverAction.click(UserDashboard_Locator.addToCourseBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on Add To Course Button" , STATUS.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.addToCourseBtn,120))
        {
            DriverAction.click(UserDashboard_Locator.addToCourseBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on Add To Course Button" , STATUS.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.resetBtn,120))
        {
            DriverAction.click(UserDashboard_Locator.resetBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on reset Button" , STATUS.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.defaultOrderBtn,120))
        {
            DriverAction.click(UserDashboard_Locator.defaultOrderBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on default Order Button" , STATUS.FAIL,
                    DriverAction.takeSnapShot());
        }
        DriverAction.waitSec(3);
        if(DriverAction.isExist(UserDashboard_Locator.updatePublishBtn,120))
        {
            DriverAction.click(UserDashboard_Locator.updatePublishBtn);
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on Update course and Publish button" , STATUS.FAIL,
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
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
    }
    }
    @And("^Delete the course$")
    public void deleteCourse(){
        try{
            if(DriverAction.isExist(UserDashboard_Locator.courseFilterInput,120))
            {
                DriverAction.typeText(UserDashboard_Locator.courseFilterInput,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
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
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }
    @Then("^Validate Course Updated on User Dashboard$")
    public void validateUpdateCourseDashboard()
    {
        try{
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            DriverAction.scrollToBottom();
            js.executeScript("window.scrollTo(0, arguments[0]);",500);
            DriverAction.waitSec(5);
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdownCourse,120))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdownCourse);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Update"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Update")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Update option" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput,120))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInputCourse,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.courseName,120))
            {
                String getCourse=DriverAction.getElementText(UserDashboard_Locator.courseName);
                String courseName="Edit "+CourseName;
                if(courseName.equals(getCourse))
                {
                    GemTestReporter.addTestStep("Validate Course Updated is present on User Dashboard", "Course is present on User Dashboard" , STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course Updated is present on User Dashboard", "Course is present on User Dashboard" , STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }
    @Then("^Validate Course Deleted on User Dashboard$")
    public void validateDeleteCourseDashboard()
    {
        try{
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            DriverAction.scrollToBottom();
            js.executeScript("window.scrollTo(0, arguments[0]);",500);
            DriverAction.waitSec(5);
            if(DriverAction.isExist(UserDashboard_Locator.actionTakenDropdownCourse,120))
            {
                DriverAction.click(UserDashboard_Locator.actionTakenDropdownCourse);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on action taken dropdown", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(By.xpath(UserDashboard_Locator.action.replace("input","Delete"))))
            {
                DriverAction.click(By.xpath(UserDashboard_Locator.action.replace("input","Delete")));
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Delete option" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(UserDashboard_Locator.searchInput,120))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInputCourse,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(UserDashboard_Locator.courseName,120))
            {
                String getCourse=DriverAction.getElementText(UserDashboard_Locator.courseName);

                if(CourseName.equals(getCourse))
                {
                    GemTestReporter.addTestStep("Validate Course Delete is present on User Dashboard", "Course is present on User Dashboard" , STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course Delete is present on User Dashboard", "Course is present on User Dashboard" , STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }
    @Then("^Validate \"([^\"]*)\" on User Dashboard$")
    public void validateCourseDetailsUserDashboard(String courseOption)
    {
        try{
            DriverAction.waitSec(5);
            DriverAction.scrollToBottom();
            if(DriverAction.isExist(UserDashboard_Locator.searchInputLibrary,120))
            {
                DriverAction.typeText(UserDashboard_Locator.searchInputLibrary,CourseName);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Search by Course Name filter input" , STATUS.FAIL,
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
            if(DriverAction.isExist(UserDashboard_Locator.pageHeading,120)) {
                String fetchedHeading = DriverAction.getElementText(UserDashboard_Locator.pageHeading);
                System.out.println(fetchedHeading);
                switch (courseOption) {
                    case "Course Summary":
                        String heading="Course Summary: "+CourseName;
                       if(heading.equals(fetchedHeading))
                       {
                           GemTestReporter.addTestStep("Validate Course Summary page opened", "Course Summary page opened successfully" , STATUS.PASS,
                                   DriverAction.takeSnapShot());
                       }
                       else {
                           GemTestReporter.addTestStep("Validate Course Summary page opened", "Failed to open Course Summary page " , STATUS.FAIL,
                                   DriverAction.takeSnapShot());
                       }
                        break;
                    case "Assign Learners":
                        String heading1="Assign Learners: "+CourseName;
                        if(heading1.equals(fetchedHeading))
                        {
                            GemTestReporter.addTestStep("Validate Learner Report page opened", "Learner Report page opened successfully" , STATUS.PASS,
                                    DriverAction.takeSnapShot());
                        }
                        else {
                            GemTestReporter.addTestStep("Validate Learner Report page opened", "Failed to open Learner Report page " , STATUS.FAIL,
                                    DriverAction.takeSnapShot());
                        }
                        break;
                    case "Learner Reports":
                        String heading2="View Learner(s) Report: "+CourseName;
                        if(heading2.contains(fetchedHeading))
                        {
                            GemTestReporter.addTestStep("Validate Learner Report page opened", "Learner Report page opened successfully" , STATUS.PASS,
                                    DriverAction.takeSnapShot());
                        }
                        else {
                            GemTestReporter.addTestStep("Validate Learner Report page opened", "Failed to open Learner Report page " , STATUS.FAIL,
                                    DriverAction.takeSnapShot());
                        }

                        break;
                    case "Edit":
                        String heading3="Create Course";
                        if(heading3.trim().equals(fetchedHeading.trim()))
                        {
                            GemTestReporter.addTestStep("Validate Create Course page opened", "Create Course page opened successfully" , STATUS.PASS,
                                    DriverAction.takeSnapShot());
                        }
                        else {
                            GemTestReporter.addTestStep("Validate Create Course page opened", "Failed to open Create Course page " , STATUS.FAIL,
                                    DriverAction.takeSnapShot());
                        }

                        break;

                }
            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to fetch the heading" , STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }




//            if(DriverAction.isExist(UserDashboard_Locator.courseName,120))
//            {
//                String getCourse=DriverAction.getElementText(UserDashboard_Locator.courseName);
//
//                if(CourseName.equals(getCourse))
//                {
//                    GemTestReporter.addTestStep("Validate Course Delete is present on User Dashboard", "Course is present on User Dashboard" , STATUS.PASS,
//                            DriverAction.takeSnapShot());
//                }
//                else
//                {
//                    GemTestReporter.addTestStep("Validate Course Delete is present on User Dashboard", "Course is present on User Dashboard" , STATUS.FAIL,
//                            DriverAction.takeSnapShot());
//                }
//
//            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }
    public static String generateRandomCourseName(int length) {
        String courseName = RandomStringUtils.randomAlphabetic(1); // Ensure the first character is a letter
        courseName += RandomStringUtils.randomAlphanumeric(length - 1); // Generate the rest of the string
        return courseName;
    }
}
