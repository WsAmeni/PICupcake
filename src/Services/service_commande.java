package Services;

import Tools.DataSource;
import Entities.Commande;
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
public class service_commande {
     Connection con =DataSource.getInstance().getConnection();
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;

    public service_commande() {
        try{
            st=con.createStatement();
            
        }catch(SQLException ex){ System.out.println("ex");
    }}
    
    
    //INSERT 
     public void inertComm(Commande c) throws SQLException {       
          String requete = "insert into commande (id_produit,libelle, description, prix,id_patisserie,type_produit,quantite) select id_produit,libelle, description, prix,id_patisserie,type_produit,quantite from panier where id_produit=?";
             pst= con.prepareStatement(requete);
             pst.setInt(1,(c.getId()));
             pst.executeUpdate();}
    
//     //DELETE
//       public void DeleteComm(int id) throws SQLException {
//        String requete ="delete from commande where id_commande="+id;
//        st=con.createStatement();
//        st.executeUpdate(requete);
//    }
     
       
     //AFFICHAGE  
     public List<Commande>displayAllCom() throws SQLException{
        String requete = "select * from commande";
        st=con.createStatement();
        rs= st.executeQuery(requete);
        List<Commande> list=new ArrayList<>();
       
       while (rs.next()){
           Commande c = new Commande (rs.getInt("id_commande"), rs.getString(2), rs.getString(3), rs.getFloat(4) ,rs.getString(5),rs.getString(6), rs.getInt(7));
           list.add(c); }
       return list;
    }
     
      public List<Commande>display() throws SQLException{
        String requete = "select libelle, description, prix, id_patisserie, quantite from commande";
        st=con.createStatement();
        rs= st.executeQuery(requete);
        List<Commande> list=new ArrayList<>();
       
       while (rs.next()){
           Commande c = new Commande (rs.getString(1), rs.getString(2), rs.getFloat(3) ,rs.getString(4),rs.getInt(5));
           list.add(c); }
       return list;
    }
     
   
     
    }
