Feature:Course module features

  Background:Check login to candidate module
    Given Navigate to page "login"
  #  And Wait while screen loads
    Then Login using "saloni02@gmail.com" and "abc@123"

    Scenario Outline:Add content without adding course info
      Given Select "<module>", "<submodule>" from sidebar
      Then Click the button "<button>"
      Then Click the button "Add Content"
      And Verify the error displayed in input fields "<error>" "<mandatoryFieldCount>"

      Examples:
      |module        |button       |error                 |mandatoryFieldCount|submodule     |
      |Manage Courses|Create Course|This Field is required|6                  |Course Library|

    Scenario Outline:Create content- positive flow
      Given Select "<module>", "<submodule>" from sidebar
      Then Click the button "Add New"
      Then Enter respective values in content fields "<contentName>", "<contentTag>", "<fileType>", "<duration>", "<fileLocation>"
      And Enter description "<description>"
      And Click the button "Save & Exit"

      Examples:
      |contentName|contentTag|fileType|duration|fileLocation                                                   |module        |submodule      |description|
      |testo       |Java      |Image   |01:00   |C:\Users\saloni.nagpal\Pictures\Screenshots\Screenshot (10).png|Manage Courses|Content Library|testAbc    |


