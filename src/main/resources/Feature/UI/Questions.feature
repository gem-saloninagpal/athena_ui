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
#      |Tests  |Questions|Basic|Checkbox question       |Logical|Hard      |Java  |10   |ques-1!  |richTextQuestion  |Rich Text |


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
#      |Tests  |Questions|Intermediate|Checkbox question       |null     |Hard      |Java  |10   |question-1|question-2|Plain Text|

  @questionModule
  Scenario Outline: View question
    Given Select "<module>", "<submodule>" from sidebar
    When Click Actions icon of recently created question
    And Select "View" from actions dropdown
    Then Verify question dialog box opens
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
    Then Verify the popup message "Question deleted successfully!"
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
    And Select Yes from dropdown
    And Enter word limit "10"
    And Click the button "Save & Add More"
    And Enter question description in subjective
    And Expand the dropdown "Has Word Limit?"
    And Select No from dropdown
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
    And Select Yes from dropdown
    And Enter word limit "10"
    And Click the button "Save & Exit"
    And Click actions icon of recently created passage
    And Select View Comprehensions from actions dropdown
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
