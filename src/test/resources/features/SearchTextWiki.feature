Feature: Search text in Wikipedia

  @searchWiki
  Scenario: Check in Wikipedia that the year the first automatic process was developed was 1785
    Given I search "Automatización" in Google
    When I open the article in Wikipedia
    Then I check the year the first automatic process was developed was "1785"
