package com.qa.Athena.Stepdefination;

import com.qa.Athena.CommonUtils.Utils;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static com.qa.Athena.CommonUtils.CommonFunctions.getDataMapValue;
import static com.qa.Athena.CommonUtils.CommonFunctions.setDataMapValue;

public class Test_Control {


    public static void setHeader() {
        String token = (String) getDataMapValue("jwt_token");
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + token);
        setDataMapValue("header", header);
    }

    @When("Set endpoints {string} method {string} to get Active Campus")
    public void setEndpointsMethodToGetActiveCampus(String endpoint, String method) {
        setHeader();
        Map<String, String> header = (Map<String, String>) getDataMapValue("header");
        Utils.hitAPI(endpoint, method, "", header);
    }
}
