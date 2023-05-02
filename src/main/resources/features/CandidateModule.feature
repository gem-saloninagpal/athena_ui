Feature: Candidate module features

  Background:Check login to candidate module
    Given Login using "test1@hotmail.com" and "abc@123"

  Scenario Outline: Login flow for Candidate
    Then Verify Candidate is logged in
    Then Expand info dropdown from navbar
    And Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    Then Select change password from dropdown and verify the dialog box
    Then Verify the email of candidate "test1@hotmail.com"

    Examples:
      |option1|option2        |option3|
      |Profile|Change Password|Logout |

  Scenario Outline: Verify the default tab selected and number of tests assigned
    Then Verify Candidate is logged in
    Then Verify the default tab selected "<tab>"
    And Verify the number of Tests "<activeTests>", "<tab>"
    Then Switch to "Completed Tests"
    And Verify the number of Tests "<completedTests>", "<tab2>"

    Examples:
      |tab         |activeTests|completedTests|tab2|
      |Active Tests|3          |2             |Completed Tests|