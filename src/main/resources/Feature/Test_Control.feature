Feature: Test Control Feature


  Scenario: Login
    Given User authenticate as "super_admin"
    Then Verify status code 200

  Scenario Outline: Test Control->Validate Active Campus
    Given User authenticate as "super_admin"
    When Set endpoints "<endpoint>" method "<method>" to get Active Campus
    Then Verify status code 200
#    When Navigate to page "login"
#    Then Login using "rahul23@gmail.com" and "abc@123"
#    And Select "<module>", "<submodule>" from sidebar
#    Then get the Campus details and validate with api

    Examples:
      | endpoint                              | method | expectedStatus |module  |submodule  |
      | /gemassessment/api/getAllCampus       | Get    | 200            |Tests |Test Control |