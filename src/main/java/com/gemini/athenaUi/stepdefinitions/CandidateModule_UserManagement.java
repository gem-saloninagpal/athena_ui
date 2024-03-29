package com.gemini.athenaUi.stepdefinitions;


import com.gemini.athenaUi.locators.MyLocators;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;

import com.github.dockerjava.api.model.Driver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.List;

public class CandidateModule_UserManagement {

    public static String _randomString = "";
    int _quesCount =0;
    String _updatedStatus ="";
    String _status ="";
    String _test="";
    public static String _name ="";


    @Given("^Login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void login(String username, String password) {

        try {
            System.out.println(_randomString);

            //enter username and password then click the sign-in button
            DriverAction.typeText(MyLocators.usernameField, username);
            DriverAction.typeText(MyLocators.passwordField, password);
            DriverAction.waitUntilElementClickable(MyLocators.signInBtn,90);
            DriverAction.click(MyLocators.signInBtn);

            Thread.sleep(25000);
            changeResolution1();
        } catch (Exception e) {
            System.out.println("Could not login to Athena.");
        }
    }

    @And("^Change resolution$")
    public void changeResolution1() {
        try {
            Robot robot = new Robot();

            for (int i = 0; i < 4; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SUBTRACT);

                robot.keyRelease(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }
        } catch (Exception e) {
           GemTestReporter.addTestStep("Change resolution","Exception encountered- "+e,STATUS.ERR);
        }


    }

    @Given("^Select \"([^\"]*)\", \"([^\"]*)\" from sidebar$")
    public void selectFromSidebar(String module, String submodule) {
        try {


            //open sidebar
                DriverAction.click(MyLocators.sidebar, "Expand the sidebar", "Sidebar expands displaying list of modules.");

            //select a module from sidebar
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", module)));

            //select submodule if required

            Thread.sleep(3000);

            if(!submodule.isEmpty()){
                DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", submodule)));
            }
            //close sidebar
            DriverAction.click(MyLocators.crossIcon, "Click the cross icon of sidebar", "Successfully clicked the cross icon.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Select module from sidebar", "Throws exception", STATUS.ERR, DriverAction.takeSnapShot());
        }
    }


    @Then("^Click the button \"([^\"]*)\"$")
    public void clickTheButton(String buttonName) throws InterruptedException {
        try {

            Thread.sleep(4000);
             if (buttonName.equals("Save & Exit") || buttonName.equals("Update & Exit") || buttonName.equals("Save & Add More") || buttonName.equals("Finish Test")) {
                    DriverAction.scrollToBottom();
                }
                DriverAction.waitUntilElementIsClickable(By.xpath(MyLocators.button.replace("input", buttonName)));
                DriverAction.click(By.xpath(MyLocators.button.replace("input", buttonName)));
                Thread.sleep(2500);

        }catch(Exception e){
            System.out.print("Exception encountered!");
        }

    }

    @And("^Verify user is navigated to page \"([^\"]*)\"$")
    public void verifyUserNavigated(String pageTitle) {
        try {
            DriverAction.waitSec(3);
            //verify the page through header
            String header = DriverAction.getElementText(By.xpath(MyLocators.header));
            if (header.contains(pageTitle)) {
                GemTestReporter.addTestStep("Verify user is navigated to " + pageTitle, "User successfully navigated to " + pageTitle, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user is navigated to " + pageTitle, "Could not navigate to page " + pageTitle, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the error while navigating to required page", "Error occured is " + e, STATUS.ERR);
        }
    }

    @And("^Verify the error displayed in input fields \"([^\"]*)\" \"([^\"]*)\"$")
    public void verifyTheErrorDisplayedInInputFields(String error, int countMandatoryFields) {
        try {
            //verify the mandatory fields display error if not filled.
            List<WebElement> fields = DriverAction.getElements(MyLocators.fieldsError);
            int c = 0;
            for(WebElement wb:fields){
                if(wb.getText().contains(error)){
                    c++;
                }
            }
            if (c == countMandatoryFields) {
                GemTestReporter.addTestStep("Verify error message in input fields", "Error message is containing " + error, STATUS.PASS, DriverAction.takeSnapShot());
            } else {

                GemTestReporter.addTestStep("Verify error message in input fields", "Error message is not displayed in empty input field.", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify error displayed in input fields", "Exception encountered " + e, STATUS.ERR);
        }
    }

    @Then("^Select a role from dropdown \"([^\"]*)\"$")
    public void selectARoleFromDropdown(String role) throws InterruptedException {
        try{
            //select a role while registering
        DriverAction.click(MyLocators.dropdownIcon, "Click the dropdown icon", "List of options displays.");
        DriverAction.click(By.xpath(MyLocators.option.replace("input", role)));

            DriverAction.click(MyLocators.crossIcon);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Then("^Enter respective values in input fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterValues(String password, String confirmPassword, String firstName, String lastName, String email, String number, String experience) {
        try {
            //enter the details in fields while registering a user
            List<WebElement> inputFields = DriverAction.getElements(MyLocators.inputFields);
            String inputValues[] = {password, confirmPassword, firstName, lastName, email, number, experience};
            for (int i = 0; i < inputFields.size(); i++) {
                String placeholder = inputFields.get(i).getAttribute("id");
                inputFields.get(i).clear();

                //here randomString is system-generated email
                if (placeholder.equalsIgnoreCase("email")) {
                    inputFields.get(i).sendKeys(_randomString);
                } else {
                    inputFields.get(i).sendKeys(inputValues[i]);
                }
                GemTestReporter.addTestStep("Enter " + inputValues[i] + " in " + placeholder, "Successfully entered " + inputValues[i], STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in input fields", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Switch to tab \"([^\"]*)\", \"([^\"]*)\"$")
    public void switchTab(String tab, int i) {
        try {
            DriverAction.click(By.xpath(MyLocators.selectTab(i)));
        }catch (Exception e) {
            GemTestReporter.addTestStep("Switch tab", "Exception encountered- " + e, STATUS.ERR);
        }
    }


    @Then("^Verify \"([^\"]*)\" is registered \"([^\"]*)\",\"([^\"]*)\"$")
    public void verifyIsRegistered(String role, String name, String lname) {
        try {
            String firstName="";
            String lastName="";

            if(role.equals("Learners")) {
                 firstName = DriverAction.getElementText(MyLocators.learnerFirstName);
                 lastName = DriverAction.getElementText(MyLocators.learnerLastName);

            }else if(role.equals("Candidates")){
                firstName = DriverAction.getElementText(MyLocators.candidateFirstName);
                lastName = DriverAction.getElementText(MyLocators.candidateLastName);

            }else{
                firstName = DriverAction.getElementText(MyLocators.employeeFirstName);
                lastName = DriverAction.getElementText(MyLocators.employeeLastName);

            }
            if (firstName.contains(name) && lastName.contains(lname)) {
                GemTestReporter.addTestStep("Verify learner is registered", "Successfully verified the registered learner " + name, STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify registered user", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Verify the popup message \"([^\"]*)\"$")
    public void verifyTheErrorPopup(String error) {
        try {
            String errorMessage = DriverAction.getElementText(MyLocators.popupMsg);
            if (errorMessage.contains(error)) {
                GemTestReporter.addTestStep("Verify the popup message", "Successfully verified popup message " + errorMessage, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the popup message", "Could not verify the popup message " + errorMessage, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify popup message", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Generate unique email$")
    public static String generateUniqueEmail() {

        try {
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            // create random string builder
            StringBuilder sb = new StringBuilder();

            // create an object of Random class
            Random random = new Random();

            // specify length of random string
            int length = 7;

            for (int i = 0; i < length; i++) {

                // generate random index number
                int index = random.nextInt(alphabet.length());

                // get character specified by index
                // from the string
                char randomChar = alphabet.charAt(index);

                // append the character to string builder
                sb.append(randomChar);
            }

            _randomString = sb.toString();
            _name = _randomString;
            _randomString = _randomString.concat("@gmail.com");
            System.out.println("Random String is: " + _randomString);
            return _name;
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while generating unique email.");
        }
        return null;

    }

    @Then("Enter password {string} and verify the required format {string}")
    public void enterPassword(String password, String format) {
        try {
            DriverAction.waitUntilElementIsClickable(MyLocators.passwordField);
            //verify the password is created based on required standards.
            DriverAction.typeText(MyLocators.passwordField, password);
            String error = DriverAction.getElementText(MyLocators.passwordFormatError1);
            if (error.equalsIgnoreCase(format)) {
                GemTestReporter.addTestStep("Verify password format", "Successfully verified the password format- "+format, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify password format", "Could not verify the password format- "+format, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify password format", "Exception encountered-" + e, STATUS.ERR, DriverAction.takeSnapShot());
        }
    }

    @Then("^Select campus from select campus dropdown$")
    public void selectCampus() {
        try {
            DriverAction.click(MyLocators.selectCampusDropdown, "Click on Select Campus dropdown", "Select Campus dropdown is clicked successfully.");
            DriverAction.click(MyLocators.selectCampus, "Select a campus from dropdown", "Campus selected successfully.");
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while selecting campus.");
        }
    }

    @Then("Select experience level from dropdown {string}")
    public void selectExperienceLevel(String level) {
        try {
            DriverAction.click(MyLocators.selectExperienceLevelDropdown, "Click on Experience Level dropdown", "Experience Level dropdown is clicked successfully.");
            DriverAction.click(By.xpath(MyLocators.selectLevel.replace("input", level)), "Select experience from dropdown", "Experience selected successfully.");
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while selecting experience level.");
        }
    }

    @Then("^Verify the role of registered user \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void verifyRole(String role1, String role2, String role3) {
        try {
            String role = DriverAction.getElementText(MyLocators.role);
            String[] roles = role.split(",");
            int numRoles = roles.length;
            int c = 0;

            //verifying role through table columns
            for (int i = 0; i < numRoles; i++) {
                if (roles[i].equals(role1) || roles[i].equals(role2) || roles[i].equals(role3)) {
                    c++;
                }
            }

            if (c <= numRoles) {
                GemTestReporter.addTestStep("Verify the role of registered user.", "Successfully verified the role", STATUS.PASS, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Verify the role of registered user.", "Could not verify the role.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the role of registered user.");
        }

    }

    @Then("^Logout of portal$")
    public void logout() {
        try {

            Thread.sleep(16000);

            DriverAction.waitUntilElementAppear(MyLocators.navbarDropdown,2);
            DriverAction.click(MyLocators.navbarDropdown, "Click dropdown icon of navbar.", "Successfully clicked the dropdown icon.");
            DriverAction.click(MyLocators.logoutOption, "Select logout from the options.", "Successfully selected Logout.");
        }
        catch(Exception e ){
            GemTestReporter.addReasonOfFailure(e+ "exception occured while logging out");
        }
    }

    @Then("^Select roles dropdown icon of navigation bar$")
    public void selectRolesIcon() {

        try {
            DriverAction.click(MyLocators.navbarRolesDropdown, "Click roles dropdown icon of navigation bar", "Roles dropdown is clicked successfully.");
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+ "exception occured while logging out");
        }

    }

    @Then("Verify the roles through user's id {string}, {string}, {string}")
    public void verifyTheRoles(String role1, String role2, String role3) {
        try {
            List<String> roles = DriverAction.getElementsText(MyLocators.rolesOption);
            int numRoles = roles.size();
            int c = 0;
            //check if count equals the number of roles
            for (int i = 0; i < numRoles; i++) {
                if (roles.get(i).contains(role1) || roles.get(i).contains(role2) || roles.get(i).contains(role3)) {
                    c++;
                }
            }
            if (c == numRoles) {
                GemTestReporter.addTestStep("Verify the roles through user's id", "Successfully verified the roles through user's id.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the roles through user's id", "Could not verify the roles through user's id.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the roles.");
        }

    }

    @Then("^Select Actions icon of first candidate displayed$")
    public void selectActionsIcon() {

        try {
            DriverAction.waitUntilElementClickable(MyLocators.actionsIcon, 40);
            DriverAction.click(MyLocators.actionsIcon, "Click the actions icon", "Successfully clicked the Actions icon.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Select actions icon of first candidate displayed","Exception encountered- "+e,STATUS.ERR);
        }

    }

    @Then("^Select Actions icon of first record displayed in Employees$")
    public void selectActionsIconEmployees() {
        try {
            DriverAction.click(MyLocators.employeeActionsIcon, "Click the actions icon", "Successfully clicked the Actions icon.");
        }catch (Exception e) {
            GemTestReporter.addTestStep("Select actions icon", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Select Edit Profile option$")
    public void editProfile() {
        try {
            DriverAction.click(MyLocators.editProfile, "Select Edit Profile option from dropdown", "Successfully selected the Edit Profile option.");
        }catch (Exception e) {
            GemTestReporter.addTestStep("Select edit profile option", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Enable editing$")
    public void enableEditing() throws InterruptedException {
        try {
            DriverAction.scrollToBottom();
            DriverAction.click(MyLocators.enableEditing1, "Enable editing of Registered user", "Successfully clicked Enable Editing option.");

            Thread.sleep(3000);

        }catch (Exception e) {
            GemTestReporter.addTestStep("Enable editing", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Verify user not able to edit role$")
    public void verifyEditRole() {
        try {
            String isEditable = "";
            isEditable=DriverAction.getAttributeName(MyLocators.rolesField, "readonly");

            System.out.println(isEditable);
            if (isEditable.equals("true")) {
                GemTestReporter.addTestStep("Verify user is not able to edit roles", "Successfully verified the user not able to edit roles.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user is not able to edit roles", "Could not verify the user unable to edit roles.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while editing the roles.");
        }
    }

    @And("^Verify the status of user and click status button$")
    public void verifyStatusButton() {
        try {
            _status = DriverAction.getElementText(MyLocators.userStatus);


            //checks the current status
            if (_status.equalsIgnoreCase("Active")) {
                GemTestReporter.addTestStep("Verify the status of user", "The current status is Active.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify the status of user", "The current status is Inactive.", STATUS.PASS);
            }

            //checks status after clicking the status button.
            DriverAction.click(MyLocators.statusButton, "Click on the status button.", "Successfully clicked the status button");
            if (_status.equalsIgnoreCase("Active")) {

                _updatedStatus = "Inactive";
            } else {
                _updatedStatus = "Active";
            }
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the status.");
        }
    }

    @Then("^Verify the updated status$")
    public void verifyTheUpdatedStatus() {
        try {
            //verify if the current status displays the expected tooltip on hovering
            String tooltip = DriverAction.getAttributeName(MyLocators.statusButton, "ng-reflect-text");
            if (_updatedStatus.equals("Inactive") && tooltip.equals("Click to Activate the user")) {
                GemTestReporter.addTestStep("Verify the updated status", "Updated status is Active.", STATUS.PASS, DriverAction.takeSnapShot());
            } else if (_updatedStatus.equals("Active") && tooltip.equals("Click to Inactivate the user")) {
                GemTestReporter.addTestStep("Verify the updated status", "Updated status is Inactive.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the updated status", "Could not update the status.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the updated status.");
        }
    }


    @Then("^Click the Yes button$")
    public void clickYesButton() {

        try {
            DriverAction.click(MyLocators.yesBtn, "Click the yes button", "Successfully clicked Yes button.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the yes button","Exception encountered- "+e,STATUS.ERR);
        }

    }

    @Then("^Verify the current role \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyTheCurrentRole(String role1, String role2, String role3) {
        try {
            //numRoles denotes no. of roles of a user registered by admin
            List<String> roles = DriverAction.getElementsText(MyLocators.currentRole);
            int numRoles = roles.size();
            //c denotes the no. of roles displayed when logged in using user's credentials.
            int c = 0;
            for (int i = 0; i < numRoles; i++) {
                if (roles.get(i).contains(role1) || roles.get(i).contains(role2) || roles.get(i).contains(role3)) {
                    c++;
                }
            }
            if (c == numRoles) {
                GemTestReporter.addTestStep("Verify the roles through user's id", "Successfully verified the roles through user's id.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the roles through user's id", "Could not verify the roles through user's id.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the current roles.");
        }
    }

    @And("^Expand Roles dropdown$")
    public void expandRolesDropdown() {

        try{
        DriverAction.click(MyLocators.rolesDropdown,"Expand roles dropdown","Roles dropdown expands displaying list of options.");
      }catch(Exception e){
            GemTestReporter.addTestStep("Expand roles dropdown","Exception encountered- "+e,STATUS.ERR);
        }

    }


    @Then("^Verify Candidate is logged in$")
    public void verifyCandidateIsLoggedIn() {
        try {
            DriverAction.waitSec(3);
            String page = DriverAction.getElementText(MyLocators.candidateDashboard);
            if (page.equalsIgnoreCase("Dashboard")) {
                GemTestReporter.addTestStep("Verify Candidate is logged in", "Candidate logged in successfully.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Candidate is logged in", "Candidate could not log in.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying whether candidate is logged in.");
        }

    }

    @Then("^Expand info dropdown from navbar$")
    public void expandInfoDropdown() {

        try {
            DriverAction.click(MyLocators.infoDropdown, "Click the dropdown icon on navbar", "Successfully clicked the dropdown icon.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand info dropdown from navbar","Exception encountered- "+e,STATUS.ERR);
        }

    }

    @And("^Verify the options present in dropdown \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyOptionsInDropdown(String option1,String option2, String option3) {

        try {
            //options on expanding info dropdown frm learner's portal.
            List<WebElement> options = DriverAction.getElements(MyLocators.infoOptions);

            //verifying options based on the number of options present.
            if(options.size()==1){
                if (options.get(0).getText().equals(option1)) {
                    GemTestReporter.addTestStep("Verify the options present in dropdown", "Successfully verified the options- " + option1, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify the options present in dropdown", "Could not verify the options- " + option1, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }else if(options.size()==2){
                if (options.get(0).getText().equals(option1) && options.get(1).getText().equals(option2)) {
                    GemTestReporter.addTestStep("Verify the options present in dropdown", "Successfully verified the options- " + option1 + "," + option2, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify the options present in dropdown", "Could not verify the options- " + option1 + "," + option2, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }else {
                if (options.get(0).getText().equals(option1) && options.get(1).getText().equals(option2) && options.get(2).getText().equals(option3)) {
                    GemTestReporter.addTestStep("Verify the options present in dropdown", "Successfully verified the options- " + option1 + "," + option2 + "," + option3, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify the options present in dropdown", "Could not verify the options- " + option1 + "," + option2 + "," + option3, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the options present in dropdown.");
        }
    }

    @Then("^Select change password from dropdown and verify the dialog box$")
    public void changePassword() {
        try {

            //check if dialog box appears on changing password.
            DriverAction.click(MyLocators.changePassword, "Select change password from dropdown", "Successfully selected change password.");
            boolean b = DriverAction.isExist(MyLocators.dialogBox);
            if (b) {
                GemTestReporter.addTestStep("Verify change password dialog box", "Successfully verified change password dialog box.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify change password dialog box", "Could not verify change password dialog box.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on selecting change password from dropdown.");
        }
    }

    @Then("Verify the email of candidate {string}")
    public void candidateEmail(String email) {
        try {
            String id = DriverAction.getAttributeName(MyLocators.email, "value");
            if (id.equals(email)) {
                GemTestReporter.addTestStep("Verify the email of candidate", "Successfully verified the email " + email, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify the email of candidate", "Could not verify the email " + email, STATUS.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the email of candidate.");
        }
    }

    @Then("^Verify the default tab selected \"([^\"]*)\"$")
    public void defaultTab(String tab) {
        try {
            //verify the tab selected when logged in by learner
            String currentTab = DriverAction.getElementText(MyLocators.tabSelected);
            if (currentTab.equals(tab)) {
                GemTestReporter.addTestStep("Verify the default tab selected", "Successfully verified the default tab- " + tab, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the default tab selected", "Could not verify the default tab- " + tab, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the default tab selected.");
        }
    }

    @And("^Verify the number of Tests \"([^\"]*)\", \"([^\"]*)\"$")
    public void numberOfTest(int testNumber,String tab) {
        try {
            //Tests denotes tests assigned by admin to a learner
            List<WebElement> Tests = DriverAction.getElements(By.xpath(MyLocators.numTest.replace("input", tab)));
            int count = Tests.size();
            if (count == 0) {
                //'No test found' is displayed in case test is not assigned.
                String message = DriverAction.getElementText(By.xpath(MyLocators.message.replace("input",tab)));
                if (message.equals("No Test Found")) {
                    GemTestReporter.addTestStep("Verify test is not found", "Successfully verified test not found message.", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify test is not found", "Could not verify test not found message.", STATUS.PASS, DriverAction.takeSnapShot());
                }
            } else if (count == testNumber) {
                GemTestReporter.addTestStep("Verify the number of " + tab, "Successfully verified the number as- " + testNumber, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the number of " + tab, "Could not verify the number as- " + testNumber, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the number of tests.");
        }
    }

    @Then("Switch to {string}")
    public void switchTo(String tab) {
        try {
            if(DriverAction.isExist(By.xpath(MyLocators.testTab.replace("input", tab)),5000));
            DriverAction.waitUntilElementClickable(By.xpath(MyLocators.testTab.replace("input", tab)),25000);
            DriverAction.click(By.xpath(MyLocators.testTab.replace("input", tab)), "Switch to " + tab, "Successfully switched to tab " + tab);
        }catch(Exception e){
            GemTestReporter.addTestStep("Switch test tab",e+" Exception occured while switching test tab.",STATUS.ERR);
        }
    }

    @Then("Select {string} from dropdown")
    public void selectFromDropdown(String option) {
        try{

                Thread.sleep(6000);
            if(DriverAction.isDisplayed(By.xpath(MyLocators.profile.replace("input",option)))) {
                DriverAction.click(By.xpath(MyLocators.profile.replace("input", option)), "Select " + option + " from dropdown", "Successfully selected " + option + " from dropdown.");
            }else if(DriverAction.isDisplayed(By.xpath(MyLocators.sectionOptions.replace("input", option)))){

                DriverAction.click(By.xpath(MyLocators.sectionOptions.replace("input", option)), "Select " + option + " from dropdown", "Successfully selected " + option + " from dropdown.");
            }else{
                GemTestReporter.addReasonOfFailure("Option not selected from dropdown.");
            }
            }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on selecting "+option+" from dropdown.");
        }
    }

    @Then("^Verify user navigates to dashboard$")
    public void dashboardScreen() {
        try {
            String page = DriverAction.getElementText(MyLocators.candidateDashboard);
            if (page.equalsIgnoreCase("Dashboard")) {
                GemTestReporter.addTestStep("Verify user is navigated to dashboard", "Successfully navigated to dashboard.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user is navigated to dashboard", "Could not navigate to dashboard.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while while navigating to dashboard.");
        }

    }

    @Then("Enter a password in confirm password field {string}, {string}")
    public void enterConfirmPassword(String newPassword, String fieldName) {
        try {
            DriverAction.typeText(MyLocators.confirmPassword,newPassword);
            String text = DriverAction.getAttributeName(MyLocators.confirmPassword,"value");
            if (text.equals(newPassword)) {
                GemTestReporter.addTestStep("Enter a different password in confirm password field", "Successfully entered a different password.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Enter a different password in confirm password field", "Could not enter a different password.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on entering a different password in confirm password field.");
        }
    }

    @Then("^Verify user not able to edit email$")
    public void verifyEditUsername() {
        try {
            String isEditable = "";
            isEditable = DriverAction.getAttributeName(MyLocators.email, "disabled");
            if (isEditable.equals("true")) {
                GemTestReporter.addTestStep("Verify user is not able to edit email", "Successfully verified the user not able to edit email.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user is not able to edit email", "Could not verify the user unable to edit email.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while editing the email.");
        }
    }

    @Then("^Get start and end date of test and verify current date is within range$")
    public void StartAndEndDate() throws ParseException {

        //fetch complete date text
        String date = DriverAction.getElementText(MyLocators.testDate);

        //segregate start and end dates
        String[] startDuration = date.split("-");
        String creationDate = startDuration[0];
        String[] startDate = creationDate.split(" ");
        String endDate = startDuration[1];
        String[] endingDate = endDate.split(" ");

        //set date format
        SimpleDateFormat sdformat = new SimpleDateFormat("MM dd yyyy");

        //get month number from month name
        //start month
        int monthNumber = getNumberFromMonthName(startDate[2], Locale.ENGLISH);
        //end month
        int monthNumber2 = getNumberFromMonthName(endingDate[2], Locale.ENGLISH);

        Date d1 = sdformat.parse(monthNumber + " " + startDate[3] + " " + startDate[4]);
        GemTestReporter.addTestStep("Get the test start date", "Start date is" + sdformat.format(d1), STATUS.PASS);

        Date d2 = sdformat.parse(monthNumber2 + " " + endingDate[3] + " " + endingDate[4]);
        GemTestReporter.addTestStep("Get the test end date", "End date is" + sdformat.format(d2), STATUS.PASS);


        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM dd yyyy");
        String str = formatter.format(current);
        Date curr=sdformat.parse(str);


        if(curr.compareTo(d1) < 0){
            GemTestReporter.addTestStep("Validate current date is within start and end date range","Its an upcoming test",STATUS.PASS,DriverAction.takeSnapShot());
        }
        else if (curr.compareTo(d1) >= 0 && curr.compareTo(d2) <= 0) {
            GemTestReporter.addTestStep("Validate current date is within start and end date range", "Successfully verified the current date is within active range.", STATUS.PASS,DriverAction.takeSnapShot());
        }else{
            GemTestReporter.addTestStep("Validate current date is within start and end date range", "Could not verify the current date is within active range.", STATUS.FAIL,DriverAction.takeSnapShot());

        }
    }

    public static int getNumberFromMonthName(String monthName, Locale locale) throws ParseException {

        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM")
                .withLocale(locale);
        TemporalAccessor temporalAccessor = dtFormatter.parse(monthName);
        return temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
    }


    @Then("^Start test and verify instructions video is displayed$")
    public void startTestVerifyInstructionsVideo() {
        try {
          //  DriverAction.click(By.xpath(MyLocators.testName.replace("name",test)));
            _test=DriverAction.getElementText(MyLocators.getTestName);
            DriverAction.click(MyLocators.startTest);
            GemTestReporter.addTestStep("Click the start test button of test", "Start test button is clicked successfully.", STATUS.PASS);


            if (DriverAction.isExist(MyLocators.instructionsVideo)) {
                GemTestReporter.addTestStep("Verify instructions video is displayed", "Successfully displayed the instructions video on clicking start test button.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify instructions video is displayed", "Instructions video is not displayed on clicking start test button.", STATUS.FAIL);
            }
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on verifying instructions video.");
        }

    }

    @And("^Wait while screen loads$")
    public void waitWhileScreenLoads() {

        DriverAction.waitUntilElementClickable(MyLocators.infoDropdown, 30);
      //  DriverAction.waitUntilElementClickable(MyLocators.loginPageLink,30);

    }


    @And("^Verify the report screen$")
    public void verifyTheReportScreen() {
        try {

            Thread.sleep(3000);

            String[] columns = {"Section", "Marks", "Questions", "Attempted", "Secured Marks"};

            //verify the header
            if (DriverAction.isExist(MyLocators.testSummary, 2)) {
                GemTestReporter.addTestStep("Verify candidate is navigated to report screen", "Successfully verified the report screen.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify candidate is navigated to report screen", "Could not verify the report screen.", STATUS.FAIL);
            }

            //verify table columns
            List<WebElement> header = DriverAction.getElements(MyLocators.reportColumns);
            int count = header.size();
            int k = 0;
            for (int i = 0; i < count; i++) {
                if (header.get(i).getText().equals(columns[i])) {
                    k++;
                }
            }
            if (count == k) {
                GemTestReporter.addTestStep("Verify the columns in test report", "Successfully verified the columns.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify the columns in test report", "Could not verify the columns.", STATUS.FAIL);
            }
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on verifying candidate report.");
        }
    }

    @Then("Enter a password {string} in new password field")
    public void enterNewPassword(String password) {

        try {
            DriverAction.typeText(MyLocators.passwordField, password);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter a new password", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Verify user is navigated to desired test$")
    public void verifyTest() {
        try {
            String url = DriverAction.getCurrentURL();
            if (url.contains(_test)) {
                GemTestReporter.addTestStep("Verify user is navigated to desired test- " + _test, "Successfully verified user navigated to test- " + _test, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user is navigated to desired test- " + _test, "Could not verify user navigated to test- " + _test, STATUS.FAIL, DriverAction.takeSnapShot());
            }
}catch (Exception e) {
    GemTestReporter.addTestStep("Enter a new password", "Exception encountered- " + e, STATUS.ERR);
}

    }

    @Then("^Verify user is navigated to desired test \"([^\"]*)\"$")
    public void verifyTest(String test) {
        try {
            String url = DriverAction.getCurrentURL();
            if (url.contains(test)) {
                GemTestReporter.addTestStep("Verify user is navigated to desired test- " + test, "Successfully verified user navigated to test- " + test, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user is navigated to desired test- " + test, "Could not verify user navigated to test- " + test, STATUS.FAIL, DriverAction.takeSnapShot());

            }
        }catch (Exception e) {
            GemTestReporter.addTestStep("Verify user navigated to desired test", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Click on the section ticket \"([^\"]*)\"$")
    public void clickSection(String section) {
        try {
            DriverAction.click(By.xpath(MyLocators.sections.replace("input", section)));
            GemTestReporter.addTestStep("Click on one of the sections- " + section, "Successfully clicked the section- " + section, STATUS.PASS);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Click the section ticket", "Exception encountered- " + e, STATUS.ERR);
        }
    }


    @And("^Verify there is no change in sections screen$")
    public void verifyNoChange() {
        try {
            if (DriverAction.isExist(MyLocators.sectionHeading)) {
                GemTestReporter.addTestStep("Verify there is no change in sections screen.", "Successfully verified no change in sections screen.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify there is no change in sections screen.", "User is able to navigate while clicking the sections ticket.", STATUS.PASS);
            }
        }catch (Exception e) {
            GemTestReporter.addTestStep("Verify there is no change in sections screen", "Exception encountered- " + e, STATUS.ERR);
        }

    }
    @Then("^Check the instructions checkbox$")
    public void instructionsCheckbox() {
        try {

            DriverAction.scrollToBottom();

            DriverAction.click(MyLocators.instructionsCheckbox);
            GemTestReporter.addTestStep("Check the instructions checkbox", "Successfully checked the instructions checkbox.", STATUS.PASS);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Check the instructions checkbox", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Verify dialog box appears$")
    public void verifyDialogBox() {
        try {
            if (DriverAction.isExist(MyLocators.startTestDialog)) {
                GemTestReporter.addTestStep("Verify confirmation dialog box appears", "Successfully verified the confirmations dialog box.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify confirmation dialog box appears", "Could not verify confirmation dialog box.", STATUS.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on verifying confirmation dialog box.");
        }
    }


    @And("^Verify user navigates to questions screen of the selected section \"([^\"]*)\"$")

    public void QuestionsScreen(String section) {
        try {
            if (DriverAction.isExist(MyLocators.questionsscreen)&&DriverAction.isExist(By.xpath(MyLocators.sectionSelected.replace("input",section)))){
                GemTestReporter.addTestStep("Verify user navigates to questions screen of the selected section", "Successfully verified user navigates to section screen.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify user navigates to questions screen of the selected section", "Could not verify user navigates to questions screen.", STATUS.FAIL);
            }
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on verification of questions screen.");
        }

    }

    @Then("^Expand the dropdown containing sections$")
    public void expandSections() {
        try {

            DriverAction.waitUntilElementIsClickable(MyLocators.sectionsDropdown);
            DriverAction.click(MyLocators.sectionsDropdown, "Click the sections dropdown", "Sections dropdown expands successfully.");

        }catch (Exception e) {
            GemTestReporter.addTestStep("Expand sections dropdown", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("Navigate to page {string}")

    public void pageNavigate(String page) {
        try {
            Thread.sleep(15000);
        //    DriverAction.waitUntilElementClickable(By.xpath(MyLocators.pageNavigate.replace("input", page)),120);

            DriverAction.click(By.xpath(MyLocators.pageNavigate.replace("input", page)), "Navigate to page " + page, "Successfully navigated to page " + page);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Navigate to page-"+page, "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("Click the {string} button of instructions video")
    public void nextButton(String button) {
        DriverAction.waitUntilElementClickable(By.xpath(MyLocators.nextBtn.replace("input",button)), 90);
        DriverAction.click(By.xpath(MyLocators.nextBtn.replace("input",button)),"Click the NEXT button displayed in video","Successfully clicked the NEXT button displayed in video.");
    }

    @Then("^Select or type an answer$")
    public void enterAnswer() {
        try {

            //close the video prompt if displays
            if(DriverAction.isDisplayed(MyLocators.videoPrompt)){
  //              if(!DriverManager.getWebDriver().findElement(MyLocators.videoPrompt).isDisplayed()){
                DriverAction.click(MyLocators.closeVideoPrompt,"Close the video prompt");
            }else{
                System.out.println("It is not a video based question.");
            }
            if (DriverAction.isDisplayed(MyLocators.textarea)) {
                String existingText=DriverAction.getElementText(MyLocators.textarea);
                if(!existingText.isEmpty()){
                    DriverAction.clearText(MyLocators.textarea);
                }
                DriverAction.waitSec(3);
                //word limit for the ques
//                String maxWord=DriverAction.getElementText(MyLocators.wordCounter);
//                System.out.println(maxWord);
//                String[]maxWordNumber=maxWord.split(" ");
//                maxWord=maxWordNumber[3];
//                int wordCount=Integer.parseInt(maxWord);
//
//                DriverAction.typeText(MyLocators.textarea, "abc");
//                GemTestReporter.addTestStep("Enter answer in input field", "Successfully entered the answer in input field", STATUS.PASS);
//                //word limit after entering a word
//                String remainingWords=DriverAction.getElementText(MyLocators.wordCounter);
//                System.out.println(remainingWords);
//                String[]remainingWordNumber=remainingWords.split(" ");
//                remainingWords=remainingWordNumber[3];
//                int remainingWordCount=Integer.parseInt(remainingWords);
//                if(remainingWordCount==wordCount-1){
//                    GemTestReporter.addTestStep("Validate the word count for subjective questions","Word count reduced by 1.",STATUS.PASS,DriverAction.takeSnapShot());
//                }else{
//                    GemTestReporter.addTestStep("Validate the word count for subjective questions","Could not validate the word count.",STATUS.FAIL,DriverAction.takeSnapShot());
//                }
            } else if (DriverAction.isDisplayed(MyLocators.selectOption)||DriverAction.isDisplayed(MyLocators.mcqOptions)) {

                DriverAction.click(MyLocators.selectOption, "Select an option");
                GemTestReporter.addTestStep("Select an answer", "Successfully selected an answer.", STATUS.PASS);
            } else {
                DriverAction.click(MyLocators.selectCheckbox, "Select an option");
                GemTestReporter.addTestStep("Select an answer", "Successfully selected the answer", STATUS.PASS);
            }
        }catch (Exception e) {
            GemTestReporter.addTestStep("Enter answers", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Verify user is able to save answers \"([^\"]*)\"$")
    public void verifyAnswers(String questionStatus) {
        try {
            DriverAction.waitSec(4);
            String status = DriverAction.getAttributeName(MyLocators.paletteBtn, "class");

            DriverAction.waitSec(3);


            //if answer selected is saved
            if (status.contains(questionStatus)) {
                GemTestReporter.addTestStep("Verify user is able to save answers", "Successfully verified answer is saved.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify user is able to save answers", "Could not save answer.", STATUS.FAIL);
            }
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while saving answer.");
        }

    }

    @Then("^Verify the answer got cleared$")
    public void clearAnswer() {
        try {
            List<WebElement>options=new ArrayList<>();

            int c=0;

            //MCQ type
            if(DriverAction.isDisplayed(MyLocators.mcqOptions)) {
                options = DriverAction.getElements(MyLocators.mcqOptions);
                c++;
            }

            //checkboxes type
            else if(DriverAction.isDisplayed(MyLocators.checkbox)){
                options = DriverAction.getElements(MyLocators.checkbox);
                c++;
            }

            //subjective type
            else if(DriverAction.isDisplayed(MyLocators.textarea)){

                if(DriverAction.getAttributeName(MyLocators.textarea,"ng-reflect-model").isEmpty()){
                    GemTestReporter.addTestStep("Verify subjective answer got cleared","Successfully verified the subjective answer got cleared.",STATUS.PASS);
                }else{
                    GemTestReporter.addTestStep("Verify subjective answer got cleared","Could not verify the subjective answer got cleared.",STATUS.FAIL);
                }

           //     System.exit(1);
            }

            //verifying none of the options is selected
            if(c==1) {
                int numOptions = 0;
                for (int i = 0; i < options.size(); i++) {
                    if (DriverAction.getAttributeName(options.get(i), "aria-checked").equals("true")) {
                        GemTestReporter.addTestStep("Verify the answer got cleared", "Could not verify the answer got cleared.", STATUS.FAIL);
                        break;
                    } else {
                        numOptions++;
                    }
                }
                if (numOptions == options.size()) {
                    GemTestReporter.addTestStep("Verify the answer got cleared", "Successfully verified the answer got cleared.", STATUS.PASS);
                }else{
                    GemTestReporter.addTestStep("Verify the answer got cleared", "Could not verify the answer got cleared.", STATUS.FAIL);
                }
            }


        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the answer got cleared.");
        }
    }

    @Then("^Select or type all the questions of a section and save$")
    public void attemptAllQues() throws InterruptedException {
        try {


            int totalQues = DriverAction.getElements(MyLocators.paletteBtn).size();
            for (int i = 0; i < totalQues; i++) {
                enterAnswer();
                clickTheButtonSaveNext();
//                clickTheButtonSaveNext();
                _quesCount++;
            }
            Thread.sleep(4000);

            clickTheButton("Finish Test");
        }
    catch (Exception e) {
        GemTestReporter.addTestStep("Enter answers and save", "Exception encountered- " + e, STATUS.ERR);
    }
    }

    @And("^Verify all the answers got saved$")
    public void verifySavedAns() {
        try {
            int totalQues = DriverAction.getElements(MyLocators.paletteBtn).size();
            for (int i = 0; i < totalQues; i++) {
                verifyAnswers("submitted");
            }
        }catch (Exception e) {
            GemTestReporter.addTestStep("Verify saved answers", "Exception encountered- " + e, STATUS.ERR);
        }
    }


    @Then("^Validate questions count$")
    public void validateQuestionCount() {
try {
    String count=Integer.toString(_quesCount);
    String totalQues = DriverAction.getElementText(MyLocators.totalQuesCount);
    String attemptedQues = DriverAction.getElementText(MyLocators.attemptedQuesCount);
    String unattemptedQues = DriverAction.getElementText(MyLocators.unattemptedQuesCount);
    if (totalQues.equals(count) && attemptedQues.equals(count) && unattemptedQues.equals("0")) {
        GemTestReporter.addTestStep("Validate questions count", "Successfully validated total questions- " + totalQues + ", attempted questions- " + attemptedQues + ", unattempted questions- " + unattemptedQues + ".", STATUS.PASS, DriverAction.takeSnapShot());

    } else {
        GemTestReporter.addTestStep("Validate questions count", "Could not validate questions count.", STATUS.FAIL, DriverAction.takeSnapShot());
    }
}catch (Exception e) {
    GemTestReporter.addTestStep("Validate questions count", "Exception encountered- " + e, STATUS.ERR);
}
    }

    @Then("^Select or type all the questions of entire test$")
    public void attemptCompleteTest() {
        try {

            expandSections();
            List<WebElement>sections=DriverAction.getElements(MyLocators.totalSections);
            int numOptions=sections.size();
            for(int k=0;k<numOptions;k++) {
                if(k!=0) {
                    expandSections();
                }
                DriverAction.waitSec(2);
                DriverAction.click(sections.get(k));

                System.out.println(k);
                int totalQues = DriverAction.getElements(MyLocators.paletteBtn).size();
                for (int i = 0; i < totalQues; i++) {
                    enterAnswer();
//                    if(k==0&&i==totalQues-1){
//                        break;
//                    }
                    clickTheButtonSaveNext();

                }
                sections=DriverAction.getElements(MyLocators.totalSections);
            }
        } catch (Exception e) {
            GemTestReporter.addReasonOfFailure(e+" Exception occured while attempting complete test");
        }
    }

    @Then("^Click the left arrow button$")
    public void leftArrowButton() {
        try {

            DriverAction.scrollToBottom();
            DriverAction.waitUntilElementIsClickable(MyLocators.leftArrowBtn);

            DriverAction.click(MyLocators.leftArrowBtn, "Click left arrow button", "Successfully clicked the left arrow button");
        }catch (Exception e) {
            GemTestReporter.addTestStep("Click the left arrow button", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Refresh the page and \"([^\"]*)\" alert$")
    public void refreshPageCancelAlert(String alertAction) {
        try {
            Robot robot = new Robot();

            // press key Ctrl+Shift+r
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_R);

            // release key Ctrl+Shift+r
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            GemTestReporter.addTestStep("Refresh the page","Successfully refreshed the page.",STATUS.PASS,DriverAction.takeSnapShot());

            //perform action on alert accordingly
            if(alertAction.equals("cancel")) {
                DriverAction.dismissAlert();
            }else{
                DriverAction.acceptAlert();
            }
        }
        catch(UnhandledAlertException e){
            System.out.println("UnhandledAlertException: " + e.getMessage());
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }


    @Then("^Verify user is on the same page \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifySamePage(String section, String questionNum) {
        try {
            String sectionName = DriverAction.getElementText(MyLocators.sectionName);
            String ques = DriverAction.getElementText(MyLocators.questionNum);
            String[] arr = ques.split(" ");
            if (section.equals(sectionName) && questionNum.equals(arr[1])) {
                GemTestReporter.addTestStep("Verify user is on the same page", "Successfully verified the user on same page", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user is on the same page", "Could not verify the user on same page", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch (Exception e) {
            GemTestReporter.addTestStep("Verify user is on same page", "Exception encountered- " + e, STATUS.ERR);
        }

    }

    @Then("^Verify user navigates to instructions screen$")
    public void instructionsScreen() {
        try {
            if (DriverAction.isExist(MyLocators.instructionsCheckbox)) {
                GemTestReporter.addTestStep("Verify user navigates to instructions screen", "Successfully verifies user navigates to instructions screen.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user navigates to instructions screen", "Could not verify user navigates to instructions screen.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep(e+" Exception encountered on verifying instructions screen.","",STATUS.ERR);
        }
    }

    @Then("^Validate the functionality of right arrow key \"([^\"]*)\"$")
    public void rightArrowKey(int totalQues) {
        try {
            int c = 0;

            DriverAction.scrollToBottom();
            DriverAction.waitUntilElementIsClickable(MyLocators.rightKey);
            String value = DriverAction.getElement(MyLocators.rightKey).getAttribute("disabled");
            while (value==null) {
                DriverAction.scrollToBottom();
                DriverAction.click(MyLocators.rightKey,"Clicked right arrow key");
                c++;
                value = DriverAction.getElement(MyLocators.rightKey).getAttribute("disabled");
                System.out.println(value);

            }
            if (c == totalQues - 1) {
                GemTestReporter.addTestStep("Validate the functionality of right arrow key", "Successfully verified the functionality of right arrow key.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {

                GemTestReporter.addTestStep("Validate the functionality of right arrow key", "Total questions in test do not match the question count passed from examples.", STATUS.FAIL, DriverAction.takeSnapShot());

            }
        }catch (Exception e) {
            GemTestReporter.addTestStep("Validate the functionality of right arrow key", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Verify key disables on last question$")
    public void verifyKeyDisables() {
        try {

            String value=DriverAction.getElement(MyLocators.rightKey).getAttribute("disabled");
            if (value!=null) {

                GemTestReporter.addTestStep("Verify key disables on last question", "Successfully verified disabled key on last question.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify key disables on last question", "Could not verify disabled key on last question.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch (Exception e) {
            GemTestReporter.addTestStep("Verify key disabled on last question", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("Click the {string} button after finishing test")

    public static void clickButton(String button) {

        try {
            DriverAction.click(By.xpath(MyLocators.testSubmitButton.replace("input", button)));
        }catch (Exception e) {
            GemTestReporter.addTestStep("Click "+button+" after finishing test", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Verify user navigates to test summary screen$")
    public void testSummaryScreen() {
        try {
            if (DriverAction.isExist(MyLocators.testSummary, 2)) {
                GemTestReporter.addTestStep("Verify candidate is navigates to test summary screen", "Successfully verified the test summary screen.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify candidate is navigates to test summary screen", "Could not verify the test summary screen.", STATUS.FAIL);
            }
        }catch (Exception e) {
            GemTestReporter.addTestStep("Verify user navigates to test summary screen", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Select Back To Dashboard$")
    public void backToDashboard() {
        try {
            DriverAction.click(MyLocators.backToDashboard);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Click back to dashboard", "Exception encountered- " + e, STATUS.ERR);
        }
    }



    @Then("Enter a password {string} in old password fields")
    public void enterOldPassword(String password) {
        try {
            DriverAction.typeText(MyLocators.oldPasswordField, password);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Enter a password in old password fields", "Exception encountered- " + e, STATUS.ERR);
        }
    }



    @Given("^Expand the login via dropdown$")
    public void expandLoginViaDropdown() {
        try{
            Thread.sleep(25000);
            DriverAction.click(MyLocators.loginVia);
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand the login via dropdown","Exception encountered- " + e, STATUS.ERR);
        }
    }


    @And("^Click on View Report$")
    public void clickOnViewReport() {
        try {
            DriverAction.click(MyLocators.viewReport);
        }catch(Exception e){
            GemTestReporter.addTestStep("Click view report","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Click the button Save & Next$")
    public void clickTheButtonSaveNext() {
        try {
         //   DriverAction.waitSec(4);
            DriverAction.waitUntilElementIsClickable(MyLocators.saveNext);
            DriverAction.click(MyLocators.saveNext,"Click Save & Next button");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Save & Next","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Refresh the page, cancel alert and verify user is on same page$")
    public void refreshThePageCancelAlertAndVerifyUserIsOnSamePage() throws InterruptedException {
        try {
            Robot robot = new Robot();

            // press key Ctrl+Shift+r
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_R);

            // release key Ctrl+Shift+r
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
                DriverAction.dismissAlert();

                //user is on same page if invigilation timer appears
                if(DriverAction.isExist(MyLocators.invigilationAlert)){

                GemTestReporter.addTestStep("Verify user is on the same page", "Successfully verified the user on same page", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user is on the same page", "Could not verify the user on same page", STATUS.FAIL, DriverAction.takeSnapShot());
            }

        }
        catch(UnhandledAlertException e){
            System.out.println("UnhandledAlertException: " + e.getMessage());
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @And("^Refresh the page, accept alert and verify user navigates to instructions screen$")
    public void refreshThePageAcceptAlertAndVerifyUserNavigatesToInstructionsScreen() throws InterruptedException {
        try {
            Robot robot = new Robot();

            // press key Ctrl+Shift+r
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_R);

            // release key Ctrl+Shift+r
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);

       //     GemTestReporter.addTestStep("Refresh the page","Successfully refreshed the page.",STATUS.PASS,DriverAction.takeSnapShot());
            DriverAction.acceptAlert();

            Thread.sleep(12000);
            if (DriverAction.isExist(MyLocators.instructionsCheckbox)) {
                GemTestReporter.addTestStep("Verify user navigates to instructions screen", "Successfully verifies user navigates to instructions screen.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify user navigates to instructions screen", "Could not verify user navigates to instructions screen.", STATUS.FAIL, DriverAction.takeSnapShot());
            }

        }
        catch(UnhandledAlertException e){
            System.out.println("UnhandledAlertException: " + e.getMessage());
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @And("Expand the dropdown {string}")
    public void expandTheDropdown(String dropdownName) {
        try {
            DriverAction.click(By.xpath(MyLocators.dropdown.replace("input", dropdownName)),"Expand the dropdown- "+dropdownName);
        }catch (Exception e){
            GemTestReporter.addTestStep("Expand the dropdown- "+dropdownName,"Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Validate question count$")
    public void validateCount() {
        try {
            //validating total question attempted by candidate
            String totalQues = DriverAction.getElementText(MyLocators.totalQuesCount);
            String attemptedQues = DriverAction.getElementText(MyLocators.attemptedQuesCount);
            if (!attemptedQues.equals("0") && totalQues.equals(attemptedQues)) {
                GemTestReporter.addTestStep("Validate question count", "Successfully validated the questions count", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validate question count", "Could not validate the questions count", STATUS.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate question count","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify user is able to save answers$")
    public void ableToSaveAnswers() throws InterruptedException {
        try {
            Thread.sleep(3000);
            String attemptedQues = DriverAction.getElementText(MyLocators.attemptedQuesCount);
            if (attemptedQues.equals("1")) {
                GemTestReporter.addTestStep("Verify user is able to save answers", "Successfully verified user is able to save answers.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify user is able to save answers", "Could not verify user is able to save answers.", STATUS.FAIL);
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify user is able to save answers","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify test is present in completed tests tab$")
    public void verifyTestIsPresentInCompletedTests() {
        try {
            List<WebElement> completedTests = DriverAction.getElements(MyLocators.allCompletedTests);
            int c=0;
            for (WebElement i : completedTests) {
                if (i.getText().contains(_test)) {
                    c++;
                }
            }
            if(c==1){
                GemTestReporter.addTestStep("Verify test is present in completed tests tab","Successfully verified the test in completed tests tab.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify test is present in completed tests tab","Could not verify the test in completed tests tab.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify test is present in completed tests","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Navigate to login page$")
    public void navigateToLoginPage() {
        try{
            DriverAction.waitUntilElementClickable(MyLocators.loginPageLink,130);
            DriverAction.click(MyLocators.loginPageLink, "Navigate to login page");
        }catch(Exception e){
            GemTestReporter.addTestStep("Navigate to login page","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Given("^Open the sidebar$")
    public void openTheSidebar() {
        try{
            //open sidebar
            DriverAction.click(MyLocators.sidebar, "Expand the sidebar", "Sidebar expands displaying list of modules.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Open the sidebar","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Validate the modules present$")
    public void validateTheModulesPresent() {
        try {
            String[] modules = new String[]{"User Dashboard", "Test Analytics", "Tests", "Manage Courses", "Mail Configurations", "Reports", "User Logs", "Manage Sections", "Campus", "User Management", "Role Management"};
            String getModule;
            int numModule = 0;
            List<WebElement> moduleName = DriverAction.getElements(MyLocators.modules);
            for (int i = 0; i < modules.length; i++) {
                getModule = moduleName.get(i).getText();
                if (getModule.equals(modules[i])) {
                    numModule++;
                }
            }
            if (numModule == 11) {
                GemTestReporter.addTestStep("Validate the modules present", "Successfully validate the modules.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate the modules present", "Could not validate the modules.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate the modules present","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Validate the submodules present$")
    public void validateTheSubmodulesPresent() {
        try{
            String[]submodules=new String[]{"Test Control","Questions","Course Library","Content Library","Assignment Library","Batches","Template Library","Send Custom Mail","Campus Performance"};
            List<WebElement>submoduleName=DriverAction.getElements(MyLocators.submodules);
            int numSubmodules=0;
            String getSubmodule;

            //expanding the modules containing submodules
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", "Tests")));
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", "Manage Courses")));
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", "Mail Configurations")));
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", "Reports")));
            for(int i=0;i<submodules.length;i++){
                getSubmodule=submoduleName.get(i).getText();
                System.out.println(getSubmodule);
                if(getSubmodule.equals(submodules[i])){
                    numSubmodules++;
                }
            }
            if(numSubmodules==9){
                GemTestReporter.addTestStep("Validate the submodules present","Successfully validated the submodules.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate the submodules present","Could not validate the submodules.",STATUS.FAIL,DriverAction.takeSnapShot());
            }

        }catch(Exception e){
            GemTestReporter.addTestStep("Validate the submodules present","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify the tabs present in candidate test summary$")
    public void verifyTabsInCandidateSummary() {
        try{
            String[]tabs={"Active Tests","Attempted Tests"};
            List<WebElement>getTabs=DriverAction.getElements(MyLocators.testSummaryTabs);
            int c=0;
            for(int i=0;i<getTabs.size();i++){
                if(getTabs.get(i).getText().equals(tabs[i])){
                    c++;
                }
            }
            if(c==2){
                GemTestReporter.addTestStep("Verify the tabs present in candidate test summary","Successfully verified the tabs present in candidate test summary",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify the tabs present in candidate test summary","Could not verify the tabs present in candidate test summary",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the tabs present in candidate test summary","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify the tabs present in learner's summary$")
    public void verifyTabsInLearnerSummary() {
        try{
            DriverAction.waitSec(3);
            String[]tabs={"Active Tests","Attempted Tests","Ongoing Courses","Completed Courses","Expired Courses"};
             List<WebElement>getTabs=DriverAction.getElements(MyLocators.testSummaryTabs);
             int c=0;
             for(int i=0;i<getTabs.size();i++){
                 if(getTabs.get(i).getText().equals(tabs[i]))
                 c++;
             }
             if(c==5){
                 GemTestReporter.addTestStep("Verify the tabs present in learner's summary","Successfully verified the tabs present in learner's summary.",STATUS.PASS,DriverAction.takeSnapShot());
             }else{
                 GemTestReporter.addTestStep("Verify the tabs present in learner's summary","Could not verify the tabs present in learner's summary",STATUS.FAIL,DriverAction.takeSnapShot());
             }
        }catch(Exception e){
             GemTestReporter.addTestStep("Verify the tabs present in learner's summary","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }

    }


    @And("^Select actions icon of first learner displayed$")
    public void firstLearnerActionIcon() {
        try{
            DriverAction.waitSec(3);
            DriverAction.click(MyLocators.learnerActionsIcon,"Select actions icon of first learner displayed","Successfully selected the actions icon of first learner.");
        }  catch(Exception e){
             GemTestReporter.addTestStep("Select actions icon of first learner displayed","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }

    }

    @And("^Click View Report in attempted tests$")
    public void clickViewReportInAttemptedTests() {
        try{
            DriverAction.click(MyLocators.viewReportInAttemptedTest,"Click View Report in attempted tests","Successfully clicked view report in attempted tests.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click View Report in attempted tests","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }
}