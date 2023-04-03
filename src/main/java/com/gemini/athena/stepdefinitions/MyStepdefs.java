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
    String randomString="";
    @Given("^Login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginUsingUsernameAndPassword(String username, String password) {

       try {
          //  DriverAction.waitUntilElementAppear(MyLocators.athenaLogo,6000);
           Thread.sleep(7000);
            DriverAction.typeText(MyLocators.usernameField, username);
            DriverAction.typeText(MyLocators.passwordField, password);
            DriverAction.click(MyLocators.loginBtn);
            DriverAction.waitUntilElementAppear(MyLocators.dashboard,4000);
            if(DriverAction.isExist(MyLocators.dashboard)){
                GemTestReporter.addTestStep("Verify dashboard is displayed","Successfully displayed the dashboard.", STATUS.PASS,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            System.out.print("Could not login to Athena.");
        }
    }

    @Given("^Select \"([^\"]*)\" from sidebar$")
    public void selectFromSidebar(String module) {
        try {
            Thread.sleep(3000);
            DriverAction.click(MyLocators.sidebar,"Expand the sidebar","Sidebar expands displaying list of modules.");
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", module)));
            DriverAction.click(MyLocators.crossIcon,"Click the cross icon of sidebar","Successfully clicked the cross icon.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Select module from sidebar","Throws exception",STATUS.ERR,DriverAction.takeSnapShot());
        }
    }


    @Then("^Click the button \"([^\"]*)\"$")
    public void clickTheButton(String buttonName) {
        DriverAction.click(By.xpath(MyLocators.button.replace("input",buttonName)));

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
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the error while navigating to required page","Error occured is "+e,STATUS.ERR);
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
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify error displayed in input fields","Exception encountered "+e,STATUS.ERR);
        }
    }

    @Then("^Select a role from dropdown \"([^\"]*)\"$")
    public void selectARoleFromDropdown(String role) {
        DriverAction.click(MyLocators.dropdownIcon,"Click the dropdown icon","List of options displays.");
        DriverAction.click(By.xpath(MyLocators.option.replace("input",role)));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DriverAction.click(MyLocators.crossIcon);
    }

    @Then("^Enter respective values in input fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterRespectiveValuesInInputFields(String password, String confirmPassword, String firstName, String lastName, String email, String number, String experience) {
        try {
            List<WebElement> inputFields = DriverAction.getElements(MyLocators.inputFields);
            String inputValues[] = {password, confirmPassword, firstName, lastName, email, number, experience};
            for (int i = 0; i < inputFields.size(); i++) {
                String placeholder = inputFields.get(i).getAttribute("id");
                if(placeholder.equalsIgnoreCase("email")){
                    inputFields.get(i).sendKeys(randomString);
                }else {
                    inputFields.get(i).sendKeys(inputValues[i]);
                }
                GemTestReporter.addTestStep("Enter " + inputValues[i] + " in " + placeholder, "Successfully entered " + inputValues[i], STATUS.PASS, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Enter respective values in input fields","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Switch to tab \"([^\"]*)\", \"([^\"]*)\"$")
    public void switchTab(String tab, int i) {
          DriverAction.click(By.xpath(MyLocators.selectTab(i)));
    }




    @Then("^Verify \"([^\"]*)\" is registered \"([^\"]*)\",\"([^\"]*)\"$")
    public void verifyIsRegistered(String role,String name,String lname) {
        try {
            String firstName = DriverAction.getElementText(MyLocators.learnerFirstName);
            String lastName = DriverAction.getElementText(By.xpath("(//tbody)[3]//td[2]"));
            if (firstName.contains(name) && lastName.contains(lname)) {
                GemTestReporter.addTestStep("Verify learner is registered", "Successfully verified the registered learner " + name, STATUS.PASS, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            System.out.println("Exception encountered while verifying registered user.");
        }
    }

    @Then("^Verify the popup message \"([^\"]*)\"$")
    public void verifyTheErrorPopup(String error) {
        try {
            String errorMessage = DriverAction.getElementText(MyLocators.popupMsg);
            if (errorMessage.contains(error)) {
                 GemTestReporter.addTestStep("Verify the popup message","Successfully verified popup message "+errorMessage,STATUS.PASS,DriverAction.takeSnapShot());
            }
            else{
                GemTestReporter.addTestStep("Verify the popup message","Could not verify the popup message "+errorMessage,STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }
        catch(Exception e){
            System.out.println("Exception encountered.");
        }
    }

    @Then("^Generate unique email$")
    public void generateUniqueEmail() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        randomString = sb.toString();
        randomString=randomString.concat("@gmail.com");
        System.out.println("Random String is: " + randomString);
    }
}
