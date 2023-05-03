/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FXML Controller class
 *
 * @author issamfekih.if
 */
package GUI;

import entity.Affectations;
import entity.Technicien;
import entity.Terrain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CRUDaffectations;
import services.CRUDtechnicien;

public class FrontAffectationsController implements Initializable {

    @FXML
    private TableView<Affectations> tvListeA;
    @FXML
    private TableColumn<Affectations, Date> LDebut;
    @FXML
    private TableColumn<Affectations, Date> LDateFin;
    
    CRUDaffectations a = new CRUDaffectations();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
        List<Affectations> Affectation = a.afficherAffectations();
        ObservableList<Affectations> LISTE = FXCollections.observableArrayList(Affectation);
        LDebut.setCellValueFactory(cellData -> new SimpleObjectProperty<>(new Date(cellData.getValue().getDateDebut().getTime())));
        LDateFin.setCellValueFactory(cellData -> new SimpleObjectProperty<>(new Date(cellData.getValue().getDateFin().getTime())));

        tvListeA.setItems(LISTE);
    }
}
