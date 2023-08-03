Feature: Candidate module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "test@gmail.com" and "abc@123"
   # And Wait while screen loads


    @regression011
  Scenario Outline: Login flow for Candidate
    Then Expand info dropdown from navbar
    And Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    Then Select change password from dropdown and verify the dialog box
    Then Verify the email of candidate "automation@gmail.com"

    Examples:
      |option1|option2        |option3|
      |Profile|Change Password|Logout |

      @regression011
  Scenario Outline: Verify the default tab selected and number of tests assigned
    Then Verify the default tab selected "<tab>"
    And Verify the number of Tests "<activeTests>", "<tab>"
    Then Switch to "Completed Tests"
    And Verify the number of Tests "<completedTests>", "<tab2>"

    Examples:
      |tab         |activeTests|completedTests|tab2|
      |Active Tests|1          |0             |Completed Tests|

  @regression011
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

  @regressionRunSeparately
  Scenario Outline: Validate password errors
    Then Expand info dropdown from navbar
    And Verify the options present in dropdown "<option1>", "<option2>", "<option3>"
    Then Select "Change Password" from dropdown
    And Click the button "Change Password"
    And Verify the error displayed in input fields "<error>" "<countMandatoryFields>"
    Then Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
    Then Enter a password in confirm password field "k", "Confirm Password"
    And Verify the error displayed in input fields "password mismatch" "<countMandatoryFields1>"
    Then Verify user not able to edit email
    Then Enter a password "abc@12311" in new password field
    Then Enter a password in confirm password field "abc@12311", "Confirm Password"
    Then Enter a password "abc@1231" in old password fields
    And Click the button "Change Password"
    And Verify the popup message "Password updated successfully!"

    Examples:
      |option1|option2        |option3|error                 |countMandatoryFields|countMandatoryFields1|
      |Profile|Change Password|Logout |This Field is required|3                   |1                   |

  @regression011
    Scenario Outline: Verify current date is within test date range
      Then Verify the default tab selected "<tab>"
      And Verify the number of Tests "<activeTests>", "<tab>"
      Then Get start and end date of test and verify current date is within range

      Examples:
        |tab         |activeTests|
        |Active Tests|1          |

  @regression011
    Scenario Outline: View test report
      Then Switch to "Completed Tests"
      And Verify the number of Tests "<completedTests>", "<tabName>"
      Then Click the button "View Report"
      And Verify the report screen

      Examples:
      |completedTests|tabName|
      |0             |Completed Tests|

  @regression011
    Scenario Outline: Verify the test that is started
      Then Verify the default tab selected "<tab>"
      And Verify the number of Tests "<activeTests>", "<tab>"
      Then Start test "<testName>" and verify instructions video is displayed
      Then Verify user is navigated to desired test "<testName>"

      Examples:
      |tab         |activeTests|testName|
      |Active Tests|1          |NewCheckEdit|

  @regression012
    Scenario Outline: Verify selected section gets opened
      Then Start test "<testName>" and verify instructions video is displayed
      Then Click the "NEXT" button of instructions video
      Then Verify user is navigated to desired test "<testName>"
      Then Check the instructions checkbox
      Then Click the button "NEXT"
      And Verify dialog box appears
      Then Click the Yes button
      And Click on the section ticket "<sectionName>"
      And Verify there is no change in sections screen
      Then Click the button "Attempt"
      And Verify user navigates to questions screen of the selected section "Technical"

      Examples:
      |testName |sectionName|
      |NewCheckEdit  | Technical |

  @regression012
    Scenario Outline: Navigation between the sections
      Then Start test "<testName>" and verify instructions video is displayed
      Then Click the "NEXT" button of instructions video
      Then Check the instructions checkbox
      Then Click the button "NEXT"
      And Verify dialog box appears
      Then Click the Yes button
      Then Click the button "Attempt"
      And Click on the section ticket "<sectionName>"
      And Verify user navigates to questions screen of the selected section "Logical"
      Then Expand the dropdown containing sections
      Then Select "Technical" from dropdown
      And Verify user navigates to questions screen of the selected section "Technical"

      Examples:
      |testName |sectionName|
      |  NewCheckEdit|  Logical|

  @regression012
    Scenario Outline: Verify user is able to save answers
      Then Start test "<testName>" and verify instructions video is displayed
      Then Click the "NEXT" button of instructions video
      Then Check the instructions checkbox
      Then Click the button "NEXT"
      And Verify dialog box appears
      Then Click the Yes button
      Then Click the button "Attempt"
      Then Select or type an answer
      And Click the button "Save & Next"
      Then Verify user is able to save answers "<questionStatus>"

      Examples:
      |testName    |questionStatus|
      | NewCheckEdit |  submitted   |

  @regression012
  Scenario Outline: Verify clear functionality
    Then Start test "<testName>" and verify instructions video is displayed
    Then Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    Then Click the button "NEXT"
    And Verify dialog box appears
    Then Click the Yes button
    Then Click the button "Attempt"
    Then Select or type an answer
    And Click the button "Clear"
    Then Verify the answer got cleared

    Examples:
    |testName   |
    | NewCheckEdit|

  @regression0122
    Scenario Outline: Verify the question count after attempting a particular section
      Then Start test "<testName>" and verify instructions video is displayed
      Then Click the "NEXT" button of instructions video
      Then Check the instructions checkbox
      Then Click the button "NEXT"
      And Verify dialog box appears
      Then Click the Yes button
      Then Click the button "Attempt"
      Then Select or type all the questions of a section and save
      Then Validate questions count "<total>", "<attempted>", "<unattempted>"

      Examples:
        |testName  |total|attempted|unattempted|
        |NewCheckEdit|  2   | 2        |  0         |

  @regression01
  Scenario Outline: Verify entire test flow
    Then Start test "<testName>" and verify instructions video is displayed
    Then Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    Then Click the button "NEXT"
    And Verify dialog box appears
    Then Click the Yes button
    Then Click the button "Attempt"
    Then Select or type all the questions of entire test
    And Click the button "Finish Test"
    Then Validate questions count "<total>", "<attempted>", "<unattempted>"

    Examples:
      |testName            |total |attempted|unattempted|
      |testMultipleSections|  5   | 5       |  0        |

  @regression012
  Scenario Outline: Verify clear functionality after saving an answer
    Then Start test "<testName>" and verify instructions video is displayed
    Then Click the "NEXT" button of instructions video
    Then Check the instructions checkbox
    Then Click the button "NEXT"
    And Verify dialog box appears
    Then Click the Yes button
    Then Click the button "Attempt"
    Then Select or type an answer
    And Click the button "Save & Next"
    Then Click the left arrow button
    And Click the button "Clear"
    Then Verify the answer got cleared

    Examples:
    |testName     |
    |NewCheckEdit |

  @regression01
    Scenario Outline: Refresh while attempting test
      Then Start test "<testName>" and verify instructions video is displayed
      Then Click the "NEXT" button of instructions video
      And Check the instructions checkbox
      Then Click the button "NEXT"
      And Verify dialog box appears
      Then Click the Yes button
      Then Click the button "Attempt"
      And Click on the section ticket "<sectionName>"
      Then Select or type an answer
      And Click the button "Save & Next"
      Then Refresh the page and "cancel" alert
      Then Verify user is on the same page "<sectionName>", "<questionNo>"
      Then Refresh the page and "accept" alert
      Then Verify user navigates to instructions screen

      Examples:
      |testName |sectionName|questionNo|
      | mcqTest | Logical |       2  |

  @regression01
    Scenario Outline: Validate the functionality of arrow key
      Then Start test "<testName>" and verify instructions video is displayed
      Then Click the "NEXT" button of instructions video
      And Check the instructions checkbox
      Then Click the button "NEXT"
      And Verify dialog box appears
      Then Click the Yes button
      And Click the button "Attempt"
      Then Validate the functionality of right arrow key "<totalQues>"
      And Verify key disables on last question

      Examples:
      |totalQues|testName|
      |5        |   NewCheckEdit     |

  @regression012
      Scenario Outline: Save a question without answering it
        Then Start test "<testName>" and verify instructions video is displayed
        Then Click the "NEXT" button of instructions video
        And Check the instructions checkbox
        Then Click the button "NEXT"
        And Verify dialog box appears
        Then Click the Yes button
        And Click the button "Attempt"
        Then Click the button "Save & Next"
        And Verify the popup message "to save the answer"

        Examples:
        |testName|
        | NewCheckEdit       |

  @regression01
  Scenario Outline: Finish a test and check in completed tests tab
    Then Start test "<testName>" and verify instructions video is displayed
    Then Click the "NEXT" button of instructions video
    And Check the instructions checkbox
    Then Click the button "NEXT"
    And Verify dialog box appears
    Then Click the Yes button
    And Click the button "Attempt"
    Then Select or type an answer
    Then Click the button "Save & Next"
    Then Click the button "Finish Test"
    And Click the "Yes" button after finishing test
    Then Verify user navigates to test summary screen
    And Select Back To Dashboard
    Then Switch to "Completed Tests"
    And Verify "<testName>" is present in completed tests

    Examples:
    |testName |
    |  NewCheckEdit|





































