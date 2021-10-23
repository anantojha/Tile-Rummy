Feature: Drawing a new tile

  Scenario: Drawing a tile from the table
    Given Game is started
    When Player 1 draws new tile
    Then Player 1 hand is updated
    And Table is updated





