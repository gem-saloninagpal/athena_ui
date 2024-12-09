Feature:Manage Sections Module features

  Background:Check login to candidate module
    Given Navigate to login page
    Then Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  @1
  Scenario Outline: Switched to Manage Sections->Create Section->Validate section is created
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Section"
    And Enter Section Name and Section Description in Create section fields
    Then Validate section is created

    Examples:
      | module        |submodule     |
      | Manage Sections|             |

  @2
  Scenario Outline: Switched to Manage Sections->Create Section->Validate View functionality
    Given Select "<module>", "<submodule>" from sidebar
    And select the any section from manage section screen and validate

    Examples:
      | module        |submodule     |
      | Manage Sections|             |