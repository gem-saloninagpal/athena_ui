package com.gemini.athenaApi.commonUtils;

import com.gemini.generic.api.utils.ApiInvocation;
//import static io.restassured.RestAssured.*;
import com.gemini.generic.api.utils.Request;
import com.gemini.generic.api.utils.Response;
import com.gemini.generic.exception.GemException;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.athenaUi.stepdefinitions.CandidateModule_UserManagement;
import com.gemini.generic.utils.ProjectConfigData;
import io.cucumber.java.en.Given;
import org.apache.http.HttpStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static com.gemini.athenaUi.stepdefinitions.CandidateModule_UserManagement.login;
import static com.gemini.athenaUi.stepdefinitions.CandidateModule_UserManagement.pageNavigate;

public class Utils {

    public static String tokenForAuth = null;

    public static String GetToken() {

       return "tocken";
    }

    public static String GetAuthorization() {
        return tokenForAuth;
    }

    public static void VerifyStatusCode(int expected, int actual) {
        if (expected == actual) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status :" + expected + ",<br>Actual :" + actual, STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Status Verification", "Expected Status :" + expected + ",<br>Actual :" + actual, STATUS.FAIL);
        }
    }

    //api calling
    public static String APIcalling() {
        String commonAPI = "https://devapi.geminisolutions.com/gemassessment/";
        return commonAPI;
    }


    //get authorization tocken

    public static void getAuthTocken() throws GemException {
        try {
            //chromeDriver Initialization
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--incognito");
            options.addArguments("start-maximized");
            DriverManager.setUpBrowser();
            DriverManager.getWebDriver().get("https://athena-dev.geminisolutions.com/");


            pageNavigate("login");
            login("rahul.adhikari@geminisolutions.com","abc@123");


            getJsonToken();
            DriverManager.quitDriver();
            GemTestReporter.endTestCase();

        } catch (Exception e) {
            e.printStackTrace();

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
    public static Response LoginUser(String UrlNameFromConfig, String method, Map<String, String> headers, String step) throws Exception {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = APIcalling() + "authenticate";
            GemTestReporter.addTestStep("Url for " + method.toUpperCase() + " Request", url, STATUS.INFO);
            request.setURL(url);
            request.setMethod(method);
            if (!headers.isEmpty()) {
                request.setHeaders(headers);
            }
            if (!step.isEmpty()) {
                request.setStep(step);
            }
//
//          response=given().
//            if (response.getStatus() == HttpStatus.SC_OK) {
//                GlobalVariable.token = response.getResponseBodyJson().getAsJsonObject().get("Token").getAsString();
//            }
////            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
//            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
//            if ((response.getResponseBody()) != null) {
//                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
//            } else {
//                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
//            }
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }
    @Given("Set endpoint {string} and method {string} for MIS Leave login")
    public void setEndpointAndMethodForMISLogin(String url, String method) {
        HashMap<String, String> token = new HashMap<String, String>();
        token.put("Authorization", Utils.GetAuthorization());
        try {
//            status = Utils.LoginUser(url, method, token, "Login User").getStatus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
