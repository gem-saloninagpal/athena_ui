package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.TemplateLibrary_Locators;
import com.qa.athenaUi.locators.TestAnalyticsLocators;
import com.qa.athenaUi.locators.Tests_TestControl_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class TemplateLibrary {
    @When("user clicks on Create Template button")
    public void userClicksOnCreateTemplateButton() {
        try {
            DriverAction.waitUntilElementIsClickable(TemplateLibrary_Locators.createTemplateBtn);
            DriverAction.click(TemplateLibrary_Locators.createTemplateBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Template not created", "Error occurred: " + e, Status.ERR);
        }
    }

    @And("^user enters \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" in respective fields$")
    public void userEntersInRespectiveFields(String templateName, String templateSubject, String content) {
        try {
            DriverAction.waitSec(2);
            DriverAction.typeText(TemplateLibrary_Locators.templateName, templateName);
            DriverAction.waitSec(1);
            DriverAction.typeText(TemplateLibrary_Locators.templateSubject, templateSubject);
            DriverAction.waitSec(1);
            DriverAction.typeText(TemplateLibrary_Locators.editor, content);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Content not entered", "Error occurred: " + e, Status.ERR);
        }
    }

    @And("user clicks on Save button")
    public void userClicksOnSaveButton() {
        try {
            DriverAction.scrollToBottom();
            DriverAction.scrollIntoView(TemplateLibrary_Locators.saveBtn);
            DriverAction.waitSec(4);
            DriverAction.click(TemplateLibrary_Locators.saveBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("^verify \"([^\"]*)\" is created$")
    public void verifyIsCreated(String templateName) {
        try {
            DriverAction.waitSec(10);
            DriverAction.waitUntilElementAppear(TemplateLibrary_Locators.searchBar, 120);
            DriverAction.typeText(TemplateLibrary_Locators.searchBar, templateName);
            DriverAction.waitSec(2);
            String searched = DriverAction.getElementText(TemplateLibrary_Locators.searched);
            if(searched.equals(templateName)) {
                GemTestReporter.addTestStep("Template created", "Template has been successfully created", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Template not created", "Template not created", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Template not created", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("^verify \"([^\"]*)\" appears$")
    public void verifyAppears(String error) {
        try {
            DriverAction.waitSec(5);
            DriverAction.scrollIntoView(TemplateLibrary_Locators.errorMsg);
            DriverAction.scrollIntoView(TemplateLibrary_Locators.error2);
            String error_displayed = DriverAction.getElementText(TemplateLibrary_Locators.errorMsg);
            String error2_displayed = DriverAction.getElementText(TemplateLibrary_Locators.error2);
            if ((error_displayed.equals(error)) || (error2_displayed.equals(error))) {
                GemTestReporter.addTestStep("Error displayed", "Error is being displayed", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Error not displayed", "Error not being displayed", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Error not displayed", "Error occurred: " + e, Status.ERR);
        }
    }

    @And("user clicks on Reset button")
    public void userClicksOnResetButton() {
        try {
            DriverAction.scrollToBottom();
            DriverAction.scrollIntoView(TemplateLibrary_Locators.resetBtn);
            DriverAction.waitSec(4);
            DriverAction.click(TemplateLibrary_Locators.resetBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("verify fields are cleared")
    public void verifyFieldsAreCleared() {
        try {
            DriverAction.waitSec(2);
            String getText = DriverAction.getElementText(TemplateLibrary_Locators.templateName);
            System.out.println(getText);
            if(getText.equals(null)) {
                GemTestReporter.addTestStep("Fields cleared", "Fields were cleared ", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Fields not cleared", "Fields were not cleared ", Status.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Fields not cleared", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("verify warning popup appears")
    public void verifyWarningPopupAppears() {
        try {
            DriverAction.waitSec(3);
            if(DriverAction.isDisplayed(TemplateLibrary_Locators.warningPopup)) {
                GemTestReporter.addTestStep("Warning popup appeared", "Warning popup appeared on the screen", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Warning popup did not appear", "Warning popup did not appear on the screen", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Warning pop did not appear", "Error occurred: " + e, Status.ERR);
        }
    }

    @And("user clicks on No in warning popup")
    public void userClicksOnNoInWarningPopup() {
        try {
            DriverAction.waitSec(3);
            if (DriverAction.isDisplayed(TemplateLibrary_Locators.noBtn)) {
                DriverAction.waitUntilElementIsClickable(TemplateLibrary_Locators.noBtn);
                DriverAction.click(TemplateLibrary_Locators.noBtn);
                GemTestReporter.addTestStep("Button clicked", "No Button was clicked", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Button not clicked", "No button was not clidked", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Button not clicked", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("verify data is not cleared")
    public void verifyDataIsNotCleared() {
        try {
            DriverAction.waitSec(3);
            String currName = DriverAction.getElementText(TemplateLibrary_Locators.templateNameContent);
            System.out.println(currName);
            if (currName.equals(null)) {
                GemTestReporter.addTestStep("Data not matched", "Data was not matched", Status.FAIL);
            } else {
                GemTestReporter.addTestStep("Data matched", "Data was matched", Status.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Data not matched", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("verify edit template screen is displayed")
    public void verifyEditTemplateScreenIsDisplayed() {
        try {
            if(DriverAction.isDisplayed(TemplateLibrary_Locators.editScreen)) {
                GemTestReporter.addTestStep("Edit screen displayed", "Successfully navigated to edit screen", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Edit screen not displayed", "Not navigated to edit screen", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Edit screen not displayed", "Error occurred: " + e, Status.ERR);
        }
    }

    @When("^user clicks on actions icon of \"([^\"]*)\" and selects \"([^\"]*)\"$")
    public void userClicksOnActionsIconOfAndSelects(String templateName, String action) {
        try {
            DriverAction.waitSec(3);
            DriverAction.typeText(TemplateLibrary_Locators.searchBar, templateName);
            DriverAction.waitSec(4);
            DriverAction.waitUntilElementAppear(TemplateLibrary_Locators.actionsIcon, 120);
            DriverAction.click(TemplateLibrary_Locators.actionsIcon);
            DriverAction.waitSec(5);
            DriverAction.waitUntilElementIsClickable(By.xpath(TemplateLibrary_Locators.actionsItem.replace("input", action)));
            DriverAction.click(By.xpath(TemplateLibrary_Locators.actionsItem.replace("input", action)));
        } catch (Exception e) {
            GemTestReporter.addTestStep("Actions button not clicked", "Error occurred: " + e, Status.ERR);
        }
    }

    @When("^user enters \"([^\"]*)\" in searchbar$")
    public void userEntersInSearchbar(String templateName) {
        DriverAction.waitSec(2);
        DriverAction.typeText(TemplateLibrary_Locators.searchBar, templateName);
    }

    @Then("^verify template \"([^\"]*)\" is searched$")
    public void verifyTemplateIsSearched(String templateName) {
        try {
            String searchedName = DriverAction.getElementText(TemplateLibrary_Locators.searched);
            if (searchedName.equals(templateName)) {
                GemTestReporter.addTestStep("Search verified", "Template was searched", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Search not verified", "Template was not searched", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Search not verified", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("verify preview screen is displayed")
    public void verifyPreviewScreenIsDisplayed() {
        try {
            if(DriverAction.isDisplayed(TemplateLibrary_Locators.previewScreen)) {
                GemTestReporter.addTestStep("Preview screen displayed", "Template preview was displayed", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Preview screen not displayed", "Template preview was not displayed", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Preview screen not displayed", "Error occurred: " + e, Status.ERR);
        }
    }

    @Then("^verify template \"([^\"]*)\" is deleted$")
    public void verifyTemplateIsDeleted(String templateName) {
        try {
            DriverAction.waitSec(10);
            DriverAction.typeText(TemplateLibrary_Locators.searchBar, templateName);
            if(DriverAction.isDisplayed(TemplateLibrary_Locators.noRecords)) {
                GemTestReporter.addTestStep("Template successfully deleted", "Template has been successfully deleted", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Template not deleted", "Template has not been deleted", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Template not deleted", "Error occurred: " + e, Status.ERR);
        }
    }

    @When("^user clicks on actions icon of \"([^\"]*)\" and selects delete$")
    public void userClicksOnActionsIconOfAndSelectsDelete(String templateName) {
        try {
            DriverAction.waitSec(3);
            DriverAction.typeText(TemplateLibrary_Locators.searchBar, templateName);
            DriverAction.waitSec(4);
            DriverAction.waitUntilElementAppear(TemplateLibrary_Locators.actionsIcon, 120);
            DriverAction.click(TemplateLibrary_Locators.actionsIcon);
            DriverAction.waitSec(5);
            DriverAction.waitUntilElementIsClickable(By.xpath(TemplateLibrary_Locators.actionsItem.replace("input", "Delete")));
            DriverAction.click(By.xpath(TemplateLibrary_Locators.actionsItem.replace("input", "Delete")));
            DriverAction.waitSec(3);
            DriverAction.click(TemplateLibrary_Locators.yesBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Template not deleted", "Error occurred: " + e, Status.ERR);
        }
    }
}
