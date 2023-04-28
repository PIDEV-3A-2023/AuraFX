/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import entities.achat;
import entities.facture;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.achatservice;
import services.factureservice;
import services.pdf;

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
    facture selected = new facture();
    achatservice as = new achatservice();
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

    @FXML
    private void onmouse(MouseEvent event) {
        selected= (facture) tab.getSelectionModel().getSelectedItem();
        /*try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("singlefacture.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                // pass the selected post to the single post controller
                SinglefactureController controller = loader.getController();
                controller.setfact(selected);

                Stage stage = (Stage) tab.getScene().getWindow();
        stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        List<achat> la=  as.achatparfact(selected.getId());
        pdf.genererFacture3(""+selected.getId(), selected.getMontant(), selected.getDate(), la);
    }
    
    
}
