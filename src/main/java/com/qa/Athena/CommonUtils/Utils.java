package com.qa.Athena.CommonUtils;

import com.gemini.generic.api.utils.ApiInvocation;
import com.gemini.generic.api.utils.ProjectSampleJson;
import com.gemini.generic.api.utils.Request;
import com.gemini.generic.api.utils.Response;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import org.apache.hc.core5.http.HttpStatus;

import java.util.Map;

import static com.qa.Athena.CommonUtils.CommonFunctions.*;

public class Utils {


    public static void getToken(String user) {
        String token = "";
        Response response = new Response();
        String userType = "";
        try {
            Request request = new Request();
            String url = "https://devapi.geminisolutions.com/gemassessment/authenticate";
            GemTestReporter.addTestStep("Url for " + "post" + " Request", url, STATUS.INFO);
            request.setURL(url);
            request.setMethod("post");
            switch (user) {
                case "super_admin":
                    userType = "login_super_admin";
                    break;
                case "admin":
                    userType = "login_admin";
                    break;
                default:
                    userType = "login_user";
            }
            String payload = ProjectSampleJson.getSampleDataString("payloads/login/" + userType);
            request.setRequestPayload(payload);
            GemTestReporter.addTestStep("Payload", payload, STATUS.INFO);
            response = ApiInvocation.handleRequest(request);
            setDataMapValue("response", response);
            if (response.getStatus() == HttpStatus.SC_OK) {
                token = response.getResponseBodyJson().getAsJsonObject().get("data")
                        .getAsJsonObject().get("jwttoken").getAsString();
                setDataMapValue("jwt_token", token);
            }
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
    }

    public static void hitAPI(String endpoint, String method, String payload,
                              Map<String, String> header) {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = getUrl(endpoint);
            request.setURL(url);
            request.setMethod(method);
            GemTestReporter.addTestStep("Url for " + method + " Request", url, STATUS.INFO);

            if (!header.isEmpty()) {
                request.setHeaders(header);
                GemTestReporter.addTestStep("Header", header.toString(), STATUS.INFO);
            }
            if (!payload.isEmpty()) {
                request.setRequestPayload(payload);
                GemTestReporter.addTestStep("Payload", payload, STATUS.INFO);
            }
            response = ApiInvocation.handleRequest(request);
            setDataMapValue("response", response);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
    }
}
