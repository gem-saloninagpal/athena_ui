Feature:Manage Sections Module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"

@1
  Scenario Outline: Switched to Manage Sections->Create Section->Validate section is created
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Section"
    And Enter Section Name and Section Description in Create section fields "<popUpMessage>"
    Then Validate section is created

    Examples:
      | module        |submodule     |popUpMessage|
      | Manage Sections|             |Section Type Added Successfully|


  @2
  Scenario Outline: Switched to Manage Sections->Create Section->Validate section is created-> edit and validate
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Section"
    And Enter Section Name and Section Description in Create section fields "<popUpMessage>"
    And Validate section is created
    Then Validate section is edited "<editPopUpMessage>"

    Examples:
      | module        |submodule     |popUpMessage|editPopUpMessage|
      | Manage Sections|             |Section Type Added Successfully|Section Type Updated Successfully|


    @3
  Scenario Outline: Switched to Manage Sections->Create Section->Validate section is created-> delete and validate
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Section"
    And Enter Section Name and Section Description in Create section fields "<popUpMessage>"
    And Validate section is created
    Then Validate section is deleted "<editPopUpMessage>"

    Examples:
      | module        |submodule     |popUpMessage|editPopUpMessage|
      | Manage Sections|             |Section Type Added Successfully|Section Type Deleted Successfully|


      @4
  Scenario Outline: Switched to Manage Sections->Create Section->Validate View functionality
    Given Select "<module>", "<submodule>" from sidebar
    And select the any section from manage section screen and validate


    Examples:
      | module        |submodule     |
      | Manage Sections|             |