Feature: Test Control Feature


  Scenario Outline: Test Control->Validate Active Campus
    Given Set Test Control endpoint "<endpoint>" and method "<method>"
    Then Verify Status code "<expectedStatus>"
    When Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"
    And Select "<module>", "<submodule>" from sidebar
    Then get the Campus details and validate with api

    Examples:
      | endpoint                 | method | expectedStatus |module  |submodule  |
      | getAllActiveCampus       | Get    | 200            |Tests |Test Control |

  Scenario Outline: Test Control->Validate Paginated Active Test
    Given Set Test Control endpoint "<endpoint>" and method "<method>"
    Then Verify Status code "<expectedStatus>"
    When Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"
    And Select "<module>", "<submodule>" from sidebar
    Then get the Paginated Active test from Ui and Validate with Api

    Examples:
      | endpoint                 | method | expectedStatus |module  |submodule  |
      | getAllPaginatedTests      | Get    | 200            |Tests |Test Control |



  Scenario Outline: Test Control->Validate Active Test
    Given Set Test Control endpoint "<endpoint>" and method "<method>"
    Then Verify Status code "<expectedStatus>"
    When Navigate to page "login"
    Then Login using "rahul23@gmail.com" and "abc@123"
    And Select "<module>", "<submodule>" from sidebar
    Then get the Active test from Ui and validate with Api

    Examples:
      | endpoint                 | method | expectedStatus |module  |submodule  |
      | getActiveTest      | Get    | 200            |Tests |Test Control |


  Scenario Outline: Test Control->Validate All Campus
    Given Set Test Control endpoint "<endpoint>" and method "<method>"
    Then Verify Status code "<expectedStatus>"


    Examples:
      | endpoint                 | method | expectedStatus |
      | getAllCampus     | Get    | 200            |


  Scenario Outline: Test Control->Post Reattempt User
    Given Set the PostInteraction "<Endpoint>", "<Method>", "<paramKey>" and "<paramValue>" for posting data
    Then Verify expected "<Status Code>" of PostInteraction API with actual Status Code
    And Verify expected "<Response Message>" of PostInteraction API matches actual Response Message
    When Navigate to page "login"
    Then Login using "rahul22@gmail.com" and "abc@123"
    And Validate that Test is assign again

    Examples:
      | Endpoint    | Method | Status Code | Response Message | paramKey                         | paramValue      | commentLikeFlag | like   |
      | reattemptTest | post   | 200       | OK          | testId,userId,candidateTestId | 345,1290,25255 | 1               | Like   |






