Feature: DeletePosts
  Test the delete operation

  @smoke
  Scenario:
    Given Ensure I perform POST operation for "/posts" with body as
      | id | title | author  |
      | 10 | layla | clapton |
    And I perform a DELETE operation for "/posts/{postid}/"
      | postid |
      | 10     |
    And I perform a GET operation with path parameter for "/posts/{postid}"
      | postid |
      | 10     |
    Then I should not see body with title "layla"