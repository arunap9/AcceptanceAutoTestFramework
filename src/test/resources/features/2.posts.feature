@posts
Feature: 2. Validate the /posts: verbs

  Background:
    Given setup for posts endpoint completed

  @smoke @regression
  Scenario: 2.1. Get call for posts is successful
    Given GET call for endpoint "/posts" is made
    Then post Success response is returned
    And all the posts are retrieved

  @smoke @regression
   Scenario: 2.2. Valid user is able to make a post
      Given a user POST using endpoint "/posts"
        |title | "foo foo bar" |
        |body  | "post by valid user"|
        |userId | 10 |
      Then post Success response is returned

  @regression
  Scenario: 2.3. Invalid user is unable to make a post
    Given a user POST using endpoint "/posts"
      |title | "foo foo bar" |
      |body  | "post by invalid user"|
      |userId | XXXX |
    Then post unSuccess response is returned

  @regression
  Scenario: 2.4. Valid user is able to update existing post using put
    Given a user calls endpoint "/posts" using method "put" for <userId>
      |title | "qui est esse" |
      |body  | "post by valid user updated"|
      |userId | 10 |
    Then post Success response is returned

  @regression
  Scenario: 2.5. Valid user is able to create new post using put
    Given a user calls endpoint "/posts" using method "put" for <userId>
      |title | "foo foo bar create" |
      |body  | "new post by valid user"|
      |userId | 100  |
    Then post Success response is returned

  @regression
  Scenario: 2.6. Valid user is able to update existing post using patch
    Given a user calls endpoint "/posts" using method "patch" for <userId>
      |title | "foo foo bar update existing" |
      |body  | "post by valid user updated"|
      |userId | 10 |
    Then post Success response is returned

  @regression
  Scenario: 2.7. user is able to delete an existing post
    Given a user calls endpoint "/posts" using method "delete" for <userId>
      |userId | 10 |
    Then post Success response is returned

  @regression
  Scenario: 2.8. Able to retrieve posts of a given user
    Given administrator wants to retrieve "/posts" for given user
          |userId | 1 |
    Then post Success response is returned
    And all the given user posts are retrieved
