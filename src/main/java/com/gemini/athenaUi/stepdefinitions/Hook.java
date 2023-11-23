package com.gemini.athenaUi.stepdefinitions;

<<<<<<< HEAD
import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;

public class Hook {
    @Before
    public static void hook() throws GemException {
        DriverManager.setUpBrowser();
    }

}

=======

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;


public class Hook {
 //   static WebDriver driver;
    @Before
    public static void hook() throws GemException {
        DriverManager.setUpBrowser();

    }
//    @BeforeAll(order = 2)
//    public static void before_all() throws GemException {
//        CommonUtils.before_Athena();
//    }


}
>>>>>>> 161a9c45bf9ec7f6f65c2e429421f49e7d3443d9
