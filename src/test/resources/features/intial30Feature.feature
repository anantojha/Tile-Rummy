Feature: Initial meld(s) must equal 30 or more points

  Scenario: Player plays initial melds equal to 28
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 1 first turn
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Player plays initial melds equal to 29
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 1 first turn
    When Player 1 plays "{R9,R10,R11}}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Player plays initial melds equal to 30
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 1 first turn
    When Player 1 plays "{R10,R11,R12}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Player plays initial melds equal to 33
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 1 first turn
    When Player 1 plays "{R3,R4,R5,R6,R7,R8}"
    Then Player 1 hand is updated
    And Table is updated





