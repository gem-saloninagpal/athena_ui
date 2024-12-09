package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class CampusPerformanceLocators {

    public static By graph=By.xpath("//*[local-name()='g']/*[local-name()='rect']");
    public static By performanceOnHover=By.xpath("//*[contains(@class,'tooltip')]//tbody//td[2]//b");
    public static By performanceOnTable=By.xpath("//table//tr[1]//td[7]");
    public static By name=By.xpath("//p-tabpanel[@header=\"Completed-Course\"]//table//tr//td[1]");
    public static By email=By.xpath("//p-tabpanel[@header=\"Completed-Course\"]//table//tr//td[3]");
    public static By nameEmailSearchbox=By.xpath("//input[@placeholder='Name/Email']");
    public static By locationDisplayedOnTable=By.xpath("//p-tabpanel[@header=\"Completed-Course\"]//tbody[@class='p-datatable-tbody']/tr/td[7]");
    public static By selectedCategoryDisplayedOnTable=By.xpath("//p-tabpanel[@header=\"Completed-Course\"]//tbody[@class='p-datatable-tbody']/tr/td[4]");
    //    public static By expandStartDateCalendar=By.xpath("//p-calendar[@placeholder='Start Date']//button");
    public static By expandStartDateCalendar=By.xpath("//input[@placeholder='Start Date']");

    public static By selectDate=By.xpath("(//div[contains(@class,'p-datepicker')]//td[contains(@class,'ng-star-inserted')])[10]");
    public static By expandEndDateCalendar=By.xpath("//p-calendar[@placeholder='End Date']//button");
    public static By selectedStartDate=By.xpath("//p-calendar[@placeholder='Start Date']");
    public static By selectedEndDate=By.xpath("//p-calendar[@placeholder='End Date']");
    public static By recordsDisplayed=By.xpath("//athena-completed-course//tbody//tr[@class='ng-star-inserted']");
    public static By startDates=By.xpath("//athena-completed-course//tr[@class='ng-star-inserted']//td[8]");
    public static By endDates=By.xpath("//athena-completed-course//tr[@class='ng-star-inserted']//td[9]");
    public static By lastname=By.xpath("//p-tabpanel[@header=\"Completed-Course\"]//table//tr//td[2]");
}
