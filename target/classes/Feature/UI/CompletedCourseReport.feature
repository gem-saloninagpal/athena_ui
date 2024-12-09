Feature:Completed-Course Report features

  Background:Check login to candidate module
    And Navigate to login page
    Then Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  Scenario Outline: Verify search and reset functionality
    Given Select "<module>", "<submodule>" from sidebar
    And Enter name or email in searchbox "<string>"
    And Click the button "Filter"
    Then Validate records get filtered on the basis of name and email "<string>"
    And Click the button "Reset"
    Then Validate records get unfiltered "<string>"

    Examples:
      |module |submodule|string|
      |Reports|         | test |

  Scenario Outline: Filter by ec and location in completed course report
    Given Select "<module>", "<submodule>" from sidebar
    And Expand dropdown in user management "Location"
    And Select "<location>" from dropdown
    And Click the button "Filter"
    Then Validate completed courses records get filtered on the basis of location "<location>"
    And Expand dropdown in user management "Category"
    And Select EC from dropdown
    And Expand selected category dropdown "<category>"
    And Select "<selected category>" from dropdown
    And Click the button "Filter"
    Then Validate completed courses records get filtered on the basis of selected category "<selected category>"
    And Expand selected category dropdown "<selected category>"
    And Remove category selected from dropdown
    And Click the button "Filter"
    Then Validate completed courses records get filtered on the basis of location "<location>"

    Examples:
      |module |submodule|location    |category|selected category|
      |Reports|         |Canaan Tower|   EC   |     Java        |

  Scenario Outline: Export records
    Given Select "<module>", "<submodule>" from sidebar
    Then Click the button Export

    Examples:
      |module |submodule|file              |
      |Reports|         |Completed_Courses |


