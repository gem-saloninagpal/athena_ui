package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class Role_Management_Locators {

    public static By createRoleBtn=By.xpath("//button//span[text()='Create Role']");

//    public static String roleFields="(//input)[itr]";

    public static By roleName = By.xpath("//input[@id='roleName']");

    public static By roleDesc = By.xpath("//input[@id='roleDescription']");

    public static By roleDisplay = By.xpath("//input[@id='roleDisplayName']");
    public static By permissionExpand = By.xpath("(//div[@role='tablist'])[2]");
    public static By permissionCheckbox = By.xpath("//input[@value='7']");
    public static By submit = By.xpath("//button[@label='Submit']");

    public static By submitBtn=By.xpath("//button//span[text()='Submit']");
    public static By createRole = By.xpath("//button[@label='Create Role']");

    public static String permissionsCheckbox="//div[text()='permissions']/following-sibling::input";

    public static String permissionDropdown="//span[text()='permissionType']/preceding-sibling::span";

    public static By roleList=By.xpath("//tbody[@class='p-datatable-tbody']//td");

//    public static By nameList=By.xpath("(//table[@role='table']//td)[56]");
    public static String name="(//table[@role='table']//td)[itr]";

    public static By nameList=By.xpath("//table[@role='table']//td");

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


    public static By Status=By.xpath("(//table[@role='table']//td)[6]//p-inputswitch");

    public static By StatusInternal=By.xpath("((//table[@role='table'])[2]//td)[5]//p-inputswitch");




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
    public static By roleTest = By.xpath("//td[@ng-reflect-text='testCheckNew2']");
    public static By roleActionsBtn = By.xpath("(//button[contains(@class, 'p-ripple')])[12]");
    public static By editRoleIcon = By.xpath("//i[@class='pi pi-pencil']");
    public static By expandManageTests = By.xpath("(//span[contains(@class, 'p-accordion-toggle-icon')])[1]");
    public static By internalTest = By.xpath("//span[text()=' Internal Test ']");
    public static By evaluateCheckbox = By.xpath("(//input[@type='checkbox'])[9]");
    public static By toastMsg = By.xpath("//div[contains(@class, 'p-toast-summary')]");
    public static By successMsg = By.xpath("//div[text()='Role edited successfully']");
}
