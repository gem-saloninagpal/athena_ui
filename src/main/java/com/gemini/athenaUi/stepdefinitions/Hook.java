package com.gemini.athenaUi.stepdefinitions;


import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import static com.gemini.athenaApi.commonUtils.Utils.getAuthTocken;


public class Hook {
 //   static WebDriver driver;
    @Before
    public static void hook() throws GemException {
        DriverManager.setUpBrowser();

        //get auth tocken for api
        getAuthTocken();

    }




}
