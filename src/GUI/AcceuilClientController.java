/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.RegisterFXMLController.copier;
import entities.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.Notifications;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;
import utils.Sessions;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AcceuilClientController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private DatePicker datenai;
    @FXML
    private TextField adresse;
    @FXML
    private ImageView imv;
        int c;
        int file;
        File pDir;
        File pfile;
        String lien;
        
         UserService us = new UserService();
    @FXML
    private Button test;
    @FXML
    private TextField idd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };
            tel.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));

            c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
            pDir = new File("src/gui/img/user" + c + ".jpg");
            lien = "user" + c + ".jpg";

            
            User user = Sessions.getLoggedInUser();
            nom.setText(user.getNom());
            prenom.setText(user.getPrenom());
            email.setText(user.getEmail());
            adresse.setText(user.getAdresse());
            tel.setText(String.valueOf(user.getTel()));
            
            imv.setImage(new Image("/gui/img/"+user.getImage()));
            
        idd.setText(""+Sessions.getLoggedInUser().getId());
    }    

    @FXML
    private void Enregistrer(ActionEvent event) throws SQLDataException {
        
            
         User u = new User ();
         
                  if( nom.getText().equals("")  || prenom.getText().equals("") || email.getText().equals("") || tel.getText().equals("0") || adresse.getText().equals("") ){
                                        Notifications.create().title("warnnig").text("Vérifier vos Cordonnée").position(Pos.BOTTOM_RIGHT).showError();
;}

             else if (us.validerEmail(email.getText())) {
                                         Notifications.create().title("warnnig").text("valider Email").position(Pos.BOTTOM_RIGHT).showError();
;
             }
                          else if (tel.getText().length()!=8) {
                                         Notifications.create().title("warnnig").text("valider Votre numéro").position(Pos.BOTTOM_RIGHT).showError();
;
             }
             else{
        Date dd = new java.sql.Date(new Date(datenai.getEditor().getText()).getTime());
        u.setNom(nom.getText());
        u.setEmail(email.getText());
        u.setPrenom(prenom.getText());
        u.setTel(tel.getText());
        u.setAdresse(adresse.getText());
        u.setEmail(email.getText());
        u.setDatenai(dd);
        u.setIsavtive(1);
        u.setId(Sessions.getLoggedInUser().getId());
        if (pfile == null){
        u.setImage(Sessions.getLoggedInUser().getImage());
        }else{
        copier(pfile, pDir);
        u.setImage(lien);
        }

        u.setRole("[\"ROLE_USER\"]");
        us.modifieUser(u);
       Notifications.create().title("Success").text("Votre Profil est bien Modifier").position(Pos.BOTTOM_RIGHT).showConfirm();

           }
    }

    @FXML
    private void Upload(ActionEvent event) throws MalformedURLException {
        
        
                         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
    }
        
    }
    
    
       public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

   
    
}
