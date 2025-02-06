package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class Leaderboard_Locators {
    public static By courses_screen = By.xpath("//div[@class='courses-container']");
    public static By arrow_icon = By.xpath("//div[@class='diamond-content']");
    public static By leaderboard_heading = By.xpath("//span[text()='LEADERBOARD']");
    public static By leaderboard_collapsed = By.xpath("//div[contains(@class, 'collapsed-view')]");
    public static By leaderboard_items = By.xpath("//div[contains(@class, 'leaderboard-item')]");
    public static By score1 = By.xpath("(//div[@class='user-score']/span)[1]");
    public static By score2 = By.xpath("(//div[@class='user-score']/span)[2]");
}
