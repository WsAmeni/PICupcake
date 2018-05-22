/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Recette;
import static Controllers.LoginController.usernid;
import Entities.Client;
import Services.ClientDAO;
import Services.Recette_Dao;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.Region;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class RecettesController implements Initializable {

    @FXML
    private TableView<Recette> table;
    @FXML
    private TableColumn<Recette, String> col;
    @FXML
    private AnchorPane ancr;
    @FXML
    private TextArea ingred;
    @FXML
    private ImageView img1;
    @FXML
    private TextArea brief;
    @FXML
    private TextArea descript;
    @FXML
    private Label labtitre;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    private ObservableList<Recette> recs = FXCollections.observableArrayList();
    @FXML
    private Button modifR;
    @FXML
    private Button suppR;
    @FXML
    private Button ajouR;
    @FXML
    private TextField sF;
    @FXML
    private Label labcl;
    ClientDAO cd1=new ClientDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        try {
            afficher();
            ancr.setVisible(false);

            FilteredList<Recette> listeFiltre = new FilteredList<>(recs, e -> true);
            sF.textProperty().addListener((observableValue, oldValue, newValue) -> {
                listeFiltre.setPredicate((Predicate<? super Recette>) rec -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    } 
                    String lowerCaseFilter = newValue.toLowerCase();
                
                        if ((rec.getIngd().contains(newValue)) || (rec.getLibelle().contains(newValue)))
//                                ||(cd1.findByID(rec.getId_client())).getLogin().contains(newValue)  ) {
                                {  return true;
                        }
                   
                    return false;
                });
                SortedList<Recette> RecsTries = new SortedList<>(listeFiltre);
                RecsTries.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(RecsTries);
            });

        } catch (SQLException ex) {
            Logger.getLogger(RecettesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void afficher() throws SQLException {
        recs.clear();
        Recette_Dao cd1 = new Recette_Dao();
        recs = cd1.getAll();
        table.setItems(recs);
        col.setCellValueFactory(cell -> cell.getValue().getLibelleO());

    }

    @FXML
    private void selectrec(MouseEvent event) throws SQLException {
        ancr.setVisible(true);
        ingred.setEditable(false);
        descript.setEditable(false);
       
        String path = recs
                .get(table.getSelectionModel().getSelectedIndex())
                .getImage();
        String imageURI = new File(path).toURI().toString();
        Image image = new Image(imageURI);
        img1.setImage(image);
        
        ingred.setText(recs
                .get(table.getSelectionModel().getSelectedIndex()).getIngd());
        labtitre.setText(recs
                .get(table.getSelectionModel().getSelectedIndex()).getLibelle());
        brief.setText("   Durée : "
                + recs.get(table.getSelectionModel().getSelectedIndex()).getDuréé() + "\n" + "   Difficullte : "
                + recs.get(table.getSelectionModel().getSelectedIndex()).getDiffic()
        );
        descript.setText(recs
                .get(table.getSelectionModel().getSelectedIndex()).getDescription()
        );

        int idr = recs.get(table.getSelectionModel().getSelectedIndex()).getId_client();
        String cl=cd1.findByID(idr).getNom()+" "+cd1.findByID(idr).getPrenom();
        labcl.setText("Par :" +"  "+cl);
        
        if (idr == usernid) {
            modifR.setVisible(true);
            suppR.setVisible(true);
            ingred.setEditable(true);
            descript.setEditable(true);
        } else {
            modifR.setVisible(false);
            suppR.setVisible(false);
        }

    }

    @FXML
    private void modifR(ActionEvent event) {
        int Id = recs.get(table.getSelectionModel().getSelectedIndex()).getId();
        Recette r = new Recette(Id, ingred.getText(), descript.getText());
        try {
            Recette_Dao cd1 = new Recette_Dao();
            cd1.updateR(r);
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ancr.setVisible(false);
    }

    @FXML
    private void suppR(ActionEvent event) throws SQLException {
        Recette_Dao r1 = new Recette_Dao();
        Recette R = table.getSelectionModel().getSelectedItem();

        try {
            r1.deleteR(R);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        afficher();
        ancr.setVisible(false);
    }

    @FXML
    private void ajouR(ActionEvent event) {
        try {

            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/newRec.fxml"));
            Region root = (Region) loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();

        } catch (IOException ex) {
            Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
