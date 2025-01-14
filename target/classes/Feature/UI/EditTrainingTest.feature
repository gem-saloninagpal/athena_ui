Feature: Edit Training Test Feature

  Background:
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  Scenario Outline: Edit test of published course
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Select Has Test checkbox
    And Click the button Add Content
    And Validate "<content>" Add to Course "<contentMessage>"
    And Click on Add new test
    And Add test details
    And Click on Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Click on Add To Course button
    And Publish the course
    And Click the Yes button
    And Search course
    And Click on edit course
    And Click the button Add Content
    And Click on Add To Course button
    And Edit added test
    And Update added questions
    Then verify test is edited

    Examples:
      | module         | submodule      | courseType | duration | courseTag | category | fileLocation                                |description | contentMessage                        | content | points| Section | Percentage | Duration |
      | Manage Courses | Course Library | Public     | 30       | Java      | Logical  | C:\Users\Pallavi.Arora\Downloads\athena.png |abc         | Content successfully added. Add more! | Content | 10    | Logical | 5          | 0030     |


  Scenario Outline: Edit test while creating course
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Select Has Test checkbox
    And Click the button Add Content
    And Validate "<content>" Add to Course "<contentMessage>"
    And Click on Add new test
    And Add test details
    And Click on Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Edit added test
    And Update added questions
    Then verify test is edited

    Examples:
      | module         | submodule      | courseType | duration | courseTag | category | fileLocation                                |description | contentMessage                        | content | points| Section | Percentage | Duration |
      | Manage Courses | Course Library | Public     | 30       | Java      | Logical  | C:\Users\Pallavi.Arora\Downloads\athena.png |abc         | Content successfully added. Add more! | Content | 10    | Logical | 5          | 0030     |

  Scenario Outline: Edit created test from Test section
    Given Select "<module>", "<submodule>" from sidebar
    And Select Training Tests
    And Search training test "<testName>"
    And Edit test
    And Update added questions when editing from training test section
    Then verify test is edited

    Examples:
      | module | submodule    | testName            |
      | Tests  | Test Control | training no learner |

  Scenario Outline: Edit attempted test from Test section
    Given Select "<module>", "<submodule>" from sidebar
    And Select Training Tests
    And Search training test "<testName>"
    And Click on edit test
    Then Verify test does not get edited

    Examples:
      | module | submodule    | testName  |
      | Tests  | Test Control | attempted |

  Scenario Outline: Edit attempted test by editing course
    Given Select "<module>", "<submodule>" from sidebar
    When User searches for course "<courseName>"
    And Click on edit course
    And Click the button Add Content
    And Click on Add To Course button
    And click on test actions icon
    Then verify edit test option is not available

    Examples:
      | module         | submodule      | courseName       |
      | Manage Courses | Course Library | learnerAttempted |
