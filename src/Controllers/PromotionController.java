/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Promotion;
import Services.Service_Promotion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static Controllers.LoginController.patid;
import Entities.Produit;
import Tools.DataSource;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class PromotionController implements Initializable {

    @FXML
    private Label id_prod;
    @FXML
    private Label id_red;
    @FXML
    private TextField redTF;
    @FXML
    private Button id_eng;
    @FXML
    private Button bt_aj;
    @FXML
    private Button bt_supp;
    @FXML
    private ComboBox<String> prodcombo;
    @FXML
    private TableView<Promotion> promotTV;
    @FXML
    private TableColumn<Promotion, Integer> prodTV;
    @FXML
    private TableColumn<Promotion, String> reducTV;
    private Statement st;
    private ResultSet rs;
    PreparedStatement pste;
    final ObservableList optionss = FXCollections.observableArrayList();
    Connection con = DataSource.getInstance().getConnection();
    private ObservableList<Promotion> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     Affichage();

        //prodcombo.setValue("Select product");
        prodcombo.setItems(optionss);

        // TODO
    }

    @FXML
    private void Enregistrer_promotion(ActionEvent event) {
        if (validerChamps()) {
            Service_Promotion sv_P = new Service_Promotion();
            Promotion P = (Promotion) promotTV.getSelectionModel().getSelectedItem();
            try {
                while (!redTF.getText().equals("")) {
                    P.setReduction(redTF.getText());
                    System.out.println("PROMO ID SET" + redTF.getText());
                    sv_P.updatePromo(P);

                    redTF.clear();
                    Affichage();
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PromotionController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // promotTV.getItems().clear();

        }
    }

    @FXML
    private void ajouter_promotion(ActionEvent event) {
        Service_Promotion sp1 = new Service_Promotion();
        if (validerChamps() && validerinput()) {
            Produit p1 = sp1.findByLib(prodcombo.getSelectionModel().getSelectedItem());
            Promotion P = new Promotion(patid, p1.getId_produit(), redTF.getText());

            try {
                sp1.insertPromotion(P);

            } catch (SQLException ex) {
                Logger.getLogger(Controllers.PromotionController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Affichage();
        }

    }

    @FXML
    private void Supprimer_promotion(ActionEvent event) {
        if (validersupp()) {
            try {
                // Promotion P = new Promotion((Integer.parseInt(promoTV.getText())), patissTV.getText(),prodTV.getText(), reducTV.getText());
                // Promotion P = (Promotion) promotTV.getSelectionModel().getSelectedItem();
                Promotion P = new Promotion();
                P = data.get(promotTV.getSelectionModel().getSelectedIndex());
                Service_Promotion sv_P = new Service_Promotion();
                sv_P.DeletePromotion(P.getId_promotion());

                redTF.clear();
            } catch (SQLException ex) {
                Logger.getLogger(Controllers.PromotionController.class.getName()).log(Level.SEVERE, null, ex);

            }
            redTF.clear();
//        combopatiss_id.getSelectionModel().clearSelection();
//        prodcombo.getItems().clear();

            Affichage();
        }

    }

    @FXML
    private void showOnClick(MouseEvent event) {
        try {
            Service_Promotion sv_P = new Service_Promotion();
            Promotion P = (Promotion) promotTV.getSelectionModel().getSelectedItem();
            sv_P.displayAll(patid);
            //  promoTF.setText(Integer.toString((P.getId_promotion())));
            String val = sv_P.findById(P.getId_produit()).getLibellé();
            prodcombo.setValue(val);
            redTF.setText(P.getReduction());

        } catch (SQLException ex) {
            Logger.getLogger(Controllers.PromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Affichage() {
        data.clear();
        Service_Promotion sv_p = new Service_Promotion();
        try {
            for (Promotion p : sv_p.displayAll(patid)) {
                System.out.println(p);
                data.add(p);
                System.out.println("testtestets" + data);
            }
            promotTV.setItems(data);

        reducTV.setCellValueFactory(new PropertyValueFactory<>("reduction"));
        prodTV.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        } catch (SQLException ex) {
            Logger.getLogger(Controllers.PromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean validerChamps() {
        if (prodcombo.getSelectionModel().getSelectedItem().isEmpty() | redTF.getText().isEmpty()) {
//            Alert at = new Alert(Alert.AlertType.WARNING);
//            at.setTitle("Valider les champs de saisies");
//            at.setContentText("Veuillez remplir tous les champs");
//            at.showAndWait();

            return false;
        }

        return true;
    }

    boolean validerinput() {

        Pattern p = Pattern.compile("[10-90]+");
        Matcher m = p.matcher(redTF.getText());
        if (m.find() && (m.group().equals(redTF.getText()))) {
            return true;
        } else {
//       
//            Alert at = new Alert(Alert.AlertType.WARNING);
//            at.setTitle("Valider le champs reduction");
//            at.setContentText("Veuillez entrer une reduction valide en % ");
//            at.showAndWait();

            return false;
        }

    }

    private boolean validersupp() {
        if (prodcombo.getSelectionModel().getSelectedItem().isEmpty() | redTF.getText().isEmpty()) {
//            Alert at = new Alert(Alert.AlertType.WARNING);
//            at.setTitle("Valider les champs de saisies");
//            at.setContentText("Veuillez séléctioner la promotion");
//            at.showAndWait();

            return false;
        }

        return true;
    }

    @FXML
    private void RemplirComboprod(Event event) {
        String test = prodcombo.getSelectionModel().getSelectedItem();
        //  String req ="select libellé from produit where id_patisserie= 2";
        String req = " SELECT libellé FROM produit where id_patisserie = " + patid;

        try {
            optionss.clear();
            pste = con.prepareStatement(req);
            rs = pste.executeQuery();
            while (rs.next()) {
                optionss.add(rs.getString("libellé"));

            }

            pste.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Service_Promotion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
