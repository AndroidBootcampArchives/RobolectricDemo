Feature: Calculation feature

  Scenario: As a user I can add two numbers
    When I enter numbers "12" and "3"
    And I select "addition" operation
    And I press the "Result" button
    Then I should see the "15" in the result box



  Scenario: As a user I calculate the factorial of a number

    When I enter number "3"
    And I select "factorial" operation
    And I press the "Result" button
    Then I should see the "6" in the result box


