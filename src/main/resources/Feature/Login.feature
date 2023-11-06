Feature: Login

  Scenario: Login
    Given User authenticate as "super_admin"
    Then Verify status code 200

  Scenario Outline: Get Dashboard Analytics
    Given User authenticate as "super_admin"
    When Set endpoints "<endpoint>" method "<method>" to get dashboard analytics
    Then Verify status code 200
    And Get the course details
    And Verify response message "Success"

    Examples:
      | endpoint                                 | method |
      | /gemassessment/api/getDashboardAnalytics | get    |
