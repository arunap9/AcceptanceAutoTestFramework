@comments
Feature: 3. Get /comments: return list of users

  Background:
    Given setup for comments endpoint completed

  @smoke @regression
  Scenario: 3.1. Get Users list
    Given endpoint "/comments" is hit
    Then Comments Success response is returned
    And all the comments on all posts can be retrieved


  @regression
  Scenario: 3.2. Get comments on given post
    Given nested call for "/posts" endpoint for "/comments" is made
      |postid | 1 |
    Then Comments Success response is returned
    And all the comments on the post can be retrieved