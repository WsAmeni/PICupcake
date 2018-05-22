/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import static Controllers.LoginController.usernid;
import Services.ClientDAO;
import Tools.config2;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class AcController implements Initializable {

    @FXML
    private ListView<String> listMenu;
    @FXML
    private AnchorPane paneData;
    Stage stage;
    Rectangle2D rec2;
    Double w,h;
    config2 con = new config2();
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
    private AnchorPane anchpane;
    
    @FXML
    private Label UC;
    @FXML
    private ImageView imgg;
    @FXML
    private Button btnLogout;
    
   
   
    //String usercncted=username.getText();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          rec2 = Screen.getPrimary().getVisualBounds(); 
          listMenu.getItems().addAll( "Produits","Patisseries","Recettes","Mon compte","Reclamtion","Panier","Commande");
          
          UC.setText(String.valueOf(usernid));    
                
           w = 0.1;
            h = 0.1;
      //  username.setText(usercncted);
          Platform.runLater(() -> {
            stage = (Stage) maximize.getScene().getWindow();
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            resize.setVisible(false);
            
            listMenu.getSelectionModel().select(0);
            con.loadAnchorPane(paneData, "prodclient.fxml"); //pane
            listMenu.requestFocus();
            
        });
          
    }

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
            case 2:{
                con.loadAnchorPane(paneData, "recettes.fxml");
            }break;
            case 3:{
                con.loadAnchorPane(paneData, "comptC.fxml");
            }break;
            case 1:{
                con.loadAnchorPane(paneData, "allPatiss.fxml");
            }break;
            case 4:{
                con.loadAnchorPane(paneData, "Reclamation.fxml");
            }break;
            case 0:{
                con.loadAnchorPane(paneData, "prodclient.fxml");
            }break;
              case 5:{
                con.loadAnchorPane(paneData, "Modifier.fxml");
            }break;
             case 6:{
                con.loadAnchorPane(paneData, "ValiderPanier.fxml");
            }break;
            
        }
    }

    public Label getUC() {
        return UC;
    }

    
    public void setUC(int u) throws SQLException {
         Client p1;
         ClientDAO ctDao=new ClientDAO();
         p1 = ctDao.findByID(usernid);
        this.UC.setText(p1.getNom());
    }
    
    public int getUsernid() {
        return usernid;
    }
    public void setUsernis(int u)
    {  usernid =u;
    }

    @FXML
    private void aksiLogout(ActionEvent event) {
          config2 config = new config2();
        config.newStage2(stage, btnLogout, "/GUI/login.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
    
    

   

   


    
    

  

    
}
