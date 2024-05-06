Feature: TC_002_Account registrations

  @regression
  Scenario: Successfull Account registration
    Given user should be on home page
    And user navigate to Registration Account page
    When user enter following details below
      | firstName | Harray     |
      | lastName  | Potter     |
      | Telephone | 1234567890 |
      | password  | test@123   |
    And user should be select privacy policy
    And user should be click on continue button
    Then user account should get created successfully
