package com.qa.athenaUi.stepdefinitions;

import com.qa.athenaUi.locators.*;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.gemini.gemjar.utils.ui.DriverManager;
import com.qa.athenaUi.locators.*;
import io.cucumber.java.en.And;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Course {
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);
    public  String _courseName=" ";
    String _assignedCourseName=" ";
    int _ActiveCourseCount=0;
    int _viewListActiveCount=0;

    String _firstCourseName="";
    String _currentCourse;
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
                                GemTestReporter.addTestStep("Verify the owner", "Successful Matched "+req_userName+" with "+ownerName, Status.PASS, DriverAction.takeSnapShot());
                            }
                            else
                            {
                                GemTestReporter.addTestStep("Verify the owner", "UnSuccessful Matched"+req_userName+" with "+ownerName, Status.FAIL, DriverAction.takeSnapShot());

                            }
                        }

                    }
                }
            }
            else
            {
                GemTestReporter.addTestStep("Profile dropdown visibility", "No Profile dropdown is there on ui", Status.FAIL, DriverAction.takeSnapShot());

            }



        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }
    @Then("^Verify course info tree for assignment and test checkbox$")
    public void treeInfo(){
        DriverAction.scrollToBottom();
        try{
//            List<WebElement> list1 = DriverAction.getElements(By.xpath(LearnerModule_Locators.courseArea.replace("itr", String.valueOf(i))));
           //in this function we are checking the course info tree
//           boolean isPresent=false;
//           boolean isPresent1=false;
//           DriverAction.waitUntilElementAppear(By.xpath("//span[text()='Add Course Info']"));
            DriverAction.scrollToBottom();
//             for(int i=1;i<=2;i++)
//            {
//                DriverAction.scrollIntoView(Course_Locators.checkboxs);
//                DriverAction.click(Course_Locators.checkboxs);
//            }
//            List<WebElement> TreeList=DriverAction.getElements(Course_Locators.treeList);
//            for(int i=1;i<=TreeList.size();i++)
//            {
//               String fetchedItem=DriverAction.getElementText(By.xpath(Course_Locators.tree.replace("itr",String.valueOf(i)))) ;
//               if(fetchedItem.equals("Add Assignment"))
//               {
//                   isPresent=true;
//                }
//
//               if(fetchedItem.equals("Add Test"))
//               {
//                   isPresent1=true;
//               }
//
//            }
//            if(isPresent!=true)
//            {
//                GemTestReporter.addTestStep("Verify Add Assignment is added after selecting the checkbox","Unsuccessful", Status.FAIL, DriverAction.takeSnapShot());
//
//            }
//            else
//            {
//                GemTestReporter.addTestStep("Verify Add Assignment is added after selecting the checkbox", "Successfully selected ", Status.PASS, DriverAction.takeSnapShot());
//
//            }
//            if(isPresent1!=true)
//            {
//                GemTestReporter.addTestStep("Verify Add Test is added after selecting the checkbox","Unsuccessfull", Status.FAIL, DriverAction.takeSnapShot());
//
//            }
//            else
//            {
//                GemTestReporter.addTestStep("Verify Add Test is added after selecting the checkbox","Successfull", Status.PASS, DriverAction.takeSnapShot());
//
//            }

          boolean isPresent =   DriverAction.isExist(By.xpath("//*[@formcontrolname='hasAssignment']"));
          boolean isPresent2 =  DriverAction.isExist(By.xpath("//*[@formcontrolname='hasTest']"));

          if(isPresent == true && isPresent2==true) {
              GemTestReporter.addTestStep("elements exist", "assignment and test cehckboxes verfified", Status.PASS);
          }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }
    public static String generateRandomCourseName(int length) {
        String courseName = RandomStringUtils.randomAlphabetic(1); // Ensure the first character is a letter
        courseName += RandomStringUtils.randomAlphanumeric(length - 1); // Generate the rest of the string
        return courseName;
    }
    @Then("^Enter respective values in course fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" \"([^\"]*)\"$")
    public void enterAssignment(String courseType, String duration,String courseTag, String fileLocation, String category, int points) {
    try{
        int c=2;
        List<WebElement> inputFields= DriverAction.getElements(Course_Locators.courseInputFields);
         _courseName = generateRandomCourseName(10);
//        courseName= RandomStringUtils.randomAlphanumeric(10);
        String inputValues[]={_courseName,courseType,duration,courseTag,fileLocation,category};
        for(int i=0;i<=5;i++){
            DriverAction.waitSec(2);
            String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
            String upload=inputFields.get(i).getAttribute("type");
            //dropdown
            if(dropdown!=null&&dropdown.equals("listbox")){
                DriverAction.waitSec(2);
                DriverAction.click(By.xpath(Course_Locators.dropdownIcon.replace("itr",String.valueOf(c))));
                c++;
                DriverAction.waitSec(2);
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
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
    }
    }
    @And("^Enter course description \"([^\"]*)\"$")
    public void courseDescription(String description) {
        try{
            //in this we are filling the description
            if (DriverAction.isExist(Course_Locators.courseDescription)) {
                DriverAction.typeText(Course_Locators.courseDescription,description);
                DriverAction.waitSec(2);
                DriverAction.scrollToBottom();
            }
            else {
            GemTestReporter.addTestStep("Error Occur", "Fail to enter text in course description", Status.FAIL,
                    DriverAction.takeSnapShot());
        }
            DriverAction.waitSec(2);
            DriverAction.scrollToBottom();
            DriverAction.scrollIntoView(Course_Locators.checkboxs);
            DriverAction.waitUntilElementIsClickable(Course_Locators.checkboxs);
//            DriverAction.click(Course_Locators.checkboxs);
            DriverAction.waitSec(2);
            DriverAction.scrollToBottom();
        }catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Validate Filter functionality \"([^\"]*)\"$")
    public void filterData(String Name){
       try{
           //In this we are filter the data and verifying weather we are getting the expected result or not
           int c=0;
           if (DriverAction.isExist(Course_Locators.addContentTagInput))
           {
               DriverAction.typeText(Course_Locators.addContentTagInput,Name);
           }
//           DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);
           DriverAction.waitSec(5);

           List<WebElement> addContentTable=DriverAction.getElements(By.xpath("//*[@id=\"pr_id_94-table\"]/tbody/tr"));

           for(int i=1;i<addContentTable.size();i++)
           {
               String contentName=DriverAction.getElementText(By.xpath(Course_Locators.addContentTableRow.replace("itr",String.valueOf(i))));

           }
           if(c==addContentTable.size()-1)
           {
               GemTestReporter.addTestStep("Verify Filter by Content/Tag is working properly","Working Fine", Status.PASS, DriverAction.takeSnapShot());
           }
           else
           {
               GemTestReporter.addTestStep("Verify Filter by Content/Tag is working properly","Not working properly", Status.FAIL, DriverAction.takeSnapShot());
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
           GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
       }
    }

@Then("^Validate \"([^\"]*)\" Add to Course \"([^\"]*)\"$")
    public void addToCourse(String type,String message){
      try{
          //In this function we are first verifying the Add to course div is empty if not we are first deleting it.
          //And after that we are adding to Course.
          if (DriverAction.isExist(Course_Locators.addToCourseDiv))
{
    if (DriverAction.isExist(Course_Locators.addToCourseBtn))
    {
        if(DriverAction.isEnabled(Course_Locators.addToCourseBtn))
        {
            GemTestReporter.addTestStep("Initially Add to Course Btn should be disabled", "It is Enabled", Status.FAIL, DriverAction.takeSnapShot());
        }
        else
        {

            GemTestReporter.addTestStep("Initially Add to Course Btn should be disabled","It is Disabled", Status.PASS, DriverAction.takeSnapShot());
        }
    }



}
else
{//Delete the data from Add to course div
    List<WebElement> addCourseTable=DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
    int tableSize=addCourseTable.size();
    for(int i=1;i<tableSize;i++)
    {
        if (DriverAction.isExist(Course_Locators.deleteIcon)) {
            DriverAction.click(Course_Locators.deleteIcon,"Clicked on Delete Icon","Successfully clicked on delete Icon");
        } else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on delete icon", Status.FAIL,
                    DriverAction.takeSnapShot());
        }

     DriverAction.waitSec(3);
     if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input",type+" removed successfully"))))
     {
         GemTestReporter.addTestStep(type+" is deleted",type+" is deleted successfully", Status.PASS, DriverAction.takeSnapShot());
     }
     else {
         GemTestReporter.addTestStep("Error Occur", "popup does not appear", Status.FAIL,
                 DriverAction.takeSnapShot());
     }
    }
}
DriverAction.waitSec(5);
//if (DriverAction.isExist(Course_Locators.addIcon))
//{
    DriverAction.click(Course_Locators.addIcon,"Clicked on add Content Icon","Successfully clicked on Add Content Icon");
//}
//else {
//    GemTestReporter.addTestStep("Error Occur", "Fail to click on add Content Icon", Status.FAIL,
//            DriverAction.takeSnapShot());
//}

if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input",message))))
{
    GemTestReporter.addTestStep(type+" is added",type+" is added successfully", Status.PASS, DriverAction.takeSnapShot());
}
else {
    GemTestReporter.addTestStep("Error Occur", "popup does not appear", Status.FAIL,
            DriverAction.takeSnapShot());
}
//String getContent=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[1]//tr[1]//td[1]"));
//String contentAfterSelecting=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr[1]//td[1]"));
//if(getContent.equals(contentAfterSelecting))
//{
//    GemTestReporter.addTestStep(type+" is added to Course Div",type+" is added successfully", Status.PASS, DriverAction.takeSnapShot());
//}
//else
//{
//    GemTestReporter.addTestStep(type+" is added to Course Div",type+" is not added successfully", Status.FAIL, DriverAction.takeSnapShot());
//}
////if (DriverAction.isExist(Course_Locators.addToCourseBtn))
////{
////    DriverAction.click(Course_Locators.addToCourseBtn,"Clicked on Add to Course Button","Successfully clicked on Add to course Button");
////}
////else {
////    GemTestReporter.addTestStep("Error Occur", "Fail to click on Add to Course Button", Status.FAIL,
////            DriverAction.takeSnapShot());
////}
          DriverAction.waitSec(5);
          DriverAction.scrollIntoView(Course_Locators.addToCourseBtn);
          DriverAction.scrollToTop();
          DriverAction.waitUntilElementClickable(Course_Locators.addToCourseBtn, 120);
          DriverAction.click(Course_Locators.addToCourseBtn);
      }
      catch (Exception e) {
          logger.info("Exception occurred", e);
          GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
            DriverAction.waitSec(5);
//            DriverAction.isDisplayed(Course_Locators.courseSummaryDiv);
            DriverAction.click(Course_Locators.defaultOrderButton);
            DriverAction.waitUntilElementIsClickable(By.xpath(Course_Locators.button.replace("input","Save As Draft")));
            DriverAction.click(By.xpath(Course_Locators.button.replace("input","Save As Draft")),"Clicked on Save As Draft Button","Successfully clicked on Save As Draft button");
            DriverAction.waitUntilElementIsClickable(Course_Locators.courseTypeDropdown);
            DriverAction.click(Course_Locators.courseTypeDropdown);
            DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
            DriverAction.click(Course_Locators.draftOrPublishDropdown);
            DriverAction.waitSec(2);
            DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Draft")));
            DriverAction.waitSec(5);
            String fetchedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
            System.out.println(_courseName);
            System.out.println(fetchedCourseName);
            System.out.println(_courseName);
            if(fetchedCourseName.equals(_courseName))
            {
                GemTestReporter.addTestStep("Course is saved in draft and can be edit to publish finally","Successfully", Status.PASS, DriverAction.takeSnapShot());

            }
            else
            {
                GemTestReporter.addTestStep("Course is saved in draft and can be edit to publish finally","UnSuccessfully", Status.FAIL, DriverAction.takeSnapShot());

            }


            DriverAction.waitSec(10);
                DriverAction.click(Course_Locators.editIcon, "clicked on edit icon", "Successfully clicked");
                    DriverAction.click(Course_Locators.editOption, "clicked on edit option", "Successfully clicked");
                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon, 120);
//                    DriverAction.scrollToBottom();

                    DriverAction.waitSec(3);
                    DriverAction.click(Course_Locators.courseNameInput);
                    DriverAction.waitSec(2);
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

                    DriverAction.waitSec(2);
                    DriverAction.scrollToBottom();
                    DriverAction.scrollIntoView(Course_Locators.courseDescription);
                    DriverAction.click(Course_Locators.courseDescription);
                    DriverAction.waitSec(2);
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

                    //Edit the Has assignment checkbox
                    DriverAction.waitSec(2);
                    if(DriverAction.isDisplayed(Course_Locators.checkedAssignmentCheckbox))
                    {
                        DriverAction.click(Course_Locators.checkedAssignmentCheckbox);
                        DriverAction.waitSec(5);

                        DriverAction.click(Course_Locators.courseDescription);
                        if(DriverAction.isDisplayed(Course_Locators.checkboxs))
                        {
                            GemTestReporter.addTestStep("Has Assignment checkbox is editable","Successfully", Status.PASS, DriverAction.takeSnapShot());
                        //check the has Assignment checkbox
                            DriverAction.click(Course_Locators.checkboxs);
                        }
                        else
                        {
                            GemTestReporter.addTestStep("Has Assignment checkbox is editable","Fail to Edit", Status.FAIL, DriverAction.takeSnapShot());
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
                        DriverAction.waitSec(5);
//                        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);
                        if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Content removed successfully"))))
                        {
                            GemTestReporter.addTestStep("Content is deleted","content is deleted successfully", Status.PASS, DriverAction.takeSnapShot());
                        }
                    }
                    DriverAction.waitUntilElementAppear(Course_Locators.addIconEdit, 120);
                    DriverAction.click(Course_Locators.addIconEdit);

                    String getContentAfterEdit=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr[1]//td[1]"));
                    if(getContentBeforeEdit!=getContentAfterEdit)
                    {
                        GemTestReporter.addTestStep("Add Content is Editable","Successfully", Status.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Add Content is Editable","Fail to Edit", Status.FAIL, DriverAction.takeSnapShot());
                    }


                    DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add To Course")));
//                    DriverAction.click(Course_Locators.addToCourseBtn);
//                    DriverAction.click(Course_Locators.addIcon);
                    DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add To Course")));
//                    DriverAction.click(Course_Locators.addToCourseBtn);
                    DriverAction.click(By.xpath(Course_Locators.button.replace("input","Default Order")),"clicked on Default Order button","Successfully clicked on Default Order button");
//                    DriverAction.click(Course_Locators.defaultOrderButton);

                    //here we are checking the Save as Draft button functionality
                    DriverAction.scrollToBottom();
                    DriverAction.waitSec(3);
                    DriverAction.click(Course_Locators.saveAsDraftButton,"Clicked on Save As Draft Button","Successfully clicked on Save As Draft button");

//                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);

                    if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Course Updated Successfully"))))
                    {
                        GemTestReporter.addTestStep("Course is drafted","course is drafted successfully", Status.PASS, DriverAction.takeSnapShot());
                    }
                    else {
                        GemTestReporter.addTestStep("Course is drafted","course is not drafted", Status.FAIL, DriverAction.takeSnapShot());

                    }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
}
@Then("^Validate Course Summary Screen$")
    public void validateCourseSummary(){
       try {

           // In this we are validating Course Summary screen it's functionality all.

           DriverAction.scrollToTop();
           DriverAction.isDisplayed(Course_Locators.courseSummaryDiv);
           DriverAction.click(Course_Locators.defaultOrderButton);

           //here we are checking the Default order button functionality

           Thread.sleep(2000);
           List<WebElement> firstTable = DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[1]//tr"));
           int firstTableSize = firstTable.size();
           DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Default Order")), "clicked on Default Order button", "Successfully clicked on Default Order button");
           List<WebElement> secondTable = DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr"));
           int secondTableSize = secondTable.size();
           if (firstTableSize == secondTableSize) {
               GemTestReporter.addTestStep("Data added after clicking the Default Order button", "Successfully added the data", Status.PASS, DriverAction.takeSnapShot());
           } else {
               GemTestReporter.addTestStep("Data added after clicking the Default Order button", "Data is not added", Status.FAIL, DriverAction.takeSnapShot());
           }

           //here we are checking the reset button functionality
           DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Reset")), "Clicked on Reset Button", "Successfully clicked on Reset button");
       }
       catch (Exception e) {
           logger.info("Exception occurred", e);
           GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
       }
}
@When("^A Learner is assign to a course$")
    public void assignLearnerToCourse(){

        try {
            //Assign a Learner to a course
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            DriverAction.waitSec(5);
//            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);
         if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                _assignedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners"))));
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners")));

                    if(DriverAction.isDisplayed(Course_Locators.nameFilterInput))
                    {
                        DriverAction.waitSec(5);
                        DriverAction.typeText(Course_Locators.nameFilterInput,"pallavi.arora@geminisolutions.com");
                        DriverAction.waitSec(5);
//                        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);
                        DriverAction.click(Course_Locators.addIcon);

                    }
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
            DriverAction.click(By.xpath(Course_Locators.userOption.replace("input",role)));
        }
//        DriverAction.waitUntilElementDisappear(By.xpath("//*[@class='p-progress-spinner-svg']"));

    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
    }
}
@And("^Filter the course and complete it$")
    public void filterCompleteCourse(){
        try{
            //in this we are completing the course
            DriverAction.waitSec(8);
            if(DriverAction.isDisplayed(Course_Locators.courseFilterInput))
            {
                DriverAction.typeText(Course_Locators.courseFilterInput,_assignedCourseName);

            }
            DriverAction.waitSec(3);
            DriverAction.scrollToBottom();
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(LearnerModule_Locators.viewCourseBtn);
            DriverAction.scrollToBottom();
            if(DriverAction.isDisplayed(LearnerModule_Locators.viewCourseBtn))
            {
                DriverAction.click(LearnerModule_Locators.viewCourseBtn,"clicked on View Course Button","Successfully clicked on view course button");
                DriverAction.waitSec(3);
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
                DriverAction.waitSec(10);
                DriverAction.waitUntilElementAppear(By.xpath("//div[text()='ASSIGNMENT']"), 150);
                DriverAction.typeText(LearnerModule_Locators.answerArea, "demo_content");
                DriverAction.waitSec(2);
                DriverAction.scrollToBottom();
                DriverAction.scrollIntoView(LearnerModule_Locators.completeAndContinueBtn);
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
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
}
@Then("^Verify the Learner Report \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyLearnerReport(String learner,String email,String percentage,String Status_new){
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
                DriverAction.typeText(Course_Locators.nameTagFilterInput,"rahulll");
                DriverAction.scrollToBottom();
            }
            DriverAction.waitSec(5);
            List<WebElement> list1 =DriverAction.getElements(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr)[2]//td"));
            for (WebElement element : list1) {
                String text = element.getText();
                if (text.equals(learner)) {
                    c++;
                    GemTestReporter.addTestStep("Learner Name match the report", "Successfully", Status.PASS, DriverAction.takeSnapShot());

                }
                if (text.equals(email)) {
                    c++;
                    GemTestReporter.addTestStep("Learner Email match the report", "Successfully", Status.PASS, DriverAction.takeSnapShot());

                }
                if (text.equals(percentage)) {
                    c++;
                    GemTestReporter.addTestStep("Learner percentage is 100%,therefore completed the course", "Successfully", Status.PASS, DriverAction.takeSnapShot());

                }
                if (text.equals(Status_new)) {
                    c++;
                    GemTestReporter.addTestStep("Learner Status is completed", "Successfully", Status.PASS, DriverAction.takeSnapShot());

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
                    GemTestReporter.addTestStep("Completed Assignment and Content","Successfully", Status.PASS, DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Completed Assignment and Content","UnSuccessfully", Status.FAIL, DriverAction.takeSnapShot());

                }

    //


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
}

@When("^Reattempt the test to the user$")
    public void reattemptTest(){
        try{
            //in this function we checking reattempt functionality
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
                DriverAction.typeText(Course_Locators.nameTagFilterInput,"rahulll");

            }
//            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);
            DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
            if(DriverAction.isDisplayed(Course_Locators.reattemptLabel))
            {
                DriverAction.click(Course_Locators.reattemptLabel, "clicked on reattempt option", "Successfully clicked");
            }
//            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon);

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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

//            DriverAction.waitUntilElementAppear(By.xpath(MyLocators.button.replace("input", buttonName)));
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
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,Status.ERR);
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
        DriverAction.waitSec(2);
        DriverAction.scrollToTop();
        DriverAction.waitSec(2);
        if(DriverAction.isExist(Course_Locators.listViewBtn))
        {
            DriverAction.click(Course_Locators.listViewBtn);
        }
        else {
            GemTestReporter.addTestStep("Error occurred","Not able to click on List View Button", Status.FAIL, DriverAction.takeSnapShot());

        }
        DriverAction.scrollToBottom();
        List<String> viewList=new ArrayList<>();
        DriverAction.waitSec(5);
        DriverAction.scrollToBottom();
        if(DriverAction.isExist(Course_Locators.leftPaginator))
        {
            DriverAction.click(Course_Locators.leftPaginator);
        }
        else {
            GemTestReporter.addTestStep("Error occurred","Not able to click on left paginator", Status.FAIL, DriverAction.takeSnapShot());

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
                GemTestReporter.addTestStep("Validate count of Active Course in Course Library and View List","Count Matches", Status.PASS, DriverAction.takeSnapShot());

            }
            else {
                GemTestReporter.addTestStep("Validate count of Active Course in Course Library and View List","Count Not Matched", Status.FAIL, DriverAction.takeSnapShot());

            }
        }
        catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,Status.ERR);
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

                   if(DriverAction.isExist(Course_Locators.inActiveBtn))
                   {
                      DriverAction.click(Course_Locators.inActiveBtn);
                   }
                   else {
                       GemTestReporter.addTestStep("Error occurred","Not able to click on left paginator", Status.FAIL, DriverAction.takeSnapShot());

                   }


                }
            }
            DriverAction.waitSec(5);
            if(DriverAction.isDisplayed(Course_Locators.inActiveDiv))
            {
                GemTestReporter.addTestStep("Validate Course Inactive Successfully","Course get Inactive Successfully", Status.PASS, DriverAction.takeSnapShot());

            }
            else {
                GemTestReporter.addTestStep("Validate Course Inactive Successfully","Course not get Inactive Successfully", Status.FAIL, DriverAction.takeSnapShot());

            }
            if(DriverAction.isExist(LearnerModule_Locators.backBtn1))
            {
                DriverAction.click(LearnerModule_Locators.backBtn1);
            }
            else {
                GemTestReporter.addTestStep("Error occurred","Not able to click on back button", Status.FAIL, DriverAction.takeSnapShot());

            }
            if(DriverAction.isExist(Course_Locators.activeDropdown))
            {
                DriverAction.click(Course_Locators.activeDropdown);
                if(DriverAction.isExist(Course_Locators.inActiveOption))
                {
                    DriverAction.click(Course_Locators.inActiveOption);
                }
                else {
                    GemTestReporter.addTestStep("Error occurred","Not able to click on Inactive Option", Status.FAIL, DriverAction.takeSnapShot());

                }
            }
            else {
                GemTestReporter.addTestStep("Error occurred","Not able to click on Active Dropdown", Status.FAIL, DriverAction.takeSnapShot());

            }
        }
        catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,Status.ERR);
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
                        Status.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate user directed to course summary screen successfully", "User not able to directed to course summary screen",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                if(DriverAction.isDisplayed(By.xpath("//span[text()='Confirmation']"))) {
                    GemTestReporter.addTestStep("Edit action", "Validated", Status.PASS, DriverAction.takeSnapShot());
                }
            }
            else if(action.equals("Assign Learners"))
            {
                if(actionHeading.trim().equals("Assign Learners: "+_firstCourseName))
                {
                    GemTestReporter.addTestStep("Validate user directed to Assign Learner screen", "User successfully directed to Assign Learner screen",
                            Status.PASS, DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Validate user directed to Assign Learner screen", "User not able to directed to  Assign Learner screen",
                            Status.FAIL, DriverAction.takeSnapShot());
                }
            }
            else
            {
                if(actionHeading.trim().equals("View Learner(s) Report: "+_firstCourseName))
                {
                    GemTestReporter.addTestStep("Validate user directed to Assign Learner screen", "User successfully directed to Assign Learner screen",
                            Status.PASS, DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Validate user directed to Assign Learner screen", "User not able to directed to  Assign Learner screen",
                            Status.FAIL, DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                        Status.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate user directed to View Learner screen successfully", "User not able to directed to View Learner screen",
                        Status.FAIL, DriverAction.takeSnapShot());
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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

            if(DriverAction.isDisplayed(By.xpath(Course_Locators.heading.replace("input"," View Learner(s) Report:  "+  _firstCourseName+" "))))
            {
                GemTestReporter.addTestStep("Validate user directed to Learner Report screen successfully", "User successfully directed to Learner Report screen",
                        Status.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate user directed to Learner Report screen successfully", "User not able to directed to Learner Report screen",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }

    @Then("Validate Status of Course")
    public void validateStatusOfCourse() {
        try{
            //content,test,assignment
//case 1-Expired
//case 2-Completed -content and assignment:completed and test:Pass or Fail
//case 3-unattempted -Status:unattempted
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
                      String Status_new=DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                    if(Status_new.equals("Unattempted"))
                    {
                        GemTestReporter.addTestStep("Validate Expired Content should have the Status unattempted", "Expired course is unattempted",
                                Status.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Validate Expired Content should have the Status unattempted", "Expired course is not giving desired Status",
                                Status.FAIL, DriverAction.takeSnapShot());
                    }
                  }
                  else if(courseStatus.equals("In Progress"))
                  {
                      String Status_update=DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                      if(Status_update.equals("Unattempted")||Status_update.equals("Completed"))
                      {

                          GemTestReporter.addTestStep("Validate In Progress Content should have the Status unattempted or Completed", "In progress course is "+Status_update,
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate In Progress Content should have the Status unattempted or Completed", "In progress course is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("Yet to Start"))
                  {
                      String Status_unattempted=DriverAction.getElementText(Course_Locators.contentsStatusUnattempted);
                      if(Status_unattempted.equals("Unattempted"))
                      {

                          GemTestReporter.addTestStep("Validate Yet to Start Content should have the Status unattempted", "Yet to Start course is unattempted",
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Yet to Start Content should have the Status unattempted", "Yet to Start course is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else
                  {
                      String Status_completed=DriverAction.getElementText(Course_Locators.contentsStatusCompleted);
                      if(Status_completed.equals("Completed"))
                      {

                          GemTestReporter.addTestStep("Validate Completed Content should have the Status Completed", "Completed course content is completed successfully",
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Completed Content should have the Status Completed", "Completed course is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }

              }
              else if(content.equals("Assignments"))
              {
                  DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr",String.valueOf(i+1))));
                  String Status_assignment=DriverAction.getElementText(Course_Locators.assignmentsStatus);
                  if(courseStatus.equals("Expired"))
                  {
                      if(Status_assignment.equals("Unattempted"))
                      {
                          GemTestReporter.addTestStep("Validate Expired Assignment should have the Status unattempted", "Expired Assignment is unattempted",
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Expired Assignment should have the Status unattempted", "Expired Assignment is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("In Progress"))
                  {
                      if(Status_assignment.equals("Unattempted")||Status_assignment.equals("Completed"))
                      {

                          GemTestReporter.addTestStep("Validate In Progress Assignment should have the Status unattempted or Completed", "In progress Assignment is "+Status_assignment,
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate In Progress Assignment should have the Status unattempted or Completed", "In progress Assignment is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("Yet to Start"))
                  {
                      if(Status_assignment.equals("Unattempted"))
                      {

                          GemTestReporter.addTestStep("Validate Yet to Start Assignment should have the Status unattempted", "Yet to Start Assignment is unattempted",
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Yet to Start Assignment should have the Status unattempted", "Yet to Start Assignment is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else
                  {
                      if(Status_assignment.equals("Completed"))
                      {

                          GemTestReporter.addTestStep("Validate Completed Assignment should have the Status Completed", "Completed course Assignment is completed successfully",
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Completed Assignment should have the Status Completed", "Completed Assignment is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
              }
              else
              {
                  DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr",String.valueOf(i+1))));
                  String Status_comp=DriverAction.getElementText(Course_Locators.contentsStatusCompleted);
                  if(courseStatus.equals("Expired"))
                  {
                      if(Status_comp.equals("Unattempted"))
                      {
                          GemTestReporter.addTestStep("Validate Expired Test should have the Status unattempted", "Expired Test is unattempted",
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Expired Test should have the Status unattempted", "Expired Test is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("In Progress"))
                  {
                      if(Status_comp.equals("Unattempted")||Status_comp.equals("PASS")||Status_comp.equals("FAIL"))
                      {

                          GemTestReporter.addTestStep("Validate In Progress Test should have the Status unattempted,pass or fail", "In progress Test is "+Status_comp,
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate In Progress Test should have the Status unattempted,pass or fail", "In progress Test is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else if(courseStatus.equals("Yet to Start"))
                  {
                      if(Status_comp.equals("Unattempted"))
                      {

                          GemTestReporter.addTestStep("Validate Yet to Start Test should have the Status unattempted", "Yet to Start Test is unattempted",
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Yet to Start Test should have the Status unattempted", "Yet to Start Test is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
                  else
                  {
                      if(Status_comp.equals("PASS")||Status_comp.equals("FAIL"))
                      {

                          GemTestReporter.addTestStep("Validate Completed Test should have the Status Pass Or Fail", "Completed Test is completed successfully",
                                  Status.PASS, DriverAction.takeSnapShot());
                      }
                      else
                      {
                          GemTestReporter.addTestStep("Validate Completed Test should have the Status Pass Or Fail", "Completed Test is not giving desired Status",
                                  Status.FAIL, DriverAction.takeSnapShot());
                      }
                  }
              }
              DriverAction.click(By.xpath(Course_Locators.courseContent.replace("itr",String.valueOf(i+1))));

          }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                GemTestReporter.addTestStep("Validate Learner assign count", "Learner count matches with selected learner", Status.PASS,
                        DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate Learner assign count", "Learner count not matches with selected learner", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                GemTestReporter.addTestStep("Validate Learner Unassign count", "Learner count matches with selected learner", Status.PASS,
                        DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate Learner Unassign count", "Learner count not matches with selected learner", Status.FAIL,
                        DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are assign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are not assign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage="All learners added successfully to the course";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are assign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are not assign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                    GemTestReporter.addTestStep("Validate Candidates are Unassign properly", "Candidates are Unassign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are Unassign properly", "Candidates are not Unassign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage = "All learners who have not attempted the course were removed successfully";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are Unassign properly", "Learners are Unassign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are Unassign properly", "Learners are not Unassign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
            //validate candidates are assigned successfully
            String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println(popupMessage);
            String reqPopUpMessage="";
            if(test.equals("test")) {
                reqPopUpMessage="All candidates added successfully to the test";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are assign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are not assign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage="learners added successfully to the course";
                if (popupMessage.contains(reqPopUpMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are assign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are not assign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                    GemTestReporter.addTestStep("Validate Candidates are Unassign properly", "Candidates are Unassign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are Unassign properly", "Candidates are not Unassign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage = "All learners who have not attempted the course were removed successfully";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are Unassign properly", "Learners are Unassign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are Unassign properly", "Learners are not Unassign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
            DriverAction.click(Tests_TestControl_Locators.candidateCheckbox2,"Select a checkbox.");
       //     DriverAction.click(By.xpath(Tests_TestControl_Locators.candidateCheckbox.replace("itr",String.valueOf(1))));
            DriverAction.click(Tests_TestControl_Locators.assignCandidateBtn,"Validate user able to click on assign candidate button","User able to click on button");
            DriverAction.click(Tests_TestControl_Locators.yesBtn,"Validate user able to click on  popup","User successfully able to click on popup");
            DriverAction.waitSec(2);
            //validate candidates are assigned successfully
           /* String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
            System.out.println(popupMessage);
            String reqPopUpMessage="";
            if(test.equals("test")) {
                reqPopUpMessage="All candidates added successfully to the test";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are assign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Candidates are assign properly", "Candidates are not assign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
            else
            {
                reqPopUpMessage="All learners added successfully to the course";
                if (reqPopUpMessage.equals(popupMessage)) {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are assign successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are not assign successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }*/


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }


    @And("Assign a learner and complete the course")
    public void assignALearnerAndCompleteTheCourse() {
        try{

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are assign successfully", Status.PASS,
                        DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate Learners are assign properly", "Learners are not assign successfully", Status.FAIL,
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
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is updated successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is not updated successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }

    }

    @Then("Filter completed Course and validate it date can be edited")
    public void filterCompletedCourseAndValidateItDateCanBeEdited() {
        try{
//Filter the completed course from Status dropdown
            DriverAction.click(Course_Locators.StatusDropdown,"Validate user clicked on Status dropdown","User successfully clicked on Status dropdown");
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
                    GemTestReporter.addTestStep("Validate Date of completed course cannot be updated", "Date of completed course not edited successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Date of completed course cannot be updated", "Date of completed course got edited which was not expected result", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                    _currentCourse=DriverAction.getElementText(Course_Locators.currentCourse);
                    DriverAction.click(LearnerModule_Locators.startCourseBtn,"Validate Learner started the course successfully","Learner started the course successfully");
                    DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn), 90);
                    if (DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn)) {
                        DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                        DriverAction.waitSec(2);
                        String popUp = DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                        if ("Content Completed Successfully".equals(popUp)) {
                            GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Successfully " + popUp + " appears", Status.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Unsuccessful", Status.FAIL, DriverAction.takeSnapShot());
                        }

                    }
                    //assignment
                    DriverAction.waitSec(5);
                    DriverAction.typeText(LearnerModule_Locators.answerArea, "demo_content");
                    DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn), 90);
                    if (DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn)) {
                        DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                        String popUp = DriverAction.getElementText(UserDashboard_Locator.popupMessage);
//                        DriverAction.waitSec(5);
//                        System.out.println("hello");
//                        System.out.println(popUp);
//                        System.out.println("hello");
                        if ("Assignment Completed Successfully".equals(popUp)) {
                            GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Successfully " + popUp + " appears", Status.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Unsuccessful", Status.FAIL, DriverAction.takeSnapShot());
                        }

 }
        }else {
                GemTestReporter.addTestStep("Verify Start button is present", "Unsuccessful", Status.FAIL, DriverAction.takeSnapShot());
            }


            DriverAction.waitSec(10);


        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                        GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Successfully " + popUp + " appears", Status.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Unsuccessful", Status.FAIL, DriverAction.takeSnapShot());
                    }

                }
            }
            DriverAction.click(Course_Locators.backBtn,"Validate user switch back from the course","User successfully switch back from the course");
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }

    @Then("Validate user able to update the date for inprogress course")
    public void validateUserAbleToUpdateTheDateForInprogressCourse() {
        try{
            //Filter the inprogress course from Status dropdown
            DriverAction.click(Course_Locators.StatusDropdown,"Validate user clicked on Status dropdown","User successfully clicked on Status dropdown");
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
                String fetchDate=DriverAction.getElementText(Course_Locators.updatedEndDate);
                String [] dateArray=fetchDate.split("/");
                if(dateArray[0].equals(endDate))
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is updated successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is not updated successfully", Status.FAIL,
                            DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
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
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is updated successfully", Status.PASS,
                            DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Course date is updated successfully", "Course date is not updated successfully", Status.FAIL,
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
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }

    @And("^Search a course completed by learner$")
    public void searchCompletedCourse() {
        try{
            if(DriverAction.isDisplayed(MyLocators.searchbox)) {
                DriverAction.typeText(MyLocators.searchbox, _currentCourse, "Search a currently completed course- " + _currentCourse);
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Search a course completed by learner","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @When("^Click actions icon of a course$")
    public void courseActionsIcon(){
        try{
            DriverAction.waitUntilElementClickable(Course_Locators.actionsIcon,3);
            DriverAction.click(Course_Locators.actionsIcon,"Click actions icon of a course","Successfully clicked actions icon.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click actions icon of a course","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("Switch the role {string}")
    public void switchTheRole(String role) {
        try{
            DriverAction.click(Course_Locators.rolesDropdown,"Expand roles dropdown");
        }catch(Exception e){

        }
    }

    @And("Select the checkbox")
    public void selectTheCheckbox() throws InterruptedException {
        Thread.sleep(5000);
        DriverAction.waitUntilElementIsClickable(Course_Locators.checkboxs);
        DriverAction.click(Course_Locators.checkboxs);
    }

    @And("Click the button Add Content")
    public void clickTheButtonAddContent() {
        DriverAction.scrollToBottom();
        DriverAction.waitSec(5);
        DriverAction.scrollIntoView(MyLocators.addContentBtn);
        DriverAction.scrollToBottom();
//        DriverAction.scrollToBottom();
        DriverAction.click(MyLocators.addContentBtn);
    }

    @And("^Enter respective values in course fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterRespectiveValuesInCourseFields(String courseType, String duration, String courseTag, String fileLocation, String category) {
        try{
            int c=2;
            List<WebElement> inputFields= DriverAction.getElements(Course_Locators.courseInputFields);
            _courseName = generateRandomCourseName(10);
//        courseName= RandomStringUtils.randomAlphanumeric(10);
//            DriverAction.typeText(Course_Locators.points, points);
            String inputValues[]={_courseName,courseType,duration, courseTag,fileLocation,category};
            for(int i=0;i<=5;i++){
                DriverAction.waitSec(2);
                String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
                String upload=inputFields.get(i).getAttribute("type");
                //dropdown
                if(dropdown!=null&&dropdown.equals("listbox")){
                    DriverAction.waitSec(2);
                    DriverAction.click(By.xpath(Course_Locators.dropdownIcon.replace("itr",String.valueOf(c))));
                    c++;
                    DriverAction.waitSec(2);
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
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", Status.FAIL);
        }
    }
}

