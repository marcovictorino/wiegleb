/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wiegleb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author fermat
 */
public class wiegleb implements ActionListener{
    
  public static void main(String[] args){
      wiegleb gui = new wiegleb();
  }
  
  Board b0;
  
  FileReader fw;
  
  BufferedReader reader;
  
  File parametros,corrida;
  
  uteis ut;
  
  graficos g;
  
  double  aleatIn,aleatFim,alfaIn,alfaFim,LAMBDA,GAMMA;
  
  DecimalFormat df,df1;
  
  int NEP,NW;
  
  long start;
  
  //-----------------------------------
  private final JFrame frame;
  private final JPanel panel;
  private final JPanel panelGraficos;
  private final JPanel panelResultados;
  
  
  private ChartPanel gPanel,gPanel2;
  
  JFreeChart chart,chart2;
  
  private final JLabel lblNtuple;
  private final JLabel lblLtuple;
  private final JLabel lblNw;
  private final JLabel lblAleatIn;
  private final JLabel lblAleatFim;  
  private final JLabel lblNep;
  private final JLabel lblAlfaIn;
  private final JLabel lblAlfaFim;
  private final JLabel lblLambda;
  private final JLabel lblGamma;
  
  private final JCheckBox chkSalvar;
  private final JCheckBox chkLer;
  private final JCheckBox chkUpdate;
  
  private final JTextArea txtResultados;
  
  
  private final JButton btnExecutar;
  
  //------------------------------------------------
  wiegleb(){
      
      frame = new JFrame("Peg solitaire w/ Ntuple and Eligibility Trace");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(900,600);
      
      panel = new JPanel(); 
      panel.setLayout(new GridLayout(5,3));
      panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      
      this.b0 = new Board();
      
      Border border = LineBorder.createGrayLineBorder();
      
      lblNtuple = new JLabel("Ntuple = " + Integer.toString(b0.getNtuple()));
      
      lblNtuple.setBorder(border);
      
      lblLtuple = new JLabel("Ltuple = " + Integer.toString(b0.getLtuple()));
      
      lblLtuple.setBorder(border);
      
      NW = b0.getNw();
      
      lblNw = new JLabel("N. weights = " + Integer.toString(NW));
      
      lblNw.setBorder(border);
      
      parametros = new File("parametros.dat");
         
      try{
          
          fw = new FileReader(parametros);
          
          reader = new BufferedReader(fw);
          
          String currentLine = reader.readLine();
          
          aleatIn = Double.parseDouble(currentLine);
          
          currentLine = reader.readLine();
          
          NEP = Integer.parseInt(currentLine);
          
          currentLine = reader.readLine();
          
          alfaIn = Double.parseDouble(currentLine);
          
          currentLine = reader.readLine();
          
          alfaFim = Double.parseDouble(currentLine);
          
          currentLine = reader.readLine();
          
          LAMBDA = Double.parseDouble(currentLine);
          
          
          currentLine = reader.readLine();
          
          GAMMA = Double.parseDouble(currentLine);
          
          reader.close();
          
      }catch(IOException e) {System.out.println("Arquivo nao encontrado");}
      
            
      
      lblAleatIn = new JLabel("Aleat Inicial = " + Double.toString(aleatIn));
        
      lblAleatIn.setBorder(border);
      
      lblNep = new JLabel("N.episodios = " + Integer.toString(NEP));
      
      lblNep.setBorder(border);
      
      ut = new uteis();
      
      aleatFim = ut.gaussianDecai(NEP,aleatIn);
      
      df = new DecimalFormat("0.####");
      
      lblAleatFim = new JLabel("Aleat Final = " + df.format(aleatFim));
      
      lblAleatFim.setBorder(border);
      
      lblAlfaIn = new JLabel("Alfa Inicial = " + df.format(alfaIn));
      
      lblAlfaIn.setBorder(border);
      
      lblAlfaFim = new JLabel("Alfa Final = " + df.format(alfaFim));
      
      lblAlfaFim.setBorder(border);
      
      
      lblLambda = new JLabel("Lambda = " + df.format(LAMBDA));
      
      lblLambda.setBorder(border);
      
      lblGamma = new JLabel("Gamma = " + df.format(GAMMA));
      
      lblGamma.setBorder(border);
      
      chkSalvar = new JCheckBox("Salvar weights");
      chkLer = new JCheckBox("Ler weights");
      chkUpdate = new JCheckBox("Update weights");
      
      chkSalvar.setSelected(true);
      chkLer.setSelected(false);
      chkUpdate.setSelected(true);
      
      
      
      panel.add(lblNtuple);      
      panel.add(lblLtuple);
      panel.add(lblNw);
      panel.add(lblNep);
      panel.add(lblAleatIn);
      panel.add(lblAleatFim);
      panel.add(lblAlfaIn);
      panel.add(lblAlfaFim);
      panel.add(lblLambda);
      panel.add(lblGamma);
     
      panel.add(chkSalvar);
      panel.add(chkLer);
      panel.add(chkUpdate);
      
      btnExecutar = new JButton("Executar");
      btnExecutar.addActionListener(this);

      panel.add(btnExecutar);

      //-------------------------------------------      
      g = new graficos();
      
      gPanel = criaGrafico("Scores","game/1000","scores");
      
      gPanel2 = criaGrafico2("Explor. rate","game/1000","Explor. rate");
      
      panelGraficos = new JPanel(); 
      panelGraficos.setLayout(new GridLayout(1,2));     
      panelGraficos.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      
      panelGraficos.add(gPanel);
      panelGraficos.add(gPanel2);
      
      
      panelResultados = new JPanel(); 
      panelResultados.setLayout(new GridLayout(1,1));
      panelResultados.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      
      txtResultados = new JTextArea(7,30);
      JScrollPane scrollPane = new JScrollPane(txtResultados);
      txtResultados.setEditable(false);
      txtResultados.setLineWrap(true);
      txtResultados.setBorder(border);
      
      
      

      
      panelResultados.add(txtResultados);
      
      //panelResultados.add(btnExecutar);
      
      frame.getContentPane().add(BorderLayout.NORTH, panel);
      frame.getContentPane().add(BorderLayout.CENTER,panelGraficos);
      frame.getContentPane().add(BorderLayout.SOUTH,panelResultados);
      
      
      frame.setVisible(true);
  }
  //------------------------------------------------
  private ChartPanel criaGrafico(String titulo,String titH,String titV){
      
      
      
      double[] x = new double[0];
      double[] y = new double[0];    
     
      
      double rangeIn=0.0;
      double rangeOut=1.0;
      
      XYDataset dataset = g.createDataset(x,y,titulo);
      
      chart = g.createChart(dataset,titulo,titH,titV,rangeIn,rangeOut);
      
      gPanel = new ChartPanel(chart);
      
      gPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      
      gPanel.setBackground(Color.white);
      
      return gPanel;
  }
    //------------------------------------------------
  private ChartPanel criaGrafico2(String titulo,String titH,String titV){
      
      
      
      double[] x = new double[0];
      double[] y = new double[0];    
     
      
      double rangeIn=0.0;
      double rangeOut=0.1;
      
      XYDataset dataset = g.createDataset(x,y,titulo);
      
      chart2 = g.createChart(dataset,titulo,titH,titV,rangeIn,rangeOut);
      
      gPanel2 = new ChartPanel(chart2);
      
      gPanel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      
      gPanel2.setBackground(Color.white);
      
      return gPanel2;
  }

  
  //------------------------------------------------
  @Override
  public void actionPerformed(ActionEvent obj){
      
      if (obj.getSource()==btnExecutar){
          try{
              
              start =  System.currentTimeMillis();
              
              new Thread(t1).start();
              
          }catch(Exception ex){System.out.println("nao executado");}  
      }
  }
  //------------------------------------------------
  
  public  Runnable t1 = new Runnable() {
      @Override
      public void run(){
         
          try{
              
            ArrayList<Integer> rota = new ArrayList<>();
            
            ArrayList<Integer> rotaOtima = new ArrayList<>();
            
            ArrayList<Integer> movAleat = new ArrayList<>();
            
            ArrayList<Double> histnG = new ArrayList<>();
            
            ArrayList<Double> histScore = new ArrayList<>();
            
            ArrayList<Double> histAleat = new ArrayList<>();
            
            playerResta1 p1 = new playerResta1();
            
            tdLearning td = new tdLearning(NW,GAMMA,LAMBDA);
            
            double score1=0,score2=0,scoreBack=0;
            int intervBack=0;
            
            if(chkLer.isSelected())
                td.readingWeights();
            
            corrida = new File("corrida.log");
            
            double score=0;
            
            //----------------------------------------------
            for(int nG=0;nG<NEP;nG++){ 
                 
                Board b = new Board();
                 
                b.inicializa();
                 
                rota.clear();movAleat.clear();   
                
                 
                double aleat = ut.gaussianDecai(nG,aleatIn);
                
                double alfa = ut.ExpDecai(nG,NEP,alfaIn,alfaFim);
                
                while(true){
                    
                    Board bOld = new Board();
                    
                    for(int i=0;i<b.getNcel();i++)
                       bOld.setItem(i,b.getItem(i));
                    
                    int choice = p1.play(b,aleat,td.getW());
                    
                    b.setMove(choice);
                    
                    rota.add(choice); 
                    
                    if(p1.getFgreed() == false)
                        movAleat.add(choice);
                    
                    if(chkUpdate.isSelected()){
                        if(p1.getFgreed() || b.geraCand().isEmpty())
                            td.updateWgame(bOld,b,alfa);
                    }
                    
                    if(b.geraCand().isEmpty())
                        break;
                }
                
                if(b.calcR1() == 1){
                    score1 +=1;
                    rotaOtima.clear();
                    for(int i=0;i<rota.size();i++)
                        rotaOtima.add(rota.get(i));
                }
                else
                    score2+=1;
                
                long end  = System.currentTimeMillis();
                
                var tempo = (end-start)/1000;
                
                if(end/1000 % 60 == 0){
                    
                    double deltaScore = score1 - scoreBack;
                    
                    int interv = nG - intervBack;
                    
                    double ss = score1+score2;
                    
                    
                    
                    System.out.printf("\n\n================================ Episodio: (%d)\n",nG);
                    
                    b.printBoard();
                    
                    if(rotaOtima.size()  > 0){
                        System.out.println("Puzzle solved");
                        ut.imprime_x(rotaOtima);
                    }
                    
                    System.out.println("----------------------------------------------------------------------:");
                    System.out.println("rota atual:");
                    imprime_x(rota,movAleat);
                    
                    
                    salvarResultados(corrida,nG,b,rotaOtima);
                    
                    
                    int wT = td.freqMais1()+td.freqMenos1();
                    
                    scoreBack = score1;intervBack = nG;
                    
                    score = deltaScore/interv;                        
                            
                    double wAtivados1 = (double) wT/NW;
                    
                    double q = (double)nG/1000;
                    
                    histnG.add(q);
                    histScore.add(score);
                    histAleat.add(aleat);
                    
                    
                    atualizaGrafico(histnG,histScore,chart,gPanel,"Scores");               
                    
                    
                    atualizaGrafico(histnG,histAleat,chart2,gPanel2,"Explor. rate");                  
                    
                    
                    df1 = new DecimalFormat("0.##");
                    
                    NumberFormat perc = NumberFormat.getPercentInstance();
                    
                    perc.setMinimumFractionDigits(2);
                    
                    
                    txtResultados.setText("N.games = " + Integer.toString(nG) + "   Tempo = " + df1.format(tempo/3600.0)+ "hs   " + Long.toString(tempo/60) + "min  " +
                            " Scores: " + perc.format(score1/ss) + "|" + perc.format(score2/ss));
                    
                                   
                    txtResultados.append("\nAlfa = " + df.format(alfa) + "    Explor. = " + df.format(aleat));
                    
                    txtResultados.append("\nWeights Min/Max -> (" + df.format(td.wMin()) + ")(" +  df.format(td.wMax())  + ")" + " Acertos Totais = "
                            + score1 + " Score/min = " + perc.format(score));
                    
                    double freqMais = (double)td.freqMais1()/NW;
                    double freqMenos = (double)td.freqMenos1()/NW;
                    
                    txtResultados.append("\nW. ativados = " + perc.format(wAtivados1) + "     w+ = " + perc.format(freqMais) + "   w- = " + perc.format(freqMenos));
                    
                    
                    Map mapaInicial = custoInicial(td);
                    
                    txtResultados.append("\nvalores iniciais:\n");
                    
                    
                    for(Object key : mapaInicial.keySet())
                        txtResultados.append("(" + String.valueOf(key) + "," + df.format(mapaInicial.get(key)) + ")");
                    
                    try { Thread.sleep (1000); } catch (InterruptedException ex) {ex.printStackTrace();}
                    
                }
                
                if(score >= 0.80)
                    break;
            }
            
            if(chkSalvar.isSelected())
              td.savingWeights();
            
            JOptionPane.showMessageDialog(frame, "****** OK *****");
            
          }catch (Exception e){System.out.println("nao iniciado");}
      }
  };
  
  //------------------------------------------------------
  
  void imprime_x(ArrayList<Integer> x,ArrayList<Integer> y){
      
      for(int i=0;i<x.size();i++){
          if(Collections.frequency(y,x.get(i))>0)
              System.out.printf("-%d-",x.get(i));
          else
              System.out.printf("(%d)",x.get(i));
      }
      
  }
  //------------------------------------------------------
  void atualizaGrafico(ArrayList<Double> x,ArrayList<Double> y,JFreeChart c,JPanel gP,String titulo){
      
      double[] x0 = new double[x.size()];
      double[] z = new double[y.size()];
      
      for(int i=0;i<x0.length;i++){
          x0[i] = x.get(i);
          z[i] = y.get(i);
      }
      
      XYDataset dataset = g.createDataset(x0,z,titulo);
      
      ((XYPlot)c.getPlot()).setDataset(dataset);
      
      gP.revalidate();
      
      gP.repaint();
      
  }
  
  //------------------------------------------------------
  Map custoInicial(tdLearning td){
      
      Map<Integer,Double> mapaInicial = new HashMap<>();
      
      Board b = new Board();
      
      b.inicializa();
      
      ArrayList<Integer> cand = b.geraCand();
      
      double[] v = new double[cand.size()];
      
      for(int i=0;i<cand.size();i++){
          int mov = cand.get(i);
          b.setMove(mov);
          v[i]= b.calcV(td.getW());
          b.unSetMove(mov);
          
          mapaInicial.put(cand.get(i),v[i]);
          
          
      }
     
      return mapaInicial;
      
  }
  
  void salvarResultados(File corrida,int nG,Board b,ArrayList<Integer> rota){
      try{
          
          FileWriter fw = new FileWriter(corrida, true);
          
          try (BufferedWriter bw = new BufferedWriter(fw)) {
              bw.write("================================================================ episodio: " + String.valueOf(nG));
              bw.newLine();
              bw.write("----------------------------------");
              bw.newLine();
              bw.write("   " + b.getX(0)  + b.getX(1) + b.getX(2));
              bw.newLine();
              bw.write("   " + b.getX(3)  + b.getX(4) +  b.getX(5));
              bw.newLine();
              bw.write("   " + b.getX(6) +  b.getX(7) +  b.getX(8));
              bw.newLine();
              bw.write(b.getX(9) +  b.getX(10)  + b.getX(11)  + b.getX(12)  + b.getX(13)+ b.getX(14)+  b.getX(15) +  b.getX(16) +b.getX(17));
              bw.newLine();
              bw.write(b.getX(18) +  b.getX(19)  + b.getX(20)  + b.getX(21)  + b.getX(22) + b.getX(23) + b.getX(24) + b.getX(25)+  b.getX(26));
              bw.newLine();
              bw.write(b.getX(27)  + b.getX(28)  + b.getX(29)  + b.getX(30)  + b.getX(31) + b.getX(32) + b.getX(33) + b.getX(34)+ b.getX(35));
              bw.newLine();
              bw.write("   " + b.getX(36)  + b.getX(37) +  b.getX(38));
              bw.newLine();
              bw.write("   " + b.getX(39) +  b.getX(40) +  b.getX(41));
              bw.newLine();
              bw.write("   " + b.getX(42) +  b.getX(43) +  b.getX(44));
              bw.newLine();
              bw.write("----------------------------------");
              bw.newLine();
              
              
              if(rota.size() > 0) {
                  bw.write("rota Otima:");
                  bw.newLine();
                          
                  for(int i=0;i<rota.size();i++)
                      bw.write("(" + String.valueOf(rota.get(i)) + ")");
                  
                  bw.newLine();
                  
              }
              
              
          }
      }catch(IOException e) {System.out.println("Arquivo nao encontrado");}
  }
}


 
