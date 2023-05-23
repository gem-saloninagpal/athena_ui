Feature: Candidate module features

  Background:Check login to candidate module
    Given Login using "test1@hotmail.com" and "abc@123"

    @regression01
  Scenario Outline: Login flow for Candidate
    #Then Verify Candidate is logged in
    Then Expand info dropdown from navbar
    And Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    Then Select change password from dropdown and verify the dialog box
    Then Verify the email of candidate "test1@hotmail.com"

    Examples:
      |option1|option2        |option3|
      |Profile|Change Password|Logout |

      @regression01
  Scenario Outline: Verify the default tab selected and number of tests assigned
   # Then Verify Candidate is logged in
    Then Verify the default tab selected "<tab>"
    And Verify the number of Tests "<activeTests>", "<tab>"
    Then Switch to "Completed Tests"
    And Verify the number of Tests "<completedTests>", "<tab2>"

    Examples:
      |tab         |activeTests|completedTests|tab2|
      |Active Tests|1          |2             |Completed Tests|

  @regression01
  Scenario Outline: Explore tests in Profile
  #  Then Verify Candidate is logged in
    Then Expand info dropdown from navbar
    And Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    Then Select "Profile" from dropdown
    Then Click the button "Explore Tests"
    And Verify user navigates to dashboard

    Examples:
      |option1|option2        |option3|
      |Profile|Change Password|Logout |

  @regression01
  Scenario Outline: Validate password errors
    Then Expand info dropdown from navbar
    And Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    Then Select "Change Password" from dropdown
    And Click the button "Change Password"
    And Verify the error displayed in input fields "<error>" "<countMandatoryFields>"
    Then Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
    Then Enter a different password in confirm password field "k", "Confirm Password"
    And Verify the error displayed in input fields "password mismatch" "<countMandatoryFields1>"
    Then Verify user not able to edit email

    Examples:
      |option1|option2        |option3|error                 |countMandatoryFields|countMandatoryFields1|
      |Profile|Change Password|Logout |This Field is required|3                   |1                   |


    Scenario Outline: Verify test date and start test
      Then Verify the default tab selected "<tab>"
      And Verify the number of Tests "<activeTests>", "<tab>"
      Then Get start and end date of test and verify current date is within range
#      And Verify current date is within the test date range
#      Then Start test and verify instructions video is displayed

      Examples:
        |tab         |activeTests|month|
        |Active Tests|1          |May  |



