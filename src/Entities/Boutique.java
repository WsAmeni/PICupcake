/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Donia Khiari
 */
public class Boutique {

    private int id_boutique;
    private int numero;
    private double lati;
    private double longi;
    private int id_patisserie;
//    nom numero lat long

    public Boutique(int id_boutique,  int numero, double lati, double longi, int id_patisserie) {
        this.id_boutique = id_boutique;
        
        this.numero = numero;
        this.lati = lati;
        this.longi = longi;
        this.id_patisserie = id_patisserie;
    }

    public Boutique(int numero, double lati, double longi,int id_patisserie ) {
       
        this.numero = numero;
        this.lati = lati;
        this.longi = longi;
        this.id_patisserie = id_patisserie;
    }

    public int getId_boutique() {
        return id_boutique;
    }

    public void setId_boutique(int id_boutique) {
        this.id_boutique = id_boutique;
    }

   

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public int getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(int id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

    @Override
    public String toString() {
        return "Boutique{" + "id_boutique=" + id_boutique +  ", numero=" + numero + ", lati=" + lati + ", longi=" + longi + ", id_patisserie=" + id_patisserie + '}';
    }

    public SimpleIntegerProperty getIdProperty() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(id_boutique);
        return x;
    }

 

    public SimpleIntegerProperty getnumProperty() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(numero);
        return x;
    }

}
