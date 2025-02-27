package com.qa.athenaUi.stepdefinitions;

import com.gemini.gemjar.enums.Status;
import com.gemini.gemjar.reporting.GemTestReporter;
import com.gemini.gemjar.utils.ui.DriverAction;
import com.qa.athenaUi.locators.SendCustomMail_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.qa.athenaUi.locators.Wishlist_Locators;

public class Wishlist {
    public String courseName;
    @Given("user is in course catalog")
    public void userIsInCourseCatalog() {
        try {
            DriverAction.waitUntilElementIsClickable(Wishlist_Locators.courseCatalog);
            DriverAction.click(Wishlist_Locators.courseCatalog);
        } catch (Exception e) {
            GemTestReporter.addTestStep("catalog not opened", "User is not on catalog. Exception: " + e, Status.ERR);
        }
    }

    @When("user clicks on Add to Wishlist button")
    public void userClicksOnAddToWishlistButton() {
        try {
            DriverAction.waitSec(3);
            DriverAction.waitUntilElementAppear(Wishlist_Locators.searchBar, 120);
            DriverAction.typeText(Wishlist_Locators.searchBar, "wishlist_regression");
            DriverAction.waitSec(2);
            DriverAction.waitUntilElementIsClickable(Wishlist_Locators.addToWishlist);
            DriverAction.click(Wishlist_Locators.addToWishlist);
            DriverAction.waitSec(10);
            if(DriverAction.isDisplayed(Wishlist_Locators.addedToWishlist)) {
                GemTestReporter.addTestStep("Added to Wishlist", "Course successfully added to wishlist", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Not added to Wishlist", "Course not added to wishlist", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Course not added", "Some error occurred. Exception: " + e, Status.ERR);
        }
    }

    @And("user opens wishlist")
    public void userOpensWishlist() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 200);
            DriverAction.waitUntilElementIsClickable(Wishlist_Locators.wishlist);
            DriverAction.click(Wishlist_Locators.wishlist);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Wishlist not opened", "Wishlist not opened. Exception: " + e, Status.ERR);
        }
    }

    @Then("verify course is added to wishlist")
    public void verifyCourseIsAddedToWishlist() {
        try {
            DriverAction.waitUntilElementDisappear(SendCustomMail_Locators.loader, 150);
            if(DriverAction.isDisplayed(Wishlist_Locators.wishlist_course)) {
                GemTestReporter.addTestStep("Course in wishlist", "Course is added to wishlist", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Course not in wishlist", "Course is not added to wishlist", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Course not in wishlist", "Course is not added to wishlist. Exception: " + e, Status.ERR);
        }
    }

    @And("user clicks on Remove course button")
    public void userClicksOnRemoveCourseButton() {
        try {
            DriverAction.waitSec(3);
            DriverAction.waitUntilElementAppear(Wishlist_Locators.searchBar, 120);
            DriverAction.typeText(Wishlist_Locators.searchBar, "wishlist_regression");
            DriverAction.waitSec(5);
            DriverAction.waitUntilElementIsClickable(Wishlist_Locators.removeCourse);
            DriverAction.click(Wishlist_Locators.removeCourse);
            DriverAction.waitSec(2);
            DriverAction.waitUntilElementIsClickable(Wishlist_Locators.yesOption);
            DriverAction.click(Wishlist_Locators.yesOption);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Course not removed", "Course not removed from wishlist. Exception: " + e, Status.ERR);
        }
    }

    @Then("verify course is removed from wishlist")
    public void verifyCourseIsRemovedFromWishlist() {
        try {
            DriverAction.waitSec(8);
            if(DriverAction.isDisplayed(Wishlist_Locators.noRecords)) {
                GemTestReporter.addTestStep("Course removed", "Course removed from wishlist", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Course not removed", "Course not removed from wishlist", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Course not removed", "Course not removed from wishlist. Exception: " + e, Status.ERR);
        }
    }

    @And("user clicks on back button")
    public void userClicksOnBackButton() {
        DriverAction.waitSec(5);
        DriverAction.waitUntilElementIsClickable(Wishlist_Locators.backBtn);
        DriverAction.click(Wishlist_Locators.backBtn);
    }

    @And("user clicks on enroll button")
    public void userClicksOnEnrollButton() {
        try {
            DriverAction.waitSec(3);
            courseName = DriverAction.getElementText(Wishlist_Locators.enrolledCourse);
            DriverAction.click(Wishlist_Locators.enrollBtn);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Enroll not clicked", "Enroll button was not clicked. Exception: " + e, Status.ERR);
        }
    }

    @Then("verify user is enrolled in course")
    public void verifyUserIsEnrolledInCourse() {
        try {
            DriverAction.waitSec(15);
            DriverAction.waitUntilElementAppear(Wishlist_Locators.searchBar, 120);
            DriverAction.typeText(Wishlist_Locators.searchBar, courseName);
            DriverAction.waitSec(5);
            if(DriverAction.isDisplayed(Wishlist_Locators.noRecords)) {
                GemTestReporter.addTestStep("Course enrolled", "User has enrolled", Status.PASS);
            } else {
                GemTestReporter.addTestStep("Course not enrolled", "User has not enrolled", Status.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Course not enrolled", "Course was not enrolled. Exception: " + e, Status.ERR);
        }
    }
}
