Feature:Course module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"

  Scenario Outline: Create Course Verify Owner
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    Then Verify the Owner of the Created Course

    Examples:
      | module        |submodule     |
      | Manage Courses|Course Library|

  Scenario Outline: Create Course Verify Course Info Tree
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    Then Verify course info tree for assignment and test checkbox

    Examples:
      | module        |submodule     |
      | Manage Courses|Course Library|

  Scenario Outline: Create Course verify Add Content
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button "Add Content"
    And Validate Filter functionality "<contentName>"
    Then Validate Add to Course
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|content -01|

  Scenario Outline: Create Course verify Add Assignment
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button "Add Content"
    And Validate Add to Course
    And Validate Filter functionality "<assignmentName>"
    Then Validate Add to Course
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|new assignment-01 |

  Scenario Outline: Create Course verify Course Summary
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button "Add Content"
    And Validate Add to Course
    And Validate Filter functionality "<assignmentName>"
    And Validate Add to Course
    Then Validate Course Summary Screen
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|new assignment-01 |


  Scenario Outline: Edit the course after creating and validate
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button "Add Content"
    And Validate Add to Course
    And Validate Filter functionality "<assignmentName>"
    And Validate Add to Course
    Then Edit the Created Course and Verify

    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|new assignment-01 |


  Scenario Outline: Assign Created Course and verify the result once the user complete the course
    Given Select "<module>", "<submodule>" from sidebar
    When A Learner is assign to a course
    And Change the user's role "<Role>"

    Examples:
    |Role   |  module      |submodule     |
    |Learner|Manage Courses|Course Library|