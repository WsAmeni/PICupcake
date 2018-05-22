/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Patisserie;
import Entities.Suggestion;
import Services.ClientDAO;
import Services.PatisserieDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class GpatisserieController implements Initializable {

    @FXML
    private SplitPane spp;
    @FXML
    private AnchorPane ap1;
    @FXML
    private Label labp2;
    @FXML
    private TextField txtp;
    @FXML
    private TableView<Patisserie> tbvp;
    @FXML
    private TableColumn<Patisserie, String> log;
    @FXML
    private TableColumn<Patisserie, String> nom;
    @FXML
    private AnchorPane ap2;
    @FXML
    private GridPane gridP;
    @FXML
    private Label info;
    @FXML
    private Button SuppP;
    @FXML
    private Button Modpatiss;
    @FXML
    private TextField ida;
    @FXML
    private TextField logina;
    @FXML
    private TextField noma;
    @FXML
    private TextField emaila;
    @FXML
    private TextField passworda;

    Stage stage;

    private ObservableList<Patisserie> patiss = FXCollections.observableArrayList();
    @FXML
    private TextField etat;
    @FXML
    private TextField verif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();

        tbvp.setOnMouseClicked(event -> {

            ida.setText(String.valueOf(patiss
                    .get(tbvp.getSelectionModel().getSelectedIndex())
                    .getID()));

            logina.setText(patiss
                    .get(tbvp.getSelectionModel().getSelectedIndex())
                    .getLogin());

            noma.setText(patiss
                    .get(tbvp.getSelectionModel().getSelectedIndex())
                    .getNom());

            emaila.setText(patiss
                    .get(tbvp.getSelectionModel().getSelectedIndex())
                    .getEmail());

            passworda.setText(patiss
                    .get(tbvp.getSelectionModel().getSelectedIndex())
                    .getPassword());
            etat.setText(String.valueOf(patiss
                    .get(tbvp.getSelectionModel().getSelectedIndex())
                    .getEtat()));
            verif.setText(String.valueOf(patiss
                    .get(tbvp.getSelectionModel().getSelectedIndex())
                    .getVerif()));
        });

        FilteredList<Patisserie> listeFiltre = new FilteredList<>(patiss, e -> true);
        txtp.textProperty().addListener((observableValue, oldValue, newValue) -> {
            listeFiltre.setPredicate((Predicate<? super Patisserie>) patiss -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (patiss.getLogin().contains(newValue)) {
                    return true;
                }
                return false;
            });
            SortedList<Patisserie> PatissTries = new SortedList<>(listeFiltre);
            PatissTries.comparatorProperty().bind(tbvp.comparatorProperty());
            tbvp.setItems(PatissTries);
        });

        Modpatiss.setOnAction(event -> {

            Patisserie p = new Patisserie(
                    Integer.parseInt(ida.getText()),
                    logina.getText(),
                    noma.getText(),
                    
                    emaila.getText(),
                    passworda.getText(),
                    Integer.parseInt(etat.getText()),
                    Integer.parseInt(verif.getText())
            );
            try {
                PatisserieDAO p1 = new PatisserieDAO();
                p.setImage((p1.findById(Integer.parseInt(ida.getText())).getImage()));
                p1.updateP(p);
            } catch (SQLException ex) {
                Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
            }
            afficher();
        });

        SuppP.setOnAction(event -> {
            PatisserieDAO Mdao = new PatisserieDAO();
            int id = tbvp.getSelectionModel().getSelectedItem().getID();

            try {
                Mdao.deleteP(id);
                afficher();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });

    }

    private void afficher() {
        patiss.clear();
        PatisserieDAO Vdao = new PatisserieDAO();
        patiss = Vdao.getAll();
        tbvp.setItems(patiss);

        log.setCellValueFactory(cell -> cell.getValue().getLogO());
        nom.setCellValueFactory(cell -> cell.getValue().getNomO());
    }
}
