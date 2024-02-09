Feature: Testing different request on the best buy  application.


  Scenario: Check if all products accessed by the users
    When    User sends GET request for all products endpoints.
    Then    User gets the valid status code 200

  Scenario: Check if all stores accessed by the users
    When    User sends GET request for all stores endpoints
    Then    User gets status code 200





