Feature: Send Custom Mail Feature

  Background:
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  Scenario Outline: Send new email
    Given Select "<module>", "<submodule>" from sidebar
    When user selects "<templateName>" and "<recipient>"
    And user clicks on send button
    Then verify mail is sent

    Examples:
      | module              | submodule        | templateName | recipient     |
      | Mail Configurations | Send Custom Mail | new_template | pallavi.arora |

  Scenario Outline: Check mandatory fields
    Given Select "<module>", "<submodule>" from sidebar
    When user does not select "<templateName>" and "<recipient>"
    And verify "<error>" appears

    Examples:
      | module              | submodule        | error                  |
      | Mail Configurations | Send Custom Mail | This field is required |

  Scenario Outline: Check reset functionality
    Given Select "<module>", "<submodule>" from sidebar
    When user selects "<templateName>" and "<recipient>"
    And user clicks on reset button
    Then verify details are reset

    Examples:
      | module              | submodule        | templateName | recipient     |
      | Mail Configurations | Send Custom Mail | new_template | pallavi.arora |

  Scenario Outline: Check view past mails feature
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on view past mails button
    Then verify mailing history page is displayed

    Examples:
      | module              | submodule        |
      | Mail Configurations | Send Custom Mail |

  Scenario Outline: Check preview functionality
    Given Select "<module>", "<submodule>" from sidebar
    When user selects "<templateName>" and "<recipient>"
    And user clicks on preview button
    Then verify mail is sent

    Examples:
      | module              | submodule        | templateName | recipient     |
      | Mail Configurations | Send Custom Mail | new_template | pallavi.arora |

  Scenario Outline: Add multiple recipients
    Given Select "<module>", "<submodule>" from sidebar
    When user selects "<templateName>" and "<recipient>", "<recipient1>"
    And user clicks on preview button
    Then verify mail is sent

    Examples:
      | module              | submodule        | templateName | recipient     | recipient1  |
      | Mail Configurations | Send Custom Mail | new_template | pallavi.arora | parul.sahni |
