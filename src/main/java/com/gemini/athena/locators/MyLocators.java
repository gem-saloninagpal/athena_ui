package com.gemini.athena.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ConcurrentHashMap;

public class MyLocators {
    public static By usernameField=By.xpath("//input[@formcontrolname='username']");
    public static By passwordField=By.xpath("//input[@formcontrolname='password']");
    public static By dashboard=By.xpath("//h6[text()='Dashboard']");
    public static By loginBtn=By.xpath("//button[@label='LOGIN']");
    public static By sidebar=By.xpath("//p-toolbar//em");
    public static String selectModule="//span[contains(text(),'input')]//parent::a";
    public static String button="//button[@label='input']";
    public static String header="//h6";
    public static By fieldsError=By.xpath("//input//following::small");
    public static By crossIcon=By.xpath("//button[contains(@class,'close')]");

    public static By dropdownIcon=By.xpath("//div[contains(@class,'placeholder')]//following::span[contains(@class,'chevron-down')]//parent::div");
    public static String option="//li[@aria-label='input']//parent::p-multiselectitem";
    public static By inputFields=By.xpath("//input[contains(@class,'fieldsDesign')]");

    public static String switchTab="//span[text()='input']//parent::a";
    public static By learnerFirstName=By.xpath("(//tbody)[3]//td");
    public static By athenaLogo=By.xpath("//div[contains(@class,'gem-image-logo')]");
    public static By popupMsg=By.xpath("//div[contains(@class,'p-toast-detail')]");
    // private static String input;
 //   public static String selectTab="(//li[@role='presentation'])["+(input)+"]//parent::a";
    public static String selectTab(int i) {

        String path = "//li[@role='presentation']["+(i)+"]//parent::a";
        return path;
    }


}

