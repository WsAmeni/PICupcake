/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AMANI
 */
public class ClientinfController implements Initializable {

    @FXML
    private ImageView inf;
    @FXML
    private ImageView cmd;
    @FXML
    private ImageView pts;

    /**
     * Initializes the controller class.point_fid
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void clickpoints(MouseEvent event) throws IOException{
              Parent root = FXMLLoader.
                    load(getClass().getResource("point_fid.fxml"));
            
            Scene scene = new Scene(root);
            Stage app_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            app_Stage.setScene(scene);
            app_Stage.show();
             
         }
    
     @FXML
    private void clickCommande(MouseEvent event) throws IOException{
              Parent root = FXMLLoader.
                    load(getClass().getResource("Modifier.fxml"));
            
            Scene scene = new Scene(root);
            Stage app_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            app_Stage.setScene(scene);
            app_Stage.show();
             
         }
    
     @FXML
    private void clickInfo(MouseEvent event) throws IOException{
              Parent root = FXMLLoader.
                    load(getClass().getResource("MesInformations.fxml"));
            
            Scene scene = new Scene(root);
            Stage app_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            app_Stage.setScene(scene);
            app_Stage.show();
             
         }
    


    
}
