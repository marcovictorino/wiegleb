import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;


//##################################################################
public class tdLearning{
  

   uteis ut = new uteis();

   double [] w;

   double [] eligib;
  
   int nW;

   double gamma;

   double lambda;

//-----------------------------------------------------
   public tdLearning(int nw){

     nW = nw;

     w =  new double[nw];
   }
//-----------------------------------------------------
   public tdLearning(int nw,double g,double l){

     nW = nw;

     gamma = g;

     lambda = l;

     w =  new double[nw];

     eligib = new double[nw];
  
   }
//-----------------------------------------------------   
//############################################################################

//############################################################################
public double[] getW(){

   return w;

}


//############################################################################


//###########################################################
public  void updateWgame(Board penultimoB,Board ultimoB,double alfa){
   
 
   uteis ut = new uteis();

   double vOld = penultimoB.calcV(w);

   double vl = ultimoB.calcV(w);

   double d = 0.0;

   if(ultimoB.geraCandSimples().size() == 0)
      d = ultimoB.calcR1() - vOld;
   else
      d = gamma*vl - vOld;   


   double[] f = penultimoB.avaliationFunction();

   
   for(int i=0;i<nW;i++){

       if(f[i] == 0)
          eligib[i] = gamma*lambda*eligib[i];       
       else
          eligib[i] = (1-vOld*vOld)*f[i];


       w[i] = w[i] + alfa*d*eligib[i];

   } 
      

}
//###############################################

//###############################################
public double wMin(){

   return w[ut.argMin(w)];

}
//###############################################
public int argMin(){  

   return ut.argMin(w);


}
//###############################################
public int argMax(){  

   return ut.argMax(w);

}
//###############################################
public double wMax(){  

   return w[ut.argMax(w)];


}
//###########################################
public  int freQ(){

  int s=0;

  for(int i=0;i<w.length;i++)
     if(w[i] != 0.0)
       s += 1;

  return s;


}
//###########################################
public  double freqMais(){

  double s=0;    

  for(int i=0;i<w.length;i++)
     if(w[i] > 0.0)
       s += 1;

   
  return s/nW;

}
//###########################################
public  double freqMenos(){

  double s=0;  

  for(int i=0;i<w.length;i++)
     if(w[i] < 0.0)
       s += 1;


  return s/nW;

}
//###########################################
public  int freqMais1(){

  int s=0;  

  for(int i=0;i<w.length;i++)
     if(w[i] > 0.0)
       s += 1;

  return s;

}
//###########################################
public  int freqMenos1(){

  int s=0;  

  for(int i=0;i<w.length;i++)
     if(w[i] < 0.0)
       s += 1;

  return s;

}
//############################################################################
public  void savingWeights(String f){       

        try { 

            FileOutputStream fos = new FileOutputStream(f);

            DataOutputStream file = new DataOutputStream(fos);

            for(int i=0;i<nW;i++)
               file.writeDouble(w[i]);


            file.close();

        }catch(IOException ex) {
                System.out.println("Error writing to file " + f);

        }


}
//############################################################################
public  void readingWeights(String arq){       
               

        try {

            FileInputStream f = new FileInputStream(arq);

            DataInputStream file = new DataInputStream(f);

            for(int i=0;i<nW;i++)
               w[i] = file.readDouble();


             file.close();

        }catch(IOException ex) {
                System.out.println("Error writing to file " + "weight.dat");

        }        

}
//====================================================
//############################################################################
public  double[] getEligib(){
 
   return eligib;

}


}//#########################################################
