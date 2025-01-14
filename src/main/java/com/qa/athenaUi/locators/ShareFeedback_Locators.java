package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class ShareFeedback_Locators {
    public static By feedbackIcon = By.xpath("//div[@ptooltip=\"User Feedback\"]");
    public static By feedbackContainer = By.xpath("//span[text()='Share Your Feedback']");
    public static By feedbackSubject = By.xpath("//input[@id='subject']");
    public static By categoryDD = By.xpath("(//p-dropdown[@id='ddown1'])[1]");
    public static By selectCategory = By.xpath("//span[text()='Design']/parent::li");
    public static By featuresDD = By.xpath("(//p-dropdown[@id='ddown1'])[2]");
    public static By selectFeature = By.xpath("//span[text()='Tests']/parent::li");
    public static By feedbackDescription = By.xpath("//textarea[@id='description']");
    public static By submitBtn = By.xpath("//button[contains(@class, 'submit')]");
    public static By successMsg = By.xpath("//div[contains(text(), 'submitted')]");
    public static By disabledSubmitBtn = By.xpath("//button[@disabled and contains(@class, 'submit')]");
    public static By chooseSS = By.xpath("//input[@type='file']");
    public static By uploadSS = By.xpath("//span[text()='Upload']/parent::button");
}
