/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Patisserie;
import static Controllers.LoginController.usernid;
import Tools.config2;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import Services.PatisserieDAO;
import static Controllers.LoginController.patid;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author user16
 */
public class PatiissintController implements Initializable {

    @FXML
    private Button fullscreen;
    @FXML
    private Button minimize;
    @FXML
    private Button maximize;
    @FXML
    private Button close;
    @FXML
    private Button resize;
    @FXML
    private ListView<String> listMenu;
    @FXML
    private AnchorPane paneData;
     Stage stage;
    Rectangle2D rec2;
    Double w,h;
    config2 con = new config2();
    @FXML
    private ImageView img;
    private int UP;
    @FXML
    private ImageView img1;
    @FXML
    private Label lab1;
    @FXML
    private Button btnLogout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      rec2 = Screen.getPrimary().getVisualBounds(); 
          listMenu.getItems().addAll("Information","Produits","Boutiques","Abonées","Livraison");
          setUP(patid);
              
            w = 0.1;
            h = 0.1;
          Platform.runLater(() -> {
            stage = (Stage) maximize.getScene().getWindow();
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            resize.setVisible(false);
            
            listMenu.getSelectionModel().select(0);
            con.loadAnchorPane(paneData, "infopat.fxml"); //pane
            listMenu.requestFocus();
    });  }  

 @FXML
    private void aksiMaximized(ActionEvent event) {
        stage = (Stage) maximize.getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
                maximize.getStyleClass().remove("decoration-button-restore");
                resize.setVisible(true);
            }else{
                stage.setMaximized(false);
                maximize.getStyleClass().remove("decoration-button-restore");
                resize.setVisible(true);
            }
            
        }else{
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            resize.setVisible(false);
        }
    }

    @FXML
    private void aksiminimize(ActionEvent event) {
        stage = (Stage) minimize.getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        }else{
            stage.setIconified(true);
        }        
    }

    @FXML
    private void aksiResize(ActionEvent event) {
    }

    @FXML
    private void aksifullscreen(ActionEvent event) {
        stage = (Stage) fullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        }else{
            stage.setFullScreen(true);
        }
    }

    @FXML
    private void aksiClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void aksiKlikListMenu(MouseEvent event) {
        switch(listMenu.getSelectionModel().getSelectedIndex()){
            case 0:{
                con.loadAnchorPane(paneData, "infopat.fxml");
            }break;
            case 1:{
                con.loadAnchorPane(paneData, "listeprod.fxml");
            }break;
            case 2 :{
                con.loadAnchorPane(paneData, "Boutique.fxml");
            }break;   
       
        }
    }

    public void setUP(int i) {
        this.UP = i;
    }

    public void setLab1(int id) {
       PatisserieDAO d1 =new PatisserieDAO();
       Patisserie p1;
        try {
            p1 = d1.findById(id);
         this.lab1.setText(p1.getNom());
        } catch (SQLException ex) {
            Logger.getLogger(PatiissintController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void aksiLogout(ActionEvent event) {
         config2 config = new config2();
        config.newStage2(stage, btnLogout, "/GUI/login.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
   
    }
     
   

   
    
}
