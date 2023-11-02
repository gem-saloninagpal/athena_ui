package com.gemini.athenaUi.stepdefinitions;

import com.gemini.athenaUi.locators.*;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Role_Management {

    String Role="TRAINEE";
    @And("^Create Role and Validate Role is created \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void enterDateInDateRangeField(String roleName,String roleDesc,String roleDisplay) {
        try {
            Role=roleName;
            String arr[]={roleName,roleDesc,roleDisplay};
            for(int i=2;i<=4;i++)
            {
                DriverAction.typeText(By.xpath(Role_Management_Locators.roleFields.replace("itr",String.valueOf(i))),arr[i-2]);
            }
           if(DriverAction.isExist(By.xpath(Role_Management_Locators.permissionDropdown.replace("permissionType"," Manage Tests ")),120))
           {
              DriverAction.click(By.xpath(Role_Management_Locators.permissionDropdown.replace("permissionType"," Manage Tests ")));
              if(DriverAction.isExist(By.xpath(Role_Management_Locators.permissionDropdown.replace("permissionType"," Placement Drives ")),120))
              {
                  DriverAction.click(By.xpath(Role_Management_Locators.permissionDropdown.replace("permissionType"," Placement Drives ")));
                  if(DriverAction.isExist(By.xpath(Role_Management_Locators.permissionsCheckbox.replace("permissions"," View My Test ")),120))
                  {
                      DriverAction.click(By.xpath(Role_Management_Locators.permissionsCheckbox.replace("permissions"," View My Test ")),"clicked on View My Test checkbox");
                  }
              }
           }
           DriverAction.scrollToBottom();
            if(DriverAction.isEnabled(Role_Management_Locators.submitBtn))
            {
                DriverAction.click(Role_Management_Locators.submitBtn);
            }
            DriverAction.waitSec(5);
            List<String> roles=DriverAction.getElementsText(Role_Management_Locators.roleList);


            Set<String> stringSet = new HashSet<>();
            stringSet.addAll(roles);
            if (stringSet.contains(roleName)) {
                GemTestReporter.addTestStep("Validate Role is created and added to Table", "Role is created successfully",
                        STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate Role is created and added to Table", "Fail to create Role",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }


        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }


    @When("Edit the Role \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void editTheRole(String permissionType,String permissionSubType,String permission1,String permission2,String permission3) {
        try {
            DriverAction.waitSec(4);
            DriverAction.click(By.xpath("//table[@role='grid']"));
            DriverAction.waitSec(4);
            DriverAction.scrollIntoView(By.xpath("//table[@role='grid']//tr[last()]//td[last()]"));
//            DriverAction.scrollToBottom();
            int idx = 0;
            boolean isTrue = false;
            List<String> names = DriverAction.getElementsText(Role_Management_Locators.nameList);
            for (int i = 1; i < names.size(); i = i + 5) {
                String reqName = DriverAction.getElementText(By.xpath(Role_Management_Locators.name.replace("itr", String.valueOf(i))));
                if (reqName.equals(Role)) {
                    idx = idx + 1;
                    isTrue = true;
                    break;
                }
                idx++;
                System.out.println(reqName);
            }
           

            if (isTrue)
            {
                if (DriverAction.isExist(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(idx))), 120)) {
                    DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(idx))));
                    if (DriverAction.isExist(Role_Management_Locators.editRole, 120)) {
                        DriverAction.click(Role_Management_Locators.editRole);
                    } else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to click edit role option",
                                STATUS.FAIL, DriverAction.takeSnapShot());

                    }
                } else {
                    GemTestReporter.addTestStep("Error Occur", "Fail to click edit icon",
                            STATUS.FAIL, DriverAction.takeSnapShot());

                }
        }
            //check the permission of the role
            String[] permissionsList={permission1,permission2,permission3};
            if(DriverAction.isDisplayed(By.xpath(Role_Management_Locators.permissionDropdown.replace("permissionType"," "+permissionType+" "))))
            {
                DriverAction.click(By.xpath(Role_Management_Locators.permissionDropdown.replace("permissionType"," "+permissionType+" ")));
                if(DriverAction.isDisplayed(By.xpath(Role_Management_Locators.permissionDropdown.replace("permissionType"," "+permissionSubType+" "))))
                {
                    DriverAction.click(By.xpath(Role_Management_Locators.permissionDropdown.replace("permissionType"," "+permissionSubType+" ")));
                    DriverAction.waitSec(4);
                  for(int i=0;i<permissionsList.length;i++)
                  {
                      if(DriverAction.isExist(By.xpath(Role_Management_Locators.permissionsCheckbox.replace("permissions"," "+permissionsList[i]+" ")),120))
                      {
                          DriverAction.click(By.xpath(Role_Management_Locators.permissionsCheckbox.replace("permissions"," "+permissionsList[i]+" ")),"clicked on "+permissionsList[i]+" checkbox");
                      }
                  }
//                    if(DriverAction.isExist(By.xpath(Role_Management_Locators.permissionsCheckbox.replace("permissions"," View My Test ")),120))
//                    {
//                        DriverAction.click(By.xpath(Role_Management_Locators.permissionsCheckbox.replace("permissions"," View My Test ")),"clicked on View My Test checkbox");
//                    }
                }
                else
                {
                    for(int i=0;i<permissionsList.length;i++)
                    {
                        if(DriverAction.isExist(By.xpath(Role_Management_Locators.permissionsCheckbox.replace("permissions"," "+permissionsList[i]+" ")),120))
                        {
                            DriverAction.click(By.xpath(Role_Management_Locators.permissionsCheckbox.replace("permissions"," "+permissionsList[i]+" ")),"clicked on "+permissionsList[i]+" checkbox");
                        }
                    }
                }
            }
            DriverAction.scrollToBottom();
            DriverAction.waitSec(5);
            if(DriverAction.isEnabled(Role_Management_Locators.submitBtn))
            {
                DriverAction.click(Role_Management_Locators.submitBtn);
            }
            DriverAction.waitSec(5);

        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }

    @And("Switch the user to {string}")
    public void switchTheUserTo(String role) {
        try{
if(DriverAction.isExist(Role_Management_Locators.roleDropdown,120))
{
    DriverAction.click(Role_Management_Locators.roleDropdown);
    if(DriverAction.isExist(By.xpath(Role_Management_Locators.selectedRole.replace("role",role)),120))
    {
        DriverAction.click(By.xpath(Role_Management_Locators.selectedRole.replace("role",role)));
    }
}
else
{
    GemTestReporter.addTestStep("Error Occur", "Fail to click on dropdown icon",
            STATUS.FAIL, DriverAction.takeSnapShot());
}
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }

    @Then("^Validate the Permission for test$")
    public void validateThePermission(DataTable data) {
        try{

            List<List<String>> dataList=data.asLists(String.class);
            String testType=dataList.get(0).get(0);
//            String data1=dataList.get(0).get(0);
//            String data2=dataList.get(0).get(1);
//            String data3=dataList.get(0).get(2);

            DriverAction.waitSec(5);
            int c=0;
            if(testType.equals("Placement Drives")) {
                String status = DriverAction.getAttributeName(Role_Management_Locators.status, "ng-reflect-disabled");
                if (status.equals("true")) {
                    DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                    List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                    for (int i = 0; i < actions.size(); i++) {
                        if (dataList.get(0).get(i+1).equals(actions.get(i))) {
                            c++;
                        }
                    }
                    if (c == actions.size()) {
                        GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                                STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else {
                    DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                    List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                    for (int i = 0; i < actions.size(); i++) {
                        if (dataList.get(0).get(i+1).equals(actions.get(i))) {
                            c++;
                        }
                    }
                    if (c == actions.size()) {
                        GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                                STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }
                System.out.println(status);
            }
            else if(testType.equals("Internal Tests"))
            {
                String status = DriverAction.getAttributeName(Role_Management_Locators.statusInternal, "ng-reflect-disabled");
                if (status.equals("true")) {
                    DriverAction.click(Role_Management_Locators.editIconInternal);
                    List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                    for (int i = 0; i < actions.size(); i++) {
                        if (dataList.get(0).get(i+1).equals(actions.get(i))) {
                            c++;
                        }
                    }
                    if (c == actions.size()) {
                        GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                                STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else {
                    DriverAction.click(Role_Management_Locators.editIconInternal);
                    List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                    for (int i = 0; i < actions.size(); i++) {
                        if (dataList.get(0).get(i+1).equals(actions.get(i))) {
                            c++;
                        }
                    }
                    if (c == actions.size()) {
                        GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                                STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }
                System.out.println(status);
            }
            else
            {
                DriverAction.click(Role_Management_Locators.editIconTraining);
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 0; i < actions.size(); i++) {
                    System.out.println(dataList.get(0).get(i));
                    System.out.println(actions.get(i));
                    if (dataList.get(0).get(i).equals(actions.get(i))) {
                        c++;
                    }
                }
                if (c == actions.size()) {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }

    @Then("^Validate the Permission for \"([^\"]*)\"$")
    public void validateThePermissionForSubmodule(String subModule,DataTable data) {
        try{
            DriverAction.waitSec(4);
            boolean isTrue=false;
            if(subModule.equals("Questions")) {
                int c = 0;
                List<List<String>> dataList = data.asLists(String.class);
                if(DriverAction.isExist(Role_Management_Locators.addNewBtn,120))
                {
                    GemTestReporter.addTestStep("Validate Add button is present for Create Permission", "Button is present",
                            STATUS.FAIL, DriverAction.takeSnapShot());

                }
                else
                {
                    isTrue=true;
                    GemTestReporter.addTestStep("Validate Add button is present for Create Permission", "Button is not present",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                //validate the option in Question Screen
                DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 0; i < actions.size(); i++) {
                    if (dataList.get(0).get(i).equals(actions.get(i))) {
                        c++;
                    }
                }
                if (c == actions.size()&&isTrue) {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
            else if(subModule.equals("Course Library")) {
                int c = 0;
                List<List<String>> dataList = data.asLists(String.class);
                if(DriverAction.isExist(Role_Management_Locators.createCourseBtn,120))
                {
                    GemTestReporter.addTestStep("Validate Create button is present for Create Permission", "Button is present",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
                else
                {
                    isTrue=true;
                    GemTestReporter.addTestStep("Validate Create button is present for Create Permission", "Button is not present",
                            STATUS.PASS, DriverAction.takeSnapShot());

                }
                //validate the option in Question Screen
                DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 0; i < actions.size(); i++) {
                    if (dataList.get(0).get(i).equals(actions.get(i))) {
                        c++;
                    }
                }
                if (c == actions.size()&&isTrue) {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }

            }
            else if(subModule.equals("Batches"))
            {
                int c = 0;
                List<List<String>> dataList = data.asLists(String.class);
                if(DriverAction.isExist(Role_Management_Locators.createBatchBtn,120))
                {
                    isTrue=true;
                    GemTestReporter.addTestStep("Validate Create button is present for Create Permission", "Button is present",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Create button is present for Create Permission", "Not present",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
                DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 0; i < actions.size(); i++) {
                    if (dataList.get(0).get(i).equals(actions.get(i))) {
                        c++;
                    }
                }
                if (c == actions.size()&&isTrue) {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
            else if(subModule.equals("Campus"))
            {
                int c = 0;
                List<List<String>> dataList = data.asLists(String.class);
                if(DriverAction.isExist(Role_Management_Locators.registerCampusBtn,120))
                {
                    isTrue=true;
                    GemTestReporter.addTestStep("Validate Create button is present for Create Permission", "Button is present",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Create button is present for Create Permission", "Not present",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
                DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 0; i < actions.size(); i++) {
                    if (dataList.get(0).get(i).equals(actions.get(i))) {
                        c++;
                    }
                }
                if (c == actions.size()) {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
            else if(subModule.equals("User Management"))
            {
                int c = 0;
                List<List<String>> dataList = data.asLists(String.class);
                if(DriverAction.isExist(Role_Management_Locators.registerBtn,120))
                {
                    isTrue=true;
                    GemTestReporter.addTestStep("Validate Create button is present for Create Permission", "Button is present",
                            STATUS.PASS, DriverAction.takeSnapShot());
                }
                else
                {
                    GemTestReporter.addTestStep("Validate Create button is present for Create Permission", "Not present",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
                DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 0; i < actions.size(); i++) {
                    if (dataList.get(0).get(i).equals(actions.get(i))) {
                        c++;
                    }
                }
                if (c == actions.size()) {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
            else
            {
                int c = 0;
                List<List<String>> dataList = data.asLists(String.class);

                //validate the option in Content Library
                DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 0; i < actions.size(); i++) {
                    if (dataList.get(0).get(i).equals(actions.get(i))) {
                        c++;
                    }
                }
                if (c == actions.size()) {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Permissions are matched successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions matched which are selected", "Fail to match permissions",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }


    @Then("^Validate permissions not granted should not be there on screen$")
    public void validatePermissionsNotGrantedShouldNotBeThereOnScreen(DataTable data) {
        try{
            List<List<String>> dataList = data.asLists(String.class);

            String button=dataList.get(0).get(0);

            int c=0;
            boolean isTrue=false;
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//            DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
//            DriverAction.waitSec(3);

            String data1=dataList.get(0).get(1);
            if("Create".equals(data1))
            {
                if(!DriverAction.isDisplayed(By.xpath(Role_Management_Locators.reqBtn.replace("input",button))))
                  {
                      isTrue=true;
                      GemTestReporter.addTestStep("Validate Permissions which are not Granted should not be visible on screen", "Validated successfully",
                              STATUS.PASS, DriverAction.takeSnapShot());
                  }
                else
                  {

                      GemTestReporter.addTestStep("Validate Permissions which are not Granted should not be visible on screen", "They are Visible",
                              STATUS.FAIL, DriverAction.takeSnapShot());
                  }
                DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 2; i < dataList.size(); i++) {
                    for(int j=0;j<actions.size();j++) {
                        if (dataList.get(0).get(i).equals(actions.get(j))) {
                            c++;
                        }
                    }
                }
                if (c==0&&isTrue) {
                    GemTestReporter.addTestStep("Validate Permissions which are not Granted should not be visible on screen", "Permissions are not Granted Successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions which are not Granted should not be visible on screen", "They are Visible",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
            else
            {
//                DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                List<String> actions = DriverAction.getElementsText(Role_Management_Locators.actionList);
                for (int i = 2; i < dataList.size(); i++) {
                    for(int j=0;j<actions.size();j++) {
                        if (dataList.get(0).get(i).equals(actions.get(j))) {
                            c++;
                        }
                    }
                }
                if (c == 0) {
                    GemTestReporter.addTestStep("Validate Permissions which are not Granted should not be visible on screen", "Permissions are not Granted Successfully",
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Validate Permissions which are not Granted should not be visible on screen", "They are Visible",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }

        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }

    }

    @And("Delete the Created Role")
    public void deleteTheCreatedRole() {
        try{
            DriverAction.waitSec(4);
            DriverAction.click(By.xpath("//table[@role='grid']"));
            DriverAction.waitSec(4);
            DriverAction.scrollIntoView(By.xpath("//table[@role='grid']//tr[last()]//td[last()]"));
//            DriverAction.scrollToBottom();
            int idx = 0;
            boolean isTrue = false;
            List<String> names = DriverAction.getElementsText(Role_Management_Locators.nameList);
            for (int i = 1; i < names.size(); i = i + 5) {
                String reqName = DriverAction.getElementText(By.xpath(Role_Management_Locators.name.replace("itr", String.valueOf(i))));
                if (reqName.equals(Role)) {
                    idx = idx + 1;
                    isTrue = true;
                    break;
                }
                idx++;
                System.out.println(reqName);
            }


            if (isTrue)
            {
                if (DriverAction.isExist(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(idx))), 120)) {
                    DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(idx))));
                    if (DriverAction.isExist(Role_Management_Locators.removeRole, 120)) {
                        DriverAction.click(Role_Management_Locators.removeRole);
                    } else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to click remove role option",
                                STATUS.FAIL, DriverAction.takeSnapShot());

                    }
                } else {
                    GemTestReporter.addTestStep("Error Occur", "Fail to click remove icon",
                            STATUS.FAIL, DriverAction.takeSnapShot());

                }

                if(DriverAction.isExist(Role_Management_Locators.yesBtn,120))
                {
                    DriverAction.click(Role_Management_Locators.yesBtn);
                }
                else
                {
                    GemTestReporter.addTestStep("Error Occur", "Fail to click yes button",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }

        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }


    @When("{string} is unassigned with the {string} which is going to be delete")
    public void isUnassignedWithTheWhichIsGoingToBeDelete(String user, String role) {
        try{
            DriverAction.waitSec(4);
            if(DriverAction.isExist(Role_Management_Locators.userInput,120))
            {
                DriverAction.typeText(Role_Management_Locators.userInput,user);
                DriverAction.waitSec(4);
                if (DriverAction.isExist(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))), 120)) {
                    DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                    if (DriverAction.isExist(Role_Management_Locators.editProfile, 120)) {
                        DriverAction.click(Role_Management_Locators.editProfile);
                    } else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to click remove role option",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }

            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in input",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(Role_Management_Locators.slider,120))
            {
                DriverAction.click(Role_Management_Locators.slider);
            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Enable editing",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(Role_Management_Locators.userRoleDropdown,120))
            {
                DriverAction.click(Role_Management_Locators.userRoleDropdown);
                if(DriverAction.isExist(Role_Management_Locators.input,120))
                {
                    DriverAction.typeText(Role_Management_Locators.input,role);
                }
                DriverAction.click(Role_Management_Locators.checkbox);
            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Dropdown",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(Role_Management_Locators.updateBtn,120))
            {
                DriverAction.click(Role_Management_Locators.updateBtn);
            }
                  else
                {
                    GemTestReporter.addTestStep("Error Occur", "Fail to click on Update button",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }


        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }

    @Then("Delete Role and validate it is deleted")
    public void deleteRoleAndValidateItIsDeleted() {
        try{
            DriverAction.waitSec(4);
            DriverAction.click(By.xpath("//table[@role='grid']"));
            DriverAction.waitSec(4);
            DriverAction.scrollIntoView(By.xpath("//table[@role='grid']//tr[last()]//td[last()]"));
//            DriverAction.scrollToBottom();
            int idx = 0;
            boolean isTrue = false;
            List<String> names = DriverAction.getElementsText(Role_Management_Locators.nameList);
            for (int i = 1; i < names.size(); i = i + 5) {
                String reqName = DriverAction.getElementText(By.xpath(Role_Management_Locators.name.replace("itr", String.valueOf(i))));
                if (reqName.equals(Role)) {
                    idx = idx + 1;
                    isTrue = true;
                    break;
                }
                idx++;
                System.out.println(reqName);
            }


            if (isTrue)
            {
                if (DriverAction.isExist(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(idx))), 120)) {
                    DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(idx))));
                    if (DriverAction.isExist(Role_Management_Locators.removeRole, 120)) {
                        DriverAction.click(Role_Management_Locators.removeRole);
                        DriverAction.click(Role_Management_Locators.yesBtn);
                    } else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to click Remove role option",
                                STATUS.FAIL, DriverAction.takeSnapShot());

                    }
                } else {
                    GemTestReporter.addTestStep("Error Occur", "Fail to click Remove icon",
                            STATUS.FAIL, DriverAction.takeSnapShot());

                }
            }

            //validate after removing the role
            isTrue=false;
            List<String> namesAfterDeleting = DriverAction.getElementsText(Role_Management_Locators.nameList);
            for (int i = 1; i < namesAfterDeleting.size(); i = i + 5) {
                String reqName = DriverAction.getElementText(By.xpath(Role_Management_Locators.name.replace("itr", String.valueOf(i))));
                if (reqName.equals(Role)) {
                    idx = idx + 1;
                    isTrue = true;
                    break;
                }
                idx++;

            }
            if(!isTrue)
            {
                GemTestReporter.addTestStep("Validate Role is deleted Successfully", "Role is deleted successfully",
                        STATUS.PASS, DriverAction.takeSnapShot());
            }
            else {
                GemTestReporter.addTestStep("Validate Role is deleted Successfully", "Not able to delete Role",
                        STATUS.FAIL, DriverAction.takeSnapShot());

            }


        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }


    @Then("Assign the {string} to the {string}")
    public void assignTheToThe(String role, String user) {
        try{
            //assign role to the user
            DriverAction.waitSec(4);
            if(DriverAction.isExist(Role_Management_Locators.userInput,120))
            {
                DriverAction.typeText(Role_Management_Locators.userInput,user);
                DriverAction.waitSec(4);
                if (DriverAction.isExist(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))), 120)) {
                    DriverAction.click(By.xpath(Role_Management_Locators.editIcon.replace("itr", String.valueOf(1))));
                    if (DriverAction.isExist(Role_Management_Locators.editProfile, 120)) {
                        DriverAction.click(Role_Management_Locators.editProfile);
                    } else {
                        GemTestReporter.addTestStep("Error Occur", "Fail to click remove role option",
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }

            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to type in input",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(Role_Management_Locators.slider,120))
            {
                DriverAction.click(Role_Management_Locators.slider);
            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Enable editing",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }

            if(DriverAction.isExist(Role_Management_Locators.userRoleDropdown,120))
            {
                DriverAction.click(Role_Management_Locators.userRoleDropdown);
                if(DriverAction.isExist(Role_Management_Locators.input,120))
                {
                    DriverAction.typeText(Role_Management_Locators.input,role);
                }
                DriverAction.waitSec(3);
                DriverAction.click(Role_Management_Locators.checkbox);
            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Dropdown",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if(DriverAction.isExist(Role_Management_Locators.updateBtn,120))
            {
                DriverAction.click(Role_Management_Locators.updateBtn);
                String popupMessage=DriverAction.getElementText(UserDashboard_Locator.popupMessage);
                String reqPopUpMessage="User updated successfully.";
                if(reqPopUpMessage.equals(popupMessage))
                {
                    GemTestReporter.addTestStep("Validate user is assign successfully", "User is assigned Successfully" , STATUS.PASS,
                            DriverAction.takeSnapShot());
                }
                else {
                    GemTestReporter.addTestStep("Validate user is assign successfully", "Fail to assign the user" , STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }

            }
            else
            {
                GemTestReporter.addTestStep("Error Occur", "Fail to click on Update button",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }


        }
        catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED" + e, STATUS.FAIL);
        }
    }
}
