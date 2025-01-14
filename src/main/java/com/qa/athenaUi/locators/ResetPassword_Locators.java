package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class ResetPassword_Locators {
    public static By forgotPassword = By.xpath("//a[contains(text(),'Forgot')]");
    public static By emailInput = By.xpath("//input[@id='email']");
    public static By resetBtn = By.xpath("//button[@label='RESET']");
    public static By successMsg = By.xpath("//div[contains(text(), 'successfully')]");
    public static By errorMsg = By.xpath("//small[contains(text(), 'Enter Valid Email')]");
    public static By userNotFoundMsg = By.xpath("//div[contains(text(), 'User not found')]");
    public static By error = By.xpath("//small[@class='p-error']");
}
