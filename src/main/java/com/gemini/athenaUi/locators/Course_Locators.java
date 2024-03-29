package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class Course_Locators {
    public static By sidebar=By.xpath("//p-toolbar//em");
    public static By navbarDropdown=By.xpath("//button[@icon='pi pi-caret-down']");

    public static By profile=By.xpath("//span[text()='Profile']");
    public static By updatedDate=By.xpath("(//tbody[@class='p-datatable-tbody']//tr[1]//td)[7]");

    public static By updateBtn=By.xpath("//button//span[text()='Update Date']");

    public static By changeDateBtn=By.xpath("//button//span[text()='Change Date Selected']");

    public static By userName=By.xpath("//div[@class='upper-card-value']");
    public static By ownerName=By.xpath("//div[contains(@class,'p-multiselect-token')]//span");
    public static By treeList=By.xpath("//div[@class='p-steps p-component p-readonly']//ul//li");
    public static String tree="(//div[@class='p-steps p-component p-readonly']//ul//li//span[@class='p-steps-title ng-star-inserted'])[itr]";
    public static By checkboxs=By.xpath("//div[@class='p-checkbox-box']");
    public static By courseInputFields=By.xpath("//form[@class='ng-untouched ng-pristine ng-invalid']//input");

    public static String option="//li[@aria-label='input']";
    public static String dropdownIcon="(//span[contains(@class,'chevron-down')]//parent::div)[itr]";
    public static By courseDescription=By.xpath("//textarea");
    public static By addContentTagInput=By.xpath("//input[@type='search']");
    public static String addContentTableRow="(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[1]//tr[itr]//td[1]";
    public static By addIcon=By.xpath("//i[contains(@class,'pi pi-plus')]");

    public static By addIconEdit=By.xpath("(//i[contains(@class,'pi pi-plus')])[2]");
    public static By deleteIcon=By.xpath("//i[contains(@class,'pi pi-trash')]");

    public static String popup="//div[text()='input']";
    public static By addToCourseBtn=By.xpath("//span[text()='Add To Course']//parent::button");

    public static By addAssignmentBtn=By.xpath(" //button//span[text()='Add New Assignment']");

    public static String button="//span[text()='input']//parent::button";
    public static By addToCourseDiv=By.xpath("//div[text()='No Records Found!!']");

    public static By courseSummaryDiv=By.xpath("//div[text()=' Move contents here to select the order! ']");

    public static By courseTypeDropdown=By.xpath(" (//span[contains(@class,'chevron-down')]//parent::div)[4]");
    public static By draftOrPublishDropdown=By.xpath(" (//span[contains(@class,'chevron-down')]//parent::div)[5]");

    public static String dropdownValue="//span[text()='type']";



    public static String heading="//h6[text()='input']";

    public static By test=By.xpath("//u[text()='Tests']");

    public static By eyeIcon=By.xpath("//i[@class='pi pi-eye mr-3']");

    public static By detailedReportBtn=By.xpath("//button//span[text()='Detailed Report']");

    //button//span[text()='Detailed Report']

    //i[@class='pi pi-eye mr-3']

    //u[text()='Tests']

    //h6[text()='input']
    public static By actionHeading=By.xpath("//h6");
    public static By draftedCourse=By.xpath("//div[@class='name-style control-overflow longer-name']");

    public static By editIcon=By.xpath("//span[contains(@class,'pi pi-ellipsis')]");

    public static By editOption=By.xpath("//label[text()='Edit']");

    public static By publishButton=By.xpath("(//button//span[text()='Update Course & Publish'])[2]");

    public static By loadingIcon=By.xpath("//*[@class='p-progress-spinner-svg']");

    public static By courseNameInput=By.xpath("//input[@id='courseName']");
    public static By checkedAssignmentCheckbox=By.xpath("//span[contains(@class,'pi pi-check')]");
    public static By saveAsDraftButton=By.xpath("(//button//span[text()='Save As Draft'])[2]");

    public static String adminOption="//label[text()='input']//parent::div";

    public static String userOption="//span[text()='input']//parent::li";

    public static String requiredOption="//li//span[text()='input']";

    //li//span[text()='input']

    public static By nameFilterInput=By.xpath("//input[@placeholder='Name/Email']");

    public static By courseFilterInput=By.xpath("//input[@placeholder='Search Courses']");

    public static By userDropdown=By.xpath("//div[contains(@class,'p-dropdown-trigger')]//span");

    public static By courseFilterTagInput=By.xpath("//input[@placeholder='Search by Name/Tags']");

    public static By nameTagFilterInput=By.xpath("//input[@placeholder='Search by Name']");

    public static By contentsReport=By.xpath("//u[text()='Contents']");

    public static By assignmentReport=By.xpath("//u[text()='Assignments']");

    public static By contentStatus = By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//tr//td)[14]");

    public static By assignmentStatus = By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//tr//td)[24]");

    public static By reattemptLabel=By.xpath("//label[text()='Reattempt Course']");

    public static By courseList=By.xpath("(//div[@class='p-tabview-panels']//div[@class='mr-1'])[1]//div[contains(@class,'p-card-title')]");

    public static By rightPaginator=By.xpath("//button[contains(@class,'p-paginator-next')]");

    public static By leftPaginator=By.xpath("//button[contains(@class,'p-paginator-first')]");

    //button[contains(@class,'p-paginator-first')]
    public static By listViewBtn=By.xpath("//button//i[@class='pi pi-list']");

    public static By firstCourseName=By.xpath("//div[@class='mr-1']//div[@class='p-card-title ng-star-inserted']");

    //div[@class='mr-1']//div[@class='p-card-title ng-star-inserted']
    public static By viewList=By.xpath("(//div[@class='list-container'])[1]//div[@class='title name-style control-overflow']");

    public static By inActiveBtn=By.xpath("//span[@class='p-inputswitch-slider']");

    public static By inActiveDiv=By.xpath("//div[contains(@class,'inactive ng-star-inserted')]");

    public static By activeDropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[3]");


    public static By inActiveOption=By.xpath("//span[text()='Inactive']");
    public static By viewAsLearnerBtn=By.xpath("//span[text()='View as learner']");

    public static By courseSummaryAssignmentList=By.xpath("(//tbody[@class='p-datatable-tbody']//tr)[2]");

    public static By courseStatusList=By.xpath("//tbody[@class='p-datatable-tbody']//tr[1]//td[7]//div[contains(@class,'text-style')]");

    public static String courseContent="(//tbody[@class='p-datatable-tbody']//tr[1]//td[7]//div[contains(@class,'text-style')])[itr]";

    public static By courseStatus=By.xpath("(//tbody[@class='p-datatable-tbody'])//tr[1]//td[5]");

    public static By contentsStatusUnattempted=By.xpath("(//tbody[@class='p-datatable-tbody'])[2]//td[4]");

    public static By contentsStatusCompleted=By.xpath("(//tbody[@class='p-datatable-tbody'])[2]//td[5]");
    public static By assignmentsStatus=By.xpath("(//tbody[@class='p-datatable-tbody'])[2]//td[6]");


    public static By emailInput=By.xpath(" //input[@placeholder='Name/Email']");

    public static By backBtn=By.xpath("//div//button[text()=' Back ']");

    public static By backBtn1=By.xpath("//div[text()='Back']");


    //div[text()='Back']


    //div//button[text()=' Back ']

    public static By statusDropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[4]");
    public static By categoryDropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");

    public static By selectedCategoryDropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[3]");

    public static By calendarIcon=By.xpath("//img[@class='calender-icon']");

    public static String calendar="(//button[contains(@class,'p-datepicker-trigger')])[itr]";



    //img[@class='calender-icon']

    //input[@placeholder='Name/Email']


    //tbody[@class='p-datatable-tbody']//tr[1]//td[7]//div[contains(@class,'text-style')]





}
