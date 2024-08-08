Feature:API testing

  Background:
    Given Set authenticate "authenticate_url" method "post" and SampleName "authenticate_sampleJson"

  Scenario Outline: Get User
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      | Endpoint    | Method|StatusCode|
      | getAllUsers | get   |200       |

    @runwithoutbg
#  Scenario Outline: Save Users
#    Given Set endpoint "<Endpoint>" method "<Method>" and Random SampleName "<SampleName>"
#    Then Verify Status code <StatusCode>
#    Examples:
#    |Endpoint|Method|SampleName|StatusCode|
#    |saveUser|post |addCandidateTest |200 |

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

#  Scenario Outline: Get question types
#    Given Set endpoint "<Endpoint>" and Method "<Method>"
#    Then Verify Status code <StatusCode>
#    Examples:
#      |Endpoint        |Method|StatusCode|
#      |getQuestionTypes|get   |200       |

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

    @runwithoutbg
#  Scenario Outline: Forgot password
#    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
#    Then Verify Status code <StatusCode>
#    Examples:
#      |Endpoint      |Method |StatusCode|SampleName    |
#      |forgotPassword|post   |200       |forgotPassword|

#  Scenario Outline: Add test
#    Given Set endpoint "<Endpoint>" method "<Method>" and SampleName "<SampleName>"
#    Then Verify Status code <StatusCode>
#    Examples:
#      |Endpoint |Method |StatusCode|SampleName |
#      |addTest  |post   |200       |addTest    |

  Scenario Outline: Get test master
    Given Set endpoint "<Endpoint>" and Method "<Method>"
    Then Verify Status code <StatusCode>
    Examples:
      |Endpoint     |Method|StatusCode|
      |getTestMaster|get   |200       |

#  Scenario Outline: Get test by id
#    Given Set endpoint "<Endpoint>" and Method "<Method>"
#    Then Verify Status code <StatusCode>
#    Examples:
#      |Endpoint   |Method|StatusCode|
#      |getTestById|get   |200       |

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
          |removeCandidateFromTest|delete|200       | Candidate has already attempted the test.|
















