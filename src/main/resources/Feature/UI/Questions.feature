Feature: Questions functionality

  Background:
    Given Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"


  @regression @questionModule
  Scenario Outline: Create and preview question
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Add New"
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>", "<text>"
    And Enter marks "<marks>"
    And Click the button "Next"
    And Enter question description "<question1>"
    And Enter options and select a correct option
    And Click Preview button
    Then Verify question dialog box opens
    Then Verify the question in dialog box "<question1>"
    And Close the dialog box
    And Click the button "Save & Add More"
    And Enter question description "<question2>"
    And Enter options and select a correct option
    And Click the button "Save & Exit"
#    Then Verify the question is created "<question1>", "<question2>"

    Examples:
      |module |submodule|level|type                    |section|difficulty|skills|marks|question1|question2|text      |
      |Tests  |Questions|Basic|Multiple choice question|Logical|Hard      |Java  |10   |ques-1?  |plainTextQuestion  |Plain Text|
      |Tests  |Questions|Basic|Checkbox question       |Logical|Hard      |Java  |10   |ques-1!  |richTextQuestion  |Rich Text |


  @questionModule
  Scenario Outline: Edit a question and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Click Actions icon of recently created question
    And Select "Edit" from actions dropdown
    And Click the edit icon
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>", "<text>"
    And Click the button "Next"
    When Enter question description "<question1>"
    And Enter options and select a correct option
    And Click Preview button
    Then Verify question dialog box opens
    And Close the dialog box
    And Click the button "Update & Add More"
    And Enter question description "<question2>"
    And Enter options and select a correct option
    And Click the button "Save & Exit"
#    Then Verify the question is created "<question1>", "<question2>"
    Examples:
      |module |submodule|level       |type                    |section  |difficulty|skills|marks|question1 |question2 |text|
      |Tests  |Questions|Intermediate|Multiple choice question|null     |Easy      |Java  |10   |Question-1|Question-2|Rich Text|
      |Tests  |Questions|Intermediate|Checkbox question       |null     |Hard      |Java  |10   |question-1|question-2|Plain Text|

  @questionModule
  Scenario Outline: View question
    Given Select "<module>", "<submodule>" from sidebar
    When Click Actions icon of recently created question
    And Select "View" from actions dropdown
    And Verify question dialog box opens
#    Then Verify question on view "<question>"
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
#    Then Verify the popup message "Question deleted successfully!"
    Examples:
      |module |submodule|
      |Tests  |Questions|

  @questionModule
  Scenario Outline:Create subjective question
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Add New"
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>", "<text>"
    And Enter marks "<marks>"
    And Click the button "Next"
    And Enter question description in subjective
    And Expand the dropdown "Has Word Limit?"
    And Select "Yes" from dropdown
    And Enter word limit "10"
    And Click the button "Save & Add More"
    And Enter question description in subjective
    And Expand the dropdown "Has Word Limit?"
    And Select "No" from dropdown
    And Click the button "Save & Exit"
#    Then Verify the subjective questions
    Examples:
      |module |submodule|level|type                       |section|difficulty|skills|marks|text|
      |Tests  |Questions|Basic|Subjective answer questions|Logical|Hard      |Java  |10   |Plain Text|

  @questionModule @inProgress
  Scenario Outline:Create comprehension based subjective question and verify view comprehension
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Comprehensions"
    And Click Add New in comprehensions tab
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>", "<text>"
    And Enter marks "<marks>"
    And Click the button "Next"
    Then Click the button "Save Passage & Continue" and verify the message "Passage cannot be empty"
    When Enter the passage
    And Click the button "Save Passage & Continue"
    And Enter comprehension based subjective question
    And Expand the dropdown "Has Word Limit?"
    And Select "Yes" from dropdown
    And Enter word limit "10"
    And Click the plus icon
    And Click the button "Save & Add More"
    And Click the button "Edit Passage"
#    Then Verify the passage and edit
    When Click the button "Save Passage & Continue"
    And Enter comprehension based subjective question
    And Expand the dropdown "Has Word Limit?"
    And Select "Yes" from dropdown
    And Enter word limit "10"
    And Click the plus icon
    And Click the button "Save & Exit"
#    Then Verify the passage is created
    And Expand the passage field
#    Then Verify the comprehension question is created "<question1>", "<question2>"
    And Click actions icon of recently created passage
    And Select "View Comprehensions" from actions dropdown
    Then Verify comprehension dialog box displays
#    Then Verify passage on view
    Examples:
      |module |submodule|level  |type                       |section  |difficulty|skills|marks|question1  |question2|text|
      |Tests  |Questions|Basic  |Subjective answer questions|Logical  |Hard      |Java  |10   |           |         |Rich Text|

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

  @questionModule
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
#    Then Verify the updated comprehension question
    Examples:
      |module |submodule|
      |Tests  |Questions|

  @questionModule
  Scenario Outline: Delete passage
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Comprehensions"
#    Then Verify the passage is created
    And Click actions icon of recently created passage
    And Select "Delete Passage" from actions dropdown
    Then Verify confirmation dialog box appears
    When Click the button "Yes"
#    Then Verify the popup message "associated with other questions"
#    And Search a passage
    Then Verify the passage state "<state>"
    Examples:
      |module |submodule|state      |
      |Tests  |Questions|not deleted|

  @questionModule @fixed
  Scenario Outline: Create video based MCQ and checkbox question and verify
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Video Based"
    And Click Add New in video tab
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>", "<text>"
    And Enter marks "<marks>"
    And Click the button "Next"
    Then Verify upload movie clip dialog box displays
    When Enter movie name and description
    And Upload a video "<movieLocation>"
    And Click the upload button

    Examples:
      |module |submodule|level  |type                    |section  |difficulty|skills|marks|movieLocation                                  |question1|question2|text|
      |Tests  |Questions|Basic  |Multiple choice question|Logical  |Hard      |Java  |10   |C:\Users\Pallavi.Arora\Downloads\sample_vid.mp4|Q1       |Q2       |Plain Text|
      |Tests  |Questions|Basic  |Checkbox question       |Logical  |Hard      |Java  |10   |C:\Users\Pallavi.Arora\Downloads\sample_vid.mp4|Q1       |Q2       |Rich Text |

  @questionModule @fixed
  Scenario Outline: Edit video
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Video Based"
    And Click actions icon of recently created video
    And Select "Edit" from actions dropdown
    Then Verify upload movie clip dialog box displays
    When Enter movie name and description
    And Upload a video "<movieLocation>"
    And Click the button "Update"
    Then Verify the video name and description
    Examples:
      |module |submodule|movieLocation|
      |Tests  |Questions|C:\Users\pallavi.arora\Downloads\metapreview.mp4|

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


  @questionModule
  Scenario Outline: Delete Video
    Given Select "<module>", "<submodule>" from sidebar
    When Switch to "Video Based"
 #   Then Verify the video name and description
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
#    And Select "Delete" from actions dropdown
    And Select Delete from actions dropdown
#    Then Verify confirmation dialog box appears
    When Click the button "Yes"
    Examples:
      |module |submodule|message             |state  |
      |Tests  |Questions|deleted successfully|deleted|

  @questionModule @inProgress
  Scenario Outline:Create coding question
    Given Select "<module>", "<submodule>" from sidebar
    When Click the button "Add New"
    And Select dropdown values in question fields "<level>", "<type>", "<section>", "<difficulty>", "<skills>", "<text>"
    And Enter marks "<marks>"
    And Click the button "Next"
    And Enter subjective question description for coding
    And Expand the dropdown "Select Coding Languages"
    And Select "<language1>" from dropdown
    And Click the button "Save & Add More"
    And Enter subjective question description for coding
    And Expand the dropdown "Select Coding Languages"
    And Select "<language2>" from dropdown
    Then Get the selected languages "<language1>", "<language2>"
    And Click the button "Save & Exit"
#    Then Verify the subjective questions
    Examples:
      |module |submodule|level|type                      |section|difficulty|skills|marks|text      |language1|language2|
      |Tests  |Questions|Basic|Coding Subjective question|Logical|Hard      |Java  |10   |Plain Text|java     |cpp     |