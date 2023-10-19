package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class FortisLocators {
    public static By firstQuestion=By.xpath("//athena-question-palette/div[2]/button");
    public static By signInBtn=By.xpath("//button[@label='Sign in']");
    public static By videoNextBtn=By.xpath("//athena-instructions-video//button[@ng-reflect-label='NEXT']");
    public static By SaveNext=By.xpath("//button[@label='Save & Next']");
    public static By quesNumber=By.xpath("//p[@id='question-number']//span");
}

