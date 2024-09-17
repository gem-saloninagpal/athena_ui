package com.gemini.athenaUi.stepdefinitions;

import com.gemini.gemjar.exception.GemException;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.gemini.gemjar.utils.ui.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;


public class Hook {
    @Before
    public static void hook() throws GemException {
        DriverManager.setUpBrowser();
    }

}


