/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author AMANI
 */
public class Panier {
    private int id_produit;
    private String libellé;
    private String description;
    private String id_patisserie;
    private float prix;
    private String quantité;

    public Panier() {
    }

    public Panier(String libellé, String quantité) {
        this.libellé = libellé;
        this.quantité = quantité;
    }

    public Panier(String libellé, String description, float prix) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
    }

    public Panier(String libellé, String description, float prix, String quantité) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.quantité = quantité;
    }

    public Panier(String libellé, String description, float prix, String id_patisserie, String quantité) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.quantité= quantité;
        this.id_patisserie=id_patisserie;
    }
    public String getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(String id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

   

    public String getLibellé() {
        return libellé;
    }

    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    public String getQuantité() {
        return quantité;
    }

    public void setQuantité(String quantité) {
        this.quantité = quantité;
    }
    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    
    
    
}
