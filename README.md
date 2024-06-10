# EscapeTheMaze

The program will receive as input a file with a description of the maze, and it will produce as output a path from the entrance to the exit, if any exists. A maze consists of a set of rooms connected by corridors, some of which are closed by doors. To open a door a number of coins between 0 and 9 are needed. Each door has associated a number that indicates how many coins are needed to open it. The program will be given a number of coins that it can use to open doors while trying to solve the maze. Each coin can be used only once.

Follow these steps to create your own maze: 

The input file is a text file with the following format:
S
A
L
k
RHRHRH· · ·RHR
VWVWVW· · ·VWV
RHRHRH· · ·RHR
VWVWVW· · ·VWV
...
RHRHRH· · ·RHR
Each one of the first 4 lines contain one number: S, A, L, or k.
 S is the scale factor used to display the maze on the screen. Your program will not use this value. If
the maze appears too small on your monitor, you can increase this value. Similarly, if the maze is too
large, choose a smaller value for the scale.
 A is the width of the maze. The rooms of the maze are arranged in a grid. The number of rooms in
each row of this grid is the width of the maze.
 L is the length of the maze, or the number of rooms in each column of the grid.
 k is the number of coins that the program has available to open maze doors.
For the rest of the file, R is any of the following characters: ’s’, ’x’, or ’o’. H could be ’w’, ’c’, or a digit
’0’, ’1’, ... ’9’. V could be ’c’, ’w’, or a digit ’0’, ’1’, ... ’9’. W must be ’w’. The meaning of the above
characters is as follows:
 ’s’: entrance to the maze
 ’x’: exit of the maze
 ’o’: room
 ’c’: corridor
 ’w’: wall
 ’0’, ’1’, ... ’9’: door that to be opened requires the number of coins specified by the digit; so ’0’
represents a door that does not need any coins to be opened, ’1’ represents a door that requires 1 coin,
and so on.
There is only one entrance and one exit, and each line of the file (except the first four lines) must have the
same length. Here is an example of an input file:
30
4
3
4
s1owo1o
cwcwcwc
o2o3oco
ww4wcwc
ococx3o
