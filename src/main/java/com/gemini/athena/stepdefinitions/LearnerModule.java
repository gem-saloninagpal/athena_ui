package com.gemini.athena.stepdefinitions;

import com.gemini.athena.locators.LearnerModule_Locators;
import com.gemini.athena.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class LearnerModule {
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);
    @When("^Expand user dropdown from navbar$")
    public void expandUserDropdown() {
        DriverAction.click(LearnerModule_Locators.userDropdown,"Click the dropdown icon on navbar","Successfully clicked the dropdown icon.");

    }

    @Then("^Verify the options present in dropdown and select it \"([^\"]*)\"$")
    public void verifyOptionsInDropdown(String option) {

        try {
            boolean found = false;
            List<WebElement> options = DriverAction.getElements(LearnerModule_Locators.optionList);
            System.out.println(options.size());
            for (WebElement option1 : options) {
                String optionText = option1.getText();
                if(optionText.equals(option))
                {
                    found=true;
                }
                System.out.println(optionText);
            }
            if (!found) {
                GemTestReporter.addTestStep("Verify the option present in dropdown", "Could not verify the option- " + option +"", STATUS.FAIL, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Verify the option present in dropdown", "Successfully verified the option- " + option + "" , STATUS.PASS, DriverAction.takeSnapShot());
            }
            DriverAction.click(LearnerModule_Locators.requiredOption, "Select "+option+" from dropdown", "Successfully selected "+option+".");
//            DriverAction.waitSec(6);

        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the options present in dropdown.");
        }
    }
    @And("^Verify View Course Button and click it$")
    public void verifyViewCourse() {
        try {
//            DriverAction.waitSec(10);
           DriverAction.scrollToBottom();
            if (DriverAction.getElement(LearnerModule_Locators.viewCourseBtn).isDisplayed()) {
                GemTestReporter.addTestStep("Verify View Course button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.click(LearnerModule_Locators.viewCourseBtn);
                DriverAction.waitSec(10);
            } else {
                GemTestReporter.addTestStep("Verify View Course button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    @And("^Validate Resume Course after starting the course$")
    public void verifyResume() {
        try {
//            DriverAction.scrollToBottom();
            if (DriverAction.getElement(LearnerModule_Locators.startCourseBtn).isDisplayed()) {
                GemTestReporter.addTestStep("Verify Start Course button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.click(LearnerModule_Locators.startCourseBtn);
                DriverAction.waitSec(10);
                DriverAction.scrollToBottom();
                if (DriverAction.getElement(LearnerModule_Locators.backBtn).isDisplayed()) {
                    DriverAction.click(LearnerModule_Locators.backBtn);
                    if(DriverAction.getElement(LearnerModule_Locators.resumeBtn).isDisplayed())
                    {
                        GemTestReporter.addTestStep("Verify Resume Course button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        DriverAction.click(LearnerModule_Locators.backBtn1);
                        if(DriverAction.getElement(LearnerModule_Locators.resumeBtn2).isDisplayed())
                        {
                            GemTestReporter.addTestStep("Verify Resume button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        }
                        else
                        {
                            GemTestReporter.addTestStep("Verify Resume button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }

                    }
                    else
                    {
                        GemTestReporter.addTestStep("Verify Resume Course button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                    }

                }
                
            } else {
                GemTestReporter.addTestStep("Verify Start Course button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("^Complete the Course and Download the certificate$")
    public void CompleteCourse() {
        try {
            int c=0;
            DriverAction.waitSec(5);
            DriverAction.scrollToBottom();
            if(DriverAction.getElement(LearnerModule_Locators.resumeBtn2).isDisplayed())
            {
                GemTestReporter.addTestStep("Verify Resume button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.click(LearnerModule_Locators.resumeBtn2);
                if(DriverAction.getElement(LearnerModule_Locators.resumeBtn).isDisplayed()) {
                    DriverAction.click(LearnerModule_Locators.resumeBtn);
                    List<WebElement> Assignments = DriverAction.getElements(LearnerModule_Locators.Assignment);
                    System.out.print(Assignments.size());
                    List<WebElement> readingModules = DriverAction.getElements(LearnerModule_Locators.readingModule);

                    List<WebElement> testList1 = DriverAction.getElements(LearnerModule_Locators.testList);


                    for(int i=0;i<readingModules.size();i++)
                    {
                        c++;
                        WebElement element = readingModules.get(i); // Get the individual element from the list
                        DriverAction.waitSec(32);
                        DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                        DriverAction.waitSec(3);
                        if(DriverAction.getElement(LearnerModule_Locators.popupDiv).isDisplayed()) {
                            GemTestReporter.addTestStep("Verify confirmation popup message", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        }
                        else
                        {
                            GemTestReporter.addTestStep("Verify confirmation popup message", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
//                        String classAttributeValue = element.getAttribute("class");

//                        if (classAttributeValue.contains("tick-icon-style ng-star-inserted")) {
//                            GemTestReporter.addTestStep("Verify module is completed by confirming green tick", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
//
//                        } else {
//                            GemTestReporter.addTestStep("Verify module is completed by confirming green tick", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
//
//                        }


                    }
                    if(Assignments.size()!=0) {
                        for (int j = 0; j < Assignments.size(); j++) {
                            DriverAction.typeText(LearnerModule_Locators.answerArea, "demo_content");
                            DriverAction.waitSec(10);
                            DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
//                        if(DriverAction.getElement(LearnerModule_Locators.assignmentPopup).isDisplayed()) {
//                            GemTestReporter.addTestStep("Verify confirmation popup message", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
//                        }
//                        else
//                        {
//                            GemTestReporter.addTestStep("Verify confirmation popup message", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
//                        }

//                        if(getConfirmPopup.equals("Content Completed Successfully"))
//                        {
//                            GemTestReporter.addTestStep("Verify confirmation popup message", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
//                        }
//                        else
//                        {
//                            GemTestReporter.addTestStep("Verify confirmation popup message", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
//                        }

                        }
                    }
                    if(testList1.size()!=0)
                    {
                        for(int k=0;k<testList1.size();k++)
                        {
                            System.out.print("hello this test");
                        }
                    }
                }
                else
                {
                    GemTestReporter.addTestStep("Verify Resume course button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                }

                //download certificate
                DriverAction.waitSec(7);
                if(DriverAction.getElement(LearnerModule_Locators.downloadCertifcatebtn).isDisplayed())
                {
                    DriverAction.waitSec(7);
                    DriverAction.click(LearnerModule_Locators. downloadCertifcatebtn);
                    String downloadPath = "C:/Users/rahul.adhikari/Downloads";
                    File latestFile = getLatestFileFromFolder(downloadPath);
                    System.out.println("Latest file: " + latestFile.getName());

                    if (latestFile.toString().contains("certificate")) {
                        System.out.println("File downloaded successfully.");
                    } else {
                        System.out.println("File download failed.");
                    }

                }
                else {
                    System.out.print("no download button");
                }


            }
            else
            {
                GemTestReporter.addTestStep("Verify Resume button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    //validate test and assignment count
    @And("^validate test and assignment count matches$")
    public void validateCount(){
        try {
            if(DriverAction.getElement(LearnerModule_Locators.courseCatalogbtn).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.courseCatalogbtn);
            }
            else
            {
                GemTestReporter.addTestStep("Verify Course Catalog button is visible", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

            }
            DriverAction.waitUntilElementClickable(LearnerModule_Locators.courseDiv,120);
            DriverAction.waitSec(6);
            String testCount = DriverAction.getElementText(LearnerModule_Locators.testCount);
            String[] count=testCount.split(" ");
            System.out.println(count[0]);
            int countTest=0;
            int countAssignment=0;
            if(count[0].equals("tests"))
            {
                countTest=0;
            }
            else
            {
                countTest=Integer.parseInt(count[0]);
            }

            String assignmentCount = DriverAction.getElementText(LearnerModule_Locators.assignmentCount);

            String[] count1=assignmentCount.split(" ");
            if(count1[0].equals("assignments"))
            {
                countAssignment=0;
            }
            else
            {
                countAssignment=Integer.parseInt(count1[0]);
            }

            if(DriverAction.getElement(LearnerModule_Locators.viewCourseBtn1).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.viewCourseBtn1);
                DriverAction.scrollToBottom();
                List<WebElement> Assignments = DriverAction.getElements(LearnerModule_Locators.catalogAssignment);
//                System.out.print(Assignments.size());
//                DriverAction.waitSec(4);
                List<WebElement> testList1 = DriverAction.getElements(LearnerModule_Locators.catalogTest);
                System.out.print(testList1.size());
                if(Assignments.size()==countAssignment&&testList1.size()==countTest)
                {
                    GemTestReporter.addTestStep("Verify test and assignment count for the view course", "Count Matches", STATUS.PASS, DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Verify test and assignment count for the view course", "Count Not Match", STATUS.FAIL, DriverAction.takeSnapShot());
                }


            }
            else
            {
                GemTestReporter.addTestStep("Verify View Course button is visible", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }


    }

    @And("^Enroll in course and verify$")
    public void enrollCourse()
    {
        try {
            DriverAction.scrollToTop();
            if(DriverAction.getElement(LearnerModule_Locators.backBtn1).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.backBtn1);
                    if (DriverAction.getElement(LearnerModule_Locators.startCourseBtn).isDisplayed()) {
                        DriverAction.click(LearnerModule_Locators.startCourseBtn);
                        if (DriverAction.getElement(LearnerModule_Locators.courseContent).isDisplayed()) {
                            GemTestReporter.addTestStep("Verify Course is able to start after enroll from course catalog", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify Course is able to start after enroll from course catalog", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                        DriverAction.click(LearnerModule_Locators.backBtn1);


                    }



            }
            else
            {
                GemTestReporter.addTestStep("Verify back button is visible", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

            }
            DriverAction.waitUntilElementClickable(LearnerModule_Locators.courseDiv,120);


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }


    }

    @Then("^Apply Filter and validate the result for Course$")
    public void applyFilter(){
        try {

            if(DriverAction.getElement(LearnerModule_Locators.courseCatalogbtn).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.courseCatalogbtn);
            }
//            DriverAction.waitSec(10);
            DriverAction.click(LearnerModule_Locators.courseDropdown);

            int catagoryCount=0;
            String firstOption=DriverAction.getElementText(LearnerModule_Locators.courseFilterdiv);
            DriverAction.waitSec(5);
            DriverAction.click(LearnerModule_Locators.courseFilterdiv);

            boolean isTrue=true;
            while(isTrue)
            {
                DriverAction.waitSec(5);
                List<WebElement> catagory = DriverAction.getElements(LearnerModule_Locators.courseCatagory);
                System.out.println(catagory.size());

                for (int i = 1; i <=catagory.size() ; i++) {
                    String reqLable = DriverAction.getElement(By.xpath(LearnerModule_Locators.courseCatagoryLable.replace("itr", String.valueOf(i)))).getText();
                    if(reqLable.equals(firstOption))
                    {
                        catagoryCount++;
                    }
                }
                DriverAction.scrollToBottom();
                DriverAction.waitSec(5);
            String paginator=DriverAction.getElementText(LearnerModule_Locators.catalogPaginator);
            System.out.println(paginator);
            String[] paginatorCount=paginator.split("\\n");
            System.out.println(paginatorCount[paginatorCount.length-2]);
            String reqValue=paginatorCount[paginatorCount.length-2];
                if(Integer.parseInt(reqValue)>catagoryCount) {
                    DriverAction.click(LearnerModule_Locators.footerRightArrow);
                }
                else if(Integer.parseInt(reqValue)==catagoryCount){
                    GemTestReporter.addTestStep("Verify catagories filter in course catalog", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                    isTrue=false;
                }
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }
    public static File getLatestFileFromFolder(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a valid directory.");
        }

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            throw new RuntimeException("The folder is empty.");
        }

        // Sort files by last modified timestamp in descending order
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];
    }

    @And("^Validate the count of Ongoing and Completed Course$")
    public void countValidation(){
        try{
            int count=0;
            String CompletedCount=DriverAction.getElementText(LearnerModule_Locators.completedCourseCount);
            String activeCount=DriverAction.getElementText(LearnerModule_Locators.activeCourseCount);
//            Map<String, Integer> map = new HashMap<>();
//            map.put("Ongoing",1);
//            map.put("Completed",2);
//            map.put("Expired",3);
            for(int i=1;i<=3;i++) {
                DriverAction.waitSec(5);
                String CourseType = DriverAction.getElement(By.xpath(LearnerModule_Locators.courseType.replace("itr", String.valueOf(i)))).getText();
                DriverAction.click(By.xpath(LearnerModule_Locators.courseType.replace("itr", String.valueOf(i))));
                List<WebElement> list1 = DriverAction.getElements(By.xpath(LearnerModule_Locators.courseArea.replace("itr", String.valueOf(i))));
                if (CourseType.equals("Ongoing")) {
                    if(Integer.parseInt(activeCount)==list1.size())
                    {
                        GemTestReporter.addTestStep("Verify Count of Ongoing Course on ui", "Count Matched", STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Verify Count of Ongoing Course on ui", "Count Not Matched", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else if (CourseType.equals("Completed")) {
                    if(Integer.parseInt(CompletedCount)==list1.size())
                    {
                        count=count+list1.size();
                        GemTestReporter.addTestStep("Verify Count of Completed Course on ui", "Count Matched", STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Verify Count of Completed Course on ui", "Count Not Matched", STATUS.FAIL, DriverAction.takeSnapShot());
                    }

                } else if (CourseType.equals("Expired")) {
                     count=count+list1.size();
                }
            }
            String totalCount=DriverAction.getElementText(LearnerModule_Locators.totalCourseCount);
            if(Integer.parseInt(totalCount)==count)
            {
                GemTestReporter.addTestStep("Verify total count of Ongoing and completed course on ui", "Count Matched", STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Verify total count of Ongoing and completed course on ui", "Count Not Matched", STATUS.FAIL, DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

}
