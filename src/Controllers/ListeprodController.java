/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Produit;
import Services.Service_produit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import static Controllers.LoginController.patid;
import Tools.DataSource;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Donia Khiari
 */
public class ListeprodController implements Initializable {

    @FXML
    private TableView<Produit> id_TV;

    @FXML
    private Label id_libellé;
    @FXML
    private Label id_desc;
    @FXML
    private Label id_prix1;
    @FXML
    private Label dispo;
    @FXML
    private Label id_type1;
    @FXML
    private TextField id_lsaisie;
    @FXML
    private TextField id_desctext;
    @FXML
    private TextField id_prixtext;
    private TextField id_dispotext;
    @FXML
    private TextField quant;
    @FXML
    private TextField reduc;

    @FXML
    private Button id_btnaj;
    private Statement st;
    PreparedStatement pste;

    private ResultSet rs;
   
    final ObservableList nompat = FXCollections.observableArrayList();

    private ObservableList<Produit> data = FXCollections.observableArrayList();

    ObservableList< Produit> list = FXCollections.observableArrayList();
    FilteredList< Produit> filter = new FilteredList<>(list, e -> true);
    //  FilteredList<Produit> filteredData=new FilteredList<>(data,e->true);
    @FXML
    private Button id_save;
    @FXML
    private Button supp;
    
    @FXML
    private ImageView imageview;
    private FileImageInputStream fs;
    @FXML
    private Button btnBrowse;
    final FileChooser fileChooser = new FileChooser();
    private ImageView image;
    @FXML
    private Label fileSelected;

    private String imagepath;
    String imageName;
    WritableImage images;
    @FXML
    private TextField search;
    Connection con=DataSource.getInstance().getConnection();
    @FXML
    private TableColumn<Produit, String> T_lib;
    @FXML
    private TableColumn<Produit, String> T_desc;
    @FXML
    private TableColumn<Produit, Float> T_prix;
    @FXML
    private TableColumn<Produit, String> T_disp;
    @FXML
    private TableColumn<Produit, Integer> T_type;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Affichage();

            T_lib.setCellValueFactory(cell -> cell.getValue().getLibelleProperty());
            T_desc.setCellValueFactory(cell -> cell.getValue().getDescriptionProperty());
            T_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            T_disp.setCellValueFactory(new PropertyValueFactory<>("quantité"));
            T_type.setCellValueFactory(new PropertyValueFactory<>("reduction"));
         
        } catch (SQLException ex) {
            Logger.getLogger(ListeprodController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        ///String libellé, String description, float prix, String disponiblité, int id_patisserie, String type_Produit, String image

        if (validerChamps() && (validerinput()) && (validprix())) {

            Produit p = new Produit(id_lsaisie.getText(), id_desctext.getText(),
                    Float.parseFloat(id_prixtext.getText()), quant.getText(),
                     Integer.parseInt(reduc.getText()),patid, imagepath);

            Service_produit sv_p = new Service_produit();

            sv_p.insertProduit(p);
            id_lsaisie.clear();
            id_desctext.clear();
            id_prixtext.clear();
            quant.clear();
            reduc.clear();
            Affichage();
        }
    }

    private boolean validerChamps() {
        String txt = id_prixtext.getText().trim();
        if (id_lsaisie.getText().isEmpty() | id_desctext.getText().isEmpty() | id_prixtext.getText().isEmpty() 
                //| quant.getText().isEmpty()
                | reduc.getText().isEmpty()) {


            return false;
        }
        return true;
    }

    private boolean validerinput() {

        if (!id_prixtext.getText().matches("[1-50]+")) {
            Alert at = new Alert(Alert.AlertType.WARNING);
            at.setTitle("Valider le champs prix");
            at.setContentText("Veuillez entrer un prix ");
            at.showAndWait();
            return false;

        }
        return true;
    }

    private boolean validprix() {
        String txt = id_prixtext.getText().trim();
        if (txt.length() > 2) {
           Alert at = new Alert(Alert.AlertType.WARNING);
          at.setTitle("Valider le champs prix");
          at.setContentText("Veuillez entrer un prix valide ");
           at.showAndWait();

            return false;

        }
        return true;
    }

    private boolean validersupp() {
        if (id_lsaisie.getText().isEmpty() | id_desctext.getText().isEmpty() | id_prixtext.getText().isEmpty() 
                | reduc.getText().isEmpty()) {
            Alert at = new Alert(Alert.AlertType.WARNING);
            at.setTitle("Valider les champs de saisies");
          at.setContentText("Veuillez sélectionner un produit");
          at.showAndWait();

            return false;
        }

        return true;
    }
//| quant.getText().isEmpty()
    public void Affichage() throws SQLException {
        data.clear();
        Service_produit sv_p = new Service_produit();
        for (Produit p : sv_p.displayAll(patid)) {
            data.add(p);
        }
        id_TV.setItems(data);
    }

    @FXML
    public void showOnClick() throws FileNotFoundException {
        try {
            Produit p;
            p = (Produit) id_TV.getSelectionModel().getSelectedItem();
            Service_produit sv_p = new Service_produit();
            sv_p.displayAll(patid);
            
            id_lsaisie.setText(p.getLibellé());
            id_desctext.setText(p.getDescription());
            id_prixtext.setText(Float.toString(p.getPrix()));
            quant.setText(p.getQuantité());
            reduc.setText(Integer.toString(p.getReduction()));
            String path = data
                    .get(id_TV.getSelectionModel().getSelectedIndex())
                    .getImage();
            String imageURI = new File(path).toURI().toString();
            Image image = new Image(imageURI);
            imageview.setImage(image);
        } catch (SQLException ex) {
            Logger.getLogger(ListeprodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Enregistrer(ActionEvent event) throws SQLException {
        if (validerChamps()) {

            Service_produit sv_p = new Service_produit();
            Produit p = (Produit) id_TV.getSelectionModel().getSelectedItem();

            try {
                while (!id_prixtext.getText().equals("")) {
                    p.setPrix((Float.parseFloat(id_prixtext.getText())));

                    sv_p.updateP(p);

                    id_lsaisie.clear();
                    id_desctext.clear();
                    id_prixtext.clear();
                       quant.clear();
                    reduc.clear();
                    Affichage();
                    return;
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListeprodController.class.getName()).log(Level.SEVERE, null, ex);

            }
            Affichage();
        }

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        if (validersupp()) {
            try {
                Produit p = new Produit();
                p = data.get(id_TV.getSelectionModel().getSelectedIndex());
                Service_produit sv_p = new Service_produit();
                sv_p.DeleteProduit(p.getId_produit());
                id_lsaisie.clear();
                id_desctext.clear();
                id_prixtext.clear();
                  quant.clear();
                 reduc.clear();
                //data.clear();
//            st.close();
//            rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListeprodController.class.getName()).log(Level.SEVERE, null, ex);

            }
            Affichage();
        }

    }

    @FXML
    private void chooseFile(ActionEvent event) throws MalformedURLException, IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(fileSelected.getScene().getWindow());
        if (file != null) {

            imagepath = file.toURI().toURL().toString();
            System.out.println("file:" + imagepath);
            // fileSelected.setText(imagepath);

            imageview.setImage(new Image(imagepath));
            ImageIO.write(SwingFXUtils.fromFXImage(new Image(imagepath), null), "png", file);
            File fileWritten = new File("C:\\Users\\Donia Khiari\\Desktop\\copi\\pidev - Copie\\src\\img\\" + file.getName());
            imagepath = fileWritten.getPath();
            imageName = fileWritten.getName();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Veuillez sélectionner une image");
           alert.setContentText("Vou n'avez pas séléctionner une image ! ");
            alert.showAndWait();
            System.out.println("error img");
        }

    }

    @FXML
    private void search() {
        search.setOnKeyPressed(e -> {
            if ((search.getText().equals(""))) {
                try {
                    Affichage();
                } catch (SQLException ex) {
                    Logger.getLogger(ListeprodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                data.clear();
                String sql = "select * from produit where libelle like '%" + search.getText() + "%'"
                        + "AND id_patisserie="+patid+ " AND type_Produit like '%" + search.getText() + "%' ";
                try {
                    pste = con.prepareStatement(sql);
                    // pste.setString(1,search.getText());
                    rs = pste.executeQuery();
                    while (rs.next()) {
                        data.add(new Produit(rs.getString("libelle"), rs.getString("description"),
                                rs.getFloat("prix"), rs.getString("quantite"), rs.getInt("id_patisserie"),
                                rs.getInt("reduction")));
                    }
                    id_TV.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ListeprodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
