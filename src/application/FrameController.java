package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import com.sun.javafx.tk.FontLoader;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FrameController implements Initializable {

	
	@FXML
	private Button browse;
	@FXML 
	private TextField txtPath,txtCluster,txtItr;
	@FXML
	private TableView  table , table1;
    @FXML
    private TableColumn<?, ?> tableName;
    @FXML
    private TableColumn<?, ?> tableX;
    @FXML
    private TableColumn<?, ?> tableY;
    @FXML
    private Button BtnResult,BtnChart;
    @FXML
    private ToggleGroup x;
    @FXML
    private RadioButton RadioManh,RadioEucli;
    
    Data data;
    
	public void Browse(ActionEvent  event) {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		txtPath.setText(selectedFile.getPath());
	    data = new Data();
		tableName.setCellValueFactory(new PropertyValueFactory<>("label"));
		tableX.setCellValueFactory(new PropertyValueFactory<>("X"));
		tableY.setCellValueFactory(new PropertyValueFactory<>("Y"));
		tableName.setStyle( "-fx-alignment: CENTER;");
		tableX.setStyle( "-fx-alignment: CENTER;");
		tableY.setStyle( "-fx-alignment: CENTER;");
		table.getItems().clear();
		data.Read(selectedFile.getPath());
		DisplayData(data);
	}
	void DisplayData(Data d) {
		Iterator<double[]> itr=d.features.iterator();
		Iterator<String> sitr=d.label.iterator();
		String s=null;
		while(itr.hasNext())
		{ 
			double db[]=itr.next();
		    s=sitr.next() ;
			table.getItems().add(new Point(s,db[0],db[1]));
		}
		
	}
	public void BtnResultAction(ActionEvent  event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Frame1.fxml"));
		Parent root = (Parent) loader.load();
		Frame1Controller frame1 = loader.getController();
		
		int k = Integer.parseInt(txtCluster.getText()) ;
		int max_itr = Integer.parseInt(txtItr.getText());
		int distance = RadioEucli.isSelected()?1:2;
		Map<Integer, double[]> centroids = new HashMap<>();
		centroids = Kmeans.initialCentroid(data.features,centroids,k);
        Map<double[], Integer> clusters = new HashMap<>();
        clusters= Kmeans.Clustering(data.features, distance, centroids, k, max_itr);
        
        frame1.DisplayData(clusters, data);
        
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Clustring");
		stage.show();
	}
	public void BtnChartAction(ActionEvent  event) throws IOException {
		int k = Integer.parseInt(txtCluster.getText()) ;
		int max_itr = Integer.parseInt(txtItr.getText());
		int distance = RadioEucli.isSelected()?1:2;
		Map<Integer, double[]> centroids = new HashMap<>();
		centroids = Kmeans.initialCentroid(data.features,centroids,k);
        Map<double[], Integer> clusters = new HashMap<>();
        clusters= Kmeans.Clustering(data.features, distance, centroids, k, max_itr);
        ScatterChart s = Chart.chart(clusters, k);  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chart.fxml"));
		AnchorPane root = loader.load(); 
        root.getChildren().add(s);  
        Stage stage = new Stage();
		stage.setScene(new Scene(root,520,420));
		stage.setTitle("K-means");
		stage.show();
         
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
