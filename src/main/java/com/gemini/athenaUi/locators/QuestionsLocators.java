package com.gemini.athenaUi.locators;

import org.openqa.selenium.By;

public class QuestionsLocators {

    public static String dropdownValue="//p-dropdownitem[contains(@ng-reflect-label,'input')]";
    public static By dropdownFields=By.xpath("//form[@fxlayout='column wrap']//p-dropdown");
    public static By marksField=By.xpath("//input[@placeholder='Marks']");

    public static By questionBox=By.xpath("//div[@data-gramm='false']");
    public static By optionsBox=By.xpath("//div[@class='editorDiv']//div[@data-gramm='false']");
    public static By enterOption=By.xpath("//textarea[contains(@class,'textarea-option')]");
    public static By selectOption=By.xpath("(//div[@class='p-scrollpanel-wrapper']//div[@class='p-checkbox-box'])[1]");
    public static By firstColumn=By.xpath("//tbody/tr/td[1]");
    public static By addButton=By.xpath("//button[@ng-reflect-label='Add']");
    public static By wordLimitTextbox=By.xpath("//input[@placeholder='Word Limit']");
    public static By passageBox=By.xpath("//div[@contenteditable=\"true\"]");
    public static By passage=By.xpath("//div[@contenteditable=\"true\"]//p");
    public static By passageStatement=By.xpath("((//tbody)[2]/tr/td[2])[1]");
    public static By expandPassage=By.xpath("((//tbody)[2]/tr/td[1]//button)[1]");
    public static By expandPassageFirstColumn=By.xpath("(//table[@role='grid'])[3]//tbody//td[1]");
    public static By addNewComprehension=By.xpath("(//button[@label='Add New'])[2]");
    public static By passageQuestionBox=By.xpath("(//div[@data-gramm=\"false\"])[2]");
    public static By addNewVideoQuestion=By.xpath("(//button[@label='Add New'])[3]");
    public static By movieDialog=By.xpath("//span[text()='Upload Movie Clip related to this question']");
    public static By movieNameInputBox=By.xpath("//label[text()='Movie Name']//preceding::input[1]");
    public static By movieDescription=By.xpath("//input[@id='description']");
    public static By uploadBtn=By.xpath("//p-button[@ng-reflect-label=\"Upload\"]");
    public static By chooseBtn=By.xpath("//input[contains(@accept,'video')]");
    public static By video=By.xpath("//source[contains(@src,'questions')]//parent::video");
    public static By expandVideo=By.xpath("(//button[@ng-reflect-icon='pi pi-chevron-right'])[1]");
    public static By videoQuestions= By.xpath("(//athena-video-questions//p-table//tbody[contains(@class,'datatable')])[2]//td[1]");
    public static By videoName=By.xpath("//athena-video-questions//tr[1]/td[3]");

    public static By videoDescription=By.xpath("//athena-video-questions//tr[1]/td[4]");
    public static By editIcon=By.xpath("//button[@ptooltip=\"Click to edit\"]");
    public static By questionDialog=By.xpath("//div[contains(@class,'p-dialog-header')]");
    public static By questionInDialogBox=By.xpath("//div[contains(@class,'question-description')]//p");
    public static By confirmationDialog=By.xpath("//div[contains(@class,'p-dialog-header')]//span[text()='Confirmation']");
    public static By passageActionsIcon=By.xpath("(//p-tabpanel[@header=\"Comprehensions\"]//p-button[@icon='pi pi-ellipsis-v']//button)[1]");
 //   public static By passageActionsIcon2=By.xpath("(//p-button[@ng-reflect-icon='pi pi-ellipsis-v'])[1]");
    public static By passageActionsIcon2=By.xpath("((//tbody)[2]//tr//td[3]//button)[1]");
    public static By comprehensionDialog=By.xpath("//div[contains(@class,'p-dialog-header')]//span[text()='Comprehension']");
    public static By rcPassage=By.xpath("//div[contains(@class,'p-dialog')]//athena-comprehension-info//p");
//    public static By videoActionsIcon=By.xpath("((//tbody[@class='p-datatable-tbody'])[3]//tr//td[5]//button)[1]");
    public static By videoActionsIcon=By.xpath("//*[@id='pr_id_11-table']/tbody/tr[3]/td[3]/athena-action/p-button/button");

    public static By videoDialog=By.xpath("//div[contains(@class,'p-dialog-header')]//span[contains(text(),'Video Based')]");

    public static By videoNameDescriptionOnView=By.xpath("//p-dynamicdialog//h4[@class='test-info']");

    public static By videoSearchbox=By.xpath("//input[@type='search' and @placeholder='Video Name']");
    public static By passageSearchbox=By.xpath("//input[@placeholder='Passage Statement']");
    public static By deleteAssociatedQuestion=By.xpath("//i[@ptooltip=\"Remove Associated question\"]");
    public static By deleteAssociatedVideoQuestions=By.xpath("//i[@ptooltip='Remove associated question']");
    public static By editComprehensionBasedQuestion=By.xpath("//i[@ptooltip='Edit']");
    public static By comprehensionQuestionTextarea=By.xpath("(//div[@data-gramm=\"false\"]/p)[2]");
    public static By uploadingIcon=By.xpath("//i[contains(@class,'pi pi-arrow')]");
    public static By chooseQuestionBtn=By.xpath("//span[contains(@class,'p-fileupload')]//input");
    public static By codingQuestionBox=By.xpath("//textarea[@formcontrolname='statement']");
    public static By selectedLanguage=By.xpath("//span[contains(@class,'multiselect-token-label')]");
    public static By questionOnPreview=By.xpath("//div[contains(@class,'unselectable')]//p");
    public static By closeDialogBox=By.xpath("//span[contains(@class,'close')]//parent::button");
    public static By questionBox1=By.xpath("//textarea[@formcontrolname='statement']");
    public static By languageOnView=By.xpath("//b[contains(text(),'Accepted Languages')]//parent::div");
    public static By enterPassageOption=By.xpath("//div[@class=\"editorDiv\"]//p");
    public static By questionBox2=By.xpath("(//div[@data-gramm=\"false\"]//p)[1]");
    public static By expandLanguageDropdown=By.xpath("//p-multiselect[@formcontrolname=\"acceptedLanguages\"]");
    public static String codingLanguageOptions="//p-multiselectitem[@ng-reflect-label='input']";
    public static By plusIcon=By.xpath("//span[contains(@class,'plus')]//parent::button");
    public static By deleteNew = By.xpath("//*[@id=\"p-tabpanel-1\"]/athena-comprehensions/p-table/div[2]/div/div[3]/label[2]");
}
