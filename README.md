# Wordle Clone - 2 Players

## Rules and Info
This is a competitive version of the popular Wordle daily challenge where a player is asked to guess a five letter word with the least amount of tries.

● This is a two player game.\
● The game has five rounds.\
● Each round, each player will guess at a Wordle.\
● Each round, each player will score points based on the scoring system below.\
● Each Wordle will be unique from the WordleList (no Wordle can be randomly selected more than once).\
● Each player gets six guesses at their Wordle.\
● Each guess must be a valid five-letter word (check against the GuessList).\
● After each guess, if it is not the Wordle, the color of the tiles/letters will change to show how close your guess was to the Wordle.\
● Yellow indicates the letter is in the word and in the correct spot.\
● Blue indicates the letter is in the word but in the wrong spot.\
● Gray indicates the letter is not in the word in any spot.\
● Letters guessed will be indicated by dark gray letter keys.\
● Letters not yet guessed will be indicated by light gray letter keys.

If the player correctly guesses the Wordle, they get the associated points below based on the number of
guesses needed. Zero points are awarded if the player is unable to guess the word in six tries. The cumulative
score will be computed after each round for each player. The player at the end with the most points wins.
1. Correct guess on first guess: 60 pts
2. Correct guess on second guess: 50 pts
3. Correct guess on third guess: 40 pts
4. Correct guess on fourth guess: 30 pts
5. Correct guess on fifth guess: 20 pts
6. Correct guess on sixth guess: 10 pts
7. No correct guesses: 0 pts

<picture>
 <source media="(prefers-color-scheme: dark)" srcset="https://github.com/jmhummer/WordleClone/blob/main/WordleClone/media/example_photo.png">
 <source media="(prefers-color-scheme: light)" srcset="https://github.com/jmhummer/WordleClone/blob/main/WordleClone/media/example_photo.png">
 <img alt="A Wordle game partially played with round one completed in 4 guesses, player one receives 30 points" src="https://github.com/jmhummer/WordleClone/blob/main/WordleClone/media/example_photo.png">
</picture>

## How to Install and Run:
1. Download all files provided
2. Compile all files in src folder, creating .class files (preferrably in a separate bin folder) using the "javac *.java" command in your preferred shell
3. Run the WordleGUI.class file using the "java WordleGUI" command 
4. Enter Player 1's name
5. Enter Player 2's name
6. Begin playing, Player 1 starts first

## Credits
This was a collaborative capstone project for NC State's CSC 116 - Intro to Java course. The primary goals being to use OOP create a responsive game that could cross-reference a given file and run a set of game rules. This was developed in equal parts by Ben Morris, Nick Sanford, Nick Schauer, and Joe Hummer.