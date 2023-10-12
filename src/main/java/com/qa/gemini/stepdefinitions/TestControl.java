package com.qa.gemini.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.generic.api.utils.Response;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.qa.gemini.Locators.TestControl_Locator;
import com.qa.gemini.pojo.TestControlPojo;
import com.qa.gemini.utils.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.List;

public class TestControl {
    static String responseMessage,responseBody;
    static int actualStatus;
    public static final ThreadLocal<Integer> status = new ThreadLocal<Integer>();
    public static final ThreadLocal<Response> response = new ThreadLocal<Response>();
    @Given("Set Test Control endpoint {string} and method {string}")
    public void setTestControlEndpointAndMethod(String urlKey, String method) {
        response.set(CommonUtils.hitAPI(urlKey, method));
        status.set(response.get().getStatus());

        // Get the Response object from the ThreadLocal

        Response responseObj = response.get();
        if (responseObj != null) {
            actualStatus = responseObj.getStatus();
            responseMessage = responseObj.getResponseMessage();
            responseBody = responseObj.getResponseBody();
        } else {

        }
    }

    @Then("^Verify Status code \"(.*)\"$")
    public static void verifyStatus(int expectedStatus) {
        CommonUtils.VerifyStatusCode(expectedStatus, status.get());
    }
    @Then("get the Campus details and validate with api")
    public void getTheCampusDetailsAndValidateWithApi() throws JsonProcessingException {
        DriverAction.waitSec(5);
        List<String> uiCampus = new ArrayList<>(), apiCampus = new ArrayList<>();
        try {
            Response responseObj = response.get();
            JsonElement s = responseObj.getResponseBodyJson();
            if (s.isJsonObject()) {
                JsonObject jsonObject = s.getAsJsonObject();
                if (jsonObject.has("data")) {
                    JsonElement dataElement = jsonObject.get("data");
                    if (dataElement.isJsonArray()) {
                        JsonArray dataArray = dataElement.getAsJsonArray();
                        for (JsonElement element : dataArray) {
                            if (element.isJsonObject()) {
                                JsonObject collegeObject = element.getAsJsonObject();
                                if (collegeObject.has("collegeName")) {
                                    String collegeName = collegeObject.get("collegeName").getAsString();
                                    apiCampus.add(collegeName);
                                }
                            }
                        }
                    } else {
                        // Handle the case where "data" is not an array
                    }
                } else {
                    // Handle the case where "data" key is not found in the JSON
                }
            } else {
                // Handle the case where the JSON is not an object
            }
            //click on dropdown of campus and fetch the list data
            if(DriverAction.isExist(TestControl_Locator.campusDropdown,120))
            {
                DriverAction.click(TestControl_Locator.campusDropdown);
            }
            else {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Campus Dropdown",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(TestControl_Locator.campusList,120))
            {
                List<String> campus=DriverAction.getElementsText(TestControl_Locator.campusList);
                for(String e:campus)
                {
                   String[] universityList=e.split(",");
                   System.out.println(universityList[0]);
                   uiCampus.add(universityList[0]);
                }
            }
            if (uiCampus.equals(apiCampus)) {
                GemTestReporter.addTestStep("Verify if UI Campus Names matches API Campus Names",
                        "Validation Passed : " + "<BR>UI Campus Names: " + uiCampus + "<BR>API Campus Names: " + apiCampus, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify if UI Campus Names matches API Campus Names",
                        "Validation Failed: " + "<BR>UI Campus Names: " + uiCampus + "<BR>API Campus Names: " + apiCampus, STATUS.FAIL);
            }
//            System.out.println(uiCampus);
//            System.out.println(apiCampus);


        }
        catch (Exception e) {
            GemTestReporter.addTestStep("Verify if UI Event Names matches API Event Names", "Validation Failed", STATUS.FAIL);
            throw e;
        }



    }
}
