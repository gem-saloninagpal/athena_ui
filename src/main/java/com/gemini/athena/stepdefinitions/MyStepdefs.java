package com.gemini.athena.stepdefinitions;

import com.gemini.athena.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.Driver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.gemini.athena.locators.MyLocators.selectTab;

public class MyStepdefs {
    String randomString = "";
    String updatedStatus="";
    String status="";


    @Given("^Login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void login(String username, String password) {

        try {
           //
              DriverAction.waitUntilElementAppear(MyLocators.athenaLogo,40);
              DriverAction.waitSec(10);
            //Thread.sleep(9000);
            System.out.println(randomString);
            //if new role is registered through the email, login using same email
            if(randomString.isEmpty()){
                DriverAction.typeText(MyLocators.usernameField, username);
                //else login using email passed from example
            }else {
                DriverAction.typeText(MyLocators.usernameField, randomString);
            }
            DriverAction.typeText(MyLocators.passwordField, password);
            DriverAction.waitSec(2);
            DriverAction.click(MyLocators.loginBtn);
            DriverAction.waitUntilElementAppear(MyLocators.dashboard, 4000);
            //verify dashboard is displayed on login
            if (DriverAction.isExist(MyLocators.dashboard)) {
                GemTestReporter.addTestStep("Verify dashboard is displayed", "Successfully displayed the dashboard.", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            System.out.print("Could not login to Athena.");
        }
    }

    @Given("^Select \"([^\"]*)\" from sidebar$")
    public void selectFromSidebar(String module) {
        try {
            Thread.sleep(3000);
            //select a module from sidebar
            DriverAction.click(MyLocators.sidebar, "Expand the sidebar", "Sidebar expands displaying list of modules.");
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", module)));
            DriverAction.click(MyLocators.crossIcon, "Click the cross icon of sidebar", "Successfully clicked the cross icon.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Select module from sidebar", "Throws exception", STATUS.ERR, DriverAction.takeSnapShot());
        }
    }


    @Then("^Click the button \"([^\"]*)\"$")
    public void clickTheButton(String buttonName) throws InterruptedException {
    //    Thread.sleep(2000);
        try {
            DriverAction.click(By.xpath(MyLocators.button.replace("input", buttonName)));
        }catch(Exception e){
            System.out.print("Exception encountered!");
        }

    }

    @And("^Verify user is navigated to page \"([^\"]*)\"$")
    public void verifyUserNavigated(String pageTitle) {
        try {
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
            for (int i = 0; i < fields.size(); i++) {
                if (fields.get(i).getText().contains(error)) {
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
         //   Thread.sleep(2000);
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
                if (placeholder.equalsIgnoreCase("email")) {
                    inputFields.get(i).sendKeys(randomString);
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
        DriverAction.click(By.xpath(MyLocators.selectTab(i)));
    }


    @Then("^Verify \"([^\"]*)\" is registered \"([^\"]*)\",\"([^\"]*)\"$")
    public void verifyIsRegistered(String role, String name, String lname) {
        try {
            //verify the registered user
            String firstName = DriverAction.getElementText(MyLocators.learnerFirstName);
            String lastName = DriverAction.getElementText(By.xpath("(//tbody)[3]//td[2]"));
            if (firstName.contains(name) && lastName.contains(lname)) {
                GemTestReporter.addTestStep("Verify learner is registered", "Successfully verified the registered learner " + name, STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            System.out.println("Exception encountered while verifying registered user.");
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
            System.out.println("Exception encountered.");
        }
    }

    @Then("^Generate unique email$")
    public void generateUniqueEmail() {
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

            randomString = sb.toString();
            randomString = randomString.concat("@gmail.com");
            System.out.println("Random String is: " + randomString);
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while generating unique email.");
        }
    }

    @Then("Enter password {string} and verify the required format {string}")
    public void enterPassword(String password, String format) {
        try {
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
            for (int i = 0; i < numRoles; i++) {
                if (roles[i].equals(role1) || roles[i].equals(role2) || roles[i].equals(role3)) {
                    c++;
                }
            }
            if (c == numRoles) {
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
        DriverAction.click(MyLocators.navbarRolesDropdown,"Click roles dropdown icon of navigation bar","Roles dropdown is clicked successfully.");
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
        DriverAction.click(MyLocators.actionsIcon,"Click the actions icon","Successfully clicked the Actions icon.");

    }

    @Then("^Select Actions icon of first record displayed in Employees$")
    public void selectActionsIconEmployees() {
        DriverAction.click(MyLocators.employeeActionsIcon,"Click the actions icon","Successfully clicked the Actions icon.");

    }

    @And("^Select Edit Profile option$")
    public void editProfile() {
        DriverAction.click(MyLocators.editProfile,"Select Edit Profile option from dropdown","Successfully selected the Edit Profile option.");

    }

    @Then("^Enable editing$")
    public void enableEditing() {
        DriverAction.click(MyLocators.enableEditing,"Enable editing of Registered user","Successfully clicked Enable Editing option.");

    }

    @And("^Verify user not able to edit role$")
    public void verifyEditRole() {
        try {
            String isEditable = "";
            isEditable = DriverAction.getAttributeName(MyLocators.rolesField, "readonly");
            if (isEditable.equals("readonly")) {
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
        try{
        status=DriverAction.getElementText(MyLocators.userStatus);
        //checks the current status
       if(status.equalsIgnoreCase("Active")){
           GemTestReporter.addTestStep("Verify the status of user","The current status is Active.",STATUS.PASS);
       }else{
           GemTestReporter.addTestStep("Verify the status of user","The current status is Inactive.",STATUS.PASS);
       }
       //checks status after clicking the status button.
       DriverAction.click(MyLocators.statusButton,"Click on the status button.","Successfully clicked the status button");
       if(status.equalsIgnoreCase("Active")){
           updatedStatus="Inactive";
       }else{
           updatedStatus="Active";
       }}
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the status.");
        }
    }

    @Then("^Verify the updated status$")
    public void verifyTheUpdatedStatus() {
        try {
            //verify if the current status displays the expected tooltip on hovering
            String tooltip = DriverAction.getAttributeName(MyLocators.statusButton, "ng-reflect-text");
            if (updatedStatus.equals("Inactive") && tooltip.equals("Click to Activate the user")) {
                GemTestReporter.addTestStep("Verify the updated status", "Updated status is Active.", STATUS.PASS, DriverAction.takeSnapShot());
            } else if (updatedStatus.equals("Active") && tooltip.equals("Click to Inactivate the user")) {
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
        DriverAction.click(MyLocators.yesBtn,"Click the yes button","Successfully clicked Yes button.");

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
        DriverAction.click(MyLocators.rolesDropdown,"Expand roles dropdown","Roles dropdown expands displaying list of options.");
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
        DriverAction.click(MyLocators.infoDropdown,"Click the dropdown icon on navbar","Successfully clicked the dropdown icon.");
    }

    @And("^Verify the options present in dropdown \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyOptionsInDropdown(String option1,String option2, String option3) {

        try {
            //options on expanding info dropdown frm learner's portal.
            List<WebElement> options = DriverAction.getElements(MyLocators.infoOptions);
            if (options.get(0).getText().equals(option1) && options.get(1).getText().equals(option2) && options.get(2).getText().equals(option3)) {
                GemTestReporter.addTestStep("Verify the options present in dropdown", "Successfully verified the options- " + option1 + "," + option2 + "," + option3, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the options present in dropdown", "Could not verify the options- " + option1 + "," + option2 + "," + option3, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
        catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the options present in dropdown.");
        }
    }

    @Then("^Select change password from dropdown and verify the dialog box$")
    public void changePassword() {
        try {
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
            DriverAction.click(By.xpath(MyLocators.testTab.replace("input", tab)), "Switch to " + tab, "Successfully switched to tab " + tab);
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while switching test tab.");
        }
    }

    @Then("Select {string} from dropdown")
    public void selectFromDropdown(String option) {
        try{
            DriverAction.click(By.xpath(MyLocators.profile.replace("input",option)),"Select "+option+" from dropdown","Successfully selected "+option+" from dropdown.");
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

    @Then("Enter a different password in confirm password field {string}, {string}")
    public void enterDifferentPassword(String newPassword, String fieldName) {
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

        String date=DriverAction.getElementText(MyLocators.testDate);
        String[] startDuration =date.split("-");
        String creationDate=startDuration[0];
        String[] startDate=creationDate.split(" ");
        String endDate=startDuration[1];
        String[] endingDate=endDate.split(" ");
        SimpleDateFormat sdformat = new SimpleDateFormat("MM dd yyyy");
        //get month number from month string
        //start month
        int monthNumber=getNumberFromMonthName(startDate[2],Locale.ENGLISH);
        //end month
        int monthNumber2=getNumberFromMonthName(endingDate[1],Locale.ENGLISH);
        Date d1 = sdformat.parse(monthNumber+" "+startDate[3]+" "+startDate[4]);
        Date d2 = sdformat.parse(monthNumber2+" "+endingDate[2]+" "+endingDate[3]);
        System.out.println("The date 1 is: " + sdformat.format(d1));
        System.out.println("The date 2 is: " + sdformat.format(d2));
        if(d1.compareTo(d2) > 0) {
            System.out.println("Date 1 occurs after Date 2");
        } else if(d1.compareTo(d2) < 0) {
            System.out.println("Date 1 occurs before Date 2");
        } else if(d1.compareTo(d2) == 0) {
            System.out.println("Both dates are equal");
        }


    }

    public static int getNumberFromMonthName(String monthName, Locale locale) throws ParseException {

        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM")
                .withLocale(locale);
        TemporalAccessor temporalAccessor = dtFormatter.parse(monthName);
        return temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
    }
    }

