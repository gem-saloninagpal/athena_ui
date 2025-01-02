Feature:UserDashboard Module features

  Background:Check login to candidate module
  #  Given Navigate to login page
    And Login using "saloni.nagpal@geminisolutions.com" and "abc@1234"

    @1 @fixed
  Scenario Outline: Create Course->Assigned Learner->Validate Assigned Learner in UserDashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
   And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Select the checkbox
    And Click the button until it appear "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And Add an assignment
    And Click the button "Add To Course"
    And publish the course
    And Assign a Learner to the course
    And Select "<module1>", "<submodule1>" from sidebar
    And Search course name
    Then Validate Assigned on UserDash Board
    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation                                  |description|assignmentName    |contentMessage                       |assignmentMessage                       |module1       |submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30    | Logical| C:\Users\saloni.nagpal\Pictures\Athena-1.PNG |abc        |new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard|           |Content|Assignment|


      @2 @fixed
  Scenario Outline: Create Course->Assigned Learner->Validate UnAssigned Learner On User DashBoard.
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Select the checkbox
    And Click the button until it appear "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And Add an assignment
    And Click the button "Add To Course"
    And publish the course
    And Assign a Learner to the course
    And Unassigned a leaner to the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Unassigned on UserDash Board
    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\saloni.nagpal\Pictures\Athena-1.PNG |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


        @3 @fixed
  Scenario Outline: Create Course->Assigned Learner->Manually complete it->Validate Manually Completed on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Select the checkbox
    And Click the button until it appear "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And Add an assignment
    And Click the button "Add To Course"
    And publish the course
    And Assign a Learner to the course
#    And Click the button "Back"
    And Manually completed course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Manually Completed on User Dashboard


    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\saloni.nagpal\Pictures\Athena-1.PNG |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


@4 @fixed
  Scenario Outline: Create Course->Assigned Learner->complete it->Reattempt it->Validate Reattempt on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Select the checkbox
    And Click the button until it appear "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And Add an assignment
    And Click the button "Add To Course"
    And publish the course
    And Assign a Learner to the course
#    And Click the button "Back"
    And Manually completed course
    And Reattempt the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Reattempt on UserDash Board


    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\saloni.nagpal\Pictures\Athena-1.PNG |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


  @5 @fixed
  Scenario Outline: Create Course->Publish course->Validate course Created
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Select the checkbox
    And Click the button until it appear "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And Add an assignment
    And Click the button "Add To Course"
    And publish the course
    And Select "<module1>", "<submodule1>" from sidebar
    And Search course name
    Then Validate Course Created on User Dashboard

    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\saloni.nagpal\Pictures\Athena-1.PNG |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


  @6
  Scenario Outline: Create Course->Publish course->Validate course Edited on User DashBoard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Select the checkbox
    And Click the button until it appear "Add Content"
    And Add a content
    And Click the button "Add To Course"
    And Add an assignment
    And Click the button "Add To Course"
    And publish the course
   And Search a course in course module
    And Click actions icon of a course
    And Select "Edit Course" from actions dropdown
   # And Select "Edit Course" from dropdown
    And Click the button "No"
    And Update the course
    And Click the button "Submit"
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Course Updated on User Dashboard

    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\saloni.nagpal\Pictures\Athena-1.PNG |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


    @7 @fixed
  Scenario Outline: Create Course->Publish course->Validate course Deleted on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
      And Select the checkbox
      And Click the button until it appear "Add Content"
      And Add a content
      And Click the button "Add To Course"
      And Add an assignment
      And Click the button "Add To Course"
    And publish the course
    And Delete the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Course Deleted on User Dashboard

    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\saloni.nagpal\Pictures\Athena-1.PNG |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


      @8
  Scenario Outline: Create Course->Publish course->Validate Course Summary on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
        And Select the checkbox
    And Click the button until it appear "Add Content"
        And Add a content
        And Click the button "Add To Course"
        And Add an assignment
        And Click the button "Add To Course"
    And publish the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate "<Course>" on User Dashboard

    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|Course|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\saloni.nagpal\Pictures\Athena-1.PNG|abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Course Summary|Content|Assignment|

