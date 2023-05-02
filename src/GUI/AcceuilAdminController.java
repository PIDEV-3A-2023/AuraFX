/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AcceuilAdminController implements Initializable {

    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> tel;
    @FXML
    private TableColumn<User, String> mail;

    UserService us = new UserService();
    private ObservableList<User> userData = FXCollections.observableArrayList();
    
    public static int idE;
    @FXML
    private TableView<User> table;
    @FXML
    private Button back113;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, Date> date;
    @FXML
    private TextField recherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   List<User> listUser= new ArrayList<User>();
                   listUser = us.getAllUser();
                   userData.clear();
                   userData.addAll(listUser);
                   table.setItems(userData);
                   nom.setCellValueFactory
                        (
                                new PropertyValueFactory<>("nom")
                        );
                   prenom.setCellValueFactory
                        (
                                new PropertyValueFactory<>("prenom")
                        );
                   mail.setCellValueFactory
                        (
                                new PropertyValueFactory<>("email")
                        );
                   adresse.setCellValueFactory
                        (
                                new PropertyValueFactory<>("adresse")
                        );
                   date.setCellValueFactory
                        (
                                new PropertyValueFactory<>("datenai")
                        );
                    tel.setCellValueFactory
                        (
                                new PropertyValueFactory<>("tel")
                        );
    }    

    @FXML
    private void back113(ActionEvent event) throws IOException{
        
        try {
          Parent  root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage myWindow = (Stage) back113.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("back ");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void blocker(ActionEvent event) throws SQLDataException {
        
        us.blouer(table.getSelectionModel().getSelectedItem());
        Notifications.create().title("Sucess").text("Bloquer").position(Pos.BOTTOM_RIGHT).showError();
                }

    @FXML
    private void Deblocker(ActionEvent event) throws SQLDataException {
                us.deblouer(table.getSelectionModel().getSelectedItem());
        Notifications.create().title("Sucess").text("User est Debloquer").position(Pos.BOTTOM_RIGHT).showError();
    }

    @FXML
    private void recherche(ActionEvent event) {
        
                           nom.setCellValueFactory
                        (
                                new PropertyValueFactory<>("nom")
                        );
                   prenom.setCellValueFactory
                        (
                                new PropertyValueFactory<>("prenom")
                        );
                   mail.setCellValueFactory
                        (
                                new PropertyValueFactory<>("email")
                        );
                   adresse.setCellValueFactory
                        (
                                new PropertyValueFactory<>("adresse")
                        );
                   date.setCellValueFactory
                        (
                                new PropertyValueFactory<>("datenai")
                        );
                    tel.setCellValueFactory
                        (
                                new PropertyValueFactory<>("tel")
                        );
         List<User> list = us.getAllUser();
            
            //tableview.setItems(observablelist);
            
            FilteredList<User> filtredData= new FilteredList<>(userData, b ->true);
            recherche.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super User> Reservation;
                filtredData.setPredicate((User pointVente) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();
                    if(pointVente.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (pointVente.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<User> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
        
    }
    
}
