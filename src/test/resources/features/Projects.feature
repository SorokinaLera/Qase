Feature: Tests on Projects page
  Background:
    Given User opens login page
    When Login using correct email and password
    Then  The project page is opened

  Scenario: Creating a new project
    Given Create a new project using random data
    And Click on "Create project" button
    Then The new project must exist
