package com.gemini.AthenaUtilities;

import com.gemini.generic.api.utils.ApiInvocation;
import com.gemini.generic.api.utils.Request;
import com.gemini.generic.api.utils.Response;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.utils.GemJarUtils;
import com.gemini.generic.utils.ProjectConfigData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.gemini.athenaApi.stepdefinitions.*;

import static com.gemini.AthenaUtilities.CommonUtils.getJsonToken;
import static com.gemini.AthenaUtilities.CommonUtils.tokenForAuth;
import static com.gemini.AthenaUtilities.ReportingUtils.Report.LOGGER_EXCEPTION;
import static com.gemini.AthenaUtilities.ReportingUtils.Report.PASS_TEST_STATUS;

public class UserLoginUtils {
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
}
