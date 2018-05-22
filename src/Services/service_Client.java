package Services;

import Tools.DataSource;
import Entities.Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMANI
 */
public class service_Client {
    Connection con =DataSource.getInstance().getConnection();
    private Statement st;
    private ResultSet rs;

    public service_Client() {
        try{
            st=con.createStatement();
            
        }
        catch(SQLException ex){ System.out.println("ex");
    }}
            
    
      
      //AFFICHAGE
      public List<Client>displayAll() throws SQLException{
        String requete = "select * from client";
        st=con.createStatement();
        rs= st.executeQuery(requete);
        List<Client> list=new ArrayList<>();
       
       while (rs.next()){
           Client p = new Client (rs.getInt("id_client"), rs.getString(2), rs.getString("Prenom"));
           list.add(p); }
       return list;
    }    
}
