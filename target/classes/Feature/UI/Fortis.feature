Feature: Candidate module features - Fortis

  Background:Check login to candidate module
    Given Sign in using "automate@gmail.com" and "abc@123"
    And Change resolution


  @fortis
  Scenario: Login flow for Candidate
    When Expand info dropdown from navbar
    Then Verify the options present in dropdown "Profile", "Change Password", "Logout"
    And Select change password from dropdown and verify the dialog box
    Then Verify the email of candidate "automate@gmail.com"

#  @fortis
#  Scenario Outline: Explore tests in Profile
#    When Expand info dropdown from navbar
#    Then Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
#    And Select "Profile" from dropdown
#    And Click the button "Explore Tests"
#    Then Verify user navigates to dashboard
#
#    Examples:
#      | option1 | option2         | option3 |
#      | Profile | Change Password | Logout  |

  @fortis
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
      | option1 | option2         | option3 | error                  | countMandatoryFields | countMandatoryFields1 |
      | Profile | Change Password | Logout  | This Field is required | 3                    | 1                     |

  @fortis
  Scenario Outline: Verify current date is within test date range
    Then Verify the default tab selected "<tab>"
    Then Get start and end date of test and verify current date is within range

    Examples:
      | tab          |
      | Active Tests |

#  @fortis
#  Scenario: View test report
#    When Switch to "Completed Tests"
#    And Click on View Report
#    Then Verify the report screen

  @fortis
  Scenario Outline: Verify the test that is started
    Then Verify the default tab selected "<tab>"
    And Start test and validate video
    And Validate instructions video and click next
    Then Verify user is navigated to desired test "<testName>"

    Examples:
      | tab          | activeTests | testName     |
      | Active Tests | 3           | NewCheckEdit |

  @fortis
  Scenario Outline: Verify user is able to save answers
    When Start test and validate video
    And Validate instructions video and click next
    Then Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Select or type an answer
    And Click the button "Save & Next"
    Then Verify the answer got saved

    Examples:
      | testName     | questionStatus |
      | NewCheckEdit | submitted      |

  @fortis
  Scenario Outline: Verify clear functionality
    When Start test and validate video
    And Validate instructions video and click next
    Then Check the instructions checkbox
    Then Click the button "NEXT"
    And Verify dialog box appears
    Then Click the Yes button
    Then Click the button "Attempt"
    Then Select or type an answer
    And Click the button "Clear"
    Then Verify the answer got cleared

    Examples:
      | testName     |
      | NewCheckEdit |

  @fortis
  Scenario Outline: Verify entire test flow
    When Start test and validate video
    And Validate instructions video and click next
    Then Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Attempt all the MCQ
    And Click the button "Finish Test"
    Then Validate questions count "", "", ""
    When Click the button "Yes"
    And Click the button "Proceed"
    Then Verify user navigates to test summary screen
    When Click the button "Proceed"
    And Click the button "Back To Dashboard"
    And Switch to "Completed Tests"

    Examples:
      | testName             | total | attempted | unattempted |
      | testMultipleSections | 5     | 5         | 0           |

  @fortis
  Scenario Outline: Refresh while attempting test
    When Start test and validate video
    And Validate instructions video and click next
    Then Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Select or type an answer
    And Click the button "Save & Next"
    And Refresh the page, cancel alert and verify user is on same page
    And Refresh the page, accept alert and verify user navigates to instructions screen

    Examples:
      | testName | sectionName | questionNo |
      | mcqTest  | Logical     | 2          |

  @fortis
  Scenario Outline: Save a question without answering it
    When Start test and validate video
    And Validate instructions video and click next
    And Check the instructions checkbox
    And Click the button "NEXT"
    Then Verify dialog box appears
    When Click the Yes button
    And Click the button "Attempt"
    And Click the button Save & Next
    Then Verify the popup message "to save the answer"

    Examples:
      | testName     |
      | NewCheckEdit |

#  @fortis
#  Scenario Outline: Finish a test and check in completed tests tab
#    When Start test "<testName>" and verify instructions video is displayed
#    And Check the instructions checkbox
#    And Click the button "NEXT"
#    Then Verify dialog box appears
#    When Click the Yes button
#    And Click the button "Attempt"
#    And Select or type an answer
#    And Click the button "Save & Next"
#    And Click the button "Finish Test"
#    And Click the "Yes" button after finishing test
#    Then Verify user navigates to test summary screen
#    When Click the button "Proceed"
#    When Select Back To Dashboard
#    And Switch to "Completed Tests"
#    Then Verify "<testName>" is present in completed tests
#
#    Examples:
#      | testName     |
#      | NewCheckEdit |

  @fortis
    Scenario: Skip functionality
      When Start test and validate video
      And Validate instructions video and click next
      And Check the instructions checkbox
      And Click the button "NEXT"
      Then Verify dialog box appears
      When Click the Yes button
      And Click the button "Attempt"
      And Click the button "Skip"
      Then Verify question is skipped







































