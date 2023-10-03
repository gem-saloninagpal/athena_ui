package com.gemini.athena.stepdefinitions;

import com.gemini.athena.locators.Manage_Sections_Locators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Manage_Sections {

    String SectionName="";
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);

    @And("^Enter Section Name and Section Description in Create section fields$")
    public void createSection()
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
    if (DriverAction.isExist(Manage_Sections_Locators.addBtn,120)) {
        DriverAction.click(Manage_Sections_Locators.addBtn);
    } else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click add button",
                STATUS.FAIL, DriverAction.takeSnapShot());
    }
    DriverAction.waitSec(2);
    if (DriverAction.isExist(Manage_Sections_Locators.addBtn,120)) {
        DriverAction.click(Manage_Sections_Locators.addBtn);
    } else {
        GemTestReporter.addTestStep("Error Occur", "Fail to click add button",
                STATUS.FAIL, DriverAction.takeSnapShot());
    }


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

}
