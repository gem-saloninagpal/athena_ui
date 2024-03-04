package com.gemini.athenaApi.stepDefinations;

import com.gemini.athenaApi.commonUtils.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.HashMap;

public class Athena {

    public static int status;


    @Given("Set endpoint {string} and method {string} for Athena login")
    public void setEndpointAndMethodForAthenaLogin(String url, String method) {
        HashMap<String, String> token = new HashMap<String, String>();
        token.put("Authorization", Utils.GetAuthorization());
        try {
            status = Utils.LoginUser(url, method, token, "Login User").getStatus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @And("Verify status code {int} for Athena")
    public void verifyStatusCodeForAthena(Integer expected) {
        Utils.VerifyStatusCode(expected, status);
    }
}
