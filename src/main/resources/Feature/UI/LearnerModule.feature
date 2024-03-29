Feature: Learner module features

  Background:Check login to Learner module
    Given Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"

  @regression012 @1
  Scenario Outline: Create Course->Assign Learner->Start Course and navigate back->Course Resume text Validation
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    Then Create a Course for Learner "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>","<description>","<contentMessage>","<assignmentMessage>"
    And A Learner is assign to a course
    And Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Verify View Course Button and click it
    And Validate Resume Course after starting the course

    Examples:
      |submodule|module|option1|courseType|duration|courseTag|fileLocation                                    |category|description|contentMessage|assignmentMessage|
      |Course Library|Manage Courses|Learner|  Public  |  30    |  Java   | C:\Users\rahul.adhikari\Pictures\testImage.png |Logical |abc        |              |                 |


  @regression012 @2 @fail
  Scenario Outline: Complete Resumed course->Download certificate->Validate certificate downloaded
    When Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Complete the Course and Download the certificate

    Examples:
      |option1|
      |Learner|


  @regression012 @3 @fail
  Scenario Outline: Validate Ongoing and Completed Course Count
    When Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Validate the count of Ongoing and Completed Course
    And Switch to Completed Tab and validate it functionality
    And Validate Course Summary

    Examples:
      |option1|
      |Learner|


    @4
  Scenario Outline: Create Course-> switch to Course catalog->validate Enroll button functionality
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    Then Create a Course for Learner "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>","<description>","<contentMessage>","<assignmentMessage>"
    And Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Switch to Course Catalog Screen
    Then Validate the Enroll button functionality


    Examples:
      |submodule|module|option1|courseType|duration|courseTag|fileLocation                                    |category|description|contentMessage|assignmentMessage|
      |Course Library|Manage Courses|Learner|  Public  |  30    |  Java   |C:\Users\rahul.adhikari\Pictures\testImage.png |Logical |abc        |              |                 |


@5 @fail
  Scenario Outline: Create Course->Switch to Course catalog->Validate course overview
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    Then Create a Course for Learner "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>","<description>","<contentMessage>","<assignmentMessage>"
    And Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Switch to Course Catalog Screen
    Then Validate course overview

    Examples:
      |submodule|module|option1|courseType|duration|courseTag|fileLocation                                    |category|description|contentMessage|assignmentMessage|
      |Course Library|Manage Courses|Learner|  Public  |  30    |  Java   | C:\Users\rahul.adhikari\Pictures\testImage.png |Logical |abc        |              |                 |


@6
  Scenario Outline: Create Course->Start Course->Validate Upload assignment file
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    Then Create a Course for Learner "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>","<description>","<contentMessage>","<assignmentMessage>"
    And A Learner is assign to a course
    And Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Verify View Course Button and click it
    Then start with course and validate upload assignment file "<fileLocation1>"

    Examples:
      |submodule|module|option1|courseType|duration|courseTag|fileLocation                                    |category|description|contentMessage|assignmentMessage|fileLocation1|
      |Course Library|Manage Courses|Learner|  Public  |  30    |  Java   | C:\Users\rahul.adhikari\Pictures\testImage.png |Logical |abc        |              |                 |C:\Users\rahul.adhikari\Downloads\dwsample1-zip.zip|


    @7
  Scenario Outline: Create Course->Start Course->Validate View Upload file
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button until it appear "Create Course"
    Then Create a Course for Learner "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>","<description>","<contentMessage>","<assignmentMessage>"
    And A Learner is assign to a course
    And Expand user dropdown from navbar
    Then Verify the options present in dropdown and select it "<option1>"
    And Verify View Course Button and click it
    And start with course and validate upload assignment file "<fileLocation1>"
    Then start with course and validate View upload file

    Examples:
      |submodule|module|option1|courseType|duration|courseTag|fileLocation                                    |category|description|contentMessage|assignmentMessage|fileLocation1|
      |Course Library|Manage Courses|Learner|  Public  |  30    |  Java   | C:\Users\rahul.adhikari\Pictures\testImage.png |Logical |abc        |              |                 |C:\Users\rahul.adhikari\Downloads\dwsample1-zip.zip|


      @8
  Scenario Outline: Validate non existing course
    When Expand user dropdown from navbar
    And Verify the options present in dropdown and select it "<option1>"
    Then Validate Non existing completed course

    Examples:
      |option1|
      |Learner|

  @9
  Scenario Outline: Validate non existing course in Course catalog
    When Expand user dropdown from navbar
    And Verify the options present in dropdown and select it "<option1>"
    Then Validate Non existing course in course catalog

    Examples:
      |option1|
      |Learner|

