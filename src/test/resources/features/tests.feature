@tests
Feature: Test

  @searchWiki
  Scenario: Check in Wikipedia that the year the first automatic process was developed was 1785
    Given I search "Automatización" in Google
    When I open the article in Wikipedia
    Then I check the year the first automatic process was developed was "1785"

  @add-user
  Scenario: Create user with HTTP and get data using according service
    Given the endpoint is "/user"
    And I request POST with payload
      | key | value |
      | id  | 0 |
      | username  | username1234 |
      | firstName  | john |
      | lastName  | doe |
      | email  | johndoe63589317@gmail.com |
      | password  | rand0Mpa$$word1234 |
      | phone  | nophone |
      | userStatus  | 0 |
    And the endpoint is "/user/username1234"
    When I request GET with no parameters
    Then endpoint code returned is 200

  @get-sold-pets
  Scenario: Get sold pets
    Given the endpoint is "/pet/findByStatus"
    When I request GET with parameters "?status=sold"
    Then I print the results by id and name

  @get-pets-with-same-name
  Scenario: Get pets with the same name
    Given the endpoint is "/pet/findByStatus"
    When I request GET with parameters "?status=sold"
    Then I print how many pets have the same name