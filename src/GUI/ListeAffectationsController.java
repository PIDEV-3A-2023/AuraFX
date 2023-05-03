/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FXML Controller class
 *
 * @author issamfekih.if
 */
package GUI;

import entity.Affectations;
import entity.Technicien;
import entity.Terrain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CRUDaffectations;
import services.CRUDtechnicien;

public class ListeAffectationsController implements Initializable {

    @FXML
    private TableView<Affectations> tvListeA;
    @FXML
    private TableColumn<Affectations, Integer> LID;
    @FXML
    private TableColumn<Affectations, Integer> LIDTerrain;
    @FXML
    private TableColumn<Affectations, Integer> LNomTechnicien;
    @FXML
    private TableColumn<Affectations, Date> LDebut;
    @FXML
    private TableColumn<Affectations, Date> LDateFin;
    @FXML
    private Button AURAHome;
    @FXML
    private Button BackA;
    @FXML
    private Button ftupdate;
    @FXML
    private Button ftdelete;
    @FXML
    private Button ftstat;
    @FXML
    private Pagination pagination;
            
    CRUDaffectations a = new CRUDaffectations();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
        List<Affectations> Affectation = a.afficherAffectations();
        ObservableList<Affectations> LISTE = FXCollections.observableArrayList(Affectation);

        LID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        LNomTechnicien.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTechnicien().getId()).asObject());
        LIDTerrain.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTerrain().getId()).asObject());
        LDebut.setCellValueFactory(cellData -> new SimpleObjectProperty<>(new Date(cellData.getValue().getDateDebut().getTime())));
        LDateFin.setCellValueFactory(cellData -> new SimpleObjectProperty<>(new Date(cellData.getValue().getDateFin().getTime())));

        tvListeA.setItems(LISTE);
           // Set up pagination
    int rowsPerPage = 10;
    int pageCount = (int) Math.ceil((double) LISTE.size() / rowsPerPage);
    pagination.setPageCount(pageCount);
    pagination.setPageFactory((Integer pageIndex) -> {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, LISTE.size());

        TableView<Affectations> pageTable = new TableView<>();
        pageTable.setItems(FXCollections.observableArrayList(LISTE.subList(fromIndex, toIndex)));
        // Set up the table columns as before

        return pageTable;
    });
        
    }
    
    
    @FXML
    private void AURAHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AURAMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("AURA");
        stage.show();
    }

    @FXML
    private void BackA(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affectations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gestion Affectations");
        stage.show();
    }

    @FXML
    private void updateTechnicien(ActionEvent event) throws IOException {
        refresh();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateAffectations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Update Affectations");
        stage.show();
    }

    @FXML
    private void deleteAffectations(ActionEvent event) {
                Affectations a = tvListeA.getSelectionModel().getSelectedItem();

        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Select Affectations");
            alert.showAndWait();
            return;
        } else {
            CRUDaffectations service = new CRUDaffectations();
            service.supprimerAffectations(a.getId());
            refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Affectations supprimé avec succé");
            alert.showAndWait();
        }
    }

    @FXML
    private void statA(ActionEvent event) throws IOException{
        
        refresh();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Stat.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Stat Affectations");
        stage.setScene(scene);
        stage.show();
    }
    
    
    

    
    
    

}
