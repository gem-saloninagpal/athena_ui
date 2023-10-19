package com.gemini.athenaUi.stepdefinitions;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;

public class Hook {
 //   static WebDriver driver;
    @Before
    public static void hook() throws GemException {
        DriverManager.setUpBrowser();
    }

}

