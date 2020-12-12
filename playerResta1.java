/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wiegleb;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author fermat
 */
public class playerResta1 {
    
    boolean fGreed;
    
    uteis ut = new uteis();
    
    int play(Board b,double aleat,double[] w){
        
        Random r = new Random();
        
        int choice= -1;
        
        ArrayList<Integer> cand = b.geraCand();        
        
        fGreed=false;
        
        if(cand.size() > 0){
            
            double[] v = new double[cand.size()];
            
            if(r.nextDouble() < aleat){
                choice = cand.get(r.nextInt(cand.size()));  
            }
            else{
                fGreed = true;
                
                for(int i=0;i<cand.size();i++){
                    int mov = cand.get(i);
                    b.setMove(mov);
                    double reward = b.calcR1();
                    if(reward != 0.0)
                        v[i]=reward;
                    else
                        v[i]= b.calcV(w);
                    
                    b.unSetMove(mov);
                   
                }               
                
                choice = cand.get(ut.argMax(v));
            }
        }
        
        return choice;
    }
    //--------------------------------------------------
    boolean getFgreed(){
       return fGreed; 
    }
}
