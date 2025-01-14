package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class TemplateLibrary_Locators {
    public static By createTemplateBtn = By.xpath("//button[@label='Create Template']");
    public static By templateName = By.xpath("//input[@id='templateName']");
    public static By templateSubject = By.xpath("//input[@id='templateSubject']");
    public static By editor = By.xpath("//div[contains(@class, 'ql-editor')]");
    public static By saveBtn = By.xpath("//button[@label='Save']");
    public static By searchBar = By.xpath("//input[@placeholder='Search']");
    public static By searched = By.xpath("(//td[@id='font-size'])[1]");
    public static By errorMsg = By.xpath("//small[contains(@class, 'p-error')]");
    public static By resetBtn = By.xpath("//button[@label='RESET']");
    public static By error2 = By.xpath("(//small[contains(@class, 'p-error')])[2]");
    public static By warningPopup = By.xpath("//span[text()='Unsaved Changes']");
    public static By noBtn = By.xpath("//span[contains(text(), 'No')]//parent::button");
    public static By templateNameContent = By.xpath("//label[@for='templateName']");
    public static By actionsIcon = By.xpath("//span[contains(@class, 'pi-ellipsis')]//parent::button");
    public static String actionsItem = "//label[text()='input']";
    public static By editScreen = By.xpath("//h6[text()=' Edit Mail Template ']");
    public static By previewScreen = By.xpath("//span[contains(@class, 'p-dialog-title')]");
    public static By noRecords = By.xpath("//div[text()='No Records Found!']");
    public static By yesBtn = By.xpath("//span[text()='Yes']//parent::button");
}
