package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class Utils_Locators {
    public static By loader = By.xpath("//div[@class='loader-circle ng-star-inserted']");

    public static By roleSpan = By.xpath("//p-dropdown[@optionlabel='roleDisplayName']//span");

    public static String tab="//a[@role='tab']//span[text()='tab1']";

    public static String input="//input[@id='label']";

    public static By unCheckedCheckbox = By.xpath("//span[@class='p-checkbox-icon']");
    public static By checkedCheckbox = By.xpath("//span[@class='p-checkbox-icon pi pi-check']");



    public static By unCheckedCheckbox2 = By.xpath("(//span[@class='p-checkbox-icon'])[2]");
    public static By downloadBtn = By.xpath("//button//span[@class='p-button-icon pi pi-download']");
    public static By uploadBtn = By.xpath("//button//span[@class='p-button-icon pi pi-upload']");

    public static String button1="//button//span[text()='buttonName']";

    public static String button="//button[@label='buttonName']";



    //button[@label='Reset']

    //button//span[text()='Reset']




}
