Feature: TC_001 Login With Valid credentials

  @sanity @regression
  Scenario: Verify login Test case
    Given user should be on home page
    And User navigate to  the Login Page
    When user should enter the Email as "prshant007k@gmail.com" and password as "test@123"
    And user should be click login button
    Then user should be redirect to MyAccount page

  #@regression
  #Scenario Outline: Login Data Driven
    #Given the user navigates to login page
    #When user enters email as "<email>" and password as "<password>"
    #And the user clicks on the Login button
    #Then the user should be redirected to the MyAccount Page
#
    #Examples: 
      #| email                 | password |
      #| prshant007@gmail.com  | test@123 |
      #| prshant007k@gmail.com | test@13  |
      #| prshant007k@gmail.com | test@123 |
      #|                       |          |
