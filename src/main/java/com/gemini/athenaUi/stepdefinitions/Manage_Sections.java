package com.gemini.athenaUi.stepdefinitions;


import com.gemini.athenaUi.locators.*;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Manage_Sections {

    String SectionName="";
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);

    @And("^Enter Section Name and Section Description in Create section fields \"([^\"]*)\"$")
    public void createSection(String popUpMessage)
    {
try{
    //type in section name textarea
    if (DriverAction.isExist(Manage_Sections_Locators.sectionNameTextArea)) {
        SectionName= RandomStringUtils.randomAlphanumeric(10);
        DriverAction.typeText(Manage_Sections_Locators.sectionNameTextArea,SectionName);

    } else {
        GemTestReporter.addTestStep("Error Occur", "Fail to type in Section Name textarea",
                STATUS.FAIL, DriverAction.takeSnapShot());
    }
    //type in section description
    if (DriverAction.isExist(Manage_Sections_Locators.sectionDescriptionTextArea)) {
        DriverAction.typeText(Manage_Sections_Locators.sectionDescriptionTextArea,SectionName+"12");

    } else {
        GemTestReporter.addTestStep("Error Occur", "Fail to type in Section Description textarea",
                STATUS.FAIL, DriverAction.takeSnapShot());
    }
    //click the add button
    DriverAction.waitSec(3);
    DriverAction.click(Manage_Sections_Locators.addBtn);
//    if (DriverAction.isExist(Manage_Sections_Locators.addBtn,120)) {
//        DriverAction.click(Manage_Sections_Locators.addBtn);
//    } else {
//        GemTestReporter.addTestStep("Error Occur", "Fail to click add button",
//                STATUS.FAIL, DriverAction.takeSnapShot());
//    }

    if (DriverAction.isExist(UserDashboard_Locator.popupMessage,120)) {
       String fetchedMsg=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
        System.out.println("hello");
       System.out.println(fetchedMsg);
        System.out.println("hello");
       if(popUpMessage.trim().equals(fetchedMsg.trim()))
       {
           GemTestReporter.addTestStep("Validate"+popUpMessage+" appear after creating section", popUpMessage+" message is appeared successfully",
                   STATUS.PASS, DriverAction.takeSnapShot());
       }
       else
       {
           GemTestReporter.addTestStep("Validate"+popUpMessage+" appear after creating section", popUpMessage+" message is not appeared successfully",
                   STATUS.FAIL, DriverAction.takeSnapShot());
       }
    } else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click add button",
                STATUS.FAIL, DriverAction.takeSnapShot());
    }
//Section Type Added Successfully

}
catch (Exception e) {
    GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
}

    }
    @Then("^Validate section is created$")
    public void validateSection()
    {
        try{
            //validate created section is visible on Manage section page.
            int c=0;
            DriverAction.waitSec(5);
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNames,120)) {
                List<String> actualOptions = DriverAction.getElementsText(Manage_Sections_Locators.sectionNames);
                for (String i : actualOptions) {
                    if(SectionName.equals(i))
                    {
                        GemTestReporter.addTestStep("Section is Created on Manage section Page", SectionName+" is successfully created and added to Manage Section Page",
                                STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        c++;
                    }
                }
                if(c==actualOptions.size())
                {
                    GemTestReporter.addTestStep("Section is Created on Manage section Page", "Section is not created",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }

        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }

    }

    @Then("^Validate section is edited \"([^\"]*)\"$")
    public void editAndValidate(String popUpMessage)
    {
        try{
            //edit the created section
            List<WebElement>  edit= DriverAction.getElements(Manage_Sections_Locators.editIconList);
            DriverAction.scrollToBottom();
            if(DriverAction.isExist(By.xpath(Manage_Sections_Locators.editIcon.replace("itr",String.valueOf(edit.size()))),120))
            {
                DriverAction.click(By.xpath(Manage_Sections_Locators.editIcon.replace("itr",String.valueOf(edit.size()))));
            }
            DriverAction.waitSec(3);
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNameTextArea)) {
                SectionName= RandomStringUtils.randomAlphanumeric(10);
                DriverAction.typeText(Manage_Sections_Locators.sectionNameTextArea,SectionName);

            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Section Name textarea",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
           DriverAction.click(Manage_Sections_Locators.updateBtn);


            if (DriverAction.isExist(UserDashboard_Locator.popupMessage,120)) {
                String fetchedMsg=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                System.out.println("hello");
                System.out.println(fetchedMsg);
                System.out.println("hello");
                if(popUpMessage.trim().equals(fetchedMsg.trim()))
                {
                    GemTestReporter.addTestStep("Validate"+popUpMessage+" appear after editing section", popUpMessage+" message is appeared successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate"+popUpMessage+" appear after editing section", popUpMessage+" message is not appeared successfully",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click update button",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            //validate on Manage section page
            int c=0;
            DriverAction.waitSec(5);
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNames,120)) {
                List<String> actualOptions = DriverAction.getElementsText(Manage_Sections_Locators.sectionNames);
                for (String i : actualOptions) {
                    if(SectionName.equals(i))
                    {
                        GemTestReporter.addTestStep("Section is updated on Manage section Page", SectionName+" is successfully updated and added to Manage Section Page",
                                STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        c++;
                    }
                }
                if(c==actualOptions.size())
                {
                    GemTestReporter.addTestStep("Section is updated on Manage section Page", "Section is not updated",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }

    }
    @Then("^Validate section is deleted \"([^\"]*)\"$")
    public void deleteAndValidate(String popUpMessage)
    {
        try{
            //edit the created section
            List<WebElement>  edit= DriverAction.getElements(Manage_Sections_Locators.deleteIconList);
            DriverAction.scrollToBottom();
            if(DriverAction.isExist(By.xpath(Manage_Sections_Locators.deleteIcon.replace("itr",String.valueOf(edit.size()))),120))
            {
                DriverAction.click(By.xpath(Manage_Sections_Locators.deleteIcon.replace("itr",String.valueOf(edit.size()))));
            }
            if(DriverAction.isExist(Manage_Sections_Locators.yesBtn,120))
            {
                DriverAction.click(Manage_Sections_Locators.yesBtn);
            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to click Yes Popup",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(3);



            if (DriverAction.isExist(UserDashboard_Locator.popupMessage,120)) {
                String fetchedMsg=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                System.out.println("hello");
                System.out.println(fetchedMsg);
                System.out.println("hello");
                if(popUpMessage.trim().equals(fetchedMsg.trim()))
                {
                    GemTestReporter.addTestStep("Validate"+popUpMessage+" appear after deleting section", popUpMessage+" message is appeared successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate"+popUpMessage+" appear after deleting section", popUpMessage+" message is not appeared successfully",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click delete button",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            //validate on Manage section page
            int c=0;
            DriverAction.waitSec(5);
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNames,120)) {
                List<String> actualOptions = DriverAction.getElementsText(Manage_Sections_Locators.sectionNames);
                for (String i : actualOptions) {
                    if(SectionName.equals(i))
                    {
                        GemTestReporter.addTestStep("Section is deleted from Manage section Page", SectionName+" is not deleted from Manage Section Page",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                    else
                    {
                        c++;
                    }
                }
                if(c==actualOptions.size())
                {
                    GemTestReporter.addTestStep("Section is deleted from Manage section Page", "Section is deleted successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }

    }

    @And("^select the any section from manage section screen and validate$")
    public void selectSection()
    {
        try{
            String sectionNameBeforeSelecting=DriverAction.getElementText(Manage_Sections_Locators.firstSectionHeading);
            String sectionDescBeforeSelecting=DriverAction.getElementText(Manage_Sections_Locators.firstSectionDesc);
            DriverAction.click(Manage_Sections_Locators.firstSectionHeading);
            String sectionName=DriverAction.getAttributeName(Manage_Sections_Locators.sectionNameTextArea,"value");
            String sectionDesc=DriverAction.getAttributeName(Manage_Sections_Locators.sectionDescriptionTextArea,"value");
              System.out.println(sectionName);
              if(sectionDescBeforeSelecting.trim().equals(sectionDesc.trim())&&sectionNameBeforeSelecting.trim().equals(sectionName.trim()))
              {
                  GemTestReporter.addTestStep("Validate section Name and Description", "Section Name and Description Matches",
                          STATUS.PASS, DriverAction.takeSnapShot());
              }
              else
              {
                  GemTestReporter.addTestStep("Validate section Name and Description", "Section Name and Description not Matches",
                          STATUS.FAIL, DriverAction.takeSnapShot());
              }
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }

    }


}
