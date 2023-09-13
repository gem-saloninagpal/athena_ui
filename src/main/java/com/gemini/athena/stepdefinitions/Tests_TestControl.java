package com.gemini.athena.stepdefinitions;

import com.gemini.athena.locators.Course_Locators;
import com.gemini.athena.locators.Tests_TestControl_Locators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Tests_TestControl {
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);
    String testName=" ";
    @And("^Add Test Info \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void addTestInfo(String testTag,String duration,String campus,String level,String startDate,String endDate ){
   try {
       int c=2;
       int c1=1;
       List<WebElement> inputFields = DriverAction.getElements(Tests_TestControl_Locators.testInputFields);
       testName = RandomStringUtils.randomAlphanumeric(10);
       String inputValues[] = {testName,testTag, duration, campus, level, startDate, endDate};
       for (int i = 1; i <=7; i++) {
           String dropdown = inputFields.get(i).getAttribute("aria-haspopup");
           String calendar = inputFields.get(i).getAttribute("id");
//           String upload = inputFields.get(i).getAttribute("type");
           //dropdown
           if (dropdown != null && dropdown.equals("listbox")) {

               DriverAction.click(By.xpath(Course_Locators.dropdownIcon.replace("itr",String.valueOf(c))));
               DriverAction.click(By.xpath(Course_Locators.option.replace("input", inputValues[i-1])));
               c++;
           }
           //calendar
           if(calendar!=null && (calendar.equals("calendar1")||calendar.equals("calendar2")))
           {
               DriverAction.click(By.xpath(Tests_TestControl_Locators.calendar.replace("itr",String.valueOf(c1))));
               DriverAction.waitSec(3);
               if(c1==1) {
//                   DriverAction.click(By.xpath("//span[text()='12']"));
                  DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", startDate)));
               }
               else if(c1==2)
               {
//                   DriverAction.click(By.xpath("//span[text()='13']"));
                  DriverAction.click(By.xpath(Tests_TestControl_Locators.date.replace("date", endDate)));
               }
               c1++;
           }
           //textbox
           else {
               DriverAction.typeText(inputFields.get(i), inputValues[i-1]);
           }
       }
       DriverAction.scrollToBottom();
   }
catch(Exception e){
    logger.info("Exception occurred", e);
    GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e, STATUS.ERR);
}

    }
    @And("^Enter Test Description \"([^\"]*)\"$")
    public void courseDescription(String description) {
        try{

            DriverAction.typeText(Course_Locators.courseDescription,description);

        }catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e,STATUS.ERR);
        }
    }
    @And("^Add Section \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void addSection(String section,String percentage,String duration){
        try{
           if(DriverAction.isDisplayed(Tests_TestControl_Locators.dropdown))
           {
               DriverAction.click(Tests_TestControl_Locators.dropdown);
               if(DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.option.replace("section",section))));
               {
                  DriverAction.click(By.xpath(Tests_TestControl_Locators.option.replace("section",section)));
               }
               if(DriverAction.isDisplayed(Tests_TestControl_Locators.percentageInput))
               {
                   DriverAction.typeText(Tests_TestControl_Locators.percentageInput,percentage);
               }
               if(DriverAction.isDisplayed(Tests_TestControl_Locators.timeInput))
               {
                   DriverAction.typeText(Tests_TestControl_Locators.timeInput,duration);
               }
           }
        }
        catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e, STATUS.ERR);
        }

    }
    @And("^Add Question to the section$")
    public void addQuestionSection(){
        try{
            DriverAction.scrollToBottom();

            //click on Add Question for the following test
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.addQuestion))
            {
                DriverAction.click(Tests_TestControl_Locators.addQuestion);
            }
            DriverAction.scrollToBottom();
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.importRandomBtn))
            {
                DriverAction.click(Tests_TestControl_Locators.importRandomBtn);
            }
            DriverAction.waitSec(3);
            //filling the Question Details
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.questionTypeDropdown))
            {
                DriverAction.click(Tests_TestControl_Locators.questionTypeDropdown);
                if(DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input","Multiple choice question"))))
                {
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input","Multiple choice question")));
                }
            }
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.noOfQuestionInput))
            {
                DriverAction.typeText(Tests_TestControl_Locators.noOfQuestionInput,"2");
            }
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.difficultyDropdown))
            {
                DriverAction.click(Tests_TestControl_Locators.difficultyDropdown);
                if(DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input","Hard"))))
                {
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input","Hard")));
                }
            }
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.levelDropdown))
            {
                DriverAction.click(Tests_TestControl_Locators.levelDropdown);
                if(DriverAction.isDisplayed(By.xpath(Tests_TestControl_Locators.options.replace("input","Basic"))))
                {
                    DriverAction.click(By.xpath(Tests_TestControl_Locators.options.replace("input","Basic")));
                }
            }
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.submitButton))
            {
                DriverAction.click(Tests_TestControl_Locators.submitButton);
            }
            DriverAction.scrollToBottom();
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.saveButton))
            {
                DriverAction.click(Tests_TestControl_Locators.saveButton);
            }
            DriverAction.scrollToBottom();
            if(DriverAction.isDisplayed(Tests_TestControl_Locators.continueButton))
            {
                DriverAction.click(Tests_TestControl_Locators.continueButton);
            }
        }
        catch(Exception e){
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e, STATUS.ERR);
        }
    }
@And("^Validate Test is Created$")
    public void validateTestCreated(){
   try{

       if(DriverAction.isDisplayed(Tests_TestControl_Locators.createdTestText))
       {
           String fetchedTestName=DriverAction.getElementText(Tests_TestControl_Locators.createdTestText);
           if(testName.equals(fetchedTestName))
           {
               GemTestReporter.addTestStep("Verify Test is Created ","Test is Created Successfully", STATUS.PASS, DriverAction.takeSnapShot());

           }
           else
           {
               GemTestReporter.addTestStep("Verify Test is Created ","Test is not Created Successfully", STATUS.FAIL, DriverAction.takeSnapShot());

           }

       }

   }
   catch(Exception e){
       logger.info("Exception occurred", e);
       GemTestReporter.addTestStep("Enter course description","Exception encountered- "+e, STATUS.ERR);
   }
}
}
