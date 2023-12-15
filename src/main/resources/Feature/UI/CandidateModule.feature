Feature: Candidate module features

  Background:Check login to candidate module
    And Navigate to login page
    Then Login using "candidate@gmail.com" and "abc@123"

@screenRevamped-toBeChecked
  @regressionCandidate @working
  Scenario Outline: Login flow for Candidate
    When Expand info dropdown from navbar
    Then Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    And Select change password from dropdown and verify the dialog box
    Then Verify the email of candidate "candidate@gmail.com"

    Examples:
      |option1|option2        |option3|
      |Profile|Change Password|Logout |

  @regressionCandidate @working
  Scenario Outline: Explore tests in Profile
    When Expand info dropdown from navbar
    Then Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    And Select "Profile" from dropdown
    And Click the button "Explore Tests"
    Then Verify user navigates to dashboard

    Examples:
      |option1|option2        |option3|
      |Profile|Change Password|Logout |

  @regressionCandidate @working
  Scenario Outline: Validate password errors
    And Expand info dropdown from navbar
    Then Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    When Select "Change Password" from dropdown
    And Click the button "Change Password"
    Then Verify the error displayed in input fields "<error>" "<countMandatoryFields>"
    And Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
    And Enter a password in confirm password field "k", "Confirm Password"
    Then Verify the error displayed in input fields "password mismatch" "<countMandatoryFields1>"
    Then Verify user not able to edit email
    When Enter a password "abc@12311" in new password field
    And Enter a password in confirm password field "abc@12311", "Confirm Password"
    And Enter a password "abc@1231" in old password fields
    And Click the button "Change Password"
    Then Verify the popup message "Password updated successfully!"

    Examples:
      |option1|option2        |option3|error                 |countMandatoryFields|countMandatoryFields1|
      |Profile|Change Password|Logout |This Field is required|3                   |1                   |

  @regressionCandidate @working
  Scenario Outline: Verify current date is within test date range
    And Verify the default tab selected "<tab>"
    Then Get start and end date of test and verify current date is within range

    Examples:
      |tab         |
      |Active Tests|

  @regressionCandidate @working @toBeDiscussed
  Scenario Outline: Verify the test that is started
    Then Verify the default tab selected "<tab>"
    And Start test and verify instructions video is displayed
    Then Verify user is navigated to desired test

    Examples:
      |tab         |
      |Active Tests|

  @regressionCandidate @working
  Scenario Outline: Verify selected section gets opened
    When Start test and verify instructions video is displayed
    And Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    Then Click the button "NEXT"
    And Verify dialog box appears
    Then Click the Yes button
    And Click on the section ticket "<sectionName>"
    Then Verify there is no change in sections screen
    And Click the button "Attempt"
    Then Verify user navigates to questions screen of the selected section "<sectionName>"

    Examples:
      |sectionName|
      |Technical |

  @regressionCandidate @working
  Scenario: Navigation between the sections
    When Start test and verify instructions video is displayed
    And Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    When Expand the dropdown containing sections
    And Select "Technical" from dropdown
    Then Verify user navigates to questions screen of the selected section "Technical"

  @regressionCandidate @working
  Scenario Outline: Verify user is able to save answers
    When Start test and verify instructions video is displayed
    And Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Select or type an answer
    And Click the button "Save & Next"
    Then Verify user is able to save answers "<questionStatus>"

    Examples:
      |questionStatus|
      |submitted     |

  @regressionCandidate @working
  Scenario: Verify clear functionality
    Then Start test and verify instructions video is displayed
    Then Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    Then Click the button "NEXT"
    And Verify dialog box appears
    Then Click the Yes button
    Then Click the button "Attempt"
    Then Select or type an answer
    And Click the button "Clear"
    Then Verify the answer got cleared


#  @regressionCandidate @toBeUpdated-screenRevamped
#  Scenario: Verify the question count after attempting a particular section
#    Then Start test and verify instructions video is displayed
#    Then Click the "NEXT" button of instructions video
#    Then Check the instructions checkbox
#    Then Click the button "NEXT"
#    And Verify dialog box appears
#    Then Click the Yes button
#    Then Click the button "Attempt"
#    Then Select or type all the questions of a section and save
#    Then Validate questions count


#  @regressionCandidate @toBeUpdated-screenRevamped
#  Scenario: Verify entire test flow
#    When Start test and verify instructions video is displayed
#    And Click the "NEXT" button of instructions video
#    Then Check the instructions checkbox
#    And Click the button "NEXT"
#    Then Verify dialog box appears
#    Then Click the Yes button
#    Then Click the button "Attempt"
#    Then Select or type all the questions of entire test
#    And Click the button "Finish Test"
#    Then Validate questions count


  @regressionCandidate @working
  Scenario: Verify clear functionality after saving an answer
    When Start test and verify instructions video is displayed
    And Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Select or type an answer
    And Click the button "Save & Next"
    And Click the left arrow button
    And Click the button "Clear"
    Then Verify the answer got cleared

  @regressionCandidate @working
  Scenario: Refresh while attempting test
    When Start test and verify instructions video is displayed
    And Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Select or type an answer
    And Click the button "Save & Next"
    And Refresh the page, cancel alert and verify user is on same page
    And Refresh the page, accept alert and verify user navigates to instructions screen

#  @regressionCandidate @toBeModified
#  Scenario Outline: Validate the functionality of arrow key
#    When Start test and verify instructions video is displayed
#    And Click the "NEXT" button of instructions video
#    And Check the instructions checkbox
#    And Click the button "NEXT"
#    And Verify dialog box appears
#    And Click the Yes button
#    And Click the button "Attempt"
#    Then Validate the functionality of right arrow key "<totalQuestions>"
#    Then Verify key disables on last question
#
#    Examples:
#      |totalQuestions|
#      |5             |

  @regressionCandidate @working
  Scenario: Save a question without answering it
    When Start test and verify instructions video is displayed
    And Click the "NEXT" button of instructions video
    And Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Click the button "Save & Next"
    Then Verify the popup message "to save the answer"

  @regressionCandidate @working
  Scenario: Finish a test and check in completed tests tab
    When Start test and verify instructions video is displayed
    And Click the "NEXT" button of instructions video
    And Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Select or type an answer
    And Click the button "Save & Next"
    And Click the button "Finish Test"
    And Click the "Yes" button after finishing test
    Then Verify user navigates to test summary screen
    When Click the button "Proceed"
    And Select Back To Dashboard
    And Switch to "Completed Tests"
    Then Verify test is present in completed tests tab

  @regressionCandidate @working
  Scenario: View test report
    When Switch to "Completed Tests"
    And Click the button "View Report"
    Then Verify the report screen




































