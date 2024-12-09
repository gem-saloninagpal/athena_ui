Feature:Role Management Module features

  Background:Check login to candidate module
    Given Navigate to login page
    Then Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  @1
  Scenario Outline: Create a role->Validate the role created
    Given Select "<module>", "<submodule>" from sidebar
#    When Click the button "Create Role"
#    When Click create role button
    Then Create Role and Validate Role is created "<roleName>","<roleDesc>","<roleDisplay>"
#    And Select "<module1>", "<submodule1>" from sidebar
#    Then Assign the "<role>" to the "<User>"

    Examples:
      | module        |submodule     |roleName|roleDesc|roleDisplay|module1        -|submodule1|role   |User                              |
      | Role Management|             | testCheckNew2|testCheckNew2  |testCheckNew2    |User Management|          |testCheckNew2|pallavi.arora@geminisolutions.com|

  @2
  Scenario Outline: Edit Role->Manage Test->Placement Drives->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for test
      |Placement Drives|Candidate Report|Evaluate Candidate|Test Summary|Copy Test|
    Then Validate permissions not granted should not be there on screen
      |Create Test||

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |testCheckNew2| Tests |Test Control| Manage Tests | Placement Drives | View Reports | Assign Test | Evaluate Test |Placement Drives|Create Test|

@3
  Scenario Outline: Edit Role->Manage Test->Internal Test->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role 
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Switch to "<TestType>"
    And Validate the Permission for test
      |Internal Tests|Candidate Report|Test Summary|Copy Test|
    Then Validate permissions not granted should not be there on screen
        |Create Test||

  Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |testCheckNew2| Tests |Test Control| Manage Tests | Internal Test |  View My InternalTest  | View Internal Reports | Evaluate Internal Test |Internal Tests|Create Test|


  @4
  Scenario Outline: Edit Role->Manage Test->Training Test->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Switch to "<TestType>"
    And Validate the Permission for test
      |Learner Report|Evaluate Learner|Test Summary|
    Then Validate permissions not granted should not be there on screen
      ||Edit Test|


    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |testCheckNew2| Tests |Test Control| Manage Tests | Training Test | View My Training Test | View Training Reports | Evaluate Training Test | Training Tests ||

  @5
  Scenario Outline: Edit Role->Manage Questions->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate the Permission for "<submodule1>"
      |View|Delete|

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|reqBtn|
      | Role Management|             |testCheckNew2| Tests |Questions| Manage Questions |  |   View Question   |Delete Question|       |Add New|

  @6
  Scenario Outline: Edit Role->Manage Courses-> Manage Content ->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for "<submodule1>"
      |Edit|Preview|
    Then Validate permissions not granted should not be there on screen
      ||Delete|

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|reqBtn|
      | Role Management|             |testCheckNew2|Manage Courses|Content Library| Manage Courses | Manage Content |  Create/Edit Content  || ||


  @7
  Scenario Outline: Edit Role->Manage Courses->Manage Assignment->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for "<submodule1>"
      |Edit|Preview|
    Then Validate permissions not granted should not be there on screen
      ||Delete|

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|reqBtn|
      | Role Management|             |testCheckNew2|Manage Courses|Assignment Library| Manage Courses | Manage Assignment |   Create/Edit Assignment   || ||


  @8
  Scenario Outline: Edit Role->Manage Courses->Manage Batch->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for "<submodule1>"
      |Edit|Add Courses|Batch Summary|Delete|
    Then Validate permissions not granted should not be there on screen
      ||Assign Learners|

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|reqBtn|
      | Role Management|             |testCheckNew2|Manage Courses|Batches| Manage Courses |  Manage Batch  |   View Batch   | Create/Edit Batch | Delete Batch  ||


  @9
  Scenario Outline: Edit Role->Other Permissions->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for "<module1>"
      |Edit|Detailed Campus Report|
    And Select "<module2>", "<submodule2>" from sidebar
    Then Validate the Permission for "<module2>"
      |Edit Profile|

    Examples:
      | module        |submodule     |role   |module1|submodule1|permissionType     |permissionSubtype|permission1  |permission2 |permission3|module2|submodule2|reqBtn|
      | Role Management|             |testCheckNew2|Campus |          | Other Permissions |                 |Manage Campus|Manage Users|           |User Management|          ||

