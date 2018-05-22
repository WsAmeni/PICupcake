/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.EmailController;
import Entities.Patisserie;
import Services.ClientDAO;
import Services.PatisserieDAO;
import Tools.config2;
import animations.FadeInRightTransition;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class AjouterPController implements Initializable {

    private TextField Id;
    @FXML
    private TextField login;
    @FXML
    private TextField nom;
    @FXML
    private TextField mail;
    @FXML
    private TextField mdp;

    @FXML
    private Label titlM;
    Stage stage;
    config2 config = new config2();
    @FXML
    private CheckBox checkp = new CheckBox();
    @FXML
    private Button ajou;
    @FXML
    private ImageView img;
    @FXML
    private Button ajouP;
    @FXML
    private ImageView AjoutPP;
    @FXML
    private Label l;
    @FXML
    private Label ps;
    @FXML
    private Label m;
    @FXML
    private Label n;

    final FileChooser fileChooser = new FileChooser();
    String imagePath;
    String imageName;
    File file;
    File fileWritten;
    BufferedImage bufferedImage;
    WritableImage image;
    Connection connection;

    private void msgbox(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    
    
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

    public boolean isValide(TextField nom, TextField login, TextField mail, TextField mdp) {
        boolean answer = false;
        if (nom.getLength() == 0) {
            this.n.setText("*court");
        } else if (login.getLength() == 0) {
            this.l.setText("*court");
        } else if (validateLogin() == false) {
            this.l.setText("*login existe");
        } else if (mail.getLength() == 0) {
            this.m.setText("*court");
        } else if (validateMail() == false) {
            this.m.setText("mail invalide");
        } else if (mdp.getLength() == 0) {
            this.ps.setText("*court");
        } else {
           
            ajou.setOnAction(event -> {
                Patisserie c = new Patisserie(login.getText(), nom.getText(),
                        mail.getText(), mdp.getText(), imagePath,0,93207);

                PatisserieDAO cd1 = new PatisserieDAO();
                try {
                    cd1.insertP(c);
                    //envoie du mail 
                    EmailController sendm = new EmailController(c.getEmail(), "Bienvenue a Cupcake " + "  "
                            + c.getNom(), "Veuillez confirmer votre compte" + "\n" + "Email :  " + c.getEmail()
                            + "\n" + "Login :  " + c.getLogin() + "\n" + "Mot de passe :  " + c.getPassword()
                            + "\n" + "Code de verification " + c.getVerif());
                    sendm.send();
                    msgbox("Un email de verification vous sera envoyé ");
                    Stage st = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
                    Region root = (Region) loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    Scene scene = new Scene(root);
                    st.setScene(scene);
                    st.show();

                } catch (SQLException ex) {
                    Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
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
        ajou.setOnAction(event -> isValide(nom, login, mail, mdp));
      
    }

    @FXML
    private void selectCK(ActionEvent event) {
        ajou.setOpacity(Double.MAX_VALUE);
    }

    @FXML
    private void addpict(ActionEvent event) {

        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().add(extFilterPNG);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            fileWritten = new File("C:\\Users\\user16\\Documents\\GitHub\\Cupcake\\src\\img\\" + file.getName());
            imagePath = fileWritten.getPath();
            imageName = fileWritten.getName();

        } catch (IOException ex) {
            Logger.getLogger(NewRecController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


 

}
