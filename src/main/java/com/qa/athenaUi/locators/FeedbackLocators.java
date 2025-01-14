package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class FeedbackLocators {
    public static By viewIcon = By.xpath("(//i[contains(@class, 'eye')])[1]");
    public static By feedbackPage = By.xpath("//div[@class='bug-heading']");
    public static By deleteIcon = By.xpath("(//i[contains(@class, 'trash')])[1]");
    public static By deletedMsg = By.xpath("//div[contains(text(), 'deleted')]");
    public static By editIcon = By.xpath("//i[contains(@class, 'pencil')]");
    public static By statusDropdown = By.xpath("(//div[@class='p-dropdown-trigger'])[2]");
    public static By closedStatus = By.xpath("//li[@aria-label='Closed']");
    public static By saveBtn = By.xpath("//span[text()='Save']/parent::button");
    public static By successMsg = By.xpath("//div[contains(text(), 'Success')]");
}
