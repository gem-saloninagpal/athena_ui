package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class EditTrainingTest_Locators {
    public static By testCheckbox = By.xpath("(//div[@class='p-checkbox-box'])[2]");
    public static By addNewTestBtn = By.xpath("//button[@label='Add New Test']");
    public static By testName = By.xpath("//input[@id='testName']");
    public static By testTag = By.xpath("//input[@id='testTag']");
    public static By duration = By.xpath("//input[@id='inputMask']");
    public static By levelDd = By.xpath("//div[contains(@class, 'tag-width-internal')]");
    public static By levelBeginner =  By.xpath("(//li[contains(@class, 'p-dropdown-item')])[1]");
    public static By description = By.xpath("//textarea[@id='description']");
    public static By nextBtn = By.xpath("//button[contains(@class, 'next-button')]");
    public static By shuffleDd = By.xpath("(//div[contains(@class, 'tag-width')])[2]");
    public static By noOption = By.xpath("//li[@aria-label='No']");
    public static By serverSide = By.xpath("//input[@id='serverSide']/parent::div/parent::div");
    public static By nextBtn1 = By.xpath("//button[text()=' Next ']");
    public static By addToCourseBtn = By.xpath("//button[@label='Add To Course']");
    public static By defaultOrder = By.xpath("//button[@label='Default Order']");
    public static By saveCourseAndPublish = By.xpath("(//button[@icon='pi pi-save'])[2]");
    public static By searchCourse = By.xpath("//input[contains(@class, 'search')]");
    public static By courseActionsIcon = By.xpath("(//button[contains(@class, 'p-ripple')])[1]");
    public static By editCourse = By.xpath("//label[text()='Edit Course']/parent::div");
    public static By archiveNo = By.xpath("//button[@label='No']");
    public static By editTest = By.xpath("//label[text()='Edit Test']/parent::div");
    public static By successfulMsg = By.xpath("//div[contains(text(), 'successfully')]");
    public static By testActions = By.xpath("(//td//child::athena-action//child::button[contains(@class, 'p-ripple')])[11]");
    public static By trainingTests = By.xpath("//span[text()='Training Tests']//parent::a");
    public static By searchTrainingTest = By.xpath("(//input[contains(@placeholder, 'Search')])[3]");
    public static By alreadyAttemptedMsg = By.xpath("(//div[contains(text(), 'already attempted')])[3]");
    public static By trainingTestActionItems = By.xpath("//div[contains(@class, 'action-items')]");
}
