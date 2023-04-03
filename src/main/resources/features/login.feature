Feature: L&D Athena

    Background:
   # Scenario Outline: Login to Athena
     Given Login using "athena@geminisolutions.com" and "gem_password"

#      Examples:
#      |username                  |password    |
#      |athena@geminisolutions.com|gem_password|

    Scenario Outline: Register Role
      Given Select "<module>" from sidebar
      Then Click the button "<button>"
      And Verify user is navigated to page "<page>"
      Then Click the button "<button>"
      And Verify the error displayed in input fields "<error>"
      Then Select a role from dropdown "<role>"
      Then Generate unique email
      Then Enter respective values in input fields "Abc@123", "Abc@123", "abcKk", "def", "", "9876543210", "10"
      Then Click the button "<button>"
      Then Switch to tab "<tab>", "<index>"
      Then Verify "<role>" is registered "abcKk","def"
      Then Click the button "<button>"
      Then Select a role from dropdown "<role>"
      Then Enter respective values in input fields "Abc@123", "Abc@123", "abcKk", "def", "", "9876543210", "10"
      Then Click the button "<button>"
      Then Verify the popup message "<popupMsg>"


      Examples:
      |module         |button  |page           |error   |role   |tab    |popupMsg     |index|
      |User Management|Register|User Management|required|Learner|Learner|already exist|3    |
      |User Management|Register|User Management|required|Admin  |Employees|already exist|1  |



