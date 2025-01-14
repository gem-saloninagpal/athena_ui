Feature: Contact us form

  Background:
    And Navigate to website
#    And Login using "pallavi.arora@geminisolutions.com" and "Pallavi1@"
  @contact
  Scenario: Fill contact us form
    Given user clicks on Contact Us tab
    When user enters required details
    And user clicks on submit button
    Then verify form is submitted

  Scenario: Not entering details
    Given user clicks on Contact Us tab
    Then verify Submit button is disabled

  Scenario: Entering invalid name
    Given user clicks on Contact Us tab
    When user enters invalid name
    Then verify Submit button is disabled

  Scenario: Enter invalid email
    Given user clicks on Contact Us tab
    When user enters invalid email
    Then verify Submit button is disabled

  Scenario: Enter invalid contact number
    Given user clicks on Contact Us tab
    When user enters invalid contact number
    Then verify Submit button is disabled