Feature: to calculate total cost of a part

  Scenario: changing base price value
    Given User is on landing page
    When User hover row to get and click on pencil icon
    And User enter new value as "5" and click on check icon
    Then Verify display shows correct base price as "5.00"
    When User click on label and add new label and value and submit
      | Alloy surcharge    | 2.15   |
      | Scrap surcharge    | 3.14   |
      | Internal surcharge | 0.7658 |
      | External surcharge | 1      |
      | Storage surcharge  | 0.3    |
    Then Displayed values are shown as expected
    Then User deletes "Internal surcharge" and displayed sum shows correct sum
    Then User edit "Storage surcharge" to change to new label "T" and checks the warning message as "This label is too short!" and label remains same
    Then User edit "Scrap surcharge" to change to new value "-2.15" and checks the warning message as "Cannot be negative!" and label remains same
    Then User edit "Alloy surcharge" to change to new value "1.79" and displayed sum shows correct sum
