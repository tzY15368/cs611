# CS611-Assignment 1

## Generic Board Game
---------------------------------------------------------------------------
Tingzhou Yuan

tzyuan15@bu.edu

U79759599

## Files
---------------------------------------------------------------------------

- Main.java
  
   Entry point of code for players, starts game.

- OrderNChaos.java

   OrderNChaos implmentation of Boardgame, takes care of game initialization and judging

- TikTakToe.java

   TikTakToe impl of Boardgame, takes care of game initialization and judging

- Boardgame.java

   Generic boardgame, has built in `runGame` and `runRound` to run any type of boardgame, has abstract `getState` method to allow specific boardgames that inherit from `Boardgame` to handle game logic, has `Board` to hold the actual board.

- Board.java

   Holds board data, and provide interface for other actors to move pieces around on a dynamic NxM 2-dimensional board where each position corresponds to an empty space or a piece.

   *every piece is a `Move` object*

- Move.java

   Represents a `Move`, players place `Move` on the Board.

- Playable.java

   Playable interface provides abstraction among `SinglePlayer` and `Team`, as long as an object implements the given methods, they can join any `BoardGame`.

- util.java



## Notes
---------------------------------------------------------------------------

1. <Files to be parsed should be stored in ConfigFiles, for parser class to read class>
2. Configurable Team / Player

## How to compile and run
---------------------------------------------------------------------------

1. `unzip cs611 && cd cs611 && cd assignment1`
2. `javac -d ./bin *.java`
3. `java -cp ./bin Main`

## Input/Output Example
---------------------------------------------------------------------------
<Place here an example of how the program runs. Include both its outputs and correctly formatted inputs. Please clearly
mark the inputs.>
e.g.:
Output:
Welcome to Tic-Tac-Toe!
Please enter player 1's name:
Input: Alex Output:
Please enter player 2's name:
Input: Bob Output:
Please enter the size of the board:
Input: 3 Output:
+--+--+--+ | | | | +--+--+--+ | | | | +--+--+--+ | | | | +--+--+--+ Alex please make a move:
Input: 1,2 Output:
+--+--+--+ | | | | +--+--+--+ |X | | | +--+--+--+ | | | | +--+--+--+ . . . +--+--+--+ |X | | | +--+--+--+ |X |O | |
+--+--+--+ |X |O | | +--+--+--+ Alex wins! Would you like to play again? Input: no Output:
Thank you for playing!
Alex won 1 time. Bob won 0 times. There were 0 stalemates.