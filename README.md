# Solving Wiegleb peg solitaire with Temporal Difference Learning, N-tuple and Java.

Features:
- Board is represented by an unidimensional vetor int[45].
- Board.java contains methods to calculate candidate moves, play and print Board.       
- Using  board symmetries: the  n-tuple [0,1,2,3,4,5,6,7,8,12,13,14,22] is rotated and applied to board 90/180/270 degree.
- Reward is given only at the end, when there is no more candidate move, +1 if puzzle is solved, -1  for shorter episodes.
- Using replacing eligibility trace to accelerate convergence. See file tdLearning.java  
- Exploration rate start at 0.1 and decays gaussian.
- The global learning rate decays exponentially from 0.005 to 0.002.
- The Gui application wiegleb.java need  JFreeChart package to show graphics.
- The algorithm always find a solution and saves in puzzleSolved.log file, and sometimes the algorithm "learns"
the solutions. When this happens the 8.192 weights are saved in weight.dat file.
