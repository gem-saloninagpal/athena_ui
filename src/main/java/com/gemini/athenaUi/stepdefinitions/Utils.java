package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.*;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static com.gemini.generic.ui.utils.DriverAction.getElements;

public class Utils {
    Logger logger = LoggerFactory.getLogger(LearnerModule.class);

    public static void waitUntilElementDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 60);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitUntilLoaderDisappear() {
        if (Utils.isExist(Utils_Locators.loader)) {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 60);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(Utils_Locators.loader));
        }
    }

    public static void waitUntilElementAppear(By locator) {
        if (Utils.isExist(locator)) {
            WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(60));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }
    public static boolean isExist(By locator) {
        List<WebElement> elementList = getElements(locator);
        if (CollectionUtils.isEmpty(elementList)) {
            return false;
        } else {
            return true;
        }
    }
    @Given("Switch the user to {string}")
    public void switchTheUserTo(String role) {
        try{
            //in this function we are switching the required role
            if(DriverAction.isExist(Role_Management_Locators.roleDropdown,120))
            {
                DriverAction.click(Role_Management_Locators.roleDropdown);
                if(DriverAction.isExist(By.xpath(Role_Management_Locators.selectedRole.replace("role",role)),120))
                {
                    DriverAction.click(By.xpath(Role_Management_Locators.selectedRole.replace("role",role)));
                }
                DriverAction.waitSec(3);
            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on dropdown icon",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }


    @Then("Validate {string} get updated")
    public void validateGetUpdated(String role) {
        try{
            //validate user get switched
            String fetchedRole=DriverAction.getElementText(Utils_Locators.roleSpan);
            if(fetchedRole.equals(role))
            {
                GemTestReporter.addTestStep("Validate Role is switched successfully", "Role get successfully updated to "+fetchedRole,
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                GemTestReporter.addTestStep("Validate Role is switched successfully", "Role not get updated successfully to "+role,
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }

    @Then("switch the tab to {string}")
    public void switchTheTabTo(String tab) {
        try{
            // switch the tab
            if(DriverAction.isExist(By.xpath(Utils_Locators.tab.replace("tab1",tab))))
            {
                DriverAction.click(By.xpath(Utils_Locators.tab.replace("tab1",tab)),"Validate user able to click on "+tab,"User successfully switched to "+tab);
            }
            else
            {
                GemTestReporter.addTestStep("Validate tab is exist", "No Tab is there",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }

        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }

    @Then("Use Date Filter and validate after removing filter it display the unfiltered Courses")
    public void useDateFilterAndValidateAfterRemovingFilterItDisplayTheUnfilteredCourses() {
        try{
            DriverAction.waitSec(4);
            if(DriverAction.isDisplayed(Course_Locators.noCompleteCourseMessage))
            {
                GemTestReporter.addTestStep("Validate there is no completed course for this learner", "User have not completed any course yet",
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else
            {
                //update the filter
                DriverAction.click(Course_Locators.yearFilter,"Validate user able to click on year filter","User successfully clicked on Year filter");
                DriverAction.click(By.xpath(Course_Locators.option.replace("input","2022")));
                DriverAction.waitSec(3);
                DriverAction.click(Course_Locators.crossIconinFilter,"Validate user able to remove filter","User successfully removed the filter");
                DriverAction.waitSec(4);
                if(DriverAction.isDisplayed(Course_Locators.noCompleteCourseMessage))
                {
                    GemTestReporter.addTestStep("Validate there is completed course data for the learner", "User have not completed any course yet",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate there is completed course data for the learner", "User successfully removed the filter and data for learner is visible",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }


            }




        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }

    @And("Click {string} button")
    public void clickButton(String buttonName) {
        try{
             DriverAction.click(By.xpath(Tests_TestControl_Locators.clickButton.replace("input",buttonName)),"Validate user able to click on "+buttonName,"User Successfully clicked on "+buttonName);
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }
}
