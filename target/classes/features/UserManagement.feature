Feature: User Management features

    Background:
     Given Login using "athena@geminisolutions.com" and "gem_password"


      @regression1
    Scenario Outline: Register Role- Learner, Admin, Invigilator, Super Admin
      Given Select "<module>" from sidebar
      Then Click the button "<button>"
      And Verify user is navigated to page "<page>"
      Then Click the button "<button>"
      And Verify the error displayed in input fields "<error>" "<countMandatoryFields>"
      Then Select a role from dropdown "<role>"
      Then Generate unique email
      Then Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
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
      |module         |button  |page           |error   |role         |tab      |popupMsg     |index|countMandatoryFields|
      |User Management|Register|User Management|required|Learner      |Learner  |already exist|3    |7                   |
      |User Management|Register|User Management|required|Admin        |Employees|already exist|1    |7                   |
      |User Management|Register|User Management|required|Invigilator  |Employees|already exist|1    |7                   |
      |User Management|Register|User Management|required|Super Admin  |Employees|already exist|1    |7                   |

  @regression1
    Scenario Outline: Register Role- Candidate
      Given Select "<module>" from sidebar
      Then Click the button "<button>"
      And Verify user is navigated to page "<page>"
      Then Click the button "<button>"
      And Verify the error displayed in input fields "<error>" "7"
      Then Select a role from dropdown "<role>"
      Then Generate unique email
      Then Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
      Then Enter respective values in input fields "Abc@123", "Abc@123", "abcKk", "def", "<inbuilt email>", "9876543210", "10"
      Then Select campus from select campus dropdown
      Then Select experience level from dropdown "Fresher"
      Then Click the button "<button>"
      Then Switch to tab "<tab>", "<index>"
      Then Verify "Candidate" is registered "abcKk","def"
      Then Verify the role of registered user "<role1>","<role2>","<role3>"
      Then Click the button "<button>"
      Then Select a role from dropdown "<role>"
      Then Enter respective values in input fields "Abc@123", "Abc@123", "abcKk", "def", "<inbuilt email>", "9876543210", "10"
      Then Select campus from select campus dropdown
      Then Select experience level from dropdown "Fresher"
      Then Click the button "<button>"
      Then Verify the popup message "<popupMsg>"

      Examples:
      |module         |button  |page           |error   |role   |tab    |popupMsg     |index|
      |User Management|Register|User Management|required|Candidate|Candidates|already exist|2    |

  @regression1
      Scenario Outline: Register user with multiple roles
        Given Select "<module>" from sidebar
        Then Click the button "<button>"
        And Verify user is navigated to page "<page>"
        Then Click the button "<button>"
        And Verify the error displayed in input fields "<error>" "7"
        Then Select a role from dropdown "<role1>"
        Then Select a role from dropdown "<role2>"
        Then Select a role from dropdown "<role3>"
        Then Generate unique email
        Then Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
        Then Enter respective values in input fields "Abc@123", "Abc@123", "abcKk", "def", "", "9876543210", "10"
        Then Click the button "<button>"
        Then Switch to tab "<tab>", "<index>"
        Then Verify "<role1>" is registered "abcKk","def"
        Then Verify the role of registered user "<role1>","<role2>","<role3>"
        Then Click the button "<button>"
        Then Select a role from dropdown "<role1>"
        Then Enter respective values in input fields "Abc@123", "Abc@123", "abcKk", "def", "", "9876543210", "10"
        Then Click the button "<button>"
        Then Verify the popup message "<popupMsg>"

        Examples:
          |module         |button  |page           |error   |role1   |tab    |popupMsg     |index|role2|role3|
          |User Management|Register|User Management|required|Admin  |Employees|already exist|1  |Invigilator|Learner|

  @regression1
        Scenario Outline: Validating roles of user from user's id
          Given Select "<module>" from sidebar
          Then Click the button "<button>"
          And Verify user is navigated to page "<page>"
          Then Click the button "<button>"
          And Verify the error displayed in input fields "<error>" "7"
          Then Select a role from dropdown "<role1>"
          Then Select a role from dropdown "<role2>"
          Then Select a role from dropdown "<role3>"
          Then Generate unique email
          Then Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
          Then Enter respective values in input fields "Abc@123", "Abc@123", "abcKk", "def", "", "9876543210", "10"
          Then Click the button "<button>"
          Then Switch to tab "<tab>", "<index>"
          Then Verify "<role1>" is registered "abcKk","def"
          Then Verify the role of registered user "<role1>","<role2>","<role3>"
          Then Logout of portal
          And Login using "<inbuilt username>" and "Abc@123"
          Then Select roles dropdown icon of navigation bar
          Then Verify the roles through user's id "<role1>", "<role2>", "<role3>"

          Examples:
            |module         |button  |page           |error   |role1   |tab    |popupMsg     |index|role2|role3|
            |User Management|Register|User Management|required|Admin  |Employees|already exist|1  |Invigilator|Learner|

  @regression
          Scenario Outline: Verify user is unable to edit role of a candidate
            Given Select "<module>" from sidebar
            Then Switch to tab "<tab>", "<index>"
            Then Select Actions icon of first candidate displayed
            And Select Edit Profile option
            Then Verify user is navigated to page "<string>"
            Then Enable editing
            And Verify user not able to edit role

            Examples:
            | module        | tab     | index | string |
            |User Management|Candidate|2      |Candidate|

  @regression1
          Scenario Outline: Verify active/inactive user
            Given Select "<module>" from sidebar
            Then Switch to tab "<tab>", "<index>"
            Then Select Actions icon of first record displayed in Employees
            And Select Edit Profile option
            Then Verify user is navigated to page "<string1>"
            Then Enable editing
            And Verify the status of user and click status button
            Then Click the button "Update"
            Then Click the button "Back"
            Then Click the Yes button
            Then Select Actions icon of first record displayed in Employees
            And Select Edit Profile option
            Then Verify user is navigated to page "<string1>"
            Then Verify the updated status

            Examples:
              | module        | tab     | index | string    |string1|
              |User Management|Employees|1      |Employees|User Management / Update User|

  @regression1
          Scenario Outline: Change role and validate
            Given Select "<module>" from sidebar
            Then Switch to tab "<tab>", "<index>"
            Then Select Actions icon of first record displayed in Employees
            And Select Edit Profile option
            Then Verify user is navigated to page "<string1>"
            Then Enable editing
            Then Verify the current role "<role1>", "<role2>", ""
            Then Select a role from dropdown "<role1>"
            Then Select a role from dropdown "<role2>"
            Then Select a role from dropdown "<new role>"
            Then Click the button "Update"
            Then Verify the role of registered user "<new role>","",""

            Examples:
              | module        | tab     | index | string  |role1  |role2      |new role|string1|
              |User Management|Employees|1      |Employees|Learner|Super Admin|Admin   |User Management / Update User|

























