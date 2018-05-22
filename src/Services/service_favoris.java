/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.DataSource;
import Entities.Client;
import Entities.Produit;
import Entities.favoris;
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
 * @author Youssef
 */
public class service_favoris {

    Connection con = DataSource.getInstance().getConnection();
    private Statement st;
    private ResultSet rs;
    PreparedStatement pste;

    public service_favoris() {
        try {
            st = con.createStatement();

        } catch (SQLException ex) {
            System.out.println("ex");
        }
    }

    //insertion
    public void insertfavoris(favoris a) throws SQLException {
        try {
            //PreparedStatement
            String requete = "INSERT INTO `favoris` (`id_client`,`id_produit`) VALUES (?,?)";
            pste = con.prepareStatement(requete);
            
//            pste.setInt(1, a.getProduit().getId_produit());
//            pste.setInt(2, a.getClient().getid());
              pste.setInt(1, a.getId_client());
              pste.setInt(2, a.getId_produit());
//           
           
            pste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(service_favoris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    
    //SUPPRESSION
    public void Deletefavoris(int id) throws SQLException {
        String requete = "delete from favoris where id_favoris=" + id;
        st = con.createStatement();
        st.executeUpdate(requete);
    }



    public List<favoris> displayfavoris(int id) throws SQLException {
        String requete = "SELECT * FROM favoris\n"
                + "INNER JOIN produit AS produit ON favoris.id_produit = produit.id_produit \n"
                + "INNER JOIN client AS client ON favoris.id_client = client.id\n"
                + "WHERE favoris.id_client =" + id;
        st = con.createStatement();
        rs = st.executeQuery(requete);
        List<favoris> listfavoris = new ArrayList<>();
        while (rs.next()) {
        favoris ab = new favoris(rs.getInt("favoris.id_favoris"),new Produit(rs.getString("produit.libellé"), rs.getString("produit.description"), rs.getFloat("produit.prix")), new Client(rs.getString("client.nom")));
            listfavoris.add(ab);
        }
        return listfavoris;
    }
 public List<favoris> getAll(int id) throws SQLException {
        String requete = "SELECT * FROM favoris where id_client =" + id;
        st = con.createStatement();
        rs = st.executeQuery(requete);
        List<favoris> listfavoris = new ArrayList<>();
        while (rs.next()) {
        favoris ab = new favoris(rs.getInt(1),rs.getInt(2),rs.getInt(3));
            listfavoris.add(ab);
        }
        return listfavoris;
    }
  
  public favoris findByIDPC(int idc,int idP) {
        favoris p = null;
        String requete = "SELECT * FROM favoris WHERE id_client=? AND id_produit=?";
       
         try {
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, idc);
            ps.setInt(2, idP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //id,nom,login,email,password
                favoris ab = new favoris(rs.getInt(1),rs.getInt(2),rs.getInt(3));
            return p;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
        }
        return p;
        
    }
}
