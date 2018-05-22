package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static Controllers.LoginController.usernid;
import static Controllers.ProdclientController.idprod;
import Entities.FeedBack;
import Services.CRUD_FeedBack;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
//import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author jihed
 */
public class ListefeedbackController implements Initializable {

    @FXML
    private TableView<FeedBack> feedback;
    @FXML
    private TableColumn<FeedBack, String> Client;
    @FXML
    private TableColumn<FeedBack, String> description;
    @FXML
    private TableColumn<FeedBack, String> date;
    @FXML
    private TextArea id_comment;
    @FXML
    private Button btn_ajout;
    @FXML
    private Label Datef;
    @FXML
    private TableColumn<FeedBack, Integer> id_feedback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Datef.setText(dateFormat.format(date));
        Afficher_Comment(idprod);

    }

    private void Afficher_Comment(int id) {

        try {
            //Client.setCellValueFactory(new PropertyValueFactory<>("id_client"));

            Client.setCellValueFactory((TableColumn.CellDataFeatures<FeedBack, String> FeedBack) -> new SimpleStringProperty(FeedBack.getValue().getClient().getNom()));
            date.setCellValueFactory((TableColumn.CellDataFeatures<FeedBack, String> FeedBack) -> new SimpleStringProperty(FeedBack.getValue().getDate()));
            description.setCellValueFactory((TableColumn.CellDataFeatures<FeedBack, String> FeedBack) -> new SimpleStringProperty(FeedBack.getValue().getDescription()));
            id_feedback.setCellValueFactory((TableColumn.CellDataFeatures<FeedBack, Integer> FeedBack) -> new SimpleIntegerProperty(FeedBack.getValue().getId_feedback()).asObject());
            CRUD_FeedBack cf = new CRUD_FeedBack();
            ObservableList<FeedBack> FeedBacks = FXCollections.observableArrayList((ArrayList<FeedBack>) cf.displayAllFeedBackProd(id));
            feedback.setItems(FeedBacks);
            System.out.println("**********************************");
            System.out.println(id);
            System.out.println("**********************************");
        } catch (SQLException ex) {
            Logger.getLogger(ListefeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Ajouter_Comment(ActionEvent event) throws SQLException {

        CRUD_FeedBack cf = new CRUD_FeedBack();
        FeedBack f = new FeedBack(usernid, idprod, id_comment.getText(), Datef.getText());
        cf.insertFeedBackProd(f);
        refresh();
        System.out.println("**********************************");
        System.out.println(idprod);
        System.out.println("**********************************");
        Afficher_Comment(idprod);
    }

    @FXML
    public void Modifier_Comment() throws SQLException, ParseException {
        FeedBack modifier = new FeedBack();
        CRUD_FeedBack f = new CRUD_FeedBack();
        modifier.setDescription(feedback.getSelectionModel().getSelectedItem().getDescription());
        TablePosition pos = feedback.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        if (pos.getColumn() == 0) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning Dialog");
//            alert.setHeaderText("Erreur");
//            alert.setContentText("Une erreur est survenu lors de la modification");
//            alert.showAndWait();
        }
        if (pos.getColumn() == 1) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning Dialog");
//            alert.setHeaderText("Erreur");
//            alert.setContentText("Une erreur est survenu lors de la modification");
//            alert.showAndWait();
        }
        // Item here is the table view type:
        FeedBack item = feedback.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        // this gives the value in the selected cell:
        String data = (String) col.getCellObservableValue(item).getValue();
//        TextInputDialog dialog = new TextInputDialog(data);
//        dialog.setTitle("Modifier");
//        dialog.setHeaderText("Fenetre de modification");
//        dialog.setContentText("Modifier la cellule:");
//        int Colo = pos.getColumn();
//        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent()) {
//            if (Colo == 2) {
//                modifier.setId_feedback(item.getId_feedback());
//                modifier.setDescription(result.get());
//                f.updateFeedBackP(modifier);
//                System.out.println(modifier);
//            }
//        }
        Afficher_Comment(idprod);
    }
}
