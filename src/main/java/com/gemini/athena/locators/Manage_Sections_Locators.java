package com.gemini.athena.locators;

import org.openqa.selenium.By;

public class Manage_Sections_Locators {


    public static By sectionNameTextArea=By.xpath("//textarea[@formcontrolname='sectionTypeName']");

    public static By sectionDescriptionTextArea=By.xpath("//textarea[@formcontrolname='sectionTypeDescription']");

    public static By addBtn=By.xpath("//button//span[text()='ADD']");


    public static By sectionNames=By.xpath("//div[@class='p-scrollpanel-content']//h6");


    //div[@class='p-scrollpanel-content']//h6

}
