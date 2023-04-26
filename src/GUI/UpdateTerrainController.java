package GUI;

import Entities.Terrain;
import Services.crudTerrain;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cyrine 
 */
public class UpdateTerrainController implements Initializable {

    @FXML
    private TextField txtSurface;
    @FXML
    private Button btngetback;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtPotentiel;
    public Terrain t = ListTerrainController.p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtAdresse.setText(t.getAdresse());
        txtPotentiel.setText(""+t.getPotentiel());
        txtSurface.setText(""+t.getSurface());
    }    

    @FXML
    private void loadMenu(ActionEvent event) {
               try {
            Parent root = FXMLLoader.load(getClass().getResource("ListTerrain.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //stage.setFullScreen(true);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AddPub(ActionEvent event) {
                       if ("".equals(txtAdresse.getText().trim()) || "".equals(txtPotentiel.getText().trim() )|| "".equals(txtSurface.getText().trim())  || !isNumeric(txtPotentiel.getText().trim()) || !isPositiveNumeric(txtSurface.getText().trim()) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("You have to fill required fields!");

            alert.showAndWait();
        } else if(t.getAdresse().equals(txtAdresse.getText())){
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Warning");
            alert.setContentText("You have to update the fields!");
                        alert.showAndWait();

        }
                       
                       else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirm adding a publication");
            alert.setContentText("A new Terrain will be published are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                crudTerrain ps = new crudTerrain();
                ps.update(t.getId_terrain(), Double.parseDouble(txtSurface.getText()),Double.parseDouble(txtPotentiel.getText()), txtAdresse.getText());
                loadMenu(event);
            }
        }

    }
       private boolean isNumeric(String str) {
    try {
        Double.parseDouble(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

private boolean isPositiveNumeric(String str) {
    try {
        double value = Double.parseDouble(str);
        return value > 0;
    } catch (NumberFormatException e) {
        return false;
    }
}
     
}
