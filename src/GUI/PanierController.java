/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import entities.achat;
import entities.facture;
import entities.membre;
import entities.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.mail.MessagingException;
import services.EmailSender;
import services.achatservice;
import services.factureservice;
import services.panier;
import services.pdf;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class PanierController implements Initializable {
    
    @FXML
    private Button back;
    @FXML
    private TableView<produit> tabpan;
    @FXML
    private TableColumn<produit, String> image;
    @FXML
    private TableColumn<produit, String> nom;
    @FXML
    private TableColumn<produit, String> actions;
    produit produit;
    @FXML
    private Button payerb;
    @FXML
    private Label somme;
    factureservice fs =new factureservice();
    achatservice as =new achatservice();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
    }    
    public void load(){
    
        Map<produit, Integer> p = panier.getInstance().getProduitsQuantites();
        ObservableList<produit> produits = FXCollections.observableArrayList(p.keySet());
        ObservableList<Integer> quantites = FXCollections.observableArrayList(p.values());
        image.setCellValueFactory(new PropertyValueFactory<produit, String>("image"));
            image.setCellFactory(param -> {
                return new TableCell<produit, String>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            try {
                                // Load the image from the file path
                                Image image = new Image(new File(item).toURI().toString());
                                imageView.setImage(image);
                                imageView.setFitHeight(100);
                                imageView.setFitWidth(100);
                                setGraphic(imageView);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            });
        Callback<TableColumn<produit, String>, TableCell<produit, String>> cellFoctory;
        cellFoctory = (TableColumn<produit, String> param) -> {
            // make cell containing buttons
            final TableCell<produit, String> cell;
            cell = new TableCell<produit, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        MaterialIconView upicon = new MaterialIconView(MaterialIcon.ARROW_DROP_UP);
                        MaterialIconView downicon = new MaterialIconView(MaterialIcon.ARROW_DROP_DOWN);
                        Integer i =panier.getInstance().getProduitsQuantites().get(tabpan.getSelectionModel().getSelectedItem());
                        Label quantityLabel = new Label("");
                        quantityLabel.setText(String.valueOf(i));
                        setGraphic(quantityLabel);
                        upicon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        downicon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        +  "-fx-fill:#ff1744;"
                        );
                        upicon.setOnMouseClicked((MouseEvent event) -> {
                            
                            
                            produit = tabpan.getSelectionModel().getSelectedItem();
                            panier.getInstance().ajouterProduit(produit);
                            //ps.supprimer(produit);
                            load();
                            
                            
                            
                            
                            
                        });
                        downicon.setOnMouseClicked((MouseEvent event) -> {
                            
                            produit = tabpan.getSelectionModel().getSelectedItem();
                            panier.getInstance().decreaseValue(produit);
                            
                            load();
                            
                            
                        });

                        HBox managebtn = new HBox(downicon,quantityLabel, upicon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(upicon, new Insets(2, 0, 0, 3));
                        HBox.setMargin(quantityLabel, new Insets(2, 3, 0, 0));
                        HBox.setMargin(downicon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        actions.setCellFactory(cellFoctory);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tabpan.setItems(produits);
        somme.setText("montant Totale "+panier.getInstance().calculerSommeTotale());
    }
    @FXML
    private void afficher(ActionEvent event) {
       // for(produit p :panier.getInstance().getProduitsQuantites().keySet()){
        System.out.println(panier.getInstance().getProduitsQuantites());//}
        
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("produitfront.fxml"));
            
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void payer(ActionEvent event) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = localDate.format(formatter);
        Date date= Date.valueOf(localDate);
        membre m=new membre(16, "ben nasr", "azer", "azerbennasr@gmail.com", "0000");
        
        facture f =new facture(m, panier.getInstance().calculerSommeTotale(), date);
        fs.ajouter(f);
        f=fs.lastfacture();
        for (Map.Entry<produit, Integer> entry : panier.getInstance().getProduitsQuantites().entrySet()) {
            produit key = entry.getKey();
            int value = entry.getValue();
            achat a=new achat(f, m, key, value, value*key.getPrix());
            as.ajouter(a);
            
       }
        List<achat> la=as.achatparfact(f.getId());
        pdf.genererFacturesansouvrir(""+f.getId(), f.getMontant(), f.getDate(), la);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String message ="Bonjour Mr/Mme "+m.getNom()+" "+m.getPrenom()+" vos achats afféctuée le "+sdf.format(f.getDate())+"sont confirmés vous trouverez au dessous votre facture";
        //EmailSender.with(m.getMail(), "Subject", message);   
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("facturefront.fxml"));
            
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
