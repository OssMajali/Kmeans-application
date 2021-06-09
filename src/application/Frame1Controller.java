package application;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Frame1Controller implements Initializable{

	@FXML
    private TableView table1;
	@FXML
	private TableColumn tableName;

	@FXML
	private TableColumn tableX;

	@FXML
	private TableColumn tableY;

	@FXML
	private TableColumn Cluster;
	public void DisplayData(Map<double[],Integer> clusters,Data d) {
		tableName.setCellValueFactory(new PropertyValueFactory<>("label"));
		tableX.setCellValueFactory(new PropertyValueFactory<>("X"));
		tableY.setCellValueFactory(new PropertyValueFactory<>("Y"));
		Cluster.setCellValueFactory(new PropertyValueFactory<>("cluster"));
		tableName.setStyle( "-fx-alignment: CENTER;");
		tableX.setStyle( "-fx-alignment: CENTER;");
		tableY.setStyle( "-fx-alignment: CENTER;");
		Cluster.setStyle( "-fx-alignment: CENTER;");
		table1.getItems().clear();
		Iterator<double[]> itr=d.features.iterator();
		Iterator<String> sitr=d.label.iterator();
		while(itr.hasNext())
		{ 
			double db[]=itr.next();
			String s=sitr.next() ;
			for (double[] key : clusters.keySet()) {
				if(db[0]==key[0] && db[1]==key[1]) {
					//System.out.println(s+", "+db[0]+" ,"+ db[1]+ ", "+clusters.get(key) );
					table1.getItems().add(new Point(s,db[0],db[1],clusters.get(key)));
				}
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	

}
