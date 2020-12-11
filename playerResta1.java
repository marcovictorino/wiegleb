import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

//##################################################
public class playerResta1{


  uteis ut = new uteis();  

  boolean fgreed;

  public playerResta1(){    

  }

  
//--------------------------------------------------------------------------------
int play(Board b,double aleat,double[] w){

      Random r = new Random();

      int choice= -1;

      ArrayList<Integer> cand = b.geraCandSimples();

      double vl=0.0;
      
      fgreed = false;
//-----------------------------------------------------------
if(cand.size() > 0){

      double[] v = new double[cand.size()];

     //System.out.println(" -------------- ok 2.1");
      //------------------------------------------------------
      if(r.nextDouble() < aleat){

         choice = cand.get(r.nextInt(cand.size()));         
         //choice = cand.get(cand.size() -1);      
      }
      else{         

         fgreed = true;

         //System.out.println(".......................... Candidatos:");

         for(int i=0;i<cand.size();i++){                                     

             int mov = cand.get(i);

             b.setMove(mov);

             double reward = b.calcR1();

             //System.out.println("rew = " + reward);

             //b.printBoard();

             if(reward != 0.0)
                v[i] = reward;
             else
                v[i]= b.calcV(w);              

             b.unsetMove(mov);

         }// fim do for
        
        // System.out.println(" -------------- ok 2.2");

         choice = cand.get(ut.argMax(v));

         //ut.printVetor(v);

     }// fim do else 

  


     //b.printBoard(); 

     //vl = b.calcV(w);

     //System.out.printf(".......................................  TD jogou: (%d)  v = (%.4f)  \n",choice,vl);
     //System.out.println(".......................................:");

     //reward = b.calcR1();  


}


//-----------------------------------------------------------

    return choice;

}
//#############################################
boolean getFgreed(){


   return fgreed;

}
}//##############################################
