package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.*;
//import com.gemini.athenaUi.locators.LearnerModule_Locators;
//import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.athenaUi.locators.Tests_TestControl_Locators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import com.gemini.athenaUi.stepdefinitions.Tests_TestControl;

public class Course {
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);
    String _courseName=" ";
    String _assignedCourseName=" ";
    int _ActiveCourseCount=0;
    int _viewListActiveCount=0;

    String _firstCourseName="";

    Tests_TestControl test = new Tests_TestControl();
    @Then("^Verify the Owner of the Created Course$")
    public void verifyOwner(){
        try{
            //In this function we are verifying the actual owner of course with given owner.
            if(DriverAction.isDisplayed(Course_Locators.navbarDropdown))
            {
                DriverAction.click(Course_Locators.navbarDropdown, "Click on the Dropdown button for Profile", "successfully clicked Dropdown button.");
                if(DriverAction.isDisplayed(Course_Locators.profile))
                {
                    DriverAction.click(Course_Locators.profile, "Click on Profile Option", "successfully select Profile option.");

                    if(DriverAction.isDisplayed(Course_Locators.userName))
                    {
                        String userName=DriverAction.getElementText(Course_Locators.userName);
                        String[] userNameString=userName.split("@");
                        String req_userName=userNameString[0];
                        DriverAction.navigateBack();
                        if(DriverAction.isDisplayed(Course_Locators.ownerName))
                        {
                            String ownerName=DriverAction.getElementText(Course_Locators.ownerName);
                            if(ownerName.equals(req_userName))
                            {
                                GemTestReporter.addTestStep("Verify the owner", "Successful Matched "+req_userName+" with "+ownerName, STATUS.PASS, DriverAction.takeSnapShot());
                            }
                            else
                            {
                                GemTestReporter.addTestStep("Verify the owner", "UnSuccessful Matched"+req_userName+" with "+ownerName, STATUS.FAIL, DriverAction.takeSnapShot());

                            }
                        }

                    }
                }
            }
            else
            {
                GemTestReporter.addTestStep("Profile dropdown visibility", "No Profile dropdown is there on ui", STATUS.FAIL, DriverAction.takeSnapShot());

            }



        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }
    @Then("^Verify course info tree for assignment and test checkbox$")
    public void treeInfo(){
        try{
//            List<WebElement> list1 = DriverAction.getElements(By.xpath(LearnerModule_Locators.courseArea.replace("itr", String.valueOf(i))));
           //in this function we are checking the course info tree
           boolean isPresent=false;
           boolean isPresent1=false;
           DriverAction.waitUntilElementAppear(By.xpath("//span[text()='Add Course Info']"),120);
            DriverAction.scrollToBottom();
             for(int i=1;i<=2;i++)
            {
                DriverAction.click(Course_Locators.checkboxs);
            }
            List<WebElement> TreeList=DriverAction.getElements(Course_Locators.treeList);
            for(int i=1;i<=TreeList.size();i++)
            {
               String fetchedItem=DriverAction.getElementText(By.xpath(Course_Locators.tree.replace("itr",String.valueOf(i)))) ;
               if(fetchedItem.equals("Add Assignment"))
               {
                   isPresent=true;
                }

               if(fetchedItem.equals("Add Test"))
               {
                   isPresent1=true;
               }

            }
            if(isPresent!=true)
            {
                GemTestReporter.addTestStep("Verify Add Assignment is added after selecting the checkbox","Unsuccessfull", STATUS.FAIL, DriverAction.takeSnapShot());

            }
            else
            {
                GemTestReporter.addTestStep("Verify Add Assignment is added after selecting the checkbox", "Successfully selected ", STATUS.PASS, DriverAction.takeSnapShot());

            }
            if(isPresent1!=true)
            {
                GemTestReporter.addTestStep("Verify Add Test is added after selecting the checkbox","Unsuccessfull", STATUS.FAIL, DriverAction.takeSnapShot());

            }
            else
            {
                GemTestReporter.addTestStep("Verify Add Test is added after selecting the checkbox","Successfull", STATUS.PASS, DriverAction.takeSnapShot());

            }

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
    @Then("^Enter respective values in course fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterAssignment(String courseType, String duration,String courseTag, String fileLocation, String category) {
    try{
        int c=2;
        List<WebElement> inputFields= DriverAction.getElements(Course_Locators.courseInputFields);
         _courseName = generateRandomCourseName(10);
//        courseName= RandomStringUtils.randomAlphanumeric(10);
        String inputValues[]={_courseName,courseType,duration,courseTag,fileLocation,category};
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
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
    }
    }
    @And("^Enter course description \"([^\"]*)\"$")
    public void courseDescription(String description) {
        try{
            //in this we are filling the description
            if (DriverAction.isExist(Course_Locators.courseDescription,120)) {
                DriverAction.typeText(Course_Locators.courseDescription,description,"Validate user able to type in Course description input","User successfully typed "+description);
                DriverAction.scrollToBottom();
            }
            else {
            GemTestReporter.addTestStep("Error Occur", "Fail to enter text in course description", STATUS.FAIL,
                    DriverAction.takeSnapShot());
        }
            if (DriverAction.isExist(Course_Locators.checkboxs,120)) {
                DriverAction.click(Course_Locators.checkboxs);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on checkbox", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

        }catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Validate Filter functionality \"([^\"]*)\"$")
    public void filterData(String Name){
       try{
           //In this we are filter the data and verifying weather we are getting the expected result or not
           int c=0;
           if (DriverAction.isExist(Course_Locators.addContentTagInput,120))
           {
               DriverAction.typeText(Course_Locators.addContentTagInput,Name);
           }
//           DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
           DriverAction.waitSec(5);

           List<WebElement> addContentTable=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[1]//tr"));

           for(int i=1;i<addContentTable.size();i++)
           {
               String contentName=DriverAction.getElementText(By.xpath(Course_Locators.addContentTableRow.replace("itr",String.valueOf(i))));
               if(contentName.equals(Name))
               {
                   c++;
               }

           }
           if(c==addContentTable.size()-1)
           {
               GemTestReporter.addTestStep("Verify Filter by Content/Tag is working properly","Working Fine", STATUS.PASS, DriverAction.takeSnapShot());
           }
           else
           {
               GemTestReporter.addTestStep("Verify Filter by Content/Tag is working properly","Not working properly", STATUS.FAIL, DriverAction.takeSnapShot());
           }

           try {
               // Create an instance of the Robot class
               Robot robot = new Robot();

               // Simulate Ctrl+A (Select All)
               robot.keyPress(KeyEvent.VK_CONTROL); // Press Ctrl key
               robot.keyPress(KeyEvent.VK_A); // Press 'A' key
               robot.keyRelease(KeyEvent.VK_A); // Release 'A' key
               robot.keyRelease(KeyEvent.VK_CONTROL); // Release Ctrl key

               // Simulate Ctrl+X (Cut)
               robot.keyPress(KeyEvent.VK_CONTROL); // Press Ctrl key
               robot.keyPress(KeyEvent.VK_X); // Press 'X' key
               robot.keyRelease(KeyEvent.VK_X); // Release 'X' key
               robot.keyRelease(KeyEvent.VK_CONTROL); // Release Ctrl key

           } catch (AWTException e) {
               e.printStackTrace();
           }
       }
       catch (Exception e) {
           logger.info("Exception occurred", e);
           GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
       }
    }
@Then("^Validate \"([^\"]*)\" Add to Course \"([^\"]*)\"$")
    public void addToCourse(String type,String message){
      try{
          //In this function we are first verifying the Add to course div is empty if not we are first deleting it.
          //And after that we are adding to Course.
          if (DriverAction.isExist(Course_Locators.addToCourseDiv,120))
{
    if (DriverAction.isExist(Course_Locators.addToCourseBtn,120))
    {
        if(DriverAction.isEnabled(Course_Locators.addToCourseBtn))
        {
            GemTestReporter.addTestStep("Initially Add to Course Btn should be disabled", "It is Enabled", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        else
        {

            GemTestReporter.addTestStep("Initially Add to Course Btn should be disabled","It is Disabled", STATUS.PASS, DriverAction.takeSnapShot());
        }


    }

}
else
{//Delete the data from Add to course div
    List<WebElement> addCourseTable=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
    int tableSize=addCourseTable.size();
    for(int i=1;i<tableSize;i++)
    {
        if (DriverAction.isExist(Course_Locators.deleteIcon,120)) {
            DriverAction.click(Course_Locators.deleteIcon,"Clicked on Delete Icon","Successfully clicked on delete Icon");
        } else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on delete icon", STATUS.FAIL,
                    DriverAction.takeSnapShot());
        }

     DriverAction.waitSec(3);
     if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input",type+" removed successfully"))))
     {
         GemTestReporter.addTestStep(type+" is deleted",type+" is deleted successfully", STATUS.PASS, DriverAction.takeSnapShot());
     }
     else {
         GemTestReporter.addTestStep("Error Occur", "popup does not appear", STATUS.FAIL,
                 DriverAction.takeSnapShot());
     }
    }
}
          DriverAction.waitSec(5);
          if (DriverAction.isExist(Course_Locators.addIcon,120))
            {
               DriverAction.click(Course_Locators.addIcon,"Clicked on add Content Icon","Successfully clicked on Add Content Icon");
            }
          else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on add Content Icon", STATUS.FAIL,
            DriverAction.takeSnapShot());
              }
          DriverAction.waitSec(3);
          //Validate popup message when we add content to Course
          String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
          System.out.println(popupMessage);
//          String reqPopUpMessage="Content successfully added. Add more!";
          String reqPopUpMessage=message;
          if (reqPopUpMessage.equals(popupMessage)) {
                  GemTestReporter.addTestStep("Validate Content is added properly", "Content is added successfully", STATUS.PASS,
                          DriverAction.takeSnapShot());
              } else {
                  GemTestReporter.addTestStep("Validate Content is added properly", "Content is not added successfully", STATUS.FAIL,
                          DriverAction.takeSnapShot());
              }


String getContent=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[1]//tr[1]//td[1]"));
String contentAfterSelecting=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr[1]//td[1]"));
if(getContent.equals(contentAfterSelecting))
{
    GemTestReporter.addTestStep(type+" is added to Course Div",type+" is added successfully", STATUS.PASS, DriverAction.takeSnapShot());
}
else
{
    GemTestReporter.addTestStep(type+" is added to Course Div",type+" is not added successfully", STATUS.FAIL, DriverAction.takeSnapShot());
}
DriverAction.waitSec(5);
if (DriverAction.isExist(Course_Locators.addToCourseBtn,120))
{
    DriverAction.click(Course_Locators.addToCourseBtn,"Clicked on Add to Course Button","Successfully clicked on Add to course Button");
}
else {
    GemTestReporter.addTestStep("Error Occur", "Fail to click on Add to Course Button", STATUS.FAIL,
            DriverAction.takeSnapShot());
}
      }
      catch (Exception e) {
          logger.info("Exception occurred", e);
          GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
      }
}
    public static String generateRandomAlphabeticalString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Add any other characters you want

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

@Then("^Edit the Created Course and Verify$")
public void editAndVerify(){
        try{
            //in this function we are editing and validating it is edit properly or not
            DriverAction.scrollToTop();
            if(DriverAction.isDisplayed(Course_Locators.courseSummaryDiv))
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
            DriverAction.click(By.xpath(Course_Locators.button.replace("input","Default Order")),"clicked on Default Order button","Successfully clicked on Default Order button");
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


            //here we are checking the Save as Draft button functionality
            DriverAction.click(By.xpath(Course_Locators.button.replace("input","Save As Draft")),"Clicked on Save As Draft Button","Successfully clicked on Save As Draft button");
            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
            if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Course Created Successfully"))))
            {
                GemTestReporter.addTestStep("Course is drafted","course is drafted successfully", STATUS.PASS, DriverAction.takeSnapShot());
            }
            else {
                GemTestReporter.addTestStep("Course is drafted","course is not drafted", STATUS.FAIL, DriverAction.takeSnapShot());

            }

            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
//            DriverAction.waitSec(5);
            DriverAction.click(Course_Locators.courseTypeDropdown);
            DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
            DriverAction.click(Course_Locators.draftOrPublishDropdown);
            DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Draft")));
            DriverAction.waitSec(5);
            String fetchedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
            System.out.println(_courseName);
            System.out.println(fetchedCourseName);
            System.out.println(_courseName);
            if(fetchedCourseName.equals(_courseName))
            {
                GemTestReporter.addTestStep("Course is saved in draft and can be edit to publish finally","Successfully", STATUS.PASS, DriverAction.takeSnapShot());

            }
            else
            {
                GemTestReporter.addTestStep("Course is saved in draft and can be edit to publish finally","UnSuccessfully", STATUS.FAIL, DriverAction.takeSnapShot());

            }


            DriverAction.waitSec(5);
            if(DriverAction.isDisplayed(Course_Locators.editIcon)) {
                DriverAction.click(Course_Locators.editIcon, "clicked on edit icon", "Successfully clicked");
                if (DriverAction.isDisplayed(Course_Locators.editOption)) {
                    DriverAction.click(Course_Locators.editOption, "clicked on edit option", "Successfully clicked");
                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon, 120);
                    String screenHeader=DriverAction.getElementText(Course_Locators.actionHeading);
                    if(screenHeader.equals("Edit Course"))
                    {
                        GemTestReporter.addTestStep("Validate user successfully directed to Edit Course", "User successfully directed to Edit Course",
                                STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Validate user successfully directed to Edit Course", "User not able to directed to Edit Course",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                    DriverAction.scrollToBottom();
                    //Edit Add Course Info
//                    String courseNameBeforeEdit=DriverAction.getElementText(Course_Locators.courseNameInput);

                    DriverAction.click(Course_Locators.courseNameInput);
                    try {
                        // Create an instance of the Robot class
                        Robot robot = new Robot();

                        // Simulate Ctrl+A (Select All)
                        robot.keyPress(KeyEvent.VK_CONTROL); // Press Ctrl key
                        robot.keyPress(KeyEvent.VK_A); // Press 'A' key
                        robot.keyRelease(KeyEvent.VK_A); // Release 'A' key
                        robot.keyRelease(KeyEvent.VK_CONTROL); // Release Ctrl key

                        // Simulate Ctrl+X (Cut)
                        robot.keyPress(KeyEvent.VK_CONTROL); // Press Ctrl key
                        robot.keyPress(KeyEvent.VK_X); // Press 'X' key
                        robot.keyRelease(KeyEvent.VK_X); // Release 'X' key
                        robot.keyRelease(KeyEvent.VK_CONTROL); // Release Ctrl key

                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    int length = 10; // Change this to the desired length of the random alphabetical string
                    String randomAlphabeticalString = generateRandomAlphabeticalString(length);


                    DriverAction.typeText(Course_Locators.courseNameInput,randomAlphabeticalString);
//                    String courseNameAfterEdit=DriverAction.getElementText(Course_Locators.courseNameInput);
//                    System.out.println("Copy1");
//                    System.out.println(courseNameAfterEdit);
//                    System.out.println("Copy1");

//                        if(courseNameAfterEdit.equals("Copy1"))
//                        {
//                            GemTestReporter.addTestStep("Course Name is Edit","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
//                        }
//                        else
//                        {
//                            GemTestReporter.addTestStep("Course Name is Edit","Fail to Edit", STATUS.FAIL, DriverAction.takeSnapShot());
//                        }

                    //Edit the description

                    DriverAction.click(Course_Locators.courseDescription);
                    try {
                        // Create an instance of the Robot class
                        Robot robot = new Robot();

                        // Simulate Ctrl+A (Select All)
                        robot.keyPress(KeyEvent.VK_CONTROL); // Press Ctrl key
                        robot.keyPress(KeyEvent.VK_A); // Press 'A' key
                        robot.keyRelease(KeyEvent.VK_A); // Release 'A' key
                        robot.keyRelease(KeyEvent.VK_CONTROL); // Release Ctrl key

                        // Simulate Ctrl+X (Cut)
                        robot.keyPress(KeyEvent.VK_CONTROL); // Press Ctrl key
                        robot.keyPress(KeyEvent.VK_X); // Press 'X' key
                        robot.keyRelease(KeyEvent.VK_X); // Release 'X' key
                        robot.keyRelease(KeyEvent.VK_CONTROL); // Release Ctrl key

                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    DriverAction.typeText(Course_Locators.courseDescription,randomAlphabeticalString);
//                    String descriptionAfterEdit=DriverAction.getElementText(Course_Locators.courseDescription);
//
//                    System.out.println("Copy1");
//                    System.out.println(courseNameAfterEdit);
//                    System.out.println("Copy1");

//                        if(descriptionAfterEdit.equals("Copy1"))
//                        {
//                            GemTestReporter.addTestStep("Course Description is Edit","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
//                        }
//                        else
//                        {
//                            GemTestReporter.addTestStep("Course Description is Edit","Fail to Edit", STATUS.FAIL, DriverAction.takeSnapShot());
//                        }

                    //Edit the Has assignment checkbox

                    if(DriverAction.isDisplayed(Course_Locators.checkedAssignmentCheckbox))
                    {
                        DriverAction.click(Course_Locators.checkedAssignmentCheckbox);
                        DriverAction.waitSec(5);

                        DriverAction.click(Course_Locators.courseDescription);
                        if(DriverAction.isDisplayed(Course_Locators.checkboxs))
                        {
                            GemTestReporter.addTestStep("Has Assignment checkbox is editable","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
                        //check the has Assignment checkbox
                            DriverAction.click(Course_Locators.checkboxs);
                        }
                        else
                        {
                            GemTestReporter.addTestStep("Has Assignment checkbox is editable","Fail to Edit", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    }

                    //Add the content after editing the course Info
                    if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input", "Add Content")))) {
                        DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Add Content")));
                    }
                     DriverAction.waitSec(5);
                    //Edit the Add content selected content
                    String getContentBeforeEdit=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr[1]//td[1]"));

                    List<WebElement> addCourseTable=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
                    int tableSize=addCourseTable.size();
                    for(int i=1;i<tableSize;i++)
                    {
                        DriverAction.click(Course_Locators.deleteIcon,"Clicked on Delete Icon","Successfully clicked on delete Icon");
                        DriverAction.waitSec(3);
//                        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                        if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Content removed successfully"))))
                        {
                            GemTestReporter.addTestStep("Content is deleted","content is deleted successfully", STATUS.PASS, DriverAction.takeSnapShot());
                        }
                    }
                    DriverAction.click(Course_Locators.addIconEdit);

                    String getContentAfterEdit=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr[1]//td[1]"));
                    if(getContentBeforeEdit!=getContentAfterEdit)
                    {
                        GemTestReporter.addTestStep("Add Content is Editable","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Add Content is Editable","Fail to Edit", STATUS.FAIL, DriverAction.takeSnapShot());
                    }


                    DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add To Course")));
                    DriverAction.click(Course_Locators.addIcon);
                    DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add To Course")));
                    DriverAction.click(By.xpath(Course_Locators.button.replace("input","Default Order")),"clicked on Default Order button","Successfully clicked on Default Order button");

                    //here we are checking the Save as Draft button functionality
                    DriverAction.scrollToBottom();
                    DriverAction.waitSec(3);
                    DriverAction.click(Course_Locators.saveAsDraftButton,"Clicked on Save As Draft Button","Successfully clicked on Save As Draft button");

                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);

                    if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Course Updated Successfully"))))
                    {
                        GemTestReporter.addTestStep("Course is drafted","course is drafted successfully", STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else {
                        GemTestReporter.addTestStep("Course is drafted","course is not drafted", STATUS.FAIL, DriverAction.takeSnapShot());

                    }
//                    DriverAction.waitSec(5);
//                    DriverAction.click(Course_Locators.courseTypeDropdown);
//                    DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
//                    DriverAction.click(Course_Locators.draftOrPublishDropdown);
//                    DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Draft")));
//                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
//                    String fetchedCourseNameAfterEdit=DriverAction.getElementText(Course_Locators.draftedCourse);
//                    System.out.print(fetchedCourseNameAfterEdit);
//                    if(fetchedCourseName.equals("Copy1"))
//                    {
//                        GemTestReporter.addTestStep("Course is Editable","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
//
//                    }
//                    else
//                    {
//                        GemTestReporter.addTestStep("Course is Editable","Not able to edit course", STATUS.FAIL, DriverAction.takeSnapShot());
//
//                    }



                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
}
@Then("^Validate Course Summary Screen$")
    public void validateCourseSummary(){
       try{

           // In this we are validating Course Summary screen it's functionality all.

           DriverAction.scrollToTop();
           if(DriverAction.isDisplayed(Course_Locators.courseSummaryDiv))
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
           DriverAction.click(By.xpath(Course_Locators.button.replace("input","Default Order")),"clicked on Default Order button","Successfully clicked on Default Order button");
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
           DriverAction.click(By.xpath(Course_Locators.button.replace("input","Reset")),"Clicked on Reset Button","Successfully clicked on Reset button");
           List<WebElement> tableAfterReset=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
           if(tableAfterReset.size()!=firstTableSize)
           {
               GemTestReporter.addTestStep("Data is Reset after clicking the Reset button", "Successfully get reset", STATUS.PASS, DriverAction.takeSnapShot());
           }
           else
           {
               GemTestReporter.addTestStep("Data is Reset after clicking the Reset button", "Not able to Reset the data", STATUS.FAIL, DriverAction.takeSnapShot());
           }
           DriverAction.click(By.xpath(Course_Locators.button.replace("input","Default Order")),"clicked on Default Order button","Successfully clicked on Default Order button");

           //here we are checking the Save as Draft button functionality
           DriverAction.click(By.xpath(Course_Locators.button.replace("input","Save As Draft")),"Clicked on Save As Draft Button","Successfully clicked on Save As Draft button");

           DriverAction.waitSec(2);
//           DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
           String popUpMessage=DriverAction.getElementText(By.xpath("(//div[contains(@class,'p-toast-message')])[3]"));

         if("Course Created Successfully".equals(popUpMessage))
           {
               GemTestReporter.addTestStep("Course is drafted","course is drafted successfully", STATUS.PASS, DriverAction.takeSnapShot());
           }
           else {
               GemTestReporter.addTestStep("Course is drafted","course is not drafted", STATUS.FAIL, DriverAction.takeSnapShot());

           }

//           DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
           DriverAction.waitSec(5);
           DriverAction.click(Course_Locators.courseTypeDropdown);
           DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
           DriverAction.click(Course_Locators.draftOrPublishDropdown);
           DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Draft")));
           DriverAction.waitSec(5);
           String fetchedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
           if(fetchedCourseName.equals(_courseName))
           {
               GemTestReporter.addTestStep("Course is saved in draft and can be edit to publish finally","Successfully", STATUS.PASS, DriverAction.takeSnapShot());

           }
           else
           {
               GemTestReporter.addTestStep("Course is saved in draft and can be edit to publish finally","UnSuccessfully", STATUS.FAIL, DriverAction.takeSnapShot());

           }

           //here we are checking the Save Course & Publish button functionality
////           DriverAction.waitUntilElementDisappear(By.xpath("//*[@class='p-progress-spinner-svg']"),120);
//
DriverAction.waitSec(5);
if(DriverAction.isDisplayed(Course_Locators.editIcon))
{
    DriverAction.click(Course_Locators.editIcon,"clicked on edit icon","Successfully clicked");
    if(DriverAction.isDisplayed(Course_Locators.editOption))
    {
        DriverAction.click(Course_Locators.editOption,"clicked on edit option","Successfully clicked");
        DriverAction.waitSec(5);
//        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
        DriverAction.scrollToBottom();
        DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add Content")));
        DriverAction.waitSec(5);
//        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);

        DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add To Course")));
//        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
        DriverAction.waitSec(5);

        DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add To Course")));
        if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input","Update Course & Publish"))))
        {
            DriverAction.scrollToBottom();
            DriverAction.click(Course_Locators.publishButton);
//            DriverAction.waitUntilElementDisappear(By.xpath("//*[@class='p-progress-spinner-svg']"),120);
           DriverAction.waitSec(3);
            if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input","Yes"))))
            {
                DriverAction.click(By.xpath(Course_Locators.button.replace("input","Yes")));
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input","Yes")))) {
                    DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Yes")));
                }
//                DriverAction.waitUntilElementDisappear(By.xpath("//*[@class='p-progress-spinner-svg']"),120);

                    DriverAction.waitSec(5);
                DriverAction.click(Course_Locators.courseTypeDropdown);
                DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
                DriverAction.click(Course_Locators.draftOrPublishDropdown);
                DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Published")));
                DriverAction.waitSec(5);
                String fetchedPublishedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);

                if(fetchedPublishedCourseName.equals(_courseName))
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
}
       }
       catch (Exception e) {
           logger.info("Exception occurred", e);
           GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
       }
}
@When("^A Learner is assign to a course$")
    public void assignLearnerToCourse(){

        try {
            //Assign a Learner to a course
            DriverAction.waitSec(5);
//            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
         if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                _assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners"))));
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners")),"Validate user clicked on Assign Learner option","User successfully clicked on Assign Learner option");

                    if(DriverAction.isDisplayed(Course_Locators.nameFilterInput))
                    {
                        DriverAction.waitSec(5);
                        DriverAction.typeText(Course_Locators.nameFilterInput,"rahul23@gmail.com","Validate user successfully filter username","Username is successfully filtered");
                        DriverAction.waitSec(5);
//                        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                        DriverAction.click(Course_Locators.addIcon,"Validate user added the filtered username","Filtered username is added successfully");
                        DriverAction.waitSec(3);
                        String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                        System.out.println(popupMessage);
                        String reqPopUpMessage="Learner added successfully to the course";
                        if (reqPopUpMessage.equals(popupMessage)) {
                            GemTestReporter.addTestStep("Validate Learner is assigned successfully", "Learner is assigned successfully", STATUS.PASS,
                                    DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Learner is assigned successfully", "Learner is not assigned successfully", STATUS.FAIL,
                                    DriverAction.takeSnapShot());
                        }

                    }
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

}
@And("^Change the user's role \"([^\"]*)\"$")
    public void switchRole(String role)
{
    try{
        //in this we are switching the role
        DriverAction.waitSec(5);
        DriverAction.click(LearnerModule_Locators.userDropdown, "Click the dropdown icon on navbar", "Successfully clicked the dropdown icon.");
        if(DriverAction.isDisplayed(By.xpath(Course_Locators.userOption.replace("input",role))))
        {
            DriverAction.click(By.xpath(Course_Locators.userOption.replace("input",role)),"Validate user role is updated successfully","User role is updated successfully");
        }
        DriverAction.waitUntilElementDisappear(By.xpath("//*[@class='p-progress-spinner-svg']"),120);
        DriverAction.waitSec(5);
    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
    }
}
@And("^Filter the course and complete it$")
    public void filterCompleteCourse(){
        try{
            //in this we are completing the course
            DriverAction.waitSec(5);
            if(DriverAction.isDisplayed(Course_Locators.courseFilterInput))
            {
                DriverAction.typeText(Course_Locators.courseFilterInput,_assignedCourseName,"Validate course name is filtered successfully","Course name is filtered successfully");

            }
            DriverAction.scrollToBottom();
            DriverAction.waitSec(4);
            if(DriverAction.isDisplayed(LearnerModule_Locators.viewCourseBtn))
            {
                DriverAction.click(LearnerModule_Locators.viewCourseBtn,"clicked on View Course Button","Successfully clicked on view course button");
                if(DriverAction.isDisplayed(LearnerModule_Locators.startCourseBtn))
                {
                    DriverAction.click(LearnerModule_Locators.startCourseBtn,"clicked on Start Course Button","Successfully clicked on Start course button");
                }
                DriverAction.scrollToBottom();
                DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn),90);
                if(DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn)) {
                    DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                }
                //Assignment
                DriverAction.waitSec(4);
                DriverAction.typeText(LearnerModule_Locators.answerArea, "demo_content");
                DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn),90);

                if(DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn))
                {
                    DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                }
                DriverAction.waitSec(10);
                DriverAction.click(Course_Locators.userDropdown, "Click the dropdown icon on navbar", "Successfully clicked the dropdown icon.");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.userOption.replace("input","Super Admin"))))
                {
                    DriverAction.click(By.xpath(Course_Locators.userOption.replace("input","Super Admin")));
                }

            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
}
@Then("^Verify the Learner Report \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyLearnerReport(String learner,String email,String percentage,String status){
        try{
             int c=0;
             if(DriverAction.isDisplayed(Course_Locators.courseFilterTagInput))
              {
                       DriverAction.typeText(Course_Locators.courseFilterTagInput,_assignedCourseName);
              }
            DriverAction.waitSec(5);
            if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                _assignedCourseName = DriverAction.getElementText(Course_Locators.draftedCourse);
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if (DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input", "Learner Reports"))))
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input", "Learner Reports")));
                }
            }
            if(DriverAction.isDisplayed(Course_Locators.nameTagFilterInput))
            {
                DriverAction.typeText(Course_Locators.nameTagFilterInput,"rahul");
                DriverAction.scrollToBottom();
            }
            DriverAction.waitSec(5);
            List<WebElement> list1 =DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr)[2]//td"));
            for (WebElement element : list1) {
                String text = element.getText();
                if (text.equals(learner)) {
                    c++;
                    GemTestReporter.addTestStep("Learner Name match the report", "Successfully", STATUS.PASS, DriverAction.takeSnapShot());

                }
                if (text.equals(email)) {
                    c++;
                    GemTestReporter.addTestStep("Learner Email match the report", "Successfully", STATUS.PASS, DriverAction.takeSnapShot());

                }
                if (text.equals(percentage)) {
                    c++;
                    GemTestReporter.addTestStep("Learner percentage is 100%,therefore completed the course", "Successfully", STATUS.PASS, DriverAction.takeSnapShot());

                }
                if (text.equals(status)) {
                    c++;
                    GemTestReporter.addTestStep("Learner status is completed", "Successfully", STATUS.PASS, DriverAction.takeSnapShot());

                }
                if(c>=4)
                {
                    break;
                }

            }
            DriverAction.waitSec(5);
                if(DriverAction.isDisplayed(Course_Locators.contentsReport))
                {
                    DriverAction.click(Course_Locators.contentsReport);
                }
                if(DriverAction.isDisplayed(Course_Locators.assignmentReport))
                {
                    DriverAction.click(Course_Locators.assignmentReport);
                }
               String content=DriverAction.getElementText(Course_Locators.contentStatus);
                String assignment=DriverAction.getElementText(Course_Locators.assignmentStatus);
                if(content.equals("Completed")&&assignment.equals("Completed"))
                {
                    GemTestReporter.addTestStep("Completed Assignment and Content","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Completed Assignment and Content","UnSuccessfully", STATUS.FAIL, DriverAction.takeSnapShot());

                }

    //


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
}

@When("^Reattempt the test to the user$")
    public void reattemptTest(){
        try{
            //in this function we checking reattempt functionality

            if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                _assignedCourseName = DriverAction.getElementText(Course_Locators.draftedCourse);
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                DriverAction.waitSec(3);
                if (DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input", "Learner Reports"))))
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input", "Learner Reports")));
                }
            }
            if(DriverAction.isDisplayed(Course_Locators.nameTagFilterInput))
            {
                DriverAction.typeText(Course_Locators.nameTagFilterInput,"rahul");

            }
            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
            DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
            if(DriverAction.isDisplayed(Course_Locators.reattemptLabel))
            {
                DriverAction.waitSec(3);
                DriverAction.click(Course_Locators.reattemptLabel, "clicked on reattempt option", "Successfully clicked");
            }
            DriverAction.waitSec(3);
            String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println(popupMessage);
            String reqPopUpMessage="Learner can reattempt course";
            if (reqPopUpMessage.equals(popupMessage)) {
                GemTestReporter.addTestStep("Validate Learner is Reattempt to course successfully", "Learner is Reattempted to course  successfully", STATUS.PASS,
                        DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate Learner is Reattempt to course successfully", "Learner is not Reattempted to course  successfully", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
}
    @Then("^Click the button until it appear \"([^\"]*)\"$")
    public void clickTheButtonWithWait(String buttonName) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 50);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MyLocators.button.replace("input", buttonName))));
            DriverAction.click(By.xpath(MyLocators.button.replace("input", buttonName)));
        }catch(Exception e){
            System.out.print("Exception encountered!");
        }

    }
    @Then("^Click the Internal Test button until it appear \"([^\"]*)\"$")
    public void clickTheInternalTestButtonWithWait(String buttonName) throws InterruptedException {
        try {

            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 50);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Tests_TestControl_Locators.buttonInternal.replace("input", buttonName))));
//            element.click();

//            DriverAction.waitUntilElementAppear(By.xpath(MyLocators.button.replace("input", buttonName)),120);
            DriverAction.click(By.xpath(Tests_TestControl_Locators.buttonInternal.replace("input", buttonName)));
        }catch(Exception e){
            System.out.print("Exception encountered!");
        }

    }
    @And("^Enter course description for Test \"([^\"]*)\"$")
    public void courseDescriptionTest(String description) {
        try{

            DriverAction.typeText(Course_Locators.courseDescription,description);
            DriverAction.scrollToBottom();
            DriverAction.click(Tests_TestControl_Locators.hasTestCheckbox);
        }catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @When("Count of Active Test from Course Library")
    public void countOfActiveTestFromCourseLibrary() {
        List<String> courseList=new ArrayList<>();
        DriverAction.waitSec(10);
        DriverAction.scrollToBottom();
        while(DriverAction.isEnabled(Course_Locators.rightPaginator))
        {
            List<WebElement> course=DriverAction.getElements(Course_Locators.courseList);
            for(int i=0;i<course.size();i++)
            {
                WebElement element = course.get(i);
                String fetchedCourse = element.getText();
                if(fetchedCourse!=null) {
                   courseList.add(fetchedCourse);
                    _ActiveCourseCount++;
                }

            }
            DriverAction.waitSec(3);
            if(DriverAction.isEnabled(Course_Locators.rightPaginator)) {
                DriverAction.click(Course_Locators.rightPaginator);
            }
        }

    }

    @And("Count the Course from List View Screen")
    public void countTheCourseFromListViewScreen() {
        //in this we are counting the course from view screen
        DriverAction.scrollToTop();
        if(DriverAction.isExist(Course_Locators.listViewBtn,120))
        {
            DriverAction.click(Course_Locators.listViewBtn);
        }
        else {
            GemTestReporter.addTestStep("Error occurred","Not able to click on List View Button", STATUS.FAIL, DriverAction.takeSnapShot());

        }
        List<String> viewList=new ArrayList<>();
        DriverAction.waitSec(10);
        DriverAction.scrollToBottom();
        if(DriverAction.isExist(Course_Locators.leftPaginator))
        {
            DriverAction.click(Course_Locators.leftPaginator);
        }
        else {
            GemTestReporter.addTestStep("Error occurred","Not able to click on left paginator", STATUS.FAIL, DriverAction.takeSnapShot());

        }
        DriverAction.waitSec(5);
        DriverAction.scrollToBottom();
        while(DriverAction.isEnabled(Course_Locators.rightPaginator))
        {
            List<WebElement> course=DriverAction.getElements(Course_Locators.viewList);
            for(int i=0;i<course.size();i++)
            {
                WebElement element = course.get(i);
                String fetchedCourse = element.getText();
                if(fetchedCourse!=null) {
                    viewList.add(fetchedCourse);
                    _viewListActiveCount++;
                }

            }
            DriverAction.waitSec(3);
            if(DriverAction.isEnabled(Course_Locators.rightPaginator)) {
                DriverAction.click(Course_Locators.rightPaginator);
            }
        }
    }

    @Then("Validate the count")
    public void validateTheCount() {
        try{
            //we are validating the count
            DriverAction.waitSec(5);
            if(_ActiveCourseCount==_viewListActiveCount)
            {
                GemTestReporter.addTestStep("Validate count of Active Course in Course Library and View List","Count Matches", STATUS.PASS, DriverAction.takeSnapShot());

            }
            else {
                GemTestReporter.addTestStep("Validate count of Active Course in Course Library and View List","Count Not Matched", STATUS.FAIL, DriverAction.takeSnapShot());

            }
        }
        catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,STATUS.ERR);
        }


    }

    @Then("Inactive the course and Validate")
    public void inactiveTheCourseAndValidate() {
        try{
            //we are checking the inactive course
            if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Course Summary"))));
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Course Summary")));

                   if(DriverAction.isExist(Course_Locators.inActiveBtn,120))
                   {
                      DriverAction.click(Course_Locators.inActiveBtn);
                      DriverAction.waitSec(3);
                       String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                       System.out.println(popupMessage);
                       String reqPopUpMessage="Course Status Updated";
                       if (reqPopUpMessage.equals(popupMessage)) {
                           GemTestReporter.addTestStep("Validate Course status updated successfully", "Course status updated successfully", STATUS.PASS,
                                   DriverAction.takeSnapShot());
                       } else {
                           GemTestReporter.addTestStep("Validate Course status updated successfully", "Course status is not updated successfully", STATUS.FAIL,
                                   DriverAction.takeSnapShot());
                       }

                   }
                   else {
                       GemTestReporter.addTestStep("Error occurred","Not able to click on left paginator", STATUS.FAIL, DriverAction.takeSnapShot());

                   }


                }
            }
            DriverAction.waitSec(5);
            if(DriverAction.isDisplayed(Course_Locators.inActiveDiv))
            {
                GemTestReporter.addTestStep("Validate Course Inactive Successfully","Course get Inactive Successfully", STATUS.PASS, DriverAction.takeSnapShot());

            }
            else {
                GemTestReporter.addTestStep("Validate Course Inactive Successfully","Course not get Inactive Successfully", STATUS.FAIL, DriverAction.takeSnapShot());

            }
            if(DriverAction.isExist(LearnerModule_Locators.backBtn1,120))
            {
                DriverAction.click(LearnerModule_Locators.backBtn1);
            }
            else {
                GemTestReporter.addTestStep("Error occurred","Not able to click on back button", STATUS.FAIL, DriverAction.takeSnapShot());

            }
            if(DriverAction.isExist(Course_Locators.activeDropdown))
            {
                DriverAction.click(Course_Locators.activeDropdown);
                if(DriverAction.isExist(Course_Locators.inActiveOption))
                {
                    DriverAction.click(Course_Locators.inActiveOption);
                }
                else {
                    GemTestReporter.addTestStep("Error occurred","Not able to click on Inactive Option", STATUS.FAIL, DriverAction.takeSnapShot());

                }
            }
            else {
                GemTestReporter.addTestStep("Error occurred","Not able to click on Active Dropdown", STATUS.FAIL, DriverAction.takeSnapShot());

            }
        }
        catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,STATUS.ERR);
        }


    }
    @When("select course and switch to course summary")
    public void selectCourseAndSwitchToCourseSummary() {
        try{
            //get the name of course
            DriverAction.waitSec(5);
            _firstCourseName=DriverAction.getElementText(Course_Locators.firstCourseName);
            //select a course
            DriverAction.click(Tests_TestControl_Locators.threeDotIcon,"Validate user able to click view course option","User successfully click on view course option");
            //select course summary option
            DriverAction.click(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input","Course Summary")));

            if(DriverAction.isDisplayed(By.xpath(Course_Locators.heading.replace("input"," Course Summary:  "+_firstCourseName+" "))))
            {
                GemTestReporter.addTestStep("Validate user directed to course summary screen successfully", "User successfully directed to course summary screen",
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate user directed to course summary screen successfully", "User not able to directed to course summary screen",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate {string} action on Course summary")
    public void validateActionOnCourseSummary(String action) {
        try{
            //select the action
            DriverAction.click(Tests_TestControl_Locators.actionBtn,"validate user click on action button","User clicked on action button");
            DriverAction.click(By.xpath(Tests_TestControl_Locators.actionOption.replace("input",action)));
            DriverAction.waitSec(5);
            String actionHeading=DriverAction.getElementText(Course_Locators.actionHeading);
            if(action.equals("Edit Course"))
            {
                if(actionHeading.trim().equals("Edit Course"))
                {
                    GemTestReporter.addTestStep("Validate user directed to Edit course screen", "User successfully directed to Edit course screen",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Validate user directed to Edit course screen", "User not able to directed to  Edit course screen",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }

            }
            else if(action.equals("Assign Learners"))
            {
                if(actionHeading.trim().equals("Assign Learners: "+_firstCourseName))
                {
                    GemTestReporter.addTestStep("Validate user directed to Assign Learner screen", "User successfully directed to Assign Learner screen",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Validate user directed to Assign Learner screen", "User not able to directed to  Assign Learner screen",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
            else
            {
                if(actionHeading.trim().equals("View Learner(s) Report: "+_firstCourseName))
                {
                    GemTestReporter.addTestStep("Validate user directed to Learner Report screen", "User successfully directed to Learner Report screen",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Validate user directed to Learner Report screen", "User not able to directed to Learner Report screen",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate View as Learner button functionality")
    public void validateViewAsLearnerButtonFunctionality() {
        try{
            //click on View as learner button
            DriverAction.click(Course_Locators.viewAsLearnerBtn,"Validate user able to click on view as Learner button","User successfully clicked on view as Learner button");
            DriverAction.waitSec(3);
            //Validate the user directed to learner screen
            if(DriverAction.isDisplayed(By.xpath(Course_Locators.heading.replace("input"," View as Learner:  "+  _firstCourseName+" "))))
            {
                GemTestReporter.addTestStep("Validate user directed to View Learner screen successfully", "User successfully directed to View Learner screen",
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate user directed to View Learner screen successfully", "User not able to directed to View Learner screen",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @When("select course and switch to Learner Report")
    public void selectCourseAndSwitchToLearnerReport() {
        try{
            //get the name of course

            _firstCourseName=DriverAction.getElementText(Course_Locators.firstCourseName);
            //select a course
            DriverAction.click(Tests_TestControl_Locators.threeDotIcon,"Validate user able to click view course option","User successfully click on view course option");
            //select Learner Report option
            DriverAction.click(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input","Learner Reports")));

            String fetchedHeading=DriverAction.getElementText(Course_Locators.actionHeading);
            String reqHeading="View Learner(s) Report: "+_firstCourseName;


            if(fetchedHeading.trim().equals(reqHeading.trim()))
            {
                GemTestReporter.addTestStep("Validate user directed to Learner Report screen successfully", "User successfully directed to Learner Report screen",
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate user directed to Learner Report screen successfully", "User not able to directed to Learner Report screen",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate Status of Course")
    public void validateStatusOfCourse() {
        try{
            //content,test,assignment
//case 1-Expired
//case 2-Completed -content and assignment:completed and test:Pass or Fail
//case 3-unattempted -status:unattempted
//case 4-inprogress
            String courseStatus=DriverAction.getElementText(Course_Locators.courseStatus);
          List<WebElement> courseStatusList=DriverAction.getElements(Course_Locators.courseStatusList);
          for(int i=0;i<courseStatusList.size();i++)
          {
              String content=DriverAction.getElementText(By.xpath(Course_Locators.courseContent.replace("itr",String.valueOf(i+1))));
              if(content.equals("Contents"))
              {
                  DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr",String.valueOf(i+1))));

                  if(courseStatus.equals("Expired"))
                  {
                      String status=DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                    if(status.equals("Unattempted")||status.equals("Completed")||status.equals("In Progress"))
                    {
                        GemTestReporter.addTestStep("Validate Expired Content should have the status unattempted or completed or In progress", "Expired course is "+status,
                                STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Validate Expired Content should have the status unattempted or completed or In progress", "Expired course is not giving desired status",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                  }
                  else if(courseStatus.equals("In Progress"))
                  {
                      String status=DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                      if(status.equals("Unattempted")||status.equals("Completed"))
                      {

                          GemTestReporter.addTestStep("Validate In Progress Content should have the status unattempted or Completed", "In progress course is "+status,
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate In Progress Content should have the status unattempted or Completed", "In progress course is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("Yet to Start"))
                  {
                      String status=DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                      if(status.equals("Unattempted"))
                      {

                          GemTestReporter.addTestStep("Validate Yet to Start Content should have the status unattempted", "Yet to Start course is unattempted",
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Yet to Start Content should have the status unattempted", "Yet to Start course is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else
                  {
                      String status=DriverAction.getElementText(Course_Locators.contentsStatusCompleted);
                      if(status.equals("Completed"))
                      {

                          GemTestReporter.addTestStep("Validate Completed Content should have the status Completed", "Completed course content is completed successfully",
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Completed Content should have the status Completed", "Completed course is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }

              }
              else if(content.equals("Assignments"))
              {
                  DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr",String.valueOf(i+1))));
                  String status=DriverAction.getElementText(Course_Locators.assignmentsStatus);
                  if(courseStatus.equals("Expired"))
                  {
                      if(status.equals("Unattempted"))
                      {
                          GemTestReporter.addTestStep("Validate Expired Assignment should have the status unattempted", "Expired Assignment is unattempted",
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Expired Assignment should have the status unattempted", "Expired Assignment is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("In Progress"))
                  {
                      if(status.equals("Unattempted")||status.equals("Completed"))
                      {

                          GemTestReporter.addTestStep("Validate In Progress Assignment should have the status unattempted or Completed", "In progress Assignment is "+status,
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate In Progress Assignment should have the status unattempted or Completed", "In progress Assignment is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("Yet to Start"))
                  {
                      if(status.equals("Unattempted"))
                      {

                          GemTestReporter.addTestStep("Validate Yet to Start Assignment should have the status unattempted", "Yet to Start Assignment is unattempted",
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Yet to Start Assignment should have the status unattempted", "Yet to Start Assignment is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else
                  {
                      if(status.equals("Completed"))
                      {

                          GemTestReporter.addTestStep("Validate Completed Assignment should have the status Completed", "Completed course Assignment is completed successfully",
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Completed Assignment should have the status Completed", "Completed Assignment is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
              }
              else
              {
                  DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr",String.valueOf(i+1))));
                  String status=DriverAction.getElementText(Course_Locators.contentsStatusCompleted);
                  if(courseStatus.equals("Expired"))
                  {
                      if(status.equals("Unattempted"))
                      {
                          GemTestReporter.addTestStep("Validate Expired Test should have the status unattempted", "Expired Test is unattempted",
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Expired Test should have the status unattempted", "Expired Test is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("In Progress"))
                  {
                      if(status.equals("Unattempted")||status.equals("PASS")||status.equals("FAIL"))
                      {

                          GemTestReporter.addTestStep("Validate In Progress Test should have the status unattempted,pass or fail", "In progress Test is "+status,
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate In Progress Test should have the status unattempted,pass or fail", "In progress Test is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("Yet to Start"))
                  {
                      if(status.equals("Unattempted"))
                      {

                          GemTestReporter.addTestStep("Validate Yet to Start Test should have the status unattempted", "Yet to Start Test is unattempted",
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Yet to Start Test should have the status unattempted", "Yet to Start Test is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else
                  {
                      if(status.equals("PASS")||status.equals("FAIL"))
                      {

                          GemTestReporter.addTestStep("Validate Completed Test should have the status Pass Or Fail", "Completed Test is completed successfully",
                                  STATUS.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Completed Test should have the status Pass Or Fail", "Completed Test is not giving desired status",
                                  STATUS.FAIL, DriverAction.takeSnapShot());
                      }
                  }
              }
              DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr",String.valueOf(i+1))));

          }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate {string} of Learner assigned")
    public void validateOfLearnerAssigned(String count) {
        try{
           //get the count of assign candidate
            String getText=DriverAction.getElementText(Tests_TestControl_Locators.learnerAssignCount);
            String counts[]=getText.split(":");
            String candidateCount=counts[1];
            if(count.equals(candidateCount.trim()))
            {
                GemTestReporter.addTestStep("Validate Learner assign count", "Learner count matches with selected learner", STATUS.PASS,
                        DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate Learner assign count", "Learner count not matches with selected learner", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate {string} of Learner Unassigned")
    public void validateOfLearnerUnassigned(String count) {
        try{
            //get the count of Unassign Learner
            String getText=DriverAction.getElementText(Tests_TestControl_Locators.learnerAssignCount);
            String counts[]=getText.split(":");
            String candidateCount=counts[1];
            if(count.equals(candidateCount.trim()))
            {
                GemTestReporter.addTestStep("Validate Learner Unassign count", "Learner count matches with selected learner", STATUS.PASS,
                        DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate Learner Unassign count", "Learner count not matches with selected learner", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }


    @And("select multiple {string} Unassigned user of different page and Assign the {string}")
    public void selectMultipleUnassignedUserOfDifferentPageAndAssignThe(String count, String test) {
        try{
            //select candidate of first screen
            int c=0;
            DriverAction.waitSec(5);
            for(int j=0;j<2;j++) {
                List<WebElement> candidateList = DriverAction.getElements(Tests_TestControl_Locators.candidateList);
                for (int i = 0; i < candidateList.size(); i++) {
                    String getStatus = " ";
                    if (test.equals("test")) {
                        getStatus = DriverAction.getElementText(By.xpath(Tests_TestControl_Locators.candidateStatus.replace("itr", String.valueOf(i + 1))));
                        if ("Unassigned".equals(getStatus)) {
                            //check the unassigned user
                            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr", String.valueOf(i + 1))));

                            c++;
                            if (c>=1) {
                                //DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateAction.replace("itr",String.valueOf(i+1))));
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        getStatus = DriverAction.getElementText(By.xpath(Tests_TestControl_Locators.learnerStatus.replace("itr", String.valueOf(i + 1))));
                        if ("Unassigned".equals(getStatus)) {
                            //check the unassigned user
                            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr", String.valueOf(i + 1))));

                            c++;
                            if (c>=1) {
                                //DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateAction.replace("itr",String.valueOf(i+1))));
                                break;
                            }
                        } else {
                            continue;
                        }
                    }

                }
                //select learner of 3rd screen
                for (int i = 0; i <2; i++) {
                    DriverAction.waitSec(3);
                    DriverAction.click(Course_Locators.rightPaginator, "Validate user able to click on right pagination", "User successfully clicked on right pagination");
                }
            }
            DriverAction.click(Tests_TestControl_Locators.assignCandidateBtn,"Validate user able to click on assign candidate button","User able to click on button");
            DriverAction.click(Tests_TestControl_Locators.yesBtn,"Validate user able to click on  popup","User successfully able to click on popup");
            DriverAction.waitSec(5);
            //validate candidates are assigned successfully
            String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println(popupMessage);
            String reqPopUpMessage="";
            if(test.equals("test")) {
                reqPopUpMessage="All candidates added successfully to the test";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are assign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are not assign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage="All learners added successfully to the course";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are assign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are not assign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    @And("select multiple {string} Assigned User of different page and Unassign the {string}")
    public void selectMultipleAssignedUserOfDifferentPageAndUnassignThe(String count, String test) {
        try{
            //select candidate of first screen
            int c=0;
            DriverAction.waitSec(5);
            for(int j=0;j<2;j++) {
                List<WebElement> candidateList = DriverAction.getElements(Tests_TestControl_Locators.candidateList);
                for(int i=0;i<candidateList.size();i++)
                {
                    String getStatus=" ";
                    if(test.equals("test")) {
                        getStatus = DriverAction.getElementText(By.xpath(Tests_TestControl_Locators.candidateStatus.replace("itr", String.valueOf(i + 1))));
                        if("Assigned".equals(getStatus))
                        {
                            //check the unassigned user
                            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr",String.valueOf(i+1))));
                            c++;
                            if(c>=1) {
                                //DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateAction.replace("itr",String.valueOf(i+1))));
                                break;
                            }
                        }
                        else
                        {
                            continue;
                        }
                    }
                    else
                    {
                        getStatus = DriverAction.getElementText(By.xpath(Tests_TestControl_Locators.learnerStatus.replace("itr", String.valueOf(i + 1))));
                        if("Unattempted".equals(getStatus))
                        {
                            //check the unassigned user
                            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr",String.valueOf(i+1))));
                            c++;
                            if(c>=1) {
                                //DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateAction.replace("itr",String.valueOf(i+1))));
                                break;
                            }
                        }
                        else
                        {
                            continue;
                        }
                    }

                }
                //select learner of 3rd screen
                for (int i = 0; i <2; i++) {
                    DriverAction.waitSec(3);
                    DriverAction.click(Course_Locators.rightPaginator, "Validate user able to click on right pagination", "User successfully clicked on right pagination");
                }

            }
            DriverAction.click(Tests_TestControl_Locators.unAssignCandidateBtn,"Validate user able to click on assign candidate button","User able to click on button");
            DriverAction.click(Tests_TestControl_Locators.yesBtn,"Validate user able to click on  popup","User successfully able to click on popup");
            DriverAction.waitSec(3);
            //validate candidates are assigned successfully
            String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println("hello");
            System.out.println(popupMessage);
            System.out.println("hello");
            String reqPopUpMessage="";
            if(test.equals("test")) {
                reqPopUpMessage = "All candidates who have not attempted the test removed successfully";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Candidates are Unassign properly", "Candidates are Unassign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are Unassign properly", "Candidates are not Unassign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage = "All learners who have not attempted the course were removed successfully";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are Unassign properly", "Learners are Unassign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are Unassign properly", "Learners are not Unassign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @And("search user by {string} and assign it {string}")
    public void searchUserByAndAssignIt(String email, String test) {
        try{
            //search the user by name
            DriverAction.typeText(Course_Locators.emailInput,email,"Validate user successfully able to type into name/email input","User successfully able to type into name/email input");
            DriverAction.waitSec(3);
            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr",String.valueOf(1))));
            DriverAction.click(Tests_TestControl_Locators.assignCandidateBtn,"Validate user able to click on assign candidate button","User able to click on button");
            DriverAction.click(Tests_TestControl_Locators.yesBtn,"Validate user able to click on  popup","User successfully able to click on popup");
            DriverAction.waitSec(3);
            //validate candidates are assigned successfully
            String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println(popupMessage);
            String reqPopUpMessage="";
            if(test.equals("test")) {
                reqPopUpMessage="All candidates added successfully to the test";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are assign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are not assign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage="All learners added successfully to the course";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are assign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are not assign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("search user by {string} and Unassign it {string}")
    public void searchUserByAndUnassignIt(String email, String test) {
        try{
            //search the user by name
            DriverAction.typeText(Course_Locators.emailInput,email,"Validate user successfully able to type into name/email input","User successfully able to type into name/email input");
            DriverAction.waitSec(3);
            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr",String.valueOf(1))));
            DriverAction.click(Tests_TestControl_Locators.unAssignCandidateBtn,"Validate user able to click on assign candidate button","User able to click on button");
            DriverAction.click(Tests_TestControl_Locators.yesBtn,"Validate user able to click on  popup","User successfully able to click on popup");
            DriverAction.waitSec(3);
            //validate candidates are assigned successfully
            String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println("hello");
            System.out.println(popupMessage);
            System.out.println("hello");
            String reqPopUpMessage="";
            if(test.equals("test")) {
                reqPopUpMessage = "All candidates who have not attempted the test removed successfully";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Candidates are Unassign properly", "Candidates are Unassign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are Unassign properly", "Candidates are not Unassign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage = "All learners who have not attempted the course were removed successfully";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are Unassign properly", "Learners are Unassign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are Unassign properly", "Learners are not Unassign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("Filter the Learner using category and assign {string}")
    public void filterTheLearnerUsingCategoryAndAssign(String test) {
        try{
            //filter learner using category
            DriverAction.click(Course_Locators.categoryDropdown,"Validate user clicked on Category dropdown","User successfully clicked on category dropdown");
            DriverAction.click(By.xpath(Course_Locators.requiredOption.replace("input","EC")));
            DriverAction.waitSec(3);
            DriverAction.click(Course_Locators.selectedCategoryDropdown,"Validate user clicked on Category dropdown","User successfully clicked on category dropdown");
            DriverAction.click(By.xpath(Course_Locators.requiredOption.replace("input","Java")));
            DriverAction.waitSec(3);
            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr",String.valueOf(1))));
            DriverAction.click(Tests_TestControl_Locators.assignCandidateBtn,"Validate user able to click on assign candidate button","User able to click on button");
            DriverAction.click(Tests_TestControl_Locators.yesBtn,"Validate user able to click on  popup","User successfully able to click on popup");
            DriverAction.waitSec(3);
            //validate candidates are assigned successfully
            String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println(popupMessage);
            String reqPopUpMessage="";
            if(test.equals("test")) {
                reqPopUpMessage="All candidates added successfully to the test";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are assign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are not assign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage="All learners added successfully to the course";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are assign successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are not assign successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @And("Assign a learner and complete the course")
    public void assignALearnerAndCompleteTheCourse() {
        try{

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Assign {string} Learner and edit date for course")
    public void assignLearnerAndEditDateForCourse(String email) {
        try{
            //assign the first leaner
            DriverAction.typeText(Course_Locators.emailInput,email,"Validate user successfully able to type into name/email input","User successfully able to type into name/email input");
            DriverAction.waitSec(3);
            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr",String.valueOf(1))));
            DriverAction.click(Tests_TestControl_Locators.assignCandidateBtn,"Validate user able to click on assign candidate button","User able to click on button");
            DriverAction.click(Tests_TestControl_Locators.yesBtn,"Validate user able to click on  popup","User successfully able to click on popup");
            DriverAction.waitSec(5);
            //validate candidates are assigned successfully
            String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println(popupMessage);
            String reqPopUpMessage="";
            reqPopUpMessage="All learners added successfully to the course";
            if (reqPopUpMessage.equals(popupMessage)) {
                GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are assign successfully", STATUS.PASS,
                        DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are not assign successfully", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            //edit the date
            LocalDate today = LocalDate.now();
            int day = today.getDayOfMonth();
            int day2 =day+1;
            String startDate = String.valueOf(day);
            String endDate = String.valueOf(day2);
            DriverAction.click(Course_Locators.calendarIcon,"Validate user able to click on Calendar icon","User successfully clicked on Calendar icon");
            int c=0;
            if(c==0)
            {
                DriverAction.click(By.xpath(Course_Locators.calendar.replace("itr", String.valueOf(c+2))));
                DriverAction.waitSec(3);
                DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", startDate)));
                DriverAction.click(By.xpath(Course_Locators.calendar.replace("itr", String.valueOf(c+3))));
                DriverAction.waitSec(3);
                DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", endDate)));
                DriverAction.waitSec(2);
                DriverAction.click(Course_Locators.updateBtn,"Validate user successfully clicked on Update date button","User successfully clicked on Update button");

                DriverAction.waitSec(4);
                //validate date is updated successfully
                String fetchDate=DriverAction.getElementText(Course_Locators.updatedDate);
                String [] dateArray=fetchDate.split("/");
                if(dateArray[0].equals(endDate))
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is updated successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is not updated successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    @Then("Filter completed Course and validate it date can be edited")
    public void filterCompletedCourseAndValidateItDateCanBeEdited() {
        try{
//Filter the completed course from status dropdown
            DriverAction.waitSec(4);
            DriverAction.click(Course_Locators.statusDropdown,"Validate user clicked on Status dropdown","User successfully clicked on Status dropdown");
            DriverAction.click(By.xpath(Course_Locators.requiredOption.replace("input","Completed")));
            DriverAction.waitSec(3);
            //edit the date
            LocalDate today = LocalDate.now();
            int day = today.getDayOfMonth();
            int day2 =day+1;
            String startDate = String.valueOf(day);
            String endDate = String.valueOf(day2);
            DriverAction.click(Course_Locators.calendarIcon,"Validate user able to click on Calendar icon","User successfully clicked on Calendar icon");
            int c=0;
            if(c==0)
            {
                DriverAction.click(By.xpath(Course_Locators.calendar.replace("itr", String.valueOf(c+2))));
                DriverAction.waitSec(3);
                DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", startDate)));
                DriverAction.click(By.xpath(Course_Locators.calendar.replace("itr", String.valueOf(c+3))));
                DriverAction.waitSec(3);
                DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", endDate)));
                DriverAction.waitSec(2);
                DriverAction.click(Course_Locators.updateBtn,"Validate user successfully clicked on Update date button","User successfully clicked on Update button");
                DriverAction.waitSec(3);
                String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                System.out.println(popupMessage);
                String reqPopUpMessage="";
                reqPopUpMessage="Course already Completed";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Date of completed course cannot be updated", "Date of completed course not edited successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Date of completed course cannot be updated", "Date of completed course got edited which was not expected result", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    @When("Completed the assign course")
    public void completedTheAssignCourse() {
        try {
            //In this we are completing the course and downloading the certificate.
            DriverAction.waitSec(7);
            DriverAction.scrollToBottom();
            DriverAction.click(LearnerModule_Locators.viewCourseBtn,"Validate Learner able to click on View course button","Learner successfully clicked on View Course button");
                if (DriverAction.getElement(LearnerModule_Locators.startCourseBtn).isDisplayed()) {
                    DriverAction.click(LearnerModule_Locators.startCourseBtn,"Validate Learner started the course successfully","Learner started the course successfully");
                    DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn), 90);
                    if (DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn)) {
                        DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                        DriverAction.waitSec(2);
                        String popUp = DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                        if ("Content Completed Successfully".equals(popUp)) {
                            GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Successfully " + popUp + " appears", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }

                    }
                    //assignment
                    DriverAction.waitSec(5);
                    DriverAction.typeText(LearnerModule_Locators.answerArea, "demo_content");
                    DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn), 90);
                    if (DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn)) {
                        DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                        String popUp = DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                        DriverAction.waitSec(5);
                        System.out.println("hello");
                        System.out.println(popUp);
                        System.out.println("hello");
                        if ("Assignment Completed Successfully".equals(popUp)) {
                            GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Successfully " + popUp + " appears", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }

 }
        }else {
                GemTestReporter.addTestStep("Verify Start button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }


            DriverAction.waitSec(10);


        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Keep the course in Progress")
    public void keepTheCourseInProgress() {
        try {
            //keep the course in progress
            DriverAction.waitSec(7);
            DriverAction.scrollToBottom();
            DriverAction.click(LearnerModule_Locators.viewCourseBtn, "Validate Learner able to click on View course button", "Learner successfully clicked on View Course button");
            if (DriverAction.getElement(LearnerModule_Locators.startCourseBtn).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.startCourseBtn, "Validate Learner started the course successfully", "Learner started the course successfully");
                DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn), 90);
                if (DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn)) {
                    DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                    DriverAction.waitSec(2);
                    String popUp = DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                    if ("Content Completed Successfully".equals(popUp)) {
                        GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Successfully " + popUp + " appears", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                    }

                }
            }
            DriverAction.click(Course_Locators.backBtn,"Validate user switch back from the course","User successfully switch back from the course");
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate user able to update the date for inprogress course")
    public void validateUserAbleToUpdateTheDateForInprogressCourse() {
        try{
            //Filter the inprogress course from status dropdown
            DriverAction.click(Course_Locators.statusDropdown,"Validate user clicked on Status dropdown","User successfully clicked on Status dropdown");
            DriverAction.click(By.xpath(Course_Locators.requiredOption.replace("input","In Progress")));
            DriverAction.waitSec(3);
            //edit the date
            LocalDate today = LocalDate.now();
            int day = today.getDayOfMonth();
            int day2 =day+1;
            String startDate = String.valueOf(day);
            String endDate = String.valueOf(day2);
            DriverAction.click(Course_Locators.calendarIcon,"Validate user able to click on Calendar icon","User successfully clicked on Calendar icon");
            int c=0;
            if(c==0)
            {
                DriverAction.click(By.xpath(Course_Locators.calendar.replace("itr", String.valueOf(c+2))));
                DriverAction.waitSec(3);
                DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", startDate)));
                DriverAction.click(By.xpath(Course_Locators.calendar.replace("itr", String.valueOf(c+3))));
                DriverAction.waitSec(3);
                DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", endDate)));
                DriverAction.waitSec(2);
                DriverAction.click(Course_Locators.updateBtn,"Validate user successfully clicked on Update date button","User successfully clicked on Update button");

                DriverAction.waitSec(4);
                //validate date is updated successfully
                String fetchDate=DriverAction.getElementText(Course_Locators.updatedDate);
                String [] dateArray=fetchDate.split("/");
                if(dateArray[0].equals(endDate))
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is updated successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is not updated successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Select the assign {string} Learner of different page change date of {string} and validate")
    public void selectTheAssignLearnerOfDifferentPageChangeDateOfAndValidate(String count, String test) {
        try{
            DriverAction.click(Course_Locators.backBtn1);
            DriverAction.waitSec(5);
            DriverAction.click(Tests_TestControl_Locators.threeDotIcon,"Validate user able to click on three dot icon to Assign Learners ","User successfully able to click on icon");
            if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input","Assign Learners")))) {
                DriverAction.click(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input","Assign Learners")),"Validate user able to select the Assign Learners","User successfully select Assign Learners");
            }
            int c=0;
            DriverAction.waitSec(5);
            for(int j=0;j<2;j++) {
                List<WebElement> candidateList = DriverAction.getElements(Tests_TestControl_Locators.candidateList);
                for (int i = 0; i < candidateList.size(); i++) {
                    String getStatus = " ";
                    if (test.equals("test")) {
                        getStatus = DriverAction.getElementText(By.xpath(Tests_TestControl_Locators.candidateStatus.replace("itr", String.valueOf(i + 1))));
                        if ("Assigned".equals(getStatus)) {
                            //check the unassigned user
                            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr", String.valueOf(i + 1))));

                            c++;
                            if (c>=1) {
                                //DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateAction.replace("itr",String.valueOf(i+1))));
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        getStatus = DriverAction.getElementText(By.xpath(Tests_TestControl_Locators.learnerStatus.replace("itr", String.valueOf(i + 1))));
                        if ("Unattempted".equals(getStatus)) {
                            //check the unassigned user
                            DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr", String.valueOf(i + 1))));

                            c++;
                            if (c>=1) {
                                //DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateAction.replace("itr",String.valueOf(i+1))));
                                break;
                            }
                        } else {
                            continue;
                        }
                    }

                }
                //select learner of 3rd screen
                for (int i = 0; i <2; i++) {
                    DriverAction.waitSec(3);
                    DriverAction.click(Course_Locators.rightPaginator, "Validate user able to click on right pagination", "User successfully clicked on right pagination");
                }
            }
            DriverAction.click(Course_Locators.changeDateBtn,"Validate user able to click on change date button","User able to click on button");

            //edit the date of selected assign leaners
            LocalDate today = LocalDate.now();
            int day = today.getDayOfMonth();
            int day2 =day+1;
            String startDate = String.valueOf(day);
            String endDate = String.valueOf(day2);
            DriverAction.click(Course_Locators.calendarIcon,"Validate user able to click on Calendar icon","User successfully clicked on Calendar icon");
            int c1=0;
            if(c1==0)
            {
                DriverAction.click(By.xpath(Course_Locators.calendar.replace("itr", String.valueOf(c+2))));
                DriverAction.waitSec(3);
                DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", startDate)));
                DriverAction.click(By.xpath(Course_Locators.calendar.replace("itr", String.valueOf(c+3))));
                DriverAction.waitSec(3);
                DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", endDate)));
                DriverAction.waitSec(2);
                DriverAction.click(Course_Locators.updateBtn,"Validate user successfully clicked on Update date button","User successfully clicked on Update button");
                DriverAction.click(Tests_TestControl_Locators.yesBtn,"Validate user able to click on  popup","User successfully able to click on popup");
                DriverAction.waitSec(4);
             //Validate date is change successfully

            }



        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate date is updated successfully in Paginating")
    public void validateDateIsUpdatedSuccessfullyInPaginating() {
        try{
            //validate updated date
            DriverAction.click(Course_Locators.backBtn1);
            DriverAction.waitSec(5);
            DriverAction.click(Tests_TestControl_Locators.threeDotIcon,"Validate user able to click on three dot icon to Assign Learners ","User successfully able to click on icon");
            if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input","Assign Learners")))) {
                DriverAction.click(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input","Assign Learners")),"Validate user able to select the Assign Learners","User successfully select Assign Learners");
            }
            LocalDate today = LocalDate.now();
            int day = today.getDayOfMonth();
            int day2 =day+1;
            String startDate = String.valueOf(day);
            String endDate = String.valueOf(day2);
            for(int i=0;i<2;i++)
            {
                String fetchDate=DriverAction.getElementText(Course_Locators.updatedDate);
                String [] dateArray=fetchDate.split("/");
                if(dateArray[0].equals(endDate))
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is updated successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is not updated successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
                //select learner of 3rd screen
                for (int j = 0; j<2; j++) {
                    DriverAction.waitSec(3);
                    DriverAction.click(Course_Locators.rightPaginator, "Validate user able to click on right pagination", "User successfully clicked on right pagination");
                }

            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @Then("Validate course Status")
    public void validateCourseStatus() {
        try {
            int inprogressCounter = 0;
            int expiredCounter = 0;
            int completedCounter = 0;
            int yetToStartCounter = 0;

            String courseStatus = DriverAction.getElementText(Course_Locators.courseStatus);
            List<WebElement> courseStatusList = DriverAction.getElements(Course_Locators.courseStatusList);
            for (int i = 0; i < courseStatusList.size(); i++) {
                String content = DriverAction.getElementText(By.xpath(Course_Locators.courseContent.replace("itr", String.valueOf(i + 1))));
                if (content.equals("Contents")) {
                    DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr", String.valueOf(i + 1))));
                    if (courseStatus.equals("Expired")) {
                        String status = DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                        if (status.equals("Unattempted") || status.equals("Completed")) {
                            expiredCounter++;
                            GemTestReporter.addTestStep("Validate Expired Content should have the status unattempted", "Expired course is " + status,
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Expired Content should have the status unattempted", "Expired course is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else if (courseStatus.equals("In Progress")) {
                        String status = DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                        if (status.equals("Unattempted") || status.equals("Completed") || status.equals("Pass") || status.equals("Fail")) {
                            inprogressCounter++;
                            GemTestReporter.addTestStep("Validate In Progress Content should have the status unattempted or Completed", "In progress course is " + status,
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate In Progress Content should have the status unattempted or Completed", "In progress course is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else if (courseStatus.equals("Yet to Start")) {
                        String status = DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                        if (status.equals("Unattempted")) {
                            yetToStartCounter++;
                            GemTestReporter.addTestStep("Validate Yet To Start Content should have the status unattempted", "Yet To Start course is " + status,
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Yet To Start Content should have the status unattempted", "Yet To Start course is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else {
                        String status = DriverAction.getElementText(Course_Locators.contentsStatusCompleted);
                        if (status.equals("Completed") || status.equals("Fail") || status.equals("Pass")) {
                            completedCounter++;
                            GemTestReporter.addTestStep("Validate Completed Content should have the status Completed", "Completed course content is completed successfully",
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Completed Content should have the status Completed", "Completed course is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    }
                } else if (content.equals("Assignments")) {
                    DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr", String.valueOf(i + 1))));
                    String status = DriverAction.getElementText(Course_Locators.assignmentsStatus);
                    if (courseStatus.equals("Expired")) {
                        if (status.equals("Unattempted") || status.equals("Completed")) {
                            expiredCounter++;
                            GemTestReporter.addTestStep("Validate Expired Assignment should have the status unattempted", "Expired Assignment is unattempted",
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {

                            GemTestReporter.addTestStep("Validate Expired Assignment should have the status unattempted", "Expired Assignment is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else if (courseStatus.equals("In Progress")) {
                        if (status.equals("Unattempted") || status.equals("Completed") || status.equals("Pass") || status.equals("Fail")) {
                            inprogressCounter++;
                            GemTestReporter.addTestStep("Validate In Progress Assignment should have the status unattempted or Completed", "In progress Assignment is " + status,
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate In Progress Assignment should have the status unattempted or Completed", "In progress Assignment is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else if (courseStatus.equals("Yet to Start")) {
                        if (status.equals("Unattempted")) {
                            yetToStartCounter++;
                            GemTestReporter.addTestStep("Validate Yet to Start Assignment should have the status unattempted", "Yet to Start Assignment is unattempted",
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Yet to Start Assignment should have the status unattempted", "Yet to Start Assignment is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else {
                        if (status.equals("Completed") || status.equals("Fail") || status.equals("Pass")) {
                            completedCounter++;
                            GemTestReporter.addTestStep("Validate Completed Assignment should have the status Completed", "Completed course Assignment is completed successfully",
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Completed Assignment should have the status Completed", "Completed Assignment is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    }
                } else {
                    DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr", String.valueOf(i + 1))));
                    String status = DriverAction.getElementText(Course_Locators.contentsStatusCompleted);
                    if (courseStatus.equals("Expired")) {
                        if (status.equals("Unattempted") || status.equals("Completed")) {
                            expiredCounter++;
                            GemTestReporter.addTestStep("Validate Expired Test should have the status unattempted", "Expired Test is unattempted",
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Expired Test should have the status unattempted", "Expired Test is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else if (courseStatus.equals("In Progress")) {
                        if (status.equals("Unattempted") || status.equals("Completed") || status.equals("Pass") || status.equals("Fail")) {
                            inprogressCounter++;
                            GemTestReporter.addTestStep("Validate In Progress Test should have the status unattempted,pass or fail", "In progress Test is " + status,
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate In Progress Test should have the status unattempted,pass or fail", "In progress Test is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else if (courseStatus.equals("Yet to Start")) {
                        if (status.equals("Unattempted")) {
                            yetToStartCounter++;
                            GemTestReporter.addTestStep("Validate Yet to Start Test should have the status unattempted", "Yet to Start Test is unattempted",
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Yet to Start Test should have the status unattempted", "Yet to Start Test is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else {
                        if (status.equals("Completed") || status.equals("Fail") || status.equals("Pass")) {
                            completedCounter++;
                            GemTestReporter.addTestStep("Validate Completed Test should have the status Pass Or Fail", "Completed Test is completed successfully",
                                    STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Completed Test should have the status Pass Or Fail", "Completed Test is not giving desired status",
                                    STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    }
                }

                DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr", String.valueOf(i + 1))));
            }
                //validate counter
                if (inprogressCounter == courseStatusList.size() || yetToStartCounter == courseStatusList.size() || completedCounter == courseStatusList.size() || expiredCounter == courseStatusList.size()) {
                    GemTestReporter.addTestStep("Validate Course status matched with its contents status", "Status matched successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Course status matched with its contents status", "Status not matched",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }


        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("Select {string}, {string} from sidebar and Validate {string} appears")
    public void selectFromSidebarAndValidateAppears(String module, String submodule, String screen) {
        try{
            //select module and submodule
            //open sidebar
            DriverAction.click(MyLocators.sidebar, "Expand the sidebar", "Sidebar expands displaying list of modules.");

            //select a module from sidebar
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", module)),"Validate user able to click on "+module,"User successfully clicked on "+module);


            //select submodule if required

            Thread.sleep(3000);

            if(!submodule.isEmpty()){
                DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", submodule)),"Validate user able to click on "+submodule,"User successfully clicked on "+submodule);
            }
            //close sidebar
            DriverAction.click(MyLocators.crossIcon, "Click the cross icon of sidebar", "Successfully clicked the cross icon.");

            //validate required screen appears
            Thread.sleep(3000);
            String screenHeader=DriverAction.getElementText(Course_Locators.actionHeading);
            if(screenHeader.equals(screen))
            {
                GemTestReporter.addTestStep("Validate user successfully directed to "+screenHeader, "User successfully directed to "+screenHeader,
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate user successfully directed to "+screenHeader, "User not able to directed to "+screenHeader,
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("Validate {string} on Add Assignment Screen")
    public void validateOnAddAssignmentScreen(String category) {
        try{
           //validate predefine category on Add assignment screen.
            if(DriverAction.isDisplayed(By.xpath(Course_Locators.assignmentInput.replace("input",category))))
            {
                GemTestReporter.addTestStep("Validate Selected category is displayed on Add assignment screen", "selected category is displayed on screen",
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate Selected category is displayed on Add assignment screen", "selected category is not displayed on screen",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("check the Has Test and Validate option appear")
    public void checkTheHasTestAndValidateOptionAppear() {
try{
    //in this we are filling the description
    if (DriverAction.isExist(Course_Locators.courseDescription,120)) {
        DriverAction.typeText(Course_Locators.courseDescription,"new Description","Validate user able to type in Course description input","User successfully typed new Description");
        DriverAction.scrollToBottom();
        DriverAction.click(Course_Locators.hasTestCheckbox,"Validate user able to click on Has Test Checkbox","User successfully clicked on Has Test checkbox");

        //Validate generate certificate anb Retake test checkbox appears

        if(DriverAction.isExist(Course_Locators.generateCertificateCheckbox)&&DriverAction.isExist(Course_Locators.allowRetakeTestCheckbox))
        {
            GemTestReporter.addTestStep("Validate Required checkboxes appear when user clicked on Has Test checkbox", "Generate Certificate and Allow Retake Test checkboxes appears successfully",
                    STATUS.PASS, DriverAction.takeSnapShot());
        }
        else
        {
            GemTestReporter.addTestStep("Validate Required checkboxes appear when user clicked on Has Test checkbox", "Generate Certificate and Allow Retake Test checkboxes not appears successfully",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
    else {
        GemTestReporter.addTestStep("Error Occur", "Fail to enter text in course description", STATUS.FAIL,
                DriverAction.takeSnapShot());
    }

    //check the Has test check box and valiad

}
catch (Exception e) {
    logger.info("Exception occurred", e);
    GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
}
    }

    @Then("Validate Automatic selection of Retake checkbox")
    public void validateAutomaticSelectionOfRetakeCheckbox() {
        try{
            //check generate certificate and validate Retake got checked automatically
            DriverAction.click(Course_Locators.generateCertificateCheckbox,"Validate user checked generate certificate checkbox","user successfully checked generate certificate checkbox");

            if(DriverAction.isExist(Course_Locators.checkedAllowRetakeTestCheckbox))
            {
                GemTestReporter.addTestStep("Validate Allow Retake Test checkbox is checked automatically when we checked Generate certificate checkbox", "Allow Retake checkbox is checked successfully",
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate Allow Retake Test checkbox is checked automatically when we checked Generate certificate checkbox", "Allow Retake checkbox is not checked successfully",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("Validate all fields exists in {string} section")
    public void validateAllFieldsExistsInSection(String section) {
        try{
            List<By> ele =new ArrayList<>();
            if(section.equals("Add Course Info"))
            {
                List<String> inputLabel= Arrays.asList("courseName", "ddown1","duration","getFile","ddown2");

                ele.add(Tests_TestControl_Locators.backBtn1);
                ele.add(Course_Locators.courseDescription);
                ele.add(By.xpath(Utils_Locators.button.replace("buttonName","Add Content")));
                ele.add(By.xpath(Course_Locators.inputLabel.replace("input","Select Category*")));
                ele.add(By.xpath(Course_Locators.inputLabel.replace("input","Course Tag*")));
                ele.add(Utils_Locators.unCheckedCheckbox);
                ele.add(Utils_Locators.unCheckedCheckbox2);
                for(int i=0;i< inputLabel.size();i++)
                {
                    ele.add(By.xpath(Utils_Locators.input.replace("label",inputLabel.get(i))));
                }

                //call boolean method to validate weather elements are present or not
                boolean isTrue=test.isEverythingRequiredPresent(ele);

                if(isTrue)
                {
                    GemTestReporter.addTestStep("Validate all required elements are present on "+section+" Screen", "All elements are present successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate all required elements are present on "+section+" Screen", "All elements are not present successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else if(section.equals("Add Test Info"))
            {
                List<String> inputLabel=Arrays.asList("testName", "testTag","inputMask","ddownLevel");

                ele.add(Tests_TestControl_Locators.backBtn1);
                ele.add(Course_Locators.courseDescription);
                ele.add(By.xpath(Tests_TestControl_Locators.buttonName.replace("input"," Next ")));
                ele.add(Utils_Locators.checkedCheckbox);
                for(int i=0;i< inputLabel.size();i++)
                {
                    ele.add(By.xpath(Utils_Locators.input.replace("label",inputLabel.get(i))));
                }

                //call boolean method to validate weather elements are present or not
                boolean isTrue=test.isEverythingRequiredPresent(ele);

                if(isTrue)
                {
                    GemTestReporter.addTestStep("Validate all required elements are present on "+section+" Screen", "All elements are present successfully", STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate all required elements are present on "+section+" Screen", "All elements are not present successfully", STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else if(section.equals("Select Options"))
            {

            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("Select option for internal Test")
    public void selectOptionForInternalTest() {
        try{
            String inputValues[] = {"Yes","Yes","Yes"};
            for(int i=2;i<=4;i++)
            {
                DriverAction.click(By.xpath(Course_Locators.dropdownIcon.replace("itr", String.valueOf(i))),"Validate user able to click dropdown","User successfully clicked on dropdown");
                DriverAction.click(By.xpath(Course_Locators.option.replace("input", inputValues[i - 2])),"Validate user able to select the option","User successfully able to select "+inputValues[i-2]);
            }
            //select next button
            String buttonName=" Next ";
            DriverAction.click(By.xpath(Tests_TestControl_Locators.buttonName.replace("input",buttonName)),"Validate user able to click on "+buttonName,"User successfully clicked on "+buttonName);



        }
        catch(Exception e){
            GemTestReporter.addTestStep("Click Save & Next","Exception encountered- "+e,STATUS.ERR);
        }
    }


    @And("save and publish course with Internal Test")
    public void saveAndPublishCourseWithInternalTest() {
        try{
            DriverAction.click(By.xpath(MyLocators.button.replace("input","Default Order")),"Validate user able to click on Default Order","User successfully clicked on Default Order");
            DriverAction.click(By.xpath(Utils_Locators.button1.replace("buttonName","Save Course & Publish")),"Validate user able to click on Save Course & Publish","User successfully clicked on Save Course & Publish");
            DriverAction.click(By.xpath(Utils_Locators.button1.replace("buttonName","Yes")),"Validate user able to click on Yes popup","User successfully clicked on Yes popup");
            DriverAction.waitSec(2);
//           DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
            String popUpMessage=DriverAction.getElementText(By.xpath("(//div[contains(@class,'p-toast-message')])[3]"));

            if("Course Created Successfully".equals(popUpMessage))
            {
                GemTestReporter.addTestStep("Course is drafted","course is drafted successfully", STATUS.PASS, DriverAction.takeSnapShot());
            }
            else {
                GemTestReporter.addTestStep("Course is drafted","course is not drafted", STATUS.FAIL, DriverAction.takeSnapShot());

            }

        }
        catch(Exception e){
            GemTestReporter.addTestStep("Click Save & Next","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("Complete the course and {string} the test when retake button is {string}")
    public void completeTheCourseAndTheTest(String status,String checked) {
        try{
        //complete the course
        DriverAction.scrollToBottom();
        if (DriverAction.getElement(LearnerModule_Locators.viewCourseBtn).isDisplayed()) {
            GemTestReporter.addTestStep("Verify View Course button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.scrollToBottom();
            DriverAction.waitSec(3);
            DriverAction.click(LearnerModule_Locators.viewCourseBtn);


            //validate no of test in course
            List<WebElement> testList = DriverAction.getElements(Tests_TestControl_Locators.testList);


            //start the course
            if (DriverAction.getElement(LearnerModule_Locators.startCourseBtn).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.startCourseBtn);

                DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn), 90);
                if (DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn)) {
                    DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                    DriverAction.waitSec(3);
                        if (DriverAction.getElement(LearnerModule_Locators.popupDiv).isDisplayed()) {
                            GemTestReporter.addTestStep("Verify confirmation popup message", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify confirmation popup message", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                        if (DriverAction.isDisplayed(LearnerModule_Locators.greenTick)) {
                            GemTestReporter.addTestStep("Verify green tick once the module is completed", "Successful", STATUS.PASS, DriverAction.takeSnapShot());

                        } else {
                            GemTestReporter.addTestStep("Verify green tick once the module is completed", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

                        }
                }

                for (WebElement element : testList) {
                    //test
                    if (DriverAction.getElement(LearnerModule_Locators.yesBtn).isDisplayed()) {
                        DriverAction.click(LearnerModule_Locators.yesBtn);
                    }
                    //next btn click with vedio
                    //click the next button to forward the vedio.
                    if(DriverAction.isDisplayed(By.xpath(MyLocators.nextBtn.replace("input", "NEXT"))))
                    {
                        DriverAction.waitUntilElementClickable(By.xpath(MyLocators.nextBtn.replace("input", "NEXT")), 90);
                        DriverAction.click(By.xpath(MyLocators.nextBtn.replace("input", "NEXT")), "Click the NEXT button displayed in video", "Successfully clicked the NEXT button displayed in video.");
                    }

                    //check the instruction checkbox
                    DriverAction.click(MyLocators.instructionsCheckbox);
                    GemTestReporter.addTestStep("Check the instructions checkbox", "Successfully checked the instructions checkbox.", STATUS.PASS);
                    DriverAction.click(By.xpath(MyLocators.button.replace("input", "NEXT")));

                    //verify dailogue box appear
                    if (DriverAction.isExist(MyLocators.startTestDialog)) {
                        GemTestReporter.addTestStep("Verify confirmation dialog box appears", "Successfully verified the confirmations dialog box.", STATUS.PASS);
                    } else {
                        GemTestReporter.addTestStep("Verify confirmation dialog box appears", "Could not verify confirmation dialog box.", STATUS.FAIL);
                    }
                    DriverAction.click(MyLocators.yesBtn, "Click the yes button", "Successfully clicked Yes button.");
                    //click attempt button
                    DriverAction.click(By.xpath(MyLocators.button.replace("input", "Attempt")));

                    //valiadte if vedio pop appears
                    if (DriverAction.isDisplayed(Course_Locators.crossIcon)) {
                        DriverAction.click(Course_Locators.crossIcon, "Validate user able to click on cross icon", "user successfully exit the movie clip");
                    }

                    //expand section
                    DriverAction.click(MyLocators.sectionsDropdown, "Click the sections dropdown", "Sections dropdown is expanded successfully.");

                    List<WebElement> sections = DriverAction.getElements(MyLocators.totalSections);

                    int numOptions = sections.size();
                    if(status.equals("Pass")) {
                        for (int k = 0; k < numOptions; k++) {
                            if (k != 0) {
                                DriverAction.click(MyLocators.sectionsDropdown, "Click the sections dropdown", "Sections dropdown is expanded successfully.");

                            }
//                        DriverAction.waitSec(2);
                            DriverAction.click(sections.get(k));

                            int totalQues = DriverAction.getElements(MyLocators.paletteBtn).size();

//                        if(status.equals("Pass"))
//                        {

                            for (int i = 0; i < totalQues; i++) {
                                //  enterAnswer();
                                //valiadte if vedio pop appears
                                if (DriverAction.isDisplayed(Course_Locators.crossIcon)) {
                                    DriverAction.click(Course_Locators.crossIcon, "Validate user able to click on cross icon", "user successfully exit the movie clip");
                                }
                                if (DriverAction.isDisplayed(MyLocators.textarea)) {
                                    DriverAction.typeText(MyLocators.textarea, "abc");
                                    GemTestReporter.addTestStep("Enter answer in input field", "Successfully entered the answer in input field", STATUS.PASS);
                                } else if (DriverAction.isDisplayed(MyLocators.mcqOptions)) {
                                    DriverAction.click(MyLocators.selectOption, "Select an option");
                                    GemTestReporter.addTestStep("Select an answer", "Successfully selected an answer.", STATUS.PASS);
                                } else if (DriverAction.isDisplayed(MyLocators.radioBtn)) {
                                    List<WebElement> tempRadioBtns = DriverAction.getElements(MyLocators.radioBtn);
                                    DriverAction.click(tempRadioBtns.get(0), "Select an option");
                                    GemTestReporter.addTestStep("Select an answer", "Successfully selected an answer.", STATUS.PASS);
                                } else {
                                    DriverAction.click(MyLocators.selectCheckbox, "Select an option");
                                    GemTestReporter.addTestStep("Select an answer", "Successfully selected the answer", STATUS.PASS);
                                }
                                DriverAction.scrollToBottom();
                                DriverAction.click(By.xpath(MyLocators.button.replace("input", "Save & Next")));
                                //clickTheButton("Save & Next");

                            }
                            sections = DriverAction.getElements(MyLocators.totalSections);
                        }
                        //Finish test
//                DriverAction.click(By.xpath(MyLocators.button.replace("input", "Finish Test")));
                        DriverAction.waitSec(5);
                        try {
                            // Create a Robot instance
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_ESCAPE);
                            robot.keyRelease(KeyEvent.VK_ESCAPE);
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(status.equals("Fail"))
                    {
                        //Finish test
//                DriverAction.click(By.xpath(MyLocators.button.replace("input", "Finish Test")));
                        DriverAction.waitSec(5);
                        try {
                            // Create a Robot instance
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_ESCAPE);
                            robot.keyRelease(KeyEvent.VK_ESCAPE);
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    }



                    if (DriverAction.isExist(LearnerModule_Locators.finishSubmit)) {
                        DriverAction.click(LearnerModule_Locators.finishSubmit, "Finish and Submit button is visible on ui", "successfully clicked Finish and Submit button.");
                    } else {
                        GemTestReporter.addTestStep("Finish and Submit button is visible on ui", "Finish and Submit button is not visible on ui.", STATUS.FAIL);
                    }
                    DriverAction.waitSec(5);
                    DriverAction.scrollToBottom();
                    //validate Retake button
                    if (checked.equals("True")) {
                        if (DriverAction.isDisplayed(By.xpath(Utils_Locators.button.replace("buttonName", "Retake Test")))) {
                            GemTestReporter.addTestStep("Validate Retake button is present on Test summary screen", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate Retake button is present on Test summary screen", "Not present on Test summary screen", STATUS.FAIL, DriverAction.takeSnapShot());

                        }
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Validate Retake button is not present on Test summary screen as we have not checked it during creation of course", "Not present on Test summary screen", STATUS.PASS, DriverAction.takeSnapShot());

                    }
                    if (DriverAction.isExist(LearnerModule_Locators.proceedBtn)) {
                        DriverAction.click(LearnerModule_Locators.proceedBtn, "proceed button is visible on ui", "successfully clicked proceed button.");
                    } else {
                        GemTestReporter.addTestStep("proceed button is visible on ui", "proceed button is not visible on ui.", STATUS.FAIL);
                    }
                    DriverAction.waitSec(5);

                    DriverAction.click(LearnerModule_Locators.backtoCourse, "back to course button is visible on ui", "successfully clicked back to course button.");
                    DriverAction.waitSec(5);
                                    if(DriverAction.isDisplayed(Course_Locators.secondTest))
                {
                    DriverAction.click(Course_Locators.secondTest,"Validate user able to select second test","User successfully select the second test");
                }
                }
//                if(DriverAction.isDisplayed(Course_Locators.secondTest))
//                {
//                    DriverAction.click(Course_Locators.secondTest,"Validate user able to select second test","User successfully select the second test");
//                    if (DriverAction.getElement(LearnerModule_Locators.yesBtn).isDisplayed()) {
//                        DriverAction.click(LearnerModule_Locators.yesBtn);
//                    }
//                    //check the instruction checkbox
//                    DriverAction.click(MyLocators.instructionsCheckbox);
//                    GemTestReporter.addTestStep("Check the instructions checkbox", "Successfully checked the instructions checkbox.", STATUS.PASS);
//                    DriverAction.click(By.xpath(MyLocators.button.replace("input", "NEXT")));
//                    if (DriverAction.isExist(MyLocators.startTestDialog)) {
//                        GemTestReporter.addTestStep("Verify confirmation dialog box appears", "Successfully verified the confirmations dialog box.", STATUS.PASS);
//                    } else {
//                        GemTestReporter.addTestStep("Verify confirmation dialog box appears", "Could not verify confirmation dialog box.", STATUS.FAIL);
//                    }
//                    DriverAction.click(MyLocators.yesBtn, "Click the yes button", "Successfully clicked Yes button.");
//                    //click attempt button
//                    DriverAction.click(By.xpath(MyLocators.button.replace("input", "Attempt")));
//
//                    if(DriverAction.isDisplayed(Course_Locators.crossIcon))
//                    {
//                        DriverAction.click(Course_Locators.crossIcon,"Validate user able to click on cross icon","user successfully exit the movie clip");
//                    }
//                    //expand section
//                    DriverAction.click(MyLocators.sectionsDropdown, "Click the sections dropdown", "Sections dropdown is expanded successfully.");
//                     sections = DriverAction.getElements(MyLocators.totalSections);
//                     numOptions = sections.size();
//                    for (int k = 0; k < numOptions; k++) {
//                        if (k != 0) {
//                            DriverAction.click(MyLocators.sectionsDropdown, "Click the sections dropdown", "Sections dropdown is expanded successfully.");
//
//                        }
////                        DriverAction.waitSec(2);
//                        DriverAction.click(sections.get(k));
//                        int totalQues = DriverAction.getElements(MyLocators.paletteBtn).size();
//                        for (int i = 0; i < totalQues; i++) {
//                            //  enterAnswer();
//                            if (DriverAction.isDisplayed(MyLocators.textarea)) {
//                                DriverAction.typeText(MyLocators.textarea, "abc");
//                                GemTestReporter.addTestStep("Enter answer in input field", "Successfully entered the answer in input field", STATUS.PASS);
//                            } else if (DriverAction.isDisplayed(MyLocators.mcqOptions)) {
//                                DriverAction.click(MyLocators.selectOption, "Select an option");
//                                GemTestReporter.addTestStep("Select an answer", "Successfully selected an answer.", STATUS.PASS);
//                            }
//                            else if(DriverAction.isDisplayed(MyLocators.radioBtn))
//                            {
//                                DriverAction.click(MyLocators.radioBtn, "Select an option");
//                                GemTestReporter.addTestStep("Select an answer", "Successfully selected an answer.", STATUS.PASS);
//                            }
//                            else {
//                                DriverAction.click(MyLocators.selectCheckbox, "Select an option");
//                                GemTestReporter.addTestStep("Select an answer", "Successfully selected the answer", STATUS.PASS);
//                            }
//                            DriverAction.scrollToBottom();
//                            DriverAction.click(By.xpath(MyLocators.button.replace("input", "Save & Next")));
//                            //clickTheButton("Save & Next");
//
//                        }
//                        sections = DriverAction.getElements(MyLocators.totalSections);
//                    }
                    //validate Retake button
//                    if(status.equals("Fail")&&checked.equals("true"))
//                    {
//                        if(DriverAction.isDisplayed(By.xpath(Utils_Locators.button.replace("buttonName","Retake Test"))))
//                        {
//                            GemTestReporter.addTestStep("Validate Retake button is present on Test summary screen", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
//                        }
//                        else
//                        {
//                            GemTestReporter.addTestStep("Validate Retake button is present on Test summary screen", "Not present on Test summary screen", STATUS.FAIL, DriverAction.takeSnapShot());
//
//                        }
//                    }
//
//
//                    if (DriverAction.isExist(LearnerModule_Locators.proceedBtn)) {
//                        DriverAction.click(LearnerModule_Locators.proceedBtn, "proceed button is visible on ui", "successfully clicked proceed button.");
//                    } else {
//                        GemTestReporter.addTestStep("proceed button is visible on ui", "proceed button is not visible on ui.", STATUS.FAIL);
//                    }
//                    DriverAction.waitSec(5);
//
//                    DriverAction.click(LearnerModule_Locators.backtoCourse, "back to course button is visible on ui", "successfully clicked back to course button.");
//                    DriverAction.waitSec(5);




            }
        }

    }
        catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
    }
    }

    @And("Validate certificate after completion of course when user {string} and option is {string}")
    public void validateCertificateAfterCompletionOfCourse(String status,String checked) {
        try{
            String filePath="";

            if(checked.equals("False")) {

                //validate download certificate button
                DriverAction.waitUntilElementIsClickable(By.xpath(Utils_Locators.button1.replace("buttonName", "Download Certificate")));
                DriverAction.click(By.xpath(Utils_Locators.button1.replace("buttonName", "Download Certificate")), "Validate user able to click on download certificate after completing course");


                String directoryPath = "C:/Users/rahul.adhikari/Downloads/";
                File directory = new File(directoryPath);
                if (directory.exists() && directory.isDirectory()) {
                    File latestfile = getLatestFile(directory);
                    if (latestfile != null) {
                        System.out.println("Latest File : " + latestfile.getAbsolutePath());
                        filePath = latestfile.getAbsolutePath();
                        if (filePath.contains("certificate")) {
                            GemTestReporter.addTestStep("Validate certificate is downloaded", "User successfully able to download certificate", STATUS.PASS, DriverAction.takeSnapShot());

                        } else {
                            GemTestReporter.addTestStep("Validate certificate is downloaded", "User not able to download certificate", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    }
                }
            }
            else if(status.equals("Pass")&&checked.equals("True"))
            {
                //validate download certificate button
                DriverAction.waitUntilElementIsClickable(By.xpath(Utils_Locators.button1.replace("buttonName", "Download Certificate")));
                DriverAction.click(By.xpath(Utils_Locators.button1.replace("buttonName", "Download Certificate")), "Validate user able to click on download certificate after completing course");


                String directoryPath = "C:/Users/rahul.adhikari/Downloads/";
                File directory = new File(directoryPath);
                if (directory.exists() && directory.isDirectory()) {
                    File latestfile = getLatestFile(directory);
                    if (latestfile != null) {
                        System.out.println("Latest File : " + latestfile.getAbsolutePath());
                        filePath = latestfile.getAbsolutePath();
                        if (filePath.contains("certificate")) {
                            GemTestReporter.addTestStep("Validate certificate is downloaded", "User successfully able to download certificate", STATUS.PASS, DriverAction.takeSnapShot());

                        } else {
                            GemTestReporter.addTestStep("Validate certificate is downloaded", "User not able to download certificate", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    }
                }
            }
            else
            {
               if(DriverAction.isDisplayed(By.xpath(Utils_Locators.button1.replace("buttonName", "Download Certificate"))))
               {
                   GemTestReporter.addTestStep("Validate certificate download for failed test", "User successfully able to download certificate", STATUS.FAIL, DriverAction.takeSnapShot());

               } else {
                   GemTestReporter.addTestStep("Validate certificate download for failed test", "User not able to download certificate", STATUS.PASS, DriverAction.takeSnapShot());
               }
            }

            //switch back to course screen
            DriverAction.waitSec(3);
            if(DriverAction.isDisplayed(By.xpath(MyLocators.optionSelected.replace("input","Courses"))))
            {
                DriverAction.click(By.xpath(MyLocators.optionSelected.replace("input","Courses")),"Validate user able to switch back to course screen","user successfully switched to course screen");
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    //get the latest modified file
    public static File getLatestFile(File directory) {
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            return null;
        }
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }


    @And("Switched to completed course section and validate view or download button functionality {string} {string}")
    public void switchedToCompletedCourseSectionAndValidateViewDownloadButtonFunctionality(String status, String checked) {
        try{
            //switch the tab
            if(DriverAction.isDisplayed(By.xpath(MyLocators.optionSelected.replace("input","Completed"))))
            {
                DriverAction.click(By.xpath(MyLocators.optionSelected.replace("input","Completed")),"User able to switch to completed section","User successfully switched to completed section");
            }
            if(DriverAction.isExist(Tests_TestControl_Locators.completedTabthreeDotIcon,120))
            {
                DriverAction.click(Tests_TestControl_Locators.completedTabthreeDotIcon);
                DriverAction.waitSec(3);
                if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input", "View/Download Certificate")))) {
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input", "View/Download Certificate")));
                    if ((checked.equals("True") && status.equals("Pass"))||checked.equals("False")) {
                        if (DriverAction.isDisplayed(Course_Locators.certificateLabel)) {

                            GemTestReporter.addTestStep("Validate view/download certificate options is present for the completed course", "View/Download certificate option is present for the course", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate view/download certificate options is present for the completed course", "View/Download certificate option is not present for the course", STATUS.FAIL, DriverAction.takeSnapShot());

                        }
                    } else if (checked.equals("True") && status.equals("Fail")) {
                        if (DriverAction.isDisplayed(Course_Locators.certificateLabel)) {
                            GemTestReporter.addTestStep("Validate view/download certificate options is should not present for the completed course", "View/Download certificate option is present for the course", STATUS.FAIL, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Validate view/download certificate options is should not present for the completed course", "View/Download certificate option is not present for the course", STATUS.PASS, DriverAction.takeSnapShot());

                        }
                    }
                }

            }
            //navigate back
            if(DriverAction.isExist(LearnerModule_Locators.backBtn1,120))
            {
                DriverAction.click(LearnerModule_Locators.backBtn1);
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @Then("validate course summary screen for Retake button {string} {string}")
    public void validateCourseSummaryScreenForRetakeButton(String status, String checked) {
        try{
            //switch the tab
            if(DriverAction.isDisplayed(By.xpath(MyLocators.optionSelected.replace("input","Completed"))))
            {
                DriverAction.click(By.xpath(MyLocators.optionSelected.replace("input","Completed")),"User able to switch to completed section","User successfully switched to completed section");
            }
            if(DriverAction.isExist(Tests_TestControl_Locators.completedTabthreeDotIcon,120))
            {
                DriverAction.click(Tests_TestControl_Locators.completedTabthreeDotIcon);
                DriverAction.waitSec(3);
                if (DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input", "Course Summary")))) {
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.threeDotOption.replace("input", "Course Summary")));

                    //validate no of test in course
                    List<WebElement> testList = DriverAction.getElements(Tests_TestControl_Locators.testList);
                    List<WebElement> testStatus = DriverAction.getElements(Course_Locators.testStatus);
                    List<WebElement> retakeButton = DriverAction.getElements(Course_Locators.retakeButton);
                    if(status.equals("Fail")&&checked.equals("True")) {
                        if (testList.size() == 2) {
                            String fetchedStatus = testStatus.get(2).getText();
                            if (fetchedStatus.equals("Fail")) {
                                if (retakeButton.size()==2) {
                                    GemTestReporter.addTestStep("Validate retake button for failed test on course summary screen", "Retake button for failed test is there on course summary screen", STATUS.PASS, DriverAction.takeSnapShot());
                                } else {
                                    GemTestReporter.addTestStep("VValidate retake button for failed test on course summary screen", "Retake button for failed test is not there on course summary screen", STATUS.FAIL, DriverAction.takeSnapShot());
                                }
                            }

                        }
                    }
                    else if(status.equals("Pass")&&checked.equals("True"))
                    {
                        if (testList.size() == 2) {
                            if (retakeButton.size()>0) {
                                    GemTestReporter.addTestStep("Validate retake button for passed test on course summary screen", "Retake button for passed test is there on course summary screen", STATUS.FAIL, DriverAction.takeSnapShot());
                                } else {
                                    GemTestReporter.addTestStep("Validate retake button for passed test on course summary screen", "Retake button for passed test is not there on course summary screen", STATUS.PASS, DriverAction.takeSnapShot());
                                }
                        }
                    }
                    else
                    {
                        if (testList.size() == 2) {
                            if (retakeButton.size()>0) {
                                GemTestReporter.addTestStep("Validate retake button for test in which retake option is unchecked should not be there on course summary screen", "Retake button is present on course summary screen", STATUS.FAIL, DriverAction.takeSnapShot());
                            } else {
                                GemTestReporter.addTestStep("Validate retake button for test in which retake option is unchecked should not be there on course summary screen", "Retake button  is not there on course summary screen", STATUS.PASS, DriverAction.takeSnapShot());
                            }
                        }
                    }

                    }

            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }
}
