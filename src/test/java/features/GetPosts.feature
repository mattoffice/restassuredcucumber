Feature:
  Verify different GET operations using REST-assured

  Scenario: Verify one author of the post
    Given I perform GET operation for "/posts"
    Then I should see the author name as "elliott"

  Scenario: Verify collection of authors in posts
    Given I perform GET operation for "/post"
    Then I should see the author names

  Scenario: Verify parameter of GET
    Given I perform GET operation for "/post"
    Then I should see the GET parameter

  Scenario: Verify query parameter of GET
    Given I perform GET operation for "/post"
    Then I should see the GET query parameter

  Scenario: Verify POST operation
    Given I perform POST operation for "/posts"

    Scenario: Verify POST operation for profile
      Given I perform POST operation for "/posts/{profileNo}/profile" with body
       | name       | profile |
       | serpentine |  6      |
      Then I should see the body name as "serpentine"
