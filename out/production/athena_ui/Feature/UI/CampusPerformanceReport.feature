Feature: Campus performance report

  Background:Check login to candidate module
    And Navigate to login page
    Then Login using "saloni.nagpal@geminisolutions.com" and "abc@123"

  Scenario Outline: Verify campus statistics
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Campus-Performance"
    And Hover over a campus and get performance
    Then Validate with the performance displayed in table
    Examples:
    |module|submodule          |
    |Reports|                  |

