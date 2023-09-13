Feature:Tests Module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"

  Scenario Outline: Create Test Placement Drive
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<TestTag>", "<Duration>", "<Campus>", "<Level>", "<StartDate>", "<EndDate>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created


    Examples:
      | module        |submodule     |TestTag|Duration|Campus    |Level   |StartDate|EndDate|Description|Section|Percentage|
      | Tests         |Test Control  |Java   |0030    |DELL, DELL|Beginner|13       |14     |abc        |Logical|5         |
