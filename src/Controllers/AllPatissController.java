/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Patisserie;
import Services.PatisserieDAO;
import Services.Recette_Dao;
import Tools.config2;
import com.sun.prism.paint.Paint;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class AllPatissController implements Initializable {

    @FXML
    private AnchorPane a1;
    @FXML
    private ScrollPane sp;
    @FXML
    private AnchorPane pane;

    GridPane grid = new GridPane();
    Label text;
    Label titre;

    String path;
    String imageURI;
    Image image;
    ImageView img;
    private static List<Patisserie> list;
    @FXML
    private Label LabP;
    @FXML
    private AnchorPane dataP;
    config2 con = new config2();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        con.loadAnchorPane(dataP, "GM.fxml"); //pane
        pane.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER_RIGHT);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(60, 0, 0, 60));

        PatisserieDAO d1 = new PatisserieDAO();
        try {
            list = d1.readAll();
            int j = list.size();

            for (int i = 0; i < j; i++) {

                path = list.get(i).getImage();
                imageURI = new File(path).toURI().toString();
                image = new Image(imageURI);
                img = new ImageView(image);
                img.setFitWidth(300);
                img.setFitHeight(150);
                grid.add(img, 0, i);
                

                text = new Label("\n  "+list.get(i).getNom()+"\n"+list.get(i).getEmail()+"\n");
                
                text.setFont(new Font("System", 17));
                text.setPrefWidth(300);
                text.setAlignment(Pos.TOP_LEFT);
                grid.add(text, 1, i);

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllPatissController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.grid.setVisible(true);

    }
}
