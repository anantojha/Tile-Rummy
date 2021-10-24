Feature: Playing a valid Meld with a Joker

  Scenario Outline: Playing a Joker
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    And Joker is used as another tile
    Examples:
      |player |hand                                                       |move                  |
      |1      |"{J0,B1,G1,O1,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B1,G1,O1}"       |
      |1      |"{J0,B2,G2,O2,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B2,G2,O2}"       |
      |1      |"{J0,B3,G3,O3,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B3,G3,O3}"       |
      |1      |"{J0,B4,G4,O4,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B4,G4,O4}"       |
      |1      |"{J0,B5,G5,O5,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B5,G5,O5}"       |
      |1      |"{J0,B6,G6,O6,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B6,G6,O6}"       |
      |1      |"{J0,B7,G7,O7,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B7,G7,O7}"       |
      |1      |"{J0,B8,G8,O8,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B8,G8,O8}"       |
      |1      |"{J0,B9,G9,O9,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"         |"{J0,B9,G9,O9}"       |
      |1      |"{J0,B10,G10,O10,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"      |"{J0,B10,G10,O10}"    |
      |1      |"{J0,B11,G11,O11,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"      |"{J0,B11,G11,O11}"    |
      |1      |"{J0,B12,G12,O12,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"      |"{J0,B12,G12,O12}"    |
      |1      |"{J0,B13,G13,O13,G4,O4,R7,R8,R9,R10,R11,R12,R13,O13}"      |"{J0,B13,G13,O13}"    |