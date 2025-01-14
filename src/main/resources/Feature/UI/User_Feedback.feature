Feature: User Feedback Feature

  Background:
    And Navigate to login page
    And Login using "pallavi.arora@geminisolutions.com" and "abcd@123"

  Scenario Outline: User shares feedback
    Given user clicks on feedback icon
    When User enters feedback details "<subject>", "<description>"
    And Click on submit button
    Then verify feedback is submitted

  Examples:
    | subject          | description           |
    | feedback testing | testing user feedback |

  Scenario Outline: Verify mandatory fields
    Given user clicks on feedback icon
    When User enters feedback details "<subject>", "<description>"
    Then verify submit button remains disabled

    Examples:
      | subject          | description           |
      |                  | testing user feedback |
      | feedback testing |                       |
      |                  |                       |

  Scenario Outline: Share feedback with screenshot
    Given user clicks on feedback icon
    When User enters feedback details "<subject>", "<description>"
    And User uploads screenshot "<path>"
    And Click on submit button
    Then verify feedback is submitted

    Examples:
      | subject          | description           | path                                        |
      | feedback testing | testing user feedback | C:\Users\Pallavi.Arora\Downloads\athena.png |