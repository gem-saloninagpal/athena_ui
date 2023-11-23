Feature:UserDashboard Module features

  Background:Check login to candidate module
    Given Navigate to page "Login"
    Then Login using "rahul23@gmail.com" and "abc@123"

    @1
  Scenario Outline: Create Course->Assigned Learner->Validate Assigned Learner in UserDashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Assign a Learner to the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Assigned on UserDash Board
    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


      @2
  Scenario Outline: Create Course->Assigned Learner->Validate UnAssigned Learner On User DashBoard.
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Assign a Learner to the course
    And Unassigned a leaner to the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Unassigned on UserDash Board
    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


        @3
  Scenario Outline: Create Course->Assigned Learner->Manually complete it->Validate Manually Completed on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Assign a Learner to the course
#    And Click the button "Back"
    And Manually completed course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Manually Completed on User Dashboard


    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


@4
  Scenario Outline: Create Course->Assigned Learner->complete it->Reattempt it->Validate Reattempt on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Assign a Learner to the course
#    And Click the button "Back"
    And Manually completed course
    And Reattempt the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Reattempt on UserDash Board


    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


  @5
  Scenario Outline: Create Course->Publish course->Validate course Created
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Course Created on User Dashboard



    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|



#    please correct the edit
  @6
  Scenario Outline: Create Course->Publish course->Validate course Edited on User DashBoard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Update the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Course Updated on User Dashboard



    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


    @7
  Scenario Outline: Create Course->Publish course->Validate course Deleted on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
      And Validate "<content>" Add to Course "<contentMessage>"
      And Validate Filter functionality "<assignmentName>"
      And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Delete the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate Course Deleted on User Dashboard



    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Content|Assignment|


      @8
  Scenario Outline: Create Course->Publish course->Validate Course Summary on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate "<Course>" on User Dashboard

    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|Course|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Course Summary|Content|Assignment|

        @9
  Scenario Outline: Create Course->Publish course->Validate Learner Assigned on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate "<Course>" on User Dashboard

    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|Course|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Assign Learners|Content|Assignment|


          @10
  Scenario Outline: Create Course->Publish course->Validate Learner Report on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
            And Assign a Learner to the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate "<Course>" on User Dashboard

    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|Course|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Learner Reports|Content|Assignment|


            @11
  Scenario Outline: Create Course->Publish course->Validate Edit Course on User Dashboard
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter the "<courseType>", "<duration>", "<fileLocation>", "<category>" in Course fields
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And publish the course
    And Select "<module1>", "<submodule1>" from sidebar
    Then Validate "<Course>" on User Dashboard

    Examples:
      | module        |submodule     |courseType|duration|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|module1|submodule1|Course|content|assignment|
      | Manage Courses|Course Library|Public    |  30  | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|User Dashboard||Edit|Content|Assignment|
