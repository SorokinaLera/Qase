Feature: Login
  Scenario: Login using correct credentials
    Given User opens login page
    When Login using correct email and password
    Then  The project page is opened
