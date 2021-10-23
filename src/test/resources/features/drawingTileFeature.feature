Feature: Drawing a new tile

  Scenario: starting a game and drawing a tile turn for first turn
    Given Game is started
    When Player 1 draws new tile
    Then Player 1 hand and table is updated
    When Player 2 draws new tile
    Then Player 2 hand and table is updated
    When Player 3 draws new tile
    Then Player 3 hand and table is updated





