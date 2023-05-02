package com.gemini.athena.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ConcurrentHashMap;

public class MyLocators {
    public static By usernameField=By.xpath("//input[@formcontrolname='username']");
    public static By passwordField=By.xpath("//input[@formcontrolname='password']");
    public static By dashboard=By.xpath("//h6[text()='Dashboard']");
    public static By loginBtn=By.xpath("//button[@label='LOGIN']");
    public static By sidebar=By.xpath("//p-toolbar//em");
    public static String selectModule="//span[contains(text(),'input')]//parent::a";
    public static String button="//button[@label='input']";
    public static String header="//h6";
    public static By fieldsError=By.xpath("//input//following::small");
    public static By crossIcon=By.xpath("//button[contains(@class,'close')]");

    public static By dropdownIcon=By.xpath("(//span[contains(@class,'chevron-down')]//parent::div)[2]");
    public static By dropdownIcon1=By.xpath("//div[contains(@class,'placeholder')]//following::span[contains(@class,'chevron-down')]//parent::div");
    public static String option="//li[@aria-label='input']//parent::p-multiselectitem";
    public static By inputFields=By.xpath("//input[contains(@class,'fieldsDesign')]");

    public static String switchTab="//span[text()='input']//parent::a";
    public static By learnerFirstName=By.xpath("(//tbody)[3]//td");
    public static By athenaLogo=By.xpath("//div[contains(@class,'gem-image-logo')]");
    public static By popupMsg=By.xpath("//div[contains(@class,'p-toast-detail')]");
    public static By passwordFormatError=By.xpath("//input[@id='password']//parent::span//following::small");
    public static By selectCampusDropdown=By.xpath("//p-dropdown[@formcontrolname='campus']//span[contains(@class,'chevron-down')]//parent::div");
    public static By selectCampus=By.xpath("//span[text()='Other, Other']//parent::li//parent::p-dropdownitem");
    public static By selectExperienceLevelDropdown=By.xpath("//label[contains(text(),'Experience Level')]//preceding::div[1]");
    public static String selectLevel="//span[text()='input']//parent::li";
    public static By role=By.xpath("//span[text()='Role']//parent::td");
    public static By navbarDropdown=By.xpath("//button[@icon='pi pi-caret-down']");
    public static By logoutOption=By.xpath("//span[text()='Logout']//parent::a");
    public static By navbarRolesDropdown=By.xpath("(//div[@role='button'])[1]");

    public static By rolesOption=By.xpath("//div[contains(@class,'p-dropdown p-component')]//span[@class='ng-star-inserted']");
    public static By actionsIcon=By.xpath("(//tbody)[2]//span[contains(@class,'pi pi-ellipsis')]//parent::button");
    public static By editProfile=By.xpath("//label[text()='Edit Profile']//parent::div");
    public static By enableEditing=By.xpath("//span[@class='p-inputswitch-slider']");
    public static By rolesField=By.xpath("//p-multiselect[@formcontrolname='roleDetails']//input");
    public static By userStatus=By.xpath("//p-togglebutton//span[@class='p-button-label']");
    public static By statusButton=By.xpath("//p-togglebutton");
    public static By employeeActionsIcon=By.xpath("(//span[contains(@class,'pi pi-ellipsis')]//parent::button)");
    public static By yesBtn=By.xpath("//span[text()=' Yes ']//parent::button");
    public static By currentRole=By.xpath("//div[contains(@class,'multiselect-label')]//div");
    public static By rolesDropdown=By.xpath("//p-multiselect[@formcontrolname=\"roleDetails\"]//div");
    public static By candidateDashboard=By.xpath("//span[text()='Dashboard']");
    public static By infoDropdown=By.xpath("//button[@type='button']");
    public static By infoOptions=By.xpath("//p-slidemenusub//a//span[2]");
    public static By changePassword=By.xpath("//span[text()='Change Password']//parent::a");
    public static By dialogBox=By.xpath("//span[text()='Change Password']//parent::div[contains(@class,'p-dialog')]");
    public static By email=By.xpath("//input[@type='email']");
    public static By tabSelected=By.xpath("//li[contains(@class,'highlight')]//span");
    public static String numTest="//p-tabpanel[@ng-reflect-header='input']//div[@class='card hover-effect']";
    public static String testTab=   "//span[text()='input']//parent::a";
    public static By message=By.xpath("//h5");

    // private static String input;
 //   public static String selectTab="(//li[@role='presentation'])["+(input)+"]//parent::a";
    public static String selectTab(int i) {

        String path = "//li[@role='presentation']["+(i)+"]//parent::a";
        return path;
    }


}

