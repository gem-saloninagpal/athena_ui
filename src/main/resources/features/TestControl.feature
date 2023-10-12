Feature: Test Control Feature


  Scenario Outline: Test Control->Validate Active Campus
    Given Set Test Control endpoint "<endpoint>" and method "<method>"
    Then Verify Status code "<expectedStatus>"
    When Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"
    And Select "<module>", "<submodule>" from sidebar
    Then get the Campus details and validate with api

    Examples:
      | endpoint                 | method | expectedStatus |module  |submodule  |
      | getAllActiveCampus       | Get    | 200            |Tests |Test Control |






