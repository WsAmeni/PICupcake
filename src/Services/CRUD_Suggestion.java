/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Entities.Patisserie;
import Entities.Produit;
import Entities.Suggestion;
import Tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jihed
 */
public class CRUD_Suggestion {
    PreparedStatement pste;
    ResultSet rs;
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    public CRUD_Suggestion (){
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void insertSuggestionP(Suggestion s) throws SQLException { //PreparedStatement
        String requete = "insert into suggestion (id_client_emetteur,id_client_recepteur,id_patisserie) values (?,?,?)";
        pste = con.prepareStatement(requete);
        pste.setInt(1,s.getClient_emetteur().getId());
        pste.setInt(2,s.getClient_recepteur().getId());
        pste.setInt(3,s.getPatisserie().getID());
        pste.executeUpdate();
    }
//    
    public void insertSuggestionProd(Suggestion s) throws SQLException { //PreparedStatement
        String requete = "insert into `suggestion` (`id_client_emetteur`, `id_client_recepteur`, `id_produit`) values (?,?,?)";
        pste = con.prepareStatement(requete);
        pste.setInt(1,s.getId_client_emetteur());
        pste.setInt(2,s.getId_client_recepteur());
        pste.setInt(3,s.getId_produit());
        pste.executeUpdate();
    }
//    
//    public void insertSuggestionB(Suggestion s) throws SQLException { //PreparedStatement
//        String requete = "insert into suggestion (id_client_emetteur,id_client_recepteur,id_boutique) values (?,?,?)";
//        pste = con.prepareStatement(requete);
//        pste.setInt(1,s.getClient_emetteur().getId());
//        pste.setInt(2,s.getClient_recepteur().getId());
//        pste.setInt(3,s.getId_boutique());
//        pste.executeUpdate();
//    }
    
    public void deleteSuggestionP(int idce, int idcr , int idp) throws SQLException {
        String requete = "delete from suggestion where (id_client_emetteur ="+idce+" AND id_client_recepteur="+idcr+" AND id_patisserie="+idp+")" ;
        ste = con.createStatement();
        ste.executeUpdate(requete);
    }
    
    public void deleteSuggestionProd(int idce, int idcr , int idprod) throws SQLException {
        String requete = "delete from suggestion where (id_client_emetteur ="+idce+" AND id_client_recepteur="+idcr+" AND id_produit="+idprod+")" ;
        ste = con.createStatement();
        ste.executeUpdate(requete);
    }
    
    public void deleteSuggestionB(int idce, int idcr , int idb) throws SQLException {
        String requete = "delete from suggestion where (id_client_emetteur ="+idce+" AND id_client_recepteur="+idcr+" AND id_boutique="+idb+")" ;
        ste = con.createStatement();
        ste.executeUpdate(requete);
    }
    
//    public List<Suggestion> rechercheSuggestionP (String nomp) throws SQLException{
//        String requete ="select * from suggestion where id_patisserie = (select id_patisserie from patisserie where nom ="+nomp+")";
//        ste=con.createStatement();
//        rs=ste.executeQuery(requete);
//        List<Suggestion> list=new ArrayList<>();
//        while (rs.next()){
//            Suggestion s = new Suggestion(rs.getInt("id_client_emetteur"),rs.getInt("id_client_recepteur"),rs.getInt("id_patisserie"));
//            list.add(s);
//        }
//        return list;
//    }
    
    
//    public List<Suggestion> displaySuggestionP () throws SQLException{
//        String requete ="SELECT * FROM suggestion \n" +
//                        "INNER JOIN client AS Client_Emetteur ON suggestion.id_client_emetteur = Client_Emetteur.id \n" +
//                        "INNER JOIN client AS Client_Recepteur ON suggestion.id_client_recepteur = Client_Recepteur.id \n" +
//                        "INNER JOIN patisserie ON suggestion.id_patisserie = patisserie.id \n" +
//                        "WHERE suggestion.id_patisserie IS NOT NULL AND suggestion. ";
//        /*
//        SELECT *
//        FROM suggestion
//        INNER JOIN patisserie ON suggestion.id_patisserie = patisserie.id_patisserie
//        WHERE suggestion.id_patisserie IS NOT NULL;
//        */
//        ste=con.createStatement();
//        rs=ste.executeQuery(requete);
//        List<Suggestion> list=new ArrayList<>();
//        while (rs.next()){
//        Suggestion s = new Suggestion(new Client(rs.getString("Client_Emetteur.nom"),rs.getString("Client_Emetteur.prenom")),new Client(rs.getString("Client_Recepteur.nom")),new Patisserie( rs.getString("patisserie.nom")));
//        //Suggestion s = new Suggestion(new Client(new SimpleStringProperty(rs.getString("Client_Emetteur.Nom"))),new Client(new SimpleStringProperty(rs.getString("Client_Recepteur.Nom"))),new Patisserie(new SimpleStringProperty(rs.getString("patisserie.nom"))));
//         list.add(s);
//        }
//        return list;
//    }
    public List<Suggestion> displaySuggestionProd (int id) throws SQLException{
        String requete ="SELECT * FROM suggestion \n" +
                        "INNER JOIN client AS Client_Emetteur ON suggestion.id_client_emetteur = Client_Emetteur.id \n" +
                        "INNER JOIN client AS Client_Recepteur ON suggestion.id_client_recepteur = Client_Recepteur.id \n" +
                        "INNER JOIN produit ON suggestion.id_produit = produit.id_produit \n" + 
                        "INNER JOIN patisserie ON produit.id_patisserie = patisserie.id \n" +
                        "WHERE suggestion.id_produit IS NOT NULL AND suggestion.id_client_recepteur = "+id;
        ste=con.createStatement();
        rs=ste.executeQuery(requete);
        List<Suggestion> list=new ArrayList<>();
        while (rs.next()){
        Suggestion s = new Suggestion(new Client(rs.getString("Client_Emetteur.nom"),rs.getString("Client_Emetteur.prenom"),rs.getString("Client_Emetteur.email"),rs.getString("Client_Emetteur.adr")),new Client(rs.getString("Client_Recepteur.nom")),new Produit( rs.getString("produit.libellé"),rs.getString("produit.description"),rs.getFloat("produit.prix"),rs.getString("produit.disponiblité"),rs.getString("produit.type_Produit")),new Patisserie (rs.getString("patisserie.nom")));
         list.add(s);
        }
        return list;
    }
//    
//    public List<Suggestion> displaySuggestionB () throws SQLException{
//        String requete ="select * from suggestion where id_boutique IS NOT NULL";
//        ste=con.createStatement();
//        rs=ste.executeQuery(requete);
//        List<Suggestion> list=new ArrayList<>();
//        while (rs.next()){
//            Suggestion s = new Suggestion(rs.getInt("id_client_emetteur"),rs.getInt("id_client_recepteur"),rs.getInt("id_boutique"));
//            list.add(s);
//        }
//        return list;
//    }
}
