Feature: Playing a Joker

  Scenario: Playing Joker as R1
    Given Game is started
    And Player 1 hand is "{J0,B1,G1,O1,G4,O4,R7,R8,R9,R10,R11,R12,R13,R13}"
    When Player 1 plays "{J0,B1,G1,O1}"
    Then Player 1 hand is updated
    And Table is updated
    And Joker is used as another tile

  Scenario: Playing Joker as R9 or R13
    Given Game is started
    And Player 1 hand is "{R1,R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,J0}"
    When Player 1 plays "{R10,R11,R12,J0}"
    Then Player 1 hand is updated
    And Table is updated
    And Joker is used as another tile

  Scenario: Playing Joker as B1
    Given Game is started
    And Player 1 hand is "{R1,J0,G1,O1,G4,O4,R7,R8,R9,R10,R11,R12,R13,R13}"
    When Player 1 plays "{R1,J0,G1,O1}"
    Then Player 1 hand is updated
    And Table is updated
    And Joker is used as another tile

  Scenario: Playing Joker as B9 or B13
    Given Game is started
    And Player 1 hand is "{B1,B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,J0}"
    When Player 1 plays "{B10,B11,B12,J0}"
    Then Player 1 hand is updated
    And Table is updated
    And Joker is used as another tile

  Scenario: Playing Joker as G1
    Given Game is started
    And Player 1 hand is "{R1,B1,J0,O1,G4,O4,R7,R8,R9,R10,R11,R12,R13,R13}"
    When Player 1 plays "{R1,B1,J0,O1}"
    Then Player 1 hand is updated
    And Table is updated
    And Joker is used as another tile

  Scenario: Playing Joker as G9 or G13
    Given Game is started
    And Player 1 hand is "{G1,G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12,J0}"
    When Player 1 plays "{G10,G11,G12,J0}"
    Then Player 1 hand is updated
    And Table is updated
    And Joker is used as another tile

  Scenario: Playing Joker as O1
    Given Game is started
    And Player 1 hand is "{R1,B1,G1,J0,G4,O4,R7,R8,R9,R10,R11,R12,R13,R13}"
    When Player 1 plays "{R1,B1,G1,J0}"
    Then Player 1 hand is updated
    And Table is updated
    And Joker is used as another tile

  Scenario: Playing Joker as O9 or O13
    Given Game is started
    And Player 1 hand is "{O1,O1,O2,O3,O4,O5,O6,O7,O8,O9,O10,O11,O12,J0}"
    When Player 1 plays "{O10,O11,O12,J0}"
    Then Player 1 hand is updated
    And Table is updated
    And Joker is used as another tile