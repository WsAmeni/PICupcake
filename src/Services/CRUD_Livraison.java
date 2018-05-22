/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Entities.livraison;
import Tools.DataSource;
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
 * @author user16
 */
public class CRUD_Livraison {
      private Connection connection;
   
   public CRUD_Livraison()    {
        connection = DataSource.getInstance().getConnection();
    }
    
     public void insertC(livraison l) throws SQLException{
                                  
        String requete = "insert into livraison (id_comande,etat) values (?,?)";
        try {
        PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, l.getId_comande());
            ps.setString(2, l.getEtat());            
            ps.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
        public void  deleteC(int id) throws SQLException
            {
            
            PreparedStatement pst; 
           String req="DELETE FROM livraison WHERE id=?";
            pst=connection.prepareStatement(req);
               pst.setInt(1,id);
        try {
                pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            } 
        
         public void updateC(livraison l) throws SQLException {
             PreparedStatement pst;      
        String req="update livraison set etat=? where id=?";
        pst=connection.prepareStatement(req);
        pst.setString(1,l.getEtat());
        pst.setInt(2,l.getIdL());
       
       try {
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } }
      
     public List<livraison> readAll() throws SQLException{
        List<livraison> v=new ArrayList<>();
        Statement ste=connection.createStatement();
        String req="Select * from livraison;";
        ResultSet rs=ste.executeQuery(req);
        while (rs.next()) {
       livraison p=new livraison(rs.getInt(1),rs.getInt(2), rs.getString(3));
        v.add(p);
        }
        return v;
        }
     
     
     
    
}
