@regression
Feature:Course module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"

    @1
  Scenario Outline: Create Course Verify Owner
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    Then Verify the Owner of the Created Course

    Examples:
      | module        |submodule     |
      | Manage Courses|Course Library|

      @2
  Scenario Outline: Create Course Verify Course Info Tree
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    Then Verify course info tree for assignment and test checkbox

    Examples:
      | module        |submodule     |
      | Manage Courses|Course Library|


        @3
  Scenario Outline: Create Course verify Add Content
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button "Add Content"
    And Validate Filter functionality "<contentName>"
    And Validate "<content>" Add to Course "<contentMessage>"
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|content|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|content -01|Content successfully added. Add more!|Content|


          @4
  Scenario Outline: Create Course verify Add Assignment
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    Then Validate "<assignment>" Add to Course "<assignmentMessage>"
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|content|assignment|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|


            @5
  Scenario Outline: Create Course->Edit and verify the course
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    Then Edit the Created Course and Verify

    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation                                    |description|assignmentName     |contentMessage                      |assignmentMessage                       |content|assignment|
      | Manage Courses|Course Library|Public    |  30    |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png|abc        |new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|


              @6
  Scenario Outline: Create Course->Complete Course->verify Course Summary
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    Then Validate Course Summary Screen
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|content|assignment|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|

@7
  Scenario Outline: Assign Created Course->verify the result once the user complete the course
    Given Select "<module>", "<submodule>" from sidebar
    When A Learner is assign to a course
    And Change the user's role "<Role>"
    And Filter the course and complete it
    And Select "<module>", "<submodule>" from sidebar
    Then Verify the Learner Report "<Learner>", "<Email>", "<Percentage>", "<Status>"

    Examples:
    |Role   |  module      |submodule     |Learner|Email|Percentage|Status|
    |Learner|Manage Courses|Course Library|rahul|rahul.adhikari@geminisolutions.com|100%|Completed|


  @8
  Scenario Outline: Reattempt already completed Course
    Given Select "<module>", "<submodule>" from sidebar
    When Reattempt the test to the user
    And Change the user's role "<Role>"
    And Filter the course and complete it
    And Select "<module>", "<submodule>" from sidebar
    Then Verify the Learner Report "<Learner>", "<Email>", "<Percentage>", "<Status>"

    Examples:
      |Role   |  module      |submodule     |Learner|Email|Percentage|Status|
      |Learner|Manage Courses|Course Library|rahul|rahul.adhikari@geminisolutions.com|100%|Completed|


    @9
  Scenario Outline: Validate count of Course on Course Library and List View should match
    Given Select "<module>", "<submodule>" from sidebar
    When Count of Active Test from Course Library
    And Count the Course from List View Screen
    Then Validate the count

    Examples:
      |  module      |submodule     |
      |Manage Courses|Course Library|


  @Bug @10
  Scenario Outline: Create Course->Inactivate->Validate
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And Validate Course Summary Screen
    Then Inactive the course and Validate
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|content|assignment|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|



    @11
  Scenario Outline: Validate Edit action in Course summary page
    Given Select "<module>", "<submodule>" from sidebar
    When select course and switch to course summary
    Then Validate "<action>" action on Course summary

    Examples:
      | module        |submodule     |action         |
      | Manage Courses|Course Library|Edit Course    |
      | Manage Courses|Course Library|Assign Learners|
      | Manage Courses|Course Library|Learner Reports|


      @12
  Scenario Outline: Validate View as Learner button functionality in Course summary screen
    Given Select "<module>", "<submodule>" from sidebar
    When select course and switch to course summary
    Then Validate View as Learner button functionality

    Examples:
      | module        |submodule     |
      | Manage Courses|Course Library|


  @13
  Scenario Outline: Validate Course status in Learner Report
    Given Select "<module>", "<submodule>" from sidebar
    When select course and switch to Learner Report
    Then Validate course Status

   #Then Validate Status of Course
    Examples:
      | module        |submodule     |
      | Manage Courses|Course Library|


      @14
  Scenario Outline: Assign Learner->Validate Learner assign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And select multiple "<count>" unassigned User and assign the "<course>"
    Then Validate "<count>" of Learner assigned

      Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|4    |


        @15
  Scenario Outline:  Remove Assign Learner->Validate Learner Unassign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And Select multiple "<count>" assigned User and Unassign the "<course>"
    Then Validate "<count>" of Learner Unassigned

    Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|0    |



  @16
  Scenario Outline:  Select learner of different page->Validate Learner assign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And select multiple "<count>" Unassigned user of different page and Assign the "<course>"
    Then Validate "<count>" of Learner assigned

    Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |

  @17
  Scenario Outline:  Select learner of different page->Validate Learner Unassign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And select multiple "<count>" Assigned User of different page and Unassign the "<course>"
    Then Validate "<count>" of Learner Unassigned

    Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |

  @18
  Scenario Outline:  Filter learner using email->Validate Learner assign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And search user by "<email>" and assign it "<course>"
    Then Validate "<count>" of Learner assigned

    Examples:
      | module        |submodule     |Learners Assigned|course|count|email|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |msahhjj@gmail.com	|

  @19
  Scenario Outline:  Filter learner using email->Validate Learner Unassign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And search user by "<email>" and Unassign it "<course>"
    Then Validate "<count>" of Learner Unassigned

    Examples:
      | module        |submodule     |Learners Assigned|course|count|email|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |msahhjj@gmail.com	|


  @20
  Scenario Outline:  Filter learner using Category dropdown->Validate Learner assign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And Filter the Learner using category and assign "<course>"
    Then Validate "<count>" of Learner assigned

    Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |

@21
  Scenario Outline: Create Course->verify Course Summary->Assign a Learner and update the date of Unattempted course->Validate date is updated successfully
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And Validate Course Summary Screen
    And  "<Learners Assigned>" to "<course>"
    Then Assign "<email>" Learner and edit date for course

    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|content|assignment|email|Learners Assigned|course|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|rahul.adhikari2018@gmail.com|Assign Learners|course|


  @22
  Scenario Outline:  Complete Course->update the date of Completed course->Validate date is not updated successfully
    Given Switch the User "<Username1>", "<Password1>"
    When Completed the assign course
    And Switch the User "<Username>", "<Password>"
    And Select "<module>", "<submodule>" from sidebar
    And  "<Learners Assigned>" to "<course>"
    Then Filter completed Course and validate it date can be edited


    Examples:
      | module        |submodule     |Learners Assigned|course|Username1|Password1|Username|Password|
      | Manage Courses       |Course Library  |Assign Learners|course|rahul.adhikari2018@gmail.com|abc@123|rahul23@gmail.com|abc@123|


@23
  Scenario Outline: Create Course->Complete Course->verify Course Summary->Assign a Learner->Keep the course in Progress->Validate date is updated for In progress course successfully
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description "<description>"
    And Click the button until it appear "Add Content"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Validate Filter functionality "<assignmentName>"
    And Validate "<assignment>" Add to Course "<assignmentMessage>"
    And Validate Course Summary Screen
    And "<Learners Assigned>" to "<course>"
    And search user by "<email>" and assign it "<course>"
    And Switch the User "<Username1>", "<Password1>"
    Then Keep the course in Progress
    And Switch the User "<Username>", "<Password>"
    And Select "<module>", "<submodule>" from sidebar
    And "<Learners Assigned>" to "<course>"
    Then Validate user able to update the date for inprogress course

  Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|content|assignment|email|Learners Assigned|course|Username1|Password1|Username|Password|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|new assignment-01 |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|rahul.adhikari2018@gmail.com|Assign Learners|course|rahul.adhikari2018@gmail.com|abc@123|rahul23@gmail.com|abc@123|

  @24
  Scenario Outline:  Select learner of different page->Assign course->Change date and validate it is changed
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And select multiple "<count>" Unassigned user of different page and Assign the "<course>"
    And Select the assign "<count>" Learner of different page change date of "<course>" and validate
    Then Validate date is updated successfully in Paginating


    Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |

@25
  Scenario Outline: Create Course->Create Test->Validate Learners Detailed Report
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields for Training "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description for Test "<description>"
    And Click the button "Add Content"
    And Validate Filter functionality "<contentName>"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Add Test to the Course "<duration>","<Level>","<description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<duration>"
    And Click the button "Add"
    And Add Question to the section
    And Add to Course
    And Assign a Learner to the Course
    And Switch the User "<Username1>", "<Password1>"
    And Change the user's role "<Role>"
    And Complete the course
    And Switch the User "<Username>", "<Password>"
    And Select "<module>", "<submodule>" from sidebar
    Then Validate Learners Report

    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|Role|
      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.png |abc|content -01|Content successfully added. Add more!|Beginner|Logical|5 |rahul.adhikari@geminisolutions.com|abc@123|rahul.adhikari@geminisolutions.com|abc@123|Content|Learner|

    @bug
  Scenario Outline:  Upload wrong Email Learner
    Given Select "<module>", "<submodule>" from sidebar
    When "<Assign Learners>" to "<Course>"
    And switch to bulk upload screen "<User>"
    Then upload and validate Invalid User is not Uploaded "<filelocation>"

    Examples:
      | module        |submodule     |Assign Learners|Course|User|filelocation|
      | Manage Courses|Course Library |Assign Learners|Course|Learner|C:\Users\rahul.adhikari\Downloads\Sample_Candidate_Assignment_wrongEmail.xlsx|

      @bug
  Scenario Outline:  Upload assigned Candidate
    Given Select "<module>", "<submodule>" from sidebar
    When "<Assign Learners>" to "<Course>"
    And switch to bulk upload screen "<User>"
    Then upload assigned user and validate "<filelocation>"

    Examples:
      | module        |submodule     |Assign Learners|Course|User|filelocation|
      | Manage Courses|Course Library  |Assign Learners|Course|Candidate|C:\Users\rahul.adhikari\Downloads\Sample_Candidate_Assignment_assignedUser.xlsx|


  Scenario Outline:  Upload Unassigned Candidate
    Given Select "<module>", "<submodule>" from sidebar
    When "<Assign Learners>" to "<Course>"
    And switch to bulk upload screen "<User>"
    Then upload Unassigned "<Learner>" and validate "<filelocation>"

    Examples:
      | module        |submodule     |Assign Learners|Course|User|filelocation|Learner|
      | Manage Courses|Course Library  |Assign Learners|Course|Learner|C:\Users\rahul.adhikari\Downloads\Sample_Candidate_Assignment_UnassignedUser.xlsx|user12@gmail.com|


  Scenario Outline:  Upload Multiple Unassigned User
    Given Select "<module>", "<submodule>" from sidebar
    When "<Assign Learners>" to "<Course>"
    And switch to bulk upload screen "<User>"
    Then Upload Multiple Unassigned "<Learner>" and Validate "<filelocation>"

    Examples:
      | module        |submodule     |Assign Learners|Course|User|filelocation|Learner|
      | Manage Courses|Course Library  |Assign Learners|Course|Learner|C:\Users\rahul.adhikari\Downloads\Sample_Candidate_Assignment_MultipleUnassignedUser.xlsx|user12@gmail.com|


  Scenario Outline:  Upload New Learner
    Given Select "<module>", "<submodule>" from sidebar
    When "<Assign Learners>" to "<Course>"
    And switch to bulk upload screen "<User>"
    Then Upload New "<Learner>" and Validate "<filelocation>"


    Examples:
      | module        |submodule     |Assign Learners|Course|User|filelocation|Learner|
      | Manage Courses|Course Library  |Assign Learners|Course|Learner|C:\Users\rahul.adhikari\Downloads\Sample_Candidate_Assignment_MultipleUnassignedUser.xlsx|user12@gmail.com|
