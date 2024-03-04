Feature: Athena

  Scenario: Athena - Login API
    Given Set endpoint "loginAPI" and method "post" for Athena login
    And Verify status code 200 for Athena