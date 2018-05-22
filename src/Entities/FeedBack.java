/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author jihed
 */
public class FeedBack {
    private int id_feedback ;
    private String description;
    private Patisserie patisserie;
    private Client client;
    private Produit produit;
    private int id_client;
    private int id_patisserie;
    private int id_produit;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
    private String Date = dtf.format(localDate);
    

    public FeedBack() {
    }

    public FeedBack(int id_feedback, String description, Patisserie patisserie, Client client) {
        this.id_feedback = id_feedback;
        this.description = description;
        this.patisserie = patisserie;
        this.client = client;
    }
    public FeedBack(int id_feedback,String Date, Patisserie patisserie, Client client,String description) {
        this.id_feedback = id_feedback;
        this.Date = Date;
        this.description = description;
        this.patisserie = patisserie;
        this.client = client;
    }
    public FeedBack(int id_feedback,String Date, Produit produit, Client client,String description) {
        this.id_feedback = id_feedback;
        this.Date = Date;
        this.description = description;
        this.produit = produit;
        this.client = client;
    }
    public FeedBack(int id_client, int id_produit,String description,String Date) {
        this.Date = Date;
        this.description = description;
        this.id_client = id_client;
        this.id_produit = id_produit;
    }

    public FeedBack(String Date, Patisserie patisserie,Client client, String description) {
        this.Date = Date;
        this.description = description;
        this.client = client;
        this.patisserie = patisserie;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }


    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(int id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

    
    public int getId_feedback() {
        return id_feedback;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public String getDate() {
        return Date;
    }

    public void setCreated_at(String Date) {
        this.Date = Date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patisserie getPatisserie() {
        return patisserie;
    }

    public void setPatisserie(Patisserie patisserie) {
        this.patisserie = patisserie;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id_feedback;
        hash = 89 * hash + Objects.hashCode(this.Date);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.patisserie);
        hash = 89 * hash + Objects.hashCode(this.client);
        hash = 89 * hash + this.id_produit;
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
        final FeedBack other = (FeedBack) obj;
        if (this.id_feedback != other.id_feedback) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        if (!Objects.equals(this.patisserie, other.patisserie)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FeedBack{" + "id_feedback=" + id_feedback + ", created_at=" + Date + ", description=" + description + ", patisserie=" + patisserie + ", client=" + client + '}';
    }

    
    
    
}
