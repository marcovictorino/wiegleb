
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;




public class uteis{

     Random randomNumbers;
 
public uteis(){


 randomNumbers = new Random();

}

//#############################################################
public  void printData(double[][] d,double[][] t,int nS){

   System.out.println("---------------------------------------");

   for(int k=0;k<nS;k++){

      System.out.printf("[%d] - ",k);

      for(int i=0;i<d[k].length;i++)
         System.out.printf("(%.1f)",d[k][i]);

      for(int i=0;i<t[k].length;i++)
         System.out.printf("[%.4f]",t[k][i]);

      System.out.println();
   }
}
//##############################################################
public static void salvaTexto(File arq,double[] x,double[] y){  

   try{
  
    
    FileWriter fw = new FileWriter(arq, true);
    BufferedWriter bw = new BufferedWriter(fw);
    
    for(int i=0;i<x.length;i++){
        bw.write(String.valueOf(x[i]));
        bw.write(",");
    }
    for(int i=0;i<y.length-1;i++){
        bw.write(String.valueOf(y[i]));
        bw.write(",");
    }
    bw.write(String.valueOf(y[y.length-1]));

    bw.newLine();
    bw.close();
 
  }catch(IOException e) {System.out.println("Arquivo nao encontrado");}

}
//######################################################################################
public void copiaVetor(double[] x,double[] y){

   for(int i=0;i<x.length;i++)
      y[i] = x[i];


}

//######################################################################################
public void copiaVetorInt(int[] x,int[] y){

   for(int i=0;i<x.length;i++)
      y[i] = x[i];


}
//######################################################################################
public void printVetor(double[] x){


       for(int i=0;i<x.length;i++)
          System.out.printf("[%d](%.4f)",i,x[i]);

       System.out.println();
}
//######################################################################################
public void printVetor1(double[] x){



       for(int i=0;i<x.length;i++){
          System.out.printf("[%d](%.4f)",i,x[i]);

       }
       System.out.println(); 
   
}


//######################################################################################
public void printVetorInt(int[] x){


       for(int i=0;i<x.length;i++)        
            System.out.printf("(%d)",x[i]);

       System.out.println();
}
//#######################################################
public void imprime_x(ArrayList<Integer> x){

    for(int i=0;i<x.size();i++)
       System.out.printf("(%d)",x.get(i));

    System.out.println();

}
//#######################################################
public void imprime_xDouble(ArrayList<Double> x){

    for(int i=0;i<x.size();i++)
       System.out.printf("(%.4f)",x.get(i));

    System.out.println();

}
//#######################################################
public void imprime_xDouble1(ArrayList<Double> x){

    for(int i=x.size()-1;i>-1;i--)
       System.out.printf("(%.4f)",x.get(i));

    System.out.println();

}
//#################################################################
public double rGreed(int ep,int nEP,double gMin,double gMax) {
        
        
        double r;

        r = gMin + ep*(gMax - gMin)/nEP;

        return r;

}


//#################################################################
public double aleat(){
  
  

  return randomNumbers.nextDouble();


}
//#############################################################
public static void encoderOne(double[] y){

     int iMax = 0;
     double vMax = y[0];

     for(int i=1;i<y.length;i++)
        if(y[i]>vMax){

          vMax = y[i];
          iMax = i;

        }

     
     for(int i=0;i<y.length;i++)
        if(i == iMax)
           y[i]=1.0;
        else
           y[i]=0;

     
}
//################################################################
 //--------------------------------------------
public static void leData1(File arq,int nS,double[][] d,double[][] p,int nInput,int nOutput) {

    int i,j,k;

    //double[] preTarget = new double[nS];

    try{       

       Scanner inputFile = new Scanner(arq);      


       for(k=0;k<nS;k++){

          String array[] = new String[nInput+nOutput];

          array = inputFile.nextLine().split(",");

          for(i=0;i<nInput;i++)
              d[k][i] = Double.parseDouble(array[i]);
          for(i=0;i<nOutput;i++)
              p[k][i] = Double.parseDouble(array[i]);
       }

    }catch(IOException e) {System.out.println("Arquivo nao encontrado");}
    
}  
//----------------------------------------------------
public void imprimeLog1(File arq,int player,String resultado){


    try{      

       FileWriter fw = new FileWriter(arq, true);
       BufferedWriter bw = new BufferedWriter(fw);
       bw.write(String.valueOf(player) + " " + resultado);
       bw.newLine();
       bw.write("===============================================");
       bw.newLine();
       bw.close();

    }catch(IOException e) {System.out.println("Arquivo nao encontrado");}


}
//----------------------------------------------------
public void imprimeLog2(File arq,ArrayList<Integer> x,int freq){


    try{      

       FileWriter fw = new FileWriter(arq, true);

       BufferedWriter bw = new BufferedWriter(fw);

       for(int i=0;i<x.size();i++)          
          bw.write("(" + String.valueOf(x.get(i)) + ")");

       bw.write(" Freq = " + String.valueOf(freq));

       bw.newLine();
       bw.write("===============================================");
       bw.newLine();
       bw.close();

    }catch(IOException e) {System.out.println("Arquivo nao encontrado");}


}
//----------------------------------------------------
public void imprimeLog3(File arq,double[] y,int ate){


    try{      

       FileWriter fw = new FileWriter(arq, true);

       BufferedWriter bw = new BufferedWriter(fw);

       for(int i=0;i<ate;i++){          
          bw.write("[" + String.valueOf(i) + "]" + String.valueOf(y[i]));
          bw.newLine();
       }
       
       bw.write("===============================================");
       bw.newLine();
       bw.close();

    }catch(IOException e) {System.out.println("Arquivo nao encontrado");}


}

//----------------------------------------------------
public double meDia(double[] y){

   double s = 0;

   for(int i=0;i<y.length;i++)
      s += y[i];

   return s/y.length;
}
//----------------------------------------------------
public double meDiaAte(double[] y,int nG){

   double s = 0;

   for(int i=0;i<nG;i++)
      s += y[i];

   return s/nG;
}
//----------------------------------------------------
public double devPad(double[] y){


  double s = 0;

  double media = meDia(y);

  for(int i=0;i<y.length;i++)
     s += Math.pow(y[i]-media,2);

  return Math.sqrt(s/y.length);
}
//----------------------------------------------------
public double devPadAte(double[] y,int nG){


  double s = 0;

  double media = meDiaAte(y,nG);

  for(int i=0;i<nG;i++)
     s += Math.pow(y[i]-media,2);

  return Math.sqrt(s/y.length);
}
//--------------------------------------------------------
public void renormalize(double[] x){

        double s = 0;

        for(int i=0;i<x.length;i++)
           if(x[i] > 0)
             s += x[i];

        
        for(int i=0;i<x.length;i++)        
           x[i] /= s;
}
//---------------------------------------------------------------------------------
public  double[] softmax(double[] z) { 

        //System.out.println("......................aplicando Softmax");

        double max = Double.NEGATIVE_INFINITY;

        for (int i=0;i<z.length; i++) {
            if (z[i] > max) {
                max = z[i];
            }
        }

        double sum = 0.0;

        for (int i = 0; i <z.length; i++) {
            double out = Math.exp(z[i] - max);
            z[i] = out;
            sum += out;
        }

        for (int i = 0; i <z.length; i++)
            z[i] /= sum;


        
        return z;
    
}
//##########################################################
public int roletaRussa(double[] y){

      
      double s = 0.0;
      
      double[] prob = new double[y.length];
      double[] sumProb = new double[y.length];

      for (int i=0;i<y.length;i++)
          s += y[i];

      prob[0] = y[0]/s;

      sumProb[0] = prob[0];

      for (int i=1;i<y.length;i++){
          prob[i] = y[i]/s;
          sumProb[i] = sumProb[i-1] + prob[i];
      }

      //printVetor(y);

      //printVetor(prob);

      //printVetor(sumProb);  

      double aleat = aleat();

      //System.out.println("aleat = " + aleat);

      if(aleat <= sumProb[0]){

        return 0;
      }
      else{

       for(int i=1;i<sumProb.length;i++)
          if(aleat <= sumProb[i] && aleat > sumProb[i-1])
            return i;

      }

      return -1;
}
//##########################################################
public int argMax(double[] x){


     double max = x[0];
     int choice = 0;

     for(int i=1;i<x.length;i++)
        if(x[i] > max){
           max = x[i];
           choice = i;
        }



     return choice;

}

//##########################################################
public int argMin(double[] x){


     double min = x[0];
     int choice = 0;

     for(int i=1;i<x.length;i++)
        if(x[i] < min){
           min = x[i];
           choice = i;
        }



     return choice;

}
//##########################################################
public int somaVetor(int[] y){

   int s = 0;

   for(int i=0;i<y.length;i++)
      s += y[i];


   return s;
}
//##########################################################
public double somaComRecursao(double[] x,int k){  // k = comprimento do vetor

  if(k <= 0)
    return 0;
  else
    return somaComRecursao(x,k-1) + x[k-1];

}
//##########################################################
public int valorMax(int[] x){


     int max = x[0];
     int choice = 0;

     for(int i=1;i<x.length;i++)
        if(x[i] > max){
           max = x[i];
           choice = i;
        }



     return max;

}
//##########################################################
public int valorMin(int[] x){


     int min = x[0];
     int choice = 0;

     for(int i=1;i<x.length;i++)
        if(x[i] < min){
           min = x[i];
           choice = i;
        }



     return min;

}
//##########################################################
public double valorMaxDouble(double[] x){


     double max = x[0];
     int choice = 0;

     for(int i=1;i<x.length;i++)
        if(x[i] > max){
           max = x[i];
           choice = i;
        }



     return max;

}
//##########################################################
public double valorMinDouble(double[] x){


     double min = x[0];
     int choice = 0;

     for(int i=1;i<x.length;i++)
        if(x[i] < min){
           min = x[i];
           choice = i;
        }



     return min;

}
//##########################################################
public void reverse(double[] y){


   for(int k=0;k<y.length;k++)
   for(int i=1;i<y.length;i++)
      if(y[i] > y[i-1]){
        double aux = y[i];
        y[i] = y[i-1];
        y[i-1]=y[i];
      }


}
//##########################################################
public void sort(double[] y){

   for(int k=0;k<y.length;k++)
   for(int i=1;i<y.length;i++)
      if(y[i] < y[i-1]){
        double aux = y[i];
        y[i] = y[i-1];
        y[i-1]=y[i];
      }


}
//###########################################
public int locationToMove(int i,int j, int COL){

  return i*COL+j;

}
//###########################################
public int[] moveToLocation(int k,int COL){


  int[] a = new int[2];

  a[0] = k/COL;
  a[1] = k%COL;

  return a;

}
//###########################################
public double mediaMovel(double[] y ,int deslocamento,int nG){

   int d = deslocamento;

   double s=0;



   if(nG > d){

      int inicio=nG-d;  

      for(int i=inicio;i<nG;i++)
         s += y[i];

      s = s/d;

   }


   return s;


}
//###########################################
public double rAleatExp(int ep,int nEP,double gMin,double gMax){

  return gMin/(Math.exp(ep*Math.log(gMin/(gMax+0.001))/nEP));

}
//###########################################
public double ExpDecai(int ep,int nEP,double gMin,double gMax){

  return gMin/(Math.exp(ep*Math.log(gMin/(gMax+0.001))/nEP));

}
//###########################################
double gaussianDecai(int ep,double gMin){

   double k = 3000;

   return  gMin/Math.exp(Math.pow(ep/1000.0,2)/k);


}
//###########################################
public double rAleatSigmoid(int ep,int nEP,double gMin,double gMax){

   double d = gMax - gMin + 1.01;

   //System.out.println("d = " + d);

   double y = Math.log((2-d)/d)*ep/nEP;

   //System.out.println("y = " + y);


   double r = gMin - 1.01 + 2.0/(1+Math.exp(y));

   return r;




}

}//==============================================================================
