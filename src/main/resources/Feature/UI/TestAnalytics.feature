Feature:  Test Analytics module

  Background:
  #  And Logout of portal
    And Navigate to login page
    And Login using "saloni02@gmail.com" and "abc@1234"
    
    Scenario Outline: Compare active tests statistics with ongoing and upcoming events
      Given Select "<module>", "<submodule>" from sidebar
      And Get active test statistics
      Then Compare ongoing and upcoming events with active statistics
      Examples:
      |module        |submodule|
      |Test Analytics|         |

    @onHold
    Scenario Outline: Verify campus performance with detailed campus report
      Given Select "<module>", "<submodule>" from sidebar
      When Get the pass count of first campus displayed
      And Select "<module2>", "<submodule2>" from sidebar
      And Search a campus 
      And Select actions icon of campus
      And Select "Detailed Campus Report" from actions dropdown
      Then Verify the passed candidates
      Examples:
      |module        |module2|submodule|submodule2|
      |Test Analytics|Campus |         |          |

