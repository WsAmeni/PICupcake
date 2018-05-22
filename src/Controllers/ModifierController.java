package Controllers;

import Entities.Commande;
import Tools.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import Entities.Panier;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Services.service_panier;


/**
 * FXML Controller class
 *
 * @author AMANI
 */
public class ModifierController implements Initializable {

    @FXML
    private TableView<Panier> cmd;
    @FXML
    private TableColumn<?, ?> lib;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<Panier, String> quant;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button rt;  
    @FXML
    private TextField search; 
    @FXML
    private TextField Textlib;
    @FXML
    private TextField Textq;
    @FXML

    Connection con =DataSource.getInstance().getConnection();
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    ObservableList<Panier> data=FXCollections.observableArrayList();
    FilteredList<Panier> filteredData = new FilteredList<>(data, p -> true);
 
   @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          lib.setCellValueFactory(new PropertyValueFactory<>("libellé"));
          desc.setCellValueFactory(new PropertyValueFactory<>("description"));
          prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          quant.setCellValueFactory(new PropertyValueFactory<>("quantité"));   
          LoadDB();
   }    
    
    @FXML
    public void showOnClick() {
        try {
                    
            service_panier sv= new service_panier();
            Panier p = (Panier)cmd.getSelectionModel().getSelectedItem();
            sv.display();
            Textlib.setText(p.getLibellé());
            Textq.setText(p.getQuantité());    
            st.close();
            rs.close();}
        
        catch (SQLException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex); }}
    
    
    @FXML
    public void Delete () throws SQLException {
       
            int selectedIndex = cmd.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {        
            service_panier sp= new service_panier();
            Panier p = (Panier)cmd.getSelectionModel().getSelectedItem();
            sp.DeleteP(p);           
            Textlib.clear();
            Textq.clear();
            data.clear();
            st.close();
            rs.close();
        
            LoadDB();}
       
              
             else {
                // Nothing selected.
                Alert alert = new Alert(AlertType.WARNING);
               // alert.initOwner(MainTest.getStage());
                alert.setTitle("Selection Invalide");
                alert.setHeaderText("Aucun produit sélectionné");
                alert.setContentText("Séléctionner un produit à supprimer !!");
                alert.showAndWait();
            }
    }
    
    
    @FXML
    public void Update () throws SQLException {
            
            service_panier sv= new service_panier();
            Panier p = (Panier)cmd.getSelectionModel().getSelectedItem();
        try {
            while (!Textq.getText().equals("")) {
               p.setQuantité(Textq.getText());
               sv.updateP(p);                 
               cmd.getItems().clear();    
               LoadDB();
               quant.isEditable();
               return;
            }}       
        catch (SQLException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex); 
            LoadDB();
         }
        
        String txt = Textq.getText().trim();
	boolean valid = true;
	if ((txt.isEmpty()) || (txt.length()>2)) {
	    valid = false;
	    Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERREUR");
            String s = "La quantité doit etre inférieure à 100! " + "Entrer une quantité valide et enregistrer!  ";
	    alert.setContentText(s);
	    alert.showAndWait();}
        if (! valid) {
        Textq.requestFocus();
	}
        }
    
            @FXML
            private void Enregistrer(ActionEvent event) throws SQLException{          
                service_panier sc= new service_panier();
                Panier p = (Panier)cmd.getSelectionModel().getSelectedItem();
                sc.inertC(p);
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Enregistrer");
                alert.setHeaderText("Votre Commande est Enregistrée :) ");
                alert.setContentText("Passer votre Commande !!");
                alert.showAndWait();
            }
    
    
    
    
    public void LoadDB()  {
        try {
            String query =" select libelle, description, prix, quantite from panier";          
            st=con.createStatement();
            rs= st.executeQuery(query);
           
            while (rs.next()){
                data.add(new Panier (
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("prix"),
                        rs.getString("quantite")));
                        cmd.setItems(data);}
                        st.close();
                        rs.close(); }
        catch (SQLException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }}
      
      
    @FXML
    private void commander(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.
                    load(getClass().getResource("ValiderPanier.fxml"));         
            Scene scene = new Scene(root);
            Stage app_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            app_Stage.setScene(scene);
            app_Stage.show(); }
    
    
        @FXML
        private void retour(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.
                        load(getClass().getResource("/GUI/prodclient.fxml"));           
            Scene scene = new Scene(root);
            Stage app_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            app_Stage.setScene(scene);
            app_Stage.show(); }
    
        @FXML
        private void recherche(ActionEvent event){
        search.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(Panier -> {
                // If filter text is empty, display all:
                if (newValue == null || newValue.isEmpty()) {
                    return true; }
                // Compare first name and last name field in your object with filter:
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(Panier.getLibellé()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;}
                 // Does not match:
                    return false;
            });
        });
        //  Wrap the FilteredList in a SortedList. 
        SortedList<Panier> sortedData = new SortedList<>(filteredData);

        //  Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(cmd.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        cmd.setItems(sortedData);    
}}
    
