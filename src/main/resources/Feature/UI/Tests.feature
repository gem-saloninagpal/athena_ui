Feature:Tests Module features

  Background:Check login to candidate module
    Given Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"

    @1
  Scenario Outline: Create Test Placement Drive
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created
    Then Assign the test to Learner


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |


      @2
  Scenario Outline: Create Test Placement Drive->verify show score after submission
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created
    And Assign the test to Learner
    And Switch the User "<Username>", "<Password>"
    Then Start with test


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5   |rahul22@gmail.com|abc@123|



        @3
  Scenario Outline: Create Test Placement Drive->verify Test Reattempt
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
#    And Check the Required Checkbox Filter "<checkboxOption>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created
    And Assign the test to Learner
    And Switch the User "<Username>", "<Password>"
    Then Start with test
    Then Validate Test Reattempt


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|checkboxOption|Username|Password|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |2       |rahul22@gmail.com|abc@123|


@4
  Scenario Outline: Create Test Placement Drive->Attempt->Validate Candidate Report
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created
    And Assign the test to Learner
    And Switch the User "<Username1>", "<Password1>"
    Then Start with test
    And Switch the User "<Username>", "<Password>"
    Then Validate Candidate Report


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|Username1|Password1|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5|rahul23@gmail.com|abc@123|rahul22@gmail.com|abc@123|


  @5
  Scenario Outline: Create Test Placement Drive->Attempt->Validate Test Summary Report
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created
    And Assign the test to Learner
    And Switch the User "<Username1>", "<Password1>"
    Then Start with test
    And Switch the User "<Username>", "<Password>"
    Then Validate Test Summary Report "<Campus>"


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|Username1|Password1|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5   |rahul23@gmail.com|abc@123|rahul22@gmail.com|abc@123|


    @6
  Scenario Outline: Create Test Placement Drive->Attempt->Validate Copy Test
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created
    Then Create and Validate Copy of Test "<EndDate>"


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner |abc        |Logical|5         |

      @7
  Scenario Outline: Create Test Placement Drive->Attempt->Validate Edit Test
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created
    Then Edit and Validate Created Test



    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |


        @8
  Scenario Outline: Create Test Placement Drive->Attempt->Validate Candidate Assigned
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created
    Then Assign Candidate and Verify it

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |

          @9
  Scenario Outline: Create Test Placement Drive->Attempt->Evaluate Candidate
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Subjective Question to the section
    And Validate Test is Created
    And Assign the test to Learner
    And Switch the User "<Username1>", "<Password1>"
    And Start with test
    And Switch the User "<Username>", "<Password>"
    Then Evaluate the Candidate

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|Username1|Password1|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |rahul23@gmail.com|abc@123|rahul22@gmail.com|abc@123|



            @10
  Scenario Outline: Create Internal Tests
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    Then Validate Test is Created for Internal
#    Then Assign the test to Learner


    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|TestType|checkboxLabel|
      | Tests         |Test Control  |0030  |Beginner|abc        |Logical|5         |Internal Tests|Internal Test|


              @11
  Scenario Outline: Create Internal Test->verify show score after submission
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username>", "<Password>"
    And Select "<learnerModule>" from sidebar
    Then Start with test


    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|Username|Password|checkboxLabel|TestType|learnerModule|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5   |rahul44@gmail.com|abc@123|Internal Test|Internal Tests|Tests|


                @12
  Scenario Outline: Create Internal Test->verify Test Reattempt
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>", "<Level>"
    And Enter Test Description "<Description>"
#    And Check the Required Checkbox Filter "<checkboxOption>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username>", "<Password>"
    And Select "<learnerModule>" from sidebar
    Then Start with test
    Then Validate Test Reattempt


    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|checkboxOption|Username|Password|learnerModule|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5         |2       |rahul44@gmail.com|abc@123|Tests|Internal Tests|Internal Test|


                  @13
  Scenario Outline: Create Internal Test->Attempt->Validate Candidate Report
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username1>", "<Password1>"
    And Select "<learnerModule>" from sidebar
    Then Start with test
    And Switch the User "<Username>", "<Password>"
    Then Validate Candidate Report for Internal Test



    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|Username|Password|Username1|Password1|learnerModule|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5|rahul23@gmail.com|abc@123|rahul44@gmail.com|abc@123|Tests|Internal Tests|Internal Test|


                    @14
  Scenario Outline: Create Internal Test->Attempt->Validate Test Summary Report
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username1>", "<Password1>"
    And Select "<learnerModule>" from sidebar
    Then Start with test
    And Switch the User "<Username>", "<Password>"
    Then Validate Test Summary Report for Internal Test "<Campus>"


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|Username1|Password1|TestType|learnerModule|checkboxLabel|
      | Tests         |Test Control  |0030    |Other, Other|Beginner|abc        |Logical|5   |rahul23@gmail.com|abc@123|rahul44@gmail.com|abc@123|    Internal Tests   | Tests       | Internal Test     |


            @15
  Scenario Outline: Create Internal Test->Attempt->Validate Copy Test
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    Then Create and Validate Copy of Internal Test "<EndDate>"

    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5      |Internal Tests |Internal Test |



              @16
  Scenario Outline: Create Internal Test->Attempt->Validate Edit Test
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    Then Edit and Validate Created Internal Test

    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5         |Internal Tests |Internal Test |




                @17
  Scenario Outline: Create Internal Test->Attempt->Evaluate Candidate
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Subjective Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username1>", "<Password1>"
    And Select "<learnerModule>" from sidebar
    And Start with test
    And Switch the User "<Username>", "<Password>"
    Then Evaluate the Candidate for Internal Test


    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|Username|Password|Username1|Password1|learnerModule|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5         |rahul23@gmail.com|abc@123|rahul44@gmail.com|abc@123|Tests | Internal Tests |Internal Test|




                  @18
  Scenario Outline: Create Course->Create Test->Validate test Created in Training test
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
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
    Then Validate Training Test is Created


    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|content|
      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|content -01|Content successfully added. Add more!|Beginner|Logical|5 |Content|



                    @19
  Scenario Outline: Create Course->Create Test->Validate Learner Report in Training Tests
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
    And Complete the course
    And Switch the User "<Username>", "<Password>"
    Then Validate Learner Report for Training Test


    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|
      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|content -01|Content successfully added. Add more!|Beginner|Logical|5 |rahul44@gmail.com|abc@123|rahul23@gmail.com|abc@123|Content|



                      @20
  Scenario Outline: Create Course->Create Test->Validate Test Summary Report in Training Tests
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
    And Complete the course
    And Switch the User "<Username>", "<Password>"
    Then Validate Test Summary Report for Training Test


    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|
      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|content -01|Content successfully added. Add more!|Beginner|Logical|5 |rahul44@gmail.com|abc@123|rahul23@gmail.com|abc@123|Content|



                        @21
  Scenario Outline: Create Course->Create Test->Complete Course->Edit Test->Validate it is Edited
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
    And Edit the test and validate it is edited
    And Switch the User "<Username1>", "<Password1>"
    Then Complete the course


    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|
      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|content -01|Content successfully added. Add more!|Beginner|Logical|5 |rahul44@gmail.com|abc@123|rahul23@gmail.com|abc@123|Content|



@22
  Scenario Outline: Create Course->Create Test->Evaluate Learner
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
    And Add Subjective Question to the section
    And Add to Course
    And Assign a Learner to the Course
    And Switch the User "<Username1>", "<Password1>"
    And Complete the course
    And Switch the User "<Username>", "<Password>"
    Then Evaluate the Learner for Training test
    
    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|
      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\rahul.adhikari\Pictures\testImage.jpg |abc|content -01|Content successfully added. Add more!|Beginner|Logical|5 |rahul44@gmail.com|abc@123|rahul23@gmail.com|abc@123|Content|


  @23
  Scenario Outline: Validate assign candidate screen placement drive
    Given Select "<module>", "<submodule>" from sidebar
    And Switch to assign candidate Screen
    And Validate back button functionality "<testType>"
    And Validate year filter functionality
    And Validate side bar
    And Validate paginator functionality of page
#    Then Validate Assign Multiple User Functionality

    Examples:
      | module        |submodule     |testType|
      | Tests         |Test Control  |Placement Drives|


    @24
  Scenario Outline: Validate assign candidate screen Internal Test
    Given Select "<module>", "<submodule>" from sidebar
    And Switch to assign candidate Screen for Internal Test
    And Validate back button functionality "<testType>"
    And Validate year filter functionality for Internal Test
    And Validate side bar
    And Validate paginator functionality of page
#    Then Validate Assign Multiple User Functionality

    Examples:
      | module        |submodule     |testType|
      | Tests         |Test Control  |Internal Tests|


      @to_be_completed
  Scenario Outline: Validate assign candidate from downloaded template
    Given Select "<module>", "<submodule>" from sidebar
    When we select candidate for Test "<testName>"
    And Download Sample template for "<Candidate assign>"
    Examples:
      | module        |submodule     |testName |Candidate assign|
      | Tests         |Test Control  |durationnn|Candidates Assigned|


  Scenario Outline: Create Test->Create Question->Validate it is created on Test info screen
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Create question and add to section
    Then Validate question added to the section

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |


  Scenario Outline: Create Test->Create Question->Edit created question
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info "<Duration>", "<Campus>", "<Level>"
    And Enter Test Description "<Description>"
    And Click the button "Next"
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Create question and add to section
    Then edit the created question
    Then Validate question added to the section

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |
