/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.Service_produit;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import static Controllers.LoginController.patid;

/**
 * FXML Controller class
 *
 * @author Donia Khiari
 */
public class StatController implements Initializable {
   Connection con ;
    private Statement st;
       PreparedStatement pste;
           private ResultSet rs;
    @FXML
    private BarChart<String, Float> barchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //   ObservableList< PieChart> list = FXCollections.observableArrayList();
         load(patid);
         
         
    }    

  
    private void load(int id) {
        Service_produit s1=new Service_produit();
        String req ="select libellé,prix FROM produit where id_patisserie="+id+"ORDER BY prix asc";
        XYChart.Series<String, Float> series = new XYChart.Series<>();
       try {
           st = con.createStatement();
                       rs = st.executeQuery(req);
                       
                       while(rs.next())
                       {series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getFloat(2)));}
                       barchart.getData().add(series);
       } catch (SQLException ex) {
           Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
       }


    }
    
}
