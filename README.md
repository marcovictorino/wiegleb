# Solving Wiegleb peg solitaire with Temporal Difference Learning, N-tuple and Java.

Features:
- Board is represented by an int[] matrix as:
           00 01 02
           03 04 05
           06 07 08
  09 10 11 12 13 14 15 16 17
  18 19 20 21 22 23 24 25 26
  27 28 29 30 31 32 33 34 35
           36 37 38
           39 40 41
           42 43 44
           
- Board.java contains methods to calculate candidate moves, play and print Board.       
- Using  board symmetries: the  n-tuple [0,1,2,3,4,5,6,7,8,12,13,14,22] is rotated and applied to board 90/180/270 degree.
- Reward is given only at the end, when there is no more candidate move, +1 if puzzle is solved, -1  for shorter episodes.
- Using replacing eligibility trace to accelerate convergence. See file tdLearning.java  
- Exploration rate start at 0.1 and decays gaussian.
- The global learning rate decays exponentially from 0.005 to 0.002.
- The Gui application r1.java need  JFreeChart package to show graphics.
- 
   
