Feature: Playing a Meld

  Scenario: Playing a Set of 3 tiles
    Given Game is started
    And Player 1 hand is "{R1,B1,G1,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,R13}"
    And Player 2 hand is "{R2,B2,O2,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B13}"
    And Player 3 hand is "{R3,G3,O3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,G13}"
    When Player 1 plays "{R1,B1,G1}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{R2,B2,O2}"
    Then Player 2 hand and table is updated
    When Player 3 plays "{R3,G3,O3}"
    Then Player 3 hand and table is updated
    When Player 1 plays "{B4,G4,O4}"
    Then Player 1 hand and table is updated

  Scenario: Playing a Set of 4 tiles
    Given Game is started
    And Player 1 hand is "{R1,B1,G1,O1,R2,R3,R4,R5,R6,B2,B3,B4,B5,B6}"
    And Player 2 hand is "{R13,B13,G13,O13,O2,O3,O4,O5,O6,O7,O8,O9,O10,O11}"
    When Player 1 plays "{R1,B1,G1,O1}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{R13,B13,G13,O13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 3 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 4 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 5 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 6 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G8,G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 7 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G7,G8,G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 8 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G6,G7,G8,G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 9 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G5,G6,G7,G8,G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 10 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G4,G5,G6,G7,G8,G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 11 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 12 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated

  Scenario: Playing a Run of 13 tiles
    Given Game is started
    And Player 1 hand is "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"
    And Player 2 hand is "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13,O1}"
    When Player 1 plays "{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13}"
    Then Player 1 hand and table is updated
    When Player 2 plays "{G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,G13}"
    Then Player 2 hand and table is updated