/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Entities.Recette;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user16
 */
public class Recette_Dao {
//     id;
//    libelle;duréé;diffic;ingredients;description;
    
     private Connection connection;
   
   public Recette_Dao()
    {
          

        connection = DataSource.getInstance().getConnection();
    }
    
      public void insertR(Recette c) throws SQLException{
                                             
        String requete = "insert into recette (libelle,duréé,diffic,ingredients,description,image,date,id_client) values (?,?,?,?,?,?,?,?)";
        try {
        PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, c.getLibelle());
            ps.setString(2, c.getDuréé());
            ps.setString(3, c.getDiffic());
            ps.setString(4, c.getIngd());
            ps.setString(5, c.getDescription());
            ps.setString(6, c.getImage());
            ps.setLong(7, c.getDate());
            ps.setLong(8, c.getId_client());

            ps.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger(Recette_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      public void  deleteR(Recette r) throws SQLException
            {
                        PreparedStatement pst;      
            String req="DELETE FROM recette WHERE  id=?" ;
            pst=connection.prepareStatement(req);
               pst.setInt(1,r.getId());
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            } 
      
      public void updateR(Recette R) throws SQLException {
         
            PreparedStatement pst;      
        String req="update recette set ingredients=?,description=? where id=?";
        pst=connection.prepareStatement(req);
        pst.setString(1,R.getIngd());
           pst.setString(2,R.getDescription());
        pst.setInt(3,R.getId());
       try {
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } }
      
//     public List<Recette> readAll() throws SQLException{
//        List<Recette> v=new ArrayList<Recette>();
//        Statement ste=connection.createStatement();
//        String req="Select * from recette ORDER By id DESC";
//        ResultSet rs=ste.executeQuery(req);
//        while (rs.next()) {
//        Recette p=new Recette(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getLong(8),rs.getInt(9));
//        v.add(p);
//        }
//        return v;
//        }
     //	id	libelle	duréé	diffic	ingredients	description	image	date	id_client
     
     public ObservableList<Recette> getAll() {
         ObservableList<Recette> list=FXCollections.observableArrayList();       
      String requete = "select * from recette";
          
        try {
            Statement ste=connection.createStatement();
          ResultSet rs=ste.executeQuery(requete);
            while (rs.next()) { 
           Recette p=new Recette(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getLong(8),rs.getInt(9));
                 list.add(p);
            }
        }  catch (SQLException ex) {
             System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        } 
        return  list;
          }

//     
//      public ObservableList<Recette> getAllC(int id) {
//         ObservableList<Recette> list=FXCollections.observableArrayList();       
//      String requete = "select * from recette where id_client=" +id;
//        try {
//            Statement ste=connection.createStatement();
//          ResultSet rs=ste.executeQuery(requete);
//            while (rs.next()) { 
//           Recette p=new Recette(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getLong(8),rs.getInt(9));
//                 list.add(p);
//            }
//        }  catch (SQLException ex) {
//             System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
//        } 
//        return  list;
//          }

     
      public Recette findByID(int id) throws SQLException{
       Recette p=null;
        String requete = "select * from recette where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password
                 p=new Recette(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getLong(8),rs.getInt(9));

                return p; 
            }}  catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }  
           return p;    }

    public void deleteR(int id) throws SQLException {
                PreparedStatement pst;      
            String req="DELETE FROM recette WHERE  id=?" ;
            pst=connection.prepareStatement(req);
               pst.setInt(1,id);
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}

    

