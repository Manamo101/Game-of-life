# Conway's Game of life (Conway 23/3)
### CLI version

Map is toroidal.

Program was written in Java and uses multithreading.

Program requires formatted text file.

```
10
10
25
5
3 4
4 5
5 3
5 4
```
[glider_small_board.txt](https://github.com/Manamo101/lab5/files/13798045/glider_small_board.txt)

Where:
- lines 1 and 2 contain the bounds of the board
- line 3 contains number of iterations
- line 4 contains number of living cells
- lines from no. 5 to  * contain indexes of living cells

Remember: indexes start from 0 and first cell, i.e. 0:0, is on the top left corner.

Arguments are typed while invoking program in undermentioned order:
1. number of threads, which have to be used by the program
2. path to the text file with initial distribution
