package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.MyLocators;
import com.gemini.athenaUi.locators.QuestionsLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static com.gemini.athenaUi.stepdefinitions.CandidateModule_UserManagement.*;

public class Questions {
    String _question;
    String _passage;
    String _passageQues;
    String _existingQues;
    String _comprehensionSubjectiveQues1;
    String _comprehensionSubjectiveQues2;
    String _movieName;
    String _movieDescription;
    String _updatedPassage;
    String _updateComprehensionQuestion;

    @And("^Select dropdown values in question fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void questionDropdownValues(String level, String type, String section, String difficulty, String skills) {
        try {
            String[] fields = {level, type, section, difficulty, skills};
            String[] fieldName = {"level", "type", "section", "difficulty", "skills"};
            List<WebElement> dropdowns = DriverAction.getElements(QuestionsLocators.dropdownFields);
            //select level, type, section etc. while creating a question
            for (int i = 0; i < dropdowns.size(); i++) {
                DriverAction.click(dropdowns.get(i));
                DriverAction.click(By.xpath(QuestionsLocators.dropdownValue.replace("input", fields[i])));
                GemTestReporter.addTestStep("Select " + fields[0] + " in " + fieldName[i], "Successfully selected " + fields[0], STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Select dropdown values in question fields", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("Enter marks {string}")
    public void enterMarks(String marks) {
        try {
            DriverAction.typeText(QuestionsLocators.marksField, marks);
            GemTestReporter.addTestStep("Enter marks-" + marks + " in marks field", "Successfully entered the marks- " + marks, STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter marks", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("Enter question description {string}")
    public void enterQuestionDescription(String questionStatement) {
        try {
            DriverAction.waitSec(4);
            DriverAction.typeText(QuestionsLocators.questionBox, questionStatement);
            GemTestReporter.addTestStep("Enter question description", "Successfully added the question- " + questionStatement, STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter question description", "Exception encountered- " + e, STATUS.ERR);
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
                DriverAction.typeText(QuestionsLocators.optionsBox, text);
                DriverAction.click(QuestionsLocators.addButton, "Click the add button");
                GemTestReporter.addTestStep("Enter option- " + text, "Successfully added the option- " + text, STATUS.PASS);
            }
            Thread.sleep(4000);
            DriverAction.click(QuestionsLocators.selectOption, "Successfully selected the option");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter options", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Verify the question is created \"([^\"]*)\", \"([^\"]*)\"$")
    public void verifyQuestionIsCreated(String question1, String question2) {
        try {
            String[] ques = {question2, question1};
            //verifying recently created 2 questions from the questions table
            List<WebElement> questions = DriverAction.getElements(QuestionsLocators.firstColumn);
            int c = 0;
            for (int i = 0; i <= 1; i++) {
                String quesStatement = DriverAction.getElementText(questions.get(i));
                if (quesStatement.contains(ques[i])) {
                    c++;
                }
            }
            if (c == 2) {
                GemTestReporter.addTestStep("Verify question is saved", "Successfully verified that question is saved.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify question is saved", "Could not verify that question is saved.", STATUS.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the question is saved", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Enter word limit \"([^\"]*)\"$")
    public void enterWordLimit(String words) {
        try {
            DriverAction.typeText(QuestionsLocators.wordLimitTextbox, words);
            GemTestReporter.addTestStep("Enter word limit", "Successfully entered the limit", STATUS.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter word limit", "Exception encountered- " + e, STATUS.ERR, DriverAction.takeSnapShot());
        }

    }

    @And("^Enter question description in subjective$")
    public void questionDescriptionSubjective() {
        try {
            _existingQues = _question;
            _question = generateUniqueEmail();
            DriverAction.typeText(QuestionsLocators.questionBox, _question, "Successfully entered the question description.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter question description in subjective", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Enter the passage$")
    public void enterPassage() {
        try {
            //here generateUniqueEmail function is generating a unique string
            _passage = generateUniqueEmail();
            DriverAction.typeText(QuestionsLocators.passageBox, _passage, "Successfully entered the passage.");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter the passage", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Verify the passage and edit$")
    public void verifyPassageAndEdit() {
        String text = DriverAction.getElementText(QuestionsLocators.passage);
        if (text.contains(_passage)) {
            GemTestReporter.addTestStep("Verify the passage", "Successfully verified the passage.", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the passage", "Could not verify the passage.", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        _passage = "updated passage is- " + _passage;
        DriverAction.typeText(QuestionsLocators.passage, _passage);
        GemTestReporter.addTestStep("Edit the passage", "Successfully edited the passage.", STATUS.PASS);
    }

    @Then("^Verify the passage is created$")
    public void verifyPassageCreated() {
        try {
            String text = DriverAction.getElementText(QuestionsLocators.passageStatement);
            if (text.contains(_passage)) {
                GemTestReporter.addTestStep("Verify the passage is created", "Successfully verified the passage.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the passage is created", "Could not verify the passage.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the passage is created", "Exception encountered- " + e, STATUS.ERR);
        }

    }

    @And("^Expand the passage field$")
    public void expandPassageField() {
        try {
            DriverAction.click(QuestionsLocators.expandPassage, "Expand the passage field", "Successfully expanded the passage field");
            DriverAction.waitSec(2);
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand the passage field","Exception encountered- "+e,STATUS.ERR);
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
                GemTestReporter.addTestStep("Verify comprehension question is saved", "Successfully verified that question is saved.", STATUS.PASS,DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify comprehension question is saved", "Could not verify that question is saved.", STATUS.FAIL,DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify comprehension question is saved", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Click Add New in comprehensions tab$")
    public void AddNewComprehensionsTab() {
        try {
            DriverAction.click(QuestionsLocators.addNewComprehension);
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Add New in comprehensions tab","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Enter question description related passage \"([^\"]*)\"$")
    public void enterQuestionDescriptionRelatedPassage(String ques) {
        try {
            //here the function generates unique string
            _passageQues = generateUniqueEmail();
            DriverAction.typeText(QuestionsLocators.passageQuestionBox, ques);
        }catch(Exception e){
            GemTestReporter.addTestStep("Enter question description related passage","Exception encountered- "+e,STATUS.ERR);
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
                GemTestReporter.addTestStep("Verify the popup message", "Successfully verified popup message " + errorMessage, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the popup message", "Could not verify the popup message " + errorMessage, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the button- "+buttonName+" and verify message- "+message,"Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify the subjective questions$")
    public void verifySubjectiveQuestions() {
        try {
            String[] ques = {_existingQues, _question};
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
                GemTestReporter.addTestStep("Verify the subjective question is saved", "Successfully verified that subjective question is saved.", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Verify the subjective question is saved", "Could not verify that subjective question is saved.", STATUS.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the subjective question is saved", "Exception encountered- " + e, STATUS.ERR);
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
            GemTestReporter.addTestStep("Enter comprehension based subjective question", "Exception encountered- " + e, STATUS.ERR);
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
                GemTestReporter.addTestStep("Verify upload movie clip dialog box displays","Successfully verified the movie clip dialog box.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify upload movie clip dialog box displays","Could not verify the movie clip dialog box.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify upload movie clip dialog box displays","Exception encountered- "+e,STATUS.ERR);

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
            GemTestReporter.addTestStep("Enter movie name and description","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Upload a video \"([^\"]*)\"$")
    public void uploadAVideo(String movieLocation) {
        try {
            DriverAction.fileUpload(QuestionsLocators.chooseBtn, movieLocation);
        }catch(Exception e){
            GemTestReporter.addTestStep("Upload a video","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Click the upload button$")
    public void clickUploadButton() {
        try{
            DriverAction.click(QuestionsLocators.uploadBtn,"Click the upload button");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the upload button","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify video is uploaded$")
    public void verifyVideoIsUploaded() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.video)){
                GemTestReporter.addTestStep("Verify video is uploaded","Successfully verified the uploaded video",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify video is uploaded","Could not verify the uploaded video",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify video is uploaded","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Expand the video field$")
    public void expandVideoField() {
        try {
            DriverAction.click(QuestionsLocators.expandVideo, "Expand the video field");
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand the video field","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify the video based question is created \"([^\"]*)\",\"([^\"]*)\"$")
    public void verifyVideoBasedQuestionCreated(String videoQues1,String videoQues2) {
        try {
            String[]ques=new String[]{videoQues1,videoQues2};
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
                GemTestReporter.addTestStep("Verify video question is saved", "Successfully verified that question is saved.", STATUS.PASS,DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify video question is saved", "Could not verify that question is saved.", STATUS.FAIL,DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify video question is saved", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @Then("^Verify the video name and description$")
    public void verifyTheVideoNameAndDescription() {
        try {
            String videoName = DriverAction.getElementText(QuestionsLocators.videoName);
            String description = DriverAction.getElementText(QuestionsLocators.videoDescription);
            if (videoName.contains(_movieName) && description.contains(_movieDescription)) {
                GemTestReporter.addTestStep("Verify the video name and description", "Successfully verified the video name and description.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the video name and description", "Could not verify the video name and description.", STATUS.FAIL, DriverAction.takeSnapShot());
            }

        }catch (Exception e){
            GemTestReporter.addTestStep("Verify the video name and description","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @When("^Click Actions icon of recently created question$")
    public void clickActionsIconOfRecentlyCreatedQuestion() throws InterruptedException {
        try {
            Thread.sleep(5000);
            DriverAction.click(MyLocators.contentActionsIcon);
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Actions icon of recently created question","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Click the edit icon$")
    public void clickTheEditIcon() {
        try {
            DriverAction.click(QuestionsLocators.editIcon, "Click the edit icon");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the edit icon","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Verify question dialog box opens$")
    public void verifyQuestionDialogBoxOpens() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.questionDialog)){
                GemTestReporter.addTestStep("Verify question dialog box opens","Successfully verified the question dialog box.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify question dialog box opens","Could not verify the question dialog box.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify question dialog box opens","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("Verify question on view \"([^\"]*)\"")
    public void verifyQuestion(String question) {
        try {
            String getQuestion = DriverAction.getElementText(QuestionsLocators.questionInDialogBox);
            if (getQuestion.contains(question)) {
                GemTestReporter.addTestStep("Verify question on view", "Successfully verified the question", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify question on view", "Could not verify the question", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the question on view","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify confirmation dialog box appears$")
    public void verifyConfirmationDialogBoxAppears() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.confirmationDialog)){
                GemTestReporter.addTestStep("Verify confirmation dialog box appears","Successfully verified the confirmation dialog box.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify confirmation dialog box appears","Could not verify the confirmation dialog box.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify confirmation dialog box appears","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Click actions icon of recently created passage$")
    public void clickActionsIconOfRecentlyCreatedPassage() {
        try{
            DriverAction.click(QuestionsLocators.passageActionsIcon,"Click Actions icon of recently created passage");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Actions icon of recently created passage","Exception encountered- "+e,STATUS.ERR);
        }

    }

    @And("^Verify comprehension dialog box displays$")
    public void verifyComprehensionDialogBoxDisplays() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.comprehensionDialog)){
                GemTestReporter.addTestStep("Verify comprehension dialog box displays","Successfully verified the comprehension dialog box.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify comprehension dialog box displays","Could not verify the comprehension dialog box.",STATUS.FAIL,DriverAction.takeSnapShot());
            }

        }catch(Exception e){
            GemTestReporter.addTestStep("Verify comprehension dialog box displays","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify passage on view$")
    public void verifyPassageOnView() {
        try{
            String rcPassage=DriverAction.getElementText(QuestionsLocators.rcPassage);
            if(rcPassage.contains(_passage)){
                GemTestReporter.addTestStep("Verify passage on view","Successfully verified the passage on view.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify passage on view","Could not verify the passage on view.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify passage on view","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
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
            GemTestReporter.addTestStep("Update the passage","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @Then("^Verify passage gets updated$")
    public void verifyPassageGetsUpdated() {
        try {
            String text = DriverAction.getElementText(QuestionsLocators.passageStatement);
            if (text.contains(_updatedPassage)) {
                GemTestReporter.addTestStep("Verify the passage is updated", "Successfully verified the updated passage.", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the passage is updated", "Could not verify the updated passage.", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify the passage is updated", "Exception encountered- " + e, STATUS.ERR);
        }
    }


    @And("^Click actions icon of recently created video$")
    public void clickActionsIconOfRecentlyCreatedVideo() {
        try{
            DriverAction.click(QuestionsLocators.videoActionsIcon,"Click Actions icon of recently created passage");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click Actions icon of recently created passage","Exception encountered- "+e,STATUS.ERR);
        }
    }

    @And("^Verify video dialog box displays$")
    public void verifyVideoDialogBoxDisplays() {
        try{
            if(DriverAction.isDisplayed(QuestionsLocators.videoDialog)){
                GemTestReporter.addTestStep("Verify video dialog box displays","Successfully verified the video dialog box.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify video dialog box displays","Could not verify the video dialog box.",STATUS.FAIL,DriverAction.takeSnapShot());
            }

        }catch(Exception e){
            GemTestReporter.addTestStep("Verify video dialog box displays","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify movie name and description on view$")
    public void verifyMovieNameAndDescriptionOnView() {
        try{
            String videoNameDescription=DriverAction.getElementText(QuestionsLocators.videoNameDescriptionOnView);
            if(videoNameDescription.contains(_movieName)&&videoNameDescription.contains(_movieDescription)){
                GemTestReporter.addTestStep("Verify movie name and description on view","Successfully verified movie name and description on view.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify movie name and description on view","Could not verify movie name and description on view.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify movie name and description on view","Exception encountered- "+e,STATUS.ERR);
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
              GemTestReporter.addTestStep("Verify the state of video","Successfully verified the video state as- "+videoState,STATUS.PASS,DriverAction.takeSnapShot());
          }else if(isPresent && videoState.equals("deleted")){
              GemTestReporter.addTestStep("Verify the state of video","Successfully verified the video state as- "+videoState,STATUS.PASS,DriverAction.takeSnapShot());
          }
          else{
              GemTestReporter.addTestStep("Verify the state of video","Could not verify the video state as- "+videoState,STATUS.FAIL,DriverAction.takeSnapShot());
          }
      }catch(Exception e){
          GemTestReporter.addTestStep("Verify the state of video","Exception encountered- "+e,STATUS.ERR);
      }
    }

    @When("^Search a video$")
    public void searchAVideo() {
        try {
            DriverAction.typeText(QuestionsLocators.videoSearchbox, _movieName,"Search movie name");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Search a video", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Search a passage$")
    public void searchAPassage() {
        try {
            DriverAction.typeText(QuestionsLocators.passageSearchbox, _updatedPassage,"Search a passage");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Search a passage", "Exception encountered- " + e, STATUS.ERR);
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
                GemTestReporter.addTestStep("Verify the state of passage","Successfully verified the passage state as- "+state,STATUS.PASS,DriverAction.takeSnapShot());
            }else if(isPresent && state.equals("deleted")){
                GemTestReporter.addTestStep("Verify the state of passage","Successfully verified the passage state as- "+state,STATUS.PASS,DriverAction.takeSnapShot());
            }
            else{
                GemTestReporter.addTestStep("Verify the state of passage","Could not verify the passage state as- "+state,STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the state of passage","Exception encountered- "+e,STATUS.ERR);
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
            GemTestReporter.addTestStep("Delete all the questions associated","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
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
            GemTestReporter.addTestStep("Delete all the questions associated to video","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Get comprehension based question statement$")
    public void getComprehensionBasedQuestionStatement() {
        try{
            String question=DriverAction.getElementText(QuestionsLocators.expandPassageFirstColumn);
            GemTestReporter.addTestStep("Get comprehension based question statement","Successfully fetched the question statement- "+question,STATUS.PASS,DriverAction.takeSnapShot());
        }catch(Exception e){
            GemTestReporter.addTestStep("Get comprehension based question statement","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("^Edit comprehension based question$")
    public void editComprehensionBasedQuestion() {
        try{
            DriverAction.click(QuestionsLocators.editComprehensionBasedQuestion,"Click the edit icon","Successfully clicked the edit icon");
        }catch(Exception e){
            GemTestReporter.addTestStep("Click the edit icon","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
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
            GemTestReporter.addTestStep("Update comprehension based question","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify the updated comprehension question$")
    public void verifyTheUpdatedComprehensionQuestion() {
        try{
            String updatedQuestion=DriverAction.getElementText(QuestionsLocators.expandPassageFirstColumn);//first column of expanded passage is the updated question
            if(updatedQuestion.equals(_updateComprehensionQuestion)){
                GemTestReporter.addTestStep("Verify the updated comprehension question","Successfully updated the comprehension question.",STATUS.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Verify the updated comprehension question","Could not update the comprehension question.",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Verify the updated question","Exception encountered- "+e,STATUS.ERR,DriverAction.takeSnapShot());
        }
    }
}


