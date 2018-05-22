/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.favoris;
import Services.service_favoris;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static Controllers.LoginController.usernid;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class Liste_favoritController implements Initializable {

    @FXML
    private TableView<favoris> favorit;
    @FXML
    private TableColumn<favoris, String> lib;
    @FXML
    private TableColumn<favoris, String> descr;
    @FXML
    private TableColumn<favoris, Float> prix;
    @FXML
    private Button supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Afficher();
    }

    public void Afficher() {
        lib.setCellValueFactory((TableColumn.CellDataFeatures<favoris, String> favoris) -> new SimpleStringProperty(favoris.getValue().getProduit().getLibellé()));
        descr.setCellValueFactory((TableColumn.CellDataFeatures<favoris, String> favoris) -> new SimpleStringProperty(favoris.getValue().getProduit().getDescription()));
        prix.setCellValueFactory((TableColumn.CellDataFeatures<favoris, Float> favoris) -> new SimpleFloatProperty(favoris.getValue().getProduit().getPrix()).asObject());
        service_favoris SA = new service_favoris();
        try {
            //favoris f = new favoris();
            ObservableList<favoris> favoris = FXCollections.observableArrayList((ArrayList<favoris>) SA.displayfavoris(usernid));
            favorit.setItems(favoris);
        } catch (SQLException ex) {
            Logger.getLogger(Liste_favoritController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer_favoris(ActionEvent event) {

        if (supprimer()) {
            favoris f = (favoris) favorit.getSelectionModel().getSelectedItem();
            service_favoris sf = new service_favoris();
            System.out.println(f.getId_favoris());
            try {
                sf.Deletefavoris(f.getId_favoris());
                // System.out.println("id_abo"+f.getId_favoris());
                Afficher();
            } catch (SQLException ex) {
                Logger.getLogger(Liste_favoritController.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    private boolean supprimer() {
        favoris a = favorit.getSelectionModel().getSelectedItem();
        if (a == null) {
//                 Alert at = new Alert(Alert.AlertType.WARNING);
//            at.setTitle("Valider les champs de saisies");
//            at.setContentText("Veuillez sélectionner un favoris");
//            at.showAndWait();

            return false;
        }

        return true;
    }
}
