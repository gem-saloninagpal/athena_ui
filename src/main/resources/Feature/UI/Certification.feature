Feature: Certificate functionality

  Background:
    #  And Logout of portal
    Then Login using "saloni.nagpal@geminisolutions.com" and "abc@1234"

  Scenario Outline: Add comment for certificate and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Enter additional comment for certificate
    And Click the button "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And publish the course
    And Assign a Learner to the course
    And Manually completed course
    And Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Switch to "Completed"
    And Click actions icon of a completed course
    And Select the option "View/Download Certificate"
    Then Verify the additional comment

    Examples:
      |submodule     |module        |option1|courseType|duration|courseTag|fileLocation                                                     |category|description|contentMessage|assignmentMessage|points|
      |Course Library|Manage Courses|Learner|  Public  |  30    |  Java   | C:\Users\saloni.nagpal\Pictures\Screenshots\Screenshot (10).png |Logical |abc        |              |                 |10    |

  Scenario Outline: Do not add comment for certificate and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And publish the course
    And Assign a Learner to the course
    And Manually completed course
    And Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Switch to "Completed"
    And Click actions icon of a completed course
    And Select the option "View/Download Certificate"
    Then Verify comment is not present

    Examples:
      |submodule     |module        |option1|courseType|duration|courseTag|fileLocation                                                     |category|description|contentMessage|assignmentMessage|points|
      |Course Library|Manage Courses|Learner|  Public  |  30    |  Java   | C:\Users\saloni.nagpal\Pictures\Screenshots\Screenshot (10).png |Logical |abc        |              |                 |10    |

    @extensionOf2ndTestcase
  Scenario Outline: Add comment after learner has completed the course
    Given Select "<module>", "<submodule>" from sidebar
    And Click actions icon of a course
    And Select "Edit Course" from actions dropdown
    And Click the button "No"
    And Enter additional comment for certificate
    And Click the button "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And Update and publish the course
    And Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Switch to "Completed"
    And Click actions icon of a completed course
    And Select the option "View/Download Certificate"
    Then Verify the additional comment

    Examples:
      |submodule     |module        |option1|
      |Course Library|Manage Courses|Learner|

    Scenario Outline: Check character limit of comment in certificate
      Given Select "<module>", "<submodule>" from sidebar
      When Click the button "Create Course"
      And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
      And Enter course description "<description>"
      And Enter additional comment with exceeded limit
      Then Verify error message "<message>"

      Examples:
        |submodule     |module        |option1|courseType|duration|courseTag|fileLocation                                                     |category|description|message|
        |Course Library|Manage Courses|Learner|  Public  |  30    |  Java   | C:\Users\saloni.nagpal\Pictures\Screenshots\Screenshot (10).png |Logical |abc        |Length must not be greater than 53 Characters|

