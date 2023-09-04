package com.gemini.athena.locators;

import org.openqa.selenium.By;

public class LearnerModule_Locators {

    public static By userDropdown=By.xpath("(//div[@class='ng-tns-c114-12 p-dropdown p-component']//span)[2]");

    public static By courseDropdown=By.xpath("(//div[contains(@class, 'p-dropdown-trigger ng-tns')]//span)[2]");

    public static By requiredOption=By.xpath("//span[text()='Learner']");
    public static By catalogPaginator=By.xpath("//span[@class='paginator-text-style']");

    //span[@class='paginator-text-style']
    public static By optionList=By.xpath("//ul[@class='p-dropdown-items ng-tns-c114-12']//span");

    public static By viewCourseBtn=By.xpath("//button//span[text()='View Course']");

    public static By startCourseBtn=By.xpath("//span[text()='Start Course']");

    public static By enrollBtn1=By.xpath("(//span[text()='Enroll'])[2]");



    public static By backBtn=By.xpath("//button[text()=' Back ']");

    public static By resumeBtn=By.xpath("//span[text()='Resume Course']");

    public static By backBtn1=By.xpath("//span[text()='Back']");

    public static By enrollBtn=By.xpath("//span[text()='Enroll']");


    public static By resumeBtn2=By.xpath("(//span[text()='Resume'])[1]");
    public static By completeAndContinueBtn=By.xpath("//button[text()=' Complete and Continue ']");

    public static By moduleList=By.xpath("//div[contains(@class, 'p-accordion-content')]//div[@class='ng-star-inserted']");
    public static By readingModule=By.xpath("//div[contains(@class, 'p-accordion-content')]//img");

    public static By Assignment=By.xpath("//div[contains(@class, 'p-accordion-content')]//i[@class='pi pi-file ng-star-inserted']");


    public static By catalogAssignment=By.xpath("//div[contains(@class, 'p-accordion p-component')]//i[@class='pi pi-file']");
    public static By catalogTest=By.xpath("//div[contains(@class, 'p-accordion p-component')]//i[@class='pi pi-question-circle']");
    //div[contains(@class, 'p-accordion p-component')]//i[@class='pi pi-file']
    //div[contains(@class, 'p-accordion p-component')]//i[@class='pi pi-question-circle']

    //div[contains(@class, 'p-accordion-content')]//i[@class='pi pi-file ng-star-inserted']

    public static By answerArea=By.xpath("//textarea");
    public static By popupDiv=By.xpath("//div[text()='Content Completed Successfully']");

    public static By assignmentPopup=By.xpath("//div[text()='Assignment Completed Successfully']");

    public static By testList=By.xpath("//div//i[@class='pi pi-question-circle ng-star-inserted']");

    public static By downloadCertifcatebtn=By.xpath("//span[text()='Download Certificate']");

    public static By courseCatalogbtn=By.xpath("//span[text()='Course Catalog']");

    public static By courseDiv=By.xpath("//div[@class='p-card-body']");

    public static By testCount=By.xpath("(//div[@class='overlay-text-style'])[2]");

    public static By assignmentCount=By.xpath("(//div[@class='overlay-text-style'])[3]");

    public static By viewCourseBtn1=By.xpath("(//span[text()='View Course'])[1]");

    public static By courseContent=By.xpath("//div[text()='Course Content']");

    public static By courseCatagory=By.xpath("//div[@class='list-container']//div[@class='p-card p-component']//span[@class='category']");

    public static String courseCatagoryLable = "(//div[@class='list-container']//div[@class='p-card p-component']//span[@class='category'])[itr]";

    //div[@class='list-container']//div[@class='p-card p-component']//span[@class='category']

    public static By courseFilterdiv=By.xpath("//ul[contains(@class, 'p-dropdown-items ng-tns')]//li");

    //ul[@class='p-dropdown-items ng-tns-c114-145']//li
    public static By footerRightArrow=By.xpath("//span[@class='p-paginator-icon pi pi-angle-right']");

    public static By completedCourseCount=By.xpath("//div[@class='completed-courses-count']");

    public static By activeCourseCount=By.xpath("//div[@class='active-courses-count']");

    public static By totalCourseCount=By.xpath("//div[@class='total-courses-count']");



    public static String courseArea="(//div[@class='ml-1 mr-1'])[itr]//div[@class='rounded ml-4 mb-3 card-style ng-star-inserted']";

    public static String courseType="(//ul[@class='p-tabview-nav']//li)[itr]";



//span[text()='Completed']
    //span[text()='Ongoing']

   // (//div[@class='ml-1 mr-1'])[itr]
    //(//ul[@class='p-tabview-nav']//li)[itr]
    //div[@class='completed-courses-count']
    //div[@class='active-courses-count']



    //span[text()='Download Certificate']
    //div//i[@class='pi pi-question-circle ng-star-inserted']


    //textarea
    //div[contains(@class, 'p-accordion-content')]//div[@class='ng-star-inserted']
    //div[contains(@class, 'p-accordion-content')]//img

    //button[text()=' Complete and Continue ']



    //span[text()='Back']

    //span[text()='Resume Course']


    //span[text()='Back']

    //span[text()='Start Course']
    //button//span[text()='View Course']
    //ul[@class='p-dropdown-items ng-tns-c114-12']
    //span[text()='Learner']

}

