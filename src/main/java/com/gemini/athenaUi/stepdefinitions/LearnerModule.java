package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.*;
//import com.gemini.athenaUi.locators.LearnerModule_Locators;
//import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class LearnerModule {
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);

    String _courseName="";

    @When("^Expand user dropdown from navbar$")
    public void expandUserDropdown() {
        DriverAction.waitSec(5);
        if(DriverAction.isExist(LearnerModule_Locators.userDropdown,120)) {
            DriverAction.click(LearnerModule_Locators.userDropdown, "Click the dropdown icon on navbar", "Successfully clicked the dropdown icon.");
        }
        else {
            GemTestReporter.addTestStep("Error Occur", "Fail to click on UserDrop down",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify the options present in dropdown and select it \"([^\"]*)\"$")
    public void verifyOptionsInDropdown(String option) {

        try {
            //In this function we are validating the dropdown option with given option.
            boolean found = false;
            List<WebElement> options = DriverAction.getElements(LearnerModule_Locators.optionList);
            System.out.println(options.size());
            for (WebElement option1 : options) {
                String optionText = option1.getText();
                if (optionText.equals(option)) {
                    found = true;
                }
                System.out.println(optionText);
            }
            if (!found) {
                GemTestReporter.addTestStep("Verify the option present in dropdown", "Could not verify the option- " + option + "", STATUS.FAIL, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Verify the option present in dropdown", "Successfully verified the option- " + option + "", STATUS.PASS, DriverAction.takeSnapShot());
            }
            DriverAction.click(LearnerModule_Locators.requiredOption, "Select " + option + " from dropdown", "Successfully selected " + option + ".");


        } catch (Exception e) {
            GemTestReporter.addReasonOfFailure(e + " Exception occured while verifying the options present in dropdown.");
        }
    }

    @And("^Verify View Course Button and click it$")
    public void verifyViewCourse() {
        try {
            //Validating View Course button and it functionality.
            DriverAction.waitUntilElementAppear((LearnerModule_Locators.courseCatalogbtn),20);
            DriverAction.scrollToBottom();
            if (DriverAction.getElement(LearnerModule_Locators.viewCourseBtn).isDisplayed()) {
                DriverAction.scrollToBottom();
                GemTestReporter.addTestStep("Verify View Course button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.click(LearnerModule_Locators.viewCourseBtn);
//                DriverAction.waitSec(10);
                DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
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
                DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
//                DriverAction.waitSec(10);
                DriverAction.scrollToBottom();
                if (DriverAction.getElement(LearnerModule_Locators.backBtn).isDisplayed()) {
                    DriverAction.click(LearnerModule_Locators.backBtn);
                    if (DriverAction.getElement(LearnerModule_Locators.resumeBtn).isDisplayed()) {
                        GemTestReporter.addTestStep("Verify Resume Course button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        DriverAction.click(LearnerModule_Locators.backBtn1);
                        if (DriverAction.getElement(LearnerModule_Locators.resumeBtn2).isDisplayed()) {
                            GemTestReporter.addTestStep("Verify Resume button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify Resume button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }

                    } else {
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
                //In this we are completing the course and downloading the certificate.

            DriverAction.waitSec(7);
            DriverAction.scrollToBottom();
            if (DriverAction.getElement(LearnerModule_Locators.resumeBtn2).isDisplayed()) {
                GemTestReporter.addTestStep("Verify Resume button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.click(LearnerModule_Locators.resumeBtn2);
                if (DriverAction.getElement(LearnerModule_Locators.resumeBtn).isDisplayed()) {
                    DriverAction.click(LearnerModule_Locators.resumeBtn);

                    DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn),90);
                    if(DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn))
                    {
                        DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                        DriverAction.waitSec(2);
                        String popUp=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                         if ("Content Completed Successfully".equals(popUp)) {
                            GemTestReporter.addTestStep("Verify confirmation popup message"+popUp, "Successfully "+popUp+" appears", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify confirmation popup message"+popUp, "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
//                        if(DriverAction.isDisplayed(LearnerModule_Locators.greenTick))
//                        {
//                            GemTestReporter.addTestStep("Verify green tick once the module is completed", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
//
//                        }
//                        else
//                        {
//                            GemTestReporter.addTestStep("Verify green tick once the module is completed", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
//
//                        }
                        }
                   //assignment
                    DriverAction.waitSec(5);
                    DriverAction.typeText(LearnerModule_Locators.answerArea, "demo_content");
                    DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn),90);
                    if(DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn))
                    {
                        DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                        String popUp=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                        DriverAction.waitSec(5);
                        System.out.println("hello");
                        System.out.println(popUp);
                        System.out.println("hello");
                        if ("Assignment Completed Successfully".equals(popUp)) {
                            GemTestReporter.addTestStep("Verify confirmation popup message"+popUp, "Successfully "+popUp+" appears", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify confirmation popup message"+popUp, "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
//                        if(DriverAction.isDisplayed(LearnerModule_Locators.greenTick))
//                        {
//                            GemTestReporter.addTestStep("Verify green tick once the module is completed", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
//
//                        }
//                        else
//                        {
//                            GemTestReporter.addTestStep("Verify green tick once the module is completed", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
//
//                        }
                    }

                }

                 else {
                    GemTestReporter.addTestStep("Verify Resume course button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                }

            } else {
                GemTestReporter.addTestStep("Verify Resume button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }


            DriverAction.waitSec(10);

            //download certificate and validate it is downloaded properly
            if (DriverAction.getElement(LearnerModule_Locators.downloadCertificate).isDisplayed()) {
                GemTestReporter.addTestStep("Verify download Certificate button is visible on ui", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.click(LearnerModule_Locators.downloadCertificate, "download Certificate button is visible on ui", "successfully clicked download Certificate button.");
                DriverAction.waitSec(5);
                String downloadPath = "C:/Users/rahul.adhikari/Downloads";
                File latestFile = getLatestFileFromFolder(downloadPath);
                System.out.println("Latest file: " + latestFile.getName());
                if (latestFile.toString().contains("certificate")) {
                    System.out.println("File downloaded successfully.");
                    GemTestReporter.addTestStep("Verify certificate is downloaded properly", "Successful", STATUS.PASS, DriverAction.takeSnapShot());

                } else {
                    System.out.println("File download failed.");
                    GemTestReporter.addTestStep("Verify certificate is downloaded properly", "UnSuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

                }
//
            } else {
                GemTestReporter.addTestStep("Verify download Certificate button is visible on ui", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    //validate test and assignment count
    @And("^validate test and assignment count matches$")
    public void validateCount() {
        try {
            //In this we are validating the count of assignment and test with actual count.
            if (DriverAction.getElement(LearnerModule_Locators.courseCatalogbtn).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.courseCatalogbtn);
            } else {
                GemTestReporter.addTestStep("Verify Course Catalog button is visible", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

            }
            DriverAction.waitUntilElementClickable(LearnerModule_Locators.courseDiv, 120);
            DriverAction.waitSec(6);
            String testCount = DriverAction.getElementText(LearnerModule_Locators.testCount);
            String[] count = testCount.split(" ");
            System.out.println(count[0]);
            int countTest = 0;
            int countAssignment = 0;
            if (count[0].equals("tests")) {
                countTest = 0;
            } else {
                countTest = Integer.parseInt(count[0]);
            }

            String assignmentCount = DriverAction.getElementText(LearnerModule_Locators.assignmentCount);

            String[] count1 = assignmentCount.split(" ");
            if (count1[0].equals("assignments")) {
                countAssignment = 0;
            } else {
                countAssignment = Integer.parseInt(count1[0]);
            }

            if (DriverAction.getElement(LearnerModule_Locators.viewCourseBtn1).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.viewCourseBtn1);
                DriverAction.scrollToBottom();
                List<WebElement> Assignments = DriverAction.getElements(LearnerModule_Locators.catalogAssignment);
                List<WebElement> testList1 = DriverAction.getElements(LearnerModule_Locators.catalogTest);
                System.out.print(testList1.size());
                if (Assignments.size() == countAssignment && testList1.size() == countTest) {
                    GemTestReporter.addTestStep("Verify test and assignment count for the view course", "Count Matches", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify test and assignment count for the view course", "Count Not Match", STATUS.FAIL, DriverAction.takeSnapShot());
                }

            } else {
                GemTestReporter.addTestStep("Verify View Course button is visible", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

            }

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }


    }

    @And("^Enroll in course and verify$")
    public void enrollCourse() {
        try {
            //In this we are enrolling in the course and validating.
            DriverAction.scrollToTop();
            if (DriverAction.getElement(LearnerModule_Locators.backBtn1).isDisplayed()) {
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


            } else {
                GemTestReporter.addTestStep("Verify back button is visible", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

            }
            DriverAction.waitUntilElementClickable(LearnerModule_Locators.courseDiv, 120);


        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }


    }

    @Then("^Apply Filter and validate the result for Course$")
    public void applyFilter() {
        try {
//In this we are applying the filter and validating the actual result is coming or not.
            if (DriverAction.getElement(LearnerModule_Locators.courseCatalogbtn).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.courseCatalogbtn);
            }
            DriverAction.waitSec(10);
            DriverAction.click(LearnerModule_Locators.courseDropdown);

            int catagoryCount = 0;
            String firstOption = DriverAction.getElementText(LearnerModule_Locators.courseFilterdiv);
            DriverAction.waitSec(5);
            DriverAction.click(LearnerModule_Locators.courseFilterdiv);

            boolean isTrue = true;
            while (isTrue) {
                DriverAction.waitSec(5);
                DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                List<WebElement> catagory = DriverAction.getElements(LearnerModule_Locators.courseCatagory);
                System.out.println(catagory.size());

                for (int i = 1; i <= catagory.size(); i++) {
                    String reqLable = DriverAction.getElement(By.xpath(LearnerModule_Locators.courseCatagoryLable.replace("itr", String.valueOf(i)))).getText();
                    if (reqLable.equals(firstOption)) {
                        catagoryCount++;
                    }
                }
                DriverAction.scrollToBottom();
                DriverAction.waitSec(5);
                String paginator = DriverAction.getElementText(LearnerModule_Locators.catalogPaginator);
                System.out.println(paginator);
                String[] paginatorCount = paginator.split("\\n");
                System.out.println(paginatorCount[paginatorCount.length - 2]);
                String reqValue = paginatorCount[paginatorCount.length - 2];
                if (Integer.parseInt(reqValue) > catagoryCount) {
                    DriverAction.click(LearnerModule_Locators.footerRightArrow);
                } else if (Integer.parseInt(reqValue) == catagoryCount) {
                    GemTestReporter.addTestStep("Verify catagories filter in course catalog", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                    isTrue = false;
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    //This is the common function to get the latest modified file from the given directory.

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
    public void countValidation() {
        try {
            //In this we are validating the count of Ongoing and Completed course.
            int count = 0;
            DriverAction.waitSec(5);
//            DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
            String CompletedCount = DriverAction.getElementText(LearnerModule_Locators.completedCourseCount);
            String activeCount = DriverAction.getElementText(LearnerModule_Locators.activeCourseCount);

            for (int i = 1; i <= 3; i++) {
                DriverAction.waitSec(5);
//                DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                String CourseType = DriverAction.getElement(By.xpath(LearnerModule_Locators.courseType.replace("itr", String.valueOf(i)))).getText();
                DriverAction.click(By.xpath(LearnerModule_Locators.courseType.replace("itr", String.valueOf(i))));
                List<WebElement> list1 = DriverAction.getElements(By.xpath(LearnerModule_Locators.courseArea.replace("itr", String.valueOf(i))));
                if (CourseType.equals("Ongoing")) {
                    if (Integer.parseInt(activeCount) == list1.size()) {
                        GemTestReporter.addTestStep("Verify Count of Ongoing Course on ui", "Count Matched", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify Count of Ongoing Course on ui", "Count Not Matched", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else if (CourseType.equals("Completed")) {
                    if (Integer.parseInt(CompletedCount) == list1.size()) {
                        count = count + list1.size();
                        GemTestReporter.addTestStep("Verify Count of Completed Course on ui", "Count Matched", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify Count of Completed Course on ui", "Count Not Matched", STATUS.FAIL, DriverAction.takeSnapShot());
                    }

                } else if (CourseType.equals("Expired")) {
                    count = count + list1.size();
                }
            }
            String totalCount = DriverAction.getElementText(LearnerModule_Locators.totalCourseCount);
            if (Integer.parseInt(totalCount) == count) {
                GemTestReporter.addTestStep("Verify total count of Ongoing and completed course on ui", "Count Matched", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify total count of Ongoing and completed course on ui", "Count Not Matched", STATUS.FAIL, DriverAction.takeSnapShot());
            }


        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("^Switch to Completed Tab and validate it functionality$")
    public void switchCompleted() {
        try {
            //In this we are validating after switching.
            if (DriverAction.getElement(LearnerModule_Locators.completedCourseTab).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.completedCourseTab);

                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
                js.executeScript("window.scrollTo(0, arguments[0]);", 200);

                DriverAction.waitSec(2);
                if(DriverAction.isExist(LearnerModule_Locators.threeDotIcon1,120))
                {
                    DriverAction.click(LearnerModule_Locators.threeDotIcon1);
                }
                else {
                    GemTestReporter.addTestStep("Error Occur", "Failed to click on edit option", STATUS.FAIL, DriverAction.takeSnapShot());
                }
                DriverAction.waitSec(5);
                if (DriverAction.isExist(LearnerModule_Locators.viewandDownload,120)) {

                    DriverAction.click(LearnerModule_Locators.viewandDownload);
                    DriverAction.waitSec(5);

                    if (DriverAction.getElement(LearnerModule_Locators.viewandDownloadLable).isDisplayed()) {
                        GemTestReporter.addTestStep("Verify Certificate Visible on ui", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify Certificate Visible on ui", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                    if (DriverAction.isExist(LearnerModule_Locators.downloadBtn,120)) {
                        GemTestReporter.addTestStep("Verify Download button on ui", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                        DriverAction.click(LearnerModule_Locators.downloadBtn);
                        String directoryPath = "C:/Users/rahul.adhikari/Downloads/";
                        File directory = new File(directoryPath);
                        if (directory.exists() && directory.isDirectory()) {
                            File latestfile = getLatestFile(directory);
                            if (latestfile != null) {
                                System.out.println("Latest File : " + latestfile.getAbsolutePath());
                                GemTestReporter.addTestStep("Validating weather User able to download certificate", "User successfully able to download the certificate", STATUS.PASS, DriverAction.takeSnapShot());

                            } else {
                                GemTestReporter.addTestStep("Validating weather User able to download certificate", "User not able to download the certificate", STATUS.FAIL, DriverAction.takeSnapShot());
                            }
                        } else {
                            GemTestReporter.addTestStep("Verify Download button on ui", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                        DriverAction.scrollToTop();
                        DriverAction.click(LearnerModule_Locators.backBtn1);

                    }
                    else
                    {
                        GemTestReporter.addTestStep("Error Occur", "Fail to click on download button", STATUS.FAIL, DriverAction.takeSnapShot());

                    }
                }
                else
                {
                    GemTestReporter.addTestStep("Error Occur", "Fail to find View Downloads option", STATUS.FAIL, DriverAction.takeSnapShot());

                }

            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @And("^Validate Course Summary$")
    public void courseSummary() {
        try {
            if (DriverAction.isExist(LearnerModule_Locators.completedCourseTab,120)) {
                DriverAction.click(LearnerModule_Locators.completedCourseTab);
                DriverAction.waitSec(3);
                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
                js.executeScript("window.scrollTo(0, arguments[0]);", 200);
                DriverAction.waitSec(3);
                DriverAction.click(LearnerModule_Locators.threeDotIcon1);
                DriverAction.waitSec(5);
                if (DriverAction.getElement(LearnerModule_Locators.courseSummary).isDisplayed()) {
                    DriverAction.click(LearnerModule_Locators.courseSummary);
                }

            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on complete Course tab", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    
    public static File getLatestFile(File directory) {
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            return null;
        }
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

    @And("Switch to Course Catalog Screen")
    public void switchToCourseCatalogScreen() {
        try{
            DriverAction.waitSec(5);
            if (DriverAction.getElement(LearnerModule_Locators.courseCatalogbtn).isDisplayed()) {
                DriverAction.click(LearnerModule_Locators.courseCatalogbtn);
            } else {
                GemTestReporter.addTestStep("Verify Course Catalog button is visible", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());

            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate the Enroll button functionality")
    public void validateTheEnrollButtonFunctionality() {
        try{
            DriverAction.waitSec(5);
       if(DriverAction.isExist(LearnerModule_Locators.enrollList,120))
       {
           GemTestReporter.addTestStep("Validate Enroll button is visible on course", "Button is visible", STATUS.PASS, DriverAction.takeSnapShot());
           DriverAction.click(LearnerModule_Locators.enrollList);
           DriverAction.waitSec(4);
           if(DriverAction.isExist(LearnerModule_Locators.startCourse,120))
           {
               DriverAction.click(LearnerModule_Locators.startCourse);
               if(DriverAction.isExist(LearnerModule_Locators.startCourseBtn,120))
               {
                   GemTestReporter.addTestStep("Validate Enroll button is working properly", "It is working properly", STATUS.PASS, DriverAction.takeSnapShot());

               }
               else
               {
                   GemTestReporter.addTestStep("Validate Enroll button is working properly", "It is not working properly", STATUS.FAIL, DriverAction.takeSnapShot());

               }
           }

       }
       else
       {
           GemTestReporter.addTestStep("Validate Enroll button is visible on course", "Button is not visible", STATUS.FAIL, DriverAction.takeSnapShot());

       }



        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Create a Course for Learner {string}, {string}, {string}, {string}, {string},{string},{string},{string}")
    public void createACourseForLearner(String courseType, String duration, String courseTag, String fileLocation, String category, String description, String contentMessage, String assignmentMessage) {
   try{
       int c=2;
       List<WebElement> inputFields= DriverAction.getElements(Course_Locators.courseInputFields);
       _courseName= RandomStringUtils.randomAlphanumeric(10);
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
       //fill the description
       if (DriverAction.isExist(Course_Locators.courseDescription,120)) {
           DriverAction.typeText(Course_Locators.courseDescription,description);
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
       WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 50);
       WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MyLocators.button.replace("input","Add Content"))));
       DriverAction.click(By.xpath(MyLocators.button.replace("input", "Add Content")));


       //add content to the course
       DriverAction.waitSec(5);
       if (DriverAction.isExist(Course_Locators.addIcon,120))
       {
           DriverAction.click(Course_Locators.addIcon,"Clicked on add Content Icon","Successfully clicked on Add Content Icon");
       }
       else {
           GemTestReporter.addTestStep("Error Occur", "Fail to click on add Content Icon", STATUS.FAIL,
                   DriverAction.takeSnapShot());
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

       //add assignment to the course
       DriverAction.waitSec(5);
       if (DriverAction.isExist(Course_Locators.addIcon,120))
       {
           DriverAction.click(Course_Locators.addIcon,"Clicked on add Content Icon","Successfully clicked on Add Content Icon");
       }
       else {
           GemTestReporter.addTestStep("Error Occur", "Fail to click on add Content Icon", STATUS.FAIL,
                   DriverAction.takeSnapShot());
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
       if(DriverAction.isExist(By.xpath(Course_Locators.button.replace("input","Default Order")),120)) {
           DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Default Order")), "clicked on Default Order button", "Successfully clicked on Default Order button");
       }
       else {
           GemTestReporter.addTestStep("Error Occur", "Fail to click on Default Order button", STATUS.FAIL,
                   DriverAction.takeSnapShot());
       }
       if(DriverAction.isExist(LearnerModule_Locators.saveCourseBtn,120)) {
           DriverAction.click(LearnerModule_Locators.saveCourseBtn);
             }
       else {
           GemTestReporter.addTestStep("Error Occur", "Fail to click on Save Course button", STATUS.FAIL,
                   DriverAction.takeSnapShot());
       }
       if(DriverAction.isDisplayed(By.xpath(Course_Locators.button.replace("input","Yes")))) {
           DriverAction.click(By.xpath(Course_Locators.button.replace("input", "Yes")));

       }


   }
   catch (Exception e) {
       logger.info("Exception occurred", e);
       GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
   }
    }

    @Then("Validate course overview")
    public void validateCourseOverview() {
        try{
            int counter=0;
            String assessmentCount=" ";
String count1=DriverAction.getElementText(LearnerModule_Locators.assignCount);
String[] count=count1.split(" ");
assessmentCount=count[0];
            DriverAction.waitSec(5);
            if(DriverAction.isExist(LearnerModule_Locators.viewCourseBtn,120)) {
                GemTestReporter.addTestStep("Validate Course button is visible on course", "Button is visible", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.click(LearnerModule_Locators.viewCourseBtn);
                DriverAction.scrollToBottom();
            }
            DriverAction.waitSec(3);
            List<WebElement> iconList=DriverAction.getElements(LearnerModule_Locators.iconListViewCourse);
            for (WebElement element : iconList) {
                String classAttribute = element.getAttribute("class");
                if("pi pi-file".equals(classAttribute))
                {
                   counter++;
                }
                System.out.println("Class attribute: " + classAttribute);
            }
            if(counter==Integer.parseInt(assessmentCount))
            {
                GemTestReporter.addTestStep("Validate the count of Assignment and test of course", "Count Matches", STATUS.PASS,
                        DriverAction.takeSnapShot());
            }
            else {
                GemTestReporter.addTestStep("Validate the count of Assignment and test of course", "Count does not Matches", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("start with course and validate upload assignment file {string}")
    public void startWithCourseAndValidateUploadAssignmentFile(String fileLocation1) {
        try{
            if (DriverAction.getElement(LearnerModule_Locators.startCourseBtn).isDisplayed()) {
                GemTestReporter.addTestStep("Verify Start Course button is present", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.click(LearnerModule_Locators.startCourseBtn);
//                DriverAction.waitUntilElementDisappear(Course_Locators.loadingIcon,120);
                DriverAction.waitSec(10);
                DriverAction.waitUntilElementClickable((LearnerModule_Locators.completeAndContinueBtn),90);
                if(DriverAction.isEnabled(LearnerModule_Locators.completeAndContinueBtn)) {
                    DriverAction.click(LearnerModule_Locators.completeAndContinueBtn);
                    DriverAction.waitSec(2);
                    String popUp = DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                    if ("Content Completed Successfully".equals(popUp)) {
                        GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Successfully " + popUp + " appears", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify confirmation popup message" + popUp, "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
                    }

                    if(DriverAction.isExist(LearnerModule_Locators.uploadFileBtn,120))
                    {

                        DriverAction.click(LearnerModule_Locators.uploadFileBtn);

                        DriverAction.fileUpload(LearnerModule_Locators.chooseFile,fileLocation1);
                        DriverAction.waitSec(5);
                        if(DriverAction.isExist(LearnerModule_Locators.uploadBtn,120))
                        {
                            DriverAction.click(LearnerModule_Locators.uploadBtn);
                        }
                        else {
                            GemTestReporter.addTestStep("Error Occur", "Fail to click on Upload assignment file", STATUS.FAIL,
                                    DriverAction.takeSnapShot());
                        }
                        if(DriverAction.isDisplayed(LearnerModule_Locators.errorMessageforEmptyFeild))
                        {
                            GemTestReporter.addTestStep("Validate keeping answer name empty error message appear", "Error message appear", STATUS.PASS,
                                    DriverAction.takeSnapShot());
                        }
                        else
                        {
                            GemTestReporter.addTestStep("Validate keeping answer name empty error message appear", "Error message not appear", STATUS.FAIL,
                                    DriverAction.takeSnapShot());
                        }

                        if(DriverAction.isEnabled(LearnerModule_Locators.cancelBtn))
                        {
                            DriverAction.click(LearnerModule_Locators.cancelBtn);
                        }
                        else {
                            GemTestReporter.addTestStep("Error Occur", "Fail to click on Cancel button", STATUS.FAIL,
                                    DriverAction.takeSnapShot());
                        }

                            DriverAction.fileUpload(LearnerModule_Locators.chooseFile, fileLocation1);
                            DriverAction.waitSec(5);
                            if(DriverAction.isExist(LearnerModule_Locators.answerInput,120))
                            {
                                DriverAction.typeText(LearnerModule_Locators.answerInput,"demo");
                            }
                            else {
                                GemTestReporter.addTestStep("Error Occur", "Fail to type into answer input", STATUS.FAIL,
                                        DriverAction.takeSnapShot());
                            }
                            if (DriverAction.isExist(LearnerModule_Locators.uploadBtn, 120)) {
                                DriverAction.click(LearnerModule_Locators.uploadBtn);
                            } else {
                                GemTestReporter.addTestStep("Error Occur", "Fail to click on Upload assignment file", STATUS.FAIL,
                                        DriverAction.takeSnapShot());
                            }

                    }
                    else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to click on Upload assignment file", STATUS.FAIL,
                                DriverAction.takeSnapShot());
                    }
                }

            } else {
                GemTestReporter.addTestStep("Verify Start Course button is present", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @Then("start with course and validate View upload file")
    public void startWithCourseAndValidateViewUploadFile() {
        try{
            DriverAction.waitSec(5);
                    if(DriverAction.isExist(LearnerModule_Locators.viewUploadBtn,120))
                    {
                        DriverAction.click(LearnerModule_Locators.viewUploadBtn);
                    }
                    else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to click on view upload button", STATUS.FAIL,
                                DriverAction.takeSnapShot());
                    }
                    LocalDate currentDate = LocalDate.now();

                    // Format the date as "year, month name, date"
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_uuuu_MMM_dd");
                    String formattedDate = currentDate.format(formatter);

                    // Print the formatted date
                    System.out.println("Formatted Date: " + formattedDate);
                    DriverAction.waitSec(5);
                    String name="demo"+formattedDate;
                    String fetchedName=DriverAction.getElementText(LearnerModule_Locators.docName);
                    if(fetchedName.contains("demo"))
                    {
                        GemTestReporter.addTestStep("Validate View upload is working properly", "Data is coming properly", STATUS.PASS,
                                DriverAction.takeSnapShot());
                    }
                    else
                    {
                        GemTestReporter.addTestStep("Validate View upload is working properly", "Data is not coming properly", STATUS.FAIL,
                                DriverAction.takeSnapShot());
                    }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate Non existing completed course")
    public void validateNonExistingCompletedCourse() {
        try{
if(DriverAction.isExist(LearnerModule_Locators.completedTab,120))
{
    DriverAction.click(LearnerModule_Locators.completedTab);
}
else {
    GemTestReporter.addTestStep("Error Occur", "Fail to click on completed tab", STATUS.FAIL,
            DriverAction.takeSnapShot());
}
            if(DriverAction.isExist(LearnerModule_Locators.searchInput,120))
            {
                DriverAction.typeText(LearnerModule_Locators.searchInput,"testtesttest");
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type into course search input", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isDisplayed(LearnerModule_Locators.noCourseMessage))
            {
                GemTestReporter.addTestStep("Validate entering non exist course name no course should be displayed", "No course display successfully", STATUS.PASS,
                        DriverAction.takeSnapShot());
            }
            else {
                GemTestReporter.addTestStep("Validate entering non exist course name no course should be displayed", "Not working fine", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate Non existing course in course catalog")
    public void validateNonExistingCourseInCourseCatalog() {
        try{
            if(DriverAction.isExist(LearnerModule_Locators.courseCatalogbtn,120))
            {
                DriverAction.click(LearnerModule_Locators.courseCatalogbtn);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Course catalog", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(LearnerModule_Locators.searchInput,120))
            {
                DriverAction.typeText(LearnerModule_Locators.searchInput,"testtesttest");
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type into course search input", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(5);
            if(DriverAction.isDisplayed(LearnerModule_Locators.noCourseCourseCatalog))
            {
                GemTestReporter.addTestStep("Validate entering non exist course name no course should be displayed", "No course display successfully", STATUS.PASS,
                        DriverAction.takeSnapShot());
            }
            else {
                GemTestReporter.addTestStep("Validate entering non exist course name no course should be displayed", "Not working fine", STATUS.FAIL,
                        DriverAction.takeSnapShot());
            }

        }
        catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }
}
