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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CRUDtechnicien;

public class UpdateTechnicien implements Initializable {
    private TextField ftnomt;
    @FXML
    private Button AURAHome;
    @FXML
    private Button AURABack;
    @FXML
    private Button ListeA;
    
    
    Technicien T = new Technicien();
    CRUDtechnicien crudtechnicien;
    @FXML
    private Button fttupdate;
    @FXML
    private TextField ftspecialitett;
    @FXML
    private TextField ftsalairett;
    @FXML
    private TextField ftteltt;
    @FXML
    private TextField ftemailtt;
    @FXML
    private TextField ftprenomtt;
    @FXML
    private TextField ftnomtt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDtechnicien ct = new CRUDtechnicien();
        List<Technicien> techniciens = ct.afficherTechnicien();
        for(Technicien technicien : techniciens){
            //ftnomt.getItems().add(technicien);
            //ftprenomt.getItems().add(technicien);
            //fttelt.getItems().add(technicien);
            //ftemailt.getItems().add(technicien);
            //ftspecialitet.getItems().add(technicien);
            //ftsalairet.getItems().add(technicien);
            
        }

    }
/*
    private void updateTechnicien(ActionEvent event) {
    try {
        // Récupération des données saisies dans les champs
        //String nom = ftnomt.getValue().toString();
        //String prenom = ftprenomt.getValue().toString();
        //String tel = fttelt.getValue().toString();
        //String email = ftemailt.getValue().toString();
        //String specialite = ftspecialitet.getValue().toString();
        //Double salaire = Double.parseDouble(ftsalairet.getValue().toString());
        //int id = Integer.parseInt(ftid.getValue().toString());

        //Technicien t = new Technicien(id, nom, prenom, tel, email, specialite, salaire);

        CRUDtechnicien ct = new CRUDtechnicien();
        //ct.updateTechnicien(t);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mise à jour réussie");
        alert.setHeaderText(null);
        alert.setContentText("Le technicien a été mis à jour avec succès !");
        alert.showAndWait();

    } catch (NumberFormatException | NullPointerException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    }
}*/
    @FXML
    private void AURAHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AURAMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("AURA");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void AURABAck(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Technicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Ajouter Technicien");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void ListeA(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeTechnicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    private void updateTechnicienU(ActionEvent event) {
        //Technicien tech = ftid.getSelectionModel().getSelectedItem(); // get the selected technician by id
        //tech.setNom(ftnomt.getSelectionModel().getSelectedItem().getNom()); // set the new name
        //tech.setPrenom(ftprenomt.getSelectionModel().getSelectedItem().getPrenom()); // set the new prenom
        //tech.setTel(fttelt.getSelectionModel().getSelectedItem().getTel()); // set the new tel
        //tech.setEmail(ftemailt.getSelectionModel().getSelectedItem().getEmail()); // set the new email
        //tech.setSpecialite(ftspecialitet.getSelectionModel().getSelectedItem().getSpecialite()); // set the new specialite
        //tech.setSalaire(ftsalairet.getSelectionModel().getSelectedItem().getSalaire()); // set the new salaire

        CRUDtechnicien ct = new CRUDtechnicien();
        //ct.updateTechnicien(tech); // update the technician in the database

        // display the updated technician's information
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherTechnicien.fxml"));

        try {
            Parent root = loader.load();
            AfficherTechnicienController atc = loader.getController();
            //atc.setftnom(tech.getNom());
            //atc.setftprenom(tech.getPrenom());
            //atc.setfttel(tech.getTel());
            //atc.setftemail(tech.getEmail());
            //atc.setftspecialite(tech.getSpecialite());
            //atc.setftsalaire(Double.toString(tech.getSalaire()));

            ftnomt.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Erreur affichage" + ex.getMessage());
        }
    }
}
