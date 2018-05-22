/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.favoris;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.service_favoris;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class Ajout_favorisController implements Initializable {

    @FXML
    private TextField idProduit;
    @FXML
    private TextField idClient;
    @FXML
    private Button aj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouté(ActionEvent event) throws SQLException, IOException{
        int id_c=Integer.parseInt(idClient.getText());
        int id_p=Integer.parseInt(idProduit.getText());
         System.out.println("id _c :"+id_c+" Id_p "+id_p);
        favoris f = new favoris(id_c,id_p);
        service_favoris sf = new service_favoris();
            sf.insertfavoris(f);        
        
    }
    
}
