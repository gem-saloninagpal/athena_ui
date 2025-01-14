Feature: Reset Password Feature

  Background:
    And Navigate to login page

  Scenario: Reset password for existing user
    Given user clicks on forgot password
    When user enters email id
    And user clicks on RESET button
    Then verify reset password mail is sent

  Scenario Outline: Reset password for invalid user
    Given user clicks on forgot password
    When user enters invalid email id "<email>"
    Then verify enter valid email error appears

    Examples:
      | email       |
      | ajkfcdbv123 |
      | afjdnvjnvjn |

  Scenario Outline: Reset password for user which does not exist
    Given user clicks on forgot password
    When user enters invalid email id "<email>"
    And user clicks on RESET button
    Then verify user not found error appears

    Examples:
      | email          |
      | zxcv@gmail.com |
      | mnbv@abc.com   |

  Scenario: Click reset password without entering anything
    Given user clicks on forgot password
    When user clicks on RESET button
    Then verify required error appears