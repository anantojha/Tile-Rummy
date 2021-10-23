Feature: Starting a game

  Scenario: Launching the server and joining the game
    Given the server is running on port 9010
    When I connect to the server on port 9010 with name "P1"
    Then Player 1 is connected to server
    When I connect to the server on port 9010 with name "P2"
    Then Player 2 is connected to server
    When I connect to the server on port 9010 with name "P3"
    Then Player 3 is connected to server
