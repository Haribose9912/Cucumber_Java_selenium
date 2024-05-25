Feature: Registration
  @Register
  Scenario Outline: Should verify Signup feature under menu using data table
    And Navigateto Home page
    When click on SignUP under menu
    Then should singinup with name and pass and validate alert message
    |username |password|
    |haris11|hari@111|
    |kuarer2|laasd01|
    |sharanya|vbs1234|
    