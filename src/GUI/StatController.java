/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.categorie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import services.achatservice;
import services.categorieservice;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class StatController implements Initializable {

    

    /**
     * Initializes the controller class.
     */
    categorieservice cs =new categorieservice();
    achatservice as =new achatservice();
    @FXML
    private BorderPane border;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<categorie> l =cs.getAll();
        ObservableList lc=FXCollections.observableArrayList(l);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (categorie element : l) 
    // Utiliser l'élément
 {
    
    
    pieChartData.add(new PieChart.Data(element.getNom(), as.achatparcat(element.getId())));
}
      PieChart pieChart = new PieChart(pieChartData);

        pieChart.setTitle("liste des achats par categorie"); //Setting the title of the Pie chart
        pieChart.setClockwise(true); //setting the direction to arrange the data 
        pieChart.setLabelLineLength(50); //Setting the length of the label line 
        pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
        pieChart.setLegendVisible(true);
        pieChart.setStartAngle(180);// TODO
        border.setCenter(pieChart);
        
    }    
    
}
