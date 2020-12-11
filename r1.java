import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.text.NumberFormat;
import java.text.DecimalFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities; 

// resta1 com TD learning + Ntuple + Incremental Delta Bar + eligib + sigmodal decay

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;

import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class r1 implements ActionListener{

  
//#############################################################
  public static void main(String[] args){

           r1 gui = new r1();
    
   }
   //----------------------------------------------------------

    uteis ut = new uteis();

    double score = 0;

    Board b00 = new Board();

    long start;

    final int NCEL = b00.getNcel();

    int nreplay=0;

    final int nTuple = b00.getNtuple();
    final int lTuple = b00.getLtuple();
    final int nVal = b00.getNval();
    final int nWeights = b00.getNw();

    final int Nep = 120000;                
    

    final double aleatInicial = 0.1;
    final double aleatFinal = ut.gaussianDecai(Nep,aleatInicial);

    final double alphain = 0.005;
    final double alphafim = 0.002;

    final double Lambda = 0.8;
    final double Gamma = 0.9;

   
   
    
    //--------------------------------------------------------------------

    //--------------------------------------------------------------------
    ArrayList<Double> yy = new ArrayList<>();
    ArrayList<Double> yy1 = new ArrayList<>();
    ArrayList<Double> xx = new ArrayList<>();

    JFreeChart chart,chart2;
    //--------------------------------------------------------------------
    private JFrame frame;
    private JPanel panel;
    private JPanel panelResultados;
    private JPanel panelGraficos;

    private ChartPanel gPanel,gPanel2;

    private JButton plotar;
    private JButton executar;
    private JButton tdvsmcts; 
    private JButton guardaWeight;
 
    private JTextField ntuple;
    private JTextField ltuple;
    private JTextField nep;
    private JTextField nw;
    private JTextField alphaIn;
    private JTextField alphaFim;

    private JTextField resultados;
    private JTextField resultados02;
    private JTextField resultados03;
    private JTextField txtResultados04;

    private JTextField lambda;
    private JTextField gamma;
    
    private JTextField step;
    private JTextField aleatIn;
    private JTextField aleatFim;

    private JCheckBox salvarW; 
    private JCheckBox lerW; 
    private JCheckBox updateW;

    //--------------------------------------------------------------------
    public r1(){
 
       DecimalFormat df = new DecimalFormat("0.####");

       frame = new JFrame("Resta1 com Ntuple e Eligib Trace");

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       frame.setSize(900,600);

       //------------------------------------------------------
        panel = new JPanel(); 
        panel.setLayout(new GridLayout(7,4));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabel label1 = new JLabel("Ntuple");

        ntuple = new JTextField(Integer.toString(nTuple),3); 
        ntuple.setEditable(false);

        JLabel label2 = new JLabel("Ltuple");

        ltuple = new JTextField(Integer.toString(lTuple),2);
        ltuple.setEditable(false);

        JLabel label3 = new JLabel("Nepisodios");

        nep = new JTextField(Integer.toString(Nep),5);
        nep.setEditable(false);

        JLabel label4 = new JLabel("N.weights");

        nw = new JTextField(Integer.toString(nWeights),5);

        nw.setEditable(false);

        JLabel label5 = new JLabel("AlphaIn");
        label5.setForeground(Color.red);

        JLabel label12 = new JLabel("AlphaFim");
        label12.setForeground(Color.red);

        alphaIn = new JTextField(Double.toString(alphain),4);
        alphaIn.setForeground(Color.RED);
        alphaIn.setEditable(false);

        alphaFim = new JTextField(Double.toString(alphafim),4);
        alphaFim.setForeground(Color.RED);
        alphaFim.setEditable(false);

        JLabel label6 = new JLabel("Lambda");
        JLabel label7 = new JLabel("Gamma");
       
        
        JLabel label10 = new JLabel("aleat Inicial");
        JLabel label11 = new JLabel("aleat Final");


        lambda = new JTextField(Double.toString(Lambda),3);
        lambda.setEditable(false);

        gamma = new JTextField(Double.toString(Gamma),3);
        gamma.setEditable(false);

        aleatIn = new JTextField(Double.toString(aleatInicial),3);
        aleatIn.setEditable(false);

        //aleatFim = new JTextField(Double.toString(aleatFinal),3);

        aleatFim = new JTextField(df.format(aleatFinal));

        aleatFim.setEditable(false);

        panel.add(label1); 
        panel.add(ntuple);

        panel.add(label2); 
        panel.add(ltuple);

        panel.add(label4); 
        panel.add(nw); 

        panel.add(label3); 
        panel.add(nep);

        panel.add(label10); 
        panel.add(aleatIn); 

        panel.add(label11); 
        panel.add(aleatFim); 

        //panel.add(label9); 
        //panel.add(step); 

        panel.add(label5); 
        panel.add(alphaIn);
 
        panel.add(label12); 
        panel.add(alphaFim);

        panel.add(label6); 
        panel.add(lambda); 

        panel.add(label7); 
        panel.add(gamma); 

        //panel.add(label8); 
        

        //----------------------------------------


        executar = new JButton("Executar");

        executar.addActionListener(this);


        //----------------------------------------
        panelResultados = new JPanel(); 
        panelResultados.setLayout(new GridLayout(3,3));

        salvarW = new JCheckBox("Salvar weights");
        salvarW.addActionListener(this);

        lerW = new JCheckBox("Ler weights");
        lerW.addActionListener(this);

        updateW = new JCheckBox("Update W");
        updateW.addActionListener(this);

        salvarW.setSelected(false);
        lerW.setSelected(true);
        updateW.setSelected(false);

        resultados = new JTextField();
        resultados.setEditable(false);

        resultados02 = new JTextField();
        resultados02.setEditable(false);

        resultados03 = new JTextField();
        resultados03.setEditable(false);

        txtResultados04 = new JTextField();
        txtResultados04.setEditable(false);

        panel.add(salvarW);
        panel.add(lerW);
        panel.add(updateW);

        panelResultados.add(resultados);
        panelResultados.add(resultados02);
        panelResultados.add(resultados03);
        panelResultados.add(txtResultados04);

        panelResultados.add(executar);

        gPanel = criaGrafico();
        gPanel2 = criaGrafico2();

        panelGraficos = new JPanel();
        panelGraficos.setLayout(new GridLayout(1,2));
        panelGraficos.add(gPanel);
        panelGraficos.add(gPanel2);
        //----------------------------------------

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER,panelGraficos);
        frame.getContentPane().add(BorderLayout.SOUTH, panelResultados);

        frame.setVisible(true);
   }//---------------------------------------------------------

    //##############################################################

     public void actionPerformed(ActionEvent obj){
         

         //############################################################################
         if (obj.getSource()==executar){

            try{

               start =  System.currentTimeMillis();

               new Thread(t1).start();


            }catch(Exception ex){
              System.out.println("nao executado");
            }  

        }        

     }//-----------------------------------


//############################################################################

//#################################################

//#################################################
public  void avaliacao(tdLearning td,ArrayList<Integer> rota){


   Board b = new Board();

   b.inicializa();

   System.out.println("=========================== AVALIACAO ========================================");

   b.printBoard();

   for(int i=0;i<rota.size();i++){

       Board bOld = new Board();

       for(int j=0;j<NCEL;j++)
          bOld.setItem(j,b.getItem(j));

       int mov = rota.get(i);

       b.setMove(mov);

       System.out.println("---------------------------------------------------");

       b.imprimeMove(mov);

       b.printBoard();       

       System.out.printf("v = (%.4f)\n",b.calcV(td.getW()));

       //td.updateWgame(bOld,b);
      
   }

  
}
//#################################################
public double[] custoInicial(tdLearning td){


   Board b = new Board();

   b.inicializa();

   //b.printBoard();

   ArrayList<Integer> cand = b.geraCandSimples();


   double[] v = new double[cand.size()];


   for(int i=0;i<cand.size();i++){                                     

      int mov = cand.get(i);

      b.setMove(mov);  

      v[i]= b.calcV(td.getW());              

      b.unsetMove(mov);

   }


   ut.imprime_x(cand);
   
   return v;

} 
//#######################################################
XYDataset createDataset(double[] x,double[] y1) {

        XYSeries series = new XYSeries("score 1");

        for(int i=0;i<x.length;i++)
           series.add(x[i], y1[i]);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        
    

        return dataset;
}
//#######################################################
XYDataset createDataset2(double[] x,double[] y1) {

        XYSeries series = new XYSeries("exploration rate");

        for(int i=0;i<x.length;i++)
           series.add(x[i], y1[i]);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

    

        return dataset;
}
//#######################################################
ChartPanel criaGrafico(){

    double[] y = new double[0];
    double[] y1 = new double[0];


   XYDataset dataset = createDataset(y,y1);

   chart = createChart(dataset);

   gPanel = new ChartPanel(chart);

   gPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

   gPanel.setBackground(Color.white);

   return gPanel;


}
//#######################################################
ChartPanel criaGrafico2(){

    double[] y = new double[0];
    double[] y1 = new double[0];
    //double[] y2 = new double[0];

   XYDataset dataset = createDataset2(y,y1);

   chart2 = createChart2(dataset);

   gPanel2 = new ChartPanel(chart2);

   gPanel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

   gPanel2.setBackground(Color.white);

   return gPanel2;


}
//#######################################################
JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "wiegleb", 
                "games/1000", 
                "Scores", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                false, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        //renderer.setSeriesPaint(1, Color.RED);
        //renderer.setSeriesShapesVisible(1,false);
        //renderer.setSeriesStroke(1, new BasicStroke(2.0f));


        //NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        //domain.setRange(0.5, 1.00);

        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(0.0, 1.0);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Scores wiegleb",
                        new Font("Serif", java.awt.Font.BOLD, 16)
                )
        );

        return chart;

    }
//#######################################################
JFreeChart createChart2(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "wiegleb", 
                "games/1000", 
                "Explor. rate", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                false, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        //renderer.setSeriesPaint(1, Color.RED);
        //renderer.setSeriesShapesVisible(1,false);
        //renderer.setSeriesStroke(1, new BasicStroke(2.0f));


        //NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        //domain.setRange(0.5, 1.00);

        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(0.0, 0.1);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Explor. rate",
                        new Font("Serif", java.awt.Font.BOLD, 16)
                )
        );

        return chart;

    }
//#######################################################
void atualizaGrafico(int nG,double score1){


   double q = (double)nG/1000;

   xx.add(q);
   yy.add(score1);
  
 

   //System.out.println("******************* repaint ****************"); 
   double[] x0 = new double[xx.size()];
   double[] z = new double[yy.size()];
 


   for(int i1=0;i1<z.length;i1++){
      x0[i1] = xx.get(i1);
      z[i1] = yy.get(i1);


   }


   XYDataset dataset = createDataset(x0,z);

   ((XYPlot)chart.getPlot()).setDataset(dataset);

   gPanel.revalidate();

   gPanel.repaint();


}
//#######################################################
void atualizaGrafico2(int nG,double aleat){


   //double q = (double)nG/1000;

   //xx.add(q);
   
   yy1.add(aleat);
 
   

   //System.out.println("******************* repaint ****************"); 
   double[] x0 = new double[xx.size()];
   
   double[] z1 = new double[yy1.size()];


   for(int i1=0;i1<z1.length;i1++){
      x0[i1] = xx.get(i1);
     
      z1[i1] = yy1.get(i1);

   }

  


   XYDataset dataset = createDataset2(x0,z1);



   ((XYPlot)chart2.getPlot()).setDataset(dataset);

   gPanel2.revalidate();

   gPanel2.repaint();

   
}
//####################################################################
public  Runnable t1 = new Runnable() {

      public void run(){

        try{

                Board b0 =new Board();

                b0.inicializa();

                b0.printBoard();

                ArrayList<Integer> rota = new ArrayList<>();

                ArrayList<Board> bHist = new ArrayList<>();

                ArrayList<Integer> rotaOtima = new ArrayList<>();

                nreplay=0;

                int ep=0;

                long tempo=0;
                
   
                NumberFormat perc = NumberFormat.getPercentInstance();
                DecimalFormat df = new DecimalFormat("0.####");
                DecimalFormat df1 = new DecimalFormat("0.##");

                double score1 = 0;
                double score2= 0;                

                perc.setMinimumFractionDigits(2);
               

                tdLearning td = new tdLearning(nWeights,Gamma,Lambda);
   

                if(lerW.isSelected())
                   td.readingWeights("weight.dat");

                playerResta1 p1 = new playerResta1();

                int kk=0;

                double scoreBack=0.0;

                int intervBack=0;

                double deltaScore = 0.0;                

                //-------------------------------------------------------------
                for(int nG=0;nG<Nep;nG++){    

                    Board b = new Board();     
                    
                    b.inicializa();

                    //b.printBoard();                   

                    rota.clear();bHist.clear();

                    double aleat = ut.gaussianDecai(nG,aleatInicial);  // sigmodal decay

                    double alfa = ut.ExpDecai(nG,Nep,alphain,alphafim);


                    //bHist.add(b);

                    //------------------------------------------------------
                    while(true){        

                        Board bOld = new Board();
                       

                        for(int i=0;i<NCEL;i++)
                           bOld.setItem(i,b.getItem(i));

                        int choice = p1.play(b,aleat,td.getW());

                        b.setMove(choice);

                        rota.add(choice);                      

                        if(updateW.isSelected()){
                          if(p1.getFgreed() || b.geraCandSimples().size() == 0)
                              td.updateWgame(bOld,b,alfa);
                        }

                        if(b.geraCandSimples().size() == 0)
                          break;

                        
                    } //-------------------- fim do while(true)


                    if(b.contaPegs()==1){

                      score1 += 1;
   
                      rotaOtima.clear();

                      for(int i1=0;i1<rota.size();i1++)
                         rotaOtima.add(rota.get(i1));

                      //System.out.println("rota Otima:");
                      //ut.imprime_x(rotaOtima);
                     }  
                     else
                      score2 += 1; 


                    long end  = System.currentTimeMillis();

                    tempo = (end-start)/1000;

                   //----------------------------------------------------------------------------
                    if(end/1000 % 60 == 0){
                       
                       deltaScore = score1 - scoreBack;

                       int interv = nG - intervBack;

                       double ss = score1+score2;                       

                       System.out.printf("\n\n================================ Episodio: (%d) aleat:(%.3f) alfa:(%.3f) tempo = (%dmin)(%.2fhs)\n",nG,aleat,alfa,tempo/60,tempo/3600.0);
                        
                       if(interv > 0){
                         double p = deltaScore*100/(interv);

                         System.out.printf("delta Score = (%.2f)  Intervalo = (%d)  perc = (%.2f)\n",deltaScore,interv,p);
                       }

                       System.out.println("-----------------------------------------------------------------------");

                       //b0.printBoard();

                       //System.out.println("-----------------------------------------------------------------------");

                       b.printBoard();

                       System.out.println("----------------------------------------------------------------------:");

                       if(rotaOtima.size()  > 0){
                         ut.imprime_x(rotaOtima);
                         
                       }

                       System.out.println("----------------------------------------------------------------------:");
                       System.out.println("rota atual:");
                       ut.imprime_x(rota);

                       //System.out.println("-----------------------------------------------------------------------");

                       //System.out.printf("Gamma = (%.2f)\n",Gamma);                       

                       //System.out.println("-----------------------------------------------------------------------");                       

                       //System.out.println("--------------------------------------------------------Weights:");

                       //System.out.printf("wMin[%d] = (%.5f)\twMax[%d] = (%.4f)\n",td.argMin(),td.wMin(),td.argMax(),td.wMax());

                       //System.out.println("--------------------------------------------------------Weights Ativados:");

                       int wT = td.freqMais1()+td.freqMenos1();

                       //System.out.println("w+ = " + perc.format(td.freqMais()) + "   w- = " + perc.format(td.freqMenos()) + " Total = " + perc.format(td.freqMais()+td.freqMenos()) + " (" + td.freqMais1()+ "|" + td.freqMenos1()+ "|" +wT +")");

                       System.out.println("--------------------------------------------------------custo inicial:");

                       double[] v = custoInicial(td);

                       ut.printVetor(v);

                       kk += 1;

                       scoreBack = score1;intervBack = nG;

                       score = deltaScore/interv;

                       //System.out.println("--------------------------------------------------------Scores:");

                       //System.out.println("[" + nG + "]" + "score1|2 = " + perc.format(score1/ss)  + "|" + perc.format(score2/ss) + "    (" + score1+"|"+score2+")"  + "  DeltaScore = ("+ perc.format(score) +")");
                      

                       if(salvarW.isSelected())
                         td.savingWeights("weight.dat");                       
                       
                       

                       double wAtivados1 = (double) wT/nWeights;

                       atualizaGrafico(nG,score);
                       atualizaGrafico2(nG,aleat);

                              resultados.setText("N.games = " + Integer.toString(nG) + "   Tempo = " + df1.format(tempo/3600.0)+ "hs   " + Long.toString(tempo/60) + "min  " + " Scores: " + perc.format(score1/ss) + "|" + perc.format(score2/ss));

                       resultados02.setText("Alfa = " + df.format(alfa) + "      Explor. = " + df.format(aleat));

                       resultados03.setText("Weights Min/Max -> TD1: (" + df.format(td.wMin()) + ")(" +  df.format(td.wMax())  + ")" + " acertos= " + score1 );

                       txtResultados04.setText("W. ativados = " + perc.format(wAtivados1) + "     w+ = " + perc.format(td.freqMais()) + "   w- = " + perc.format(td.freqMenos()));

                       try { Thread.sleep (1000); } catch (InterruptedException ex) {ex.printStackTrace();}

                    }//-----------------------fim do step

 //----------------------------------------------------------


                  
                    //if(nG % 50 == 00 && rotaOtima.size() > 0 && updateW.isSelected())
                    //     replayMemoria(td,rotaOtima);               

                    if(score >= 0.80)
                      break;
                }//--------------- fim do int nG

                if(rotaOtima.size() > 0)
                  avaliacao(td,rotaOtima);
                else
                  avaliacao(td,rota);

                JOptionPane.showMessageDialog(frame, "****** OK *****");


         } catch (Exception e){System.out.println("nao iniciado");}

      }// fim do run

};
}//=========================================================
