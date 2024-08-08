Feature: Server Side rendered test

  Background:
    #  And Logout of portal
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

    @in-progress
  Scenario Outline:Create server side rendered test
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Fill all the fields in select options section "Yes","Yes","Yes","Yes","Yes","Yes"
    And Click the button "Next"
    And Add cut off percentage "<Percentage>"
    And Click the button "Add"
    Then Validate Server Side section is created
    And Click the button "Add Questions"
    And Click the button "Choose Specific"
    When Add questions in test

    Examples:
    |module|submodule   |Duration|Campus    |Level   |Description|Section    |Percentage|
    |Tests |Test Control|0030    |DELL, DELL|Beginner|abc        |Server Side|5         |



