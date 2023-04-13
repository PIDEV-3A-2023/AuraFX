/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.categorie;
import entities.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import services.categorieservice;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class AffichercatController implements Initializable {

    @FXML
    private TableColumn<categorie, String> id;
    @FXML
    private TableColumn<categorie, String> nom;
    @FXML
    private TableColumn<categorie, String> nbr;
    @FXML
    private TableColumn<categorie, String> image;
    @FXML
    private TableColumn<categorie, String> actions;
    categorie c = new categorie();
    categorieservice cs = new categorieservice();
    @FXML
    private TableView<categorie> tab;
    @FXML
    private Button ajouter;
    @FXML
    private Button refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
    }    
    public void load(){
        List<categorie> l =cs.getAll();
        ObservableList lc=FXCollections.observableArrayList(l); 
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        image.setCellValueFactory(new PropertyValueFactory<categorie, String>("image"));
            image.setCellFactory(param -> {
                return new TableCell<categorie, String>() {
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
            Callback<TableColumn<categorie, String>, TableCell<categorie, String>> cellFoctory;
        cellFoctory = (TableColumn<categorie, String> param) -> {
            // make cell containing buttons
            final TableCell<categorie, String> cell;
            cell = new TableCell<categorie, String>() {
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
                            
                            
                            c = tab.getSelectionModel().getSelectedItem();
                            cs.supprimer(c);
                            load();
                            
                            
                            
                            
                            
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            c = tab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("editcat.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherproduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            EditcatController EditcatController = loader.getController();
                            //EditproduitController.setUpdate(true);
                            EditcatController.setTextField(c.getNom(),c.getNbrproduit(),c.getImage(),c.getId());
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
         tab.setItems(lc);   
    }

    @FXML
    private void toadd(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("addcat.fxml"));
            ajouter.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        load();
    }
    
}
