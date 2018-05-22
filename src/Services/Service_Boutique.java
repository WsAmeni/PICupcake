/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Boutique;
import Entities.Produit;
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
public class Service_Boutique {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    PreparedStatement pste;

    public Service_Boutique() {
        con = DataSource.getInstance().getConnection();

    }

    //Insertion
    //(int id_boutique, String nom, int numero, double lati, double longi, int id_patisserie)
    public void insertBoutique(Boutique b) throws SQLException {
        String requete = "insert into boutique (numero,lat,longi,id_patisserie) values(?,?,?,?) ";
         try {
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, b.getNumero());
            ps.setDouble(2, b.getLati());
            ps.setDouble(3, b.getLongi());
            ps.setInt(4,b.getId_patisserie());
        
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Service_Boutique.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //delete
    public void DeleteBoutique(int id_boutique) throws SQLException {
        String requete = "delete from boutique where id_boutique=" + id_boutique;
        st = con.createStatement();
        st.executeUpdate(requete);
    }
    //affchige 

    public List<Boutique> displayAll(int idp) throws SQLException {

        List<Boutique> v = new ArrayList<>();
        Statement ste = con.createStatement();
        String req = "Select * from boutique where id_patisserie=" + idp;

        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
            Boutique p = new Boutique(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5));
            v.add(p);
        }
        return v;
    }

    public void updateB(Boutique b) throws SQLException {
        String requete = "UPDATE boutique SET numero=? WHERE id_boutique=?";
        pste = con.prepareStatement(requete);

        pste.setInt(1, b.getNumero());
        // pste.setInt(5,b.getId_patisserie());
        pste.setInt(2, b.getId_boutique());

        try {
            pste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Service_Boutique.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
