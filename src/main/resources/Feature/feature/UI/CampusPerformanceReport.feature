Feature: Campus performance report

  Background:Check login to candidate module
    And Logout of portal
    Then Login using "saloni02@gmail.com" and "abc@123"

  Scenario Outline: Verify campus statistics
    Given Select "<module>", "<submodule>" from sidebar
    When Hover over a campus and get performance
    Then Validate with the performance displayed in table
    Examples:
    |module|submodule          |
    |Reports|Campus Performance|
