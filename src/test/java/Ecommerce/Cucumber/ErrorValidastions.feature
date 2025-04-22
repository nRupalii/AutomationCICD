@tag
Feature: Purchase the Order from Ecommerce Website
  Background:
    Given I landed on Ecommerce Page

  @ErrorValidastions
  Scenario Outline: Negative Test of Login with Wrong User
    Given Logged in with username <name> and password <password>
    Then "Incorrect email" message is displayed
    Examples:
      | name           | password    |
      | anka@gmail.com | Iamking@000 |