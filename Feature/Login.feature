Feature: Login

  Background: 
    Given user on the Dashborad login page

  Scenario: First login for user added by admin
    Given email address is already filled
    When the user enters password 
    And the user enters password to confirm
    And user click "log in" button 
    Then user should see "the User Dashboard"


  Scenario: Verification of Admin Login Function
    When user enters "email address" with "user1admin@gmail.com"
    And user enters "password" with "admin@1234"
    And user click "log in" button
    Then user should see "the Admin Dashboard"

  Scenario: Verification of user Login Function
    When user enters "email address" with "user1@gmail.com"
    And user enters "password" with "user@1234"
    And user click "log in" button
    Then user should see "the User Dashboard"

  Scenario: Unsuccessful login
    And user enters "email address" with "useradmin@gmail.com"
    And user enters "password" with "adminwrong@1234"
    And user clicks "login" button
    Then error message displayed with “incorrect username or password"
    And user returns back on login page

  Scenario:  Login with Github account
    When user clicks on "log in with Github" button
    Then user should be redirected to "the Admin Dashboard"

  Scenario:  Login with LinkedIn  account
    When user clicks on "log in with LinkedIn " button
    Then user should be redirected to "the Admin Dashboard"

  Scenario:  Login with Google account
    When user clicks on "log in with Google " button
    Then user should be redirected to "the Admin Dashboard"