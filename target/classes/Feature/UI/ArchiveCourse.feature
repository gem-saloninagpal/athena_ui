Feature: Archive Course Feature

  Background:
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  Scenario Outline: Archive course
    Given Select "<module>", "<submodule>" from sidebar
    When User selects Archive course option
    And user enters archived course name
    And user clicks on submit button
    Then verify course is archived

    Examples:
      | module         | submodule      |
      | Manage Courses | Course Library |

  Scenario Outline: Check course summary of archived course
    Given Select "<module>", "<submodule>" from sidebar
    When User clicks on archived courses tab
    And user clicks on actions icon
    And user selects course summary option
    Then verify course summary is displayed

    Examples:
      | module         | submodule      |
      | Manage Courses | Course Library |

  Scenario Outline: Check learner reports of archived courses
    Given Select "<module>", "<submodule>" from sidebar
    When User clicks on archived courses tab
    And user clicks on actions icon
    And user selects learner reports option
    Then verify learner reports are displayed

    Examples:
      | module         | submodule      |
      | Manage Courses | Course Library |

  Scenario Outline: Verify that archive courses should have unique names
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on actions icon of course
    And user clicks on archive course and enters name "<archiveCourse>"
    Then verify error appears

    Examples:
      | module         | submodule      | archiveCourse |
      | Manage Courses | Course Library | archive_check |

  Scenario Outline: Verify archive course option appears while editing course
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on actions icon of course
    And user clicks on edit
    Then verify archive course confirmation appears

    Examples:
      | module         | submodule      |
      | Manage Courses | Course Library |

  Scenario Outline: Verify edit screen appears after archiving course
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on actions icon of course
    And user clicks on edit
    And Click the Yes button
    And user enters archived course name
    And user clicks on submit button
    Then verify edit screen appears

    Examples:
      | module         | submodule      |
      | Manage Courses | Course Library |

  Scenario Outline: Verify edit screen appears after clicking no
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on actions icon of course
    And user clicks on edit
    And user clicks on No in warning popup
    Then verify edit screen appears

    Examples:
      | module         | submodule      |
      | Manage Courses | Course Library |