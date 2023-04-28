/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.membre ; 
import Entities.Solde;
import Entities.Terrain;
import Entities.membre;
import Services.CrudSolde;
import Services.crudTerrain;

import Services.CrudMembre;
import Utils.PDFTerrain;
import Utils.SendMail;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;
import com.sun.prism.paint.Color;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Cyrine
 */
public class ListTerrainController implements Initializable {

    @FXML
    private Text topText;
    @FXML
    private Button addPub;
    @FXML
    private VBox mainVBox;
    @FXML
    private AnchorPane mainPain;
     @FXML
    private Button exportPDF;
       @FXML
    private Button Stat;
      @FXML
    private Button tri;
    @FXML
    private Button recherche;
    @FXML
    private TextField txtAdresse;
    private TextField txtSurface;
   
    
    
 crudTerrain ts = new crudTerrain ();
    CrudSolde cs = new CrudSolde();
    public static Terrain p;
    public static int terrainId = 0;
    /**
     * Initializes the controller class.
     */ 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
       List<Terrain> listPub = ts.afficherAll();

        if (!listPub.isEmpty()) {
            for (Terrain abo : listPub) {
               
                mainVBox.getChildren().add(makePubModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    
    }    
public Parent makePubModel(Terrain terrain) {
        Parent parent = null;

        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModelTerrain.fxml")));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#id")).setText("id : " +terrain.getId_terrain());
         ((Text) innerContainer.lookup("#idPartenaire")).setText("membre : " + terrain.getMembre().getId_membre());
            ((Text) innerContainer.lookup("#surface")).setText("surface : " + terrain.getSurface());
            ((Text) innerContainer.lookup("#adresse")).setText("adresse : " + terrain.getAdresse());
            ((Text) innerContainer.lookup("#potentiel")).setText("potentiel : " + terrain.getPotentiel());
 
            
            ((Pane) innerContainer.lookup("#commentPane")).setVisible(false);

            ((Pane) innerContainer.lookup("#sss")).setVisible(true);
            ((Button) innerContainer.lookup("#btnModifier")).setOnAction((event) -> {
p=terrain;
 try {
                    Parent root = FXMLLoader.load(getClass().getResource("UpdateTerrain.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            });
            ((Button) innerContainer.lookup("#btnDelete")).setOnAction((event) -> { 
               
                ts.supprimerTerrain(terrain.getId_terrain()); 
      
            }); 
            ((Button) innerContainer.lookup("#btnAddSolde")).setOnAction((event) -> {
                ((Pane) innerContainer.lookup("#commentPane")).setVisible(true);
                ((Button) innerContainer.lookup("#confirm")).setOnAction((e) -> {
                     if ("".equals(((TextField) innerContainer.lookup("#txtMontant")).getText().trim())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("You have to fill required fields!");

            alert.showAndWait();
              
        }
                    java.util.Date date = new java.util.Date();
                    
                     cs.create( Integer.parseInt(((Text) innerContainer.lookup("#id")).getText().replaceAll("[^\\d]", "")), Double.parseDouble(((TextField) innerContainer.lookup("#txtMontant")).getText()));

                    String montant = ((TextField) innerContainer.lookup("#txtMontant")).getText();
   
     
                                   try {
                                       CrudMembre c = new CrudMembre();
                                     
                                       membre membre = c.getMembreById(Integer.parseInt((((Text) innerContainer.lookup("#idPartenaire")).getText()).split(":")[1].trim()));
                                   String emailSansEspaces = membre.getEmail().replaceAll("\\s", ""); 
                                     
                                        SendMail sendEmail = new SendMail("pidev.aura@gmail.com", "aldeuwdqmtbotzry", emailSansEspaces  , "Solde added succesfully","Cher(e) client(e), Nous espérons que ce message vous trouve en bonne santé et que vous profitez bien de nos services. Nous avons le plaisir de vous informer que le solde de votre terrain a été ajouté avec succès sur notre application, Nous restons à votre disposition pour toute question ou assistance supplémentaire, Nous tenons à vous remercier pour votre confiance et votre fidélité. Nous sommes impatients de vous offrir la meilleure expérience possible sur notre plateforme, \"L'équipe d'AURA" );
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
                
                });

            });
            ((Button) innerContainer.lookup("#btnQrCode")).setOnAction((event) -> {
                try {
                  Image bufferedImage=  generateQRCodeImage(terrain.toString(),200,200);
                       ImageView qrView = new ImageView();
        qrView.setImage(bufferedImage);
          
             innerContainer.getChildren().add(qrView); 
           // mainVBox.getChildren().add(qrView);
          
                } catch (WriterException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                }
      
            });
 
            ((Button) innerContainer.lookup("#btnAfficheSoldes")).setOnAction((event) -> {
             
                p = new Terrain(terrain.getId_terrain(), terrain.getAdresse(), terrain.getSurface(), terrain.getPotentiel(),terrain.getMembre());
             
                try {
                    terrainId =terrain.getId_terrain();
                    Parent root = FXMLLoader.load(getClass().getResource("ListSoldes.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    @FXML
    private void addPubAction(ActionEvent event) {
  
             
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("AddTerrain.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
          
    }
      @FXML
 private void exportPDFAction(ActionEvent event) {
        try {
            PDFTerrain pdf = new PDFTerrain();
            pdf.pdfGeneration();
            System.out.println("done");
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }
    }
         private Image generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
             BitMatrix  bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

    BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
   ByteArrayOutputStream out = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", out);
    out.flush();
    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    return new Image(in);

         } 
         
         
    @FXML
    private void triAction(ActionEvent event) {
         crudTerrain a = new crudTerrain();
        List<Terrain> listTerrain = a.displayTerrainsTrier();
      if (!listTerrain.isEmpty()) {
          //supp tous les elements de la boite main . 
          mainVBox.getChildren().clear();
            for (Terrain abo : listTerrain) {
                mainVBox.getChildren().add(makePubModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }
     @FXML  
    private void rechercheAction(ActionEvent event) {
             Label label = null;
             //test vide 
        if ("".equals(txtAdresse.getText())){
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("You have to insert the required fields ?");
        alert.setContentText("");
              
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
           label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
          
      
            
        } else if (option.get() == ButtonType.CANCEL) {
            label.setText("Exit!");
        } else {
   
            label.setText("-");
        }
    }else{
        crudTerrain a = new crudTerrain();
        List<Terrain> listTerrain = a.displayTerrainRecherche(txtAdresse.getText());
      if (!listTerrain.isEmpty()) {
          mainVBox.getChildren().clear();
            for (Terrain abo : listTerrain) {
                mainVBox.getChildren().add(makePubModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
        }
    }
    
       @FXML 
   private void statAction(ActionEvent event) {
    List<Terrain> listTerrain = ts.afficherAll();

    Map<Terrain, Double> soldesParTerrain = new HashMap<>();
    for (Terrain terrain : listTerrain) {
        double sommeSoldes = new CrudSolde().getSommeSoldesParTerrain(terrain.getId_terrain());
        soldesParTerrain.put(terrain, sommeSoldes);
    }

    List<Terrain> terrainsTries = soldesParTerrain.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

    mainVBox.getChildren().clear();

    Text titre = new Text("Top 3 des terrains les plus bénéficiaires :");
    titre.setFont(Font.font("Arial", FontWeight.BOLD, 16));
  
    mainVBox.getChildren().add(titre);

    VBox top3Box = new VBox();
    top3Box.setAlignment(Pos.CENTER_LEFT);
    top3Box.setSpacing(10);
    top3Box.setPadding(new Insets(10));

    for (int i = 0; i < 3 && i < terrainsTries.size(); i++) {
        Terrain terrainBeneficie = terrainsTries.get(i);
        double sommeSoldes = soldesParTerrain.get(terrainBeneficie);

        HBox terrainBox = new HBox();
        terrainBox.setAlignment(Pos.CENTER_LEFT);
        terrainBox.setSpacing(10);

        Text idTerrain = new Text(String.format("Terrain %d : ", terrainBeneficie.getId_terrain()));
        idTerrain.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text adresseTerrain = new Text(terrainBeneficie.getAdresse());
        Text soldeText = new Text(String.format("Somme des soldes : %.2f TND", sommeSoldes));

        terrainBox.getChildren().addAll(idTerrain, adresseTerrain, soldeText);

        top3Box.getChildren().add(terrainBox);
    }

    mainVBox.getChildren().add(top3Box);
}   
   
 }
    