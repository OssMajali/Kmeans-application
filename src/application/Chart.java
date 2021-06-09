package application;

import java.util.Map;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class Chart {	
	static ScatterChart  chart (Map<double[],Integer> clusters ,int k) {
		NumberAxis xaxis = new NumberAxis(0,14,2);  
        NumberAxis yaxis = new NumberAxis(0,14,2);  
        xaxis.setLabel("X");  
        yaxis.setLabel("Y");  
        ScatterChart s = new ScatterChart(xaxis,yaxis);  
        s.setTitle("Clustering with k-means");  
		for(int i = 0;i<k;i++) {
		 XYChart.Series series = new XYChart.Series();  
		 series.setName("Cluster "+ i); 
			 for (double[] key : clusters.keySet()) {
					if (clusters.get(key)==i) {
						
						series.getData().add(new XYChart.Data(key[0],key[1],"P1"));  
					}
			 }
	     s.getData().add(series);   
		 }
		return s ;
	}
}
