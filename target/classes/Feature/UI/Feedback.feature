Feature: Feedback Feature

  Background:
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  Scenario Outline: View user feedback
    Given Select "<module>", "<submodule>" from sidebar
    When Click on view icon
    Then verify feedback is visible

    Examples:
      | module    | submodule |
      | Feedbacks |           |

  Scenario Outline: Delete user feedback
    Given Select "<module>", "<submodule>" from sidebar
    When Click on delete icon
    And Click the Yes button
    Then verify feedback is deleted

    Examples:
      | module    | submodule |
      | Feedbacks |           |

  Scenario Outline: Update feedback status
    Given Select "<module>", "<submodule>" from sidebar
    When Click on view icon
    And Update the status
    Then verify status is updated

    Examples:
      | module    | submodule |
      | Feedbacks |           |