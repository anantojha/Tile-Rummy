Feature: Playing Melds

  Scenario Outline: Playing a Set of 3 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                       |move                 |
      |1      |"{R1,B1,G1,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R1,B1,G1}"         |
      |1      |"{R2,B2,G2,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R2,B2,G2}"        |
      |1      |"{R3,B3,G3,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R3,B3,G3}"         |
      |1      |"{R4,B4,G4,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R4,B4,G4}"         |
      |1      |"{R5,B5,G5,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R5,B5,G5}"         |
      |1      |"{R6,B6,G6,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R6,B6,G6}"         |
      |1      |"{R7,B7,G7,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R7,B7,G7}"         |
      |1      |"{R8,B8,G8,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R8,B8,G8}"         |
      |1      |"{R9,B9,G9,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"         |"{R9,B9,G9}"         |
      |1      |"{R10,B10,G10,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"      |"{R10,B10,G10}"      |
      |1      |"{R11,B11,G11,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"      |"{R11,B11,G11}"      |
      |1      |"{R12,B12,G12,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"      |"{R12,B12,G12}"      |
      |1      |"{R13,B13,G13,B4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"      |"{R13,B13,G13}"      |


  Scenario Outline: Playing a Set of 4 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                   |
      |1      |"{R1,B1,G1,O1,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R1,B1,G1,O1}"        |
      |1      |"{R2,B2,G2,O2,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R2,B2,G2,O2}"        |
      |1      |"{R3,B3,G3,O3,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R3,B3,G3,O3}"        |
      |1      |"{R4,B4,G4,O4,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R4,B4,G4,O4}"        |
      |1      |"{R5,B5,G5,O5,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R5,B5,G5,O5}"        |
      |1      |"{R6,B6,G6,O6,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R6,B6,G6,O6}"        |
      |1      |"{R7,B7,G7,O7,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R7,B7,G7,O7}"        |
      |1      |"{R8,B8,G8,O8,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R8,B8,G8,O8}"        |
      |1      |"{R9,B9,G9,O9,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"          |"{R9,B9,G9,O9}"        |
      |1      |"{R10,B10,G10,O10,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"      |"{R10,B10,G10,O10}"    |
      |1      |"{R11,B11,G11,O11,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"      |"{R11,B11,G11,O11}"    |
      |1      |"{R12,B12,G12,O12,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"      |"{R12,B12,G12,O12}"    |
      |1      |"{R13,B13,G13,O13,G4,O4,R7,R8,R9,R10,R11,R12,R13,B13}"      |"{R13,B13,G13,O13}"    |



  Scenario Outline: Playing a Run of 3 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                   |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3}"           |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4}"           |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5}"           |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R4,R5,R6}"           |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R5,R6,R7}"           |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R6,R7,R8}"           |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R7,R8,R9}"           |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R8,R9,R10}"          |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R9,R10,R11}"         |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R10,R11,R12}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R11,R12,R13}"        |

  Scenario Outline: Playing a Run of 4 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                   |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5,R6}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R4,R5,R6,R7}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R5,R6,R7,R8}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R6,R7,R8,R9}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R7,R8,R9,R10}"       |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R8,R9,R10,R11}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R9,R10,R11,R12}"     |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R10,R11,R12,R13}"    |

  Scenario Outline: Playing a Run of 5 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5,R6}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5,R6,R7}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R4,R5,R6,R7,R8}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R5,R6,R7,R8,R9}"        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R6,R7,R8,R9,R10}"       |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R7,R8,R9,R10,R11}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R8,R9,R10,R11,R12}"     |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R9,R10,R11,R12,R13}"    |

  Scenario Outline: Playing a Run of 6 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5,R6}"       |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5,R6,R7}"       |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5,R6,R7,R8}"       |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R4,R5,R6,R7,R8,R9}"       |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R5,R6,R7,R8,R9,R10}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R6,R7,R8,R9,R10,R11}"     |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R7,R8,R9,R10,R11,R12}"    |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R8,R9,R10,R11,R12,R13}"   |

  Scenario Outline: Playing a Run of 7 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                          |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5,R6,R7}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5,R6,R7,R8}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5,R6,R7,R8,R9}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R4,R5,R6,R7,R8,R9,R10}"     |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R5,R6,R7,R8,R9,R10,R11}"    |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R6,R7,R8,R9,R10,R11,R12}"   |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R7,R8,R9,R10,R11,R12,R13}"  |

  Scenario Outline: Playing a Run of 8 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                              |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5,R6,R7,R8}"       |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5,R6,R7,R8,R9}"       |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5,R6,R7,R8,R9,R10}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R4,R5,R6,R7,R8,R9,R10,R11}"     |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R5,R6,R7,R8,R9,R10,R11,R12}"    |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R6,R7,R8,R9,R10,R11,R12,R13}"   |

  Scenario Outline: Playing a Run of 9 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                                |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5,R6,R7,R8,R9}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5,R6,R7,R8,R9,R10}"     |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5,R6,R7,R8,R9,R10,R11}"    |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R4,R5,R6,R7,R8,R9,R10,R11,R12}"   |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R5,R6,R7,R8,R9,R10,R11,R12,R13}"  |

  Scenario Outline: Playing a Run of 10 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                                    |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5,R6,R7,R8,R9,R10,R11}"     |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5,R6,R7,R8,R9,R10,R11,R12}"    |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R4,R5,R6,R7,R8,R9,R10,R11,R12,R13}"   |

  Scenario Outline: Playing a Run of 11 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                                        |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11}"      |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12}"     |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13}"    |

  Scenario Outline: Playing a Run of 12 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                                          |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12}"    |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13}"   |

  Scenario Outline: Playing a Run of 13 tiles
    Given Round is started
    And Player <player> hand is <hand>
    When Player <player> plays <move>
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      |player |hand                                                        |move                                              |
      |1      |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R13}"          |"{R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13}"    |