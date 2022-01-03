Feature: Different scenarios for sign in

  @positive
  Scenario Outline: Verify if user can successfully sign in with  valid e mail and valid password
    Given user navigates to home page
    Then verify if user is on the home page
    When user hover over on account&lists arrow
    And user clicks on Sign In button
    And user enters their "<email>"
    And user clicks on Continue button
    And user enters their "<password>"
    And user clicks on SignIn button
    Then verify if they are signed in
    Examples:
      | email                | password |
      | kacars2010@gmail.com | fake     |


  @negative
  Scenario Outline: Verify if error message when user wants to sign in with valid email but invalid password
    Given user navigates to home page
    Then verify if user is on the home page
    When user hover over on account&lists arrow
    And user clicks on Sign In button
    And user enters their "<email>"
    And user clicks on Continue button
    And user enters their "<password>"
    And user clicks on SignIn button
    Then verify error message for password
    Examples:
      | email                | password |
      | kacars2010@gmail.com | abc      |

  @negative
  Scenario Outline: Verify if error message when user wants to sign in with invalid email
    Given user navigates to home page
    Then verify if user is on the home page
    When user hover over on account&lists arrow
    And user clicks on Sign In button
    And user enters their "<email>"
    And user clicks on Continue button
    Then verify error message for email
    Examples:
      | email               |
      | kacar2010@gmail.com |