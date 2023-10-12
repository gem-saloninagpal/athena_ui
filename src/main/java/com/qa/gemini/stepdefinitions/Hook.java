package com.qa.gemini.stepdefinitions;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gemini.utils.CommonUtils;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hook {
    @Before
    public static void hook() throws GemException {
        DriverManager.setUpBrowser();

    }
    @BeforeAll(order = 2)
    public static void before_all() throws GemException {
        CommonUtils.before_Athena();
    }

}
