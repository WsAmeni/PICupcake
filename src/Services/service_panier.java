/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.DataSource;
import Entities.Commande;
import Entities.Panier;
import Entities.Produit;
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
public class service_panier {
    Connection con =DataSource.getInstance().getConnection();
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;

    public service_panier() {
        try{
            st=con.createStatement(); } 
        catch(SQLException ex){ System.out.println("ex");}}
    
    //INSERT  PAnier:
     public void inertPa(Produit p) throws SQLException {       
          String requete = "insert into panier (id_produit,libelle, description, prix,id_patisserie,quantite) select id_produit,libelle, description, prix,id_patisserie,quantite from produit where id_produit=?";
             pst= con.prepareStatement(requete);
             pst.setInt(1,(p.getId_produit()));
             pst.executeUpdate();}

     
       //INSERT  Commande:
     public void inertC(Panier p) throws SQLException {       
          String req = "insert into commande(id_produit,libelle, description, prix,id_patisserie,quantite) select id_produit,libelle, description, prix,id_patisserie,quantite from panier";
          st=con.createStatement();
          st.executeUpdate(req);}
           

    //AFFICHAGE
     public List<Panier>display() throws SQLException{
          String requete = "select libelle, description, prix,id_patisserie,quantite from panier where 1";
          st=con.createStatement();
          rs= st.executeQuery(requete);
          List<Panier> list=new ArrayList<>();
       
        while (rs.next()){
          Panier p = new Panier (rs.getString(1), rs.getString(2), rs.getFloat(3),rs.getString(4), rs.getString(5));
          list.add(p); }
        return list;}
     
     
      //DELETE
       public void DeleteP(Panier p ) throws SQLException {
       String query="delete from panier where libelle=?";
             pst= con.prepareStatement(query);
             pst.setString(1,(p.getLibellé()));
             pst.executeUpdate();}
       
       //UPDATE
       public void updateP(Panier p) throws SQLException {
        String requete ="UPDATE `panier` SET `quantite`=? WHERE libelle=?";
            pst=con.prepareStatement(requete);
            pst.setString(1,p.getQuantité());
            pst.setString(2,p.getLibellé());
       try {
            pst.executeUpdate();} 
       catch (SQLException ex) {
            Logger.getLogger(service_panier.class.getName()).log(Level.SEVERE, null, ex);
        } }
    
}
