package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AssignLearnerInBatchLocators {

    public static By assignLearner=By.xpath("//i[@ptooltip='Assign']");
    public static By assignedLearnerCount=By.xpath("//div[contains(text(),'Learners assigned')]//span");
    public static By searchbox=By.xpath("//input[@placeholder='Name/Email']");
    public static By unassignLearner=By.xpath("//i[@ptooltip='Remove']");;
    public static By selectUnassignedCheckbox=By.xpath("(//td[@ng-reflect-text=\"Unassigned\"]//parent::tr//p-tablecheckbox)[1]");
    public static By nextPage=By.xpath("//span[contains(@class,'angle-right')]//parent::button");
    public static By selectAssignedCheckbox=By.xpath("(//td[@ng-reflect-text=\"Assigned\"]//parent::tr//p-tablecheckbox)[1]");
    public static By rows=By.xpath("//tbody[@class=\"p-datatable-tbody\"]/tr/td[6]");
    public static By status=By.xpath("//span[text()='Status']//parent::td//span[2]");
    public static By expandSelectedCategory=By.xpath("//p-dropdown[contains(@class,'selected-category')]");

}
