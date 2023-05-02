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
import java.util.List;
import java.util.Random;

import static com.gemini.athena.locators.MyLocators.selectTab;

public class MyStepdefs {
    String randomString = "";
    String updatedStatus="";
    String status="";

    @Given("^Login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginUsingUsernameAndPassword(String username, String password) {

        try {
            //  DriverAction.waitUntilElementAppear(MyLocators.athenaLogo,6000);
            Thread.sleep(7000);
            System.out.println(randomString);
            if(randomString.isEmpty()){
                DriverAction.typeText(MyLocators.usernameField, username);
            }else {
                DriverAction.typeText(MyLocators.usernameField, randomString);
            }
            DriverAction.typeText(MyLocators.passwordField, password);
            DriverAction.click(MyLocators.loginBtn);
            DriverAction.waitUntilElementAppear(MyLocators.dashboard, 4000);
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
    public void verifyUserIsNavigatedToPage(String pageTitle) {
        try {
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

    @And("^Verify the error displayed in input fields \"([^\"]*)\"$")
    public void verifyTheErrorDisplayedInInputFields(String error) {
        try {
            List<WebElement> fields = DriverAction.getElements(MyLocators.fieldsError);
            int c = 0;
            for (int i = 0; i < fields.size(); i++) {
                if (fields.get(i).getText().contains(error)) {
                    c++;
                }
            }
            if (c == 7) {
                GemTestReporter.addTestStep("Verify error message in input fields", "Error message is containing " + error + " is displayed.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {

                GemTestReporter.addTestStep("Verify error message in input fields", "Error message is not displayed in empty input field.", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify error displayed in input fields", "Exception encountered " + e, STATUS.ERR);
        }
    }

    @Then("^Select a role from dropdown \"([^\"]*)\"$")
    public void selectARoleFromDropdown(String role) throws InterruptedException {
        DriverAction.click(MyLocators.dropdownIcon, "Click the dropdown icon", "List of options displays.");
        DriverAction.click(By.xpath(MyLocators.option.replace("input", role)));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DriverAction.click(MyLocators.crossIcon);
        Thread.sleep(3000);
    }

    @Then("^Enter respective values in input fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterRespectiveValuesInInputFields(String password, String confirmPassword, String firstName, String lastName, String email, String number, String experience) {
        try {
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
    public void enterPasswordAndVerifyTheRequiredFormat(String password, String format) {
        try {
            DriverAction.typeText(MyLocators.passwordField, password);
            String error = DriverAction.getElementText(MyLocators.passwordFormatError);
            if (error.equalsIgnoreCase(format)) {
                GemTestReporter.addTestStep("Verify password format", "Successfully verified the password format.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify password format", "Could not verify the password format.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify password format", "Exception encountered-" + e, STATUS.ERR, DriverAction.takeSnapShot());
        }
    }

    @Then("^Select campus from select campus dropdown$")
    public void selectCampusFromSelectCampusDropdown() {
        try {
            DriverAction.click(MyLocators.selectCampusDropdown, "Click on Select Campus dropdown", "Select Campus dropdown is clicked successfully.");
            DriverAction.click(MyLocators.selectCampus, "Select a campus from dropdown", "Campus selected successfully.");
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while selecting campus.");
        }
    }

    @Then("Select experience level from dropdown {string}")
    public void selectExperienceLevelFromDropdown(String level) {
        try {
            DriverAction.click(MyLocators.selectExperienceLevelDropdown, "Click on Experience Level dropdown", "Experience Level dropdown is clicked successfully.");
            DriverAction.click(By.xpath(MyLocators.selectLevel.replace("input", level)), "Select experience from dropdown", "Experience selected successfully.");
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured while selecting experience level.");
        }
    }

    @Then("^Verify the role of registered user \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void verifyTheRoleOfRegisteredUser(String role1, String role2, String role3) {
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
    public void logoutOfPortal() {
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
    public void selectRolesDropdownIconOfNavigationBar() {
        DriverAction.click(MyLocators.navbarRolesDropdown,"Click roles dropdown icon of navigation bar","Roles dropdown is clicked successfully.");
    }

    @Then("Verify the roles through user's id {string}, {string}, {string}")
    public void verifyTheRoles(String role1, String role2, String role3) {
        try {
            List<String> roles = DriverAction.getElementsText(MyLocators.rolesOption);
            int numRoles = roles.size();
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
            GemTestReporter.addReasonOfFailure(e+" Exception occured while verifying the roles.");
        }

    }

    @Then("^Select Actions icon of first candidate displayed$")
    public void selectActionsIconOfFirstCandidateDisplayed() {
        DriverAction.click(MyLocators.actionsIcon,"Click the actions icon","Successfully clicked the Actions icon.");

    }

    @Then("^Select Actions icon of first record displayed in Employees$")
    public void selectActionsIconOfFirstCandidateDisplayedEmployees() {
        DriverAction.click(MyLocators.employeeActionsIcon,"Click the actions icon","Successfully clicked the Actions icon.");

    }

    @And("^Select Edit Profile option$")
    public void selectEditProfileOption() {
        DriverAction.click(MyLocators.editProfile,"Select Edit Profile option from dropdown","Successfully selected the Edit Profile option.");

    }

    @Then("^Enable editing$")
    public void enableEditing() {
        DriverAction.click(MyLocators.enableEditing,"Enable editing of Registered user","Successfully clicked Enable Editing option.");

    }

    @And("^Verify user not able to edit role$")
    public void verifyUserNotAbleToEditRole() {
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
    public void verifyTheStatusOfUserClickStatusButton() {
        try{
        status=DriverAction.getElementText(MyLocators.userStatus);
       if(status.equalsIgnoreCase("Active")){
           GemTestReporter.addTestStep("Verify the status of user","The current status is Active.",STATUS.PASS);
       }else{
           GemTestReporter.addTestStep("Verify the status of user","The current status is Inactive.",STATUS.PASS);
       }
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
    public void clickTheYesButton() {
        DriverAction.click(MyLocators.yesBtn,"Click the yes button","Successfully clicked Yes button.");

    }

    @Then("^Verify the current role \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyTheCurrentRole(String role1, String role2, String role3) {
        try {
            List<String> roles = DriverAction.getElementsText(MyLocators.currentRole);
            int numRoles = roles.size();
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
    public void expandInfoDropdownFromNavbar() {
        DriverAction.click(MyLocators.infoDropdown,"Click the dropdown icon on navbar","Successfully clicked the dropdown icon.");
    }

    @And("^Verify the options present in dropdown \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyTheOptionsPresentInDropdown(String option1,String option2, String option3) {

        try {
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
    public void selectChangePasswordVerifyDialog() {
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
    public void verifyTheEmailOfCandidate(String email) {
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
    public void verifyTheDefaultTabSelected(String tab) {
        try {
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
    public void verifyTheNumberOfTests(int testNumber,String tab) {
        try {
            List<WebElement> Tests = DriverAction.getElements(By.xpath(MyLocators.numTest.replace("input", tab)));
            int count = Tests.size();
            if (count == 0) {
                String message = DriverAction.getElementText(MyLocators.message);
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
}
