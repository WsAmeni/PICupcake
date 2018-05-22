/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import static Controllers.LoginController.usernid;
import Services.ClientDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class InfoclientController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField mail;
    @FXML
    private TextField mdp;
    @FXML
    private TextField adresse;
    @FXML
    private Button editC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Client p1;
        ClientDAO ctDao = new ClientDAO();
     
            p1 = ctDao.findByID(usernid);

            login.setText(p1.getLogin());
            nom.setText(p1.getNom());
            prenom.setText(p1.getPrenom());
            mail.setText(p1.getMail());
            mdp.setText(p1.getMdp());
            adresse.setText(p1.getAddress());


    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Client c = new Client(
                usernid,
                login.getText(),
                nom.getText(),
                prenom.getText(),
                mail.getText(),
                mdp.getText(),
                adresse.getText());
        try {
            ClientDAO cd1 = new ClientDAO();
            cd1.updateCC(c);

            login.setText("");
            nom.setText("");
            prenom.setText("");
            mail.setText("");
            mdp.setText("");
            adresse.setText("");

            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ac.fxml"));
            Region root = (Region) loader.load();
            AcController ac1 = loader.<AcController>getController();
            ac1.setUC(usernid);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();

        } catch (SQLException ex) {
            Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
