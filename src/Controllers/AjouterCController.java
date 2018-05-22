/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import Entities.EmailController;
import Entities.Patisserie;
import Services.ClientDAO;
import Services.PatisserieDAO;
import Tools.config2;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class AjouterCController implements Initializable {

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
    private Label titlM;
    Stage stage;
    @FXML
    private CheckBox checkC;
    @FXML
    private Button ajou;
    @FXML
    private ImageView img;
    @FXML
    private Label l;
    @FXML
    private Label n;
    @FXML
    private Label p;
    @FXML
    private Label m;
    @FXML
    private Label md;
    @FXML
    private Label ad;
    @FXML
    private ComboBox<String> combo = new ComboBox<>();
    @FXML
    private Label lbc;

    private void msgbox(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    //control saisi
    //control saisi
    public boolean validateMail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(mail.getText());
        if (m.find() && m.group().equals(mail.getText())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateLogin() {
        ClientDAO cd1 = new ClientDAO();
        PatisserieDAO pd1 = new PatisserieDAO();

        if (((cd1.findByLogin(login.getText())) == null) && ((pd1.findByLogin(login.getText())) == null)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValide(TextField nom, TextField prenom, TextField login, ComboBox combo, TextField mail, TextField mdp,
            TextField adresse) {
        boolean answer = false;

        if (nom.getLength() == 0) {
            this.n.setText("*court");
        } else if (prenom.getLength() == 0) {
            this.p.setText("*court");
        } else if (adresse.getLength() == 0) {
            this.ad.setText("*court");
        } else if (login.getLength() == 0) {
            this.l.setText("*court");
        } else if (validateLogin() == false) {
            this.l.setText("*login existe");
        } else if (mail.getLength() == 0) {
            this.m.setText("*court");
        } else if (validateMail() == false) {
            this.m.setText("mail invalide");
        } else if (mdp.getLength() == 0) {
            this.md.setText("*court");
        } else {
            ajou.setOnAction(event -> {

                Client c = new Client(
                        login.getText(),
                        nom.getText(),
                        prenom.getText(),
                        (String) combo.getValue(),
                        mail.getText(),
                        mdp.getText(),
                        adresse.getText());
                ClientDAO cd1 = new ClientDAO();
                try {
                    cd1.insertC(c);
                    EmailController sendm = new EmailController(c.getMail(), "Bienvenue a Cupcake " + "  "
                            + c.getNom() + " " + c.getPrenom(), "Email :  " + c.getMail()
                            + "\n" + "Login :  " + c.getLogin() + "\n" + "Mot de passe :  " + c.getMdp()
                    );
                    sendm.send();

                    msgbox("Un email vous sera envoyé avec les details");
                    Stage st = new Stage();
                    javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/GUI/login.fxml"));
                    Region root = (Region) loader.load();
                    LoginController ac1 = loader.<LoginController>getController();

                    ac1.setLabC("** Veuiller vous connecter maintenant **");

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    Scene scene = new Scene(root);
                    st.setScene(scene);
                    st.show();

                } catch (SQLException ex) {
                    Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        return answer;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setPromptText("Entrer le nom");
        login.setPromptText("Entrer le login");
        mail.setPromptText("Entrer l'adresse mail");
        mdp.setPromptText("Entrer le mot de passe");
        prenom.setPromptText("Entrer le prenom");
        adresse.setPromptText("Entrer l'adresse");
        combo.getItems().addAll("Homme", "Femme");

        ajou.setOnAction(event -> isValide(nom, prenom, login, combo, mail, mdp, adresse));

    }

    @FXML
    private void CKC(ActionEvent event) {

        ajou.setOpacity(Double.MAX_VALUE);
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public void setPrenom(String prenom) {
        this.prenom.setText(prenom);
    }

    public void setMail(String mail) {
        this.mail.setText(mail);
    }

    public void setAdresse(String adresse) {
        this.adresse.setText(adresse);
    }

    public void setLbc(String lbc) {
        this.lbc.setText(lbc);
    }

}
