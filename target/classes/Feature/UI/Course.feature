Feature:Course module features

  Background:Check login to candidate module
    Given Navigate to login page
    Then Login using "pallavi.arora@geminisolutions.com" and "abcd@123"
#    Then Login using "testing.user123@gmail.com" and "test@123"

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
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>", "<points>"
    And Enter course description "<description>"
    And Click the button Add Content
    And Validate "<content>" Add to Course "<contentMessage>"
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation                                  |description|contentName|contentMessage|content| points |
      | Manage Courses|Course Library|Public    |  30     |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc        |content1   |Content successfully added. Add more!|Content| 10 |


  @4
  Scenario Outline: Create Course verify Add Assignment
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>", "<points>"
    And Enter course description "<description>"
    And Select the checkbox
    And Click the button Add Content
    And Validate "<content>" Add to Course "<contentMessage>"
#    And Validate Filter functionality "<assignmentName>"
    Then Validate "<assignment>" Add to Course "<assignmentMessage>"
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation                             |description|assignmentName     |contentMessage|assignmentMessage|content|assignment|points|
      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc        |assignment1 |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|10|


  @5
  Scenario Outline: Create Course->Edit and verify the course
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>", "<points>"
    And Enter course description "<description>"
    And Click the button Add Content
    And Validate "<content>" Add to Course "<contentMessage>"
#    Then Edit the Created Course and Verify

    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation                                    |description|assignmentName     |contentMessage                      |assignmentMessage                       |content|assignment|points|
      | Manage Courses|Course Library|Public    |  30    |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc        |regressionTest |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|10          |



#  @6 @working
#  Scenario Outline: Create Course->Complete Course->verify Course Summary
#    Given Select "<module>", "<submodule>" from sidebar
#    When Click the button "Create Course"
#    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>", "<points>"
#    And Enter course description "<description>"
##    And Select the checkbox
#    And Click the button until it appear "Add Content"
##    And Validate "<content>" Add to Course "<contentMessage>"
##    And Validate Filter functionality "<assignmentName>"
##    And Validate "<assignment>" Add to Course "<assignmentMessage>"
#    Then Validate Course Summary Screen
#    Examples:
#      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|content|assignment|points|
#      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc|test_assignment |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|10|

#  @7 @inProgress
#  Scenario Outline: Assign Created Course->verify the result once the user complete the course
#    Given Select "<module>", "<submodule>" from sidebar
#    When A Learner is assign to a course
#    And Change the user's role "<Role>"
##    And Switch the role "<Learner>"
#    And Filter the course and complete it
#    And Select "<module>", "<submodule>" from sidebar
#    Then Verify the Learner Report "<Learner>", "<Email>", "<Percentage>", "<Status>"
#
#    Examples:
#      |Role   |  module      |submodule     |Learner|Email                            |Percentage|Status|
#      |Learner|Manage Courses|Course Library|pallavi |pallavi.arora@geminisolutions.com|100%|Completed|


#  @8
#  Scenario Outline: Reattempt already completed Course
#    Given Select "<module>", "<submodule>" from sidebar
#    When Reattempt the test to the user
#    And Change the user's role "<Role>"
#    And Filter the course and complete it
#    And Select "<module>", "<submodule>" from sidebar
#    Then Verify the Learner Report "<Learner>", "<Email>", "<Percentage>", "<Status>"
#
#    Examples:
#      |Role   |  module      |submodule     |Learner|Email                            |Percentage|Status|
#      |Learner|Manage Courses|Course Library|pallavi |pallavi.arora@geminisolutions.com|100%|Completed|


  @9
  Scenario Outline: Validate count of Course on Course Library and List View should match
    Given Select "<module>", "<submodule>" from sidebar
    When Count of Active Test from Course Library
    And Count the Course from List View Screen
    Then Validate the count

    Examples:
      |  module      |submodule     |
      |Manage Courses|Course Library|


  Scenario Outline: Validate Edit action in Course summary page
    Given Select "<module>", "<submodule>" from sidebar
    When select course and switch to course summary
    Then Validate "<action>" action on Course summary

    Examples:
      | module        |submodule     |action         |
      | Manage Courses|Course Library|Edit Course    |
      | Manage Courses|Course Library|Assign Learners|
      | Manage Courses|Course Library|Learner Reports|

  @fixed
#  Scenario Outline: Validate View as Learner functionality in Course summary screen
#    Given Select "<module>", "<submodule>" from sidebar
#    When select course and switch to course summary
#    Then Validate View as Learner button functionality
#
#    Examples:
#      | module        |submodule     |
#      | Manage Courses|Course Library|


  @new @fixed
  Scenario Outline:  Select learner of different page->Validate Learner assign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And Get assigned learners count
    And Assign a learner
    And Go to next page
    And Assign a learner
    Then Validate count after assigning learners from different pages

    Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |

#  @new @fixed
#  Scenario Outline:  Select learner of different page->Validate Learner Unassign count and popup
#    Given Select "<module>", "<submodule>" from sidebar
#    When "<Learners Assigned>" to "<course>"
#    And Get assigned learners count
#    And Unassign a learner
#    And Go to next page
#    And Unassign a learner
#    Then Validate the count after unassigning learners from different pages

    Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |

  @new
  Scenario Outline:  Filter learner using email->Validate Learner assign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And search user by "<email>" and assign it "<course>"
    Then Validate "<count>" of Learner assigned

    Examples:
      | module        |submodule     |Learners Assigned|course|count|email|
      | Manage Courses       |Course Library  |Assign Learners|course|4    |priyanka.bansal@geminisolutions.com	|

  @new
  Scenario Outline:  Filter learner using email->Validate Learner Unassign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
    And search user by "<email>" and Unassign it "<course>"
    Then Validate "<count>" of Learner Unassigned

    Examples:
      | module        |submodule     |Learners Assigned|course|count|email|
      | Manage Courses       |Course Library  |Assign Learners|course|3    |priyanka.bansal@geminisolutions.com	|


  @new @fixed
  Scenario Outline:  Filter learner using Category dropdown->Validate Learner assign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Learners Assigned>" to "<course>"
#    When Click actions icon of a course
#    And Select "<Learners Assigned>" from actions dropdown
    And Get assigned learners count
    And Filter the Learner using category and assign "<course>"
    Then Validate count after assigning

    Examples:
      | module        |submodule     |Learners Assigned|course|count|
      | Manage Courses       |Course Library  |Assign Learners|course|2    |

#  @new
#  Scenario Outline: Create Course->verify Course Summary->Assign a Learner and update the date of Unattempted course->Validate date is updated successfully
#    Given Select "<module>", "<submodule>" from sidebar
#    When Click the button until it appear "Create Course"
#    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>", "<points>"
#    And Enter course description "<description>"
#    And Select the checkbox
#    And Click the button until it appear "Add Content"
#    And Validate "<content>" Add to Course "<contentMessage>"
#    And Validate Filter functionality "<assignmentName>"
#    And Validate "<assignment>" Add to Course "<assignmentMessage>"
#    And Validate Course Summary Screen
##    And  "<Learners Assigned>" to "<course>"
##    Then Assign "<email>" Learner and edit date for course
#
#    Examples:
#      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|assignmentName|contentMessage|assignmentMessage|content|assignment|email|Learners Assigned|course|points|
#      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc|test_assignment |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|check123@gmail.com|Assign Learners|course|10|


#  @new @toBeChecked
#  Scenario Outline:  Complete Course->update the date of Completed course->Validate date is not updated successfully
#    Given Switch the User "<Username1>", "<Password1>"
#    When Completed the assign course
#    And Switch the User "<Username>", "<Password>"
#    And Select "<module>", "<submodule>" from sidebar
#    And  "<Learners Assigned>" to "<course>"
#    Then Filter completed Course and validate it date can be edited
#
#    Examples:
#      | module        |submodule       |Learners Assigned|course|Username1                         |Password1|Username                         |Password|
#      | Manage Courses|Course Library  |Assign Learners  |course|check123@gmail.com|check@123  |pallavi.arora@geminisolutions.com|abcd@123 |
#

#  @new @tobeFixed
#  Scenario Outline: Create Course->Complete Course->verify Course Summary->Assign a Learner->Keep the course in Progress->Validate date is updated for In progress course successfully
#    Given Select "<module>", "<submodule>" from sidebar
#    When Click the button until it appear "Create Course"
#    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>", "<points>", "<points>"
#    And Enter course description "<description>"
#    And Click the button until it appear "Add Content"
#    And Validate "<content>" Add to Course "<contentMessage>"
#    And Validate Filter functionality "<assignmentName>"
#    And Validate "<assignment>" Add to Course "<assignmentMessage>"
#    And Validate Course Summary Screen
#    And "<Learners Assigned>" to "<course>"
#    And search user by "<email>" and assign it "<course>"
#    And Switch the User "<Username1>", "<Password1>"
#    Then Keep the course in Progress
#    And Switch the User "<Username>", "<Password>"
#    And Select "<module>", "<submodule>" from sidebar
#    And "<Learners Assigned>" to "<course>"
#    Then Validate user able to update the date for inprogress course
#
#    Examples:
#      | module        |submodule     |courseType|duration|courseTag|category|fileLocation                            |description|assignmentName     |contentMessage                       |assignmentMessage                       |content|assignment|email                             |Learners Assigned|course|Username1                         |Password1|Username                         |Password|
#      | Manage Courses|Course Library|Public    |  30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc       |regressionTest |Content successfully added. Add more!|Assignment successfully added. Add more!|Content|Assignment|rahul.adhikari@geminisolutions.com|Assign Learners  |course|rahul.adhikari@geminisolutions.com|abc@123  |saloni.nagpal@geminisolutions.com|abc@123|
