package com.gemini.athenaAPI.stepDefinition;

import com.gemini.athenaAPI.utils.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class APIStepDefinition {
    int statusCode;

    @Given("Set endpoint {string} and Method {string}")
    public void setEndpointAndMethod(String endpoint, String method) {
        statusCode = CommonUtils.HitAPI(endpoint, method,null,null).getStatus();
    }

    @Given("Set endpoint {string} method {string} and SampleName {string}")
    public void setEndpointMethodAndSampleName(String endpoint, String method, String sampleName) {
        System.out.println("helloooooo!!!!!!!!!");
        statusCode = CommonUtils.HitAPI(endpoint, method, null,sampleName).getStatus();
    }
    @Given("Set authenticate {string} method {string} and SampleName {string}")
    public void setAuthenticateMethodAndSampleName(String endpoint, String method, String sampleName) {
        statusCode = CommonUtils.HitApiToGetToken(endpoint, method, null,sampleName).getStatus();
    }

    @Then("Verify Status code {int}")
    public void verifyStatusCodeExpected_status(int expectedStatusCode) {
        CommonUtils.statusValidation(statusCode, expectedStatusCode);
    }

}



