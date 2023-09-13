package com.gemini.athena.locators;

import org.openqa.selenium.By;

public class Tests_TestControl_Locators {
    public static String date="//span[text()='date']";
    public static By testInputFields=By.xpath("//form[@class='ng-untouched ng-pristine ng-invalid']//input");
    public static String calendar="(//input[contains(@id,'calendar')])[itr]";

    public static By dropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");

    public static String option="//span[text()='section']";

    public static By percentageInput=By.xpath("(//form[@class='p-ml-5 p-mr-5 ng-invalid ng-dirty ng-touched']//input)[2]");

    public static By timeInput=By.xpath("(//form[@class='p-ml-5 p-mr-5 ng-invalid ng-dirty ng-touched']//input)[3]");

    public static By addQuestion=By.xpath("//button//span[text()='Add Questions']");
    public static By importRandomBtn=By.xpath("//button//span[text()='Import Random']");

    public static By questionTypeDropdown=By.xpath("//div//span[text()='Question Type']");

    public static By difficultyDropdown=By.xpath("//div//span[text()='Difficulty']");

    public static By levelDropdown=By.xpath("//div//span[text()='Level']");

    public static By noOfQuestionInput=By.xpath("//span[@class='p-inputnumber p-component']//input");

    public static By submitButton=By.xpath("//button[text()=' Submit ']");

    public static String options="//li//span[text()='input']";

    public static By saveButton=By.xpath("//button//span[text()='Save']");

    public static By continueButton=By.xpath("//button//span[text()='Continue']");

    public static By createdTestText=By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr//td)[1]");


    //button//span[text()='Continue']

    //button//span[text()='Save']

//button[text()=' Submit ']
    //div//span[text()='Question Type']
    //div//span[text()='Difficulty']
    //div//span[text()='Level']
    //div//span[@class='p-inputnumber p-component']

    //button//span[text()='Import Random']

    //button//span[text()='Add Questions']

    //label[text()='HH:MM*']
    //label[text()='Cut Off Percentage*']

}
