package Entities;

/**
 *
 * @author AMANI
 */
public class Points {
    private int id_patisserie;
    private int id_client;
    private int nombre_pts;

    public Points(int id_patisserie, int id_client, int nombre_pts) {
        this.id_patisserie = id_patisserie;
        this.id_client = id_client;
        this.nombre_pts = nombre_pts;
    }

    public Points() {
    }

    
    public int getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(int id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getPoint() {
        return nombre_pts;
    }

    public void setPoint(int nombre_pts) {
        this.nombre_pts = nombre_pts;
    }

    @Override
    public String toString() {
        return "Points{" + "id_patisserie=" + id_patisserie + ", id_client=" + id_client + ", point=" + nombre_pts + '}';
    }
    
    
    
    
}
