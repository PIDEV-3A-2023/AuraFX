/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Technicien;
import java.io.IOException;
import java.net.URL;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CRUDtechnicien;

/**
 * FXML Controller class
 *
 * @author issamfekih.if
 */
public class TechnicienController {
    Technicien t = new Technicien();
    CRUDtechnicien TechService;
    Technicien selecttech;
    @FXML
    private TextField ftnom;
    @FXML
    private TextField ftprenom;
    @FXML
    private TextField fttel;
    @FXML
    private TextField ftemail;
    @FXML
    private TextField ftspecialite;
    @FXML
    private TextField ftsalaire;
    @FXML
    private Button ftsave;
    @FXML
    private Button AURAHome;
    @FXML
    private Button ListeT;
    @FXML
    private Button AURABackGT;
    @FXML
    private Button ftupdate;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        //ftupdate.setOnAction(this::modifierTechnicien);
        TechService = new CRUDtechnicien();
    }

    public void setTechnicien(Technicien tech) {
        this.selecttech = tech;
        ftnom.setText(tech.getNom());
        ftprenom.setText(tech.getPrenom());
        fttel.setText(tech.getTel());
        ftemail.setText(tech.getEmail());
        ftspecialite.setText(tech.getSpecialite());
        ftsalaire.setText(Double.toString(tech.getSalaire()));
    }

    @FXML
    private void saveTechnicien(ActionEvent event) {
        refresh();
        String nom = ftnom.getText();
        String prenom = ftprenom.getText();
        String tel = fttel.getText();
        String email = ftemail.getText();
        String specialite = ftspecialite.getText();
        String salaireStr = ftsalaire.getText();

        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs vide");
            alert.setContentText("Nom Invalide");
            alert.showAndWait();
            return;
        }

        if (prenom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs vide");
            alert.setContentText("Prenom Invalide");
            alert.showAndWait();
            return;
        }

        if (!tel.matches("^\\d{8}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs vide");
            alert.setContentText("Tel Invalide");
            alert.showAndWait();
            return;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs vide");
            alert.setContentText("Email Invalide");
            alert.showAndWait();
            return;
        }

        if (specialite.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs vide");
            alert.setContentText("specialite Invalide");
            alert.showAndWait();
            return;
        }

        if (salaireStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs vide");
            alert.setContentText("Salare Invalide");
            alert.showAndWait();
            return;
        }

        Double salaire;
        try {
            salaire = Double.parseDouble(salaireStr);
            if (salaire <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Salaire invalide");
            alert.setContentText("Saisie salaire valide");
            alert.showAndWait();
            return;
        }

        Technicien t = new Technicien(nom, prenom, tel, email, specialite, salaire);

        CRUDtechnicien ct = new CRUDtechnicien();
        ct.ajouterTechnicien2(t);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherTechnicien.fxml"));

        try {
            Parent root = loader.load();
            AfficherTechnicienController atc = loader.getController();
            atc.setftnom(t.getNom());
            atc.setftprenom(t.getPrenom());
            atc.setfttel(t.getTel());
            atc.setftemail(t.getEmail());
            atc.setftspecialite(t.getSpecialite());
            atc.setftsalaire(Double.toString(t.getSalaire()));

            ftnom.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Erreur affichage" + ex.getMessage());
        }

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
    private void ListeT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeTechnicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void BackGT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AURAMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("AURA");
        stage.setScene(scene);
        stage.show();        
    }

    @FXML
    private void updateTechnicien(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateTechnicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("AURA");
        stage.setScene(scene);
        stage.show(); 
    }
}
