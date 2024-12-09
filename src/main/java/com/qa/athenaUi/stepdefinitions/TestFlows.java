package com.qa.athenaUi.stepdefinitions;

import com.qa.athenaUi.locators.TestFlowLocators;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.utils.ui.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestFlows {
    @And("^Fill all the fields in select options section \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void fillSelectOptionsSection(String option1,String option2,String option3,String option4,String option5,String option6) {
        try{
            String[] fields =new String[]{"Test Reattempt,Suppress auto test submission,Show scores after submissions,Switch between sections,Shuffle Questions,Server Side Rendered"};
            String[] option =new String[]{option1,option2,option3,option4,option5,option6};
            List<WebElement> selectTestOptions= DriverAction.getElements(TestFlowLocators.testOptions);
            int totalFields=selectTestOptions.size();

            for(int i=0;i<totalFields;i++){
                Thread.sleep(3000);
                DriverAction.waitSec(2);
                DriverAction.click(selectTestOptions.get(i),"Click "+fields[i],"Successfully expanded "+fields[i]);
                DriverAction.click(By.xpath(TestFlowLocators.selectOption.replace("option",option[i])),"Select option- "+option[i]+" from "+fields[i]);
                selectTestOptions= DriverAction.getElements(TestFlowLocators.testOptions);
            }
            DriverAction.typeText(TestFlowLocators.questionsForEachUser,"4","Successfully entered question for each user.");
            DriverAction.typeText(TestFlowLocators.totalMarks,"2","Successfully entered total marks of test.");
        }catch(Exception e){
            GemTestReporter.addTestStep("Fill all the fields in select options section","Exception encountered- "+e, Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @Then("^Validate Server Side section is created$")
    public void validateServerSideSectionIsCreated() {
        try{
            if(DriverAction.getElementText(TestFlowLocators.ticketCreated).equals("Server Side")){
                GemTestReporter.addTestStep("Validate Server Side section is created","Successfully created server side rendered section.",Status.PASS,DriverAction.takeSnapShot());
            }else{
                GemTestReporter.addTestStep("Validate Server Side section is created","Server side section is not created.",Status.FAIL,DriverAction.takeSnapShot());
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Validate Server Side section is created","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @When("^Add questions in test$")
    public void addQuestionsInTest() {
        try{
            List<WebElement>questions=DriverAction.getElements(TestFlowLocators.questions);
            for(int i=0;i<=1;i++){
                DriverAction.click(questions.get(i),"Add a question","Successfully added a question.");
            }
        }catch(Exception e){
            GemTestReporter.addTestStep("Add questions in test","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }

    @And("Add cut off percentage {string}")
    public void addCutOffPercentage(String percentage) {
        try{
            DriverAction.typeText(TestFlowLocators.percentageColumn,percentage,"Add cut off percentage as- "+percentage);
        }catch(Exception e){
            GemTestReporter.addTestStep("Add cut off percentage","Exception encountered- "+e,Status.ERR,DriverAction.takeSnapShot());
        }
    }
}
