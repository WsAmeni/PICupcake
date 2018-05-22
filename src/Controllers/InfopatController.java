/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import Entities.Patisserie;
import static Controllers.LoginController.patid;
import Services.ClientDAO;
import Services.PatisserieDAO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class InfopatController implements Initializable {

    @FXML
    private Button modif;
    @FXML
    private TextField login;
    @FXML
    private TextField mdp;
    @FXML
    private TextField mail;
    @FXML
    private TextField nom;
    @FXML
    private ImageView imgP;
    String path;
    String imageURI;
    Image image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Patisserie p1;
        PatisserieDAO ptDao = new PatisserieDAO();
        try {
            p1 = ptDao.findById(patid);
            login.setText(p1.getLogin());
            nom.setText(p1.getNom());
            mail.setText(p1.getEmail());
            mdp.setText(p1.getPassword());

            String path = p1.getImage();
            imageURI = new File(path).toURI().toString();
            image = new Image(imageURI);
            imgP.setImage(image);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {

        Patisserie c = new Patisserie(
                patid, login.getText(),
                nom.getText(),
                mail.getText(), mdp.getText()
        );
        PatisserieDAO pd1 = new PatisserieDAO();
        try {
            Patisserie p1 = pd1.findById(patid);
            String path = p1.getImage();
            c.setImage(path);
            pd1.updateP(c);

            login.setText("");
            nom.setText("");

            mail.setText("");
            mdp.setText("");

            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/patiissint.fxml"));
            Region root = (Region) loader.load();
            PatiissintController ac1 = loader.<PatiissintController>getController();

            patid = c.getID();
            ac1.setUP(patid);
            ac1.setLab1(patid);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();

        } catch (SQLException ex) {
            Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InfopatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
