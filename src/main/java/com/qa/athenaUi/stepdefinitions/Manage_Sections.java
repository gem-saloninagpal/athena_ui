package com.qa.athenaUi.stepdefinitions;


import com.qa.athenaUi.locators.*;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.Manage_Sections_Locators;
import com.qa.athenaUi.locators.UserDashboard_Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Manage_Sections {

    String SectionName = "";
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);

    @And("^Enter Section Name and Section Description in Create section fields$")
    public void createSection() {
        try {
            //type in section name textarea
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNameTextArea)) {
                SectionName = RandomStringUtils.randomAlphanumeric(10);
                DriverAction.typeText(Manage_Sections_Locators.sectionNameTextArea, SectionName);

            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Section Name textarea",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
            //type in section description
            if (DriverAction.isExist(Manage_Sections_Locators.sectionDescriptionTextArea)) {
                DriverAction.typeText(Manage_Sections_Locators.sectionDescriptionTextArea, SectionName + "12");

            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Section Description textarea",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
            //click the add button
            DriverAction.waitSec(3);
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, Status.FAIL);
        }

    }

    @Then("^Validate section is created$")
    public void validateSection() throws InterruptedException {
        Thread.sleep(3000);
        try {
            //validate created section is visible on Manage section page.
            int c = 0;
            DriverAction.waitSec(5);
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNames)) {
                List<String> actualOptions = DriverAction.getElementsText(Manage_Sections_Locators.sectionNames);
                for (String i : actualOptions) {
                    if (SectionName.equals(i)) {
                        GemTestReporter.addTestStep("Section is Created on Manage section Page", SectionName + " is successfully created and added to Manage Section Page",
                                Status.PASS, DriverAction.takeSnapShot());
                    } else {
                        c++;
                    }
                }
                if (c == actualOptions.size()) {
                    GemTestReporter.addTestStep("Section is Created on Manage section Page", "Section is not created",
                            Status.FAIL, DriverAction.takeSnapShot());
                }
            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, Status.FAIL);
        }

    }

    @Then("^Validate section is edited \"([^\"]*)\"$")
    public void editAndValidate(String popUpMessage) {
        try {
            //edit the created section
            List<WebElement> edit = DriverAction.getElements(Manage_Sections_Locators.editIconList);
            DriverAction.scrollToBottom();
            if (DriverAction.isExist(By.xpath(Manage_Sections_Locators.editIcon.replace("itr", String.valueOf(edit.size()))))) {
                DriverAction.click(By.xpath(Manage_Sections_Locators.editIcon.replace("itr", String.valueOf(edit.size()))));
            }
            DriverAction.waitSec(3);
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNameTextArea)) {
                SectionName = RandomStringUtils.randomAlphanumeric(10);
                DriverAction.typeText(Manage_Sections_Locators.sectionNameTextArea, SectionName);

            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in Section Name textarea",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(Manage_Sections_Locators.updateBtn);


            if (DriverAction.isExist(UserDashboard_Locator.popupMessage)) {
                String fetchedMsg = DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                System.out.println("hello");
                System.out.println(fetchedMsg);
                System.out.println("hello");
                if (popUpMessage.trim().equals(fetchedMsg.trim())) {
                    GemTestReporter.addTestStep("Validate" + popUpMessage + " appear after editing section", popUpMessage + " message is appeared successfully",
                            Status.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate" + popUpMessage + " appear after editing section", popUpMessage + " message is not appeared successfully",
                            Status.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click update button",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
            //validate on Manage section page
            int c = 0;
            DriverAction.waitSec(5);
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNames)) {
                List<String> actualOptions = DriverAction.getElementsText(Manage_Sections_Locators.sectionNames);
                for (String i : actualOptions) {
                    if (SectionName.equals(i)) {
                        GemTestReporter.addTestStep("Section is updated on Manage section Page", SectionName + " is successfully updated and added to Manage Section Page",
                                Status.PASS, DriverAction.takeSnapShot());
                    } else {
                        c++;
                    }
                }
                if (c == actualOptions.size()) {
                    GemTestReporter.addTestStep("Section is updated on Manage section Page", "Section is not updated",
                            Status.FAIL, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, Status.FAIL);
        }

    }

    @Then("^Validate section is deleted \"([^\"]*)\"$")
    public void deleteAndValidate(String popUpMessage) {
        try {
            //edit the created section
            List<WebElement> edit = DriverAction.getElements(Manage_Sections_Locators.deleteIconList);
            DriverAction.scrollToBottom();
            if (DriverAction.isExist(By.xpath(Manage_Sections_Locators.deleteIcon.replace("itr", String.valueOf(edit.size()))))) {
                DriverAction.click(By.xpath(Manage_Sections_Locators.deleteIcon.replace("itr", String.valueOf(edit.size()))));
            }
            if (DriverAction.isExist(Manage_Sections_Locators.yesBtn)) {
                DriverAction.click(Manage_Sections_Locators.yesBtn);
            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click Yes Popup",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(3);


            if (DriverAction.isExist(UserDashboard_Locator.popupMessage)) {
                String fetchedMsg = DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                System.out.println("hello");
                System.out.println(fetchedMsg);
                System.out.println("hello");
                if (popUpMessage.trim().equals(fetchedMsg.trim())) {
                    GemTestReporter.addTestStep("Validate" + popUpMessage + " appear after deleting section", popUpMessage + " message is appeared successfully",
                            Status.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate" + popUpMessage + " appear after deleting section", popUpMessage + " message is not appeared successfully",
                            Status.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click delete button",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
            //validate on Manage section page
            int c = 0;
            DriverAction.waitSec(5);
            if (DriverAction.isExist(Manage_Sections_Locators.sectionNames)) {
                List<String> actualOptions = DriverAction.getElementsText(Manage_Sections_Locators.sectionNames);
                for (String i : actualOptions) {
                    if (SectionName.equals(i)) {
                        GemTestReporter.addTestStep("Section is deleted from Manage section Page", SectionName + " is not deleted from Manage Section Page",
                                Status.FAIL, DriverAction.takeSnapShot());
                    } else {
                        c++;
                    }
                }
                if (c == actualOptions.size()) {
                    GemTestReporter.addTestStep("Section is deleted from Manage section Page", "Section is deleted successfully",
                            Status.PASS, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, Status.FAIL);
        }

    }

    @And("^select the any section from manage section screen and validate$")
    public void selectSection() {
        try {
            String sectionNameBeforeSelecting = DriverAction.getElementText(Manage_Sections_Locators.firstSectionHeading);
            String sectionDescBeforeSelecting = DriverAction.getElementText(Manage_Sections_Locators.firstSectionDesc);
            DriverAction.click(Manage_Sections_Locators.firstSectionHeading);
            String sectionName = DriverAction.getAttributeName(Manage_Sections_Locators.sectionNameTextArea, "value");
            String sectionDesc = DriverAction.getAttributeName(Manage_Sections_Locators.sectionDescriptionTextArea, "value");
            System.out.println(sectionName);
            if (sectionDescBeforeSelecting.trim().equals(sectionDesc.trim()) && sectionNameBeforeSelecting.trim().equals(sectionName.trim())) {
                GemTestReporter.addTestStep("Validate section Name and Description", "Section Name and Description Matches",
                        Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate section Name and Description", "Section Name and Description not Matches",
                        Status.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, Status.FAIL);
        }

    }
}