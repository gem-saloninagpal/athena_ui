Feature: Demo Videos Feature

  Background:
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  Scenario Outline: Verify video is being displayed
    Given Select "<module>", "<submodule>" from sidebar
    When User clicks on Demo Video
    Then verify video is played

    Examples:
      | module         | submodule          |
      | Tests          | Test Control       |
      | Manage Courses | Course Library     |
      | Manage Courses | Content Library    |
      | Manage Courses | Assignment Library |