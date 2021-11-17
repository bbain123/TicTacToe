# TicTacToe
A game of tic-tac-toe with obstacles!

## How to run
run PlayGame.java with arguments *input1.txt, input2.txt* or *input3.txt*. You can also make your own board and pass that text file as an argument.  
> Ex. java PlayGame input1.txt

## How to play
The computer plays as 'O' and the user plays as 'X'  
Click on any of the green tiles to place your piece  
Dark blue tiles are obstacles - no one can place a piece there
Get a certain number of pieces in a straight line to win (specified as the 3rd number in the input file)

## Configure a custom board
**To play a custom board, follow this format:**  
number-of-rows  
number-of-columns  
number-of-pieces-in-a-row-to-win  
3 (the speed the computer reacts, but plays best at 3)  
*place each piece of the board*     where g = open tile and d = obstacle
>Ex.    
>5  
>4  
>3  
>3  
>gggg  
>gdgg  
>gggg  
>gggg  
>gggg
#### Note: Each row has 4 letters because there are 4 columns on the board, 
and there are 5 rows of letters because there are 5 rows on the board. The example creates a 5x4 board with an obstacle at (2, 4)
