/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user16
 */
public class Client {

    private int id;
    private String login;
    private String Nom;
    private String prenom;
    private String sexe;

    private String mail;
    private String mdp;
    private String address;
    private int etat;

    private List<String> favoris;//type favoris 
    private List<String> abonements;//type abonement
    private List<String> commandes; //type commande
    private List<Recette> recettes;

    public Client(int id, String login, String Nom, String prenom, String mail, String mdp, String address) {
        this.id = id;
        this.login = login;
        this.Nom = Nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.address = address;
    }

    public Client(String Nom, String prenom, String mail, String address) {
        this.Nom = Nom;
        this.prenom = prenom;
        this.mail = mail;
        this.address = address;
    }

    public Client(int id, String Nom, String prenom, String mail) {
        this.id = id;
        this.Nom = Nom;
        this.prenom = prenom;
        this.mail = mail;
    }
    
    public Client(int id, String Nom, String prenom) {
        this.id = id;
        this.Nom = Nom;
        this.prenom = prenom;
    }

    public Client(int id, String Nom, String prenom, List<String> favoris, List<String> abonements) {
        this.id = id;
        this.Nom = Nom;
        this.prenom = prenom;
        this.favoris = favoris;
        this.abonements = abonements;
    }
    
    

    public Client() {
    }

    public Client(String Nom) {
        this.Nom = Nom;
    }

    public Client(String Nom, String prenom) {
        this.Nom = Nom;
        this.prenom = prenom;
    }

    public Client(int id, String Nom) {
        this.id = id;
        this.Nom = Nom;
    }

    public Client(String login, String Nom, String prenom,String sexe, String mail, String mdp, String address) {
        this.login = login;
        this.Nom = Nom;
        this.prenom = prenom;
       this.sexe=sexe;
       this.mail = mail;
        this.mdp = mdp;
        this.address = address;
    }

    public Client(int id, String login, String Nom, String prenom, String sexe, String mail, String mdp, String address, int etat) {
        this.id = id;
        this.login = login;
        this.Nom = Nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.mail = mail;
        this.mdp = mdp;
        this.address = address;
        this.etat = etat;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SimpleStringProperty getLoginO() {
        return new SimpleStringProperty(login);

    }

    public SimpleStringProperty getNomO() {
        return new SimpleStringProperty(Nom);

    }

    public SimpleStringProperty getPrenomO() {
        return new SimpleStringProperty(prenom);
    }

    public SimpleStringProperty getAddressO() {
        return new SimpleStringProperty(address);

    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Client(int id, String login, String Nom, String prenom, String mail, String mdp, String address, int etat) {
        this.id = id;
        this.login = login;
        this.Nom = Nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.address = address;
        this.etat = etat;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

   
}
