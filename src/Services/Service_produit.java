/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.DataSource;


import Entities.Produit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
public class Service_produit {
    
    private Statement st;
    private ResultSet rs;
    PreparedStatement pste;
   private Connection con;

    public Service_produit() {
        con = DataSource.getInstance().getConnection();
    }
    
    //Insertion
  public void insertProduit(Produit p) throws SQLException {
 String requete = "insert into produit(libelle,description,prix,id_patisserie,image,quantite,Reduction) values( '"+p.getLibellé()+"', '"+p.getDescription()+"',"
                  + "'"+p.getPrix()+"','"+p.getId_patisserie()+"','"+p.getImage()+"','"+p.getQuantité()+"','"+p.getReduction()+"' ) ";
          st=con.createStatement();
          st.executeUpdate(requete);
     }
  //delete
     public void DeleteProduit(int id_produit) throws SQLException {
        String requete ="delete from produit where id_produit="+id_produit;
        st=con.createStatement();
        st.executeUpdate(requete);
    }
    
    public void showProductImage(String image) throws SQLException, FileNotFoundException{
        
          try{ 
           pste=con.prepareStatement("select image from produit where image=?");
           pste.setString(1, image);
        rs= pste.executeQuery();
        if(rs.next()){
            InputStream is=rs.getBinaryStream(1);
            OutputStream os=new FileOutputStream("");
        }
    }catch (SQLException ex) {
             Logger.getLogger(Service_produit.class.getName()).log(Level.SEVERE, null, ex);
    }}
     
     //affchige 
     public List<Produit>displayAll() throws SQLException{
        String requete = "select * from produit";
        st=con.createStatement();
        rs= st.executeQuery(requete);
        List<Produit> list=new ArrayList<>();
       
       while (rs.next()){
 Produit p = new Produit (rs.getInt("id_produit"),rs.getString("libelle"), rs.getString("description"),
 rs.getFloat("prix"),rs.getInt("id_patisserie"),
         rs.getString("image"));
           list.add(p); 
       }
       return list;
    }
     
     //affchige 
     public List<Produit>displayAll(int id) throws SQLException{
        String requete = "select * from produit where id_patisserie="+id;
        st=con.createStatement();
        rs= st.executeQuery(requete);
        List<Produit> list=new ArrayList<>();
       
       while (rs.next()){
 Produit p = new Produit (rs.getInt("id_produit"),rs.getString("libelle"), rs.getString("description"),
 rs.getFloat("prix"),rs.getString("quantite"),rs.getInt("Reduction"),rs.getInt("id_patisserie"),
         rs.getString("image"));
           list.add(p); 
       }
       return list;
    }
     
       public void updateP(Produit p) throws SQLException {
        String requete="UPDATE produit SET prix=?,quantite=?,Reduction=? WHERE id_produit=?";
        pste = con.prepareStatement(requete);
     
        pste.setFloat(1, p.getPrix());
        pste.setString(2, p.getQuantité());
        pste.setInt( 3,p.getReduction());
       // pste.setString(7, p.getType());
        pste.setInt(4, p.getId_produit());

try{
        pste.executeUpdate();
    }catch (SQLException ex) {
             Logger.getLogger(Service_produit.class.getName()).log(Level.SEVERE, null, ex);
    }
      } 
       

       
       
}
