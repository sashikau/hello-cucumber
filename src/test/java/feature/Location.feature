
Feature: Location Information Validation

  Scenario: Verify Location Information in the API Response
    Given I make a request to the location API
    When I make a GET request to the endpoint "nz/1041"
    Then the response status code should be 200
    And the response should have post code "1041"
    And the response should have country "New Zealand"
    And the response should have country abbreviation "NZ"
    And the response should have place name "Mount Roskill"
    And the response should have longitude "174.7335"
    And the response should have latitude "-36.9125"

      