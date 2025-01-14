package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class Wishlist_Locators {
    public static By courseCatalog = By.xpath("//button[@label='Course Catalog']");
    public static By searchBar = By.xpath("//input[contains(@class, 'search')]");
    public static By addToWishlist = By.xpath("//button[@label='Add to Wishlist']");
    public static By addedToWishlist = By.xpath("//button[@label='Added to Wishlist']");
    public static By wishlist = By.xpath("//button[@ptooltip=\"View Wishlist\"]");
    public static By wishlist_course = By.xpath("//b[@title=\"wishlist_regression\"]");
    public static By removeCourse = By.xpath("//button[@label='Remove Course']");
    public static By yesOption = By.xpath("//button[@ng-reflect-label='Yes']");
    public static By successMsg = By.xpath("//div[contains(@class, 'p-toast-detail') and contains(text(), 'Removed')]");
    public static By noRecords = By.xpath("//h5[text()='No Records Found!']");
    public static By backBtn = By.xpath("//button[@label='Back']");
    public static By enrollBtn = By.xpath("(//button[@label='Enroll'])[1]");
    public static By enrolledCourse = By.xpath("(//b[contains(@class, 'name')])[1]");
}
