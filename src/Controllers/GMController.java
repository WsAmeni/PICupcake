/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Boutique;
import Entities.Client;
import Services.PatisserieDAO;
import Tools.DataSource;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.restfb.types.Location;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class GMController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    @FXML
    private Label labe;

    private void msgbox(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    Connection connection = DataSource.getInstance().getConnection();

    protected StringProperty to = new SimpleStringProperty();
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    GoogleMap map;

    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextField toTextField;
    @FXML
    private Button search;

    List<Location> locc = new ArrayList();
    List<Location> locb = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        PatisserieDAO pd1 = new PatisserieDAO();
        try {
            List<Boutique> lb = readAll();
            for (Boutique b : lb) {
                Location l = new Location();
                l.setName(pd1.findById(b.getId_patisserie()).getNom());
                l.setLatitude(b.getLati());
                l.setLongitude(b.getLongi());
                locb.add(l);
            }
            Location l1 = new Location();
            l1.setName("Lac2");
            l1.setLatitude(36.845220);
            l1.setLongitude(10.272632);
            locc.add(l1);

            Location l2 = new Location();
            l2.setName("Ben Arous");
            l2.setLatitude(36.75306);
            l2.setLongitude(10.21889);
            locc.add(l2);

            Location l3 = new Location();
            l3.setName("Tunis");
            l3.setLatitude(36.806495);
            l3.setLongitude(10.181532);
            locc.add(l3);

            Location l4 = new Location();
            l4.setName("La Marsa");
            l4.setLatitude(36.896480);
            l4.setLongitude(10.310501);
            locc.add(l4);
            System.out.println(locb);
            System.out.println(locc);

        } catch (SQLException ex) {
            Logger.getLogger(GMController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.center(new LatLong(36.806495, 10.181532))
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();

    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {

    }

    @FXML
    private void ok(ActionEvent event) {
        map.clearMarkers();
        String pos = toTextField.getText();

        for (Location l : locc) {

            if (l.getName().equals(pos)) {
                System.out.println("Yaaaaaaaaaaas Workiiiiiiiiiing ::::: ");

                for (Location l1 : locb) {
                    if (distanceTo(l, l1) < 0.5) {

                        System.out.println(l1.getName());
                        labe.setText("         ");

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(new LatLong(l1.getLatitude(), l1.getLongitude()));
                        Marker mark = new Marker(markerOptions);

                        map.addMarker(mark);

                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("<h2>" + l1.getName() + "</h2>"
                                + " Location :" + pos + "<br>");
                        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                        fredWilkeInfoWindow.open(map, mark);

                    }
                }
            } else {
                labe.setText("oops no shops found! try another location!");
            }
        }
    }

    public List<Boutique> readAll() throws SQLException {
        List<Boutique> v = new ArrayList<>();
        Statement ste = connection.createStatement();
        String req = "Select * from boutique;";
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
            Boutique p = new Boutique(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5));
            v.add(p);
        }
        return v;
    }

    public double distanceTo(Location l1, Location l2) {
        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        double lat1 = Math.toRadians(l1.getLatitude());
        double lon1 = Math.toRadians(l1.getLongitude());
        double lat2 = Math.toRadians(l2.getLatitude());
        double lon2 = Math.toRadians(l2.getLongitude());

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }

}
