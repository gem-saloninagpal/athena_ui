package com.gemini.athenaApi.stepdefinitions;

import com.gemini.AthenaUtilities.UserLoginUtils;
import com.gemini.AthenaUtilities.UserLoginUtils;
import com.gemini.generic.api.utils.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class UserLoginApi {
    public static final ThreadLocal<Integer> status = new ThreadLocal<Integer>();
    public static final ThreadLocal<Response> response = new ThreadLocal<Response>();
    @Given("Set endpoint and method {string} and {string}")
    public void setEndpointMethod(String urlKey, String method) throws Exception {
        response.set(UserLoginUtils.hitAPI(urlKey, method));
        status.set(response.get().getStatus());
    }

    @Then("^Verify Status code \"(.*)\"$")
    public static void verifyStatus(int expectedStatus) {
        UserLoginUtils.VerifyStatusCode(expectedStatus, status.get());
    }



}
