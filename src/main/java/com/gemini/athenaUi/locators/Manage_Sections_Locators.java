package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class Manage_Sections_Locators {


    public static By sectionNameTextArea=By.xpath("//textarea[@formcontrolname='sectionTypeName']");

    public static By sectionDescriptionTextArea=By.xpath("//textarea[@formcontrolname='sectionTypeDescription']");

    public static By addBtn=By.xpath("//button//span[text()='ADD']");


    public static By sectionNames=By.xpath("//div[@class='p-scrollpanel-content']//h6");

    public static By editIconList=By.xpath("//i[@id='pencil']");

    public static String editIcon="(//i[@id='pencil'])[itr]";

    public static By updateBtn=By.xpath("//button//span[text()='UPDATE']");


    public static By yesBtn=By.xpath("//button//span[text()='Yes']");


    public static By deleteIconList=By.xpath("//i[@id='trash']");

    public static String deleteIcon="(//i[@id='trash'])[itr]";

    public static By firstSectionHeading=By.xpath("//div[@class='rounded1 mb-4 ng-star-inserted']//h6");

    public static By firstSectionDesc=By.xpath("//div[@class='sectionDesc ml-2']");

    //div[@class='sectionDesc ml-2']



    //div[@class='rounded1 mb-4 ng-star-inserted']//h6

   // trash

//button//span[text()='Yes']



}
