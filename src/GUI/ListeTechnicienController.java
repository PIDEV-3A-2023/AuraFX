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

import entity.Technicien;
import java.io.IOException;
import java.net.URL;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CRUDtechnicien;

public class ListeTechnicienController implements Initializable {
    @FXML
    private TableView<Technicien> tvListe;
    @FXML
    private TableColumn<Technicien, Integer> LIDTechnicien;
    @FXML
    private TableColumn<Technicien, String> LNom;
    @FXML
    private TableColumn<Technicien, String> LPrenom;
    @FXML
    private TableColumn<Technicien, String> LTelephone;
    @FXML
    private TableColumn<Technicien, String> LEmail;
    @FXML
    private TableColumn<Technicien, String> LSpecialite;
    @FXML
    private TableColumn<Technicien, Double> LSalaire;
    @FXML
    private Button AURAHome;
    @FXML
    private Button BackT;
    @FXML
    private Button ftupdate;
    @FXML
    private Button ftdelete;
    @FXML
    private Button email;
    @FXML
    private Pagination pagination;
    //private TextField searchField;
    //private ObservableList<Technicien> masterData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
        CRUDtechnicien ct = new CRUDtechnicien();
        List<Technicien> technicien = ct.afficherTechnicien();

        LIDTechnicien.setCellValueFactory(new PropertyValueFactory<Technicien, Integer>("id"));
        LNom.setCellValueFactory(new PropertyValueFactory<Technicien, String>("nom"));
        LPrenom.setCellValueFactory(new PropertyValueFactory<Technicien, String>("prenom"));
        LTelephone.setCellValueFactory(new PropertyValueFactory<Technicien, String>("tel"));
        LEmail.setCellValueFactory(new PropertyValueFactory<Technicien, String>("email"));
        LSpecialite.setCellValueFactory(new PropertyValueFactory<Technicien, String>("specialite"));
        LSalaire.setCellValueFactory(new PropertyValueFactory<Technicien, Double>("salaire"));

        tvListe.setItems((ObservableList<Technicien>) technicien);
        

    int rowsPerPage = 10;
    int pageCount = (int) Math.ceil((double) technicien.size() / rowsPerPage);

    pagination.setPageCount(pageCount);
    pagination.setPageFactory((Integer pageIndex) -> {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, technicien.size());

        TableView<Technicien> pageTable = new TableView<>();
        pageTable.setItems(FXCollections.observableArrayList(technicien.subList(fromIndex, toIndex)));

        return tvListe;
    });

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


    @FXML
    private void BackT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Technicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ajouter Technicien");
        stage.show();
    }
    @FXML
    private void updateTechnicien(ActionEvent event) throws IOException {
        refresh();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateTechnicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Update Technicien");
        stage.show();
    }

    @FXML
    private void deleteTechnicien(javafx.event.ActionEvent event) {
        refresh();
        Technicien a = tvListe.getSelectionModel().getSelectedItem();

        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Selectionner Technicien");
            alert.showAndWait();
            return;
        } else {
            CRUDtechnicien service = new CRUDtechnicien();
            service.supprimerTechnicien(a.getId());
            refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Technicien supprimé avec succé");
            alert.showAndWait();

              int rowsPerPage = 10;
        int pageCount = (int) Math.ceil((double) tvListe.getItems().size() / rowsPerPage);
        pagination.setPageCount(pageCount);
        pagination.setCurrentPageIndex(pagination.getCurrentPageIndex());
        }
    }

    @FXML
    private void emailt(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Email.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Email Technicien");
        stage.show();
    }
}
