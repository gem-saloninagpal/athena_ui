package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class MyLocators {
    public static By usernameField=By.xpath("//input[@formcontrolname='username']");
    public static By passwordField=By.xpath("//input[@formcontrolname='password']");

    public static By dashboard=By.xpath("//h6[contains(text(),'User Dashboard')]");
    public static By loginBtn=By.xpath("//button[@label='LOGIN']");
    public static By sidebar=By.xpath("//p-toolbar//em");
    public static String selectModule="//span[text()='input']//parent::a";
    public static String button="//button[@label='input']";
    public static By userDashboard=By.xpath("//h6[text()=' User Dashboard ']");

    public static String backBtnIcon="//div[text()='input']";

    public static String header="(//h6[contains(@class,'text')])[1]";
    public static By fieldsError=By.xpath("//input//following::small");
    public static By crossIcon=By.xpath("//button[contains(@class,'close')]");
    public static By dropdownIcon=By.xpath("(//span[contains(@class,'chevron-down')]//parent::div)[2]");
    public static By fieldsDropdown=By.xpath("//div[@fxlayout='row wrap']//span[contains(@class,'chevron-down')]//parent::div");
    public static By dropdownIcon1=By.xpath("//div[contains(@class,'placeholder')]//following::span[contains(@class,'chevron-down')]//parent::div");
    public static String option="//span[text()='input']//parent::li";
    public static String option1="//span[contains(text(),'input')]//parent::li//parent::p-multiselectitem";

    public static By crossBtn=By.xpath("//span[text()='Close']");

    public static By inputFields=By.xpath("//input[contains(@class,'fieldsDesign')]");

    public static String switchTab="//span[text()='input']//parent::a";
    public static By learnerFirstName=By.xpath("(//tbody)[3]//td");
    public static By athenaLogo=By.xpath("//div[contains(@class,'gem-image-logo')]");
    public static By popupMsg=By.xpath("//div[contains(@class,'p-toast-detail')]");
    public static By passwordFormatError=By.xpath("//input[@id='password']//parent::span//following::small");

    public static By passwordFormatError1=By.xpath("//input[@id='password']//following::small");

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
    public static By learnerActionsIcon=By.xpath("(//tbody)[3]//span[contains(@class,'pi pi-ellipsis')]//parent::button");
    public static By editProfile=By.xpath("//label[text()='Edit Profile']//parent::div");
    public static By enableEditing=By.xpath("//span[@class='p-inputswitch-slider']//parent::div");
    public static By enableEditing1=By.xpath("//p-inputswitch[@class='prime-switch']");
    public static By rolesField=By.xpath("//p-multiselect[@formcontrolname='roleDetails']//input");
    public static By rolesField1=By.xpath("//p-multiselect[@formcontrolname='roleDetails']");
    public static By userStatus=By.xpath("//p-togglebutton//span[@class='p-button-label']");
    public static By statusButton=By.xpath("//p-togglebutton");
    public static By employeeActionsIcon=By.xpath("(//span[contains(@class,'pi pi-ellipsis')]//parent::button)");
    public static By yesBtn=By.xpath("//span[contains(text(),'Yes')]//parent::button");
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
    public static String message="//p-tabpanel[@header='input']//h5";
    public static String profile="//span[text()='input']//parent::a";
    public static String passwordFields="//label[text()='fieldName']//following::input";
    public static By confirmPassword=By.xpath("//label[text()='Confirm Password']//following::input");
    public static By testDate=By.xpath("//b[text()='Date']//parent::p");
    public static By startTest=By.xpath("//button[contains(text(),'Start Test')]");
    public static By instructionsVideo=By.xpath("//span[text()='Instructions Video']");
    public static By testSummary=By.xpath("//h4[text()='Summary']");
    public static By reportColumns=By.xpath("//tr[@class=\"ng-star-inserted\"]//th");
    public static String testName="//h6[@title='name']//following::button[contains(text(),'Start Test')][1]";
    public static String sections="//h6[contains(text(),'input')]//parent::div";
    public static By sectionHeading=By.xpath("//h4[text()='Sections']");
    public static By instructionsCheckbox=By.xpath("//div[@class='p-checkbox-box']");
    public static By startTestDialog=By.xpath("//span[contains(@class,'confirm-dialog')]");
    public static By questionsscreen=By.xpath("//div//athena-questions-screen");

    public static String pageNavigate="//a[contains(@href,'input')]";
    public static String nextBtn="(//button[@label='input'])[2]";
    public static String sectionSelected="//p[contains(text(),'input')]";
    public static By sectionsDropdown=By.xpath("//p-dropdown[@placeholder='Select Category']");
    public static String sectionOptions="//span[text()='input']//parent::li//parent::p-dropdownitem";
    public static By textarea=By.xpath("//div//textarea");
    public static By selectOption=By.xpath("//p-radiobutton[@name='groupname']");
    public static By paletteBtn=By.xpath("//athena-question-palette//button");

    public static By mcqOptions=By.xpath("(//p-radiobutton//input[@type='radio'])[1]");

    public static By checkbox=By.xpath("//input[@type='checkbox']");
    public static By selectCheckbox=By.xpath("//input[@type='checkbox']");
    public static By totalQuestions=By.xpath("//athena-question-palette//button");
    public static By totalQuesCount=By.xpath("//h5[text()='Total Questions']//following::p");
    public static By attemptedQuesCount=By.xpath("//h5[text()='Attempted']//following::p");
    public static By unattemptedQuesCount=By.xpath("//h5[text()='Unattempted']//following::p");;
    public static By totalSections=By.xpath("//ul[@role='listbox']//p-dropdownitem");
    public static By leftArrowBtn=By.xpath("//button[contains(@ng-reflect-icon,'left')]");
    public static By sectionName=By.xpath("//athena-questions-screen//div//p[@id='header']");
    public static By questionNum=By.xpath("//div[contains(@class,'question-number')]//span");
    public static By rightKey=By.xpath("//button[contains(@icon,'right')]");
    public static String testSubmitButton="//button[text()='input']";
    public static By backToDashboard=By.xpath("//p-button[@label='Back To Dashboard']//button");
    public static String completedTest="//div[contains(text(),'input')]";
    public static By employeeFirstName=By.xpath("(//tbody)[1]//td");
    public static By candidateFirstName=By.xpath("(//tbody)[2]//td");
    public static By oldPasswordField=By.xpath("//input[@formcontrolname='oldPassword']");
    public static By contentFields=By.xpath("//span[@class='p-float-label']//input");
    public static By listbox=By.xpath("//div[@class='othertag-disable']//input[@aria-haspopup='listbox']");
    public static By fileUpload=By.xpath("//input[@id='getFile']");
    public static By contentInputFields=By.xpath("//div[@class='othertag-disable']//input");
    public static By spinner=By.xpath("//div[@role='alert' and @class='p-progress-spinner']");
    public static By contentData=By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]/td");
    public static By assignmentDescription=By.xpath("//div[contains(@class,'p-editor')]//p");
    public static By assignmentInputFields=By.xpath("//athena-add-new-assignment//input");
    public static By learnerLastName=By.xpath("(//tbody)[3]//td[2]");
    public static By candidateLastName=By.xpath("(//tbody)[2]//td[2]");
    public static By employeeLastName=By.xpath("(//tbody)[1]//td[2]");
    public static By contentActionsIcon=By.xpath("//tr//button[contains(@class,'p-button')]");

    public static String editOptions="//label[text()='input']//parent::div";
    public static By assignmentMarks=By.xpath("//input[@formcontrolname=\"assignmentMarks\"]");
    public static By assignmentTagDisplayed=By.xpath("//tbody//tr[1]//td[3]");
    public static By assignmentMarksDisplayed=By.xpath("//tbody//tr[1]//td[4]");
    public static By contentTags=By.xpath("//p-multiselect[@formcontrolname=\"contentTag\"]");
    public static By contentDuration=By.xpath("//p-inputmask[@formcontrolname='duration']//input");
    public static By contentTagDisplayed=By.xpath("//tbody//tr//td[2]");
    public static By contentDurationDisplayed=By.xpath("//tbody//tr//td[3]");
    public static By switchStatus=By.xpath("//p-inputswitch[@ng-reflect-text='Active']");
    public static By statusDropdown=By.xpath("//p-dropdown[@placeholder='Status']");
    public static By inactiveStatusBar= By.xpath("//tbody//tr//p-inputswitch");
    public static By getTestName=By.xpath("//div[@class='card hover-effect']//h6//div");
    public static By loginVia=By.xpath("//p-dropdown[@placeholder='Select Option']");
    public static By viewReport=By.xpath("//button[@label='View Report' and contains(@class,'instBtn')]");
    public static By saveNext=By.xpath("//button[@ng-reflect-label='Save & Next']");
    public static By invigilationAlert=By.xpath("//strong[contains(text(),'automatic submission')]");
    public static By batchActionsIcon=By.xpath("(//div[@class='p-card-body']//button[contains(@class,'p-button')])[1]");
    public static By addCourse=By.xpath("//i[@ptooltip='Add Course']");
    public static By addedCourseName=By.xpath("(//thead)[2]//following::tbody//td[1]");
    public static By recentlyAddedCourse=By.xpath("//div[@class='p-card-body']//div[contains(@class,'name')]");
    public static By recentlyAddedCourseAfterEdit=By.xpath("(//div[@class='p-card-body']//div[contains(@class,'name')])[1]");
    public static By owner=By.xpath("//div[contains(@class,'multiselect')]//span");
    public static By userEmail=By.xpath("//div[@class='upper-card-details']//div[2]/div");
    public static By batchInputFields=By.xpath("//form[contains(@class,'ng-invalid')]//input");
    public static By batchCreated=By.xpath("//div[@class='p-card-body']//div[contains(@class,'name')]");
    public static String createBatch="//button[contains(@ng-reflect-label,'input')]";
    public static By searchbox=By.xpath("//input[@type='search']");
    public static String dropdown="//p-dropdown[@placeholder='input']";
    public static By signInBtn=By.xpath("//button[@label='Sign in']");
    public static By allCompletedTests=By.xpath("//p-tabpanel[@header='Completed Tests']//div[contains(@class,'name')]");
    public static By loginPageLink=By.xpath("(//a[contains(text(),'Login')])[2]");
    public static By videoPrompt=By.xpath("//span[text()='Movie Clip']");
    public static By wordCounter=By.xpath("//b[contains(text(),'Word Limit')]");
    public static By closeVideoPrompt=By.xpath("//span[contains(@class,'close')]//parent::button");
    public static By modules=By.xpath("//a[contains(@class,'header-link')]//span[contains(@class,'p-menuitem-text')]");
    public static By submodules=By.xpath("//a[@role='treeitem']//span[contains(@class,'p-menuitem-text')]");
    public static By testSummaryTabs=By.xpath("//li[@role='presentation']//span[1]");
    public static By learnerSearchbox=By.xpath("(//input[@placeholder='Name/Email'])[3]");
    public static By viewReportInAttemptedTest=By.xpath("(//button[contains(text(),'View Report')])[1]");

    public static String selectTab(int i) {

        String path = "//li[@role='presentation']["+(i)+"]//parent::a";
        return path;
    }


}

