Feature: WorkForce take-home test

  @wp
  Scenario: Verify that user can login to the book store and add the book
  Given User navigates to the login page
  When user loging in using username and password
  Then navigates to BookStore
  And filter the listed books using the word: "Guide"
  When clicks on the book and add it to your collection
  And Check that the book was added on your profile page
  Then Log out


  @api
  Scenario: BookStore api verification
    Given  User sends GET request to "/v1/Book" and content type should be "application/json"
    Then status code should be 200
    And "title" should "Git Pocket Guide"
    And "pages" should be 234

#  @api
#  Scenario: Verify that BookStore that we have a 200 status code
#    Given  url and content type should be "ContentType.JSON"
#    When we navigate to the endpoint "/v1/Book"
#    Then status code should be 200
#    And "title" should "Git Pocket Guide"
#    And "pages" should be "234"