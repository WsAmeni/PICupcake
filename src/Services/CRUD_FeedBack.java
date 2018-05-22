/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Entities.FeedBack;
import Entities.Patisserie;
import Entities.Produit;
import Tools.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author jihed
 */
public class CRUD_FeedBack {
    PreparedStatement pste;
    ResultSet rs;
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    Date date= new java.sql.Date(Calendar.getInstance().getTime().getTime());
    public CRUD_FeedBack (){
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void insertFeedBackP(FeedBack f) throws SQLException { //PreparedStatement
        String requete = "insert into feedback (id_client,id_patisserie,description,date) values (?,?,?,?)";
        pste = con.prepareStatement(requete);
//        pste.setInt(1,f.getClient().getId());
//        pste.setInt(2,f.getPatisserie().getID());
        pste.setInt(1,f.getId_client());
        pste.setInt(2,f.getId_patisserie());
        pste.setString(3, f.getDescription());
        pste.setDate(4, date);
        pste.executeUpdate();
    }
    
    public void insertFeedBackProd(FeedBack f) throws SQLException { //PreparedStatement
        String requete = "insert into feedback (id_client,id_produit,description,date) values (?,?,?,?)";
        pste = con.prepareStatement(requete);
        pste.setInt(1,f.getId_client());
        pste.setInt(2,f.getId_produit());
        pste.setString(3, f.getDescription());
        pste.setDate(4, date);     
        pste.executeUpdate();
    }
    
    
    public void deleteFeedBackP(int idf) throws SQLException {
        String requete = "delete from feedback where id_feedback = "+idf;
        ste = con.createStatement();
        ste.executeUpdate(requete);
    }
    
    
    public void updateFeedBackP(FeedBack f) throws SQLException {
        String requete="UPDATE feedback SET description=? WHERE id_feedback=? ";
        pste = con.prepareStatement(requete);
        pste.setString(1, f.getDescription());
       pste.setInt(2,f.getId_feedback());
        pste.executeUpdate();
    }
    
    
    public List<FeedBack> displayAllFeedBackP (int idp) throws SQLException{        
        String requete ="SELECT * FROM feedback \n" +
                        "INNER JOIN client ON feedback.id_client = client.id \n" +
                        "INNER JOIN patisserie ON feedback.id_patisserie = patisserie.id \n" +
                        "WHERE feedback.id_patisserie ="+idp;

        
        ste=con.createStatement();
        rs=ste.executeQuery(requete);
        List<FeedBack> list=new ArrayList<>();
        while (rs.next()){
            FeedBack f = new FeedBack(rs.getInt("id_feedback"),rs.getString("date"),new Patisserie (rs.getInt("patisserie.id_patisserie"),rs.getString("patisserie.nom")),new Client (rs.getInt("client.id_client"), rs.getString("client.Nom")),rs.getString("description"));
            list.add(f);
        }
        return list;
    }
    
    public List<FeedBack> displayAllFeedBackProd (int idp) throws SQLException{        
        String requete ="SELECT * FROM feedback \n" +
                        "INNER JOIN client ON feedback.id_client = client.id \n" +
                        "INNER JOIN produit ON feedback.id_produit = produit.id_produit \n" +
                        "WHERE feedback.id_produit ="+idp;

        
        ste=con.createStatement();
        rs=ste.executeQuery(requete);
        List<FeedBack> list=new ArrayList<>();
        while (rs.next()){
            FeedBack f = new FeedBack(rs.getInt("id_feedback"),rs.getString("date"),new Produit (rs.getInt("produit.id_produit"),rs.getString("produit.libellé")),new Client (rs.getInt("client.id"), rs.getString("client.nom")),rs.getString("description"));
            list.add(f);
        }
        return list;
    }
}
