/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wiegleb;

import java.util.ArrayList;

/**
 *
 * @author Marco Victorino
 */
public class uteis {
    
    
    double gaussianDecai(int nep,double aleatIn){
       double k = 3000;
       return aleatIn/Math.exp(Math.pow(nep/1000.0,2)/k);
    }
    //-------------------------------------------------------------
    double ExpDecai(int nG,int Nep,double dIn,double dOut){
       return dIn/(Math.exp(nG*Math.log(dIn/(dOut+0.001))/Nep)); 
    }
    //-------------------------------------------------------------
    int argMax(double[] x){
        double max = x[0];
        int choice = 0;
        
        for(int i=1;i<x.length;i++){
            if(x[i] > max){
                max = x[i];
                choice = i;
            }
        }
        
        return choice;
    }
    
    int argMin(double[] x){
        double min = x[0];
        int choice = 0;
        
        for(int i=1;i<x.length;i++){
            if(x[i] < min){
               min = x[i];
               choice = i;
            }
        }
        return choice;
    }
    //-------------------------------------------------------------
    void imprime_x(ArrayList<Integer> x){
        for(int i=0;i<x.size();i++)
            System.out.printf("(%d)",x.get(i));
        
        System.out.println();
    }
    //-------------------------------------------------------------
    void printVetor(double[] x){
        for(int i=0;i<x.length;i++)
            System.out.printf("[%d](%.4f)",i,x[i]);
        
        System.out.println();
    }
}
