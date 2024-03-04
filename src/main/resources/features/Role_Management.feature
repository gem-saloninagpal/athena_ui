Feature:Role Management Module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "rahul.adhikari2@gmail.com" and "abc@123"

  @1
  Scenario Outline: Create a role->Validate the role created
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Role"
    And Create Role and Validate Role is created "<roleName>","<roleDesc>","<roleDisplay>"
    And Select "<module1>", "<submodule1>" from sidebar
    Then Assign the "<role>" to the "<User>"

    Examples:
      | module        |submodule     |roleName|roleDesc|roleDisplay|module1        |submodule1|role   |User             |
      | Role Management|             | Rahul|Rahul |Rahul  |User Management|          |Rahul|rahul.adhikari1@gmail.com|

@2
  Scenario Outline: Edit Role->Manage Test->Placement Drives->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for test
      |Placement Drives|Candidate Report|Test Summary|Candidates Assigned|
    Then Validate permissions not granted should not be there on screen
      |Create Test||

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |Rahul| Tests |Test Control| Manage Tests | Placement Drives | View Reports | Assign Test |View All Test |Placement Drives|Create Test|

@3
  Scenario Outline: Edit Role->Manage Test->Internal Test->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Switch to "<TestType>"
    And Validate the Permission for test
      |Internal Tests|Candidate Report|Test Summary|
    Then Validate permissions not granted should not be there on screen
        |Create Test|Create|Copy Test|Edit Test|Candidates Assigned|

  Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |Rahul| Tests |Test Control| Manage Tests | Internal Test |  View My InternalTest  | View Internal Reports | View All Internal Test |Internal Tests|Create Test|


  @4
  Scenario Outline: Edit Role->Manage Test->Training Test->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Switch to "<TestType>"
    And Validate the Permission for test
      |Learner Report|Test Summary|
    Then Validate permissions not granted should not be there on screen
      ||Edit Test|


    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |Rahul| Tests |Test Control| Manage Tests | Training Test | View My Training Test | View Training Reports | View All Training Test | Training Tests ||



  @5 @error @fail
  Scenario Outline: Edit Role->Manage Questions->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for "<submodule1>"
      |View|Delete|
    Then Validate permissions not granted should not be there on screen
      |Add New|Create|Edit|

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|reqBtn|
      | Role Management|             |Rahul| Tests |Questions| Manage Questions |  |   View Question   |Delete Question|       |Add New|

  @6 @fail
  Scenario Outline: Edit Role->Manage Courses->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And switch the tab to "<tabName>"
    And Validate the Permission for "<submodule1>"
      |Course Summary|Learner Reports|Delete|
    Then Validate permissions not granted should not be there on screen
      |Create Course|Create|Edit|Assign Learners|

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|reqBtn|tabName|
      | Role Management|             |Rahul|Manage Courses|Course Library| Manage Courses || View Course |Delete Course| Evaluate Course | Create Course|All Courses|


  @7 @fail
  Scenario Outline: Edit Role->Manage Courses-> Manage Content ->Validate Permissions
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
      | Role Management|             |Rahul|Manage Courses|Content Library| Manage Courses | Manage Content |  Create/Edit Content  || ||


  @8 @fail
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
      | Role Management|             |Rahul|Manage Courses|Assignment Library| Manage Courses | Manage Assignment |   Create/Edit Assignment   || ||


  @9 @fail
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
      | Role Management|             |Rahul|Manage Courses|Batches| Manage Courses |  Manage Batch  |   View Batch   | Create/Edit Batch | Delete Batch  ||


  @10 @fail
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
      | Role Management|             |Rahul|Campus |          | Other Permissions |                 |Manage Campus|Manage Users|           |User Management|          ||



  @11
  Scenario Outline: Delete Role->Validate it is deleted
    Given Select "<module>", "<submodule>" from sidebar
    When "<User>" is unassigned with the "<role>" which is going to be delete
    And Select "<module1>", "<submodule1>" from sidebar
    Then Delete Role and validate it is deleted

    Examples:
    |module         |submodule|role    |User             |module1        |submodule1|
    |User Management|         | Rahul|rahul.adhikari2@gmail.com|Role Management|          |
