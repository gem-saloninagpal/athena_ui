Feature:Completed-Course Report features

  Background:Check login to candidate module
    And Navigate to login page
    Then Login using "saloni.nagpal@geminisolutions.com" and "abc@123"

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
    And Select "<category>" from dropdown
    And Expand selected category dropdown in user management "<category>"
    And Select "<selected category>" from dropdown
    And Click the button "Filter"
    Then Validate completed courses records get filtered on the basis of selected category "<selected category>"
    And Expand selected category dropdown in user management "<selected category>"
    And Remove category selected from dropdown
    And Click the button "Filter"
    Then Validate completed courses records get filtered on the basis of location "<location>"

    Examples:
      |module |submodule|location    |category|selected category|
      |Reports|         |Canaan Tower|   EC   |     Java        |

  Scenario Outline: Export records
    Given Select "<module>", "<submodule>" from sidebar
    And Click the button "Export"
    Then Verify the file gets downloaded "<file>"

    Examples:
      |module |submodule|file              |
      |Reports|         |Completed_Courses |

#    @toBeUpdated
#  Scenario Outline: Filter by date
#    Given Select "<module>", "<submodule>" from sidebar
#    And Select start date and end date
#    And Click the button "Filter"
#    Then Verify records get filtered on the basis of date
#
#    Examples:
#    |module |submodule|
#    |Reports|         |



