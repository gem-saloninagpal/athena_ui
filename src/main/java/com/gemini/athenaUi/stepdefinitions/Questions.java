package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.athenaUi.locators.QuestionsLocators;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.*;
import com.gemini.gemjar.utils.ui.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import static com.gemini.athenaUi.stepdefinitions.CandidateModule_UserManagement.clickButton;
import static com.gemini.athenaUi.stepdefinitions.CandidateModule_UserManagement.generateUniqueEmail;
public class Questions {
    String _question;
    static String _passage;
    static String _passageQues;
    String _existingQues;
    String _comprehensionSubjectiveQues1;
    String _comprehensionSubjectiveQues2;
    static String _movieName;
    static String _movieDescription;
    String _updatedPassage;
    String _updateComprehensionQuestion;
    String _getLanguage1;
    String _getLanguage2;

    @And("^Select dropdown values in question fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void questionDropdownValues(String level, String type, String section, String difficulty, String skills, String text) {
        try {
            String[] fields = {level, type, section, difficulty, skills, text};
            String[] fieldName = {"level", "type", "section", "difficulty", "skills", "text"};
            List<WebElement> dropdowns = DriverAction.getElements(QuestionsLocators.dropdownFields);
            //select level, type, section etc. while creating a question
            for (int i = 0; i < dropdowns.size(); i++) {
                if(i==2&&section.equals("null")){
                    continue;
                }
                DriverAction.waitSec(3);
                DriverAction.click(dropdowns.get(i));
                DriverAction.click(By.xpath(QuestionsLocators.dropdownValue.replace("input", fields[i])));
                GemTestReporter.addTestStep("Select " + fields[0] + " in " + fieldName[i], "Successfully selected " + fields[i], Status.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Select dropdown values in question fields", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("Enter marks {string}")
    public void enterMarks(String marks) {
        try {
            DriverAction.typeText(QuestionsLocators.marksField, marks);
            GemTestReporter.addTestStep("Enter marks-" + marks + " in marks field", "Successfully entered the marks- " + marks, Status.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter marks", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("Enter question description {string}")
    public void enterQuestionDescription(String questionStatement) {
        try {
            DriverAction.waitUntilElementAppear(QuestionsLocators.questionBox1,10);
            if(DriverAction.isDisplayed(QuestionsLocators.questionBox1)) {
                DriverAction.typeText(QuestionsLocators.questionBox1, questionStatement);
            }else if(DriverAction.isDisplayed(QuestionsLocators.questionBox2)){
                DriverAction.typeText(QuestionsLocators.questionBox2, questionStatement);
            }else{
                GemTestReporter.addTestStep("Enter question description","Could not enter question description.",Status.FAIL,DriverAction.takeSnapShot());
            }
      //      DriverAction.typeText(QuestionsLocators.questionBox, questionStatement);
            GemTestReporter.addTestStep("Enter question description", "Successfully added the question- " + questionStatement, Status.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter question description", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("Enter second question description {string}")
    public void enterSecondQuestion(String questionStatement) {
        try {
            DriverAction.waitSec(4);
            if(DriverAction.isDisplayed(QuestionsLocators.questionBox2)) {
                DriverAction.typeText(QuestionsLocators.questionBox2, questionStatement);
            }
            //      DriverAction.typeText(QuestionsLocators.questionBox, questionStatement);
            GemTestReporter.addTestStep("Enter question description", "Successfully added the question- " + questionStatement, Status.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter question description", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Enter options and select a correct option$")
    public void enterOptions() {
        try {
            String text = "";
            for (int i = 0; i < 3; i++) {
                //call generate unique mail function and remove @gmail.com
                text = generateUniqueEmail();
                //enter option and add
                //   DriverAction.typeText(QuestionsLocators.optionsBox, text);
                if (DriverAction.isDisplayed(QuestionsLocators.enterOption)){
                    DriverAction.typeText(QuestionsLocators.enterOption, text);
                }
                else if (DriverAction.isDisplayed(QuestionsLocators.enterPassageOption)) {
                    DriverAction.typeText(QuestionsLocators.enterPassageOption, text);
                }else{

                }
                DriverAction.waitUntilElementClickable(QuestionsLocators.addButton, 5);
                DriverAction.click(QuestionsLocators.addButton, "Click the add button");
                GemTestReporter.addTestStep("Enter option- " + text, "Successfully added the option- " + text, Status.PASS);
            }
         //   Thread.sleep(6000);
            DriverAction.waitUntilElementClickable(QuestionsLocators.selectOption,3);
            DriverAction.click(QuestionsLocators.selectOption, "Successfully selected the option");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter options", "Exception encountered- " + e, Status.ERR);
        }
    }

    @Then("^Verify the question is created \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyQuestionIsCreated(String question1, String question2) {
        try {
            DriverAction.waitSec(4);
            String[] ques = {question2, question1};
            //verifying recently created 2 questions from the questions table
            DriverAction.waitUntilElementAppear(QuestionsLocators.firstColumn,5);
            List<WebElement> questions = DriverAction.getElements(QuestionsLocators.firstColumn);
            int c = 0;
            for (int i = 0; i <= 1; i++) {
                String quesStatement = DriverAction.getElementText(questions.get(i));
                if (quesStatement.contains(ques[i])) {
                    c++;
                }
            }
            if (c == 2) {
                GemTestReporter.addTestStep("Verify question is saved", "Successfully verified that question is saved.", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Verify question is saved", "Could not verify that question is saved.", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the question is saved", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Enter word limit \"([^\"]*)\"$")
    public void enterWordLimit(String words) {
        try {
            DriverAction.waitUntilElementAppear(QuestionsLocators.wordLimitTextbox,5);
            DriverAction.typeText(QuestionsLocators.wordLimitTextbox, words);
            GemTestReporter.addTestStep("Enter word limit", "Successfully entered the limit", Status.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter word limit", "Exception encountered- " + e, Status.ERR, DriverAction.takeSnapShot());
        }

    }

    @And("^Enter question description in subjective$")
    public void questionDescriptionSubjective() {
        try {
            _existingQues = _question;
        //    _existingQues = generateUniqueEmail();
            _question = generateUniqueEmail();
            DriverAction.waitUntilElementAppear(QuestionsLocators.questionBox1,4);
            DriverAction.typeText(QuestionsLocators.questionBox1, _question, "Successfully entered the question description.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter question description in subjective", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Enter subjective question description for coding$")
    public void questionDescriptionCoding() {
        try {
            DriverAction.waitSec(3);
            _existingQues = _question;
            _question = generateUniqueEmail();
            DriverAction.typeText(QuestionsLocators.codingQuestionBox, _question, "Successfully entered the question description.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter coding question description", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Enter the passage$")
    public void enterPassage() {
        try {
            //here generateUniqueEmail function is generating a unique string
            _passage = generateUniqueEmail();
            DriverAction.typeText(QuestionsLocators.passageBox, _passage, "Successfully entered the passage.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter the passage", "Exception encountered- " + e, Status.ERR);
        }
    }

    @Then("^Verify the passage and edit$")
    public void verifyPassageAndEdit() {
        String text = DriverAction.getElementText(QuestionsLocators.passage);
        if (text.contains(_passage)) {
            GemTestReporter.addTestStep("Verify the passage", "Successfully verified the passage.", Status.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the passage", "Could not verify the passage.", Status.FAIL, DriverAction.takeSnapShot());
        }
        _passage = "updated passage is- " + _passage;
        DriverAction.typeText(QuestionsLocators.passage, _passage);
        GemTestReporter.addTestStep("Edit the passage", "Successfully edited the passage.", Status.PASS);
    }

    @Then("^Verify the passage is created$")
    public void verifyPassageCreated() {
        try {
            DriverAction.waitUntilElementAppear(QuestionsLocators.passageStatement,5);
            String text = DriverAction.getElementText(QuestionsLocators.passageStatement);
            if (text.contains(_passage)) {
                GemTestReporter.addTestStep("Verify the passage is created", "Successfully verified the passage.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the passage is created", "Could not verify the passage.", Status.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the passage is created", "Exception encountered- " + e, Status.ERR);
        }

    }

    @And("^Expand the passage field$")
    public void expandPassageField() {
        try {
            DriverAction.waitUntilElementClickable(QuestionsLocators.expandPassage,8);
            DriverAction.click(QuestionsLocators.expandPassage, "Expand the passage field", "Successfully expanded the passage field");
            DriverAction.waitSec(2);
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand the passage field","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify the comprehension question is created \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyComprehensionQuestionCreated(String question1, String question2) {
        try {
            String[]ques;
            if(!question1.isEmpty() && !question2.isEmpty()) {
                 ques = new String[]{question2, question1};
            }else{
                ques=new String[]{_comprehensionSubjectiveQues1, _comprehensionSubjectiveQues2};
            }
            List<WebElement> questions = DriverAction.getElements(QuestionsLocators.expandPassageFirstColumn);
            int c = 0;
            //verifying 2 latest created comprehension questions
            for (int i = 0; i <= 1; i++) {
                String quesStatement = DriverAction.getElementText(questions.get(i));
                if (quesStatement.contains(ques[i])) {
                    c++;
                }
            }
            if (c == 2) {
                GemTestReporter.addTestStep("Verify comprehension question is saved", "Successfully verified that question is saved.", Status.PASS,DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify comprehension question is saved", "Could not verify that question is saved.", Status.FAIL,DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify comprehension question is saved", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Click Add New in comprehensions tab$")
    public void AddNewComprehensionsTab() {
        try {
            DriverAction.click(QuestionsLocators.addNewComprehension);
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Add New in comprehensions tab","Exception encountered- "+e,Status.ERR);
        }
    }

    @And("^Enter question description related passage \"([^\"]*)\"$")
    public void enterQuestionDescriptionRelatedPassage(String ques) {
        try {
            //here the function generates unique string
            _passageQues = generateUniqueEmail();
            DriverAction.waitUntilElementClickable(QuestionsLocators.passageQuestionBox,6);
            DriverAction.typeText(QuestionsLocators.passageQuestionBox, ques);
        }catch(Exception e){
            GemTestReporter.addTestStep("Enter question description related passage","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Click the button \"([^\"]*)\" and verify the message \"([^\"]*)\"$")
    public void clickTheButtonAndVerifyTheMessage(String buttonName, String message) {
        try {
            Thread.sleep(4000);

            if (buttonName.equals("Save & Exit") || buttonName.equals("Update & Exit") || buttonName.equals("Save & Add More")) {
                DriverAction.scrollToBottom();
            }
            DriverAction.waitUntilElementIsClickable(By.xpath(MyLocators.button.replace("input", buttonName)));
            DriverAction.click(By.xpath(MyLocators.button.replace("input", buttonName)));
            String errorMessage = DriverAction.getElementText(MyLocators.popupMsg);
            //verifying popup message after clicking the button
            if (errorMessage.contains(message)) {
                GemTestReporter.addTestStep("Verify the popup message", "Successfully verified popup message " + errorMessage, Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the popup message", "Could not verify the popup message " + errorMessage, Status.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the button- "+buttonName+" and verify message- "+message,"Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify the subjective questions$")
    public void verifySubjectiveQuestions() {
        try {
            String[] ques = { _question,_existingQues};
            DriverAction.waitUntilElementAppear(QuestionsLocators.firstColumn,5);
            List<WebElement> questions = DriverAction.getElements(QuestionsLocators.firstColumn);
            int c = 0;
            //verifying 2 latest created subjective questions
            for (int i = 0; i <= 1; i++) {
                String quesStatement = DriverAction.getElementText(questions.get(i));
                if (quesStatement.contains(ques[i])) {
                    c++;
                }
            }
            if (c == 2) {
                GemTestReporter.addTestStep("Verify the subjective question is saved", "Successfully verified that subjective question is saved.", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Verify the subjective question is saved", "Could not verify that subjective question is saved.", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the subjective question is saved", "Exception encountered- " + e, Status.ERR);
        }

    }

    @And("^Enter comprehension based subjective question$")
    public void enterComprehensionBasedSubjectiveQuestion() {
        try {
            //generating unique string
            _passageQues = generateUniqueEmail();
            //assigning question2 to question1
            _comprehensionSubjectiveQues1 = _comprehensionSubjectiveQues2;
            //assigning new string to question1
            _comprehensionSubjectiveQues2 = _passageQues;
            DriverAction.typeText(QuestionsLocators.passageQuestionBox, _passageQues);
        }catch(Exception e){
            GemTestReporter.addTestStep("Enter comprehension based subjective question", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Click Add New in video tab$")
    public void clickAddNewInVideoTab() {
            DriverAction.click(QuestionsLocators.addNewVideoQuestion);
        }

    @Then("^Verify upload movie clip dialog box displays$")
    public void verifyUploadMovieClipDialog() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.movieDialog)){
                GemTestReporter.addTestStep("Verify upload movie clip dialog box displays","Successfully verified the movie clip dialog box.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify upload movie clip dialog box displays","Could not verify the movie clip dialog box.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify upload movie clip dialog box displays","Exception encountered- "+e,Status.ERR);

        }
    }

    @When("^Enter movie name and description$")
    public void enterMovieNameAndDescription() {
        try{
            //generating unique string
            _movieName =generateUniqueEmail();
            _movieDescription =generateUniqueEmail();
            DriverAction.typeText(QuestionsLocators.movieNameInputBox, _movieName,"Successfully entered the movie name- "+ _movieName);
            DriverAction.typeText(QuestionsLocators.movieDescription, _movieDescription,"Successfully entered the movie description- "+ _movieDescription);
        }catch(Exception e){
            GemTestReporter.addTestStep("Enter movie name and description","Exception encountered- "+e,Status.ERR);
        }
    }

    @And("^Upload a video \"([^\"]*)\"$")
    public void uploadAVideo(String movieLocation) {
        try {
            DriverAction.fileUpload(QuestionsLocators.chooseBtn, movieLocation);
        }catch(Exception e){
            GemTestReporter.addTestStep("Upload a video","Exception encountered- "+e,Status.ERR);
        }
    }

    @And("^Click the upload button$")
    public void clickUploadButton() {
        try{
            DriverAction.click(QuestionsLocators.uploadBtn,"Click the upload button");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the upload button","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify video is uploaded$")
    public void verifyVideoIsUploaded() {
        try{
            DriverAction.waitUntilElementAppear(MyLocators.popupMsg,20);
            if(DriverAction.isDisplayed(QuestionsLocators.video)){
                GemTestReporter.addTestStep("Verify video is uploaded","Successfully verified the uploaded video",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify video is uploaded","Could not verify the uploaded video",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify video is uploaded","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Expand the video field$")
    public void expandVideoField() {
        try {
            DriverAction.waitSec(2);
            DriverAction.waitUntilElementClickable(QuestionsLocators.expandVideo,10);
            DriverAction.click(QuestionsLocators.expandVideo, "Expand the video field");
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand the video field","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify the video based question is created \"([^\"]*)\",\"([^\"]*)\"$")
    public void verifyVideoBasedQuestionCreated(String videoQues1,String videoQues2) {
        try {
            String[]ques=new String[]{videoQues2,videoQues1};
            List<WebElement> questions = DriverAction.getElements(QuestionsLocators.videoQuestions);
            int c = 0;
            //verifying 2 latest created video based questions
            for (int i = 0; i <= 1; i++) {
                String quesStatement = DriverAction.getElementText(questions.get(i));
                if (quesStatement.contains(ques[i])) {
                    c++;
                }
            }
            if (c == 2) {
                GemTestReporter.addTestStep("Verify video question is saved", "Successfully verified that question is saved.", Status.PASS,DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify video question is saved", "Could not verify that question is saved.", Status.FAIL,DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify video question is saved", "Exception encountered- " + e, Status.ERR);
        }
    }

    @Then("^Verify the video name and description$")
    public void verifyTheVideoNameAndDescription() {
        try {
            DriverAction.waitUntilElementClickable(QuestionsLocators.videoActionsIcon,20);
            String videoName = DriverAction.getElementText(QuestionsLocators.videoName);
            String description = DriverAction.getElementText(QuestionsLocators.videoDescription);
            if (videoName.contains(_movieName) && description.contains(_movieDescription)) {
                GemTestReporter.addTestStep("Verify the video name and description", "Successfully verified the video name and description.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the video name and description", "Could not verify the video name and description.", Status.FAIL, DriverAction.takeSnapShot());
            }

        }catch (Exception e){
            GemTestReporter.addTestStep("Verify the video name and description","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @When("^Click Actions icon of recently created question$")
    public void clickActionsIconOfRecentlyCreatedQuestion() throws InterruptedException {
        try {
            Thread.sleep(5000);
            DriverAction.click(MyLocators.contentActionsIcon);
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Actions icon of recently created question","Exception encountered- "+e,Status.ERR);
        }
    }

    @And("^Click the edit icon$")
    public void clickTheEditIcon() {
        try {
            DriverAction.click(QuestionsLocators.editIcon, "Click the edit icon");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the edit icon","Exception encountered- "+e,Status.ERR);
        }
    }

    @And("^Verify question dialog box opens$")
    public void verifyQuestionDialogBoxOpens() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.questionDialog)){
                GemTestReporter.addTestStep("Verify question dialog box opens","Successfully verified the question dialog box.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify question dialog box opens","Could not verify the question dialog box.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify question dialog box opens","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify question on view \"([^\"]*)\"$")
    public void verifyQuestion(String question) {
        try {
            String getQuestion = DriverAction.getElementText(QuestionsLocators.questionInDialogBox);
            if (getQuestion.contains(question)) {
                GemTestReporter.addTestStep("Verify question on view", "Successfully verified the question", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify question on view", "Could not verify the question", Status.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the question on view","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify confirmation dialog box appears$")
    public void verifyConfirmationDialogBoxAppears() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.confirmationDialog)){
                GemTestReporter.addTestStep("Verify confirmation dialog box appears","Successfully verified the confirmation dialog box.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify confirmation dialog box appears","Could not verify the confirmation dialog box.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify confirmation dialog box appears","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Click actions icon of recently created passage$")
    public void clickActionsIconOfRecentlyCreatedPassage() {
        try{
            DriverAction.waitSec(2);
            DriverAction.waitUntilElementClickable(QuestionsLocators.passageActionsIcon2,6);
            DriverAction.click(QuestionsLocators.passageActionsIcon2,"Click Actions icon of recently created passage");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Actions icon of recently created passage","Exception encountered- "+e,Status.ERR);
        }

    }

    @And("^Verify comprehension dialog box displays$")
    public void verifyComprehensionDialogBoxDisplays() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.comprehensionDialog)){
                GemTestReporter.addTestStep("Verify comprehension dialog box displays","Successfully verified the comprehension dialog box.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify comprehension dialog box displays","Could not verify the comprehension dialog box.",Status.FAIL,DriverAction.takeSnapShot());
            }

        }catch(Exception e){
            GemTestReporter.addTestStep("Verify comprehension dialog box displays","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify passage on view$")
    public void verifyPassageOnView() {
        try{
            String rcPassage=DriverAction.getElementText(QuestionsLocators.rcPassage);
            if(rcPassage.contains(_passage)){
                GemTestReporter.addTestStep("Verify passage on view","Successfully verified the passage on view.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify passage on view","Could not verify the passage on view.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify passage on view","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Update passage$")
    public void updatePassage() {
        try {
            DriverAction.clearText(QuestionsLocators.passageBox);
            //generating unique string
            _updatedPassage = generateUniqueEmail();
            DriverAction.typeText(QuestionsLocators.passageBox, _updatedPassage, "Update the passage");
        }catch(Exception e){
            GemTestReporter.addTestStep("Update the passage","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify passage gets updated$")
    public void verifyPassageGetsUpdated() {
        try {
            String text = DriverAction.getElementText(QuestionsLocators.passageStatement);
            if (text.contains(_updatedPassage)) {
                GemTestReporter.addTestStep("Verify the passage is updated", "Successfully verified the updated passage.", Status.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the passage is updated", "Could not verify the updated passage.", Status.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the passage is updated", "Exception encountered- " + e, Status.ERR);
        }
    }


    @And("^Click actions icon of recently created video$")
    public void clickActionsIconOfRecentlyCreatedVideo() {
        try{
            DriverAction.waitUntilElementClickable(QuestionsLocators.videoActionsIcon1,20);
            DriverAction.click(QuestionsLocators.videoActionsIcon1,"Click Actions icon of recently created passage");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Actions icon of recently created passage","Exception encountered- "+e,Status.ERR);
        }
    }

    @And("^Verify video dialog box displays$")
    public void verifyVideoDialogBoxDisplays() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.videoDialog)){
                GemTestReporter.addTestStep("Verify video dialog box displays","Successfully verified the video dialog box.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify video dialog box displays","Could not verify the video dialog box.",Status.FAIL,DriverAction.takeSnapShot());
            }

        }catch(Exception e){
            GemTestReporter.addTestStep("Verify video dialog box displays","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify movie name and description on view$")
    public void verifyMovieNameAndDescriptionOnView() {
        try{
            String videoNameDescription=DriverAction.getElementText(QuestionsLocators.videoNameDescriptionOnView);
            if(videoNameDescription.contains(_movieName)&&videoNameDescription.contains(_movieDescription)){
                GemTestReporter.addTestStep("Verify movie name and description on view","Successfully verified movie name and description on view.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify movie name and description on view","Could not verify movie name and description on view.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify movie name and description on view","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify the state of video \"([^\"]*)\"$")
    public void verifyVideoState(String videoState) {
      try{
          //verifying if video is deleted or not
          boolean isPresent=false;
          String video=DriverAction.getElementText(QuestionsLocators.videoName);
          if(video.equals(_movieName)){
              isPresent=true;
          }
          if(!isPresent && videoState.equals("not deleted")){
              GemTestReporter.addTestStep("Verify the state of video","Successfully verified the video state as- "+videoState,Status.PASS,DriverAction.takeSnapShot());
          }else if(isPresent && videoState.equals("deleted")){
              GemTestReporter.addTestStep("Verify the state of video","Successfully verified the video state as- "+videoState,Status.PASS,DriverAction.takeSnapShot());
          }
          else{
              GemTestReporter.addTestStep("Verify the state of video","Could not verify the video state as- "+videoState,Status.FAIL,DriverAction.takeSnapShot());
          }
      }catch(Exception e){
          GemTestReporter.addTestStep("Verify the state of video","Exception encountered- "+e,Status.ERR);
      }
    }

    @When("^Search a video$")
    public void searchAVideo() {
        try {
            DriverAction.typeText(QuestionsLocators.videoSearchbox, _movieName,"Search movie name");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Search a video", "Exception encountered- " + e, Status.ERR);
        }
    }

    @And("^Search a passage$")
    public void  searchAPassage() {
        try {
            DriverAction.typeText(QuestionsLocators.passageSearchbox, _updatedPassage,"Search a passage");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Search a passage", "Exception encountered- " + e, Status.ERR);
        }
    }

    @Then("^Verify the passage state \"([^\"]*)\"$")
    public void verifyThePassageState(String state) {
        try{

            //verifying if passage is deleted or not
            boolean isPresent=false;
            String passage=DriverAction.getElementText(QuestionsLocators.passageStatement);
            if(passage.equals(_updatedPassage)){
                isPresent=true;
            }
            if(!isPresent && state.equals("not deleted")){
                GemTestReporter.addTestStep("Verify the state of passage","Successfully verified the passage state as- "+state,Status.PASS,DriverAction.takeSnapShot());
            }else if(isPresent && state.equals("deleted")){
                GemTestReporter.addTestStep("Verify the state of passage","Successfully verified the passage state as- "+state,Status.PASS,DriverAction.takeSnapShot());
            }
            else{
                GemTestReporter.addTestStep("Verify the state of passage","Could not verify the passage state as- "+state,Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the state of passage","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Delete all the questions associated$")
    public void deleteAllTheQuestionsAssociated() {
        try{
            List<WebElement>deleteAssociatedQuestions= DriverAction.getElements(QuestionsLocators.deleteAssociatedQuestion);
            //deleting all the questions associated to a passage
            for(WebElement e:deleteAssociatedQuestions){
                DriverAction.click(e,"Delete all the questions associated","Successfully deleted all the associated questions.");
                verifyConfirmationDialogBoxAppears();
                clickButton("Yes");
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Delete all the questions associated","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Delete all the questions associated to video$")
    public void deleteAllTheQuestionsAssociatedToVideo() {
        try{
            List<WebElement>deleteAssociatedQuestions= DriverAction.getElements(QuestionsLocators.deleteAssociatedVideoQuestions);
            for(WebElement e:deleteAssociatedQuestions){
                DriverAction.click(e,"Delete all the questions associated to video","Successfully deleted all the video associated questions.");
                verifyConfirmationDialogBoxAppears();
                clickButton("Yes");
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Delete all the questions associated to video","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Get comprehension based question statement$")
    public void getComprehensionBasedQuestionStatement() {
        try{
            String question=DriverAction.getElementText(QuestionsLocators.expandPassageFirstColumn);
            GemTestReporter.addTestStep("Get comprehension based question statement","Successfully fetched the question statement- "+question,Status.PASS,DriverAction.takeSnapShot());
        }catch(Exception e){
            GemTestReporter.addTestStep("Get comprehension based question statement","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Edit comprehension based question$")
    public void editComprehensionBasedQuestion() {
        try{
            DriverAction.click(QuestionsLocators.editComprehensionBasedQuestion,"Click the edit icon","Successfully clicked the edit icon");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the edit icon","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Update comprehension based question$")
    public void updateComprehensionBasedQuestion() {
        try{
            //generating unique string
            _updateComprehensionQuestion =generateUniqueEmail();
            //deleting the previous question and entering a new question
            DriverAction.clearText(QuestionsLocators.comprehensionQuestionTextarea);
            DriverAction.typeText(QuestionsLocators.comprehensionQuestionTextarea, _updateComprehensionQuestion,"Update comprehension based question");
        }catch(Exception e){
            GemTestReporter.addTestStep("Update comprehension based question","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify the updated comprehension question$")
    public void verifyTheUpdatedComprehensionQuestion() {
        try{
            String updatedQuestion=DriverAction.getElementText(QuestionsLocators.expandPassageFirstColumn);//first column of expanded passage is the updated question
            if(updatedQuestion.equals(_updateComprehensionQuestion)){
                GemTestReporter.addTestStep("Verify the updated comprehension question","Successfully updated the comprehension question.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify the updated comprehension question","Could not update the comprehension question.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the updated question","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Upload an excel \"([^\"]*)\"$")
    public void uploadExcel(String fileLocation) {
        try {
            DriverAction.fileUpload(QuestionsLocators.chooseQuestionBtn,fileLocation);
        }catch(Exception e){
            GemTestReporter.addTestStep("Upload an excel","Exception encountered- "+e,Status.ERR);
        }
    }

    @Then("^Verify Status \"([^\"]*)\" and message \"([^\"]*)\" in uploaded excel$")
    public void verifyQuestionsGetUploaded(String expectedStatus, String expectedMessage) {
        try{
            DriverAction.waitUntilElementAppear(MyLocators.popupMsg,50);
            String message=DriverAction.getElementText(MyLocators.popupMsg);
            if(message.contains("upload is in progress")){
                Thread.sleep(120000);//wait until tick displays
                if(DriverAction.isDisplayed(QuestionsLocators.uploadingIcon)){//if file keeps on uploading after 90 sec
                    GemTestReporter.addTestStep("Verify if question gets uploaded","File keeps on uploading",Status.FAIL,DriverAction.takeSnapShot());
                }else{

                    //fetch recent excel file
                    File dir = new File("C:\\Users\\saloni.nagpal\\Downloads");
                    File[] files = dir.listFiles((d, name) -> name.endsWith(".xlsx"));
                    File recentFile = Arrays.stream(files).max((f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified())).orElse(null);

                    //fetch values from excel
                    assert recentFile != null;
                    FileInputStream fis = new FileInputStream(recentFile);
                    Workbook workbook = new XSSFWorkbook(fis);
                    Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
                    String excelStatus = sheet.getRow(1).getCell(16).getStringCellValue();
                    String excelMessage=sheet.getRow(1).getCell(17).getStringCellValue();
                    if(excelStatus.equals(expectedStatus)&&excelMessage.equalsIgnoreCase(expectedMessage)){
                        GemTestReporter.addTestStep("Verify if question gets uploaded","Successfully verified the uploaded question.",Status.PASS,DriverAction.takeSnapShot());
                    }else{
                        GemTestReporter.addTestStep("Verify if question gets uploaded","Could not verify the uploaded question.",Status.FAIL,DriverAction.takeSnapShot());
                    }
                }
            }else {
                GemTestReporter.addTestStep("Verify if question gets uploaded","Invalid popup message- " + message,Status.ERR,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify Status and message after uploading excel","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Upload recently downloaded file$")
    public void uploadRecentlyDownloadedFile() {
        // Get the path of the most recently downloaded file in the default Downloads directory
   //     DriverAction.click(QuestionsLocators.chooseQuestionBtn);
        String downloadPath = "C:\\Users\\saloni.nagpal\\Downloads";
        File mostRecentFile = getLastModifiedFile(downloadPath);

        // Send the file path to the file input element
        DriverAction.fileUpload(QuestionsLocators.chooseQuestionBtn, String.valueOf(mostRecentFile));
    }

                private static File getLastModifiedFile(String directoryPath)

                {
                    File directory = new File(directoryPath);
                    File[] files = directory.listFiles();
                    if (files == null || files.length == 0) {
                        return

                                null; // Handle the case where no files are found
                    }

                    File mostRecentFile = files[0];
                    for (File file : files) {
                        if (file.lastModified() > mostRecentFile.lastModified()) {
                            mostRecentFile = file;
                        }
                    }
                    return mostRecentFile;
                }

    @Then("^Verify Statuses \"([^\"]*)\", \"([^\"]*)\" and messages \"([^\"]*)\", \"([^\"]*)\" in uploaded excel$")
    public void verifyStatusesAndMessagesInUploadedExcel(String Status1, String Status2, String message1, String message2) {
        try{
            DriverAction.waitUntilElementAppear(MyLocators.popupMsg,50);
            String message=DriverAction.getElementText(MyLocators.popupMsg);
            if(message.contains("upload is in progress")){
                Thread.sleep(120000);//wait until tick displays
                if(DriverAction.isDisplayed(QuestionsLocators.uploadingIcon)){//if file keeps on uploading after 90 sec
                    GemTestReporter.addTestStep("Verify if question gets uploaded","File keeps on uploading",Status.FAIL,DriverAction.takeSnapShot());
                }else{

                    //fetch recent excel file
                    File dir = new File("C:\\Users\\saloni.nagpal\\Downloads");
                    File[] files = dir.listFiles((d, name) -> name.endsWith(".xlsx"));
                    File recentFile = Arrays.stream(files).max((f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified())).orElse(null);

                    //fetch values from excel
                    assert recentFile != null;
                    FileInputStream fis = new FileInputStream(recentFile);
                    Workbook workbook = new XSSFWorkbook(fis);
                    Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
                    String excelStatus1 = sheet.getRow(1).getCell(16).getStringCellValue();
                    String excelMessage1=sheet.getRow(1).getCell(17).getStringCellValue();
                    String excelStatus2 = sheet.getRow(2).getCell(16).getStringCellValue();
                    String excelMessage2=sheet.getRow(2).getCell(17).getStringCellValue();
                    if(excelStatus1.equals(Status1)&&excelMessage1.equalsIgnoreCase(message1)&&excelStatus2.equals(Status2)&&excelMessage2.equalsIgnoreCase(message2)){
                        GemTestReporter.addTestStep("Verify if question gets uploaded","Successfully verified the uploaded question.",Status.PASS,DriverAction.takeSnapShot());
                    }else{
                        GemTestReporter.addTestStep("Verify if question gets uploaded","Could not verify the uploaded question.",Status.FAIL,DriverAction.takeSnapShot());
                    }
                }
            }else {
                GemTestReporter.addTestStep("Verify if question gets uploaded","Invalid popup message- " + message,Status.ERR,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
                GemTestReporter.addTestStep("Verify Statuses and messages after uploading excel","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Get the selected languages \"([^\"]*)\", \"([^\"]*)\"$")
    public void getSelectedLanguages(String language1, String language2) {
        try{
            List<WebElement>languages=DriverAction.getElements(QuestionsLocators.selectedLanguage);
            if(language2.equals("null")) {
                _getLanguage1 = languages.get(0).getText();
                if (_getLanguage1.equals(language1)) {
                    GemTestReporter.addTestStep("Get the selected languages", "Successfully fetched the first language.", Status.PASS, DriverAction.takeSnapShot());
                }else{
                    GemTestReporter.addTestStep("Get the selected languages", "First language fetched is not correct.", Status.FAIL, DriverAction.takeSnapShot());
                }
            }
            else if(language1.equals("existing language")){
                _getLanguage2=languages.get(1).getText();
                if(_getLanguage1.equals(language1)&&_getLanguage2.equals(language2)){
                    GemTestReporter.addTestStep("Get the selected languages", "Successfully fetched the selected languages.", Status.PASS, DriverAction.takeSnapShot());
                }else{
                    GemTestReporter.addTestStep("Get the selected languages","Languages fetched are not expected.",Status.FAIL,DriverAction.takeSnapShot());
                }
            }
//            else{
//                _getLanguage1=languages.get(0).getText();
//                _getLanguage2=languages.get(1).getText();
//                if(_getLanguage1.equals(language1)&&_getLanguage2.equals(language2)){
//                    GemTestReporter.addTestStep("Get the selected languages", "Successfully fetched the selected languages.", Status.PASS, DriverAction.takeSnapShot());
//                }else{
//                    GemTestReporter.addTestStep("Get the selected languages","Languages fetched are not expected.",Status.FAIL,DriverAction.takeSnapShot());
//                }
//            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Get the selected languages","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Preview question$")
    public void previewQuestion() {
        try{
            clickButton("Preview");

        }catch(Exception e){
          GemTestReporter.addTestStep("Preview question","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify the question in dialog box \"([^\"]*)\"$")
    public void verifyQuestionInDialogBox(String question) {
        try{
            if(DriverAction.getElementText(QuestionsLocators.questionOnPreview).contains(question)){
                GemTestReporter.addTestStep("Verify the question in dialog box","Successfully verified the question in dialog box.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify the question in dialog box","Could not verify the question in dialog box.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the question in dialog box","Exception encountered- "+e,Status.ERR, DriverAction.takeSnapShot());
        }
    }

    @And("^Close the dialog box$")
    public void closeDialogBox() {
        try{
            DriverAction.waitUntilElementClickable(QuestionsLocators.closeDialogBox,4);
            DriverAction.click(QuestionsLocators.closeDialogBox,"Close the preview dialog box.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Close the dialog box","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify languages on view \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyLanguagesOnView(String language1, String language2) {
        try{
            String languagesOnView=DriverAction.getElementText(QuestionsLocators.languageOnView);
            if(languagesOnView.contains(language1)&&languagesOnView.contains(language2)){
                GemTestReporter.addTestStep("Verify languages on view.","Successfully verified the languages on view.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify languages on view.","Could not verify the languages on view.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify languages on view.","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Expand select coding languages dropdown$")
    public void expandSelectCodingLanguages() {
        try{
            DriverAction.click(QuestionsLocators.expandLanguageDropdown,"Expand select coding languages dropdown","Successfully expands select coding language dropdown.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand select coding languages dropdown","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Click the plus icon$")
    public void clickThePlusIcon() {
        try {
            DriverAction.click(QuestionsLocators.plusIcon,"Click the plus icon while creating subjective question");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Click the plus icon","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }
}





