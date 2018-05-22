/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Entities.Patisserie;
import Entities.Reclamation;
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
public class CRUD_Reclamation {

    PreparedStatement pste;
    ResultSet rs;
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public CRUD_Reclamation() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void insertReclamation(Reclamation r) throws SQLException { //PreparedStatement
        String requete = "insert into reclamation (id_client,id_patisserie,content,date_rec) values (?,?,?,?)";
        pste = con.prepareStatement(requete);
        pste.setInt(1, r.getId_client());
        pste.setInt(2, r.getId_patisserie());
        pste.setString(3, r.getContent());
        pste.setString(4, r.getDate());
        pste.executeUpdate();
    }

    public void deleteReclamation(int idr) throws SQLException {
        String requete = "delete from reclamation where id_reclamation = " + idr;
        ste = con.createStatement();
        ste.executeUpdate(requete);
    }

    public void updateFeedBackP(Reclamation r) throws SQLException {
        String requete = "UPDATE reclamation SET content=? WHERE id_reclamation=? ";
        pste = con.prepareStatement(requete);
        pste.setString(1, r.getContent());
        pste.setInt(2, r.getId_rec());
        pste.executeUpdate();
    }

    public List<Reclamation> displayAllFeedBackP1(int idr) throws SQLException {
        String requete = "SELECT * FROM reclamation \n"
                + "INNER JOIN client ON reclamation.id_client = client.id \n"
                + "INNER JOIN patisserie ON reclamation.id_patisserie = patisserie.id \n"
                + "WHERE reclamation.id_client =" + idr;

        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>();
        while (rs.next()) {
            Reclamation r = new Reclamation(rs.getString("date_rec"), new Patisserie(rs.getInt("patisserie.id"), rs.getString("patisserie.nom")), new Client(rs.getInt("client.id"), rs.getString("client.Nom")), rs.getString("content"));
            list.add(r);
        }
        return list;
    }
    
    public List<Reclamation> displayAllReclamtion() throws SQLException {
        String requete = "SELECT * FROM reclamation \n"
                + "INNER JOIN client ON reclamation.id_client = client.id \n"
                + "INNER JOIN patisserie ON reclamation.id_patisserie = patisserie.id";

        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>();
        while (rs.next()) {
            Reclamation r = new Reclamation(rs.getString("date_rec"), new Patisserie(rs.getInt("patisserie.id"), rs.getString("patisserie.nom"), rs.getString("patisserie.email")), new Client(rs.getInt("client.id"), rs.getString("client.nom"), rs.getString("client.prenom"), rs.getString("client.email")), rs.getString("content"));
            list.add(r);
        }
        return list;
    }
    public List<Reclamation> displayAllReclamtionC(int id) throws SQLException {
        String requete = "SELECT * FROM reclamation \n"
                + "INNER JOIN client ON reclamation.id_client = client.id \n"
                + "INNER JOIN patisserie ON reclamation.id_patisserie = patisserie.id \n" +
                        "WHERE reclamation.id_client = "+id;

        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>();
        while (rs.next()) {
            Reclamation r = new Reclamation(rs.getString("date_rec"), new Patisserie(rs.getInt("patisserie.id"), rs.getString("patisserie.nom")), new Client(rs.getInt("client.id"), rs.getString("client.nom"), rs.getString("client.prenom")), rs.getString("content"));
            list.add(r);
        }
        return list;
    }
}
