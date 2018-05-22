/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author jihed
 */
public class Suggestion {
    private int id_suggestion;
    private int id_boutique ;
    private int id_produit;
    private Patisserie patisserie;
    private Client client_emetteur;
    private Client client_recepteur;
    private Produit produit;
    private int id_client_emetteur;
    private int id_client_recepteur;

    public Suggestion() {
    }

    public Suggestion(int id_client_emetteur, int id_client_recepteur,int id_produit) {
        this.id_produit = id_produit;
        this.id_client_emetteur = id_client_emetteur;
        this.id_client_recepteur = id_client_recepteur;
    }

    public Suggestion(Client client_emetteur, Client client_recepteur,Produit produit,Patisserie patisserie) {
        this.client_emetteur = client_emetteur;
        this.client_recepteur = client_recepteur;
        this.produit = produit;
        this.patisserie= patisserie;
    }
    public int getId_suggestion() {
        return id_suggestion;
    }

    public void setId_suggestion(int id_suggestion) {
        this.id_suggestion = id_suggestion;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getId_client_emetteur() {
        return id_client_emetteur;
    }

    public void setId_client_emetteur(int id_client_emetteur) {
        this.id_client_emetteur = id_client_emetteur;
    }

    public int getId_client_recepteur() {
        return id_client_recepteur;
    }

    public void setId_client_recepteur(int id_client_recepteur) {
        this.id_client_recepteur = id_client_recepteur;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getId_boutique() {
        return id_boutique;
    }

    public void setId_boutique(int id_boutique) {
        this.id_boutique = id_boutique;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }



    public Patisserie getPatisserie() {
        return patisserie;
    }

    public void setPatisserie(Patisserie patisserie) {
        this.patisserie = patisserie;
    }

    public Client getClient_emetteur() {
        return client_emetteur;
    }

    public void setClient_emetteur(Client client_emetteur) {
        this.client_emetteur = client_emetteur;
    }

    public Client getClient_recepteur() {
        return client_recepteur;
    }

    public void setClient_recepteur(Client client_recepteur) {
        this.client_recepteur = client_recepteur;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id_suggestion;
        hash = 89 * hash + this.id_boutique;
        hash = 89 * hash + this.id_produit;
        hash = 89 * hash + Objects.hashCode(this.patisserie);
        hash = 89 * hash + Objects.hashCode(this.client_emetteur);
        hash = 89 * hash + Objects.hashCode(this.client_recepteur);
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
        final Suggestion other = (Suggestion) obj;
        if (this.id_suggestion != other.id_suggestion) {
            return false;
        }
        if (this.id_boutique != other.id_boutique) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (!Objects.equals(this.patisserie, other.patisserie)) {
            return false;
        }
        if (!Objects.equals(this.client_emetteur, other.client_emetteur)) {
            return false;
        }
        if (!Objects.equals(this.client_recepteur, other.client_recepteur)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Suggestion{" + "client_emetteur=" + client_emetteur.getNom() + ", client_recepteur=" + client_recepteur.getNom() + ", patisserie=" + patisserie.getNom()+ '}';
    }
    
}