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