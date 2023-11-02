Feature:Role Management Module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"

  @1
  Scenario Outline: Create a role->Validate the role created
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Role"
    And Create Role and Validate Role is created "<roleName>","<roleDesc>","<roleDisplay>"
    And Select "<module1>", "<submodule1>" from sidebar
    Then Assign the "<role>" to the "<User>"

    Examples:
      | module        |submodule     |roleName|roleDesc|roleDisplay|module1        |submodule1|role   |User             |
      | Role Management|             | Trainee|Trainee |Trainee    |User Management|          |Trainee|rahul23@gmail.com|

@2
  Scenario Outline: Edit Role->Manage Test->Placement Drives->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for test
      |Placement Drives|Candidate Report|Evaluate Candidate|Test Summary|Candidates Assigned|
    Then Validate permissions not granted should not be there on screen
      |Create Test||




    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |Trainee| Tests |Test Control| Manage Tests | Placement Drives | View Reports | Assign Test | Evaluate Test |Placement Drives|Create Test|

@3
  Scenario Outline: Edit Role->Manage Test->Internal Test->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Switch to "<TestType>"
    And Validate the Permission for test
      |Internal Tests|Candidate Report|Evaluate Candidate|Test Summary|
    Then Validate permissions not granted should not be there on screen
        |Create Test|Create|Copy Test|Edit Test|Candidates Assigned|




  Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |Trainee| Tests |Test Control| Manage Tests | Internal Test |  View My InternalTest  | View Internal Reports | Evaluate Internal Test |Internal Tests|Create Test|


  @4
  Scenario Outline: Edit Role->Manage Test->Training Test->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Switch to "<TestType>"
    And Validate the Permission for test
      |Learner Report|Evaluate Learner|Test Summary|
    Then Validate permissions not granted should not be there on screen
      ||Edit Test|


    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|TestType|reqBtn|
      | Role Management|             |Trainee| Tests |Test Control| Manage Tests | Training Test | View My Training Test | View Training Reports | Evaluate Training Test | Training Tests ||



  @5 @error
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
      | Role Management|             |Trainee| Tests |Questions| Manage Questions |  |   View Question   |Delete Question|       |Add New|

  @6
  Scenario Outline: Edit Role->Manage Courses->Validate Permissions
    Given Select "<module>", "<submodule>" from sidebar
    When Edit the Role "<permissionType>","<permissionSubtype>","<permission1>","<permission2>","<permission3>"
    And Switch the user to "<role>"
    And Select "<module1>", "<submodule1>" from sidebar
    And Validate the Permission for "<submodule1>"
      |Course Summary|Learner Reports|Delete|
    Then Validate permissions not granted should not be there on screen
      |Create Course|Create|Edit|Assign Learners|

    Examples:
      | module        |submodule     |role|module1|submodule1|permissionType|permissionSubtype|permission1|permission2|permission3|reqBtn|
      | Role Management|             |Trainee|Manage Courses|Course Library| Manage Courses || View Course |Delete Course| Evaluate Course | Create Course|


  @7
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
      | Role Management|             |Trainee|Manage Courses|Content Library| Manage Courses | Manage Content |  Create/Edit Content  || ||


  @8
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
      | Role Management|             |Trainee|Manage Courses|Assignment Library| Manage Courses | Manage Assignment |   Create/Edit Assignment   || ||


  @9
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
      | Role Management|             |Trainee|Manage Courses|Batches| Manage Courses |  Manage Batch  |   View Batch   | Create/Edit Batch | Delete Batch  ||


  @10
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
      | Role Management|             |Trainee|Campus |          | Other Permissions |                 |Manage Campus|Manage Users|           |User Management|          ||



  @11
  Scenario Outline: Delete Role->Validate it is deleted
    Given Select "<module>", "<submodule>" from sidebar
    When "<User>" is unassigned with the "<role>" which is going to be delete
    And Select "<module1>", "<submodule1>" from sidebar
    Then Delete Role and validate it is deleted

    Examples:
    |module         |submodule|role    |User             |module1        |submodule1|
    |User Management|         | Trainee|rahul23@gmail.com|Role Management|          |
