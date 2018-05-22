package Controllers;

import Tools.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.Commande;
import Entities.Points;
import Entities.Produit;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.DataFormat.IMAGE;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import Services.service_commande;

/**
 * FXML Controller class
 *
 * @author AMANI
 */
public class ValiderPanierController implements Initializable {

    @FXML
    private TableView<Commande> t;
    @FXML
    private TableColumn<Commande, Float> prix;
    @FXML
    private Button payer;
    @FXML
    private TableColumn<?, ?> lib;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private TableColumn<?, ?> pat;
    @FXML
    private TableColumn<Commande, String> quantité;
    @FXML
    private Button re;
    @FXML
    private Label totat;
    @FXML
    private Label fid;
    @FXML
    private TextField mail;
    @FXML
    private Button envoyer;
    private String smtpHost = "localhost";
   
    Connection con =DataSource.getInstance().getConnection();
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps; 
    ObservableList<Commande> data=FXCollections.observableArrayList();

    

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        lib.setCellValueFactory(new PropertyValueFactory<>("libellé"));
        desc.setCellValueFactory(new PropertyValueFactory<>("Description"));     
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        pat.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
        quantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));
        LoadDB();

    }

    @FXML
    public void showOnClick() {
        try {
                    
            service_commande sv= new service_commande();
            Commande c = (Commande)t.getSelectionModel().getSelectedItem();
            sv.display();          
            st.close();
            rs.close();}
        catch (SQLException ex) {
            Logger.getLogger(ValiderPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
     public void LoadDB()  {
        try {
            String query ="select libelle, description, prix, id_patisserie, quantite from commande";          
            st=con.createStatement();
            rs= st.executeQuery(query);
           
            while (rs.next()){
                data.add(new Commande (
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("prix"),
                        rs.getString("id_patisserie"),
                        rs.getInt("quantite")));

            t.setItems(data);}
            st.close();
            rs.close(); }
        catch (SQLException ex) {
            Logger.getLogger(ValiderPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }}
     
    /* @FXML
     private void Retours(ActionEvent event) throws IOException{
     Parent root = FXMLLoader.
                    load(getClass().getResource("Modifier.fxml"));
            
            Scene scene = new Scene(root);
            Stage app_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            app_Stage.setScene(scene);
            app_Stage.show();
    }*/
     
     @FXML
     private void Total (ActionEvent event )  throws IOException, SQLException{
               String req ="SELECT SUM(prix*quantite) AS prix_total FROM panier";  
               st=con.createStatement();
               rs= st.executeQuery(req);
               
               while(rs.next()){
               Integer total = rs.getInt("prix_total");
               totat.setText(total.toString()+"  Dt");
}
     }
     
      @FXML
     private void Fid (ActionEvent event )  throws IOException, SQLException{
         Produit p = new Produit();
         Points pn = new Points();
               String req ="SELECT nombre_pts from points_fid where id_client=?";  
                st= con.prepareStatement(req);
                ps.setInt(1,(pn.getId_client()));
                ps.executeUpdate();
               
               String reqe ="SELECT SUM(prix*quantite) AS prix_total FROM panier";  
               st=con.createStatement();
               rs= st.executeQuery(reqe);
               
               while(rs.next()){
                Integer total = rs.getInt("prix_total");               
                Integer tl = rs.getInt("nombre_pts");
                Integer f =  ((tl*100)/total);
                
                fid.setText(f.toString());
               }
     }
     
    
     @FXML
     private void PDF(ActionEvent event) throws IOException, ClassNotFoundException, SQLException, DocumentException{         
        try {
           Class.forName("com.mysql.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/pidev", "root", "");
                Statement st = con.createStatement();

                    /* Define the SQL query */
                    String query ="SELECT * From panier";   
                    ResultSet query_set = st.executeQuery(query);
                    /* Step-2: Initialize PDF documents - logical objects */
                    Document my_pdf_report = new Document();
                    my_pdf_report.addHeader("Titre", "Votre Commande:");
                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream("MaCommande.pdf"));
                    my_pdf_report.open();
                    
                    Font red = new Font(FontFamily.HELVETICA, 15, Font.BOLDITALIC, BaseColor.BLUE);
                     Chunk redText = new Chunk("Votre Commande : ", red);
                      Paragraph p1 = new Paragraph(redText);
                        my_pdf_report.add(p1);
                    
                    //we have 5 columns in our table
                   PdfPTable my_report_table = new PdfPTable(5);
                   Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, 2, BaseColor.RED);
                        
                    Phrase t= new Phrase("Libellé", f);
                    my_report_table.addCell(t);
                    Phrase t1= new Phrase("Description", f);
                    my_report_table.addCell(t1);
                    Phrase t2= new Phrase("Prix", f);
                    my_report_table.addCell(t2);
                    Phrase t3= new Phrase("Patisserie", f);
                    my_report_table.addCell(t3);
                    Phrase t4= new Phrase("Quantité", f);
                    my_report_table.addCell(t4);
                    my_report_table.setHeaderRows(1);

                    //create a cell object
                    PdfPCell table_cell;
                 
                 
                    
                    while (query_set.next()) {  
                                      

//                                    PdfPCell c1 = new PdfPCell(new Phrase("Patisserie"));
//                                    my_report_table.getHeaderRows();
                                    String lib = query_set.getString("libelle");
                                    table_cell=new PdfPCell(new Phrase(lib));
                                    my_report_table.addCell(table_cell);
                                    table_cell.setBorderColor(BaseColor.BLUE);
                                   
                                    
                                   
//                                    PdfPCell c2 = new PdfPCell(new Phrase("description"));
//                                    my_report_table.addCell(c2);
                                    String desc=query_set.getString("description");
                                    table_cell=new PdfPCell(new Phrase(desc));
                                    my_report_table.addCell(table_cell);
                                    
//                                    PdfPCell c3 = new PdfPCell(new Phrase("Prix"));  
//                                    my_report_table.addCell(c3);
                                    String prix=query_set.getString("prix");
                                    table_cell=new PdfPCell(new Phrase(prix));
                                    my_report_table.addCell(table_cell);
                                    
//                                    PdfPCell c4 = new PdfPCell(new Phrase("Patisserie"));  
//                                    my_report_table.addCell(c4);
                                    String pat=query_set.getString("id_patisserie");
                                    table_cell=new PdfPCell(new Phrase(pat));
                                    my_report_table.addCell(table_cell);
                                    
//                                    PdfPCell c5 = new PdfPCell(new Phrase("Quantité"));  
//                                    my_report_table.addCell(c5);
                                    String quant=query_set.getString("quantite");
                                    table_cell=new PdfPCell(new Phrase(quant));
                                    my_report_table.addCell(table_cell);
                                    }
                    
                    /* Attach report table to PDF */
                    my_pdf_report.add(my_report_table);                       
                    my_pdf_report.close();

                    /* Close all DB related objects */
                    query_set.close();
                    st.close(); 
                    con.close();               
    } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (DocumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }    
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Génération PDF");
            alert.setHeaderText("Votre PDF est généré avec succès !! ");
            ButtonType buttonTypeOne = new ButtonType("Ouvrir");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            
            final Button ouv = (Button) alert.getDialogPane().lookupButton(buttonTypeOne);
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                 Application application = new Application() {
                @Override
                public void start(Stage primaryStage) throws Exception {

                }
            };
                        application.getHostServices().showDocument("C:\\Users\\AMANI\\Documents\\MobileProjects\\pidev\\MaCommande.pdf");                
            }  else {
                alert.close();
            }
        }

 @FXML
     private void Mail(ActionEvent event) throws MessagingException, IOException {
         Parent root = FXMLLoader.
                    load(getClass().getResource("/GUI/Email.fxml"));           
            Scene scene = new Scene(root);
            Stage app_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            app_Stage.setScene(scene);
            app_Stage.show(); 
     
     }
           
    
     }





