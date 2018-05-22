/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user16
 */
public class ClientDAO {

    private Connection connection;

    public ClientDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    public void insertC(Client c) throws SQLException {
        //	id	login	nom	prenom	email	mdp	adr        
        String requete = "insert into client (login,nom,prenom,sexe,email,mdp,adr,etat) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, c.getLogin());
            ps.setString(2, c.getNom());
            ps.setString(3, c.getPrenom());
            ps.setString(4, c.getSexe());
            ps.setString(5, c.getMail());
            ps.setString(6, c.getMdp());
            ps.setString(7, c.getAddress());
            ps.setInt(8, 1);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteC(int id) throws SQLException {

        PreparedStatement pst;
        String req = "DELETE FROM client WHERE id=?";
        pst = connection.prepareStatement(req);
        pst.setInt(1, id);
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //`id`=[value-1],`login`=[value-2],`nom`=[value-3],`prenom`=[value-4],`email`=[value-5],`mdp`=[value-6],`adr`=[value-7]

    public void updateC(Client c) throws SQLException {
        PreparedStatement pst;
        String req = "update client set login=?,nom=?,prenom=?,sexe=?,email=?,mdp=?,adr=?,etat=?  where id=?";
        pst = connection.prepareStatement(req);
        pst.setString(1, c.getLogin());
        pst.setString(2, c.getNom());

        pst.setString(3, c.getPrenom());
        pst.setString(4, c.getSexe());
        pst.setString(5, c.getMail());
        pst.setString(6, c.getMdp());
        pst.setString(7, c.getAddress());
        pst.setInt(8, c.getEtat());
        pst.setInt(9, c.getId());
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void updateCC(Client c) throws SQLException {
        PreparedStatement pst;
        String req = "update client set login=?,nom=?,prenom=?,email=?,mdp=?,adr=? where id=?";
        pst = connection.prepareStatement(req);
        pst.setString(1, c.getLogin());
        pst.setString(2, c.getNom());
        pst.setString(3, c.getPrenom());
        pst.setString(4, c.getMail());
        pst.setString(5, c.getMdp());
        pst.setString(6, c.getAddress());
        pst.setInt(7, c.getId());
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Client> readAll() throws SQLException {
        List<Client> v = new ArrayList<>();
        Statement ste = connection.createStatement();
        String req = "Select * from client;";
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
            Client p = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
            v.add(p);
        }
        return v;
    }

    public Client findByLogin(String log) {
        Client p = null;
        String requete = "select * from client where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, log);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password
                p = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }
        return p;
    }
        public Client findByName(String nom) {
        Client p = null;
        String requete = "select * from client where nom=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password
                p = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }
        return p;
    }

    public Client findByID(int id){
        Client p = null;
        String requete = "select * from client where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password
                p = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }
        return p;
    }

    public ObservableList<Client> getAll() {
        String req = "select * from client";
        ObservableList<Client> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            Statement ste = connection.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Client p = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
