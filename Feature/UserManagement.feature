Feature: User management

  Background: 
    Given user on the Dashborad login page

  Scenario: User mng is not allowed by User profile
    When User User is logged
    Then the dashboard is displayed
    And "Users" tab is not visible

  Scenario: User mng is available for admin user
    When User admin is logged
    Then the dashboard is displayed
    And "Users" tab is visible and activated

  Scenario: user Creation
    Given When User admin is logged
    When the user clicks on "User" button
    Then User list is displayed
    When he clicks on "add User" button
    Then User creation popup is opened
    When he enters the email "userx@gmail.com"
    And toggle off the toggle button
    And tick the list of projects
    And clicks on ok button
    Then a tooltip is displayed :"User has been added successfully"
    And the newly created user is added to the list

  Scenario: remove user
    Given When User admin is logged
    When the user clicks on "User" button
    And he clicks on "delete" button
    Then confirmation popup is opened
    When he clicks on yes button
    Then the selected user is removed from the list
    Then a tooltip is displayed :"User has been removed successfully"

  Scenario: Edit user
    Given When User admin is logged
    When the user clicks on "User" button
    And the user clicks on "edit" button
    Then edit popup is opened
    When the user changes the email
    And set admin toggle button to on
    And select "the project4"
    And clicks on ok button
    Then the user information is updated in the user list
    And a notification is sent to the newly entred email

  Scenario Outline: Viewing items by status
    Given When User admin is logged
    And the user clicks on "User" button
    And the user list containing <Users>
    When the users filters the list to show only <filter> status
    Then the user list should contain <expected>
    Examples: 
      | Users                                   | filter    | expected            |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | pending   | AiriSatou@gmail.com |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | registred | AshtonCox@gmail.com |

  Scenario Outline: Viewing items by role
    Given When User admin is logged
    And the user clicks on "User" button
    And the user list containing <Users>
    When the users filters the list to show only <filter> status
    Then the user list should contain <expected>
    Examples: 
      | Users                                   | filter | expected            |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | admin  | AiriSatou@gmail.com |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | user   | AshtonCox@gmail.com |

  Scenario Outline: Viewing items by Projects
    Given When User admin is logged
    And the user clicks on "User" button
    And the user list containing <Users>
    When the users filters the list to show only <filter> status
    Then the user list should contain <expected>
    Examples: 
      | Users                                   | filter          | expected            |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | Ewa watch alert | AiriSatou@gmail.com |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | ewa monotoring  | AshtonCox@gmail.com |

  Scenario Outline: Viewing items by Name
    Given When User admin is logged
    And the user clicks on "User" button
    And the user list containing <Users>
    When the users filters the list to show only <filter> status
    Then the user list should contain <expected>
    Examples: 
      | Users                                   | filter | expected            |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | Airi   | AiriSatou@gmail.com |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | Ashton | AshtonCox@gmail.com |

  Scenario Outline: Viewing items by email
    Given When User admin is logged
    And the user clicks on "User" button
    And the user list containing <Users>
    When the users filters the list to show only <filter> status
    Then the user list should contain <expected>
    Examples: 
      | Users                                   | filter          | expected            |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | Ewa watch alert | AiriSatou@gmail.com |
      | AiriSatou@gmail.com,AshtonCox@gmail.com | ewa monotoring  | AshtonCox@gmail.com |

  Scenario Outline: Export functionality
    Given When User admin is logged
    And the user clicks on "User" button
    When the user clicks on export button
    And the user selects <document_type> from the displayed dropdown list
    Then document will be automatically downloaded
    Examples: 
      | document |
      | PDF      |
      | Excel    |

  Scenario Outline: Show entries functionality
    Given When User admin is logged
    And The user clicks on "User" button
    When the user selects <number> from the dropdown list
    Then the user list contains <number> entries
    Examples: 
      | Number |
      | 10     |
      | 50     |