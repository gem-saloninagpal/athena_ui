Feature: Assign learner to batch

  Background:
    #  And Logout of portal
    And Navigate to login page
    And Login using "saloni02@gmail.com" and "abc@1234"

  Scenario Outline: Validate count and popup message on assigning learner
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Assign Learners" from actions dropdown
    And Get assigned learners count
    And Assign a learner
    Then Verify the popup message "<message>"
    Then Validate count after assigning

    Examples:
    |module        |submodule|learner|message                                |
    |Manage Courses|Batches  |saloni |Learner added successfully to the batch|

  Scenario Outline: Validate count and popup message on unassigning learner
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Assign Learners" from actions dropdown
  #  And Search a learner "<learner>"
    And Get assigned learners count
    And Unassign a learner
    Then Verify the popup message "<message>"
    Then Validate count after unassigning

    Examples:
      |module        |submodule|learner|message                                    |
      |Manage Courses|Batches  |saloni |Learner removed successfully from the batch|

  Scenario Outline: Validate assign selected
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Assign Learners" from actions dropdown
    And Get assigned learners count
    And Select an unassigned learner
    And Go to next page
    And Select an unassigned learner
    And Click the button "Assign Selected"
    Then Verify the popup message "<message>"
    Then Validate the count after assigning learners from different pages

    Examples:
    |module        |submodule|message|
    |Manage Courses|Batches  |All learners added successfully|

  Scenario Outline: Validate unassign selected
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Assign Learners" from actions dropdown
    And Get assigned learners count
    And Select an assigned learner
    And Go to next page
    And Select an assigned learner
    And Click the button "Unassign Selected"
    Then Verify the popup message "<message>"
    Then Validate the count after unassigning learners from different pages

    Examples:
      |module        |submodule|message|
      |Manage Courses|Batches  |removed successfully|

  Scenario Outline: Filter by status
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Assign Learners" from actions dropdown
    And Expand the dropdown "Status"
    And Select "Assigned" from dropdown
    Then Validate records get filtered on the basis of status "<status>"

    Examples:
    |module        |submodule|status  |
    |Manage Courses|Batches  |Assigned|

  Scenario Outline: Filter by category
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Assign Learners" from actions dropdown
    And Expand the dropdown "Category"
    And Select "EC" from dropdown
    And Expand the dropdown "EC"
    And Select "QA" from dropdown
    Then Validate records get filtered on the basis of EC "<EC>"

    Examples:
      |module        |submodule|EC  |
      |Manage Courses|Batches  |QA  |
    
  Scenario Outline: Filter and unassign a candidate
    Given Select "<module>", "<submodule>" from sidebar
    When Click actions icon of a batch
    And Select "Assign Learners" from actions dropdown
    And Expand the dropdown "Category"
    And Select "EC" from dropdown
    And Expand selected category dropdown
    And Select "QA" from dropdown
    And Get assigned learners count
    And Unassign a learner
    Then Verify the popup message "Learner removed successfully"
    Then Validate count after unassigning

    Examples:
      |module        |submodule|
      |Manage Courses|Batches  |
    
    


