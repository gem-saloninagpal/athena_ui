package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class TestFlowLocators {

    public static By testOptions=By.xpath("//p-card[@class=\"option-card\"]//p-dropdown");
    public static String selectOption="//p-dropdownitem[@ng-reflect-label='option']";
    public static By questionsForEachUser=By.xpath("//input[@formcontrolname=\"questionCountForEachUser\"]");
    public static By totalMarks=By.xpath("//input[@formcontrolname=\"totalTestMarks\"]");
    public static By ticketCreated=By.xpath("//div//h6[contains(text(),'Server Side')]");
    public static By questions=By.xpath("//i[@ptooltip='Add Question']");
    public static By percentageColumn=By.xpath("//input[@formcontrolname='cutOffPercentage']");
    public static By nextBtn = By.xpath("//button[text()=' Next ']/i");
    public static By loader = By.xpath("//p-progressspinner[@class='p-element']");
}
