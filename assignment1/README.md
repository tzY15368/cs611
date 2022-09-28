# CS611-Assignment 1

## Generic Board Game

----
Tingzhou Yuan

tzyuan15@bu.edu

U79759599

## Files

----

- Main.java
  
   Entry point of code for players, starts game.

- OrderNChaos.java

   OrderNChaos implmentation of `Boardgame`, takes care of game initialization and judging

- TikTakToe.java

   TikTakToe impl of `Boardgame`, takes care of game initialization and judging

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

----

### Correctness

1. Any board with m * n is allowed, as long as it's not less than default size.
2. `Board` checks position duplicates before placing any `Move` onto the board.
3. Game will end when board is filled, with game result specified by classes inherited from `Boardgame`.

### Scalability

1. Board scalability: fully supported

2. Fully Configurable Team / Player, with interface. Each player can be assigned name and any desired amount of Move pieces. The way teams swap players totally depends up to the team implementaiton.

### Extendability

1. cell-based board games: `chess` is doable, `wordle` would require the ability to rollback, which is easily achievable as we keep track of round numbers on board moves
2. turn based games: `card games` are easily doable, `monopoly` would need additional attributes on `Playable` interface to record player balances.

## How to compile and run

----

1. `unzip cs611 && cd cs611/assignment1`
2. `javac -d ./bin *.java`
3. `java -cp ./bin Main`

## Input/Output Example

----

  Note: input format is different for tiktaktoe and ordern'chaos, please read the input prompt for accurate information*

### TikTakToe example

```bash

choose your game, 1 for tiktaktoe and 2 for orderNchaos
1
Welcome to TikTakToe...    
---------------------------
enter [int] [int] to resize or any string to stick to defaultPair<3,3>: 
new m and new n must be greater or equal to original (default)
4 4
_ _ _ _ 
_ _ _ _ 
_ _ _ _ 
_ _ _ _ 

<Player[x-holder]>: input: [m] [n] (piece: X): 
0 0
X _ _ _
_ _ _ _
_ _ _ _
_ _ _ _

<Team[<Player[alice]>, <Player[bob]>, ]>: sending <Player[alice]>
<Player[alice]>: input: [m] [n] (piece: O):
0 1
......
X O _ _
X O _ _
X O _ _
X _ _ _

winner is <Player[x-holder]>
enter Y to play next game:  
N
Summary:
        Out of 1 rounds,
 <Player[x-holder]> won [1] games;
 <Team[<Player[alice]>, <Player[bob]>, ]> won [0] games; 
Bye.

```
