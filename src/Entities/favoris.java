/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Youssef
 */
public class favoris {    
    
    private int id_favoris;
    private int id_client;
    private int id_produit;

    private Produit produit;
    private Client client;

    public favoris(int id_client, int id_produit) {
        this.id_client = id_client;
        this.id_produit = id_produit;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_produit() {
        return id_produit;
    }
    

    public favoris(Produit produit, Client client) {
        this.produit = produit;
        this.client = client;
    }

    public favoris() {
    }

    public favoris(int id_favoris, int id_client, int id_produit) {
        this.id_favoris = id_favoris;
        this.id_client = id_client;
        this.id_produit = id_produit;
    }
    
  
    
    public favoris(int id_favoris, Produit produit, Client client) {
        this.id_favoris = id_favoris;
        this.produit = produit;
        this.client = client;
    }

    public int getId_favoris() {
        return id_favoris;
    }

    public void setId_favoris(int id_favoris) {
        this.id_favoris = id_favoris;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id_favoris;
        hash = 83 * hash + Objects.hashCode(this.produit);
        hash = 83 * hash + Objects.hashCode(this.client);
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
        final favoris other = (favoris) obj;
        if (this.id_favoris != other.id_favoris) {
            return false;
        }
        if (!Objects.equals(this.produit, other.produit)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "favoris{" + "id_favoris=" + id_favoris + ", produit=" + produit + ", client=" + client + '}';
    }
    
    
    
    
}
