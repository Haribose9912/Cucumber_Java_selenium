#@tag
Feature:Login

  @login_positive
  Scenario Outline: Check login with valid multiple credentials
    Given User Launch chrome browser
    And Navigate to login page
    When Input username "<username>" and password "<password>"
    And Click on login
    Then login page user message should be validated
    When User click on logout
    Then Page title should be validated
    And close the browser
    Examples: 
      | username | password |
      | harish24 | Hari@3344|
      | harish13 | test123  |
 @login_negative
  Scenario Outline: Check login with invalid credentials
    Given User Launch chrome browser
    And Navigate to login page
    When Input username "<username>" and password "<password>"
    And Click on login
    When error message should be displayed
    And close the browser
    Examples: 
      | username    | password    |
      | hagdhj | sdafgg|
      | teshha       | hjklaj      |