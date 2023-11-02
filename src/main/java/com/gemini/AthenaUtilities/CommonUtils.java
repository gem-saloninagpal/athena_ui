package com.gemini.AthenaUtilities;

import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.generic.exception.GemException;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeOptions;


public class CommonUtils {

    public static String tokenForAuth = null;
    static String  randomString = "";

    @Then("Navigate to page {string}")
    public static void pageNavigate(String page) throws InterruptedException {
        try {
//            DriverAction.waitSec(20);
            DriverAction.waitUntilElementClickable(By.xpath(MyLocators.pageNavigate.replace("input", page)),120);
            DriverAction.click(By.xpath(MyLocators.pageNavigate.replace("input", page)), "Navigate to page " + page, "Successfully navigated to page " + page);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Navigate to page-"+page, "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Given("^Login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public static void login(String username, String password) {

        try {

            DriverAction.waitUntilElementClickable(MyLocators.usernameField,120);
            System.out.println(randomString);

            //if new role is registered through the email, login using same email
            if(randomString.isEmpty()){
                DriverAction.typeText(MyLocators.usernameField, username);
                //else login using email passed from example
            }else {
                DriverAction.typeText(MyLocators.usernameField, randomString);
            }
            DriverAction.typeText(MyLocators.passwordField, password);
            DriverAction.waitUntilElementClickable(MyLocators.loginBtn,90);
            DriverAction.click(MyLocators.loginBtn);


            //wait while the page loads.
            if(DriverAction.isExist(MyLocators.spinner));
//            DriverAction.waitSec(8);
            DriverAction.waitUntilElementDisappear(MyLocators.spinner,20);

DriverAction.waitSec(20);
            //verify dashboard is displayed on login
            if (DriverAction.isExist(MyLocators.dashboard)) {
                GemTestReporter.addTestStep("Verify dashboard is displayed", "Successfully displayed the dashboard.", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            System.out.print("Could not login to Athena.");
        }
    }

    public static void getJsonToken() throws Exception {
        try {
            DriverAction.waitSec(4);
            tokenForAuth = (String) ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript(
                    "return window.localStorage.getItem('token');"
            );
        } catch (Exception e) {
            throw new Exception("Unable to fetch token");
        }
    }

    public static void before_Athena() throws GemException {
        try {
//            GemTestReporter.startSuite(GemJarGlobalVar.projectName, GemJarGlobalVar.environment, GemJarGlobalVar.s_run_id);
            // GemTestReporter.startTestCase("Gembook_Health", "In Before ALL", false);
            //////////////////////////////////////////////////////////////
            //chromeDriver Initialization
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--incognito");
            options.addArguments("start-maximized");
            DriverManager.setUpBrowser();
            DriverManager.getWebDriver().get("https://athena-dev.geminisolutions.com/");
            ///////////////////////////////////////////////////////////////
            //logging In to the Application
            //    OtherPortals.userLaunchesTheURL();
//            DriverAction.waitSec(10);
//            clickOnSignIn();
            pageNavigate("login");
            login("rahul23@gmail.com","abc@123");
//            enterCredentials("username");
//            enterCredentials("password");
//            userLoggedIn();
            ///////////////////////////////////////////////////////////////
            //calling HealthCheck function
//            healthCheck();
            getJsonToken();
            DriverManager.quitDriver();
            GemTestReporter.endTestCase();
            ////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            e.printStackTrace();
//            DriverManager.quitDriver();
//            GemTestReporter.addTestStep("Check if application is working ", LOGGER_EXCEPTION.getMessage() + e.getMessage(), STATUS.FAIL);
//            GemTestReporter.endTestCase();
//            GemTestReporter.endSuite(GemJarGlobalVar.reportLocation);
//            logger.error(LOGGER_EXCEPTION.getMessage() + e);
//            System.exit(1);
        }
    }
}
