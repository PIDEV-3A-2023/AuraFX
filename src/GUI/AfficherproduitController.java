/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import services.produitservice;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class AfficherproduitController implements Initializable {

    @FXML
    private TableColumn<produit, Integer> id;
    @FXML
    private TableColumn<produit, String> nom;
    @FXML
    private TableColumn<produit, String> desc;
    @FXML
    private TableColumn<produit, String> cat;
    @FXML
    private TableColumn<produit, String> prix;
    @FXML
    private TableColumn<produit, String> nbr;
    @FXML
    private TableView<produit> tab;
    produitservice ps = new produitservice();
    produit produit = new produit();
    @FXML
    private Button add;
    @FXML
    private TableColumn<produit, String> actions;
    @FXML
    private Button refresh;
    @FXML
    private TableColumn<produit, String> image;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    } 
    
    public void load(){
        List<produit>l= ps.getAll();
        ObservableList lp=FXCollections.observableArrayList(l); 
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
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

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            
                            produit = tab.getSelectionModel().getSelectedItem();
                            ps.supprimer(produit);
                            
                            
                            
                            
                            
                            
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            produit = tab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("editproduit.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherproduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            EditproduitController EditproduitController = loader.getController();
                            //EditproduitController.setUpdate(true);
                            EditproduitController.setTextField(produit.getNom(), produit.getDesc(), produit.getCategorie(), produit.getNbr(), produit.getPrix(), produit.getImage(),produit.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                            
                            
                            
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        
         actions.setCellFactory(cellFoctory);
        tab.setItems(lp);
    }

    @FXML
    private void Toadd(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("addproduit.fxml"));
            add.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        load();
    }

    
}
