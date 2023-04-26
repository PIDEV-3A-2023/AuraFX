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
import services.CRUDDon;
import entites.Don;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDateTime;

public class DonController implements Initializable {

    @FXML
    private TableView<Don> tableView;
    //@FXML
    //private TableColumn<Don, Integer> idColumn;
    @FXML
    private TableColumn<Don, Double> montantColumn;
    @FXML
    private TableColumn<Don, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Don, String> carteColumn;
    @FXML
    private TableColumn<Don, String> messageColumn;
    @FXML
    private TableColumn<Don, String> emailColumn;
    @FXML
    private ComboBox<Integer> idComboBox;
    @FXML
    private TextField fxMessage;
    @FXML
    private TextField fxEmail;
    @FXML
    private TextField fxMontant;
    @FXML
    private TextField fxCarte;

    private CRUDDon crud = new CRUDDon();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        carteColumn.setCellValueFactory(new PropertyValueFactory<>("carte"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.setItems(crud.listerDons());
        idComboBox.setItems(crud.listerIds());
        idComboBox.setOnAction(e -> {
            Integer selectedId = idComboBox.getSelectionModel().getSelectedItem();
            if (selectedId != null) {
                Don selectedDon = crud.rechercherDon(selectedId);
                if (selectedDon != null) {
                    fxMontant.setText(Double.toString(selectedDon.getMontant()));
                    fxCarte.setText(selectedDon.getCarte());
                    fxMessage.setText(selectedDon.getMessage());
                    fxEmail.setText(selectedDon.getEmail());
                }
            }
        });
    }

    private boolean validateMontant(double montant) {
        // check if montant is positive
        if (montant <= 0) {
            // show error message and return false
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Montant invalide");
            alert.setContentText("Veuillez saisir un montant positif.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean validateCarte(String carte) {
        // check if carte is not empty
        if (carte.isEmpty()) {
            // show error message and return false
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Carte invalide");
            alert.setContentText("Veuillez saisir un numéro de carte valide.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean validateMessage(String message) {
        // check if message is not empty
        if (message.isEmpty()) {
            // show error message and return false
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Message invalide");
            alert.setContentText("Veuillez saisir un message valide.");
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

    private boolean validateInput(double montant, String carte, String message, String email) {
        boolean isValid = true;
        if (!validateMontant(montant)) {
            isValid = false;
        }
        if (!validateCarte(carte)) {
            isValid = false;
        }
        if (!validateMessage(message)) {
            isValid = false;
        }
        if (!validateEmail(email)) {
            isValid = false;
        }

        return isValid;
    }

    @FXML
    private void save() {
        double montant = Double.parseDouble(fxMontant.getText());
        LocalDateTime date = LocalDateTime.now();
        String carte = fxCarte.getText();
        String message = fxMessage.getText();
        String email = fxEmail.getText();
        if (!validateInput(montant, carte, message, email)) {
            // if any of the inputs are invalid, return without saving the association
            return;
        }
        Don don = new Don(montant, date, carte, message, email);
        crud.ajouterDon(don);
        tableView.setItems(crud.listerDons());
        idComboBox.setItems(crud.listerIds());
    }

    @FXML
    private void update() {
        Integer selectedId = idComboBox.getSelectionModel().getSelectedItem();
        if (selectedId != null) {
            Double montant = Double.parseDouble(fxMontant.getText());
            LocalDateTime date = LocalDateTime.now();
            String carte = fxCarte.getText();
            String message = fxMessage.getText();
            String email = fxEmail.getText();
            if (!validateInput(montant, carte, message, email)) {
                // if any of the inputs are invalid, return without saving the association
                return;
            }
            Don Don = new Don(selectedId, montant, date, carte, message, email);
            crud.modifierDon(Don);
            tableView.setItems(crud.listerDons());
            idComboBox.setItems(crud.listerIds());
        }
    }

    @FXML
    private void delete() {
        Integer selectedId = idComboBox.getSelectionModel().getSelectedItem();
        if (selectedId != null) {
            Don Don = new Don();
            Don.setId(selectedId);
            crud.supprimerDon(Don);
            tableView.setItems(crud.listerDons());
            idComboBox.setItems(crud.listerIds());
        }
    }

}
