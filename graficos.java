/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wiegleb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
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

/**
 *
 * @author Marco Victorino
 */
public class graficos {
    
    
    
    XYDataset createDataset(double[] x,double[] y,String titulo){
        
        XYSeries series = new XYSeries(titulo);
        
        for(int i=0;i<y.length;i++)
           series.add(x[i], y[i]);
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        
        return dataset;
    }
    
    //------------------------------------------------------
    JFreeChart createChart(XYDataset dataset,String titulo,String titHoriz,String titVert,double rangeIn,double rangeOut) {
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                titulo,
                titHoriz,
                titVert,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        
        
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(rangeIn, rangeOut);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        
        chart.getLegend().setFrame(BlockBorder.NONE);
        
        chart.setTitle(new TextTitle(titulo,new Font("Serif", java.awt.Font.BOLD, 14)));
        
        return chart;
        
    }
    
    
}
