package com.qa.Athena.Stepdefination;

import com.gemini.generic.api.utils.Response;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.google.gson.JsonObject;
import com.qa.Athena.CommonUtils.CommonFunctions;
import com.qa.Athena.CommonUtils.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.openqa.selenium.devtools.v85.cachestorage.model.Header;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.qa.Athena.CommonUtils.CommonFunctions.getDataMapValue;
import static com.qa.Athena.CommonUtils.CommonFunctions.setDataMapValue;

public class LoginSteps {

    @Given("User authenticate as {string}")
    public void userAuthenticateAs(String user) {
        Utils.getToken(user);
    }

    public static void setHeader() {
        String token = (String) getDataMapValue("jwt_token");
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + token);
        setDataMapValue("header", header);
    }

    @Then("Verify status code {int}")
    public void verifyStatusCode(int status_code) {
        Response response = (Response) getDataMapValue("response");
        if (status_code == response.getStatus()) {
            GemTestReporter.addTestStep("Verifying Status Code",
                    "expected - " + status_code + " Actual - " + response.getStatus()
                    , STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Verifying Status Code",
                    "expected - " + status_code + " Actual - " + response.getStatus()
                    , STATUS.FAIL);
        }
    }

    @When("Set endpoints {string} method {string} to get dashboard analytics")
    public void setEndpointsMethodToGetDashboardAnalytics(String endpoint, String method) {
        setHeader();
        Map<String, String> header = (Map<String, String>) getDataMapValue("header");
        Utils.hitAPI(endpoint, method, "", header);
    }

    @And("Get the course details")
    public void getTheCourseDetails() {
        Response response = (Response) getDataMapValue("response");
        JsonObject data = response.getResponseBodyJson().getAsJsonObject().get("data")
                .getAsJsonObject();
        System.out.println(data);
    }

    @And("Verify response message {string}")
    public void verifyResponseMessage(String message) {
        Response response = (Response) getDataMapValue("response");
        String actual_message = response.getResponseBodyJson().getAsJsonObject()
                .get("message").getAsString();
        if (actual_message.equalsIgnoreCase(message)) {
            GemTestReporter.addTestStep("Response Message", "Expected - " + message +
                    " Actual - " + actual_message, STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Response Message", "Expected - " + message +
                    " Actual - " + actual_message, STATUS.FAIL);
        }
    }
}
