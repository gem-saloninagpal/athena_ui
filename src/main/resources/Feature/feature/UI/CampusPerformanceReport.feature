Feature: Campus performance report

  Background:Check login to candidate module
  #  Given Navigate to page "login"
    And Logout of portal
    Then Login using "saloni02@gmail.com" and "abc@123"

  Scenario Outline: Verify campus statistics
    Given Select "<module>", "<submodule>" from sidebar
    When Hover over a campus and get performance
    Then Validate with the performance displayed in table
    Examples:
    |module|submodule          |
    |Reports|Campus Performance|

#  @onHold- already covered in Test Analytics file
#  Scenario Outline: Verify campus performance with detailed campus report
#    Given Select "<module>", "<submodule>" from sidebar
#    When Get the pass count of first campus displayed
#    And Select "<module2>", "<submodule2>" from sidebar
#    And Search a campus
#    And Select actions icon of campus
#    And Select "Detailed Campus Report" from actions dropdown
#    Then Verify the passed candidates
#    Examples:
#      |module        |module2|
#      |Test Analytics|Campus |