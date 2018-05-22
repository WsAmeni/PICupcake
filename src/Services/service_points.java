/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.DataSource;
import Entities.Points;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMANI
 */
public class service_points {
    
 Connection con =DataSource.getInstance().getConnection();
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;

    public service_points() {
        try{
            st=con.createStatement();
            
        }catch(SQLException ex){ System.out.println("ex");
    }}
    
     
       
     //AFFICHAGE  
     public List<Points>displayAllP() throws SQLException{
        String requete = "SELECT * FROM `points_fid` WHERE 1";
        st=con.createStatement();
        rs= st.executeQuery(requete);
        List<Points> list=new ArrayList<>();
       
       while (rs.next()){
           Points p = new Points (rs.getInt("id_patisserie"), rs.getInt(2), rs.getInt("nombre_pts"));
           list.add(p); }
       return list;
    }
      
     
}

