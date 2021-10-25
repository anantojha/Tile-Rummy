Feature: Reusing Tiles From Table

  Scenario Outline: Reuse from Run without Joker
    Given Round is started
    And Player <playerOne> hand is <playerOneHand>
    And Player <playerTwo> hand is <playerTwohand>
    When Player <playerOne> plays <playerOneMove>
    Then Player <playerOne> hand is updated
    And Table is updated
    When Player <playerTwo> plays <playerTwoMove>
    Then Player <playerTwo> hand is updated
    And Table is updated
    Examples:
    |playerOne|playerTwo|playerOneHand                                      |playerTwohand                                       |playerOneMove                 |playerTwoMove          |
    |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,G1}"   |"{R1,R2,R3,R4}"               |"{*R4,R5,R6}"          |
    |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,G1}"   |"{R1,R2,R3,R4,R5}"            |"{R3,R4,*R5,R6}"       |
    |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,G1}"   |"{R1,R2,R3,R4,R5,R6}"         |"{R4,*R5,*R6}"         |
    |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,G1}"   |"{R1,R2,R3,R4,R5,R6,R7}"      |"{R4,*R5,*R6,*R7}"     |
    |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,G1}"   |"{R1,R2,R3,R4,R5,R6,R7,R8}"   |"{*R5,*R6,*R7,*R8,R9}" |
    |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R4}"  |"{R3,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1,B4,G4,O4}"   |"{R2,R3,R4}{R4,R5,R6,R7}"     |"{**R4,B4,G4,O4}"      |


  Scenario Outline: Reuse from Set without Joker
    Given Round is started
    And Player <playerOne> hand is <playerOneHand>
    And Player <playerTwo> hand is <playerTwohand>
    When Player <playerOne> plays <playerOneMove>
    Then Player <playerOne> hand is updated
    And Table is updated
    When Player <playerTwo> plays <playerTwoMove>
    Then Player <playerTwo> hand is updated
    And Table is updated
    Examples:
      |playerOne|playerTwo|playerOneHand                                    |playerTwohand                                   |playerOneMove     |playerTwoMove     |
      |1        |2        |"{R1,R2,R3,R4,R5,B1,B2,B3,B4,B5,G1,G2,O1,O2}"    |"{G1,G2,G3,G4,G5,O1,O2,O3,O4,O5,R2,R3,B1,B2}"   |"{R1,B1,G1,O1}"   |"{*R1,R2,R3}"     |
      |1        |2        |"{R1,R2,R3,R4,R5,B1,B2,B3,B4,B5,G1,G2,O1,O2}"    |"{G1,G2,G3,G4,G5,O1,O2,O3,O4,O5,R1,R2,B2,B3}"   |"{R1,B1,G1,O1}"   |"{*B1,B2,B3}"     |
      |1        |2        |"{R1,R2,R3,R4,R5,B1,B2,B3,B4,B5,G1,G2,O1,O2}"    |"{G1,G2,G3,G4,G5,O1,O2,O3,O4,O5,R1,R2,B1,B2}"   |"{R1,B1,G1,O1}"   |"{*G1,G2,G3}"     |
      |1        |2        |"{R1,R2,R3,R4,R5,B1,B2,B3,B4,B5,G1,G2,O1,O2}"    |"{G1,G2,G3,G4,G5,O1,O2,O3,O4,O5,R1,R2,B1,B2}"   |"{R1,B1,G1,O1}"   |"{*O1,O2,O3}"     |

  Scenario Outline: Reuse from Run with Joker
    Given Round is started
    And Player <playerOne> hand is <playerOneHand>
    And Player <playerTwo> hand is <playerTwohand>
    When Player <playerOne> plays <playerOneMove>
    Then Player <playerOne> hand is updated
    And Table is updated
    When Player <playerTwo> plays <playerTwoMove>
    Then Player <playerTwo> hand is updated
    And Table is updated
    And Joker is used as another tile
    Examples:
      |playerOne|playerTwo|playerOneHand                                      |playerTwohand                                       |playerOneMove                 |playerTwoMove             |
      |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,J0}"   |"{R1,R2,R3,R4}"               |"{*R4,R5,J0}"             |
      |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,J0}"   |"{R1,R2,R3,R4,R5}"            |"{J0,R4,*R5,R6}"          |
      |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,J0}"   |"{R1,R2,R3,R4,R5,R6}"         |"{J0,*R5,*R6}"            |
      |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,J0}"   |"{R1,R2,R3,R4,R5,R6,R7}"      |"{J0,*R5,*R6,*R7}"        |
      |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,B1}"  |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,J0}"   |"{R1,R2,R3,R4,R5,R6,R7,R8}"   |"{*R5,*R6,*R7,*R8,R9,J0}" |
      |1        |2        |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R4}"  |"{R3,R5,R6,R7,R8,R10,R11,R12,R13,B1,B4,G4,J0,J0}"   |"{R2,R3,R4}{R4,R5,R6,R7}"     |"{**R4,B4,G4,J0}"         |

  Scenario Outline: Reuse from Set with Joker(s)
    Given Round is started
    And Player <playerOne> hand is <playerOneHand>
    And Player <playerTwo> hand is <playerTwohand>
    When Player <playerOne> plays <playerOneMove>
    Then Player <playerOne> hand is updated
    And Table is updated
    When Player <playerTwo> plays <playerTwoMove>
    Then Player <playerTwo> hand is updated
    And Table is updated
    And Joker is used as another tile
    Examples:
      |playerOne|playerTwo|playerOneHand                                    |playerTwohand                                   |playerOneMove     |playerTwoMove            |
      |1        |2        |"{R1,R2,R3,R4,R5,B1,B2,B3,B4,B5,G1,G2,O1,O2}"    |"{G1,G2,G3,G4,G5,O1,O2,O3,O4,O5,R1,R2,R4,J0}"   |"{R1,B1,G1,O1}"   |"{*R1,R2,J0,R4}"         |
      |1        |2        |"{R1,R2,R3,R4,R5,B1,B2,B3,B4,B5,G1,G2,O1,O2}"    |"{G1,G2,G3,G4,G5,O1,O2,O3,O5,R1,R2,B2,B4,J0}"   |"{R1,B1,G1,O1}"   |"{*B1,B2,J0,B4}"         |
      |1        |2        |"{R1,R2,R3,R4,R5,B1,B2,B3,B4,B5,G1,G2,O1,O2}"    |"{G2,G3,G4,G5,G6,O1,O2,O3,O4,O5,R1,R2,B1,J0}"   |"{R1,B1,G1,O1}"   |"{*G1,G2,J0,G4,G5,G6}"   |
      |1        |2        |"{R1,R2,R3,R4,R5,B1,B2,B3,B4,B5,G1,G2,O1,O2}"    |"{G1,G2,G3,G4,G5,O1,O2,O4,O5,O6,R1,R2,B1,J0}"   |"{R1,B1,G1,O1}"   |"{*O1,O2,J0,O4}"         |