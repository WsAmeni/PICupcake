/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import Entities.Recette;
import static Controllers.LoginController.usernid;
import Services.ClientDAO;
import Services.Recette_Dao;
import Tools.config2;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class NewRecController implements Initializable {

    @FXML
    private TextField libelle;
    @FXML
    private TextField dur;
    @FXML
    private TextField diff;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
    @FXML
    private Label lab4;
    @FXML
    private ImageView img1;
    @FXML
    private Label lab5;
    @FXML
    private TextArea desc;
    @FXML
    private Button ajoutR;
    @FXML
    private Button ajoutImg;
    @FXML
    private TextArea ingred;
    final FileChooser fileChooser = new FileChooser();
    String imagePath;
    String imageName;
    File file;
    File fileWritten;
    BufferedImage bufferedImage;
    WritableImage image;
    Stage stage;
    @FXML
    private ImageView img3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void ajouterimage(ActionEvent event) {
          //Set extension filter
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().add(extFilterPNG);
              
            //Show open file dialog
            file = fileChooser.showOpenDialog(null);
            
            try {
                bufferedImage = ImageIO.read(file);
                image = SwingFXUtils.toFXImage(bufferedImage, null);
                fileWritten = new File("C:\\Users\\user16\\Documents\\GitHub\\PICupcake\\src\\img\\" + file.getName());
                imagePath = fileWritten.getPath();
                imageName = fileWritten.getName();
                img1.setImage(image);
               // imageDirPath.setText(imageName);
              
               
            } catch (IOException ex) {
                Logger.getLogger(NewRecController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
    try{
         
        Recette a = new Recette(libelle.getText(),dur.getText(),diff.getText(),ingred.getText(),desc.getText(),imagePath,usernid);
            a.setDate(LocalDate.now().toEpochDay());
            Recette_Dao rd1 = new Recette_Dao();
            rd1.insertR(a); 
            ClientDAO cd1=new ClientDAO();
           
               
            Stage st = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ac.fxml"));
                Region root = (Region) loader.load();
                AcController ac1= loader.<AcController>getController();
                ac1.setUC(usernid);
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.hide();
                Scene scene = new Scene(root);
                st.setScene(scene);
                st.show();
        } catch (SQLException ex) {
            Logger.getLogger(NewRecController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
                Logger.getLogger(GclientController.class.getName()).log(Level.SEVERE, null, ex);
            }    
    
}
}
