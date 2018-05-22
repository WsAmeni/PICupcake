/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Recette;
import Services.ClientDAO;
import Services.Recette_Dao;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class RecAdController implements Initializable {

    @FXML
    private TableView<Recette> tbvrc;
    @FXML
    private TableColumn<Recette, String> logCl;
    @FXML
    private TableColumn<Recette, String> libRec;
    @FXML
    private TableColumn<Recette, Integer> Idec;
    @FXML
    private Button suppR;
    private ObservableList<Recette> recs = FXCollections.observableArrayList();
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        
        tbvrc.setOnMouseClicked((MouseEvent event) -> {
            
        String path = recs
                .get(tbvrc.getSelectionModel().getSelectedIndex())
                .getImage();
        String imageURI = new File(path).toURI().toString();
        Image image = new Image(imageURI);
        img.setImage(image);
           
        });
        
        suppR.setOnAction(event->{
               Recette_Dao rd = new Recette_Dao();
            int id = tbvrc.getSelectionModel().getSelectedItem().getId();

            try {
                rd.deleteR(id);
                afficher();
                

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        
        });

    }

    private void afficher() {
        recs.clear();
        Recette_Dao rc = new Recette_Dao();
        recs = rc.getAll();
        tbvrc.setItems(recs);
        ClientDAO cd1= new ClientDAO();
        
        logCl.setCellValueFactory(cell ->new SimpleStringProperty(cd1.findByID(cell.getValue().getId_client()).getNom()));
        libRec.setCellValueFactory(cell -> cell.getValue().getLibelleO());
        Idec.setCellValueFactory(new PropertyValueFactory<>("id"));

        
    }

}
