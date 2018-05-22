/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Patisserie;
import Entities.Reclamation;
import Services.CRUD_Reclamation;
import Services.PatisserieDAO;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static Controllers.LoginController.usernid;
import Services.ClientDAO;
import Services.SendMail;

/**
 * FXML Controller class
 *
 * @author jihed
 */
public class ReclamationController implements Initializable {

    String mail = "";
    @FXML
    private Label titlM;
    @FXML
    private TextField CC;
    @FXML
    private ComboBox<String> CP;
    @FXML
    private TextField reclmationtxt;
    @FXML
    private Button btnReclamer;
    @FXML
    private TextField Date;
    CRUD_Reclamation cr = new CRUD_Reclamation();
    PatisserieDAO p = new PatisserieDAO();
    ClientDAO c = new ClientDAO();
    List<Patisserie> cp;

//    Notifications notificationBuilder;
//    Node graphic;
//    String Text = "helo";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Date.setText(dateFormat.format(date));
        try {
            CC.setText(c.findByID(usernid).getNom()+" "+c.findByID(usernid).getPrenom());

            cp = p.readAll();
            for (int i = 0; i < cp.size(); i++) {
                CP.getItems().add(cp.get(i).getNom());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isValide(String text) {
        if (text.matches("^[a-zA-Z ]+$")) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void Reclamer(ActionEvent event) throws SQLException {
        if (!CC.getText().trim().isEmpty() && !reclmationtxt.getText().trim().isEmpty()) {
            if (isValide(reclmationtxt.getText())) {
                String val1 = CP.getValue();
                System.out.println(usernid);
                System.out.println(val1);

                Reclamation r = new Reclamation(usernid, p.findByName(val1).getID(), reclmationtxt.getText(), Date.getText());
                cr.insertReclamation(r);
                SendMail sm = new SendMail();
                sm.envoyer(c.findByID(usernid).getMail(),"Reclamation","Votre reclamation est envoyer avec succes");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Ajout reclamation");
//                alert.setHeaderText("Information sur l'ajout");
//                alert.setContentText("la Réclamation a été bien ajouté!");
//                alert.show();
                Notifications.create().title("Reclamation").text("Reclamation envoyer ").darkStyle().hideAfter(Duration.seconds(30)).showConfirm();
            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Ajout reclamation");
//                alert.setHeaderText("Erreur sur l'ajout");
//                alert.setContentText("il vous manque des informations a remplir!");
//                alert.show();
                Notifications.create().title("Erreur").text("champ de reclamation non valide! ").darkStyle().hideAfter(Duration.seconds(30)).showWarning();
            }
//        } else if (reclmationtxt.getText().trim().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Ajout reclamation");
//            alert.setHeaderText("Erreur sur l'ajout");
//            alert.setContentText("il vous manque des informations a remplir!");
//            alert.show();
//            Notifications.create().title("Attention").text("Vous devez ecrire une reclamation ").darkStyle().showError();
////            btnReclamer.setOnAction(e -> {
//                graphic = null;
//                notification(Pos.TOP_LEFT, graphic, Text);
//                notificationBuilder.showWarning();
//            });
        }
    }
//
//    private void notification(Pos pos, Node graphic, String Text) {
//        notificationBuilder = Notifications.create()
//                .title("Notification")
//                .text(Text)
//                .graphic(graphic)
//                .hideAfter(Duration.seconds(40))
//                .position(pos)
//                .onAction((ActionEvent arg0) -> {
//                    System.out.println("Notifiation is Clicked");
//                });
//    }

}
