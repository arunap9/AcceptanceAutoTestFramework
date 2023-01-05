@users
Feature: 1. Get /users: return list of users

  Background:
    Given setup for users endpoint completed

  @smoke @regression
  Scenario: 1.1. Get call for Users list is successful
      Given users endpoint "/users" is hit
      Then Success response is returned
      And all the users details can be retrieved

  @smoke @regression
  Scenario: 1.2. Able to Get given user details
    Given GET method call is made for endpoint "/users" for <userId>
      |userId | 1 |
    Then Success response is returned
    And all the given user details can be retrieved
