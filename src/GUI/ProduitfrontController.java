/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.produitservice;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class ProduitfrontController implements Initializable {

    @FXML
    private TableView<produit> tab;
    @FXML
    private TableColumn<produit, String> image;
    @FXML
    private TableColumn<produit, String> nom;
    produit p = new produit();
    produitservice ps = new produitservice();
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<produit>l= ps.getAll();
        ObservableList lp=FXCollections.observableArrayList(l); 
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
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
        tab.setItems(lp);
        // TODO
        FilteredList<produit> filterdata=new FilteredList<>(lp,b ->true);
        search.textProperty().addListener((observable,oldvalue,newvalue)->{
            filterdata.setPredicate(produit -> {
        if (newvalue == null || newvalue.isEmpty() ) {
            return true;
        }
        String lowerCaseSearchText = newvalue.toLowerCase();
        if (produit.getNom().toLowerCase().contains(lowerCaseSearchText) ) {
            return true;
        }
        return false;
    });

        });
        SortedList<produit> sorteddata = new SortedList<>(filterdata);
        sorteddata.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sorteddata);
    }    

    @FXML
    private void onmouse(MouseEvent event) {
        p= tab.getSelectionModel().getSelectedItem();
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("singleproduit.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                // pass the selected post to the single post controller
                SingleproduitController controller = loader.getController();
                controller.setproduit(p);

                Stage stage = (Stage) tab.getScene().getWindow();
        stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
   
    
    
}
