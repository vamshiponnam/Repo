Feature: Iphone5 search
  @Runme
  Scenario: verify the results of Iphone search
    Given The landing page is opened
    When  I Enter IPhone5 in the search box
    Then  I click the search button
    Then  I verify the results of Iphone5

