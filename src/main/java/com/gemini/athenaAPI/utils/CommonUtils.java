package com.gemini.athenaAPI.utils;

import com.gemini.athenaAPI.stepDefinition.APIStepDefinition;
import com.gemini.gemjar.utils.api.ApiInvocation;
import com.gemini.gemjar.utils.api.ProjectSampleJson;
import com.gemini.gemjar.utils.api.Request;
import com.gemini.gemjar.utils.api.Response;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.app.ProjectConfigData;
import com.gemini.gemjar.utils.api.Request;
import com.gemini.gemjar.utils.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    public static String tokenForAuth;
    public static Response response;

    public static Response HitAPI(String UrlNameFromConfig, String method, String step, String sampleName) {
        response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url of the test case", url, Status.INFO);
            request.setURL(url);
            request.setHeader("Authorization", "Bearer " + tokenForAuth);
            request.setMethod(method);
            if (step != null) {
                request.setStep(step);
            }
            if (sampleName != null) {
                String payload = ProjectSampleJson.getSampleDataString(sampleName);
                request.setRequestPayload(payload);
            }
            response = ApiInvocation.handleRequest(request);
//            token =response.getJsonObject().get("jwttoken").toString();
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), Status.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), Status.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", Status.INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", Status.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), Status.INFO);
        }
        return response;
    }

    public static Response HitAPIWithRandomPayload(String UrlNameFromConfig, String method, String step, String sampleName) {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url of the test case", url, Status.INFO);
            request.setURL(url);
            request.setHeader("Authorization", "Bearer " + tokenForAuth);
            request.setMethod(method);
            if (step != null) {
                request.setStep(step);
            }
            if (sampleName != null) {

                JsonElement payload = ProjectSampleJson.getSampleData(sampleName);
                Map<String,Object> resMap=jsonElementToMap(payload);
                resMap.replace("userId", APIStepDefinition.generateRandomNumber());
                JsonElement abc=mapToJsonElement(resMap);
                writeJsonToFile(abc,"src/main/resources/addCandidateTest.json");
                String payload1 = ProjectSampleJson.getSampleDataString(sampleName);
                request.setRequestPayload(payload1);

            }
//            response = ApiInvocation.handleRequest(request);
//            token =response.getJsonObject().get("jwttoken").toString();
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), Status.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), Status.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", Status.INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", Status.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), Status.INFO);
        }
        return response;
    }

    public static Response HitApiToGetToken(String UrlNameFromConfig, String method, String step, String sampleName) {
        response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url of the test case", url, Status.INFO);
            request.setURL(url);
            request.setMethod(method);
            if (step != null) {
                request.setStep(step);
            }
            if (sampleName != null) {
                String payload = ProjectSampleJson.getSampleDataString(sampleName);
                request.setRequestPayload(payload);
            }
            response = ApiInvocation.handleRequest(request);
            tokenForAuth = response.getResponseBodyJson().getAsJsonObject().get("data").getAsJsonObject().get("jwttoken").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void StatusValidation(int actual, int expected) {
        Status Status = actual == expected ? com.gemini.gemjar.enums.Status.PASS : com.gemini.gemjar.enums.Status.FAIL;
        GemTestReporter.addTestStep("Status Validation", "Expected: " + expected
                + " Actual: " + actual, Status);
    }

    public static Map<String, Object> jsonElementToMap(JsonElement jsonElement) {
         Gson gson = new Gson();
         Map<String, Object> resultMap = gson.fromJson(jsonElement, new TypeToken<HashMap<String, Object>>(){}.getType());
         return resultMap;
    }
    public static JsonElement mapToJsonElement(Map<String, Object> map) {
         Gson gson = new Gson();
         JsonElement jsonElement = gson.toJsonTree(map);
         return jsonElement;
    }

    public static void writeJsonToFile(JsonElement jsonElement, String filePath) {
        try {
             Gson gson = new GsonBuilder().setPrettyPrinting().create();
             FileWriter writer = new FileWriter(filePath);
            gson.toJson(jsonElement, writer);
            writer.close();
             } catch (IOException e)
             {
                 e.printStackTrace();
             }
             }
}

