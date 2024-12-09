Feature: Batches functionality

  Background:
    #  And Logout of portal
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  @regression @batch @headless_check
  Scenario Outline: Create a batch and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Batch"
    And Enter respective values in batch fields "<fileLocation>", "<owner>"
    And Enter description "abc"
    And Click the button "Add courses"
    And Add a course in batch
    And Click the button in batch "Create Batch"

    Examples:
      |module        |submodule|fileLocation                                                  |owner |batchName|
      |Manage Courses|Batches  |C:\\Users\\Pallavi.Arora\\Downloads\\athena.png|athena|abc     |

  @regression @batch
  Scenario Outline: Add a course in batch and verify from batch summary
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Add Courses" from actions dropdown
    And Add a course in batch
    And Click the button "Back"
    And Click actions icon of a batch
    And Select "Batch Summary" from actions dropdown
    Then Verify added course displays in batch summary

    Examples:
      |module        |submodule|
      |Manage Courses|Batches  |

  @regression @batch
  Scenario Outline:  Verify batch owner is selected by default
    When Expand info dropdown from navbar
    And Select "Profile" from dropdown
    Then Get email of user
    When Select "<module>", "<submodule>" from sidebar
    And Click the button "Create Batch"
    Then Verify owner of a batch is selected by default

    Examples:
      |module        |submodule|
      |Manage Courses|Batches  |


  @regression @batch
  Scenario Outline: Edit a batch and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Edit" from actions dropdown
    And Enter respective values in batch fields while editing "<fileLocation>", "<owner>"
    And Enter description "def"
    And Click the button "Add courses"
    And Add a course in batch
    And Click the button in batch "Update Batch"
    Then Verify batch is created/updated
    When Click actions icon of a batch
    And Select "Batch Summary" from actions dropdown
    Then Verify added course displays in batch summary
  #    Then Verify added course after editing displays in batch summary

    Examples:
      |module        |submodule|fileLocation                                                  |owner |
      |Manage Courses|Batches  |C:\Users\Pallavi.Arora\Downloads\athena.png|athena|






