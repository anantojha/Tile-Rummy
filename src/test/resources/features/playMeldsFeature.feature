Feature: Playing a Meld

  Scenario: Playing a Set of 3 tiles
    Given Game is started
    And Player 1 hand is "{R1,B1,G1,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,R13}"
    When Player 1 plays "{R1,B1,G1}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Set of 4 tiles
    Given Game is started
    And Player 1 hand is "{R1,B1,G1,O1,R2,R3,R4,R5,R6,B2,B3,B4,B5,B6}"
    When Player 1 plays "{R1,B1,G1,O1}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 3 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 4 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 5 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 6 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 7 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 8 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 9 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 10 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 11 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 12 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12}"
    Then Player 1 hand is updated
    And Table is updated

  Scenario: Playing a Run of 13 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13}"
    Then Player 1 hand is updated
    And Table is updated