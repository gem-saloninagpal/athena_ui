Feature:Manage Sections Module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"


  Scenario Outline: Switched to Manage Sections->Create Section->Validate section is created
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Section"
    And Enter Section Name and Section Description in Create section fields
    Then Validate section is created

    Examples:
      | module        |submodule     |
      | Manage Sections|             |
