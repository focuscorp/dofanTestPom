Feature: Registration

  Background: 
    Given user on the homepage
    And user clicks on "Sign in"
    When user clicks on create new account

  Scenario: Create a New User
    When user enters the following details
      | email adress             | user1@gmail.com                 |
      | First Name               | user1                           |
      | Last Name                | user1                           |
      | User Name                | user1                           |
      | Password                 | azerty@1234                     |
      | Enter the password again | azerty@1234                     |
      | Country                  | Tunisia                         |
      | Job title                | sales manager                   |
      | professional phone       | 123456789                       |
      | Company Name             | Focus                           |
      | Address                  | Focus Building, Z.I Chotrana II |
      | City                     | Ariana                          |
      | Postale code             | 2036                            |
    And user clicks on "register button"
    Then the account is created successfully

  Scenario: This email address is already being used
    When user enters the following details
      | Adress email | user1@gmail.com |
    Then a tooltip is displayed "This email address is already being used"
    

  Scenario: User does not follow form validations
    When user enters wrong characters
    And user clicks on "register button"
    Then error message is displayed
   

  Scenario: mandatory fields
    When user enters blank details for Register
    And user clicks on "register button"
    Then error message is displayed

  Scenario: Login with Github account
    When user clicks on "Sign up with Github" button
    Then the user will be redirected to Dofan registration 
    When user enters the following details
      | Country                  | Tunisia                         |
      | Job title                | sales manager                   |
      | professional phone       | 123456789                       |
      | Company Name             | Focus                           |
      | Address                  | Focus Building, Z.I Chotrana II |
      | City                     | Ariana                          |
      | Postale code             | 2036                            |
    And clicks on register button
    Then the account is created successfully

  
  Scenario:  Login with LinkedIn  account
    When user clicks on "Sign up with LinkedIn " button
    Then the user will be redirected to Dofan registration 
    When user enters the following details
      | Country                  | Tunisia                         |
      | Job title                | sales manager                   |
      | professional phone       | 123456789                       |
      | Company Name             | Focus                           |
      | Address                  | Focus Building, Z.I Chotrana II |
      | City                     | Ariana                          |
      | Postale code             | 2036                            |
    And clicks on register button
    Then the account is created successfully

  Scenario: Login with Google account
    When user clicks on "Sign up with Google " button
    Then the user will be redirected to Dofan registration 
    When user enters the following details
      | Country                  | Tunisia                         |
      | Job title                | sales manager                   |
      | professional phone       | 123456789                       |
      | Company Name             | Focus                           |
      | Address                  | Focus Building, Z.I Chotrana II |
      | City                     | Ariana                          |
      | Postale code             | 2036                            |
    And clicks on register button
    Then the account is created successfully
   
   