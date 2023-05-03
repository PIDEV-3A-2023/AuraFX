package GUI;

import entity.Affectations;
import entity.Technicien;
import entity.Terrain;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.CRUDaffectations;
import services.CRUDtechnicien;
import services.CRUDterrain;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


/**
 *
 * @author issamfekih.if
 */
public class Stat implements Initializable {
    
    String f;
    double r;

    @FXML
    private PieChart pieChart;
    @FXML
    private Button BackA;
    @FXML
    private Button AURAHome;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDaffectations ca = new CRUDaffectations();
        CRUDtechnicien ct = new CRUDtechnicien();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        List<Affectations> affectations = ca.afficherAffectations();
        
        for (Affectations element : affectations) {
        list.add(new PieChart.Data(f=Integer.toString(element.getTechnicien().getId()), r=ca.stat(element.getTechnicien())));
        }
        PieChart.Data affData = new PieChart.Data(f,r);
         
       list.add(affData);  
        pieChart.setData(list);        
}    

    @FXML
    private void BackA(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAffectations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liste Affectations");
        stage.show();
    }

    @FXML
    private void AURAHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AURAMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
