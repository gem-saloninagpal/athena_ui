package com.gemini.athena.stepdefinitions;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;

public class Hook {
    @Before
    public static void hook() throws GemException {
        DriverManager.setUpBrowser();
    }

}
