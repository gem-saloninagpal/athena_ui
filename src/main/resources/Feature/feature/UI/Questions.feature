Feature: Questions functionality

  Background:
    Given Navigate to page "login"
    And Login using "saloni02@gmail.com" and "abc@123"


    @regression @questionModule
    Scenario Outline: Create a question
      Given Select "<module>", "<submodule>" from sidebar
      When Click the button "Add New"
      And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>"
      And Enter marks "<marks>"
      And Click the button "Next"
      And Enter question description "<question1>"
      And Enter options and select a correct option
      And Click the button "Save & Add More"
      And Enter question description "<question2>"
      And Enter options and select a correct option
      And Click the button "Save & Exit"
      Then Verify the question is created "<question1>", "<question2>"

      Examples:
      |module |submodule|level|type                    |section|difficulty|skills|marks|question1|question2|
      |Tests  |Questions|Basic|Multiple choice question|Logical|Hard      |Java  |10   |ques-1?  |ques-2?  |
      |Tests  |Questions|Basic|Checkbox question       |Logical|Hard      |Java  |10   |ques-1!  |ques-2!  |


  @questionModule
  Scenario Outline: Edit a question and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Click Actions icon of recently created question
    And Select "Edit" from actions dropdown
    And Click the edit icon
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>"
    And Enter marks "<marks>"
    And Click the button "Next"
    When Enter question description "<question1>"
    And Enter options and select a correct option
    And Click the button "Update & Add More"
    And Enter question description "<question2>"
    And Enter options and select a correct option
    And Click the button "Update & Exit"
    Then Verify the question is created "<question1>", "<question2>"
    Examples:
      |module |submodule|level       |type                    |section  |difficulty|skills|marks|question1 |question2 |
      |Tests  |Questions|Intermediate|Multiple choice question|Logical  |Easy      |Java  |10   |Question-1|Question-2|
      |Tests  |Questions|Intermediate|Checkbox question       |Technical|Hard      |Java  |10   |question-1|question-2|

  @questionModule
    Scenario Outline: View question
      Given Select "<module>", "<submodule>" from sidebar
      When Click Actions icon of recently created question
      And Select "View" from actions dropdown
      And Verify question dialog box opens
      Then Verify question on view "<question>"
      Examples:
        |module |submodule|question  |
        |Tests  |Questions|question-2|

  @questionModule
    Scenario Outline: Delete question
      Given Select "<module>", "<submodule>" from sidebar
      When Click Actions icon of recently created question
      And Select "Delete" from actions dropdown
      Then Verify confirmation dialog box appears
      When Click the button "Yes"
      Then Verify the popup message "Question deleted successfully!"
      Examples:
        |module |submodule|
        |Tests  |Questions|

  @questionModule
    Scenario Outline:Create subjective question
      Given Select "<module>", "<submodule>" from sidebar
      When Click the button "Add New"
      And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>"
      And Enter marks "<marks>"
      And Click the button "Next"
      And Enter question description in subjective
      And Enter word limit "10"
      And Click the button "Save & Add More"
      And Enter question description in subjective
      And Expand the dropdown "Has Word Limit?"
      And Click the button "Save & Exit"
      Then Verify the subjective questions
      Examples:
        |module |submodule|level|type                       |section|difficulty|skills|marks|
        |Tests  |Questions|Basic|Subjective answer questions|Logical|Hard      |Java  |10   |

  @questionModule
    Scenario Outline:Create comprehension question(both positive and negative flow)-MCQ and Checkbox
      Given Select "<module>", "<submodule>" from sidebar
      When Switch to "Comprehensions"
      And Click Add New in comprehensions tab
      And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>"
      And Enter marks "<marks>"
      And Click the button "Next"
      Then Click the button "Save Passage & Continue" and verify the message "Passage cannot be empty"
      When Enter the passage
      And Click the button "Save Passage & Continue"
      And Enter question description related passage "<question1>"
      And Enter options and select a correct option
      And Click the button "Save & Add More"
      And Click the button "Edit Passage"
      Then Verify the passage and edit
      When Click the button "Save Passage & Continue"
      And Enter question description related passage "<question2>"
      And Enter options and select a correct option
      And Click the button "Save & Exit"
      Then Verify the passage is created
      And Expand the passage field
      Then Verify the comprehension question is created "<question1>", "<question2>"
      Examples:
        |module |submodule|level  |type                    |section  |difficulty|skills|marks|question1  |question2|
        |Tests  |Questions|Basic  |Multiple choice question|Logical  |Hard      |Java  |10   |quess-1?   |quess-2? |
        |Tests  |Questions|Advance|Checkbox question       |Technical|Easy      |Java  |10   |quess-1!!  |quess-2!!|

  @questionModule
  Scenario Outline:Create comprehension based subjective question and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Comprehensions"
    And Click Add New in comprehensions tab
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>"
    And Enter marks "<marks>"
    And Click the button "Next"
    Then Click the button "Save Passage & Continue" and verify the message "Passage cannot be empty"
    When Enter the passage
    And Click the button "Save Passage & Continue"
    And Enter comprehension based subjective question
    And Expand the dropdown "Has Word Limit?"
    And Select "Yes" from dropdown
    And Enter word limit "10"
    And Click the button "Save & Add More"
    And Click the button "Edit Passage"
    Then Verify the passage and edit
    When Click the button "Save Passage & Continue"
    And Enter comprehension based subjective question
    And Expand the dropdown "Has Word Limit?"
    And Select "Yes" from dropdown
    And Enter word limit "10"
    And Click the button "Save & Exit"
    Then Verify the passage is created
    And Expand the passage field
    Then Verify the comprehension question is created "<question1>", "<question2>"
    Examples:
      |module |submodule|level  |type                       |section  |difficulty|skills|marks|question1  |question2|
      |Tests  |Questions|Basic  |Subjective answer questions|Logical  |Hard      |Java  |10   |           |         |

  @questionModule
  Scenario Outline: View Comprehensions
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Comprehensions"
    And Click actions icon of recently created passage
    And Select "View Comprehensions" from actions dropdown
    And Verify comprehension dialog box displays
    Then Verify passage on view
    Examples:
      |module |submodule|
      |Tests  |Questions|

  @questionModule
  Scenario Outline: Edit passage
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Comprehensions"
    And Click actions icon of recently created passage
    And Select "Edit Passage" from actions dropdown
    And Update passage
    And Click the button "Update"
    Then Verify passage gets updated
    Examples:
      |module |submodule|
      |Tests  |Questions|

    Scenario Outline: Edit comprehension based question and verify
      Given Select "<module>", "<submodule>" from sidebar
      When Switch to "Comprehensions"
      And Expand the passage field
      And Get comprehension based question statement
      And Edit comprehension based question
      Then Verify confirmation dialog box appears
      When Click the button "Yes"
      And Update comprehension based question
      And Enter options and select a correct option
      And Click the button "Save & Exit"
      And Expand the passage field
      Then Verify the updated comprehension question
      Examples:
        |module |submodule|
        |Tests  |Questions|

#  Scenario Outline: Add Question in passage  - bug encountered
#    Given Select "<module>", "<submodule>" from sidebar
#    When Switch to "Comprehensions"
#    And Click actions icon of recently created passage
#    And Select "Add Question" from actions dropdown
#    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>"
#    And Enter marks "<marks>"
#    And Click the button "Next"
#    And Enter comprehension based subjective question
#    And Expand the dropdown "Has Word Limit?"
#    And Select "Yes" from dropdown
#    And Enter word limit "10"
#    And Click the button "Save & Exit"
#    And Expand the passage field
#    Then Verify a question is added
#    Examples:
#      |module |submodule|
#      |Tests  |Questions|

  @questionModule
  Scenario Outline: Delete passage
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Comprehensions"
    Then Verify the passage is created
    And Click actions icon of recently created passage
    And Select "Delete Passage" from actions dropdown
    Then Verify confirmation dialog box appears
    When Click the button "Yes"
    Then Verify the popup message "associated with other questions"
    And Search a passage
    Then Verify the passage state "<state>"
    Examples:
      |module |submodule|state      |
      |Tests  |Questions|not deleted|

  @questionModule
  Scenario Outline: Delete passage after deleting all associated questions
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Comprehensions"
    And Expand the passage field
    And Delete all the questions associated
    And Click actions icon of recently created passage
    And Select "Delete Passage" from actions dropdown
    Then Verify confirmation dialog box appears
    When Click the button "Yes"
    Then Verify the popup message "<message>"
    And Search a passage
    Then Verify the passage state "<state>"
    Examples:
      |module |submodule|message             |state  |
      |Tests  |Questions|deleted successfully|deleted|

  @questionModule
  Scenario Outline: Create video based MCQ and checkbox question and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Video Based"
    And Click Add New in video tab
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>"
    And Enter marks "<marks>"
    And Click the button "Next"
    Then Verify upload movie clip dialog box displays
    When Enter movie name and description
    And Upload a video "<movieLocation>"
    And Click the upload button
    Then Verify video is uploaded
    When Enter question description "<question1>"
    And Enter options and select a correct option
    And Click the button "Save & Add More"
    And Enter question description "<question2>"
    And Enter options and select a correct option
    And Click the button "Save & Exit"
    And Expand the video field
    Then Verify the video name and description
    Then Verify the video based question is created "<question1>","<question2>"
    Examples:
      |module |submodule|level  |type                    |section  |difficulty|skills|marks|movieLocation                                  |question1|question2|
      |Tests  |Questions|Basic  |Multiple choice question|Logical  |Hard      |Java  |10   |C:\Users\saloni.nagpal\Downloads\kids-26796.mp4|Q1       |Q2       |
      |Tests  |Questions|Basic  |Checkbox question       |Logical  |Hard      |Java  |10   |C:\Users\saloni.nagpal\Downloads\kids-26796.mp4|Q1       |Q2       |

  @questionModule
  Scenario Outline: Edit video
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Video Based"
    And Click actions icon of recently created video
    And Select "Edit" from actions dropdown
    Then Verify upload movie clip dialog box displays
    When Enter movie name and description
    And Upload a video "<movieLocation>"
    And Click the upload button
    Then Verify the video name and description
    Examples:
      |module |submodule|movieLocation|
      |Tests  |Questions|C:\Users\saloni.nagpal\Downloads\metapreview.mp4|

  @questionModule
  Scenario Outline: View Video
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Video Based"
    And Click actions icon of recently created video
    And Select "View" from actions dropdown
    And Verify video dialog box displays
    Then Verify movie name and description on view
    Examples:
      |module |submodule|
      |Tests  |Questions|

    @inProgress
  Scenario Outline: Edit video based question and verify - inProgress
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Video Based"
    And Expand the video field
    And Get comprehension based question statement
    And Edit comprehension based question
    Then Verify confirmation dialog box appears
    When Click the button "Yes"
    And Update comprehension based question
    And Enter options and select a correct option
    And Click the button "Save & Exit"
    And Expand the passage field
    Then Verify the updated comprehension question
    Examples:
      |module |submodule|
      |Tests  |Questions|

#     @inProgress
#  Scenario: Add a question in video - bug encountered
#    Given Select "<module>", "<submodule>" from sidebar
#    When Switch to "Video Based"
#    And Click actions icon of recently created video
#    And Select "Add" from actions dropdown

  @questionModule
  Scenario Outline: Delete Video
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Video Based"
    Then Verify the video name and description
    And Click actions icon of recently created video
    And Select "Delete" from actions dropdown
    And Click the button "Yes"
    Then Verify the popup message "associated with other questions"
    And Search a video
    Then Verify the state of video "<state>"
    Examples:
      |module |submodule|state      |
      |Tests  |Questions|not deleted|

  @questionModule
  Scenario Outline: Delete video after deleting all associated questions
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Comprehensions"
    And Expand the video field
    And Delete all the questions associated to video
    And Click actions icon of recently created video
    And Select "Delete" from actions dropdown
    Then Verify confirmation dialog box appears
    When Click the button "Yes"
    Then Verify the popup message "<message>"
    And Search a video
    Then Verify the passage state "<state>"
    Examples:
      |module |submodule|message             |state  |
      |Tests  |Questions|deleted successfully|deleted|












    













