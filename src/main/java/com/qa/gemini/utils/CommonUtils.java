package com.qa.gemini.utils;

import com.gemini.generic.api.utils.ApiInvocation;
import com.gemini.generic.api.utils.Request;
import com.gemini.generic.api.utils.Response;
import com.gemini.generic.exception.GemException;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.GemJarUtils;
import com.gemini.generic.utils.ProjectConfigData;
import com.qa.gemini.Locators.MyLocators;
import com.qa.gemini.stepdefinitions.BaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.qa.gemini.utils.ReportingUtils.Report.LOGGER_EXCEPTION;
import static com.qa.gemini.utils.ReportingUtils.Report.PASS_TEST_STATUS;

public class CommonUtils {
    public static String tokenForAuth = null;
    static String  randomString = "";



//    public static final ThreadLocal<Integer> status = new ThreadLocal<Integer>();
//    public static final ThreadLocal<Response> response = new ThreadLocal<Response>();

    private static final Logger logger = LogManager.getLogger(UserLoginUtils.class);
    public static String nameOfToolLink = null;




    public static String buildURL(String endpoint) {
        String url = new String();
        if (GemJarUtils.getGemJarConfigData("environment").equals("dev")) {
            url = BaseURL.prodBaseURL + ProjectConfigData.getProperty(endpoint);
        } else {
            url = BaseURL.betaBaseURL + ProjectConfigData.getProperty(endpoint);
        }
        return url;
    }
    public static Response hitAPI(String UrlNameFromConfig, String method) {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = buildURL(UrlNameFromConfig);
            GemTestReporter.addTestStep("URL of the test case", "URL being hit is: " + url, STATUS.INFO);
            request.setURL(url);
            request.setMethod(method);
            // JWT Auth
            request.setHeader("Authorization", "Bearer " + tokenForAuth);
            //
            response = ApiInvocation.handleRequest(request);


            GemTestReporter.addTestStep("Request Method ", method.toUpperCase(), STATUS.PASS);

            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
                logger.info("Response Body : " + response.getResponseBody());
                logger.info("Response Message : " + response.getResponseMessage());
            } else if (response.getResponseBody().equalsIgnoreCase("Jwt is expired")) {
                getJsonToken();
                GemTestReporter.addTestStep("JWT is expired", "New Token is generated having value " + tokenForAuth, STATUS.INFO);
                hitAPI(ProjectConfigData.getProperty(UrlNameFromConfig), method);
            } else {
                GemTestReporter.addTestStep("Response Body", "No Response was received", STATUS.INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(LOGGER_EXCEPTION.getMessage() + e);
            logger.info("Response Error Message : " + response.getErrorMessage());
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request did not execute successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Error Message", response.getErrorMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response hitAPI(String UrlNameFromConfig, String method, String payLoad) {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = buildURL(UrlNameFromConfig);
            GemTestReporter.addTestStep("URL of the test case", "URL being hit is: " + url, STATUS.INFO);

            request.setURL(url);
            request.setMethod(method);
            request.setHeader("Authorization", "Bearer " + tokenForAuth);
            response = ApiInvocation.handleRequest(request);

            GemTestReporter.addTestStep(" Request Method ", method.toUpperCase(), STATUS.PASS);

            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
                logger.info("Response Body : " + response.getResponseBody());
                logger.info("Response Message : " + response.getResponseMessage());
            } else if (response.getResponseBody().equalsIgnoreCase("Jwt is expired")) {
                getJsonToken();
                hitAPI(ProjectConfigData.getProperty(UrlNameFromConfig), method);
            } else {
                GemTestReporter.addTestStep("Response Body", "No Response was received", STATUS.INFO);
                logger.info("Response Message : " + response.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("An error occurred ", e);
            logger.info("Response Error Message : " + response.getErrorMessage());
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Error Message", response.getErrorMessage(), STATUS.INFO);
            e.printStackTrace();
        }
        return response;
    }

    public static void VerifyStatusCode(int expected, int actual) {
        if (expected == actual) {
            GemTestReporter.addTestStep("Status Verification", PASS_TEST_STATUS.getMessage() + actual, STATUS.PASS);
            logger.info("Status matched as expected : " + expected);
        } else {
            GemTestReporter.addTestStep("Status Verification", "Expected Status :" + expected + ", Actual :" + actual, STATUS.FAIL);
            logger.info("Status Mismatch expected :" + expected + ", Found : " + actual);
        }
    }
//    @Given("Set endpoint {string} and method {string}")
//    public void setEndpointAndMethod(String urlKey, String method) throws Exception {
//        response.set(CommonUtils.hitAPI(urlKey, method));
//        status.set(response.get().getStatus());
//    }
//
//    @Then("^Verify Status code \"(.*)\"$")
//    public static void verifyStatus(int expectedStatus) {
//        CommonUtils.VerifyStatusCode(expectedStatus, status.get());
//    }

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
//
            //////////////////////////////////////////////////////////////
            //chromeDriver Initialization
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--incognito");
            options.addArguments("start-maximized");
            DriverManager.setUpBrowser();
            DriverManager.getWebDriver().get("https://athena-dev.geminisolutions.com/");
            ///////////////////////////////////////////////////////////////

            pageNavigate("login");
            login("rahul23@gmail.com","abc@123");

            ///////////////////////////////////////////////////////////////
            //calling HealthCheck function
//            healthCheck();
            getJsonToken();
            DriverManager.quitDriver();
            GemTestReporter.endTestCase();
            ////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Given("^Select \"([^\"]*)\", \"([^\"]*)\" from sidebar$")
    public void selectFromSidebar(String module, String submodule) {
        try {
//           DriverAction.waitUntilElementAppear(MyLocators.sidebar,120);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 50);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(MyLocators.sidebar));
//            element.click();
            //open sidebar
            DriverAction.click(MyLocators.sidebar, "Expand the sidebar", "Sidebar expands displaying list of modules.");
            //select a module from sidebar
            DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", module)));

            //select submodule if required
            if(!submodule.isEmpty()){
                DriverAction.click(By.xpath(MyLocators.selectModule.replace("input", submodule)));
            }
            //close sidebar
            DriverAction.click(MyLocators.crossIcon, "Click the cross icon of sidebar", "Successfully clicked the cross icon.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Select module from sidebar", "Throws exception", STATUS.ERR, DriverAction.takeSnapShot());
        }
    }



}
