Feature:Tests Module features

  Background:Check login to candidate module
    Given Navigate to login page
    Then Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  @1 @pass @test_check
  Scenario Outline: Create Test Placement Drive
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    Then Assign the test to Learner


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |


  @2 @pass @test_check
  Scenario Outline: Create Test Placement Drive->verify show score after submission
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Assign the test to Learner
    And Switch the User "<Username>", "<Password>"
    Then Start with test


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5   |cand2@gmail.com|cand@2|


  @3 @pass @test_check
  Scenario Outline: Create Test Placement Drive->verify Test Reattempt
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Assign the test to Learner
    And Switch the User "<Username>", "<Password>"
    Then Start with test
    Then Validate Test Reattempt


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|checkboxOption|Username|Password|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |2       |cand2@gmail.com|cand@2|


  @4 @pass @test_check
  Scenario Outline: Create Test Placement Drive->Attempt->Validate Candidate Report
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Assign the test to Learner
    And Switch the User "<Username>", "<Password>"
    Then Start with test
    And Switch the User "<Username1>", "<Password1>"
    Then Validate Candidate Report


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|Username1|Password1|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5|cand2@gmail.com|cand@2|pallavi.arora@geminisolutions.com|abcd@123|


  @5 @test_check
  Scenario Outline: Create Test Placement Drive->Attempt->Validate Test Summary Report
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Assign the test to Learner
    And Switch the User "<Username1>", "<Password1>"
    Then Start with test
    And Switch the User "<Username>", "<Password>"
    Then Validate Test Summary Report "<Campus>"


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|Username1|Password1|
      | Tests         |Test Control  |0030    |Other, Other|Beginner|abc        |Logical|5   |pallavi.arora@geminisolutions.com|abcd@123|cand2@gmail.com|cand@2|


  @6 @test_check
  Scenario Outline: Create Test Placement Drive->Attempt Test->Validate Copy Test
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    Then Create and Validate Copy of Test

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner |abc        |Logical|5         |

  @7 @test_check
  Scenario Outline: Create Test Placement Drive->Attempt Test->Validate Edit Test
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    Then Edit and Validate Created Test

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |Load Testing, Gemini|Beginner|abc        |Logical|5         |


  @8 @test_check
  Scenario Outline: Create Test Placement Drive->Attempt Test->Validate Candidate Assigned
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    Then Assign Candidate and Verify it

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |

  @9 @test_check
  Scenario Outline: Create Test Placement Drive->Attempt Test->Evaluate Candidate
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Subjective Question to the section
    And Assign the test to Learner
    And Switch the User "<Username1>", "<Password1>"
    And Start with test
    And Switch the User "<Username>", "<Password>"
    Then Evaluate the Candidate

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|Username1|Password1|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |pallavi.arora@geminisolutions.com|abcd@123|cand2@gmail.com|cand@2|



  @10 @test_check
  Scenario Outline: Create Internal Tests
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
#    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>"
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    Then Validate Test is Created for Internal


    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|TestType|checkboxLabel|
      | Tests         |Test Control  |0030  |Beginner|abc        |Logical|5         |Internal Tests|Internal Test|


  @11 @test_check
  Scenario Outline: Create Internal Test->verify show score after submission
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
#    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>"
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username>", "<Password>"
    And Select Tests from menu
    Then Start with test

    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|Username|Password|checkboxLabel|TestType|learnerModule|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5   |check123@gmail.com|check@123|Internal Test|Internal Tests|Tests|


  @12 @test_check
  Scenario Outline: Create Internal Test->verify Test Reattempt
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
#    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>"
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username>", "<Password>"
    And Select Tests from menu
    Then Start with test
    Then Validate Test Reattempt


    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|checkboxOption|Username|Password|learnerModule|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5         |2       |check123@gmail.com|check@123|Tests|Internal Tests|Internal Test|


  @13 @test_check
  Scenario Outline: Create Internal Test->Attempt Test->Validate Candidate Report
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
#    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>"
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username1>", "<Password1>"
    And Select Tests from menu
    Then Start with test
    And Switch the User "<Username>", "<Password>"
    Then Validate Candidate Report for Internal Test


    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|Username|Password|Username1|Password1|learnerModule|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5|pallavi.arora@geminisolutions.com|abcd@123|check123@gmail.com|check@123|Tests|Internal Tests|Internal Test|


  @14 @test_check
  Scenario Outline: Create Internal Test->Attempt Test->Validate Test Summary Report
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
#    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>"
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username1>", "<Password1>"
    And Select Tests from menu
    Then Start with test
    And Switch the User "<Username>", "<Password>"
    Then Validate Test Summary Report for Internal Test "<Campus>"


    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|Username|Password|Username1|Password1|TestType|learnerModule|checkboxLabel|
      | Tests         |Test Control  |0030    |Other, Other|Beginner|abc        |Logical|5   |pallavi.arora@geminisolutions.com|abcd@123|check123@gmail.com|check@123|    Internal Tests   | Tests       | Internal Test     |


  @15 @test_check
  Scenario Outline: Create Internal Test->Attempt Test->Validate Copy Test
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
#    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>"
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    Then Create and Validate Copy of Internal Test

    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5      |Internal Tests |Internal Test |



  @16 @test_check
  Scenario Outline: Create Internal Test->Attempt Test->Validate Edit Test
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
#    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>"
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Question to the section
    And Validate Test is Created for Internal
    Then Edit and Validate Created Internal Test

    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5         |Internal Tests |Internal Test |


  @17
  Scenario Outline: Create Internal Test->Attempt Test->Evaluate Candidate
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "<TestType>"
    And Click the Internal Test button until it appear "Create Test"
#    And check the checkbox "<checkboxLabel>"
    And Add Test Info for Internal Test  "<Duration>"
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Add Subjective Question to the section
    And Validate Test is Created for Internal
    And Assign the Internal test to Learner
    And Switch the User "<Username1>", "<Password1>"
    And Select Tests from menu
    And Start with test
    And Switch the User "<Username>", "<Password>"
    Then Evaluate the Candidate for Internal Test


    Examples:
      | module        |submodule     |Duration|Level   |Description|Section|Percentage|Username|Password|Username1|Password1|learnerModule|TestType|checkboxLabel|
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5         |pallavi.arora@geminisolutions.com|abcd@123|check123@gmail.com|check@123|Tests | Internal Tests |Internal Test|


  @18
  Scenario Outline: Create Course->Create Test->Validate test Created in Training test
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Course"
    And Enter respective values in course fields "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
    And Enter course description for Test "<description>"
    And Select test checkbox
    And Click the button "Add Content"
    And Validate Filter functionality "<contentName>"
    And Validate "<content>" Add to Course "<contentMessage>"
    And Add Test to the Course "<duration>","<Level>","<description>"
    And Click the Next button
    And Add Select Option details for test
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<duration>"
    And Click the button "Add"
    And Add Question to the section
    And Add to Course
    Then Validate Training Test is Created

    Examples:
      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|content|
      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc|test|Content successfully added. Add more!|Beginner|Logical|5 |Content|


#  @19 @fixing
#  Scenario Outline: Create Course->Create Test->Validate Learner Report in Training Tests
#    Given Select "<module>", "<submodule>" from sidebar
#    When Click the button "Create Course"
#    And Enter respective values in course fields for Training "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
#    And Enter course description for Test "<description>"
#    And Select test checkbox
#    And Click the button "Add Content"
#    And Validate Filter functionality "<contentName>"
#    And Validate "<content>" Add to Course "<contentMessage>"
#    And Add Test to the Course "<duration>","<Level>","<description>"
#    And Click the Next button
#    And Add Select Option details for test
#    And Click the Next button
#    And Add Section "<Section>", "<Percentage>", "<duration>"
#    And Click the button "Add"
#    And Add Question to the section
#    And Add to Course
#    And Assign a Learner to the Course
#    And Switch the User "<Username1>", "<Password1>"
#    And Complete the course
#    And Switch the User "<Username>", "<Password>"
#    Then Validate Learner Report for Training Test
#
#    Examples:
#      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|
#      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc|test|Content successfully added. Add more!|Beginner|Logical|5 |check123@gmail.com|check@123|pallavi.arora@geminisolutions.com|abcd@123|Content|

#
#  @20
#  Scenario Outline: Create Course->Create Test->Validate Test Summary Report in Training Tests
#    Given Select "<module>", "<submodule>" from sidebar
#    When Click the button "Create Course"
#    And Enter respective values in course fields for Training "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
#    And Enter course description for Test "<description>"
#    And Click the button "Add Content"
#    And Validate Filter functionality "<contentName>"
#    And Validate "<content>" Add to Course "<contentMessage>"
#    And Add Test to the Course "<duration>","<Level>","<description>"
#    And Click the Next button
#    And Add Section "<Section>", "<Percentage>", "<duration>"
#    And Click the button "Add"
#    And Add Question to the section
#    And Add to Course
#    And Assign a Learner to the Course
#    And Switch the User "<Username1>", "<Password1>"
#    And Complete the course
#    And Switch the User "<Username>", "<Password>"
#    Then Validate Test Summary Report for Training Test
#
#
#    Examples:
#      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|
#      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc|test|Content successfully added. Add more!|Beginner|Logical|5 |pallavi.arora@geminisolution.com|abcd@123|check123@gmail.com|abcd@123|Content|
#
#
#  @21
#  Scenario Outline: Create Course->Create Test->Complete Course->Edit Test->Validate it is Edited
#    Given Select "<module>", "<submodule>" from sidebar
#    When Click the button "Create Course"
#    And Enter respective values in course fields for Training "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
#    And Enter course description for Test "<description>"
#    And Click the button "Add Content"
#    And Validate Filter functionality "<contentName>"
#    And Validate "<content>" Add to Course "<contentMessage>"
#    And Add Test to the Course "<duration>","<Level>","<description>"
#    And Click the Next button
#    And Add Section "<Section>", "<Percentage>", "<duration>"
#    And Click the button "Add"
#    And Add Question to the section
#    And Add to Course
#    And Assign a Learner to the Course
#    And Edit the test and validate it is edited
#    And Switch the User "<Username1>", "<Password1>"
#    Then Complete the course
#
#
#    Examples:
#      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|
#      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc|test|Content successfully added. Add more!|Beginner|Logical|5 |pallavi.arora@geminisolution.com|abcd@123|check123@gmail.com|abcd@123|Content|
#
#
#
#@22
#  Scenario Outline: Create Course->Create Test->Evaluate Learner
#    Given Select "<module>", "<submodule>" from sidebar
#    When Click the button "Create Course"
#    And Enter respective values in course fields for Training "<courseType>", "<duration>", "<courseTag>", "<fileLocation>", "<category>"
#    And Enter course description for Test "<description>"
#    And Click the button "Add Content"
#    And Validate Filter functionality "<contentName>"
#    And Validate "<content>" Add to Course "<contentMessage>"
#    And Add Test to the Course "<duration>","<Level>","<description>"
#    And Click the Next button
#    And Add Section "<Section>", "<Percentage>", "<duration>"
#    And Click the button "Add"
#    And Add Subjective Question to the section
#    And Add to Course
#    And Assign a Learner to the Course
#    And Switch the User "<Username1>", "<Password1>"
#    And Complete the course
#    And Switch the User "<Username>", "<Password>"
#    Then Evaluate the Learner for Training test
#
#    Examples:
#      | module        |submodule     |courseType|duration|courseTag|category|fileLocation|description|contentName|contentMessage|Level|Section|Percentage|Username1|Password1|Username|Password|content|
#      | Manage Courses|Course Library|Public    |  00:30 |  Java   | Logical| C:\Users\Pallavi.Arora\Downloads\athena.png |abc|test|Content successfully added. Add more!|Beginner|Logical|5 |pallavi.arora@geminisolution.com|abcd@123|check123@gmail.com|abcd@123|Content|
#

  @23
  Scenario Outline: Validate assign candidate screen placement drive
    Given Select "<module>", "<submodule>" from sidebar
    And Switch to assign candidate Screen
    And Validate back button functionality "<testType>"
    And Validate year filter functionality
    And Validate side bar

    Examples:
      | module        |submodule     |testType|
      | Tests         |Test Control  |Placement Drives|


  @24
  Scenario Outline: Validate assign candidate screen Internal Test
    Given Select "<module>", "<submodule>" from sidebar
    And Switch to assign candidate Screen for Internal Test
    And Validate back button functionality "<testType>"
    And Validate year filter functionality for Internal Test
    Then Validate side bar

    Examples:
      | module        |submodule     |testType|
      | Tests         |Test Control  |Internal Tests|


  @25
  Scenario Outline: Validate assign candidate from downloaded template
    Given Select "<module>", "<submodule>" from sidebar
    When we select candidate for Test "<testName>"
    Then Download Sample template for "<Candidate assign>"

    Examples:
      | module        |submodule     |testName |Candidate assign|
      | Tests         |Test Control  |script_fixing|Candidates Assigned|

  @26
  Scenario Outline: Upload assign candidate from excel
    Given Select "<module>", "<submodule>" from sidebar
    When we select candidate for Test "<testName>"
    Then upload assign candidate from excel and validate

    Examples:
      | module        |submodule     |testName  |
      | Tests         |Test Control  |script_fixing|

  @27
  Scenario Outline: Create Test->Create Question->Validate it is created on Test info screen
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Create question and add to section
    Then Validate question added to the section

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |Other, Other|Beginner|abc        |Logical|5         |


  @28
  Scenario Outline: Create Test->Create Question->Edit created question
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Create question and add to section
    Then edit the created question
    Then Validate edit question added to the section

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |


  @29
  Scenario Outline: Create Test->Create Question->Delete created question
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    And Create question and add to section
    Then delete created question and validate it is deleted

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |

  @30
  Scenario Outline: Create Test->Choose Specific Question
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Create Test"
    And Add Test Info
    And Enter Test Description "<Description>"
    And Click the Next button
    And Add Select Options details
    And Click the Next button
    And Add Section "<Section>", "<Percentage>", "<Duration>"
    And Click the button "Add"
    Then choose specific question for test

    Examples:
      | module        |submodule     |Duration|Campus    |Level   |Description|Section|Percentage|
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |

  @31
  Scenario Outline: Assign Candidate->Validate candidate is assigned
    Given Select "<module>", "<submodule>" from sidebar
    When "<Candidates Assigned>" to "<test>"
    And select unassigned User and assign the "<test>"
    Then Validate user is assigned successfully


    Examples:
      | module        |submodule     |Candidates Assigned|test|
      | Tests         |Test Control  |Candidates Assigned|script_fixing|

  @32
  Scenario Outline: Assign Candidate->Validate candidate assign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Candidates Assigned>" to "<test>"
    And select multiple "<count>" unassigned User and assign the "<test>"
    Then Validate "<count>" of candidate assigned

    Examples:
      | module        |submodule     |Candidates Assigned|test|count|
      | Tests         |Test Control  |Candidates Assigned|reg_test|1    |

  @33
  Scenario Outline:  Remove Assign Candidate->Validate candidate Unassign count and popup
    Given Select "<module>", "<submodule>" from sidebar
    When "<Candidates Assigned>" to "<test>"
    And Select multiple "<count>" assigned User and Unassign the "<test>"
    Then Validate "<count>" of candidate Unassigned

    Examples:
      | module        |submodule     |Candidates Assigned|test|count|
      | Tests         |Test Control  |Candidates Assigned|test|0   |

  @34
  Scenario Outline:  Select Assign Candidate of test->Edit View Profile->Validate Candidate updated
    Given Select "<module>", "<submodule>" from sidebar
    When "<Candidates Assigned>" to "<test>"
    Then Edit selected candidate profile and validate


    Examples:
      | module        |submodule     |Candidates Assigned|test|
      | Tests         |Test Control  |Candidates Assigned|test|


