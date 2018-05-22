/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import Entities.Patisserie;
import Services.ClientDAO;
import Services.PatisserieDAO;
import Tools.config2;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import animations.FadeInLeftTransition;
import animations.FadeInLeftTransition1;
import animations.FadeInRightTransition;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Entities.EmailController;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblClose;
    Stage stage;
    @FXML
    private ImageView img11;
    @FXML
    private ComboBox<String> combobox = new ComboBox<>();
    @FXML
    private Text aslabel;
    @FXML
    private Button insrire;
    static int usernid;
    static int patid;
    @FXML
    private Text labC;
    @FXML
    private Button fb;
    String msg;
    @FXML
    private Label title;
    @FXML
    private ImageView imgf;
    @FXML
    private AnchorPane anv;
    @FXML
    private TextField verif;
    @FXML
    private Text lblver;
    @FXML
    private Label FPsd;

    private void msgbox(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anv.setVisible(false);
        Platform.runLater(() -> {
            txtUsername.setPromptText("entrer le login");
            txtPassword.setPromptText("entrer le mot de passe");
            new FadeInRightTransition(lblUserLogin).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
            new FadeInRightTransition(aslabel).play();
            combobox.getItems().addAll("Administrateur", "Client", "Patisserie");
            new FadeInRightTransition(combobox).play();
            new FadeInRightTransition(lblClose).play();
            new FadeInLeftTransition1(insrire).play();
            new FadeInRightTransition(labC).play();
            new FadeInRightTransition(fb).play();
            new FadeInRightTransition(FPsd).play();

            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
        });

    }

    @FXML
    private void aksiLogin(ActionEvent event) throws SQLException {
        String user = txtUsername.getText();
        String pswd = txtPassword.getText();
        ClientDAO ctDao = new ClientDAO();
        PatisserieDAO ptDao = new PatisserieDAO();
       String val = combobox.getValue();
           
        switch (val) {
            case"":
                msgbox("choisir utilisateur");
                break;
            
            case "Administrateur":

                if ((user.equals("admin")) && (pswd.equals("admin"))) {
                    config2 config = new config2();
                    config.newStage2(stage, btnLogin, "/GUI/cp.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
                } //Control saisi
                else {
                    msgbox("Données invalides , Verifier les champs !");
                    txtUsername.setText("");
                    txtPassword.setText("");
                }

                break;

            case "Patisserie":
                if ((user.length() > 0) && (pswd.length() > 0)) {
                    Patisserie p1;
                    p1 = ptDao.findByLogin(user);
                    System.out.println(p1);
                    if (p1 == null) {
                        msgbox("  Veuillez vous inscrire !");
                    }
                    
                    if ((p1.getEtat() == 1) && (user.equals(p1.getLogin())) && (pswd.equals(p1.getPassword()))) {

                        try {
                            anv.setVisible(false);
                            Stage st = new Stage();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/patiissint.fxml"));
                            Region root = (Region) loader.load();
                            PatiissintController ac1 = loader.<PatiissintController>getController();

                            patid = p1.getID();
                            ac1.setUP(patid);
                            ac1.setLab1(patid);

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.hide();
                            Scene scene = new Scene(root);
                            st.setScene(scene);
                            st.show();

                        } catch (IOException ex) {
                            Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                    if ((p1.getEtat() == 0) && (user.equals(p1.getLogin())) && (pswd.equals(p1.getPassword())) ) {
                        anv.setVisible(true);
                        if (Integer.parseInt(verif.getText()) == p1.getVerif()) {
                            p1.setEtat(1);
                            ptDao.updateP(p1);
                            try {
                                Stage st = new Stage();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/patiissint.fxml"));
                                Region root = (Region) loader.load();
                                PatiissintController ac1 = loader.<PatiissintController>getController();

                                patid = p1.getID();
                                ac1.setUP(patid);
                                ac1.setLab1(patid);

                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.hide();
                                Scene scene = new Scene(root);
                                st.setScene(scene);
                                st.show();

                            } catch (IOException ex) {
                                Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            labC.setText("Verifier le code envoyé !");
                        }
                    } 
                    if (!((user.equals(p1.getLogin())) && (pswd.equals(p1.getPassword())))) {
                        msgbox("  Login ou mot de passe erroné !");

                    }
                } else {
                    msgbox("Données invalides , Verifier les champs !");
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
                break;

            case "Client":
                if ((user.length() > 0) && (pswd.length() > 0)) {
                    Client p1;
                    p1 = ctDao.findByLogin(user);
                    System.out.println(p1);
                    if (p1 == null) {
                        msgbox("  Veuillez vous inscrire !");
                    } else{
                    if (user.equals(p1.getLogin()) && pswd.equals(p1.getMdp()) && p1.getEtat() == 1) {
                        try {
                            Stage st = new Stage();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ac.fxml"));
                            Region root = (Region) loader.load();
                            AcController ac1 = loader.<AcController>getController();

                            usernid = p1.getId();
                            ac1.setUC(usernid);

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.hide();
                            Scene scene = new Scene(root);
                            st.setMaximized(true);
                            st.initStyle(StageStyle.UNDECORATED);
                            st.setScene(scene);
                            st.show();

                        } catch (IOException ex) {
                            Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (user.equals(p1.getLogin()) && pswd.equals(p1.getMdp()) && p1.getEtat() == 0) {
                        msgbox("  Compte blocké !");
                    } else if (!(user.equals(p1.getLogin()) && pswd.equals(p1.getMdp()))) {
                        msgbox("  Login ou mot de passe erroné !");
                    }
                } }
                else {
                    msgbox("Données invalides, Verifier les champs !");
                    txtUsername.setText("");
                    txtPassword.setText("");
                }

                break;
        }
    }

    public void setLabC(String labC) {
        this.labC.setText(labC);
    }

    @FXML
    private void aksINSCR(ActionEvent event) {
        config2 config = new config2();
        String val = combobox.getValue();
        if (val.equals("Client")) {
            config.newStage2(stage, insrire, "/GUI/ajouterC.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);

        } else if (val.equals("Patisserie")) {
            config.newStage2(stage, insrire, "/GUI/ajouterP.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);

        }
    }

    @FXML
    private void fblogin(ActionEvent event) throws SQLException {

        String domain = "https://google.com";
        String appId = "2008278769420484";
//        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_about_me";
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_about_me,email,public_profile";
        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        while (true) {

            if (!driver.getCurrentUrl().contains("facebook.com")) {
                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me", User.class);
                driver.quit();
                String nom = user.getName();
                String[] splited = nom.split("\\s+");
                System.out.println(nom);

                ClientDAO cd1 = new ClientDAO();
                Client c = cd1.findByName(splited[1]);
                if (c == null) {
                    try {
                        Stage st = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ajouterC.fxml"));
                        Region root = (Region) loader.load();
                        AjouterCController ac1 = loader.<AjouterCController>getController();
                        ac1.setNom(splited[1]);
                        ac1.setPrenom(splited[0]);
                        ac1.setLbc("Completer votre inscription");
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.hide();
                        Scene scene = new Scene(root);
                        st.setScene(scene);
                        st.show();

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        Stage st = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ac.fxml"));
                        Region root = (Region) loader.load();
                        AcController ac1 = loader.<AcController>getController();

                        usernid = c.getId();
                        ac1.setUC(usernid);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.hide();
                        Scene scene = new Scene(root);
                        st.setMaximized(true);
                        st.initStyle(StageStyle.UNDECORATED);
                        st.setScene(scene);
                        st.show();

                    } catch (IOException ex) {
                        Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }
    }

    @FXML
    private void forgotPass(MouseEvent event) {
        String usL = txtUsername.getText();
        ClientDAO cd1 = new ClientDAO();
        PatisserieDAO pd1 = new PatisserieDAO();
        Patisserie p1 = pd1.findByLogin(usL);
        Client c1 = cd1.findByLogin(usL);

        if (c1 != null) {
            EmailController sendm = new EmailController(c1.getMail(), "Recuperation de votre compte   " + c1.getPrenom(), "Email :  " + c1.getMail() + "\n" + "Login :  " + c1.getLogin() + "\n" + "Mot de passe :  " + c1.getMdp());
            sendm.send();

        }
        if (p1 != null) {
            EmailController sendm = new EmailController(c1.getMail(), "Recuperation de votre compte   " + p1.getNom(), "Email :  " + p1.getEmail() + "\n" + "Login :  " + p1.getLogin() + "\n" + "Mot de passe :  " + p1.getPassword());
            sendm.send();

        }

    }

}
