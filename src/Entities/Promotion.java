/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Donia Khiari
 */
public class Promotion {
    private int id_promo;
    private int id_patisserie;
    private int id_produit;
    private String reduction;

    @Override
    public String toString() {
        return "Promotion{" + "id_promotion=" + id_promo + ", id_patisserie=" + id_patisserie + ", id_produit=" + id_produit + ", reduction=" + reduction + '}';
    }

    public Promotion() {
    }
    

    public int getId_promotion() {
        return id_promo;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promo = id_promotion;
    }

    public int getId_promo() {
        return id_promo;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }

    public int getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(int id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

  
    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    public Promotion(int id_promo, int id_patisserie, int id_produit, String reduction) {
        this.id_promo = id_promo;
        this.id_patisserie = id_patisserie;
        this.id_produit = id_produit;
        this.reduction = reduction;
    }

    public Promotion(int id_patisserie, int id_produit, String reduction) {
        this.id_patisserie = id_patisserie;
        this.id_produit = id_produit;
        this.reduction = reduction;
    }


    public Promotion(String reduction) {
        this.reduction = reduction;
    }
    
}
