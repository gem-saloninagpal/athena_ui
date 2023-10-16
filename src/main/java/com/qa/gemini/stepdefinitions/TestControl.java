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
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
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

    @Then("get the Active test from Ui and validate with Api")
    public void getTheActiveTestFromUiAndValidateWithApi() {
        DriverAction.waitSec(5);
        List<String> uiTest = new ArrayList<>(), apiTest = new ArrayList<>();
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
                                if (collegeObject.has("testName")) {
                                    String collegeName = collegeObject.get("testName").getAsString();
                                    apiTest.add(collegeName);
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

           List<String> activeTest=DriverAction.getElementsText(TestControl_Locator.activeTestList);
            for(String e:activeTest)
            {
                uiTest.add(e);
            }

            System.out.println(uiTest);
            System.out.println(apiTest);
//            Collections.sort(uiTest);
//            Collections.sort(apiTest);
            if (uiTest.equals(apiTest)) {
                GemTestReporter.addTestStep("Verify if UI Test Names matches API Test Names",
                        "Validation Passed : " + "<BR>UI Test Names: " + uiTest + "<BR>API Test Names: " + apiTest, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify if UI Test Names matches API Test Names",
                        "Validation Failed: " + "<BR>UI Test Names: " + uiTest + "<BR>API Test Names: " + apiTest, STATUS.FAIL);
            }



        }
        catch (Exception e) {
            GemTestReporter.addTestStep("Verify if UI Event Names matches API Event Names", "Validation Failed", STATUS.FAIL);
            throw e;
        }
    }

    @Then("get the Paginated Active test from Ui and Validate with Api")
    public void getThePaginatedActiveTestFromUiAndValidateWithApi() {
        DriverAction.waitSec(5);
        List<String> uiTest = new ArrayList<>(), apiTest = new ArrayList<>();
        try {
            Response responseObj = response.get();
            JsonElement s = responseObj.getResponseBodyJson();
            if (s.isJsonObject()) {
                JsonObject jsonObject = s.getAsJsonObject();
                if (jsonObject.has("data")) {
                    JsonElement dataElement = jsonObject.get("data");
                    if (dataElement.isJsonObject()) { // Use isJsonObject to check for an object
                        JsonObject dataObject = dataElement.getAsJsonObject();
                        if (dataObject.has("paginatedData")) {
                            JsonArray dataArray = dataObject.getAsJsonArray("paginatedData");
                            for (JsonElement element : dataArray) {
                                if (element.isJsonObject()) {
                                    JsonObject collegeObject = element.getAsJsonObject();
                                    if (collegeObject.has("testName")) {
                                        String collegeName = collegeObject.get("testName").getAsString();
                                        apiTest.add(collegeName);
                                    }
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

//            List<WebElement> status=DriverAction.getElements(TestControl_Locator.statusList);

            while(DriverAction.isEnabled(TestControl_Locator.rightPaginator))
            {
                List<WebElement> status=DriverAction.getElements(TestControl_Locator.statusList);
                for(int i=0;i<status.size();i++)
                {
                    String fetchedStatus=DriverAction.getAttributeName(By.xpath(TestControl_Locator.status.replace("itr",String.valueOf(i+1))),"aria-checked");
                    if(fetchedStatus!=null) {
                        if (fetchedStatus.equals("true")) {
                            uiTest.add(DriverAction.getElementText(By.xpath(TestControl_Locator.name.replace("itr", String.valueOf(i + 1)))));
                        }
                    }

                }
                if(DriverAction.isEnabled(TestControl_Locator.rightPaginator)) {
                    DriverAction.click(TestControl_Locator.rightPaginator);
                }
            }
            Collections.sort(uiTest);
            Collections.sort(apiTest);
            if (uiTest.equals(apiTest)) {
                GemTestReporter.addTestStep("Verify if UI Paginated Test Names matches API Paginated Test Names",
                        "Validation Passed : " + "<BR>UI Paginated Test Names: " + uiTest + "<BR>API Paginated Test Names: " + apiTest, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify if UI Paginated Test Names matches API Paginated Test Names",
                        "Validation Failed: " + "<BR>UI Paginated Test Names: " + uiTest + "<BR>API Paginated Test Names: " + apiTest, STATUS.FAIL);
            }



        }
        catch (Exception e) {
            GemTestReporter.addTestStep("Verify if UI Paginated Test Names matches API Paginated Test Names", "Validation Failed", STATUS.FAIL);
            throw e;
        }
    }

    @Given("Set the PostInteraction {string}, {string}, {string} and {string} for posting data")
    public void hitPostApiWithParams(String endpoint, String reqMethod, String paramKey, String paramValue) {
        Response response = new Response();
        try {
            String url = CommonUtils.buildURL(endpoint);
            response = CommonUtils.hitPostApiWithParams(url, reqMethod, paramKey, paramValue);

            actualStatus = response.getStatus();
            responseMessage = response.getResponseMessage();
            responseBody = response.getResponseBody();

        } catch (Exception e) {
            GemTestReporter.addTestStep("Hit Api: " + CommonUtils.buildURL(endpoint), "Unable to hit API", STATUS.FAIL);
        }
    }

    @Then("Verify expected {string} of PostInteraction API with actual Status Code")
    public void verifyPostInteractionAPIStatusCode(String expectedStatus) {
        CommonUtils.matchStatusCodes(Integer.parseInt(expectedStatus), actualStatus);
    }

    @Then("Verify expected {string} of PostInteraction API matches actual Response Message")
    public void verifyPostInteractionAPIResponseMessage(String expectedResponseMessage) {
        CommonUtils.matchResponseMessage(expectedResponseMessage, responseMessage);
    }

    @And("Validate that Test is assign again")
    public void validateThatTestIsAssignAgain() {
        if(DriverAction.isExist(TestControl_Locator.startBtn))
        {
            GemTestReporter.addTestStep("Validate test is assign again", "Test is assign again successfully", STATUS.PASS);
        }
        else
        {
            GemTestReporter.addTestStep("Validate test is assign again", "Test is not assign again", STATUS.FAIL);

        }
    }
}
