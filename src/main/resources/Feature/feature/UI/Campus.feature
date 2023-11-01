Feature: Create campus functionality

  Background:
    And Logout of portal
    And Login using "saloni02@gmail.com" and "abc@123"


  @regression
    Scenario Outline: Register campus
      Given Select "<module>", "<submodule>" from sidebar
      When Click the button "Register Campus"
      And Enter respective values in campus fields "<location>", "<university>", "<tpoName>", "<tpoEmail>", "<tpoContact>", "<description>"
      And Click the button "Register"
      And Search a campus
      Then Verify campus is registered

      Examples:
      |module|submodule|location|university|tpoName|tpoEmail     |tpoContact|description|
      |Campus|         |  loc   |uni       |nameee |abc@gmail.com|9876545670|abc        |

  @regression
    Scenario Outline: Verify mandatory fields
      Given Select "<module>", "<submodule>" from sidebar
      When Click the button "Register Campus"
      And Click the button "Register"
      Then Verify the error displayed in input fields "<error>" "<fieldCount>"

      Examples:
      |module|submodule|error                 |fieldCount|
      |Campus|         |This field is required|7         |

  @regression
    Scenario Outline: Edit campus
      Given Select "<module>", "<submodule>" from sidebar
      And Search a campus
      And Select actions icon of campus
      And Select "Edit" from actions dropdown
      And Enter respective values in campus fields "<location>", "<university>", "<tpoName>", "<tpoEmail>", "<tpoContact>", "<description>"
      And Click the button "Update"
      And Search a campus
      Then Verify campus is updated "<tpoName>", "<tpoContact>", "<location>", "<university>"

      Examples:
      |module|submodule|location|university|tpoName|tpoEmail   |tpoContact|description|
      |Campus|         |chd     |chitkara  |xyz    |x@gmail.com|8907543256|desc       |












