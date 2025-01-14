package com.qa.athenaUi.locators;

import io.cucumber.java.zh_cn.那么;
import org.openqa.selenium.By;

public class ArchiveCourseLocators {
    public static By actionsIcon = By.xpath("(//button[contains(@class, 'p-ripple')])[1]");
    public static By archiveCourseOption = By.xpath("//label[text()='Archive Course']");
    public static By archivedName = By.xpath("//input[@id='archived-name']");
    public static By archivedMsg = By.xpath("//div[contains(text(), 'Course Archived Successfully')]");
    public static By archivedCoursesTab = By.xpath("//span[contains(text(), 'Archived')]/parent::a");
    public static By courseSummaryOption = By.xpath("//label[text()='Course Summary']");
    public static By courseSummary = By.xpath("//h6[contains(text(),'Course Summary')]");
//    public static By actionsIconArchived = By.xpath("(//p-button[contains(@class, 'action-icon')])[17]/child::button");
    public static By actionsIconArchived = By.xpath("//div[@id=\"p-tabpanel-2\"]/athena-widget-view/div/div/div[1]/p-card/div/div[2]/div[1]/athena-action/p-button/button\n");
    public static By learnerReportsOption = By.xpath("//label[text()='Learner Reports']");
    public static By learnerReports = By.xpath("//h6[contains(text(), 'Report')]");
    public static By archivedNameInput = By.xpath("//input[@id='archived-name']");
    public static By archiveError = By.xpath("//div[text()='Course Name is already used']");
    public static By editCourse = By.xpath("//label[text()='Edit Course']/parent::div");
    public static By archiveConfirmation = By.xpath("//span[text()='Confirmation']");
    public static By editScreen = By.xpath("//h6[contains(text(), ' Edit Course ')]");
}
