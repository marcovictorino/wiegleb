/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wiegleb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Marco Victorino
 */
public class Board {
    final int COL=9;
    
    final int NCEL=45;
    final int NTUPLE,NW,NVAL,LTUPLE;
    final int POWER=2;
    
    
    private  int[]  x;
    
    private final int[][] m = {{0,1,2},{0,3,6},{1,4,7},{2,1,0},{2,5,8},{3,4,5},{3,6,12},{4,7,13},{5,4,3},{5,8,14},
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
    
    private final int[][] d = {{0,1,2,3,4,5,6,7,8,12,13,14,22}};
    
    private final int[][] espelho = {{0,17,44,27},{1,26,43,18},{2,35,42,9},{3,16,41,28},{4,25,40,19},{5,34,39,10},{6,15,38,29},
                    {7,24,37,20},{8,33,36,11},{9,2,35,42},{10,5,34,39},{11,8,33,36},{12,14,32,30},{13,23,31,21},
                    {14,32,30,12},{15,38,29,6},{16,41,28,3},{17,44,27,0},{18,1,26,43},{19,4,25,40},{20,7,24,37},
                    {21,13,23,31},{22,22,22,22},{23,31,21,13},{24,37,20,7},{25,40,19,4},{26,43,18,1},{27,0,17,44},
                    {28,3,16,41},{29,6,15,38},{30,12,14,32},{31,21,13,23},{32,30,12,14},{33,36,11,8},{34,39,10,5},
                    {35,42,9,2},{36,11,8,33},{37,20,7,24},{38,29,6,15},{39,10,5,34},{40,19,4,25},{41,28,3,16},
                    {42,9,2,35},{43,18,1,26},{44,27,0,17}};
    
    
    public Board(){
        x = new int[NCEL];
        NTUPLE = d.length;
        LTUPLE = d[0].length;
        NVAL = (int)Math.pow(POWER,LTUPLE);
        NW = NTUPLE*NVAL;
    }
    
    void printBoard(){
        String[] w = {" ","x"};
        
        System.out.println("-------------------------------------");
        System.out.println("          | " + w[x[0]] + " | " + w[x[1]] + " | " + w[x[2]] + " |");
        System.out.println("          | " + w[x[3]] + " | " + w[x[4]] + " | " + w[x[5]] + " |");
        System.out.println("          | " + w[x[6]] + " | " + w[x[7]] + " | " + w[x[8]] + " |");
        System.out.println(w[x[9]] + " | " + w[x[10]] + " | " + w[x[11]] + " | " + w[x[12]] + " | " + w[x[13]]+ " | " + w[x[14]]+ " | " + w[x[15]] + " | " + w[x[16]]+ " | " + w[x[17]] + " |");
        System.out.println(w[x[18]] + " | " + w[x[19]] + " | " + w[x[20]] + " | " + w[x[21]] + " | " + w[x[22]]+ " | " + w[x[23]]+ " | " + w[x[24]] + " | " + w[x[25]]+ " | " + w[x[26]] + " |");
        System.out.println(w[x[27]] + " | " + w[x[28]] + " | " + w[x[29]] + " | " + w[x[30]] + " | " + w[x[31]]+ " | " + w[x[32]]+ " | " + w[x[33]] + " | " + w[x[34]]+ " | " + w[x[35]] + " |");
        System.out.println("          | " + w[x[36]] + " | " + w[x[37]] + " | " + w[x[38]] + " |");
        System.out.println("          | " + w[x[39]] + " | " + w[x[40]] + " | " + w[x[41]] + " |");
        System.out.println("          | " + w[x[42]] + " | " + w[x[43]] + " | " + w[x[44]] + " |");
        System.out.println("-------------------------------------");
    }
    
    String getX(int i){
        
        String[] w = {".","o"};
        
        return w[x[i]];
        
    }
    
    int[] getMov(int i){
        return m[i];
    }
    //--------------------------------------------------------------------------
    void inicializa(){
        
        try{
            File input = new File("resta1Input.dat");
            Scanner inputFile = new Scanner(input);
            
            for(int i=0;i<NCEL;i++)
             x[i] = inputFile.nextInt();
            
        }catch(IOException e) {System.out.println("Arquivo nao encontrado");}
    }
    
    int getNtuple(){
        return NTUPLE;
    }
    int getLtuple(){
        return LTUPLE;
    }
    int getNw(){
        return NW;        
    }
    int getNcel(){
        return NCEL;
    }
    int getCol(){
        return COL;
    }
    int getItem(int k){
        return x[k];
    }
    void setItem(int i,int k){
        x[i] = k;
    }
    ArrayList<Integer> geraCand(){
        ArrayList<Integer> cand = new ArrayList<>();
        
        for(int i=0;i<m.length;i++)
           if(x[m[i][0]] == 1 && x[m[i][1]] == 1 && x[m[i][2]] == 0) 
             cand.add(i);
        
        return cand;
        
    }
    void setMove(int i){
        x[m[i][0]] = 0;
        x[m[i][1]] = 0;
        x[m[i][2]] = 1;
    }
    void unSetMove(int i){
        x[m[i][0]] = 1;
        x[m[i][1]] = 1;
        x[m[i][2]] = 0;
        
    }
    
    double calcR1(){
        
        double reward = 0.0;
        
        if(geraCand().size() == 0){
            if(contaPegs() == 1 && x[22] == 1)
                reward = 1;  
            else
                reward = -1;  
        }
        
        return reward;
    }
    
    int contaPegs(){
        int s=0;
        for(int i=0;i<x.length;i++)
            s += x[i];
        return s; 
    }
    
    double calcV(double[] w){
        
        double v=0;
        
        ArrayList<Integer> f  = avaliationFunction1();
        
        for(int i=0;i<f.size();i++)
            v += w[f.get(i)];
        
        v = Math.tanh(v);
        
        return v;
    }
    
    ArrayList<Integer> avaliationFunction1(){
      
        ArrayList<Integer> f = new ArrayList<>();
        
        for(int i=0;i<NTUPLE;i++){ 
            int[] tupla = d[i];
            int j = geraIndice(tupla);
            int k = i*NVAL + j;
            f.add(k); 
            
            j = geraIndice(getEspelho(tupla,1));
            k = i*NVAL + j;
            f.add(k);
            
            j = geraIndice(getEspelho(tupla,2));
            k = i*NVAL + j;
            f.add(k);            
            
            j = geraIndice(getEspelho(tupla,3));
            k = i*NVAL + j;
            f.add(k);

        }
        
        return f;
    }
    //-------------------------------------------
    public int geraIndice(int[] tupla){
        
        int k=0;
        
        for(int i=0;i<LTUPLE;i++){            
            k += x[tupla[i]]*Math.pow(POWER,i);
        }
        return k;
    }
    //-------------------------------------------
    int[] getEspelho(int[] tupla,int indice){
        
        int[] espelhado = new int[tupla.length];
        
        int j=0;
        for(int i=0;i<tupla.length;i++)
            for (int[] espelho1 : espelho) {
                if (tupla[i] == espelho1[0]) {
                    espelhado[j] = espelho1[indice];
                    j += 1;
                    break;
                }
            }
        
        return espelhado;
    }
    //-------------------------------------------
    double[] avaliationFunction(){
        
        double[] f = new double[NW];
        
        for(int i=0;i<NTUPLE;i++){
            int[] tupla = d[i];
            int j = geraIndice(tupla);
            int k = i*NVAL + j;
            f[k] = 1.0;
            
            j = geraIndice(getEspelho(tupla,1));
            k = i*NVAL + j;
            f[k] = 1.0;
            
            j = geraIndice(getEspelho(tupla,2));
            k = i*NVAL + j;
            f[k] = 1.0;      
            
            j = geraIndice(getEspelho(tupla,3));
            k = i*NVAL + j;
            f[k] = 1.0;
            
        }
        return f;
    }
    //-------------------------------------------
    void imprimeMove(int i){
        
        System.out.printf("move -> (%d,%d,%d)\n",m[i][0],m[i][1],m[i][2]);
        
    }
}
