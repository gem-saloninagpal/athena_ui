Feature:Leaderboard feature

  Background:Check login to candidate module
    Given Navigate to login page
    Then Login using "pratik.thorat@geminisolutions.com" and "abcd@123"

  Scenario: Verify leaderboard is being displayed
    Given User is on Courses page
    When user clicks on arrow icon
    Then verify leaderboard is being displayed

  Scenario: Verify expand and collapse of leaderboard
    Given User is on Courses page
    When user clicks on arrow icon
    Then verify leaderboard is being displayed
    When user clicks on arrow icon
    Then verify leaderboard is collapsed

  Scenario: Verify ten entries are being displayed
    Given User is on Courses page
    When user clicks on arrow icon
    Then verify ten entries are displayed

  Scenario: Verify top entry has highest score
    Given User is on Courses page
    When user clicks on arrow icon
    Then verify top user has highest score