package com.qa.gemini.Locators;

import org.openqa.selenium.By;

public class TestControl_Locator {


    public static By campusDropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");

    public static By campusList=By.xpath("//ul[@role='listbox']//li");

    public static String status="(//div[contains(@class,'p-inputswitch p-component')]//input)[itr]";

    public static By rightPaginator=By.xpath("//span[@class='p-paginator-icon pi pi-angle-right']/..");

    public static String name="(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr[itr]//td)[1]";

    public static By statusList=By.xpath("//div[contains(@class,'p-inputswitch p-component')]//input");

    public static By activeTestList=By.xpath(" //div[@class='p-scrollpanel-content']//div[@class='rounded m-2 sections ng-star-inserted']//p[@class='font-weight-bold mt-2 test-name']");


    public static By startBtn=By.xpath("//button[text()=' Start Test ']");
    //button[text()=' Start Test ']
    //div[@class='p-scrollpanel-content']//div[@class='rounded m-2 sections ng-star-inserted']//p[@class='font-weight-bold mt-2 test-name']



    //span[@class='p-paginator-icon pi pi-angle-right']


    //div[contains(@class,'p-inputswitch p-component')]//input


}
