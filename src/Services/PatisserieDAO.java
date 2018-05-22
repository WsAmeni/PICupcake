/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Entities.Patisserie;
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
public class PatisserieDAO {

    private Connection connection;

    public PatisserieDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    public void insertP(Patisserie p) throws SQLException {

        String requete = "insert into patisserie (login,nom,email,password,image,etat,verif) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);

            ps.setString(1, p.getLogin());
            ps.setString(2, p.getNom());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getPassword());
            ps.setString(5, p.getImage());
            ps.setInt(6, p.getEtat());
            ps.setInt(7, p.getVerif());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PatisserieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteP(int id) throws SQLException {

        PreparedStatement pst;
        String req = "DELETE FROM patisserie WHERE id=?";
        pst = connection.prepareStatement(req);
        pst.setInt(1, id);
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateP(Patisserie p) throws SQLException {
        PreparedStatement pst;
        String req = "update patisserie set login=?,nom=?,email=?,password=?,image=?,etat=?,verif=? where id=?";
        pst = connection.prepareStatement(req);
        pst.setString(1, p.getLogin());
        pst.setString(2, p.getNom());
        pst.setString(3, p.getEmail());
        pst.setString(4, p.getPassword());
        pst.setString(5, p.getImage());
        pst.setInt(6, p.getEtat());
        pst.setInt(7, p.getVerif());
        pst.setInt(8, p.getID());
        pst.executeUpdate();
    }

    public List<Patisserie> readAll() throws SQLException {
        List<Patisserie> v = new ArrayList<Patisserie>();
        Statement ste = connection.createStatement();
        String req = "Select * from patisserie;";
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
            Patisserie p = new Patisserie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
            v.add(p);
        }
        return v;
    }

    public Patisserie findByLogin(String log) {
        Patisserie p = null;
        String requete = "select * from patisserie where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, log);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password,image,etat,verif
            p = new Patisserie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }
        return p;
    }

    public Patisserie findById(int i) throws SQLException {
        Patisserie p = null;
        String requete = "select * from patisserie where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password
                p = new Patisserie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }
        return p;
    }

    public ObservableList<Patisserie> getAll() {
        String req = "select * from patisserie";
        ObservableList<Patisserie> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            Statement ste = connection.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Patisserie p = new Patisserie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Patisserie findByName(String log) {
        Patisserie p = null;
        String requete = "select * from patisserie where nom=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, log);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = new Patisserie(rs.getInt(1));
                return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
