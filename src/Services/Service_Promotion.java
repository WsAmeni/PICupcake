/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Boutique;
import Entities.Produit;
import Entities.Promotion;
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
 * @author Donia Khiari
 */
public class Service_Promotion {
    Connection con ;
    private Statement st;
    private ResultSet rs;
    PreparedStatement pste;

    public Service_Promotion() {
              con = DataSource.getInstance().getConnection();

    }
    
    
    
      //Insertion
  public void insertPromotion(Promotion P) throws SQLException {
 String requete = "insert into promotion (id_patisserie,id_produit,reduction) values(?,?,?) ";
   try {
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1,P.getId_patisserie() );
            ps.setInt(2,P.getId_produit() );
            ps.setString(3, P.getReduction());
           
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Service_Promotion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    //delete
     public void DeletePromotion(int id_promo) throws SQLException {
        String requete ="delete from promotion where id_promo="+id_promo;
        st=con.createStatement();
        st.executeUpdate(requete);
    }
     
      //affchige 
     public List<Promotion>displayAll(int id) throws SQLException{
         
        String requete = "select * from promotion where id_patisserie="+id;
        st=con.createStatement();
        rs= st.executeQuery(requete);
        List<Promotion> list=new ArrayList<>();
       
       while (rs.next()){
 Promotion P = new Promotion(rs.getInt("id_promo"),rs.getInt("id_patisserie"), rs.getInt("id_produit"),rs.getString("reduction"));
           list.add(P); 
       }
       return list;
    }
     
     
       public void updatePromo(Promotion P) throws SQLException {
        String requete="UPDATE promotion SET reduction=? where id_promo=?";
        pste = con.prepareStatement(requete);
         pste.setString(1,P.getReduction());
         pste.setInt(2, P.getId_promotion());



try{
        pste.executeUpdate();
    }catch (SQLException ex) {
             Logger.getLogger(Service_Boutique.class.getName()).log(Level.SEVERE, null, ex);
    }
       }
       
        public Produit findByLib(String lib) {
        Produit p = null;
        String requete = "select * from produit where libellé=?";
        try {
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, lib);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password
                p = new Produit(rs.getInt(1), rs.getString(2));
                return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }
        return p;
    }
       
 public Produit findById(int id) {
        Produit p = null;
        String requete = "select * from produit where id_produit=?";
        try {
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password
                p = new Produit(rs.getInt(1), rs.getString(2));
                return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }
        return p;
    }
  
}

     
     

