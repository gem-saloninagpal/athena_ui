Feature: User Login Api


  Scenario Outline: Login with valid credentials
    Given Set endpoint and method "<endpoint>" and "<method>"
    Then Verify Status code "<expectedStatus>"
#    When Click on sign In
#    When User enters the "username" in UI
#    When User enters the "password" in UI
#    And User logged in
#    Then Verify data from the Api for Other Portals with UI having "<fieldName>" and "<fieldDesc>"
    Examples:
      | endpoint      | method | expectedStatus | fieldName | fieldDesc |
      | getUser       | Get    | 200            | toolName  | toolLink  |



    Examples: