/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user16
 */
public class Patisserie {

    private int ID;
    private String login;
    private String nom;
    private String email;
    private String password;
    private String image;
    private int etat;
    private int verif;

    private List<String> boutiques;
    private List<String> livraison;
    private List<String> produits;
    private List<String> clients;

    public Patisserie(int ID, String login, String nom, String email, String password, String image) {
        this.ID = ID;
        this.login = login;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public Patisserie(int ID) {
        this.ID = ID;
    }

    public Patisserie(String nom) {
        this.nom = nom;
    }

    public Patisserie(int ID, String nom, String email) {
        this.ID = ID;
        this.nom = nom;
        this.email = email;
    }
   
 

    public Patisserie(int ID, String login, String nom, String email, String password) {
        this.ID = ID;
        this.login = login;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    public Patisserie(int ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }

    public Patisserie(String login, String nom, String email, String password) {

        this.login = login;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    public Patisserie(String login, String nom, String email, String password, String image) {
        this.login = login;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getBoutiques() {
        return boutiques;
    }

    public void setBoutiques(List<String> boutiques) {
        this.boutiques = boutiques;
    }

    public List<String> getLivraison() {
        return livraison;
    }

    public void setLivraison(List<String> livraison) {
        this.livraison = livraison;
    }

    public List<String> getProduits() {
        return produits;
    }

    public void setProduits(List<String> produits) {
        this.produits = produits;
    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Patisserie{" + "ID=" + ID + ", login=" + login + ", nom=" + nom + ", email=" + email + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.ID;
        hash = 19 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patisserie other = (Patisserie) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

    public SimpleIntegerProperty getIDO() {
        return new SimpleIntegerProperty(this.ID);
    }

    public SimpleStringProperty getLogO() {
        return new SimpleStringProperty(this.login);
    }

    public SimpleStringProperty getNomO() {
        return new SimpleStringProperty(this.nom);
    }

    public SimpleStringProperty getEmailO() {
        return new SimpleStringProperty(this.email);
    }

    public SimpleStringProperty getPassO() {
        return new SimpleStringProperty(this.password);
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getVerif() {
        return verif;
    }

    public void setVerif(int verif) {
        this.verif = verif;
    }

    public Patisserie(int ID, String login, String nom, String email, String password, String image, int etat, int verif) {
        this.ID = ID;
        this.login = login;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.image = image;
        this.etat = etat;
        this.verif = verif;
    }

    public Patisserie(int ID, String login, String nom, String email, String password, int etat, int verif) {
        this.ID = ID;
        this.login = login;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.etat = etat;
        this.verif = verif;
    }

  public Patisserie( String login, String nom, String email, String password, String image, int etat, int verif) {
       
        this.login = login;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.image = image;
        this.etat = etat;
        this.verif = verif;
    }
}
