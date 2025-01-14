Feature: Template Library

  Background:
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  Scenario Outline: Create new template
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on Create Template button
    And user enters "<templateName>", "<templateSubject>", "<content>" in respective fields
    And user clicks on Save button
    Then verify "<templateName>" is created

    Examples:
      | module              | submodule        | templateName        | templateSubject              | content                         |
      | Mail Configurations | Template Library | regression_template | mail template for regression | checking mail template creation |

  Scenario Outline: Check errors for required fields
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on Create Template button
    And user enters "<templateName>", "<templateSubject>", "<content>" in respective fields
    And user clicks on Save button
    Then verify "<error>" appears

    Examples:
      | module              | submodule        | templateName         | templateSubject              | content                         | error |
      | Mail Configurations | Template Library |                      | mail template for regression | checking mail template creation | This field is required |
      | Mail Configurations | Template Library | regression_template1 |                              | checking mail template creation | This field is required |
      | Mail Configurations | Template Library | regression_template2 | mail template for regression |                                 | This field is required |

  Scenario Outline: Check reset functionality
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on Create Template button
    And user enters "<templateName>", "<templateSubject>", "<content>" in respective fields
    And user clicks on Reset button
    Then verify fields are cleared

    Examples:
      | module              | submodule        | templateName         | templateSubject              | content                         |
      | Mail Configurations | Template Library | regression_template1 | mail template for regression | checking mail template creation |

  Scenario Outline: Enter template fields greater than max length
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on Create Template button
    And user enters "<templateName>", "<templateSubject>", "<content>" in respective fields
    Then verify "<error>" appears

    Examples:
      | module              | submodule        | templateName        | templateSubject              | content                         | error |
      | Mail Configurations | Template Library | reeeeeeeeggggggggggggrrrrrrrrrreeeeeeeeesssssssssiiiiiiioooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn                    | mail template for regression | checking mail template creation | Length must not be greater than 255 Characters |
      | Mail Configurations | Template Library | regression_template4 | reeeeeeeeggggggggggggrrrrrrrrrreeeeeeeeesssssssssiiiiiiioooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn | checking mail template creation | Length must not be greater than 100 Characters |

  Scenario Outline: Enter details and click back button
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on Create Template button
    And user enters "<templateName>", "<templateSubject>", "<content>" in respective fields
    And user clicks on back button
    Then verify warning popup appears

    Examples:
      | module              | submodule        | templateName        | templateSubject              | content                         |
      | Mail Configurations | Template Library | regression_template2 | mail template for regression | checking mail template creation |

  Scenario Outline: Enter details and click back button > Select No > Verify data remains
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on Create Template button
    And user enters "<templateName>", "<templateSubject>", "<content>" in respective fields
    And user clicks on back button
    And user clicks on No in warning popup
    Then verify data is not cleared

    Examples:
      | module              | submodule        | templateName        | templateSubject              | content                         |
      | Mail Configurations | Template Library | regression_template2 | mail template for regression | checking mail template creation |

  Scenario Outline: Check search functionality
    Given Select "<module>", "<submodule>" from sidebar
    When user enters "<templateName>" in searchbar
    Then verify template "<templateName>" is searched

    Examples:
      | module              | submodule        | templateName        |
      | Mail Configurations | Template Library | regression_template |

  Scenario Outline: Check edit template functionality
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on actions icon of "<templateName>" and selects "<action>"
    Then verify edit template screen is displayed

    Examples:
      | module              | submodule        | templateName        | action  |
      | Mail Configurations | Template Library | regression_template | Edit |

  Scenario Outline: Check preview template functionality
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on actions icon of "<templateName>" and selects "<action>"
    Then verify preview screen is displayed

    Examples:
      | module              | submodule        | templateName        | action  |
      | Mail Configurations | Template Library | regression_template | Preview |

  Scenario Outline: Check delete template functionality
    Given Select "<module>", "<submodule>" from sidebar
    When user clicks on actions icon of "<templateName>" and selects delete
    Then verify template "<templateName>" is deleted

    Examples:
      | module              | submodule        | templateName        |
      | Mail Configurations | Template Library | regression_template |