Feature: e2e test

  @E2E
  Scenario Outline: Verify if user can add a product to cart
    Given user navigates to home page
    Then verify if user is on the home page
    When user hover over on account&lists arrow
    And user clicks on Sign In button
    And user enters their "<email>"
    And user clicks on Continue button
    And user enters their "<password>"
    And user clicks on SignIn button
    Then verify if they are signed in
    When user clicks on All in search box verify if first option is "All Departments"
    And user clicks on the "Toys & Games" from dropdown
    And user searches for "Puzzles"
    And user selects any product
    And user clicks on add to cart button
    Then verify if product is added to cart
    Examples:
      | email                | password |
      | kacars2010@gmail.com | fake     |
