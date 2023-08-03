package com.gemini.athena.stepdefinitions;

import com.gemini.athena.locators.MyLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Course {
    @Then("^Enter respective values in content fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterContent(String contentName, String contentTags, String filetype, String duration, String file) {
        try {
            List<WebElement> inputFields= DriverAction.getElements(MyLocators.contentInputFields);
            String inputValues[]={contentName,contentTags,filetype,duration};
            for(int i=0;i<inputFields.size();i++){
                String dropdown=inputFields.get(i).getAttribute("aria-haspopup");
                String upload=inputFields.get(i).getAttribute("type");
                //dropdown
                if(dropdown!=null&&dropdown.equals("listbox")){

                    DriverAction.click(MyLocators.dropdownIcon);
                    DriverAction.click(By.xpath(MyLocators.option.replace("input",inputValues[i])));
                }
                //file-upload
                else if(upload!=null&&upload.equals("file")){
                     DriverAction.fileUpload(inputFields.get(i),"C:\\Users\\saloni.nagpal\\Pictures\\Screenshots");
                }
                //textbox
                else{

                    DriverAction.typeText(inputFields.get(i),inputValues[i]);
                }

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enter respective values in input fields", "Exception encountered- " + e, STATUS.ERR);
        }
    }

    @And("^Enter description \"([^\"]*)\"$")
    public void addDescription(String description){
        try{

            DriverAction.typeText(MyLocators.textarea,description);
        }catch(Exception e){

            GemTestReporter.addTestStep("Enter description","Exception encountered- "+e,STATUS.ERR);
        }
    }
}
