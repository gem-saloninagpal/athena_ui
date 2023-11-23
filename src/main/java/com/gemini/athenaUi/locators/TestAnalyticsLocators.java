package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class TestAnalyticsLocators {
    public static By activeStats=By.xpath("//*[local-name()='g' and contains(@class,'xaxis')]/*[local-name()='text']");
    public static By events=By.xpath("//p-scrollpanel//div[contains(@class,'card')]");
    public static By ongoingEventsDropdown=By.xpath("//p-dropdown[@placeholder=\"Select Event\"]");
    public static By selectUpcomingEvents=By.xpath("//p-dropdownitem//span[text()='Upcoming']");
    public static By campus=By.xpath("//table//tr[1]//td[2]");
    public static By passCount=By.xpath("//table//tr[1]//td[3]");
    public static By passedCandidates=By.xpath("//div[contains(@class,'card hover')]//div/p[3]");
}
