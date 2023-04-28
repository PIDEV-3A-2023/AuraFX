/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.achat;
import entities.facture;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.achatservice;
import services.pdf;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class SinglefactureController implements Initializable {

    @FXML
    private TableView<achat> tab;
    @FXML
    private TableColumn<achat, String> produit;
    @FXML
    private TableColumn<achat, String> nbr;
    @FXML
    private TableColumn<achat, String> prix;
    facture f=new facture();
    achatservice as =new achatservice();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }  
    public void load(){
      List<achat> a=  as.achatparfact(f.getId());
      ObservableList lp=FXCollections.observableArrayList(a);
      produit.setCellValueFactory(new PropertyValueFactory<>("produit"));
      nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
      prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       tab.setItems(lp);
    }
    public void setfact(facture f){
        this.f=f;
    }

    @FXML
    private void imprimer(ActionEvent event) {
        List<achat> la=  as.achatparfact(f.getId());
        pdf.genererFacture3(""+f.getId(), f.getMontant(), f.getDate(), la);
    }
    
}
