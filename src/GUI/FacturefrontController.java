/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.facture;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.factureservice;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class FacturefrontController implements Initializable {

    @FXML
    private TableColumn<facture, Integer> id;
    @FXML
    private TableColumn<facture, String> montant;
    @FXML
    private TableColumn<facture, Date> date;
    @FXML
    private TableView<?> tab;
    factureservice fs=new factureservice();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
    }  
    public void load(){
      List<facture> f=  fs.getbymem(16);
      ObservableList lp=FXCollections.observableArrayList(f);
      id.setCellValueFactory(new PropertyValueFactory<>("id"));
      montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
      date.setCellValueFactory(new PropertyValueFactory<>("date"));
       tab.setItems(lp);
    }
    
}
