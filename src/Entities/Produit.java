/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Donia Khiari
 */
public class Produit {

    private int id_produit;

    private String libellé;

    private String description;

    private float prix;

    private String disponiblité;
              private String quantité;
                private int reduction;
    
    
  

    private int id_patisserie;
    private String type_Produit;
    private String image;

    public Produit(String libellé) {
        this.libellé = libellé;
    }

    public Produit(String libellé, String description, float prix, String quantité, int reduction, int id_patisserie, String image) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.quantité = quantité;
        this.reduction = reduction;
        this.id_patisserie = id_patisserie;
        this.image = image;
    }

    public Produit(int id_produit, String libellé, String description, float prix, String quantité, int reduction, int id_patisserie, String image) {
        this.id_produit = id_produit;
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.quantité = quantité;
        this.reduction = reduction;
        this.id_patisserie = id_patisserie;
        this.image = image;
    }

  

    public Produit(String libellé, String description, float prix, String disponiblité, String type_Produit) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.disponiblité = disponiblité;
        this.type_Produit = type_Produit;
    }

    public Produit(int id_produit, String libellé, String description, float prix, String disponiblité, int id_patisserie, String type_Produit) {
        this.id_produit = id_produit;
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.disponiblité = disponiblité;
        this.id_patisserie = id_patisserie;
        this.type_Produit = type_Produit;
    }

    public Produit(int id_produit, String libellé, String description, float prix, int id_patisserie, String image) {
        this.id_produit = id_produit;
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.id_patisserie = id_patisserie;
        this.image = image;
    }

    
    public Produit(String libellé, String description, float prix) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
    }

    public Produit(String libellé, String description, float prix, String disponiblité, int id_patisserie, String type_Produit, String image) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.disponiblité = disponiblité;
        this.id_patisserie = id_patisserie;
        this.type_Produit = type_Produit;
        this.image = image;
    }

    public Produit(int id_produit, String libellé, String description, float prix, String disponiblité, int id_patisserie, String type_Produit, String image) {
        this.id_produit = id_produit;
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.disponiblité = disponiblité;
        this.id_patisserie = id_patisserie;
        this.type_Produit = type_Produit;
        this.image = image;
    }

    public Produit(String description, float prix, String disponiblité) {
        this.description = description;
        this.prix = prix;
        this.disponiblité = disponiblité;
    }

    public Produit(int id, String libelle) {
         this.id_produit = id;
        this.libellé = libelle;
    }
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Produit() {
    }
    

    public Produit(String libellé, String description, float prix, String disponiblité, int id_patisserie, String type_Produit) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.disponiblité = disponiblité;
        this.id_patisserie = id_patisserie;
        this.type_Produit = type_Produit;
    }

    public Produit(String libellé, String description, float prix, String quantité, int reduction, int id_patisserie) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.quantité = quantité;
        this.reduction = reduction;
        this.id_patisserie = id_patisserie;
    }

   
    
    public SimpleIntegerProperty getIdProperty(){
         SimpleIntegerProperty x=new SimpleIntegerProperty(id_produit);
         return x;
    }
   public SimpleIntegerProperty getReductionProperty(){
         SimpleIntegerProperty x=new SimpleIntegerProperty(reduction);
         return x;
    }
    public SimpleStringProperty getLibelleProperty(){
         SimpleStringProperty x=new SimpleStringProperty(libellé);
         return x;
    }
     public SimpleStringProperty getQuantitéProperty(){
         SimpleStringProperty x=new SimpleStringProperty(quantité);
         return x;
    }
     public SimpleStringProperty getDescriptionProperty(){
         SimpleStringProperty x=new SimpleStringProperty(description);
         return x;
    }
      public SimpleFloatProperty getPrixProperty(){
         SimpleFloatProperty x=new SimpleFloatProperty(prix);
         return x;
    }
 public SimpleStringProperty getDisponibiliteProperty(){
         SimpleStringProperty x=new SimpleStringProperty(disponiblité);
         return x;
    }
  public SimpleIntegerProperty getIDPattiserieProperty(){
        SimpleIntegerProperty x;
             x=    new SimpleIntegerProperty(id_patisserie);
        return x;
    }
   public SimpleStringProperty getTypeProduitProperty(){
         SimpleStringProperty x=new SimpleStringProperty(type_Produit);
         return x;
    }
    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDisponiblité() {
        return disponiblité;
    }

    public void setDisponiblité(String disponiblité) {
        this.disponiblité = disponiblité;
    }


    public String getType() {
        return type_Produit;
    }

    public void setType(String type_Produit) {
        this.type_Produit = type_Produit;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", libell\u00e9=" + libellé + ", description=" + description + ", prix=" + prix + ", disponiblit\u00e9=" + disponiblité + ", quantit\u00e9=" + quantité + ", reduction=" + reduction + ", id_patisserie=" + id_patisserie + ", type_Produit=" + type_Produit + ", image=" + image + '}';
    }

  

    public int getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(int id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

    public String getQuantité() {
        return quantité;
    }

    public void setQuantité(String quantité) {
        this.quantité = quantité;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }



   

   

    
}
