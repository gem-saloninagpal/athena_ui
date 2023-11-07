package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class Tests_TestControl_Locators {
    public static String date="//span[text()='date']";
    public static By testInputFields=By.xpath("//form[@class='ng-untouched ng-pristine ng-invalid']//input");

    public static By trainingTestInputFields=By.xpath("//form[@class='ng-pristine ng-invalid ng-touched']//input");

    public static By testInputFieldsInternal=By.xpath("//form[@class='ng-invalid ng-touched ng-dirty']//input");

    //form[@class='ng-invalid ng-touched ng-dirty']//input
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

    public static By submitBtn=By.xpath("//button//span[text()='SUBMIT']");



    public static By continueButton=By.xpath("//button//span[text()='Continue']");


    //button//span[text()='Add New Test']

    public static By addNewTestBtn=By.xpath(" //button//span[text()='Add New Test']");
    public static By createdTestText=By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr//td)[1]");

    public static By createdTestTextInternal=By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[2]//tr//td[1]");

    public static By createdTestTextTraining=By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table)[3]//tr//td[1]");

    public static By threeDotIcon=By.xpath("(//span[contains(@class,'pi pi-ellipsis')])[1]");

    public static By threeDotInternal=By.xpath("(//span[contains(@class,'pi pi-ellipsis')])[11]");

    public static By threeDotTrainingTest=By.xpath(" (//span[contains(@class,'pi pi-ellipsis')])[21]");

    public static String threeDotOption="//div//label[text()='input']";

    public static By backBtn=By.xpath("//button//span[text()='Back']");

    public static By filterInput=By.xpath("(//input[@type='text'])[2]");

    public static By firstMcqOption=By.xpath("//button[text()=' 1 ']");

    public static By logout=By.xpath("//span[text()='Logout']");


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
    public static By totalCandidateCount=By.xpath("//div[@class='ng-star-inserted']//div");
    public static By candidateAssignToTest=By.xpath("(//div[@class='ng-star-inserted'])[2]");
    public static String yearOption="(//ul[contains(@class,'p-dropdown-items ng-tns')]//li)[itr]";
    public static By dropdownIcon=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[3]");
    public static By dropdownIconInternal=By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");
    public static By eyeIcon=By.xpath("//i[@class='pi pi-eye']");

    public static By sideBarDiv=By.xpath("//div[contains(@class,'p-sidebar')]");
    public static By rightPaginatorIcon=By.xpath("//button[contains(@class,'p-paginator-last')]");

    public static By assignMultipleUserBtn=By.xpath("//i[contains(@class,'pi pi-forward')]");

    public static By testNameSearchInput=By.xpath("//input[@type='search']");

    public static By createNewQuestionBtn=By.xpath("//button//span[text()='Create New']");

    public static By questionsInputDropdown=By.xpath("//form//div//span[contains(@class,'pi pi-chevron-down')]");

    public static String questionOptions="//span[text()='input']";

    public static By inputMarks=By.xpath("//input[@placeholder='Marks']");

    public static By comprehensiveCheckbox=By.xpath("//div[@class='p-checkbox-box']");
    public static By nextBtn=By.xpath("//button//span[text()='Next']");

    public static By editBtn=By.xpath("//button//span[text()='Edit']");

    public static By rcPassage=By.xpath("//div[@class='ql-editor ql-blank']");

    public static By savePassageBtn=By.xpath("//button//span[text()='Save Passage & Continue']");


    public static By questionStatementDiv=By.xpath("//textarea");

    public static By optionDiv=By.xpath("(//textarea)[2]");

    public static By addOptionBtn=By.xpath("//button//span[text()='Add']");

    public static By answerOption=By.xpath("(//div[@class='p-checkbox-box'])[2]");

    public static By saveAndMoreBtn=By.xpath("//button//span[text()='Save & Add More']");




    //button//span[text()='Add']

    //textarea

    //button//span[text()='Save Passage & Continue']


    //div[@class='ql-editor ql-blank']
    //button//span[text()='Next']


    //div[@class='p-checkbox-box']
    //input[@placeholder='Marks']
    //span[text()='input']

    //form//div//span[contains(@class,'pi pi-chevron-down')]

    //button//span[text()='Create New']

//input[@type='search']
}
