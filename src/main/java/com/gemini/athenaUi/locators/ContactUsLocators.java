package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class ContactUsLocators {
    public static By contactUsTab = By.xpath("(//a[@class='contact-us-style'])[2]");
    public static By name = By.xpath("//input[@id='name']");
    public static By email = By.xpath("//input[@id='email']");
    public static By contact = By.xpath("//input[@id='contactNumber']");
    public static By desc = By.xpath("//textarea[@id='description']");
    public static By submitBtn = By.xpath("//button[@label='Submit']");
    public static By toast_msg = By.xpath("//div[contains(@class, 'p-toast-detail')]");
    public static By logo = By.xpath("(//img[@alt='athena logo'])[2]");
    public static By submit_disabled = By.xpath("//button[@disabled]");
}
