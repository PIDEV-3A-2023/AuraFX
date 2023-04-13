package GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CRUDAssociation;
import entites.Association;

import java.net.URL;
import java.util.ResourceBundle;

public class AssociationController implements Initializable {

    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxAdresse;
    @FXML
    private TextField fxEmail;
    @FXML
    private TextField fxRib;
    @FXML
    private TableView<Association> tableView;
    @FXML
    private TableColumn<Association, Integer> idColumn;
    @FXML
    private TableColumn<Association, String> nomColumn;
    @FXML
    private TableColumn<Association, String> adresseColumn;
    @FXML
    private TableColumn<Association, String> emailColumn;
    @FXML
    private TableColumn<Association, String> ribColumn;
    @FXML
    private ComboBox<Integer> idComboBox;

    private CRUDAssociation crud = new CRUDAssociation();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ribColumn.setCellValueFactory(new PropertyValueFactory<>("rib"));
        tableView.setItems(crud.listerAssociations());
        idComboBox.setItems(crud.listerIds());
        idComboBox.setOnAction(e -> {
            Integer selectedId = idComboBox.getSelectionModel().getSelectedItem();
            if (selectedId != null) {
                Association selectedAssociation = crud.rechercherAssociation(selectedId);
                if (selectedAssociation != null) {
                    fxNom.setText(selectedAssociation.getNom());
                    fxAdresse.setText(selectedAssociation.getAdresse());
                    fxEmail.setText(selectedAssociation.getEmail());
                    fxRib.setText(selectedAssociation.getRib());
                }
            }
        });
    }

    private boolean validateNom(String nom) {
        // check if nom is not empty
        if (nom.isEmpty()) {
            // show error message and return false
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nom invalide");
            alert.setContentText("Veuillez saisir un nom valide.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean validateRib(String rib) {
        // check if rib is not empty and contains only digits
        if (!rib.matches("[0-9]+")) {
            // show error message and return false
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("RIB invalide");
            alert.setContentText("Veuillez saisir un RIB valide (contenant uniquement des chiffres).");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean validateAdresse(String adresse) {
        // check if adresse is not empty
        if (adresse.isEmpty()) {
            // show error message and return false
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Adresse invalide");
            alert.setContentText("Veuillez saisir une adresse valide.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        // check if email is valid using regex pattern matching
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            // show error message and return false
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("E-mail invalide");
            alert.setContentText("Veuillez saisir une adresse e-mail valide.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean validateInput(String nom, String adresse, String email, String rib) {
        boolean isValid = true;
        if (!validateNom(nom)) {
            isValid = false;
        }
        if (!validateAdresse(adresse)) {
            isValid = false;
        }
        if (!validateEmail(email)) {
            isValid = false;
        }
        if (!validateRib(rib)) {
            isValid = false;
        }

        return isValid;
    }

    @FXML
    private void save() {
        String nom = fxNom.getText();
        String adresse = fxAdresse.getText();
        String email = fxEmail.getText();
        String rib = fxRib.getText();
        if (!validateInput(nom, adresse, email, rib)) {
            // if any of the inputs are invalid, return without saving the association
            return;
        }
        Association association = new Association(nom, adresse, email, rib);
        crud.ajouterAssociation(association);
        tableView.setItems(crud.listerAssociations());
        idComboBox.setItems(crud.listerIds());
    }

    @FXML
    private void update() {
        Integer selectedId = idComboBox.getSelectionModel().getSelectedItem();
        if (selectedId != null) {
            String nom = fxNom.getText();
            String adresse = fxAdresse.getText();
            String email = fxEmail.getText();
            String rib = fxRib.getText();
            if (!validateInput(nom, adresse, email, rib)) {
                // if any of the inputs are invalid, return without saving the association
                return;
            }
            Association association = new Association(selectedId, nom, adresse, email, rib);
            crud.modifierAssociation(association);
            tableView.setItems(crud.listerAssociations());
            idComboBox.setItems(crud.listerIds());
        }
    }

    @FXML
    private void delete() {
        Integer selectedId = idComboBox.getSelectionModel().getSelectedItem();
        if (selectedId != null) {
            Association association = new Association();
            association.setId(selectedId);
            crud.supprimerAssociation(association);
            tableView.setItems(crud.listerAssociations());
            idComboBox.setItems(crud.listerIds());
        }
    }

}
