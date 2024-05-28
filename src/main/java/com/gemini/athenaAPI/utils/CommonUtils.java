package com.gemini.athenaAPI.utils;

import com.gemini.athenaAPI.stepDefinition.APIStepDefinition;
import com.gemini.generic.api.utils.ApiInvocation;
import com.gemini.generic.api.utils.ProjectSampleJson;
import com.gemini.generic.api.utils.Request;
import com.gemini.generic.api.utils.Response;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.utils.ProjectConfigData;
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

    public static Response HitAPI(String UrlNameFromConfig, String method, String step, String sampleName) {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url of the test case", url, STATUS.INFO);
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
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response HitAPIWithRandomPayload(String UrlNameFromConfig, String method, String step, String sampleName) {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url of the test case", url, STATUS.INFO);
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
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response HitApiToGetToken(String UrlNameFromConfig, String method, String step, String sampleName) {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url of the test case", url, STATUS.INFO);
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

    public static void statusValidation(int actual, int expected) {
        STATUS status = actual == expected ? STATUS.PASS : STATUS.FAIL;
        GemTestReporter.addTestStep("Status Validation", "Expected: " + expected
                + " Actual: " + actual, status);
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

