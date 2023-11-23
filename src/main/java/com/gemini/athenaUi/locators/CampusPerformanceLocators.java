package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class CampusPerformanceLocators {

    public static By graph=By.xpath("//*[local-name()='g']/*[local-name()='rect']");
    public static By performanceOnHover=By.xpath("//*[contains(@class,'tooltip')]//tbody//td[2]//b");
    public static By performanceOnTable=By.xpath("//table//tr[1]//td[7]");
}
