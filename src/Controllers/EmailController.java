/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author AMANI
 */
public class EmailController implements Initializable {
//    public static User cu;

    @FXML
    private TextField recipientField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField contentField;
    @FXML
    private Button btn;
    @FXML
    private VBox vbox;
    
      private String username ;
      private String password ;

    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
//     public void getUser(String a){
//        recipientField.setText(a);
//        
//    }

    public void envoyer() {
            String username= "Ameni Weslati";
            String password= "ameni7777";
            // Etape 1 : Création de la session
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","587");
            Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
           
                @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                        return new javax.mail.PasswordAuthentication("ameni.weslati7@gmail.com","ameni7777");
                    }
                });
            try {
            // Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ameni.weslati7@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse("ameni.oueslati.1@esprit.tn"));
            message.setSubject(subjectField.getText());
            message.setText(contentField.getText());
            // Etape 3 : Envoyer le message
            Transport.send(message);
            } 
            catch (MessagingException e) {
            throw new RuntimeException(e);
            } }
                @FXML
                private void sendButtonAction(ActionEvent event) {
                   envoyer();
                   Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("E-mail envoyé avec succés !");

                    alert.showAndWait();

                }
                
                 @FXML
                private void retour(ActionEvent event) throws IOException{
                Parent root = FXMLLoader.
                                load(getClass().getResource("/GUI/ValiderPanier.fxml"));           
                Scene scene = new Scene(root);
                Stage app_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                app_Stage.setScene(scene);
                app_Stage.show(); }             
        }    
    

