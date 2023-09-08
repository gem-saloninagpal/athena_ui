package com.gemini.athena.stepdefinitions;

import com.gemini.athena.locators.Course_Locators;
import com.gemini.athena.locators.LearnerModule_Locators;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Course {
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);
    String courseName=" ";
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
           boolean isPresent=false;
           boolean isPresent1=false;
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
    @Then("^Enter respective values in course fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterAssignment(String courseType, String duration,String courseTag, String fileLocation, String category) {
    try{
        int c=2;
        List<WebElement> inputFields= DriverAction.getElements(Course_Locators.courseInputFields);
        courseName= RandomStringUtils.randomAlphanumeric(10);
        String inputValues[]={courseName,courseType,duration,courseTag,fileLocation,category};
        for(int i=0;i<=5;i++){
            String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
            String upload=inputFields.get(i).getAttribute("type");
            //dropdown
            if(dropdown!=null&&dropdown.equals("listbox")){
//                List<WebElement>dropdownFields=DriverAction.getElements(MyLocators.dropdownIcon);
                DriverAction.click(By.xpath(Course_Locators.dropdownIcon.replace("itr",String.valueOf(c))));
                c++;
//                DriverAction.click(dropdownFields.get(i));
                DriverAction.click(By.xpath(Course_Locators.option.replace("input",inputValues[i])));
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


    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
    }
    }
    @And("^Enter course description \"([^\"]*)\"$")
    public void courseDescription(String description) {
        try{

            DriverAction.typeText(Course_Locators.courseDescription,description);
            DriverAction.scrollToBottom();
            DriverAction.click(Course_Locators.checkboxs);
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
           if(DriverAction.isDisplayed(Course_Locators.addContentTagInput))
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
@Then("^Validate Add to Course$")
    public void addToCourse(){
      try{
          //In this function we are first verifying the Add to course div is empty if not we are first deleting it.
          //And after that we are adding to Course.
if(DriverAction.isDisplayed(Course_Locators.addToCourseDiv))
{
    if(DriverAction.isDisplayed(Course_Locators.addToCourseBtn))
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
     DriverAction.click(Course_Locators.deleteIcon,"Clicked on Delete Icon","Successfully clicked on delete Icon");
     DriverAction.waitSec(3);
     if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Content removed successfully"))))
     {
         GemTestReporter.addTestStep("Content is deleted","content is deleted successfully", STATUS.PASS, DriverAction.takeSnapShot());
     }
    }
}

if(DriverAction.isDisplayed(Course_Locators.addIcon))
{
    DriverAction.click(Course_Locators.addIcon,"Clicked on add Content Icon","Successfully clicked on Add Content Icon");
}
DriverAction.waitSec(3);
if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Content successfully added. Add more!"))))
{
    GemTestReporter.addTestStep("Content is added","content is added successfully", STATUS.PASS, DriverAction.takeSnapShot());

}
String getContent=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[1]//tr[1]//td[1]"));
String contentAfterSelecting=DriverAction.getElementText(By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr[1]//td[1]"));
if(getContent.equals(contentAfterSelecting))
{
    GemTestReporter.addTestStep("Content is added to Course Div","content is added successfully", STATUS.PASS, DriverAction.takeSnapShot());
}
else
{
    GemTestReporter.addTestStep("Content is added to Course Div","content is not added successfully", STATUS.FAIL, DriverAction.takeSnapShot());
}

if(DriverAction.isDisplayed(Course_Locators.addToCourseBtn))
{
    DriverAction.click(Course_Locators.addToCourseBtn,"Clicked on Add to Course Button","Successfully clicked on Add to course Button");
}
      }
      catch (Exception e) {
          logger.info("Exception occurred", e);
          GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
      }
}

@Then("^Edit the Created Course and Verify$")
public void editAndVerify(){
        try{
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

            if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Course Updated Successfully"))))
            {
                GemTestReporter.addTestStep("Course is drafted","course is drafted successfully", STATUS.PASS, DriverAction.takeSnapShot());
            }
            else {
                GemTestReporter.addTestStep("Course is drafted","course is not drafted", STATUS.FAIL, DriverAction.takeSnapShot());

            }

            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
            DriverAction.click(Course_Locators.courseTypeDropdown);
            DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
            DriverAction.click(Course_Locators.draftOrPublishDropdown);
            DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Draft")));
            String fetchedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
            if(fetchedCourseName.equals(courseName))
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
                    DriverAction.typeText(Course_Locators.courseNameInput,"copy");
                    String courseNameAfterEdit=DriverAction.getElementText(Course_Locators.courseNameInput);

                        if(courseNameAfterEdit.equals("copy"))
                        {
                            GemTestReporter.addTestStep("Course Name is Edit","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
                        }
                        else
                        {
                            GemTestReporter.addTestStep("Course Name is Edit","Fail to Edit", STATUS.FAIL, DriverAction.takeSnapShot());
                        }

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
                    DriverAction.typeText(Course_Locators.courseDescription,"copy");
                    String descriptionAfterEdit=DriverAction.getElementText(Course_Locators.courseDescription);

                        if(descriptionAfterEdit.equals("copy"))
                        {
                            GemTestReporter.addTestStep("Course Description is Edit","Successfully", STATUS.PASS, DriverAction.takeSnapShot());
                        }
                        else
                        {
                            GemTestReporter.addTestStep("Course Description is Edit","Fail to Edit", STATUS.FAIL, DriverAction.takeSnapShot());
                        }

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
//                        DriverAction.waitSec(3);
                        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
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
                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                    DriverAction.click(Course_Locators.addIcon);
                    DriverAction.waitSec(5);
                    DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add To Course")));
                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
//                    DriverAction.waitSec(5);
                    DriverAction.click(By.xpath(Course_Locators.button.replace("input","Default Order")),"clicked on Default Order button","Successfully clicked on Default Order button");

                    //here we are checking the Save as Draft button functionality
                    DriverAction.scrollToBottom();
                    DriverAction.waitSec(3);
                    DriverAction.click(Course_Locators.saveAsDraftButton,"Clicked on Save As Draft Button","Successfully clicked on Save As Draft button");

//                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                    DriverAction.waitSec(2);

                    if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Course Updated Successfully"))))
                    {
                        GemTestReporter.addTestStep("Course is drafted","course is drafted successfully", STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else {
                        GemTestReporter.addTestStep("Course is drafted","course is not drafted", STATUS.FAIL, DriverAction.takeSnapShot());

                    }
                    DriverAction.waitSec(5);
                    DriverAction.click(Course_Locators.courseTypeDropdown);
                    DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
                    DriverAction.click(Course_Locators.draftOrPublishDropdown);
                    DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Draft")));
                    DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                    String fetchedCourseNameAfterEdit=DriverAction.getElementText(Course_Locators.draftedCourse);
                    System.out.print(fetchedCourseNameAfterEdit);
                    if(fetchedCourseName.equals("copy"))
                    {
                        GemTestReporter.addTestStep("Course is Editable","Successfully", STATUS.PASS, DriverAction.takeSnapShot());

                    }
                    else
                    {
                        GemTestReporter.addTestStep("Course is Editable","Not able to edit course", STATUS.FAIL, DriverAction.takeSnapShot());

                    }



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

           DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);

         if(DriverAction.isDisplayed(By.xpath(Course_Locators.popup.replace("input","Course Updated Successfully"))))
           {
               GemTestReporter.addTestStep("Course is drafted","course is drafted successfully", STATUS.PASS, DriverAction.takeSnapShot());
           }
           else {
               GemTestReporter.addTestStep("Course is drafted","course is not drafted", STATUS.FAIL, DriverAction.takeSnapShot());

           }

           DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
           DriverAction.click(Course_Locators.courseTypeDropdown);
           DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Public")));
           DriverAction.click(Course_Locators.draftOrPublishDropdown);
           DriverAction.click(By.xpath(Course_Locators.dropdownValue.replace("type","Draft")));
           String fetchedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
           if(fetchedCourseName.equals(courseName))
           {
               GemTestReporter.addTestStep("Course is saved in draft and can be edit to publish finally","Successfully", STATUS.PASS, DriverAction.takeSnapShot());

           }
           else
           {
               GemTestReporter.addTestStep("Course is saved in draft and can be edit to publish finally","UnSuccessfully", STATUS.FAIL, DriverAction.takeSnapShot());

           }

           //here we are checking the Save Course & Publish button functionality
//           DriverAction.waitUntilElementDisappear(By.xpath("//*[@class='p-progress-spinner-svg']"),120);

DriverAction.waitSec(5);
if(DriverAction.isDisplayed(Course_Locators.editIcon))
{
    DriverAction.click(Course_Locators.editIcon,"clicked on edit icon","Successfully clicked");
    if(DriverAction.isDisplayed(Course_Locators.editOption))
    {
        DriverAction.click(Course_Locators.editOption,"clicked on edit option","Successfully clicked");

        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
        DriverAction.scrollToBottom();
        DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add Content")));

        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);

        DriverAction.click(By.xpath(Course_Locators.button.replace("input","Add To Course")));
        DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);


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
                String fetchedPublishedCourseName=DriverAction.getElementText(Course_Locators.draftedCourse);
                if(fetchedPublishedCourseName.equals(courseName))
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
            if (DriverAction.isDisplayed(Course_Locators.editIcon)) {
                DriverAction.click(Course_Locators.editIcon, "clicked on Dropdown icon", "Successfully clicked");
                if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners"))));
                {
                    DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input","Assign Learners")));

                    if(DriverAction.isDisplayed(Course_Locators.nameFilterInput))
                    {
                        DriverAction.typeText(Course_Locators.nameFilterInput,"rahul23@gmail.com");
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
@And("^Change the user's role \"([^\"]*)\"$")
    public void switchRole(String role)
{
    try{
        DriverAction.click(LearnerModule_Locators.userDropdown, "Click the dropdown icon on navbar", "Successfully clicked the dropdown icon.");
        if(DriverAction.isDisplayed(By.xpath(Course_Locators.adminOption.replace("input",role))))
        {
            DriverAction.click(By.xpath(Course_Locators.adminOption.replace("input",role)));
        }
        DriverAction.waitUntilElementDisappear(By.xpath("//*[@class='p-progress-spinner-svg']"),120);

    }
    catch (Exception e) {
        logger.info("Exception occurred", e);
        GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
    }
}
    }
