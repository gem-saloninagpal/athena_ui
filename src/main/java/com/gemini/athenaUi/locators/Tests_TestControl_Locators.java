package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class Tests_TestControl_Locators {
    public static String date="(//span[text()='date'])";
    public static String secondDate="(//span[text()='date'])[2]";

    public static String buttonName="//button[text()='input']";

    //small[contains(text(),' Test')]
    public static By testList=By.xpath("//small[contains(text(),' Test')]");

    //button[text()='Next']
    public static By testInputFields=By.xpath("//form[@class='ng-untouched ng-pristine ng-invalid']//input");
    public static By trainingTestInputFields=By.xpath("//form[@class='ng-pristine ng-invalid ng-touched']//input");
    public static By testInputFieldsInternal=By.xpath("//form[@class='ng-untouched ng-pristine ng-invalid']//input");

    public static By testInputFieldsInternalTraining=By.xpath("//form[@class='ng-pristine ng-invalid ng-touched']//input");
    public static By saveCourseAndPublish=By.xpath("(//span[text()='Save Course & Publish']//parent::button)[2]");
    public static String calendar="(//input[contains(@id,'calendar')])[itr]";

    public static By dropdown=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");

    public static String option="//span[text()='section']";
//    public static By percentageInput=By.xpath("(//form[@class='p-ml-5 p-mr-5 ng-invalid ng-dirty ng-touched']//input)[2]");
    public static By percentageInput=By.xpath("//input[@id='cutOffPercentage']");

    //input[@id='cutOffPercentage']
//    public static By timeInput=By.xpath("(//form[@class='p-ml-5 p-mr-5 ng-invalid ng-dirty ng-touched']//input)[3]");
    public static By timeInput=By.xpath("//input[@id='inputmask']");


    //input[@id='inputmask']
    public static By addQuestion=By.xpath("//button//span[text()='Add Questions']");

    public static String clickButton="//button//span[text()='input']";

    //button//span[text()='Add Questions']
    public static By importRandomBtn=By.xpath("//button//span[text()='Import Random']");
    public static By questionTypeDropdown=By.xpath("//div//span[text()='Question Type']");
    public static By difficultyDropdown=By.xpath("//div//span[text()='Difficulty']");
    public static By levelDropdown=By.xpath("//div//span[text()='Level']");
    public static By noOfQuestionInput=By.xpath("//span[@class='p-inputnumber p-component']//input");
    public static By submitButton=By.xpath("//button[text()=' Submit ']");

    public static String options="//li//span[text()='input']";
    public static By saveButton=By.xpath("//button//span[text()='Save']");

    public static By screenHeader=By.xpath("//h6");
    public static By submitBtn=By.xpath("//button//span[text()='SUBMIT']");
    public static By continueButton=By.xpath("//button//span[text()='Continue']");
    public static By addNewTestBtn=By.xpath(" //button//span[text()='Add New Test']");
    public static By createdTestText=By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr//td)[1]");
    public static By createdTestText1=By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr//td)[2]");
    public static By createdTestTextInternal=By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr//td[1]");
    public static By createdTestTextTraining=By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[3]//tr//td[1]");
    public static By threeDotIcon=By.xpath("(//span[contains(@class,'pi pi-ellipsis')])[1]");

    public static By completedTabthreeDotIcon=By.xpath("//p-tabpanel[@header='Completed']//span[contains(@class,'pi pi-ellipsis')]");

    //p-tabpanel[@header='Completed']//span[contains(@class,'pi pi-ellipsis')]
    public static By threeDotInternal=By.xpath("(//span[contains(@class,'pi pi-ellipsis')])[11]");

    public static By threeDotTrainingTest=By.xpath(" (//span[contains(@class,'pi pi-ellipsis')])[21]");
    public static String threeDotOption="//div//label[text()='input']";

    public static By uploadBtn=By.xpath(" (//span[contains(@class,'pi pi-upload')])");
    public static By assignedStatus=By.xpath("(//tbody[@class='p-datatable-tbody']//tr//td)[5]");


    public static By actionBtn=By.xpath("(//span[contains(@class,'pi pi-caret-down')])[2]");

    public static String actionOption="//span[text()='input']";

    //span[text()='input']

    public static By candidateList=By.xpath("(//tbody[@class='p-datatable-tbody']//tr)");
    public static String candidateStatus="(//tbody[@class='p-datatable-tbody']//tr[itr]//td)[5]";
    public static String learnerStatus="(//tbody[@class='p-datatable-tbody']//tr[itr]//td)[8]";
    public static String candidateCheckbox="((//tbody[@class='p-datatable-tbody']//tr[itr]//td)[1]//div)[3]";

    public static By assignCandidate=By.xpath("(//tbody[@class='p-datatable-tbody']//tr[1]//td)[3]");

    public static By editIcon=By.xpath("//img[@class='edit-icon']");



    public static By emailInput=By.xpath("//input[@id='email']");
    //input[@id='firstName']
    //img[@class='edit-icon']
    public static By assignCandidateEyeIcon=By.xpath("(//tbody[@class='p-datatable-tbody']//tr[1]//td)[6]//i[@class='pi pi-eye ng-star-inserted']");




    public static String candidateAction="(//i[@class='add pi pi-plus ng-star-inserted'])[itr]";


    //span/parent::div[text()=' Candidates assigned to this test: ']
    public static By learnerAssignCount=By.xpath("//span/parent::div[text()=' Learners assigned to this course: ']");
    public static By candidateAssignCount=By.xpath("//span/parent::div[text()=' Candidates assigned to this test: ']");
    public static By assignCandidateBtn=By.xpath("//span[text()='Assign Selected']");

    public static By updateBtn=By.xpath("//span[text()='Update']");
    public static By unAssignCandidateBtn=By.xpath("//span[text()='Unassign Selected']");




    //(//tbody[@class='p-datatable-tbody']//tr[itr]//td)[5]

    //(//tbody[@class='p-datatable-tbody']//tr)

    //(//tbody[@class='p-datatable-tbody']//tr[itr]//td)[1]
    public static By backBtn=By.xpath("//div[text()='Back']");
    public static By backBtn1=By.xpath("//span[text()='Back']");
    public static By noDataFound=By.xpath("//div[text()='No Records Found!!']");
    public static By filterInput=By.xpath("(//input[@type='text'])[2]");
    public static By firstMcqOption=By.xpath("//button[text()=' 1 ']");

    public static By logout=By.xpath("//span[text()='Logout']");
    public static By upDateAndMoreBtn=By.xpath("//span[text()='Update & Exit']");

    public static By startTestBtn=By.xpath("//button[text()=' Start Test ']");
    public static By scoreBoard=By.xpath("//h4[text()='Summary']");
    public static By completedTab=By.xpath("//a//span[text()='Completed Tests']");
    public static By testHeading=By.xpath("(//div[@class='card-adjustments'])[2]//div[@class='rounded m-2 ng-star-inserted']//h6//div");
    public static By reattemptBtn=By.xpath("(//div[@class='card-adjustments'])[2]//div[@class='rounded m-2 ng-star-inserted']//button//span[text()='Reattempt']");

    public static String checkbox="(//form[@class='ng-dirty ng-touched ng-valid']//div[@class='p-checkbox-box'])[itr]";
    public static By testControlFilterInput=By.xpath("//span[@class='p-input-icon-right p-ml-auto']//input");
    public static By testControlFilterInputInternal=By.xpath("(//span[@class='p-input-icon-right p-ml-auto']//input)[2]");
    public static By testControlFilterInputTraining=By.xpath("(//span[@class='p-input-icon-right p-ml-auto']//input)[3]");
    public static By statusColumn=By.xpath("//span[@class='p-tag-value']");
    public static By activeTestHeadingList=By.xpath("(//div[@class='card-adjustments'])[1]//div[@class='rounded m-2 ng-star-inserted']//h6");
    public static String startBtn="(//button[text()=' Start Test '])[itr]";
    public static By summaryHeading=By.xpath("(//h5[@class='test-heading']//b)[1]");
    public static By summaryCampus=By.xpath("(//h5[@class='test-heading']//b)[2]");
    public static By testNameInput=By.xpath("//input[@id='testName']");
    public static String buttonInternal="(//button[@label='input'])[2]";
    public static String checkboxDiv="//span[text()='input']/preceding-sibling::p-checkbox//div";
    public static By questionStatement=By.xpath("(//p)[4]");

    public static By closeVideoIcon=By.xpath("//span[contains(@class,'p-dialog-header-close-icon pi pi-times')]");
    public static By percentageIcon=By.xpath("//i[contains(@class,'pi pi-percentage ng-star-inserted')]");
    public static By marksInput=By.xpath("//input[@class='marks ng-untouched ng-pristine ng-invalid p-inputtext p-component']");

    public static By status=By.xpath("//div[@class='testinfo pass']");
    public static By hasTestCheckbox=By.xpath("(//div[@class='p-checkbox-box'])[2]");
    public static By addToCourse=By.xpath("//span[text()='Add To Course']");
    public static By searchInputCourse=By.xpath("//input[@placeholder='Search by Name/Tags']");
    public static By yearDropdown=By.xpath("//span[text()='Year']");
    public static By yearList=By.xpath("//ul[contains(@class,'p-dropdown-items ng-tns')]//li");
    public static By totalCandidateCount=By.xpath("//span/parent::div[text()=' Total Candidates: ']");
    public static By candidateAssignToTest=By.xpath("(//div[@class='ng-star-inserted'])[2]");
    public static String yearOption="(//ul[contains(@class,'p-dropdown-items ng-tns')]//li)[itr]";
    public static By dropdownIcon=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[3]");
    public static By dropdownIconInternal=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");
    public static By eyeIcon=By.xpath("//i[@class='pi pi-eye ng-star-inserted']");
    public static By sideBarDiv=By.xpath("//span[text()='View Profile Details']");
    public static By rightPaginatorIcon=By.xpath("//button[contains(@class,'p-paginator-last')]");
    public static By assignMultipleUserBtn=By.xpath("//i[contains(@class,'pi pi-forward')]");
    public static By testNameSearchInput=By.xpath("//input[@type='search']");
    public static By createNewQuestionBtn=By.xpath("//button//span[text()='Create New']");
    public static By questionsInputDropdown=By.xpath("//form//div//span[contains(@class,'pi pi-chevron-down')]");
    public static String questionOptions="//span[text()='input']";
    public static By inputMarks=By.xpath("//input[@placeholder='Marks']");
    public static By comprehensiveCheckbox=By.xpath("//div[@class='p-checkbox-box']");
    public static By nextBtn=By.xpath("//button//span[text()='Next']");
    public static By yesBtn=By.xpath("//span[text()='Yes']");
    public static By editBtn=By.xpath("//button//span[text()='Edit']");
    public static By chooseSpecificBtn=By.xpath("//span[text()='Choose Specific']");
    public static By deleteBtn=By.xpath("//button//span[text()='Delete']");
    public static By rcPassage=By.xpath("//div[@class='ql-editor ql-blank']");
    public static By savePassageBtn=By.xpath("//button//span[text()='Save Passage & Continue']");
    public static By questionStatementDiv=By.xpath("//textarea");
    public static By optionDiv=By.xpath("(//textarea)[2]");
    public static By addOptionBtn=By.xpath("//button//span[text()='Add']");
    public static By answerOption=By.xpath("(//div[@class='p-checkbox-box'])[2]");
    public static By saveAndMoreBtn=By.xpath("//button//span[text()='Save & Add More']");
    public static By saveAndExitBtn=By.xpath("//button//span[text()='Save & Exit']");
    public static By calendarInput1=By.xpath("//input[@id='calendar1']");

    public static By calendarInput2=By.xpath("//input[@id='calendar2']");





    //input[@id='calendar1']


    public static String chooseSpecificDropDowns="(//span[contains(@class,'pi pi-chevron-down')])[itr]";
    public static By tagFilter=By.xpath("//input[@placeholder='Search by Statement/Tag']");

    public static By addIcon=By.xpath(" //i[@class='add-btn-plus pi pi-plus']");

    public static By addTestBtn=By.xpath("//span[text()='Add To Test']");

    public static String testButton="//p-tabpanel[@header='Internal Tests']//button[@label='input']";

    //p-tabpanel[@header="Internal Tests"]//button[@label='Create Test']

    public static By clearBtn=By.xpath("//button//span[text()='Clear']");

}
