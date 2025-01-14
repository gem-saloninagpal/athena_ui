package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class SendCustomMail_Locators {
    public static By templateName = By.xpath("//p-dropdown[@optionlabel='templateName']");
    public static By nameInput = By.xpath("//input[contains(@class, 'p-dropdown-filter')]");
    public static By recipientDD = By.xpath("(//p-multiselect[@optionlabel='name'])[1]");
    public static By recipientInput = By.xpath("//input[contains(@class, 'p-multiselect-filter')]");
    public static By sendBtn = By.xpath("//span[text()='Send']//parent::button");
    public static By toastMsg = By.xpath("//div[contains(text(), 'Sent')]");
    public static By nameItem = By.xpath("//li[contains(@class, 'p-dropdown-item')]");
    public static By recipientItem = By.xpath("//li[contains(@class, 'p-multiselect-item')]");
    public static By loader = By.xpath("//div[@class='p-progress-spinner']");
    public static By subject = By.xpath("//input[@id='mailerSubject']");
    public static By resetBtn = By.xpath("//button[@label='Reset']");
    public static By blankEditor = By.xpath("//div[contains(@class, 'ql-blank')]");
    public static By viewPastMails = By.xpath("//button[@label='View Past Mails']");
    public static By mailingHistory = By.xpath("//h6[text()=' Mailing History ']");
    public static By previewBtn = By.xpath("//span[text()='Preview']//parent::button");
}
