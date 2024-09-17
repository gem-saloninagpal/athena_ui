Feature: Contact us form

  Background:
    Given Navigate to website

  Scenario: Fill contact us form
    Given user clicks on Contact Us tab
    When user enters required details
    When user clicks on submit button
#    Then validate form is submitted

  Scenario: Not entering any details
    Given user clicks on Contact Us tab
    Then verify Submit button is disabled