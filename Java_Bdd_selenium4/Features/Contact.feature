Feature: Contact
  @Contactfill
  Scenario: Should verify contact feature under menu using Excel data
    Given User Launch chrome browser
    And Navigate to Home page
    When click on Contact under menu
    Then should input Contact details from Excel
    And Click on send message
    When Alert is opened
    And validate Alert message
