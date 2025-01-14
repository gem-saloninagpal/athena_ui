Feature: Wishlist Feature

  Background:
    And Navigate to login page
    And Login using "check123@gmail.com" and "check@123"

  Scenario: Add course to wishlist
    Given user is in course catalog
    When user clicks on Add to Wishlist button
    And user opens wishlist
    Then verify course is added to wishlist

  Scenario: Remove course from wishlist and add again
    Given user is in course catalog
    When user opens wishlist
    And user clicks on Remove course button
    And user clicks on back button
    And user clicks on Add to Wishlist button
    And user opens wishlist
    Then verify course is added to wishlist

  Scenario: Remove course from wishlist
    Given user is in course catalog
    When user opens wishlist
    And user clicks on Remove course button
    Then verify course is removed from wishlist

  Scenario: Enroll for course
    Given user is in course catalog
    When user opens wishlist
    And user clicks on enroll button
    Then verify user is enrolled in course