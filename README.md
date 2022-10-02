# TheMaze

The maze itself will consist of a two dimensional grid of colored cells. The maze will start
at the cell (0,0) and the exit point is the cell (getNCols()-1,getNRows()-1). The operations getNCols()
and getNRows() are methods of the class TwoDimGrid described below. All cells that can be part of
the path will be in the NON_BACKGROUND color. 

<img width="407" alt="image" src="https://user-images.githubusercontent.com/87237633/193433413-f86c7021-f079-4a3f-b50e-9c6fc608fe16.png">


All the cells that represent barriers and cannot be part of the path will be in the BACKGROUND color. To keep track of a cell that
we have visited, we will set it to the TEMPORARY color. If we find a path, all cells on the path
will be reset the PATH color. So there are a total of four possible colors. Initially, the maze
has all cells set to color BACKGORUND, as depicted in Fig. 1. The values we will use for each
of these constants is:
• Green for PATH;
• White for BACKGROUND;
• Red for NON_BACKGROUND; and
• Black for TEMPORARY.
This is specified in the interface GridColors described below
