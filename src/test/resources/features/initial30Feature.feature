Feature: Initial melds must equal 30 or more points

  Scenario Outline: Player plays initial 30 point melds
    Given Round is started
    And Player <player> hand is <hand>
    And Player <player> first turn
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                     |move                                             |
      |1      |"{R11,R12,R13,B7,B8,B9,G1,G4,G10,G11,O2,O5,O9,O10}"      |"{R11,R12,R13}"                                  |
      |1      |"{R3,R12,G2,B7,B8,B12,G1,G4,G10,G12,O2,O5,O9,O10}"       |"{R12,G12,B12}"                                  |
      |1      |"{R9,R10,R11,R12,R13,B8,B9,G1,G4,G10,G11,O2,O5,O9,O10}"  |"{R9,R10,R11,R12,R13}"                           |
      |1      |"{R3,R5,R13,B2,B8,B13,G1,G4,G13,G11,O2,O5,O9,O13}"       |"{R13,G13,B13,O13}"                              |
      |1      |"{R3,R5,R9,B2,B8,B9,G1,G4,G9,G11,O2,O5,O9,O9}"           |"{R9,G9,B9,O9}"                                  |
      |1      |"{R2,R3,R4,B7,B8,B9,G1,G4,G10,G11,O2,O5,O9,O10}"         |"{R2,R3,R4}{B7,B8,B9}"                           |
      |1      |"{R2,R3,R4,R5,B2,B5,G1,G4,G10,G11,O2,O4,O5,O10}"         |"{R2,B2,O2}{G4,O4,R4}{O5,B5,R5}"                 |
      |1      |"{R2,R3,R4,R8,B2,B5,G1,G4,G8,G11,O2,O4,O8,O10}"          |"{R8,G8,O8}{R2,R3,R4}"                           |
      |1      |"{R2,R3,B2,B3,B5,B6,B7,G2,G3,G4,G8,G11,O2,O3}"           |"{R2,O2,B2}{G2,G3,G4}{R3,B3,O3}{B5,B6,B7}"       |
      |1      |"{R2,B2,G2,G3,G4,G5,G6,G7,O2,O4,O5,O6,O7,O8}"            |"{R2,B2,G2,O2}{G3,G4,G5,G6,G7}{O4,O5,O6,O7,O8}"  |


