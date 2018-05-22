/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.LoginController.patid;
import Entities.Rate;
import Services.CRUD_Rate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jihed
 */
public class RateController implements Initializable {

    @FXML
    private Slider rateslide = new Slider();
    @FXML
    private Button btn_rate;
    CRUD_Rate r = new CRUD_Rate();
    @FXML
    private TextField rate;
    @FXML
    private Label Rating;
    @FXML
    private Label ratenbr;
    @FXML
    private Label ratetotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            rate.setText("Rate : "+String.valueOf(r.findById(1).getRate())+" Nombre : "+String.valueOf(r.findById(1).getNbre_rate()));
            ratetotal.setText("Rate : "+String.valueOf(r.findById(1).getRate()));
            ratenbr.setText("Nombre : "+String.valueOf(r.findById(1).getNbre_rate())); 
        } catch (SQLException ex) {
            Logger.getLogger(RateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    private void RatingButton(ActionEvent event) throws SQLException {
        int nbr = (r.findById(1).getNbre_rate())+1;
        Rate ra = new Rate(((r.findById(1).getRate()+ rateslide.getValue())/2),nbr,1);
        r.updateRateP(ra);
        System.out.println(nbr);
        rate.setText(String.valueOf(r.findById(1).getRate()));
        Rating.setText(String.valueOf(rateslide.getValue()));
    }

    @FXML
    private void Ratescroll(MouseEvent event) throws SQLException {
        Rating.setText(String.valueOf(rateslide.getValue()));
        rate.setText(String.valueOf((r.findById(1).getRate()+rateslide.getValue())/2));
    }




    
}
