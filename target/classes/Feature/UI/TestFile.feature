Feature: Contact us form

  Background:
    And Navigate to website
#    And Login using "pallavi.arora@geminisolutions.com" and "Pallavi1@"

  Scenario: Fill contact us form
    Given user clicks on Contact Us tab
    When user enters required details
    And user clicks on submit button
