/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wiegleb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Marco Victorino
 */
public class tdLearning {
    
    double [] w;
    
    double [] eligib;
    
    int NW;
    
    double GAMMA,LAMBDA;
    
    File arq;
    
    uteis ut;
    
    public tdLearning(int nw,double g,double l){
        
        NW=nw;
        GAMMA=g;
        LAMBDA=l;
        
        w =  new double[nw];
        
        eligib = new double[nw];
        
        arq=new File("weight.dat");
        
        ut = new uteis();
    }
    //------------------------------------------------
    public double[] getW(){
        return w;
    }
    //------------------------------------------------
    public  void updateWgame(Board penultimoB,Board ultimoB,double alfa){
        
        double vOld = penultimoB.calcV(w);
        double vl = ultimoB.calcV(w);
        
        double d = 0.0;
        
        if(ultimoB.geraCand().isEmpty())
            d = ultimoB.calcR1() - vOld;
        else
            d = GAMMA*vl - vOld;
        
        double[] f = penultimoB.avaliationFunction();
        
        for(int i=0;i<NW;i++){
            if(f[i] == 0)
                eligib[i] = GAMMA*LAMBDA*eligib[i];
            else
                eligib[i] = (1-vOld*vOld)*f[i];
            
            w[i] = w[i] + alfa*d*eligib[i];
        }
    }
    //------------------------------------------------
    public  int freqMais1(){
        int s=0;
        for(int i=0;i<w.length;i++)
            if(w[i] > 0.0)
                s+=1;
        
        return s;
    }
    //------------------------------------------------
    public  int freqMenos1(){
        int s=0;
        for(int i=0;i<w.length;i++)
            if(w[i] < 0.0)
                s+=1;
        
        return s;
    }
    //------------------------------------------------
    void savingWeights(){
        
        try{
            
            FileWriter fw = new FileWriter(arq, true);
            
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for(int i=0;i<NW;i++){
                    bw.write(String.valueOf(w[i]));
                    bw.newLine();
                }
            }
            
        }catch(IOException e) {System.out.println("Arquivo nao encontrado");}
    }
    
    //------------------------------------------------
    void readingWeights(){
        
        try{
            FileReader fw = new FileReader(arq);
            
            try (BufferedReader reader = new BufferedReader(fw)) {
                String currentLine;
                
                for(int i=0;i<NW;i++){
                    currentLine = reader.readLine();
                    w[i] = Double.parseDouble(currentLine);
                }
            }
            
        }catch(IOException e) {System.out.println("Arquivo nao encontrado");}
        
    }
    //------------------------------------------------
    public double wMin(){
        return w[ut.argMin(w)];
    }
    
    public double wMax(){
        return w[ut.argMax(w)];
    }
}
