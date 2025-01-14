package com.qa.athenaUi.locators;

import org.openqa.selenium.By;

public class Tests_TestControl_Locators {
    public static String date = "//span[text()='date']";
    public static By testInputFields = By.xpath("//form[@class='ng-untouched ng-pristine ng-invalid']//input");
    public static By trainingTestInputFields = By.xpath("//form[@class='ng-pristine ng-invalid ng-touched']//input");
    public static By testInputFieldsInternal = By.xpath("//form[@class='ng-invalid ng-touched ng-dirty']//input");
    public static By saveCourseAndPublish = By.xpath("(//span[text()='Save Course & Publish']//parent::button)[2]");
    public static String calendar = "(//input[contains(@id,'calendar')])[itr]";

    public static By dropdown = By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");

    public static String option = "//span[text()='section']";
    public static By percentageInput = By.xpath("//input[@id='cutOffPercentage']");
    public static By timeInput = By.xpath("//input[@id='inputmask']");
    public static By addQuestion = By.xpath("//span[text()='Add Questions']/parent::button");
    public static By importRandomBtn = By.xpath("//button//span[text()='Import Random']");
    public static By questionTypeDropdown = By.xpath("//div//span[text()='Question Type']");
    public static By difficultyDropdown = By.xpath("//div//span[text()='Difficulty']");
    public static By levelDropdown = By.xpath("//div//span[text()='Level']");
    public static By noOfQuestionInput = By.xpath("//span[@class='p-inputnumber p-component']//input");
    public static By submitButton = By.xpath("//button[text()=' Submit ']");

    public static String options = "//li//span[text()='input']";
    public static By saveButton = By.xpath("//button//span[text()='Save']");
    public static By submitBtn = By.xpath("//button//span[text()='SUBMIT']");
    public static By continueButton = By.xpath("//button//span[text()='Continue']");
    public static By addNewTestBtn = By.xpath(" //button//span[text()='Add New Test']");
    public static By createdTestText = By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr//td)[1]");
    public static By createdTestText1 = By.xpath("(//div[@class='p-datatable-wrapper ng-star-inserted']//table//tr//td)[2]");
    public static By createdTestTextInternal = By.xpath("//table[@id='pr_id_32-table']/tbody/tr/td[1]/span");
    public static By createdTestTextTraining = By.xpath("(//div[@class='p-datatable-wrapper']//table)[3]//tr//td[1]");
    public static By threeDotIcon = By.xpath("(//span[contains(@class,'pi pi-ellipsis')])[1]");
    public static By threeDotInternal = By.xpath("(//span[contains(@class,'pi pi-ellipsis')])[11]");
    public static By actions = By.xpath("(//span[contains(@class,'pi pi-ellipsis')])[1]");

    public static By threeDotTrainingTest = By.xpath(" (//span[contains(@class,'pi pi-ellipsis')])[21]");
    public static String threeDotOption = "//div//label[text()='input']";

    public static By actionBtn = By.xpath("(//span[contains(@class,'pi pi-caret-down')])[2]");

    public static String actionOption = "//span[text()='input']";

    //span[text()='input']

    public static By candidateList = By.xpath("//tbody[contains(@class,'p-datatable-tbody')]//tr");
    public static String candidateStatus = "(//tbody[contains(@class, 'p-datatable-tbody')]//tr[itr]//td)[5]";
    public static String learnerStatus = "(//tbody[contains(@class, 'p-datatable-tbody')]//tr[itr]//td)[9]";
    public static String candidateCheckbox = "((//tbody[contains(@class, 'p-datatable-tbody')]//tr[itr]//td)[1]//div)[3]";

    public static By assignCandidate = By.xpath("(//tbody[contains(@class, 'p-datatable-tbody')]//tr[1]//td)[3]");

    public static By editIcon = By.xpath("//img[@class='edit-icon']");


    public static By emailInput = By.xpath("//input[@id='email']");
    //input[@id='firstName']
    //img[@class='edit-icon']
    public static By assignCandidateEyeIcon = By.xpath("//table[contains(@class, 'p-datatable-table')]/tbody/tr[1]/td[2]");


    public static String candidateAction = "(//i[@class='add pi pi-plus ng-star-inserted'])[itr]";


    //span/parent::div[text()=' Candidates assigned to this test: ']
    public static By learnerAssignCount = By.xpath("//span/parent::div[text()=' Learners assigned to this course: ']");
    public static By candidateAssignCount = By.xpath("//span/parent::div[text()=' Candidates assigned to this test: ']");
    public static By assignCandidateBtn = By.xpath("//button[@ng-reflect-label=\"Assign Selected\"]");

    public static By updateBtn = By.xpath("//span[text()='Update']");
    public static By unAssignCandidateBtn = By.xpath("//span[text()='Unassign Selected']");


    //(//tbody[@class='p-datatable-tbody']//tr[itr]//td)[5]

    //(//tbody[@class='p-datatable-tbody']//tr)

    //(//tbody[@class='p-datatable-tbody']//tr[itr]//td)[1]
    public static By backBtn = By.xpath("//div[text()='Back']");
    public static By backBtn1 = By.xpath("//span[text()='Back']");
    public static By noDataFound = By.xpath("//div[text()='No Records Found!!']");
    public static By filterInput = By.xpath("(//input[@type='text'])[2]");
    public static By firstMcqOption = By.xpath("//button[text()=' 1 ']");

    public static By logout = By.xpath("(//a[contains(@class,'p-menuitem-link')])[3]");
    public static By upDateAndMoreBtn = By.xpath("//span[text()='Update & Exit']");

    public static By startTestBtn = By.xpath("//button[text()=' Start Test ']");
    public static By scoreBoard = By.xpath("//h4[text()='Summary']");
    public static By completedTab = By.xpath("//a//span[text()='Completed Tests']");
    public static By testHeading = By.xpath("(//div[@class='card-adjustments'])[2]//div[@class='rounded m-2 ng-star-inserted']//h6//div");
    public static By reattemptBtn = By.xpath("(//div[@class='card-adjustments'])[2]//div[@class='rounded m-2 ng-star-inserted']//button//span[text()='Reattempt']");

    public static String checkbox = "(//form[@class='ng-dirty ng-touched ng-valid']//div[@class='p-checkbox-box'])[itr]";
    public static By testControlFilterInput = By.xpath("//input[@placeholder='Name/Email']");
    public static By testControlFilterInputInternal = By.xpath("(//span[@class='p-input-icon-right p-ml-auto']//input)[2]");
    public static By testControlFilterInputTraining = By.xpath("(//span[@class='p-input-icon-right p-ml-auto']//input)[3]");
    public static By statusColumn = By.xpath("//span[@class='p-tag-value']");
    public static By activeTestHeadingList = By.xpath("(//div[@class='card-adjustments'])[1]//div[@class='rounded m-2 ng-star-inserted']//h6");
    public static String startBtn = "(//button[text()=' Start Test '])[itr]";
    public static By summaryHeading = By.xpath("(//h5[@class='test-heading']/b)[1]");
    public static By summaryCampus = By.xpath("(//h5[@class='test-heading']//b)[2]");
    public static By testNameInput = By.xpath("//input[@id='testName']");
    public static String buttonInternal = "(//button[@label='input'])[2]";
    public static String checkboxDiv = "//span[text()='input']/preceding-sibling::p-checkbox//div";
    public static By questionStatement = By.xpath("(//p)[4]");

    public static By closeVideoIcon = By.xpath("//span[contains(@class,'p-dialog-header-close-icon pi pi-times')]");
    public static By percentageIcon = By.xpath("//i[contains(@class,'pi pi-percentage ng-star-inserted')]");
    public static By marksInput = By.xpath("//input[contains(@class,'marks')]");

    public static By status = By.xpath("//div[@class='testinfo pass']");
    public static By hasTestCheckbox = By.xpath("(//div[@class='p-checkbox-box'])[2]");
    public static By addToCourse = By.xpath("//span[text()='Add To Course']");
    public static By searchInputCourse = By.xpath("//input[@placeholder='Search by Name/Tags']");
    public static By yearDropdown = By.xpath("//span[text()='Year']");
    public static By yearList = By.xpath("//ul[contains(@class,'p-dropdown-items ng-tns')]//li");
    public static By totalCandidateCount = By.xpath("//span/parent::div[text()=' Total Candidates: ']");
    public static By candidateAssignToTest = By.xpath("(//div[@class='ng-star-inserted'])[2]");
    public static String yearOption = "(//ul[contains(@class,'p-dropdown-items ng-tns')]//li)[itr]";
    public static By dropdownIcon = By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[3]");
    public static By dropdownIconInternal = By.xpath("(//span[contains(@class,'pi pi-chevron-down')])[2]");
    public static By eyeIcon = By.xpath("//i[@class='pi pi-eye ng-star-inserted']");
    public static By sideBarDiv = By.xpath("//span[text()=' View Profile Details ']");
    public static By rightPaginatorIcon = By.xpath("//button[contains(@class,'p-paginator-last')]");
    public static By assignMultipleUserBtn = By.xpath("//i[contains(@class,'pi pi-forward')]");
    public static By testNameSearchInput = By.xpath("//input[@type='search']");
    public static By createNewQuestionBtn = By.xpath("//button//span[text()='Create New']");
    public static By questionsInputDropdown = By.xpath("//form//div//span[contains(@class,'pi pi-chevron-down')]");
    public static String questionOptions = "//span[text()='input']";
    public static By inputMarks = By.xpath("//input[@placeholder='Marks']");
    public static By comprehensiveCheckbox = By.xpath("//div[@class='p-checkbox-box']");
    public static By nextBtn = By.xpath("//button//span[text()='Next']");
    public static By yesBtn = By.xpath("//span[text()='Yes']");
    public static By editBtn = By.xpath("//button//span[text()='Edit']");
    public static By chooseSpecificBtn = By.xpath("//span[text()='Choose Specific']");
    public static By deleteBtn = By.xpath("//button//span[text()='Delete']");
    public static By rcPassage = By.xpath("//div[@class='ql-editor ql-blank']");
    public static By savePassageBtn = By.xpath("//button//span[text()='Save Passage & Continue']");
    public static By questionStatementDiv = By.xpath("//textarea");
    public static By optionDiv = By.xpath("(//textarea)[2]");
    public static By addOptionBtn = By.xpath("//button//span[text()='Add']");
    public static By answerOption = By.xpath("(//div[@class='p-checkbox-box'])[2]");
    public static By saveAndMoreBtn = By.xpath("//button//span[text()='Save & Add More']");
    public static String chooseSpecificDropDowns = "(//span[contains(@class,'pi pi-chevron-down')])[itr]";
    public static By tagFilter = By.xpath("//input[@placeholder='Search by Statement/Tag']");

    public static By addIcon = By.xpath(" //i[@class='add-btn-plus pi pi-plus']");
    public static By addQuestionIcon = By.xpath("(//i[contains(@class,'add-btn-plus pi pi-plus')])[1]");
    public static By addTestBtn = By.xpath("//span[text()='Add To Test']");

    public static By candidateAssignCheckbox = By.xpath("(//div[@aria-checked=\"false\"])[2]");
    public static By candidateCheckbox2 = By.xpath("(//span[text()='Unassigned']//parent::td//parent::tr//p-tablecheckbox)[1]");
    public static By startcalendar = By.xpath("//input[@id='calendar1']");
    public static By startDate = By.xpath("//td[contains(@class, 'datepicker-today')]");
    public static By endCalendar = By.xpath("//input[@id='calendar2']");
    public static By endDate = By.xpath("(//button[contains(@class, 'p-ripple')])[5]");
    public static By testName = By.xpath("//input[@id='testName']");
    public static By testTag = By.xpath("//input[@id='testTag']");
    public static By testTime = By.xpath("//input[@id='inputMask']");
    public static By campusDropdown = By.xpath("//p-dropdown[@id='ddownCampus']");
    public static By selectCampus = By.xpath("(//p-dropdownitem)[1]");
    public static By levelDd = By.xpath("//p-dropdown[@id='ddownLevel']");
    public static By selectLevel = By.xpath("//p-dropdownitem[@ng-reflect-option='Beginner']");
    public static By selectLevelInternal = By.xpath("//li[@aria-label='Beginner']");
    public static By testReattempt = By.xpath("//p-dropdown[@id='reattempt']");
    public static By testSuppress = By.xpath("//p-dropdown[@id='suppress']");
    public static By testScore = By.xpath("//p-dropdown[@id='score']");
    public static By switchSections = By.xpath("//p-dropdown[@id='switch']");
    public static By shuffleQues = By.xpath("//p-dropdown[@id='shuffle']");
    public static By serverSide = By.xpath("//p-dropdown[@id='serverSide']");
    public static By selectNo = By.xpath("//li[@aria-label='No']");
    public static By selectYes = By.xpath("//li[@aria-label='Yes']");
    public static By nextbtn = By.xpath("//button[@type='submit']");
    public static By nextbtn1 = By.xpath("(//button[contains(@class, 'addnewbtn')])[3]");
    public static By testsTab = By.xpath("/html/body/app-root/div/athena-candidate-layout/div/athena-candidate-header/div/p-menubar/div/p-menubarsub/ul/li[1]/a");
    public static By placementTab = By.xpath("//span[text()='Placement Drives']");
    public static By subjective = By.xpath("//textarea");
    public static By duration = By.xpath("//input[@id='inputMask']");
    public static By testLevelDropdown = By.xpath("//p-dropdown[@id='ddownLevel']");
    public static By testDesc = By.xpath("//textarea[@id='description']");
    public static By updatedEmail = By.xpath("//table[contains(@class, 'p-datatable-table')]/tbody/tr[1]/td[3]");
    public static By updateName = By.xpath("//input[@id='firstName']");
    public static By lastname = By.xpath("//input[@id='lastName']");
    public static By assignCheckbox = By.xpath("(//div[@class='p-checkbox-box p-component'])[1]");
    public static By assignSelected = By.xpath("//button/span[text()='Assign Selected']");
    public static By countAfterAssigning = By.xpath("(//span[@class='header-number'])[2]");
    public static By addedQuestionActions = By.xpath("//button[contains(@class,'p-ripple')]");
    public static By questionDelete = By.xpath("//label[text()='Delete']");
    public static By yesOption = By.xpath("//button/span[text()='Yes']");
    public static By quesCount = By.xpath("//p[@id='ques-count']");
    public static By questionEdit = By.xpath("//label[text()='Edit']");
    public static By updateAndExit = By.xpath("//button/span[text()='Update & Exit']");
    public static By quesNameAfter = By.xpath("//td[@id='col1']");
    public static By yearDropdownOption = By.xpath("//li[@aria-label='2023']");
    public static By noRecords = By.xpath("//div[@class='text-center']");
    public static By clearIcon = By.xpath("//i[contains(@class, 'p-dropdown-clear')]");
    public static By threeDotPlacement = By.xpath("(//span[contains(@class,'pi pi-ellipsis')])[1]");
    public static By updateQuestionTypeDD = By.xpath("(//div//span[text()='Question Type'])[3]");
    public static By updateDifficultyDD = By.xpath("(//div//span[text()='Difficulty'])[3]");
    public static By updateLevelDD = By.xpath("(//div//span[text()='Level'])[3]");
    public static By updateQuestionTypeTraining = By.xpath("(//div//span[text()='Question Type'])[13]");
    public static By updateLevelTraining = By.xpath("(//div//span[text()='Level'])[13]");
    public static By difficultyTraining = By.xpath("(//div//span[text()='Difficulty'])[13]");

}
