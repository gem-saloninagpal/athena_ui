Feature:API HealthCheck

  Background:
    Given Set authenticate "authenticate_url" method "post" and SampleName "authenticate_sampleJson"

  Scenario Outline: Get User
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      | Endpoint    | Method|StatusCode|
      | getAllUsers | get   |200       |

  Scenario Outline: Find by username
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |findUser|get   |200       |

  Scenario Outline: Find all active users
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint   |Method|StatusCode|
      |findAllUser|get   |200       |

  Scenario Outline: Get all master questions
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint       |Method|StatusCode|
      |getAllQuestions|get   |200       |

  Scenario Outline: Get question by id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint       |Method|StatusCode|
      |getQuestionById|get   |200       |

  Scenario Outline: Get test master
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint     |Method|StatusCode|
      |getTestMaster|get   |200       |

   Scenario Outline: Get random questions
     Given Set endpoint "<Endpoint>" and Method "<Method>"
     Then Verify Status code <StatusCode>
     Examples:
       |Endpoint          |Method|StatusCode|
       |getRandomQuestions|get   |200       |

   Scenario Outline: Paginated get test
     Given Set endpoint "<Endpoint>" and Method "<Method>"
     Then Verify Status code <StatusCode>
     Examples:
       |Endpoint         |Method|StatusCode|
       |paginatedGetTest |get   |200       |

  Scenario Outline: Get all active candidate user
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint             |Method|StatusCode|
      |getAllActiveCandidate|get   |200       |

  Scenario Outline: Get candidate answer
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint          |Method|StatusCode|
      |getCandidateAnswer|get   |200       |

  Scenario Outline: Generate report
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint      |Method|StatusCode|
      |generateReport|post   |200      |

    Scenario Outline: Get all candidates by test id
      Given Set endpoint "<Endpoint>" and Method "<Method>"
      Then Verify Status code <StatusCode>
      Examples:
        |Endpoint                |Method|StatusCode|
        |getAllCandidatesByTestId|get   |200       |

    Scenario Outline: Get active test and question count
      Given Set endpoint "<Endpoint>" and Method "<Method>"
      Then Verify Status code <StatusCode>
      Examples:
        |Endpoint                     |Method|StatusCode|
        |getActiveTestAndQuestionCount|get   |200       |

      Scenario Outline: Remove candidate from test
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Then Verify response message "<message>"
        Examples:
          |Endpoint               |Method|StatusCode|message                                   |
          |removeCandidateFromTest|delete|200       |Candidate has already attempted the test.|

      Scenario Outline: Clear answer
        Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint    |Method |StatusCode|SampleName |
          |clearAnswer |post   |200       |clearAnswer|

      Scenario Outline: Get all users paginated
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint            |Method|StatusCode|
          |getAllUsersPaginated|get   |200      |

      Scenario Outline: Get sections
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |getSection|get   |200      |


      Scenario Outline: Get active test
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |getActiveTest|get   |200 |


      Scenario Outline: Get all active campus
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |getAllActiveCampus|get   |200 |

  Scenario Outline: Get all movie clips
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllMovieClips|get   |200 |

      Scenario Outline: Get all comprehensions
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |getAllComprehensions|get   |200 |

      Scenario Outline: Get all comprehensions
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |getAllComprehensions|get   |200 |

      Scenario Outline: Assign all users to test
        Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint |Method |StatusCode|SampleName |
          |assignAllUsersToTest  |post   |200       |assignAllUsersToTest |

      Scenario Outline: Update date of all courses for user
        Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint |Method |StatusCode|SampleName |
          |updateDateOfAllCoursesForUser  |put   |200       |updateDateOfAllCoursesForUser |

      Scenario Outline: Update date of selected courses for user
        Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint |Method |StatusCode|SampleName |
          |updateDateOfSelectedCoursesForUser  |put   |200       |updateDateOfSelectedCoursesForUser |

      Scenario Outline: Get all course details
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |getAllCourseDetails|get   |200 |

      Scenario Outline: Update date of selected courses for user
        Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint |Method |StatusCode|SampleName |
          |getAllCompletedCoursesCandidatesView  |post   |200       |getAllCompletedCoursesCandidatesView |

      Scenario Outline: Get ongoing course data
        Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint |Method |StatusCode|SampleName |
          |getOngoingCourseData  |post   |200       |getOngoingCourseData |

      Scenario Outline: Get all top scorers - leaderboard
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |getAllTopScorers|get   |200 |

      Scenario Outline: Send monthly compliance reports
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |sendMonthlyComplianceCourseReports|get   |200 |

      Scenario Outline: Send quarterly compliance reports
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |sendQuarterlyComplianceCourseReports|get   |200 |

      Scenario Outline: Send yearly compliance reports
        Given Set endpoint "<Endpoint>" and Method "<Method>"
        Then Verify Status code <StatusCode>
        Examples:
          |Endpoint|Method|StatusCode|
          |sendYearlyComplianceCourseReports|get   |200 |

  Scenario Outline: Get Contripoint Token
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getContripointToken |post   |200 |

  Scenario Outline: Get All Excluded Users
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllExcludedUsers |get   |200 |

  Scenario Outline: Add new section
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |addSection  |post   |200       |addSection |

  Scenario Outline: Get all paginated tests
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |paginatedGetTest|get   |200 |

  Scenario Outline: Get roles
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getRoles|get   |200 |

  Scenario Outline: Get all permissions
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllPermissions|get   |200 |

  Scenario Outline: Get campus performance report
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getCampusPerformanceReport|get   |200 |

  Scenario Outline: Get certified learners by category
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getCertifiedLearnersByCourse|get   |200 |
