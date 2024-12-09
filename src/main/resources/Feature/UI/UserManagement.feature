Feature: User Management features

  Background:
    And Navigate to login page
    #  And Logout of portal
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"


  @regression
  Scenario Outline: Register Role- Learner, Admin, Invigilator, Super Admin
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "<button>"
    Then Verify user is navigated to page "<page>"
    When Click the button "<button>"
    Then Verify the error displayed in input fields "<error>" "<countMandatoryFields>"
    When Select a role from dropdown "<role>"
    And Generate unique email
    And Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
    And Enter respective values in input fields "AbcD@1234", "AbcD@1234", "abcKk", "def", "", "9876543210", "10"
    And Click the button "<button>"
#      And Switch to Gemini Users
#      Then Verify "<role>" is registered "abcKk","def"
    When Click the button "<button>"
    And Select a role from dropdown "<role>"
    And Enter respective values in input fields "AbcD@1234", "AbcD@1234", "abcKk", "def", "", "9876543210", "10"
    And Click the button "<button>"
#      Then Verify the popup message "<popupMsg>"

    Examples:
      |module         |button  |page           |error   |role         |tab      |popupMsg     |index|countMandatoryFields|submodule|
      |User Management|Register|User Management|required|Learner      |Gemini Users |already exist|3   |7                   |         |
      |User Management|Register|User Management|required|Admin        |Gemini Users|already exist|1    |7                   |         |
      |User Management|Register|User Management|required|Invigilator  |Gemini Users|already exist|1    |7                   |         |
      |User Management|Register|User Management|required|Super Admin  |Gemini Users|already exist|1    |7                   |         |

  @regression
  Scenario Outline: Register Role- Candidate
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "<button>"
    Then Verify user is navigated to page "<page>"
    When Click the button "<button>"
    Then Verify the error displayed in input fields "<error>" "7"
    And Select a role from dropdown "<role>"
    And Generate unique email
    And Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
    And Enter respective values in input fields "AbcD@1234", "AbcD@1234", "abcKk", "def", "<inbuilt email>", "9876543210", "10"
    And Select campus from select campus dropdown
    And Select experience level from dropdown "Fresher"
    And Click the button "<button>"
#      Then Verify the popup message "<popupMsg>"
    And Switch to tab "<tab>", "<index>"
    Then Verify "Candidate" is registered "abcKk","def"
    Examples:
      |module         |button  |page           |error   |role     |tab       |popupMsg               |index|submodule|inbuilt email|
      |User Management|Register|User Management|required|Candidate|Candidates|registered successfully|2    |         |             |

  @regression @working
  Scenario Outline: Register user with multiple roles
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "<button>"
    Then Verify user is navigated to page "<page>"
    When Click the button "<button>"
    Then Verify the error displayed in input fields "<error>" "7"
    When Select a role from dropdown "<role1>"
#        And Select a role from dropdown "<role2>"
#        And Select a role from dropdown "<role3>"
    And Generate unique email
    And Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
    And Enter respective values in input fields "AbcD@1234", "AbcD@1234", "abcKk", "def", "", "9876543210", "10"
    And Click the button "<button>"
    And Switch to Gemini Users
    Then Verify "<role1>" is registered "abcKk","def"
    Then Verify the role of registered user "<role1>","<role2>","<role3>"
    When Click the button "<button>"
    And Select a role from dropdown "<role1>"
    And Enter respective values in input fields "AbcD@1234", "AbcD@1234", "abcKk", "def", "", "9876543210", "10"
    And Click the button "<button>"

    Examples:
      |module         |button  |page           |error   |role1   |tab    |popupMsg     |index|role2     |role3  |submodule|
      |User Management|Register|User Management|required|Admin  |Employees|already exist|1  |Invigilator|Learner|         |

  @regression @working
  Scenario Outline: Validating roles of user from user's id
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "<button>"
    Then Verify user is navigated to page "<page>"
    When Click the button "<button>"
    Then Verify the error displayed in input fields "<error>" "<countMandatoryFields>"
    When Select a role from dropdown "<role1>"
#          And Select a role from dropdown "<role2>"
#          And Select a role from dropdown "<role3>"
    And Generate unique email
    And Enter password "abc" and verify the required format "Must contain min 1 Alphabet(Lowercase), 1 Number & 1 Special character, Min Length should be 6, Max Length should be 10"
    And Enter respective values in input fields "AbcD@1234", "AbcD@1234", "abcKk", "def", "", "9876543210", "10"
    And Click the button "<button>"
    Then Verify "<role1>" is registered "abcKk","def"
    Then Verify the role of registered user "<role1>","<role2>","<role3>"
    And Logout of portal
    When Login using "<inbuilt username>" and "AbcD@1234"
    And Select roles dropdown icon of navigation bar
#          Then Verify the roles through user's id "<role1>", "<role2>", "<role3>"

    Examples:
      |module         |button  |page           |error   |role1   |role2|role3       |countMandatoryFields|submodule|inbuilt username|
      |User Management|Register|User Management|required|Admin  |Invigilator|Learner|7                   |         |null            |

  @regression @working
  Scenario Outline: Verify user is unable to edit role of a candidate
    Given Select "<module>", "<submodule>" from sidebar
    Then Switch to tab "<tab>", "<index>"
    When Select Actions icon of first candidate displayed
    And Select Edit Profile option
    Then Verify user is navigated to page "<string>"
    And Enable editing
    Then Verify user not able to edit role

    Examples:
      | module        | tab     | index | string    |submodule|
      |User Management|Candidates|2      |Update User|         |

  @regression @working
  Scenario Outline: View test report
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to tab "<tab>", "<index>"
    And Select Actions icon of first candidate displayed
    And Select "View Test Reports" from actions dropdown
    Then Verify the tabs present in candidate test summary

    Examples:
      | module        |submodule| tab       | index |
      |User Management|         | Candidates|   2   |

  @regression @working
  Scenario Outline: View learner's report
    Given Select "<module>", "<submodule>" from sidebar
    And Search a learner "pallavi.arora@geminisolutions.com"
    And Select actions icon of first learner displayed
    And Select "View Learner Reports" from actions dropdown
    Then Verify the tabs present in learner's summary
    And Switch to "Attempted Tests"
    And Click View Report in attempted tests
    And Click the button "Back"

    Examples:
      | module        |submodule| tab         | tabNumber |
      |User Management|         | Gemini Users|   3       |
