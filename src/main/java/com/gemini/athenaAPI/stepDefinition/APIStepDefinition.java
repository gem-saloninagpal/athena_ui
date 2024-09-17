package com.gemini.athenaAPI.stepDefinition;

import com.gemini.athenaAPI.utils.CommonUtils;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class APIStepDefinition {
    int StatusCode;
    String responseBody;

    @Given("Set endpoint {string} and Method {string}")
    public void setEndpointAndMethod(String endpoint, String method) {
        StatusCode = CommonUtils.HitAPI(endpoint, method, null, null).getStatus();
    }

    @Given("Set endpoint {string} method {string} and SampleName {string}")
    public void setEndpointMethodAndSampleName(String endpoint, String method, String sampleName) {
        System.out.println("helloooooo!!!!!!!!!");
        StatusCode = CommonUtils.HitAPI(endpoint, method, null, sampleName).getStatus();
    }
    @Given("Set endpoint {string} method {string} and Random SampleName {string}")
    public void setEndpointMethodAndRandomSampleName(String endpoint, String method, String sampleName) {
        System.out.println("helloooooo!!!!!!!!!");
        StatusCode = CommonUtils.HitAPIWithRandomPayload(endpoint, method, null, sampleName).getStatus();
    }

    @Given("Set authenticate {string} method {string} and SampleName {string}")
    public void setAuthenticateMethodAndSampleName(String endpoint, String method, String sampleName) {
        StatusCode = CommonUtils.HitApiToGetToken(endpoint, method, null, sampleName).getStatus();
    }

    @Then("Verify Status code {int}")
    public void verifyStatusCodeExpected_Status(int expectedStatusCode) {
        CommonUtils.StatusValidation(StatusCode, expectedStatusCode);
    }

    public static int generateRandomNumber() {
        Random rand = new Random();

        // Generate a random number greater than 6 digits
        int randomNumber = 1000000 + rand.nextInt(9000000);
        return randomNumber;
    }

    @Given("Set endpoint {string}, method and payload to save a user")
    public void saveAUser(String endpoint) throws Throwable {
        RestAssured.baseURI = endpoint;

        // Create a payload (using Map)
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "saloni.nagpal@geminisolutions.com");
        payload.put("passwword", "YWJjQDEyMw==");

        // Hit POST request with payload
        Response response = RestAssured.given()
                .contentType(ContentType.JSON) // Specify content type
                .body(payload) // Set payload
                .post("/endpoint"); // Hit POST request to specific endpoint

        // Get response body
        responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // Get Status code
        int StatusCode = response.getStatusCode();
        System.out.println("Status Code: " + StatusCode);
    }

    @Then("Verify response message {string}")
    public void verifyResponseMessage(String message) {
        try{
          String getMessage= CommonUtils.response.getResponseBodyJson().getAsJsonObject().get("message").getAsString();
          if(getMessage.contains(message)){
              GemTestReporter.addTestStep("Verify response message","Successfully verified response message- "+message, Status.PASS,DriverAction.takeSnapShot());
          }else{
              GemTestReporter.addTestStep("Verify response message","Could not verify response message- "+message, Status.FAIL,DriverAction.takeSnapShot());
          }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify response message","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }
}



