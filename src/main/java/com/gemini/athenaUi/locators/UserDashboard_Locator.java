package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class UserDashboard_Locator {


    public static By publishBtn=By.xpath("(//span[text()='Save Course & Publish'])[2]");

    public static By actionTakenDropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");


    public static By actionTakenDropdownCourse=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[3]");

    public static String action="//span[text()='input']";

    public static By searchInput=By.xpath("//input[@placeholder='Search by Course Name']");


    public static By searchInputCourse=By.xpath("(//input[@placeholder='Search by Course Name'])[2]");


    public static By searchInputLibrary=By.xpath("(//input[@placeholder='Search by Course Name'])[3]");


    public static By learnerId=By.xpath("(//tr//td[2])[1]");


    public static By courseName=By.xpath("(//tbody//tr//td[1])[1]");


    public static By courseNameInput=By.xpath("(//span[@class=\"p-float-label\"]//input)[1]");


    public static By deleteIcon=By.xpath("//i[contains(@class,'check-icon pi pi-times')]");


    public static By courseFilterInput=By.xpath("//input[@placeholder='Search by Name/Tags']");



    public static String label="//label[text()='input']";


    public static By popupMessage=By.xpath("//div[contains(@class,'p-toast-message-text')]");


    public static By addContentBtn=By.xpath("//button//span[text()='Add Content']");

    public static By addToCourseBtn=By.xpath("//button//span[text()='Add To Course']");

    public static By resetBtn=By.xpath("//button//span[text()='Reset']");


    public static By defaultOrderBtn=By.xpath("//button//span[text()='Default Order']");


    public static By updatePublishBtn=By.xpath("(//button//span[text()='Update Course & Publish'])[2]");


    public static By pageHeading=By.xpath("//h6");


    public static By addContent=By.xpath("//button[@ng-reflect-label=\"Add Content\"]");

    public static By addAssignment=By.xpath("(//i[@ptooltip='Add Assignment'])[1]");
    public static By searchCourse=By.xpath("(//input[contains(@placeholder,'Course Name')])[1]");
    public static By addContentInCourse=By.xpath("(//i[@ptooltip=\"Add Content\"])[1]");
    public static By confirmationDialog=By.xpath("//span[text()='Confirmation']");
    public static By updateAndPublishBtn=By.xpath("(//button[@ng-reflect-label='Update Course & Publish'])[2]");
}
