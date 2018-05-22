/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Boutique;
import Services.Service_Boutique;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static Controllers.LoginController.patid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleIntegerProperty;
import Entities.Boutique;
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class BoutiqueController implements Initializable {

    @FXML
    private TableView<Boutique> boutiq_TV;
    @FXML
    private Label id_b;
    @FXML
    private TableColumn<Boutique, Integer> num;
    @FXML
    private TextField numTF;
    @FXML
    private Button id_aj;
    @FXML
    private Button supp;
    @FXML
    private Button id_save;
    @FXML
    private TableColumn<Boutique, Double> lat;
    @FXML
    private TableColumn<Boutique, Double> longi;
    @FXML
    private TextField latit;
    @FXML
    private TextField longit;

    Connection con;
    private Statement st;
    private ResultSet rs;
    PreparedStatement pste;
    private ObservableList<Boutique> data = FXCollections.observableArrayList();
    @FXML
    private Label numero;
    @FXML
    private Label lati;
    @FXML
    private Label longitu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Affichage();
            System.out.println(patid);
//        System.out.println(patid);
//        num.setCellValueFactory(new PropertyValueFactory<>("numero"));
//        lat.setCellValueFactory(new PropertyValueFactory<>("lat"));
//        longi.setCellValueFactory(new PropertyValueFactory<>("long"));
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showOnClick(MouseEvent event) {
        try {
            Boutique b = (Boutique) boutiq_TV.getSelectionModel().getSelectedItem();
            Service_Boutique sv_b = new Service_Boutique();
            sv_b.displayAll(patid);

            latit.setText(String.valueOf(b.getLati()));
            numTF.setText(String.valueOf(b.getNumero()));
            longit.setText(String.valueOf(b.getLongi()));

        } catch (SQLException ex) {
            Logger.getLogger(Controllers.BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter_boutique(ActionEvent event) {

        if (validerChamps() && validerinput()) {
            Boutique b = new Boutique(Integer.parseInt(numTF.getText()), Double.parseDouble(latit.getText()), Double.parseDouble(longit.getText()), patid);
            Service_Boutique sv_b = new Service_Boutique();

            try {
                sv_b.insertBoutique(b);

                latit.clear();
                longit.clear();
                numTF.clear();
                Affichage();
            } catch (SQLException ex) {
                Logger.getLogger(Controllers.BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        if (validerChamps()) {
            try {
                Service_Boutique sv_b = new Service_Boutique();
                int b = boutiq_TV.getSelectionModel().getSelectedItem().getId_boutique();
                //System.out.println("aaaaaaaaaaa" + data.get(4));
                System.out.println(b);
                sv_b.DeleteBoutique(b);
                latit.clear();
                longit.clear();
                numTF.clear();
                Affichage();
            } catch (SQLException ex) {
                Logger.getLogger(Controllers.BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);

            }

            
        }
    }

    @FXML
    private void Enregistrer(ActionEvent event) {
        if (validerChamps()) {

            Service_Boutique sv_b = new Service_Boutique();
            Boutique b = (Boutique) boutiq_TV.getSelectionModel().getSelectedItem();

            try {
                while (!numTF.getText().equals("")) {
                    b.setNumero(Integer.parseInt(numTF.getText()));
                    sv_b.updateB(b);

                    latit.clear();
                    longit.clear();
                    numTF.clear();
                    Affichage();
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controllers.BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
    }

    private boolean validersupp() {
        if (latit.getText().isEmpty() | longit.getText().isEmpty() | numTF.getText().isEmpty()) {
//            Alert at = new Alert(Alert.AlertType.WARNING);
//            at.setTitle("Valider les champs de saisies");
//            at.setContentText("Veuillez sélectionner une boutique");
//            at.showAndWait();

            return false;
        }

        return true;
    }

    boolean validerinput() {

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(numTF.getText());
        if (m.find() && (m.group().equals(numTF.getText()))) {
            return true;
        } else {

//            Alert at = new Alert(Alert.AlertType.WARNING);
//            at.setTitle("Valider le champs numero");
//            at.setContentText("Veuillez entrer un numero valide ");
//            at.showAndWait();
            return false;
        }

    }

    private boolean validerChamps() {
        String txt = numTF.getText().trim();

        if (latit.getText().isEmpty() | longit.getText().isEmpty() | (numTF.getText().isEmpty() || (txt.length() < 8) || (txt.length() > 8))) {
//            Alert at = new Alert(Alert.AlertType.WARNING);
//            at.setTitle("Valider les champs de saisies");
//            at.setContentText("Veuillez remplir tous les champs");
//            at.showAndWait();

            return false;
        }

        return true;
    }

    public void Affichage() throws SQLException {
        data.clear();
        Service_Boutique sv_b = new Service_Boutique();

        num.setCellValueFactory((TableColumn.CellDataFeatures<Boutique, Integer> Boutiques) -> new SimpleIntegerProperty(Boutiques.getValue().getNumero()).asObject());
        lat.setCellValueFactory((TableColumn.CellDataFeatures<Boutique, Double> Boutiques) -> new SimpleDoubleProperty(Boutiques.getValue().getLati()).asObject());
        longi.setCellValueFactory((TableColumn.CellDataFeatures<Boutique, Double> Boutiques) -> new SimpleDoubleProperty(Boutiques.getValue().getLongi()).asObject());

        ObservableList<Boutique> Boutiques = FXCollections.observableArrayList((ArrayList<Boutique>) sv_b.displayAll(patid));
        boutiq_TV.setItems(Boutiques);


    }

}
