import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


//##############################################################################
public class Board{

  int NCEL = 45;
  int NDIM = 7;
  int NDIM2 = 14;

  int lastMove;

  int cluster;

  int NTUPLE,NW,NVAL,LTUPLE;

  int POWER=2;

  Random r = new Random();

  File bLog = new File("bLog.txt");

  private  int[]  x; 

  private int[][] m = {{0,1,2},{0,3,6},{1,4,7},{2,1,0},{2,5,8},{3,4,5},{3,6,12},{4,7,13},{5,4,3},{5,8,14},
                      {6,3,0},{6,7,8}, {6,12,21},{7,4,1},{7,13,22},{8,5,2},{8,7,6},{8,14,23},{9,10,11},{9,18,27},
                      {10,11,12},{10,19,28},{11,10,9},{11,12,13},{11,20,29},{12,6,3},{12,11,10},{12,21,30},{12,13,14},
                      {13,7,4},{13,12,11},{13,14,15},{13,22,31},{14,8,5},{14,13,12},{14,15,16},{14,23,32},
                      {15,14,13},{15,16,17},{15,24,33},{16,15,14},{16,25,34},{17,16,15},{17,26,35},
                      {18,19,20},{19,20,21},{20,19,18},{20,21,22},{21,12,6},{21,22,23},{21,20,19},{21,30,36},
                      {22,13,7},{22,21,20},{22,23,24},{22,31,37},{23,14,8},{23,24,25},{23,22,21},{23,32,38},
                      {24,23,22},{24,25,26},{25,24,23},{26,25,24},{27,18,9},{27,28,29},{28,19,10},{28,29,30},
                      {29,20,11},{29,28,27},{29,30,31},{30,21,12},{30,29,28},{30,31,32},{30,36,39},
                      {31,22,13},{31,30,29},{31,37,40},{31,32,33},{32,31,30},{32,23,14},{32,33,34},{32,38,41},
                      {33,24,15},{33,34,35},{33,32,31},{34,25,16},{34,33,32},{35,26,17},{35,34,33},
                      {36,30,21},{36,37,38},{36,39,42},{37,31,22},{37,40,43},{38,32,23},{38,37,36},{38,41,44},
                      {39,36,30},{39,40,41},{40,37,31},{41,38,32},{41,40,39},{42,39,36},{42,43,44},{43,40,37},
                      {44,41,38},{44,43,42}};
    
    
  
  

/*

          0  1  2
          3  4  5
          6  7  8
09 10 11 12 13 14 15 16 17
18 19 20 21 22 23 24 25 26
27 28 29 30 31 32 33 34 35
         36 37 38
         39 40 41
         42 43 44

*/




  private int[][] tabxy = {{0,2},{0,3},{0,4},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6},{3,0},{3,1},{3,2},
                         {3,3},{3,4},{3,5},{3,6},{4,0},{4,1},{4,2},{4,3},{4,4},{4,5},{4,6},{5,2},{5,3},{5,4},{6,2},{6,3},{6,4}};


  private int[] pagoda = {0,1,0,
                     0,0,0,
                   -1,1,0,1,0,1,-1,
                    0,0,0,0,0,0,0,
                   -1,1,0,1,0,1,-1,
                        0,0,0,
                        0,1,0};

/*

          0  1  2
          3  4  5
          6  7  8
09 10 11 12 13 14 15 16 17
18 19 20 21 22 23 24 25 26
27 28 29 30 31 32 33 34 35
         36 37 38
         39 40 41
         42 43 44

*/

  private int[][] d = {{0,1,2,3,4,5,6,7,8,12,13,14,22}};

  uteis ut;

 int[][] espelho = {{0,17,44,27},{1,26,43,18},{2,35,42,9},{3,16,41,28},{4,25,40,19},{5,34,39,10},{6,15,38,29},
                    {7,24,37,20},{8,33,36,11},{9,2,35,42},{10,5,34,39},{11,8,33,36},{12,14,32,30},{13,23,31,21},
                    {14,32,30,12},{15,38,29,6},{16,41,28,3},{17,44,27,0},{18,1,26,43},{19,4,25,40},{20,7,24,37},
                    {21,13,23,31},{22,22,22,22},{23,31,21,13},{24,37,20,7},{25,40,19,4},{26,43,18,1},{27,0,17,44},
                    {28,3,16,41},{29,6,15,38},{30,12,14,32},{31,21,13,23},{32,30,12,14},{33,36,11,8},{34,39,10,5},
                    {35,42,9,2},{36,11,8,33},{37,20,7,24},{38,29,6,15},{39,10,5,34},{40,19,4,25},{41,28,3,16},
                    {42,9,2,35},{43,18,1,26},{44,27,0,17}};

 

/*

          0  1  2
          3  4  5
          6  7  8
09 10 11 12 13 14 15 16 17
18 19 20 21 22 23 24 25 26
27 28 29 30 31 32 33 34 35
         36 37 38
         39 40 41
         42 43 44

*/

//---------------------------------------------

//---------------------------------------------

//---------------------------------------------

//---------------------------------------------
 public Board(){ 

     x = new int[NCEL];

     ut = new uteis();

     NTUPLE = d.length;

     LTUPLE = d[0].length;

     NVAL = (int)Math.pow(POWER,LTUPLE);

     NW = NTUPLE*NVAL;
     
 }
//----------------------------------------

 public boolean gameOver(){

  boolean termino=false;

  if(contaPegs() == 1)
    termino=true;

  return termino;

 }
//----------------------------------------

//----------------------------------------

//------------------------------------------

 public void printBoard(){


   String[] w = {" ","x"};

   System.out.println("--------------------------"); 
   System.out.println("          | " + w[x[0]] + " | " + w[x[1]] + " | " + w[x[2]] + " |");
   System.out.println("          | " + w[x[3]] + " | " + w[x[4]] + " | " + w[x[5]] + " |");
   System.out.println("          | " + w[x[6]] + " | " + w[x[7]] + " | " + w[x[8]] + " |");
   System.out.println(w[x[9]] + " | " + w[x[10]] + " | " + w[x[11]] + " | " + w[x[12]] + " | " + w[x[13]]+ " | " + w[x[14]]+ " | " + w[x[15]] + " | " + w[x[16]]+ " | " + w[x[17]] + " |");
   System.out.println(w[x[18]] + " | " + w[x[19]] + " | " + w[x[20]] + " | " + w[x[21]] + " | " + w[x[22]]+ " | " + w[x[23]]+ " | " + w[x[24]] + " | " + w[x[25]]+ " | " + w[x[26]] + " |");
   System.out.println(w[x[27]] + " | " + w[x[28]] + " | " + w[x[29]] + " | " + w[x[30]] + " | " + w[x[31]]+ " | " + w[x[32]]+ " | " + w[x[33]] + " | " + w[x[34]]+ " | " + w[x[35]] + " |");
   System.out.println("          | " + w[x[36]] + " | " + w[x[37]] + " | " + w[x[38]] + " |");
   System.out.println("          | " + w[x[39]] + " | " + w[x[40]] + " | " + w[x[41]] + " |");
   System.out.println("          | " + w[x[42]] + " | " + w[x[43]] + " | " + w[x[44]] + " |");
   System.out.println("--------------------------");


}
//--------------------------------------------
public void inicializa() {

    int i;

    try{
       File input = new File("resta1Input.dat");

       Scanner inputFile = new Scanner(input);

       for(i=0;i<NCEL;i++)
          x[i] = inputFile.nextInt();

    }catch(IOException e) {System.out.println("Arquivo nao encontrado");}
    
 }

//--------------------------------------------
int getNtuple(){

   return NTUPLE;
}
//--------------------------------------------
int getLtuple(){

   return LTUPLE;
}
//--------------------------------------------
int getNval(){

   return NVAL;
}
//--------------------------------------------
int getNcel(){

   return NCEL;
}
//--------------------------------------------
int getNw(){

   return NW;
}
//-----------------------------------------------------------

 public ArrayList<Integer> geraCandSimples(){

  ArrayList<Integer> cand = new ArrayList<>();  

  for(int i=0;i<m.length;i++)
      if(x[m[i][0]] == 1 && x[m[i][1]] == 1 && x[m[i][2]] == 0)        
        cand.add(i);       

  return cand;

}

//--------------------------------------------
 public ArrayList<Integer> geraCand(){

  ArrayList<Integer> cand = new ArrayList<>();  

  for(int i=0;i<m.length;i++)
      if(x[m[i][0]] == 1 && x[m[i][1]] == 1 && x[m[i][2]] == 0)        
        cand.add(i);       


  //System.out.println("------------------------------------------");


  if(cand.size() > 0){
    
    double[] d = new double[cand.size()];

    for(int i=0;i<cand.size();i++){
       setMove(cand.get(i));
       d[i] = distCentro();
       //imprimeMove(cand.get(i));
       //System.out.printf("distcentro = (%.1f)\n",d[i]);
       unsetMove(cand.get(i));
    }

    //System.out.println("--------------------antes");
    //ut.printVetor(d);
    //ut.imprime_x(cand);

     for(int k=0;k<cand.size();k++)
        for(int j=1;j<cand.size();j++)
           if(d[j] > d[j-1]){

             double daux = d[j-1];
             d[j-1] = d[j];
             d[j] = daux;

             int c = cand.get(j-1);
             cand.set(j-1,cand.get(j));
             cand.set(j,c);

           }


      //System.out.println("--------------------depois");
      //ut.printVetor(d);
      //ut.imprime_x(cand);
  }

  return cand;
}
//--------------------------------------------
public void swap(double a,double b){

  double c = a;

  a = b;

  b = c;

}
//--------------------------------------------
public void setMove(int i){
 
    x[m[i][0]] = 0;
    x[m[i][1]] = 0;
    x[m[i][2]] = 1;

    lastMove=i;
}
//--------------------------------------------
public void unsetMove(int i){

    x[m[i][0]] = 1;
    x[m[i][1]] = 1;
    x[m[i][2]] = 0;

}
//--------------------------------------------
public int contaPegs(){

 int s=0;

 for(int i=0;i<x.length;i++)
    s += x[i];

 return s;

}
//--------------------------------------------
public int getItem(int i){

  return x[i];
}
//--------------------------------------------
public void setItem(int i,int p){

   x[i] = p;
}

//--------------------------------------------
 public void imprimeMove(int i){

 System.out.printf("mov = (%d)(%d)(%d)\n",m[i][0],m[i][1],m[i][2]);

}
//--------------------------------------------
public int calcPagoda(){

  int s=0;

  for(int i=0;i<x.length;i++)
     if(x[i] == 1)
       s += pagoda[i];

  return s;
}
//--------------------------------------------
public int distCentro(){

   int s=0;

  for(int i=0;i<x.length;i++)
     if(x[i] == 1)
       s += Math.abs(tabxy[i][0] - 3) + Math.abs(tabxy[i][1] - 3);


  return s;
}
//--------------------------------------------
public int dispersao(){

    int s = 0;

    for(int i=0;i<x.length;i++)
    for(int j=i+1;j<x.length;j++)
       if(x[i] == 1 && x[j] == 1)
         s += Math.abs(tabxy[i][0]-tabxy[j][0]) + Math.abs(tabxy[i][1]-tabxy[j][1]);



    return s;

}
//--------------------------------------------
public int cornerPegs(){

   return x[0] + x[2] + x[6] + x[12] + x[20] + x[26] + x[30] + x[32];
}
//--------------------------------------------
public int cornerPegs1(){

   return x[0] + x[1] + x[2] + x[3] + x[4] + x[5] + x[6] + x[13] + x[20] + x[7] + x[14] + x[21]
        + x[12] + x[19] + x[26] + x[11] + x[18] + x[25] + x[27] + x[28] + x[29] + x[30] + x[31] + x[32];
}
//--------------------------------------------
public int centroPegs(){

   return x[8] + x[10] + x[22] + x[24];
}
//--------------------------------------------

public void imprimeLog(){


    try{      

       FileWriter fw = new FileWriter(bLog, true);

       BufferedWriter bw = new BufferedWriter(fw);

       for(int i=0;i<x.length;i++)          
          bw.write(String.valueOf(x[i]) + " ");

       //bw.write(" Freq = " + String.valueOf(freq));

       bw.newLine();
       //bw.write("===============================================");
       //bw.newLine();
       bw.close();

    }catch(IOException e) {System.out.println("Arquivo nao encontrado");}


}
//--------------------------------------------
public void leBlog(Scanner sc) {


       for(int i=0;i<NCEL;i++)
          x[i] = sc.nextInt();


}
//--------------------------------------------
public int peaoIsolado() {

//      00 01 02
//      03 04 05
//06 07 08 09 10 11 12
//13 14 15 16 17 18 19
//20 21 22 23 24 25 26
//      27 28 29
//      30 31 32

   int p=0;

   if(x[0]+x[1]+x[2] == 1 && x[3]+x[4]+x[5] == 0)
     p = 1;

   if(x[6]+x[13]+x[20] == 1 && x[7]+x[14]+x[21] == 0)
     p = 1;

   if(x[12]+x[19]+x[26] == 1 && x[11]+x[18]+x[25] == 0)
     p = 1;

   if(x[12]+x[19]+x[26] == 2 && x[19] == 0 && x[11]+x[18]+x[25] == 0)
     p = 1;


   if(x[30]+x[31]+x[32] == 1 && x[27]+x[28]+x[29] == 0)
     p = 1;

   return p;

}
//-------------------------------------------------------
//--------------------------------------------

//-------------------------------------------------------
public double calcR1(){

   double reward = 0.0;
   
   if(geraCandSimples().size() == 0){
       if(contaPegs() == 1 && x[22] == 1)
          reward = 1;       
       else  
          reward = -1.0;
   
   }

   return reward;
}
//#######################################################

public double calcV(double[] w){
   

    double v = 0;    

    ArrayList<Integer> f  = avaliationFunction1();

    for(int i=0;i<f.size();i++)
          v += w[f.get(i)];
   
 
     //System.out.println("v= " + v);

     v = Math.tanh(v);

    return v;

}
//#############################################
public double calcV1(double[] w){


    double v = 0;    

    double[] f = avaliationFunction();

    for(int i=0;i<NW;i++)
        v += w[i]*f[i];
   
 
      //System.out.println("v= " + v);

      v = Math.tanh(v);

    return v;

}
//#######################################################
public double[] avaliationFunction(){

     double[] f = new double[NW];


     for(int i=0;i<NTUPLE;i++){ 

            int[] tupla = d[i];

            int j = geraIndice(tupla);
            
            int k = i*NVAL + j;

            f[k] = 1.0;

            
            j = geraIndice(getEspelho(tupla));

            k = i*NVAL + j;

            f[k] = 1.0; 

            j = geraIndice(getEspelho1(tupla));

            k = i*NVAL + j;

            f[k] = 1.0; 

            j = geraIndice(getEspelho2(tupla));

            k = i*NVAL + j;

            f[k] = 1.0;
            

     }
       

    return f;

}
//#######################################################

ArrayList<Integer> avaliationFunction1(){

     ArrayList<Integer> f = new ArrayList<>();

     
     for(int i=0;i<NTUPLE;i++){ 

            int[] tupla = d[i];

            int j = geraIndice(tupla);
            
            int k = i*NVAL + j;

            f.add(k);   

            
            //------------------
            j = geraIndice(getEspelho(tupla));

            k = i*NVAL + j;

            f.add(k);    

            //-----------------
            j = geraIndice(getEspelho1(tupla));

            k = i*NVAL + j;

            f.add(k);

            //-----------------
            j = geraIndice(getEspelho2(tupla));

            k = i*NVAL + j;

            f.add(k);
            
            
    }
       

    return f;

}
//#######################################################
public int geraIndice(int[] tupla){
       
    
    int k = 0;

    int kmax = tupla.length;

    for(int i=0;i<kmax;i++){

       k += x[tupla[i]]*Math.pow(POWER,i);

    }

    return k;
}
//#######################################################
int[] getEspelho(int[] tupla){

    int[] espelhado = new int[tupla.length];

    int j=0;

    for(int i=0;i<tupla.length;i++)
       for(int k=0;k<espelho.length;k++)
           if(tupla[i] == espelho[k][0]){
             espelhado[j] = espelho[k][1];
             j += 1;
             break;
           }



   return espelhado;

}
//#######################################################
int[] getEspelho1(int[] tupla){

    int[] espelhado = new int[tupla.length];

    int j=0;

    for(int i=0;i<tupla.length;i++)
       for(int k=0;k<espelho.length;k++)
           if(tupla[i] == espelho[k][0]){
             espelhado[j] = espelho[k][2];
             j += 1;
             break;
           }



   return espelhado;

}
//#######################################################
int[] getEspelho2(int[] tupla){

    int[] espelhado = new int[tupla.length];

    int j=0;

    for(int i=0;i<tupla.length;i++)
       for(int k=0;k<espelho.length;k++)
           if(tupla[i] == espelho[k][0]){
             espelhado[j] = espelho[k][3];
             j += 1;
             break;
           }



   return espelhado;

}

//#######################################################

//#######################################################

//#######################################################

//#######################################################


}//###############################################################################
