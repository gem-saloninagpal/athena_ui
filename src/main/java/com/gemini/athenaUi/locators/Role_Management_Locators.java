package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class Role_Management_Locators {

    public static By createRoleBtn=By.xpath("//button//span[text()='Create Role']");

    public static String roleFields="(//input)[itr]";

    public static By submitBtn=By.xpath("//button//span[text()='Submit']");

    public static String permissionsCheckbox="//div[text()='permissions']/following-sibling::input";

    public static String permissionDropdown="//span[text()='permissionType']/preceding-sibling::span";

    public static By roleList=By.xpath("//tbody[@class='p-datatable-tbody']//td");

//    public static By nameList=By.xpath("(//table[@role='grid']//td)[56]");
    public static String name="(//table[@role='grid']//td)[itr]";

    public static By nameList=By.xpath("//table[@role='grid']//td");

    public static String editIcon="(//span[contains(@class,'pi pi-ellipsis-v')])[itr]";

    public static By editIconInternal=By.xpath("(//span[contains(@class,'pi pi-ellipsis-v')])[11]");


    public static By editIconTraining=By.xpath("(//span[contains(@class,'pi pi-ellipsis-v')])[11]");


    public static By editRole=By.xpath("//label[text()='Edit Role']");

    public static By editProfile=By.xpath("//label[text()='Edit Profile']");

    public static By removeRole=By.xpath("//label[text()='Remove Role']");



    public static By roleDropdown=By.xpath("//span[contains(@class,'pi pi-chevron-down')]");

    public static String selectedRole="//li//span[text()='role']";


    public static String permissionsList="//div[@aria-labelledby='p-accordiontab-itr']//div[@class='pl-4 ml-4']//div";


    //div[@aria-labelledby='p-accordiontab-itr']//div[@class='pl-4 ml-4']//div


    public static By status=By.xpath("(//table[@role='grid']//td)[6]//p-inputswitch");

    public static By statusInternal=By.xpath("((//table[@role='grid'])[2]//td)[5]//p-inputswitch");




    public static By actionList=By.xpath("//div[contains(@class,'p-overlaypanel-content')]//div//label[2]");



    public static By createCourseBtn=By.xpath("//button//span[text()='Create Course']");

    public static By addNewBtn=By.xpath("//button//span[text()='Add New']");


    public static By createBatchBtn=By.xpath("//button//span[text()='Create Batch']");

    public static By registerCampusBtn=By.xpath("//button//span[text()='Register Campus']");

    public static By registerBtn=By.xpath("//button//span[text()='Register']");

    public static String reqBtn="//button//span[text()='input']";




    public static By yesBtn=By.xpath("//button//span[text()='Yes']");

    public static By userInput=By.xpath("//input[@placeholder='Name/Email']");


    public static By slider=By.xpath("//span[@class='p-inputswitch-slider']");


    public static By userRoleDropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");



    public static By input=By.xpath("//input[@role='textbox']");


    public static By checkbox=By.xpath("//span[contains(@class,'p-checkbox-icon')]/parent::div");


    public static By updateBtn=By.xpath("//button//span[text()='Update']");


}
