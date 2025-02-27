Feature:Tests Module features

  Background:Check login to candidate module
    Given Navigate to login page
    Then Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  @1 @placement_test
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


  @2 @placement_test
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
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5   |regression@gmail.com|reg@123|


  @3 @placement_test
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
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |2       |regression@gmail.com|reg@123|


  @4 @placement_test
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
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5|regression@gmail.com|reg@123|pallavi.arora@geminisolutions.com|abcd@123|


  @5 @placement_test
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
      | Tests         |Test Control  |0030    |Other, Other|Beginner|abc        |Logical|5   |pallavi.arora@geminisolutions.com|abcd@123|regression@gmail.com|reg@123|


  @6 @placement_test
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

  @7 @placement_test
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


  @8 @placement_test
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

  @9 @placement_test
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
      | Tests         |Test Control  |0030    |DELL, DELL|Beginner|abc        |Logical|5         |pallavi.arora@geminisolutions.com|abcd@123|regression@gmail.com|reg@123|



  @10 @internal_test
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


  @11 @internal_test
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
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5   |vaibhav.batra@geminisolutions.com|abcd@123|Internal Test|Internal Tests|Tests|


  @12 @internal_test
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
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5         |2       |vaibhav.batra@geminisolutions.com|abcd@123|Tests|Internal Tests|Internal Test|


  @13 @internal_test
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
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5|pallavi.arora@geminisolutions.com|abcd@123|vaibhav.batra@geminisolutions.com|abcd@123|Tests|Internal Tests|Internal Test|


  @14 @internal_test
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
      | Tests         |Test Control  |0030    |Other, Other|Beginner|abc        |Logical|5   |pallavi.arora@geminisolutions.com|abcd@123|vaibhav.batra@geminisolutions.com|abcd@123|    Internal Tests   | Tests       | Internal Test     |


  @15 @internal_test
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



  @16 @internal_test
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


  @17 @internal_test
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
      | Tests         |Test Control  |0030    |Beginner|abc        |Logical|5         |pallavi.arora@geminisolutions.com|abcd@123|vaibhav.batra@geminisolutions.com|abcd@123|Tests | Internal Tests |Internal Test|


  @18 @internal_test
  Scenario Outline: Validate assign candidate screen Internal Test
    Given Select "<module>", "<submodule>" from sidebar
    And Switch to assign candidate Screen for Internal Test
    And Validate back button functionality "<testType>"
    And Validate year filter functionality for Internal Test
    Then Validate side bar

    Examples:
      | module        |submodule     |testType|
      | Tests         |Test Control  |Internal Tests|


  @25 @placement_test
  Scenario Outline: Validate assign candidate from downloaded template
    Given Select "<module>", "<submodule>" from sidebar
    When we select candidate for Test "<testName>"
    Then Download Sample template for "<Candidate assign>"

    Examples:
      | module | submodule    | testName      | Candidate assign    |
      | Tests  | Test Control | reg-check-new | Candidates Assigned |

  @26 @placement_test
  Scenario Outline: Upload assign candidate from excel
    Given Select "<module>", "<submodule>" from sidebar
    When we select candidate for Test "<testName>"
    Then upload assign candidate from excel and validate

    Examples:
      | module | submodule    | testName      |
      | Tests  | Test Control | reg-check-new |

  @27 @placement_test
  Scenario Outline: Assign Candidate->Validate candidate is assigned
    Given Select "<module>", "<submodule>" from sidebar
    When "<Candidates Assigned>" to "<test>"
    And select unassigned User and assign the "<test>"
    Then Validate user is assigned successfully


    Examples:
      | module        |submodule     |Candidates Assigned |test          |
      | Tests         |Test Control  |Candidates Assigned |reg-check-new |


  @29 @placement_test
  Scenario Outline:  Select Assign Candidate of test->Edit View Profile->Validate Candidate updated
    Given Select "<module>", "<submodule>" from sidebar
    When "<Candidates Assigned>" to "<test>"
    Then Edit selected candidate profile and validate


    Examples:
      | module        |submodule     |Candidates Assigned|test|
      | Tests         |Test Control  |Candidates Assigned|reg-check-new|


