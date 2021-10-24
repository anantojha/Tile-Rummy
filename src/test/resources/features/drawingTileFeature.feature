Feature: Drawing a new tile

  Scenario Outline: Drawing a tile from the table
    Given Round is started
    When Player <player> draws new tile
    Then Player <player> hand is updated
    And Table is updated
    Examples:
      | player        |
      |1              |
      |2              |
      |3              |




