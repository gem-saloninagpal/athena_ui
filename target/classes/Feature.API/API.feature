Feature:API testing

  Background:
    Given Set authenticate "authenticate_url" method "post" and SampleName "authenticate_sampleJson"

#  Scenario Outline: Get User
#    Given Set endpoint "<Endpoint>" and Method "<Method>"
#    Then Verify Status code <StatusCode>
#    Examples:
#      | Endpoint    | Method|StatusCode|
#      | getAllUsers | get   |200       |
#
  Scenario Outline: Save Users
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
    |Endpoint|Method|SampleName|StatusCode|
    |saveUsers|post |saveUsers |201       |
#
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

  Scenario Outline: Get question types
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint        |Method|StatusCode|
      |getQuestionTypes|get   |200       |

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

#  Scenario Outline: Forgot password
#    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
#    Then Verify Status code <StatusCode>
#    Examples:
#      |Endpoint      |Method |StatusCode|SampleName    |
#      |forgotPassword|post   |201       |forgotPassword|

  Scenario Outline: Add test
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |addTest  |post   |200       |addTest    |

  Scenario Outline: Get test master
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint     |Method|StatusCode|
      |getTestMaster|get   |200       |

  Scenario Outline: Get test by id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint   |Method|StatusCode|
      |getTestById|get   |200       |

  Scenario Outline: Add candidate test
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint        |Method |StatusCode|SampleName      |
      |addCandidateTest|post   |200       |addCandidateTest|

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

  Scenario Outline: Get all tests assigned to a user
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint   |Method|StatusCode|
      |getAllTests|get   |200      |

  Scenario Outline: Get all users paginated
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint            |Method|StatusCode|
      |getAllUsersPaginated|get   |200      |

  Scenario Outline: Get user by id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getUserById|get   |200      |

  Scenario Outline: Add new section
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |addSection  |post   |200       |addSection |

  Scenario Outline: Get sections
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getSection|get   |200      |

  Scenario Outline: Get active question count
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getActiveQuestionCount|get   |200      |

  Scenario Outline: Get all candidates by test id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllCandidatesByTestId|get   |200 |

  Scenario Outline: Remove candidate from test
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |removeCandidateFromTest|delete   |200 |

  Scenario Outline: Get all paginated tests
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |paginatedGetTest|get   |200 |

  Scenario Outline: Get user
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getUser|get   |200 |

  Scenario Outline: Get active test
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getActiveTest|get   |200 |

  Scenario Outline: Delete question
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |deleteQuestion|delete   |200 |

  Scenario Outline: Get test associated question
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getTestAssociatedQuestion|get   |200 |

  Scenario Outline: Delete option
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |deleteOption|delete   |200 |

  Scenario Outline: Remove question from test
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |removeQuestionFromTest|delete   |200 |

  Scenario Outline: Delete section type
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |deleteSectionType|delete   |200 |

  Scenario Outline: Get test associated sections
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getTestAssociatedSections|get   |200 |

  Scenario Outline: Get all campus
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllCampus|get   |200 |

  Scenario Outline: Get all active campus
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllActiveCampus|get   |200 |

  Scenario Outline: Get role
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getRole|get   |200 |

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

  Scenario Outline: Get permission by code
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getPermissionByCode|get   |200 |

  Scenario Outline: Get campus by id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getCampusById|get   |200 |

  Scenario Outline: Get campus reports by campus id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getCampusReportsByCampusId|get   |200 |

  Scenario Outline: Get comprehension by id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getComprehensionById|get   |200 |

  Scenario Outline: Get all comprehensions
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllComprehensions|get   |200 |

  Scenario Outline: Get all comprehension assoc questions
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllComprehensionAssocQuestions|get   |200 |

  Scenario Outline: Get all movie clips
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllMovieClips|get   |200 |

  Scenario Outline: Get all movie assoc questions
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllMovieAssocQuestions|get   |200 |

  Scenario Outline: Get movie by id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getMovieById|get   |200 |

  Scenario Outline: Get movie by name
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getMovieByName|get   |200 |

  Scenario Outline: Get movie assoc questions by id
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getMovieAssocQuestionsById|get   |200 |

  Scenario Outline: Get all active test
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getAllActiveTest|get   |200 |

  Scenario Outline: Delete movie
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |deleteMovie|delete   |200 |

  Scenario Outline: Get campus performance report
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getCampusPerformanceReport|get   |200 |

#  Scenario Outline: Get all paginated candidates
#    Given Set endpoint "<Endpoint>" and Method "<Method>"
#    Then Verify Status code <StatusCode>
#    Examples:
#      |Endpoint|Method|StatusCode|
#      |getAllPaginatedCandidates|get   |200 |

  Scenario Outline: Get random questions
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getRandomQuestions|get   |200 |

  Scenario Outline: Add new questions
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |addQuestions  |post   |200       |saveQuestionMaster |

  Scenario Outline: Add candidate answer
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |addCandidateAnswer  |post   |200       |addCandidateAnswer |

  Scenario Outline: Save campus details
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |saveCampusDetails  |post   |200       |saveCampusDetails |

  Scenario Outline: Add role
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |addRole  |post   |200       |addRole |

  Scenario Outline: Update user password manually
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |updateUserPasswordManually  |post   |200       |updatePasswordManually |

  Scenario Outline: Save comprehension details
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |saveComprehensionDetails  |post   |200       |saveComprehensionDetails |

  Scenario Outline: Reattempt Test
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |reattemptTest  |post   |200       |reattemptTest |

  Scenario Outline: Send email
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |sendEmail  |post   |200       |sendEmail |

  Scenario Outline: Get All Paginated Questions
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |getAllPaginatedQuestions  |post   |200       |getAllPaginatedQuestions |

  Scenario Outline: Get All Paginated Questions
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |addMovieQuestion  |post   |200       |addMovieQuestion |

  Scenario Outline: Assign all users to test
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |assignAllUsersToTest  |post   |200       |assignAllUsersToTest |

  Scenario Outline: Remove movie question
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |removeMovieQuestionAssoc  |put   |200       |removeMovieQuestionAssoc |

  Scenario Outline: Remove comprehension question
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |removeComprehensionQuestionAssoc  |put   |200       |removeComprehensionQuestionAssoc |

  Scenario Outline: Update test status
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |updateTestStatus|put   |200 |

  Scenario Outline: Update user
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |updateUser  |put   |200       |updateUser |

  Scenario Outline: Get test info by tag
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getTestInfoByTag|get   |200 |

  Scenario Outline: Get Active Test And Question Count
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getActiveTestAndQuestionCount|get   |200 |

  Scenario Outline: Clear answer
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |clearAnswer  |post   |200       |clearAnswer |

  Scenario Outline: Update marks
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |updateMarks  |post   |200       |updateMarks |

  Scenario Outline: Update password
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |updatePassword  |put   |200       |updatePassword |

  Scenario Outline: Update Question
    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint |Method |StatusCode|SampleName |
      |updateQuestion  |put   |200       |updateQuestion |

  Scenario Outline: Get Comprehension Assoc Questions
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |getComprehensionAssocQuestions|get   |200 |

  Scenario Outline: Assign random evaluators
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint|Method|StatusCode|
      |assignRandomEvaluators|post   |200 |